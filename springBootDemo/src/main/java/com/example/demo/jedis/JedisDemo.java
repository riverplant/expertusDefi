package com.example.demo.jedis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis的测试
 * 
 * @author riverplant
 *
 */
public class JedisDemo {
	@Test
	public void demo1() {

		Jedis jedis = new Jedis("127.0.0.1", 6379);
		// 保存数据
		jedis.set("name", "riverplant");
		// 获取数据
		jedis.get("name");
		jedis.close();// 关闭资源
	}

	/**
	 * 使用连接池
	 */
	@Test
	public void demo2() {
		// 获得连接池配置对象
		JedisPoolConfig confgi = new JedisPoolConfig();
		// 设置最大连接数
		confgi.setMaxTotal(30);
		// 设置最大空闲连接数
		confgi.setMaxIdle(10);
		try (JedisPool pool = new JedisPool(confgi, "127.0.0.1", 6379); // 获得连接池
			 Jedis jedis = pool.getResource();) {// 通过连接池获得连接// 获得核心对象
			jedis.set("name", "张三");
			jedis.get("name");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
