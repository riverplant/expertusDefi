package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import com.example.demo.util.filter.MyFilter;
import com.example.demo.util.listener.MyListener;
import com.example.demo.util.servlet.MyServlet;
/**
 * extends SpringBootServletInitializer:相当于web.xml
 * @author riverplant
 *
 */
@EnableJms//启用ActiveMQ
@EnableCaching//该注解用于启动缓存
@ServletComponentScan//该注解用于注入filter\servlet\lisentner
@SpringBootApplication
public class SpringBootDemoApplication extends SpringBootServletInitializer{
//	@Bean//在这里注册MyServlet
//	public ServletRegistrationBean<MyServlet> servletRegistrationBean() {
//		return new ServletRegistrationBean<MyServlet>(new MyServlet(),"/river");
//	}
//	
//	@Bean//在这里注册MyFilter
//	public FilterRegistrationBean<MyFilter> filterRegistrationBean() {
//		//只拦截MyServlet
//		//return new FilterRegistrationBean<MyFilter>(new MyFilter(),servletRegistrationBean());
//		//拦截全部请求
//		return new FilterRegistrationBean<MyFilter>(new MyFilter());
//	}
//	
//	@Bean//在这里注册MyFilter
//	public ServletListenerRegistrationBean<MyListener> servletListenerRegistrationBean() {
//		//拦截MyServlet
//		//return new FilterRegistrationBean<MyFilter>(new MyFilter(),servletRegistrationBean());
//		//拦截全部请求
//		return new ServletListenerRegistrationBean<MyListener>(new MyListener());
//	}
	/**
	 * builder.sources(SpringBootDemoApplication.class)
	 * 相当于配置
	 * 适合3.0以上
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootDemoApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

}

