package com.riverplant.rspringboot.interview.java.multiThreads;
/**
 * 
 * @author riverplant
 *
 */

import java.util.concurrent.locks.ReentrantLock;
/**
 * 公平锁
 * @author riverplant
 *
 */
public class ReentrantLockDemo implements Runnable{
    //true:为公平锁
	private static ReentrantLock lock = new ReentrantLock(true);
	@Override
	public void run() {
		while(true) {
			try {
				lock.lock();//获得了锁
				System.out.println(Thread.currentThread().getName()+" get lock");
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		}
	}
}
