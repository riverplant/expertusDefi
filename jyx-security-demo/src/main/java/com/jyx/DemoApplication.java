package com.jyx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo启动类
 * @author riverplant
 *
 */
@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);//Spring程序启动
	}
	@GetMapping("/hello")
	public String hello() {
		return "spring security";
	}

}
