package com.example.demo.util.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author riverplant
 *
 */
/**
 * filterName String 指定过滤器的 name 属性，等价于 <filter-name>
 * 
 * value String[] 该属性等价于 urlPatterns 属性。但是两者不应该同时使用。
 * 
 * urlPatterns String[] 指定一组过滤器的 URL 匹配模式。等价于 <url-pattern> 标签。
 * 
 * servletNames String[] 指定过滤器将应用于哪些 Servlet。取值是 @WebServlet 中的 name 属性的取值，或者是
 * web.xml中<servlet-name> 的取值。
 * 
 * @author riverplant
 *
 */
//@WebFilter(urlPatterns = "/*")
//@WebFilter(filterNames = {"myfilter1", "myfilter2"})
@WebFilter(filterName = "myfilter")
public class MyFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("XsstFilter init...");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("do filter...");
		chain.doFilter((HttpServletRequest) request, response);

	}

	@Override
	public void destroy() {
		System.out.println("XsstFilter destroy...");

	}

}
