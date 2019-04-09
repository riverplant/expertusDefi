package com.riverplant.rspringboot.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
@WebFilter("/*")
public class LogFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("income log filter"+request.getRemoteHost());
		chain.doFilter(request, response);		
	}	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}
}
