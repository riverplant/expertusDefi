package com.example.demo.configuration;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * redis 自定义缓存管理器
 * 
 * @author wujing
 */
@Configuration
public class MyRedisCacheConfiguration extends CachingConfigurerSupport {
//	@Autowired
//	private RedisTemplate redisTemplate;
	/**
	 * 自定义缓存管理器.相当于缓存的配置文件
	 * 
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		 RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
	                .entryTtl(Duration.ofMinutes(10)); // 设置缓存有效期十分钟

		RedisCacheManager redisCacheManager  =   RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();
		//redisTemplate.opsForValue().set("riverCache", value, 2000L, TimeUnit.MILLISECONDS);
		return redisCacheManager;
    }	
	
	/**
	 * 自定义缓存key. 此方法将会根据类名+方法名+所有参数的值生成唯一的一个key,即使@Cacheable中的value属性一样，key也会不一样。
	 */
	@Override
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object o, Method method, Object... objects) {
				StringBuilder sb = new StringBuilder();
				sb.append(o.getClass().getName());
				sb.append(method.getName());
				for (Object obj : objects) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}
}
