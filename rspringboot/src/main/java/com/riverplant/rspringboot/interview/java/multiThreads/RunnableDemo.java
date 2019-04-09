package com.riverplant.rspringboot.interview.java.multiThreads;

public class RunnableDemo {

	public static void main(String[] args) {
		MyRunnable mr1 = new MyRunnable("MyRunnable1");
		MyRunnable mr2 = new MyRunnable("MyRunnable2");
		MyRunnable mr3 = new MyRunnable("MyRunnable3");
		MyRunnable mr4 = new MyRunnable("MyRunnable4");
		Thread t1 = new Thread(mr1);
		Thread t2 = new Thread(mr2);
		Thread t3 = new Thread(mr3);
		Thread t4 = new Thread(mr4);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
	}
}
