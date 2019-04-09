package com.example.java8.future.CompletableFuture;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 通过回调接口Handler来避免等待
 * 
 * @author riverplant
 *
 */
public class MyCompletableFutureInAction {
	public static void main(String[] args) {
		MyFuture<String> future = invoke(()->{
			try {
				Thread.sleep(10000L);
				return "I am finished.";
			} catch (Exception e) {
				return "I am error.";
			}
		} );
		/**
		 * 完成后自动去调用Completable
		 */
		future.setCompletable(new Completable<String>() {

			@Override
			public void complete(String t) {
				System.out.println(t);
				System.out.println("此时结果:"+future.get());
			}

			@Override
			public void exception(Throwable cause) {
				System.out.println("error");
				cause.printStackTrace();
			}
			
		});
		System.out.println("执行其它的业务逻辑...........");
		System.out.println(future.get());//此时线程不会变成阻塞状态
	}

	/**
	 * 
	 * @author riverplant
	 *
	 * @param <T>
	 */
	private interface MyFuture<T> {

		T get();

		boolean isDon();

		void setCompletable(Completable<T> completable);

		Completable<T> getCompletable();
	}

	/**
	 * 使用该方法可以避免阻塞，在处理的同时可以进行其它操作
	 * 
	 * @param callable
	 * @return
	 */
	private static <T> MyFuture<T> invoke(MyCallable<T> callable) {
		AtomicReference<T> result = new AtomicReference<T>();// 通过原子来传递结果
		AtomicBoolean finished = new AtomicBoolean(false);

		MyFuture<T> future = new MyFuture<T>() {
			private Completable<T> completable;

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

			@Override
			public void setCompletable(Completable<T> completable) {
				this.completable = completable;
			}

			@Override
			public Completable<T> getCompletable() {

				return this.completable;
			}

		};// future
        
		Thread t = new Thread(() -> {
			try {
				T value = callable.action();
				result.set(value);
				finished.set(true);
				//完成调用
				if (future.getCompletable() != null)
					future.getCompletable().complete(value);
			} catch (Throwable cause) {
				if (future.getCompletable() != null)
					future.getCompletable().exception(cause);
			}

		});

		t.start();
		return future;
	}// invoke

	/**
	 * 
	 * @author riverplant
	 *
	 * @param <T>
	 */
	private interface Completable<T> {

		void complete(T t);

		void exception(Throwable cause);
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
