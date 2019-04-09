package com.riverplant.expertus.defi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.riverplant.expertus.defi.interfaces.EmployeeRepository;
import com.riverplant.expertus.defi.outils.H2DBUtil;
/**
 * Hello world!
 *
 */
@EnableAsync
@SpringBootApplication(scanBasePackages="com.riverplant.expertus.defi")
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
    
    @Bean
    CommandLineRunner initializeDatabase(EmployeeRepository employeeRepository) {
        return (evt) -> {
        	
        };
    }
}
