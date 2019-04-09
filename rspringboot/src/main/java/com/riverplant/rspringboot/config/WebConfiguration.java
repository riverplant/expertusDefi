package com.riverplant.rspringboot.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import com.riverplant.rspringboot.interceptor.MyHandlerInteceptor;
/**
 * 配置拦截器interceptor
 * @author riverplant
 *
 */
//@SpringBootConfiguration
public class WebConfiguration extends WebMvcConfigurationSupport {
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyHandlerInteceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/login","/ref");
		super.addInterceptors(registry);
	}
}
