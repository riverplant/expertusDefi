package com.example.demo;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
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
//@SpringBootApplication
public class SpringBootDemoApplication2 implements ServletContextInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication2.class, args);
	}

	/**
	 * 将servlet/filter/listener的注入工作在这里完成
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addServlet("myservlet", new MyServlet()).addMapping("/river");
		servletContext.addFilter("myfilter", new MyFilter())
		.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, "myservlet");
		servletContext.addListener(new MyListener());

	}

}
