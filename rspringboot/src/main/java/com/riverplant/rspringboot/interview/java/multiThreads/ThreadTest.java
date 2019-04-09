package com.riverplant.rspringboot.interview.java.multiThreads;

public class ThreadTest {

	private static void attack() {
		System.out.println("Fight");
		System.out.println("Current Thread is: "+Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		Thread t = new Thread(()->{
			attack();
		});
		System.out.println("Current main Thread is: "+Thread.currentThread().getName());
		t.run();//沿用主线程来执行方法
		t.start();//使用新线程来执行方法
	}
}
