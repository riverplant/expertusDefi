package com.riverplant.expertus.defi.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.riverplant.expertus.defi.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findById(Integer id);
	
	Optional<Employee> findByfirstName(String firstName);
	
}
