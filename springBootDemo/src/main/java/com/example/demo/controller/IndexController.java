package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.User;

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
	@RequestMapping(value = "/error")
	public String error() {
		throw new RuntimeException("测试异常");
	}
}
