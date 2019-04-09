package com.riverplant.rspringboot.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="tomcat")
public class TomcatProperties {
    
	private List<String> hots = new ArrayList<>();
	private String port;
	

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public List<String> getHots() {
		return hots;
	}

	public void setHots(List<String> hots) {
		this.hots = hots;
	}

	@Override
	public String toString() {
		return "TomcatProperties [hots=" + hots + ", port=" + port + "]";
	}


	
}
