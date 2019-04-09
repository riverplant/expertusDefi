package com.example.java8.future;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自定义Future
 * 
 * @author riverplant
 *
 */
public class FutureInAction {

	public static void main(String[] args) throws InterruptedException {
		MyFuture<String> myFuture = invoke(() -> {
			try {
				Thread.sleep(10000);// 模拟处理或者请求服务等待响应的时间
				return "i am finished";
			} catch (Exception e) {
				return "error";
			}
		});
		System.out.println(myFuture.get());
        /**
         * 此处依然可以进行其它业务操作
         */
		//Optional.ofNullable(myFuture.get()).ifPresent(System.out::println);// 处理未完成，依然可以做其它的操作
		while (!myFuture.isDon()) {// 当处理没有完成，就继续等待
			Thread.sleep(10);
		}
		System.out.println(myFuture.get());
		//Optional.ofNullable(myFuture.get()).ifPresent(System.out::println);// 处理完成，得到结果
	}// main
   /**
    * 该方法会引起阻塞
    * @param callable
    * @return
    */
	private static <T> T block(MyCallable<T> callable) {
		
		return callable.action();
	}
	/**
	 * 使用该方法可以避免阻塞，在处理的同时可以进行其它操作
	 * @param callable
	 * @return
	 */
	private static <T> MyFuture<T> invoke(MyCallable<T> callable) {
		AtomicReference<T> result = new AtomicReference<T>();// 通过原子来传递结果
		AtomicBoolean finished = new AtomicBoolean(false);
		Thread t = new Thread(() -> {
			T value = callable.action();
			result.set(value);
			finished.set(true);
		});

		t.start();

		MyFuture<T> future = new MyFuture<T>() {

			@Override
			public T get() {
				// TODO Auto-generated method stub
				return result.get();
			}

			@Override
			public boolean isDon() {
				// TODO Auto-generated method stub
				return finished.get();
			}

		};
		return future;
	}// invoke

	/**
	 * 
	 * @author riverplant
	 *
	 * @param <T>
	 */
	private interface MyFuture<T> {

		T get();

		boolean isDon();
	}

	/**
	 * 
	 * @author riverplant
	 *
	 * @param <T>
	 */
	private interface MyCallable<T> {

		T action();// 做了一个动作，返回一个我想要的结果
	}
}
