package com.example.demo.common;

public class Node<T> {
	//不希望用户传入后再改变,没有set方法
private final T value;
private Node<T> next;


public Node(T value, Node<T> next) {
	super();
	this.value = value;
	this.next = next;
}
/**
 * 构造第一个Node
 * @param value
 */
public Node(T value) {
	super();
	this.value = value;
	this.next = null;
}
public T getValue() {
	return value;
}

public Node<T> getNext() {
	return next;
}
public void setNext(Node<T> next) {
	this.next = next;
}
@Override
public String toString() {
	return "Node [value=" + value + ", next=" + next + "]";
}

}
