package com.example.demo.controller;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.SdUserLog;
import com.example.demo.bean.User;
import com.example.demo.ehcache.SdUserLogCache;
import com.example.demo.ehcache.impl.SdUserLogCachImpl;

/**
 * @ResponseBody:将字符串返回
 * @author riverplant
 * @CrossOrigin:通过注解直接配置跨域
 */
//@CrossOrigin(origins = "http://127.0.0.1:8080", maxAge = 3600)
@RestController
@RequestMapping(value = "/index")
public class IndexController {
	/**
	 * 通过注解@Value(value = "${roncoo.secret}")获取配置文件中的值
	 */
	@Value(value = "${river.secret}")
	private String roncoo_secret;

	@Value(value = "${river.number}")
	private int roncoo_number;
	
	@Value(value = "${river.desc}")
	private String river_desc;

	@RequestMapping
	public String index() {
		return "hello index";
	}
	@Autowired
	private SdUserLogCache sdUserLogCache;

	@RequestMapping(value = "/find/{id:\\d+}/{name}")
	public User find(@PathVariable int id, @PathVariable String name) {
		// public User find(@RequestBody User user) {
		User user = new User();
		user.setId(roncoo_number);
		user.setName(roncoo_secret);
		user.setDate(LocalDate.now());
		user.setDes(river_desc);
		return user;
	}
	
	@RequestMapping(value = "/findredis/{id:\\d+}/{name}")
	public SdUserLog findRedis(@PathVariable int id, @PathVariable String name) {
		// public User find(@RequestBody User user) {
		SdUserLog sdUserLog = new SdUserLog();
		sdUserLog.setId(1);
		sdUserLog.setUserIp("127.0.0.2");
		sdUserLog.setUserName(name);
		sdUserLog.setCreateTime(new Date());
		sdUserLogCache.selectById(id);
		//System.out.println(sdUserLogCache.updateById(sdUserLog));
		return sdUserLog;
	}
	@RequestMapping(value = "/error")
	public String error() {
		throw new RuntimeException("测试异常");
	}
}
