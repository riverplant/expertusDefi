package com.riverplant.rspringboot.interview.java.multiThreads;
/**
 * 
 * @author riverplant
 *
 */
public class NotificationDemo {

	private volatile boolean go = false;//volatile表示一旦线程A对go进行了改变，其它线程都可以立刻看到
	
	public static void main(String[] args) throws InterruptedException {
		final NotificationDemo test = new NotificationDemo();
		//进入等待状态
		Runnable waitTask = new Runnable() {
			
			public void run() {
				try {
					test.shouldGo();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"finished Execution");
			};
		};//waitTask
//唤醒		
Runnable notifyTask = new Runnable() {
			
			public void run() {
				try {
					test.go();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"finished Execution");
			};
		};//notifyTask
	Thread t1 = new Thread(waitTask, "WT1");
	Thread t2 = new Thread(waitTask, "WT2");
	Thread t3 = new Thread(waitTask, "WT3");
	Thread t4 = new Thread(notifyTask, "NT1");
	t1.start();t2.start();t3.start();
	
	Thread.sleep(200);
	t4.start();
	
	}//main
	
	private synchronized void shouldGo() throws InterruptedException {
		while(go!=true) {//进入等待状态
			System.out.println(Thread.currentThread()+"is going to wait on this object");
			wait();//释放锁
			System.out.println(Thread.currentThread()+"is woken up");
		}//while
		go = false;
	}
	
	private synchronized void go() {
		while(go==false) {
			System.out.println(Thread.currentThread()
					+"is going to notify all or one thread waiting on the ");
			go = true;
			notify();
		}//while
		go = false;
	}
}
