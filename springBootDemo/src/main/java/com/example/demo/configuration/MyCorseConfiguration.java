package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
/**
 * 全局设置
 * @author riverplant
 *
 */
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 通过实现WebMvcConfigurer配置跨域
 * @author riverplant
 *
 */
@Configuration
public class MyCorseConfiguration implements WebMvcConfigurer{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/index/**").allowedOrigins("http://127.0.0.1:8080");
	}
}
