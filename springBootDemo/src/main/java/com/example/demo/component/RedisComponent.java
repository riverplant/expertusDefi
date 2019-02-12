package com.example.demo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisComponent {
    @Autowired
	private StringRedisTemplate stringRedisTemplate;//springdata中封装
    
    public void set(String key,String value) {
    	ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
    	if(!stringRedisTemplate.hasKey(key)) {
    		ops.set(key, value);
    		System.out.println("set key:"+key+" success");
    	}else {
    		System.out.println("value of the key:"+key+" is "+ops.get(key));
    	}
    }
    
    public String get(String key) {
    	
    	return stringRedisTemplate.opsForValue().get(key);
    }
    
    public void delet(String key) {
    	stringRedisTemplate.delete(key);
    }
}
