package com.riverplant.rspringboot.listener;

import java.time.LocalDateTime;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.FilterRegistration.Dynamic;

import com.riverplant.rspringboot.filter.LogFilter;

public class MyStarupListener implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Dynamic dynamic = sce.getServletContext().addFilter("logFilter", LogFilter.class);
		
		System.out.println("Myapp starup at "+LocalDateTime.now().toString());
	}
}
