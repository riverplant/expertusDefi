package com.example.demo;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.example.demo.util.filter.MyFilter;
import com.example.demo.util.listener.MyListener;
import com.example.demo.util.servlet.MyServlet;

/**
 * extends SpringBootServletInitializer:相当于web.xml
 * 
 * @author riverplant
 *
 */
//添加该注解可以自动注入
//@ServletComponentScan
//@SpringBootApplication
public class SpringBootDemoApplication3 {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication3.class, args);
	}
	
}
