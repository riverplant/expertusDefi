package com.riverplant.rspringboot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * controller拦截器，在controller调用前调用该拦截器
 * @author riverplant
 *
 */

public class MyHandlerInteceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {	
		System.out.println("==============MyHandlerInteceptor.preHandle========"+handler.getClass());
		return true;//如果返回false将不会进入controller
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("==============MyHandlerInteceptor.postHandle========");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override//在controller调用后调用该方法
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("==============MyHandlerInteceptor.afterCompletion========");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
