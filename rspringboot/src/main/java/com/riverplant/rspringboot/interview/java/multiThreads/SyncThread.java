package com.riverplant.rspringboot.interview.java.multiThreads;

import java.time.LocalDateTime;

public class SyncThread implements Runnable {

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		if (threadName.startsWith("A")) {
			async();
		} else if (threadName.startsWith("B")) {
			syncObjectBlock1();
		} else if (threadName.startsWith("C")) {
			syncObjectMethod1();
		}
	}

	/**
	 * 异步方法
	 */
	private void async() {
		try {
			System.out.println(Thread.currentThread().getName() + "_Async_Start: " + LocalDateTime.now().toString());
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + "_Async_End: " + LocalDateTime.now().toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
   /**
    * 同步代码块
    */
	private void syncObjectBlock1() {
		System.out.println(
				Thread.currentThread().getName() + "_syncObjectBlock1_Block1: " + LocalDateTime.now().toString());
		synchronized (this) {//同步代码块
			try {
				System.out.println(Thread.currentThread().getName() + "_syncObjectBlock1_Start: "
						+ LocalDateTime.now().toString());
				Thread.sleep(1000);
				System.out.println(
						Thread.currentThread().getName() + "_syncObjectBlock1_End: " + LocalDateTime.now().toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
   /**
    * 同步方法
    */
	private synchronized static void syncMethod1() {
		System.out.println(
				Thread.currentThread().getName() + "_syncObjectMethod1: " + LocalDateTime.now().toString());
		try {
			System.out.println(
					Thread.currentThread().getName() + "_syncObjectMethod1_Start: " + LocalDateTime.now().toString());
			Thread.sleep(1000);
			System.out.println(
					Thread.currentThread().getName() + "_syncObjectMethod1_End: " + LocalDateTime.now().toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void syncBlock1() {
		System.out.println(
				Thread.currentThread().getName() + "_syncObjectBlock1_Block1: " + LocalDateTime.now().toString());
		synchronized (SyncThread.class) {//同步代码块
			try {
				System.out.println(Thread.currentThread().getName() + "_syncObjectBlock1_Start: "
						+ LocalDateTime.now().toString());
				Thread.sleep(1000);
				System.out.println(
						Thread.currentThread().getName() + "_syncObjectBlock1_End: " + LocalDateTime.now().toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
   /**
    * 同步方法
    */
	private synchronized void syncObjectMethod1() {
		System.out.println(
				Thread.currentThread().getName() + "_syncObjectMethod1: " + LocalDateTime.now().toString());
		try {
			System.out.println(
					Thread.currentThread().getName() + "_syncObjectMethod1_Start: " + LocalDateTime.now().toString());
			Thread.sleep(1000);
			System.out.println(
					Thread.currentThread().getName() + "_syncObjectMethod1_End: " + LocalDateTime.now().toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
