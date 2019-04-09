package com.example.boot.spring4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.boot.spring4.beans.Jeep;

@Configuration
public class JeepConfig {
    @Bean
	public Jeep createJeep() {
		return new Jeep();
	}
}
