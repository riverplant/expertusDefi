package com.riverplant.rspringboot.listener;

import java.time.LocalDateTime;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.riverplant.rspringboot.filter.LogFilter;
//@WebListener
public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Dynamic dynamic = sce.getServletContext().addFilter("logFilter", LogFilter.class);
		
		System.out.println("app starup at "+LocalDateTime.now().toString());
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}
