package com.jyx.dto;
/**
 * dto包下的类都是输入输出数据的
 * @author riverplant
 *
 */
public class User {

	private String uuid;
	private String username;
	private String password;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	public User(String uuid, String username, String password) {
		super();
		this.uuid = uuid;
		this.username = username;
		this.password = password;
	}
	public User() {
		super();
	}
	
}
