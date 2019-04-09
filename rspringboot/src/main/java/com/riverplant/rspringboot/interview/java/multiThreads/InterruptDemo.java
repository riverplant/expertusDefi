package com.riverplant.rspringboot.interview.java.multiThreads;
/**
 * @author riverplant
 */
public class InterruptDemo {
public static void main(String[] args) throws InterruptedException {
	Runnable Interruptask = new Runnable() {
		@Override
		public void run() {
		int i = 0;
		try {
			while(!Thread.currentThread().isInterrupted()) {
				Thread.sleep(100);//当休眠时线程相当于处于阻塞，此时如果调用interrup指令，将会退出阻塞并且抛出异常
				i++;
				System.out.println(Thread.currentThread().getName()+"("+Thread.currentThread().getState()+") loop "+i);
			}
		} catch (InterruptedException e) {//调用阻塞方法时处理InterruptedException
			System.out.println(Thread.currentThread().getName()+"("+Thread.currentThread().getState()+")");
		}	
		}	
	};//task
	Thread t1 = new Thread(Interruptask,"t1");
	System.out.println(t1.getName()+"("+t1.getState()+") is new");
	t1.start();//启动t1
	System.out.println(t1.getName()+"("+t1.getState()+") is started");
	Thread.sleep(300);
	t1.interrupt();//向t1发送中断指令
	System.out.println(t1.getName()+"("+t1.getState()+") is interrupted");
	Thread.sleep(300);
	System.out.println(t1.getName()+"("+t1.getState()+") is interrupted now.");
}
}
