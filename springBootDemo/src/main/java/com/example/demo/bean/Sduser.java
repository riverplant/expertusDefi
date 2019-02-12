package com.example.demo.bean;

import java.util.Date;
/**
 * 实体类
 * @author riverplant
 *
 */
public class Sduser {

	private int id;
	private String name;
	private Date create_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	@Override
	public String toString() {
		return "Sduser [id=" + id + ", name=" + name + ", create_time=" + create_time + "]";
	}
	
}
