package com.example.boot.spring4.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.boot.spring4.dao.UserDao;
import com.example.boot.spring4.service.UserService;

@Component//表示该类是一个组件,无确定角色时使用
public class User {
    @Autowired//将组件注入
	private UserService userService;
    
    public void show() {
    	System.out.println(userService);
    }

    public void init() {
    	System.out.println("user init");
    }
	@Override
	public String toString() {
		return "User [userService=" + userService + "]";
	}
	
}
