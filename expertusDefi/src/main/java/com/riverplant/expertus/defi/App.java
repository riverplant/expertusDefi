package com.riverplant.expertus.defi;
import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.riverplant.expertus.defi.domain.Employee;
import com.riverplant.expertus.defi.impl.MyRepositoryImpl;
import com.riverplant.expertus.defi.repository.EmployeeRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * username: user
 * password: passw0rd
 * http://localhost:8080/swagger-ui.html
 */
@SpringBootApplication
@EnableSwagger2
@EnableCaching
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
    	          new Employee("test1", "defi", "test1@gmail.com","123456", new Date()),
    	          new Employee("test2", "defi", "test2@gmail.com","123456", new Date()),
    	          new Employee("test3", "defi", "test3@gmail.com","123456", new Date())
    	  ));
};
    }
}
