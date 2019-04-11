package com.riverplant.expertus.defi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.riverplant.expertus.defi.domain.Employee;
@CacheConfig(cacheNames="defiCache")
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
	@Cacheable(key= "#p0")
	Optional<Employee> findById(Long id);
	
	 @Override
	 @Cacheable(key= "#p0")
	 List<Employee> findAll(); 
	 
	@Cacheable(key= "#p0")
	@Query("from Employee emp where emp.firstName like?1 and emp.email = ?2 order by emp.id desc")
	Page<Employee> findEmployees(String firstName,String email,Pageable pageable);
	
	@CachePut(key= "#p0")
	@Query("update Employee emp set emp.firstName = ?1 where emp.id =?2")
	@Modifying
	Integer updateEmployees(String firstName,Long id);
	
	@Override
	@CacheEvict
	void delete(Employee entity) ;
	
	@Override
	@CacheEvict
	void deleteById(Long id);
}
