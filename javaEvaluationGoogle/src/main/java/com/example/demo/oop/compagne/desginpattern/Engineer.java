package com.example.demo.oop.compagne.desginpattern;

public class Engineer implements Role {

	@Override
	public void doWork() {
		System.out.println("Doing engineer ok");
	}

	@Override
	public String toString() {
		return "Engineer";
	}
	

}
