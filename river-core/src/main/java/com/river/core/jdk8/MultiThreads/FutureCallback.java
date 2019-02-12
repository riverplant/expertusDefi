package com.river.core.jdk8.MultiThreads;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/**
 * 回调
 * @author riverplant
 *
 */
public class FutureCallback {
	public static void main(String[] args) {
		Future<String> future =  invoke(()->{
        	   try {
        		   Thread.sleep(10000);
   				return "finish";
			} catch (Exception e) {
				return "error";
			}
           });
		/**
		 * 此处可以做其它的操作需求
		 */
		System.out.println(future.get());
		/**
		 * 这里可以设置回调
		 */
		future.setCompletable(new Completable<String>() {

			@Override//当请求操作完成
			public void complete(String t) {
				System.out.println(t);
			}

			@Override//当请求操作出线异常
			public void exception(Throwable error) {
				System.out.println(error);
			}
			
		});
	}

	/**
	 * 定义获得Future<T>的invoke(Callable<T>)
	 * 
	 * @param callable
	 * @return
	 */
	private static <T> Future<T> invoke(Callable<T> callable) {
		AtomicReference<T> result = new AtomicReference<T>();
		AtomicBoolean flag = new AtomicBoolean(false);
		Future<T> future = new Future<T>() {
			private Completable<T> completable;
			@Override
			public T get() {
				// TODO Auto-generated method stub
				return result.get();
			}
			@Override
			public boolean isDone() {
				// TODO Auto-generated method stub
				return flag.get();
			}
			@Override
			public void setCompletable(Completable<T> completable) {
                     this.completable = completable;
			}
			@Override
			public Completable<T> getCompletable() {

				return completable;
			}

		};
		Thread t = new Thread(() -> {
			try {
				T value = callable.action();
				result.set(value);
				flag.set(true);
				if (future.getCompletable() != null) {
					future.getCompletable().complete(value);
				}
			} catch (Throwable cause) {
				if (future.getCompletable() != null)
				future.getCompletable().exception(cause);
			}

		});
		t.start();

		return future;

	}//invoke

	/**
	 * 自定义一个Future<T>接口
	 * 
	 * @author riverplant
	 *
	 * @param <T>
	 */
	private interface Future<T> {
		T get();

		boolean isDone();

		void setCompletable(Completable<T> completable);

		Completable<T> getCompletable();
	}

	/**
	 * 
	 * @author riverplant
	 *
	 * @param <T>
	 */
	private interface Completable<T> {
		void complete(T t);// 完成后回调

		void exception(Throwable error);// 抛异常时回调
	}

	/**
	 * 
	 * @author riverplant 自定义一个Callable<T>接口
	 * @param <T>
	 */
	private interface Callable<T> {
		T action();
	}
}
