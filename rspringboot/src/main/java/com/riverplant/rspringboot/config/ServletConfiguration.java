package com.riverplant.rspringboot.config;

import java.util.Arrays;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.riverplant.rspringboot.filter.BookeFilter;
import com.riverplant.rspringboot.listener.MyStarupListener;
import com.riverplant.rspringboot.servlet.BookServlet;

@SpringBootConfiguration
public class ServletConfiguration {
    @Bean
	public ServletRegistrationBean<BookServlet> createServelt() {
		ServletRegistrationBean<BookServlet> servletRegistrationBean  = new ServletRegistrationBean<>(new BookServlet(),"/book.do");
		return  servletRegistrationBean;
	}
    @Bean
    public FilterRegistrationBean<BookeFilter > createFilter(){
    	FilterRegistrationBean<BookeFilter> filterRegistrationBean = new FilterRegistrationBean<>();
    	filterRegistrationBean.setFilter(new BookeFilter());
    	filterRegistrationBean.setUrlPatterns(Arrays.asList("/book.do"));
    	return filterRegistrationBean;
    }
    
//    @Bean
//    public ServletListenerRegistrationBean<MyStarupListener> createListener(){
//    	ServletListenerRegistrationBean<MyStarupListener> filterRegistrationBean = new ServletListenerRegistrationBean<>();
//    	filterRegistrationBean.setListener(new MyStarupListener());
//    	return filterRegistrationBean;
//    }
}
