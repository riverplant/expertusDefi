package com.river.core.jdk8.stream.demo;

public class Trader {
private final String name;
private final String city;

public String getName() {
	return name;
}
public String getCity() {
	return city;
}
public Trader(String name, String city) {
	super();
	this.name = name;
	this.city = city;
}
@Override
public String toString() {
	return "Trader [name=" + name + ", city=" + city + "]";
}
}
