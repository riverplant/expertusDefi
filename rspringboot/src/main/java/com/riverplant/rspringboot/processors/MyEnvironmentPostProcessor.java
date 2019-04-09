package com.riverplant.rspringboot.processors;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
/**
 * 
 * @author riverplant
 *
 */
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {
	public static final String SPRINGBOOTPROPERTIE = "springboot.properties";
	
	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		
		//String  filePath = this.getClass().getClassLoader().getResource(SPRINGBOOTPROPERTIE).getPath();
		String filePath = Thread.currentThread().getContextClassLoader().getResource(SPRINGBOOTPROPERTIE).getPath();
		System.out.println(filePath);
		try(InputStream stream = new FileInputStream(new File(filePath))) {
			 Properties source = new Properties();
			source.load(stream);
			PropertiesPropertySource propertySource = new PropertiesPropertySource("myPropertiesPropertySource", source);
			environment.getPropertySources().addLast(propertySource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
