package com.river.java.question02;

public class Node {
	String name;
	Node children[];
	//Node n3 = new Node("9", new Node("4"));
	public Node(String name, Node... children) {
		this.name = name;
		if (children != null) {
			this.children = new Node[children.length];
			System.arraycopy(children, 0, this.children, 0, children.length);
		}
	}
}
