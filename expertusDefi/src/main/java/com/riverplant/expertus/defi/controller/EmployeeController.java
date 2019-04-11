package com.riverplant.expertus.defi.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.Callable;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import com.riverplant.expertus.defi.domain.Employee;
import com.riverplant.expertus.defi.dto.EmployeeInfo;
import com.riverplant.expertus.defi.dto.EmployeeInfo.EmployeeDetailView;
import com.riverplant.expertus.defi.dto.EmployeeInfo.EmployeeListView;
import com.riverplant.expertus.defi.outils.MD5Util;
import com.riverplant.expertus.defi.repository.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	private static final ModelMapper modelMapper = new ModelMapper();
	@GetMapping
	@CrossOrigin(origins = "http://localhost:8080")
	@JsonView(EmployeeListView.class)
	public Collection<EmployeeInfo> listEmployees(
			@PageableDefault(size = 10, page = 0) Pageable pageable) {
		Collection<EmployeeInfo> employees = new ArrayList<>();
		employeeRepository.findAll(pageable).stream().forEach(employee->{
			EmployeeInfo einfo  = modelMapper.map(employee, EmployeeInfo.class);
			employees.add(einfo);
		});
		
		return employees;
	}

	@GetMapping("/{id:\\d+}")
	@CrossOrigin(origins = "http://localhost:8080")
	@JsonView(EmployeeDetailView.class)
	public Callable<EmployeeInfo> getEmployeesInfo(@PathVariable Long id ) throws SQLException {
		Callable<EmployeeInfo> result = ()->{
			Employee emp = employeeRepository.findById(id).get();
			EmployeeInfo einfo = modelMapper.map(emp, EmployeeInfo.class);
			return einfo;
		};
		return result;
	}

	@PostMapping
	@CrossOrigin(origins = "http://localhost:8080")
	public EmployeeInfo createEmployees(@Valid @RequestBody EmployeeInfo employeeInfo, BindingResult result)
			throws SQLException {
		if (result.hasErrors()) {
			result.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
			throw new RuntimeException("NotBlank...");
		}
		String password = MD5Util.md5Hex(employeeInfo.getPassword());
		employeeRepository.save(
  	          new Employee(employeeInfo.getFirstName(), 
  	        		  employeeInfo.getLastName(), employeeInfo.getEmail(),password,new Date())
  	  );
		return employeeInfo;
	}

	@PutMapping
	@CrossOrigin(origins = "http://localhost:8080")
	public EmployeeInfo updateEmployees(@Valid @RequestBody EmployeeInfo employeeInfo, BindingResult result) {
		if (result.hasErrors()) {
			result.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
			throw new RuntimeException("NotBlank...");
		}
		String password = MD5Util.md5Hex(employeeInfo.getPassword());
		System.out.println("pw="+password);
		Employee emp = modelMapper.map(employeeInfo, Employee.class);
		emp.setPassword(password);
		employeeRepository.save(emp);
		EmployeeInfo empVo = modelMapper.map(emp, EmployeeInfo.class);
		return empVo;
	}
	
	@DeleteMapping("/{id:\\d+}")
	@CrossOrigin(origins = "http://localhost:8080")
	public void delet(@PathVariable Long id) {
		employeeRepository.deleteById(id);
	}
}
