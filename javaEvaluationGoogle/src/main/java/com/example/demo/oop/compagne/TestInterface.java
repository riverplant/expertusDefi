package com.example.demo.oop.compagne;

public class TestInterface {

	public static void main(String[] args) {
		MyLinkedList<Integer> myLinkedList = new MyLinkedList<Integer>();
		for(int i=0;i<100;i++) {
			myLinkedList.add(i);
		}
		for(Integer value:myLinkedList) {
			System.out.println(value);
		}
	}
}
