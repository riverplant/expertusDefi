package com.riverplant.rspringboot.interview.java.multiThreads;
/**
 * 
 * @author riverplant
 *
 */
public class WaitSleepDemo {
public static void main(String[] args) {
	final Object lock = new Object();
	new Thread(()-> {//A
		System.out.println("thread A is waiting to get lock");
		synchronized(lock) {
			System.out.println("thread A get lock");
			try {
				Thread.sleep(20);//不会释放锁，所以B此时得不到锁不能执行synchronized块，只能阻塞
				System.out.println("thread A do wait");
				lock.wait();//此时A会释放锁lock,让B开始执行,没有设置时间会让B执行完后A无法被唤醒
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	} ).start();
	
	try {
		Thread.sleep(10);
	} catch (Exception e2) {
		e2.printStackTrace();
	}
	new Thread(()-> {//B
		System.out.println("thread B is waiting to get lock");
		synchronized(lock) {
			System.out.println("thread B get lock");
			try {
				System.out.println("thread B is sleeping 10 ms");
				Thread.sleep(10);
				System.out.println("thread B is done");
				lock.notify();//此时唤醒A
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	} ).start();
}
}
