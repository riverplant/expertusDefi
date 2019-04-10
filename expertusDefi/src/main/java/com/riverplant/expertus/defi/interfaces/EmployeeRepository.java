package com.riverplant.expertus.defi.interfaces;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.riverplant.expertus.defi.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
	Optional<Employee> findById(Long id);
	
//	@EntityGraph("Employee.fetch.department.and.projects")
//	Optional<Employee> findByFirstName(String firstName);
	
	@Query("from Employee emp where emp.firstName like?1 and emp.email = ?2 order by emp.id desc")
	Page<Employee> findEmployees(String firstName,String email,Pageable pageable);
	
	@Query("update Employee emp set emp.firstName = ?1 where emp.id =?2")
	@Modifying
	Integer updateEmployees(String firstName,Long id);
}
