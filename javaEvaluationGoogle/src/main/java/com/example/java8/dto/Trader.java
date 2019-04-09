package com.example.java8.dto;

/**
 * 
 * @author riverplant
 *
 */
public class Trader {
	private final String name;
	private final String city;

	public Trader(String name, String city) {
		this.name = name;
		this.city = city;
	}

	@Override
	public String toString() {
		return "Trader [name=" + name + ", city=" + city + "]";
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

}
