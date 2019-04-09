package com.riverplant.rspringboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author riverplant
 *
 */
@Component
public class JdbcConfig {
    @Value("${url}")
	private String url;
    @Value("${driverClassName}")
	private String driverClassName;

	@Override
	public String toString() {
		return "JdbcConfig [url=" + url + ", driverClassName=" + driverClassName + "]";
	}
	
	
	
	
	
}
