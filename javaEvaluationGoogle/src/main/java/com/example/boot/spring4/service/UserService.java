package com.example.boot.spring4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.boot.spring4.dao.UserDao;

@Service//用在业务逻辑层
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public void show() {
    	System.out.println(userDao);
    }

	@Override
	public String toString() {
		return "UserService [userDao=" + userDao + "]";
	}
	
}
