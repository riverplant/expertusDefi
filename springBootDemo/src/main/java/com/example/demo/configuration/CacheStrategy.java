package com.example.demo.configuration;

import java.util.Map;

import javax.annotation.PostConstruct;

import com.google.common.collect.Maps;

public class CacheStrategy {
	public static final String KEY = "_TEST";

	public static Long CACHE_TIME = 10000L;
	private Map<String, Long> expiresMap = null;

	@PostConstruct
	public void init(){
	    expiresMap = Maps.newHashMap();
	    expiresMap.put(KEY, CACHE_TIME);
	}

	public Map<String, Long> getExpiresMap(){
	    return this.expiresMap;
	}
}
