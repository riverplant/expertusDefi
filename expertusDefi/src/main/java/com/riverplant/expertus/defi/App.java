package com.riverplant.expertus.defi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.riverplant.expertus.defi.domain.Employee;
import com.riverplant.expertus.defi.impl.MyRepositoryImpl;
import com.riverplant.expertus.defi.interfaces.EmployeeRepository;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = MyRepositoryImpl.class)
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
    
    @Bean
    CommandLineRunner initializeDatabase(EmployeeRepository employeeRepository) {
        return (evt) -> {
          employeeRepository.saveAll(Arrays.asList(
    	          new Employee("test1", "defi", "test1@gmail.com","123456"),
    	          new Employee("test2", "defi", "test2@gmail.com","123456"),
    	          new Employee("test3", "defi", "test3@gmail.com","123456")
    	  ));
};
    }
}
