package com.riverplant.rspringboot.interview.java.multiThreads;

public class YieldDemo {

	public static void main(String[] args) {
		
		Runnable yieldTask = new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<10;i++) {
					System.out.println(Thread.currentThread().getName()+i);
					if(i==5)Thread.yield();
				}	
			}
			};//yieldTask
			Thread t1 = new Thread(yieldTask,"t1");
			Thread t2 = new Thread(yieldTask,"t2");
			Thread t3 = new Thread(yieldTask,"t3");
			
			t1.start();t2.start();t3.start();
	}
}
