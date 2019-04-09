package com.example.java8.dto;

public class Apple {
	private String color;
	private long weight;
	
	public Apple() {

	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public long getWeight() {
		return weight;
	}
	public void setWeight(long weight) {
		this.weight = weight;
	}
	public Apple(String color, long weight) {
		super();
		this.color = color;
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "Apple [color=" + color + ", weight=" + weight + "]";
	}
	
	

}
