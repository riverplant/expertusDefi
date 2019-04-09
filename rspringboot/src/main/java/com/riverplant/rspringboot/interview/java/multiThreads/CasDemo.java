package com.riverplant.rspringboot.interview.java.multiThreads;

import java.util.concurrent.atomic.AtomicInteger;

public class CasDemo {

	private static volatile int value = 0;
	
	public static AtomicInteger add() {
		AtomicInteger val = new AtomicInteger(value);
		val.getAndIncrement();
		return val;
	}
	
	public static void main(String[] args) {
		AtomicInteger val = add();
		System.out.println(val.get());
	}
}
