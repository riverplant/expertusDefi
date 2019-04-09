package com.riverplant.expertus.defi.controller;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riverplant.expertus.defi.domain.Employee;
import com.riverplant.expertus.defi.interfaces.EmployeeRepository;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@RequestMapping("/employees")
	@CrossOrigin(origins = "http://localhost:4200")
	public Collection<Employee> listEmployees() throws SQLException {
		List<Employee> employees = employeeRepository.findAll();
		// employees.stream().forEach(employe->employe.setEmail(emailToMd5(employe.getEmail())));
		return employees;
	}
	
	@RequestMapping("/addemployees")
	@CrossOrigin(origins = "http://localhost:4200")
	public Collection<Employee> addEmployees() throws SQLException {
		List<Employee> employees = employeeRepository.findAll();

		return employees;
	}
}
