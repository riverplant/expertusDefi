package com.riverplant.expertus.defi.repostory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import com.riverplant.expertus.defi.BaseTest;
import com.riverplant.expertus.defi.domain.Employee;
import com.riverplant.expertus.defi.repository.EmployeeRepository;

public class RepostoryTest extends BaseTest{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
	public void findByPageable() {
		
		Pageable pageable = PageRequest.of(0, 10, Direction.DESC, "id");
		Page<Employee> emps = employeeRepository.findAll(pageable);
		emps.get().forEach(System.out::println);
		}
	@Test
	public void findEmployees() {
		
		Pageable pageable = PageRequest.of(0, 10, Direction.DESC, "id");
		Page<Employee> emps = employeeRepository.findEmployees("test2", "jsmith@gmail.com", pageable);
		emps.get().forEach(System.out::println);
		}
	
	@Test
	public void findByExemple() {
		
		Pageable pageable = PageRequest.of(0, 10, Direction.DESC, "id");
		Employee emp =new Employee();
		emp.setEmail("jsmith@gmail.com");
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);
		Example<Employee>example = Example.of(emp, matcher);
		employeeRepository.findAll(example,pageable);
		}
	
	@Test
	public void updateEmployees() {
		
		employeeRepository.updateEmployees("newTester01", 1L);
		employeeRepository.findAll().stream().forEach(System.out::println);
		}
	
	@Test
	public void test() {
		
		Specification<Employee> spec = new Specification<Employee>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("email"),"jsmith@gmail.com");
			}	
		};
		employeeRepository.findOne(spec);
		}
	
	@Test
	public void Specification() {
		
		Specification<Employee> spec = new Specification<Employee>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p1 = cb.equal(root.get("email"),"jsmith@gmail.com");
				Predicate p2 = cb.equal(root.get("first_name"),"test2");
				Predicate p3 = cb.and(p1,p2);
				return p3;
			}	
		};
		employeeRepository.findOne(spec);
		}
	
	
}
