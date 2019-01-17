package com.river.core.jdk8.Lambda.dao;

public class Apple {
private String color;
private long weight;

public Apple() {
	super();
}
public Apple(String color, long weight) {
	super();
	this.color = color;
	this.weight = weight;
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
@Override
public String toString() {
	return "Apple [color=" + color + ", weight=" + weight + "]";
}

}
