package com.riverplant.expertus.defi.interceptor;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
 * @author riverplant
 * RestApi
 */
@Component
public class MyTimeInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("class name = "+ ((HandlerMethod)handler).getBean().getClass().getName());
		System.out.println("method name = "+ ((HandlerMethod)handler).getMethod().getName());
		
		request.setAttribute("startTime", new Date().getTime());
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Service processing takes "+(new Date().getTime() - (Long)request.getAttribute("startTime"))+" seconds");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("Service processing takes "+(new Date().getTime() - (Long)request.getAttribute("startTime"))+" seconds");
//		System.out.println("Exception: "+ ex.getMessage());
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
