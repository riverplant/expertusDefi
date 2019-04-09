package com.riverplant.rspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;
/**
 * 
 * @author riverplant
 * 获取配置的方法
 */
//@Component
public class UserConifg {
	@Autowired
	private ConfigurableEnvironment env;
	@Value("${river.port}")
	private String port;
	
	@Value("${river.app.name}")
	private String name;

	public String getIp() {
		String ip = env.getProperty("river.local.ip");
		//System.out.println("river.local.ip = " + ip);
		return ip;
	}

	@Override
	public String toString() {
		return "UserConifg [river.local.ip=" + getIp() + ", name=" +name+ ", port=" + port + "]";
	}
	
}
