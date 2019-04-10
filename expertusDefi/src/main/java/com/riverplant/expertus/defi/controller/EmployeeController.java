package com.riverplant.expertus.defi.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.riverplant.expertus.defi.domain.Employee;
import com.riverplant.expertus.defi.dto.EmployeeCondition;
import com.riverplant.expertus.defi.dto.EmployeeInfo;
import com.riverplant.expertus.defi.dto.EmployeeInfo.EmployeeDetailView;
import com.riverplant.expertus.defi.dto.EmployeeInfo.EmployeeListView;
import com.riverplant.expertus.defi.interfaces.EmployeeRepository;
import com.riverplant.expertus.defi.outils.MD5Util;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping
	@CrossOrigin(origins = "http://localhost:4200")
	@JsonView(EmployeeListView.class)
	public Collection<Employee> listEmployees(EmployeeCondition employeeCondition,
			@PageableDefault(size = 10, page = 0) Pageable pageable) {
		List<Employee> employees = employeeRepository.findAll(pageable).getContent();
		return employees;
	}

	@GetMapping("/{id:\\d+}")
	@CrossOrigin(origins = "http://localhost:4200")
	@JsonView(EmployeeDetailView.class)
	public EmployeeInfo getEmployeesInfo(@PathVariable Long id , BindingResult result, @CookieValue String token ,@RequestHeader String auth) throws SQLException {
		if (result.hasErrors()) {
			result.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
			throw new RuntimeException("test...");
		}
		Employee emp = employeeRepository.findById(id).get();
		EmployeeInfo einfo = new EmployeeInfo();
		einfo.setId(emp.getId());
		einfo.setEmail(emp.getEmail());
		einfo.setFirstName(emp.getFirstName());
		einfo.setLastName(emp.getLastName());
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd hh:mm:ss");
		einfo.setCreatedTime(new Date());
		return einfo;
	}

	@PostMapping
	@CrossOrigin(origins = "http://localhost:4200")
	public EmployeeInfo createEmployees(@Valid @RequestBody EmployeeInfo employeeInfo, BindingResult result)
			throws SQLException {
		if (result.hasErrors()) {
			result.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
			throw new RuntimeException("NotBlank...");
		}
		System.out.println(employeeInfo.getCreatedTime());
		employeeInfo.setId(4L);
		String password = MD5Util.md5Hex(employeeInfo.getPassword());
		employeeInfo.setPassword(password);
		return employeeInfo;
	}

	@PutMapping
	@CrossOrigin(origins = "http://localhost:4200")
	public EmployeeInfo updateEmployees(@Valid @RequestBody EmployeeInfo employeeInfo, BindingResult result) {
		if (result.hasErrors()) {
			result.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
			throw new RuntimeException("NotBlank...");
		}
		System.out.println(employeeInfo.getCreatedTime());
		employeeInfo.setId(4L);
		String password = MD5Util.md5Hex(employeeInfo.getPassword());
		employeeInfo.setPassword(password);
		return employeeInfo;
	}
	
	@DeleteMapping("/{id:\\d+}")
	@CrossOrigin(origins = "http://localhost:4200")
	public void delet(@PathVariable Long id) {
		employeeRepository.deleteById(id);
	}
}
