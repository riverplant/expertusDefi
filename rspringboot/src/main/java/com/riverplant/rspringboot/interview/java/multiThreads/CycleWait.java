package com.riverplant.rspringboot.interview.java.multiThreads;

public class CycleWait implements Runnable{
   private String value;
	@Override
	public void run() {
	   try {
		Thread.currentThread().sleep(5000L);
	} catch (Exception e) {
		e.printStackTrace();
	}
	   value = "We are data now"; 
	}
	
	public static void main(String[] args) throws InterruptedException {
		CycleWait cycle = new CycleWait();
		Thread t1 = new Thread(cycle);
		t1.start();
		//主线程等待法
//		while(cycle.value==null) {
//			try {
//				Thread.currentThread().sleep(100L);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		//2.join()方法
		t1.join();//阻塞当前线程等待子线程处理完毕
		System.out.println("value:"+cycle.value);
	}

}
