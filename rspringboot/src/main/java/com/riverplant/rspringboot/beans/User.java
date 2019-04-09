package com.riverplant.rspringboot.beans;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
@Component
public class User implements Runnable{
	private String username;
	
	private String password;
	
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}


	public User() {
		super();
	}


	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	@Async//指定该方法为异步
	public void run() {
		try {
			for(int i=0;i<10;i++) {
				System.out.println("i="+i);
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
