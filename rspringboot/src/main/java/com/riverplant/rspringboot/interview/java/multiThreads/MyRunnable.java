package com.riverplant.rspringboot.interview.java.multiThreads;

public class MyRunnable implements Runnable {
private String name;

	public MyRunnable(String name) {
	super();
	this.name = name;
}
	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println("Thread start: "+this.name+"-"+i);
		}

	}

}
