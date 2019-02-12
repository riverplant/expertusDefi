package com.river.core.jdk8.MultiThreads;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Future模式
 * 
 * @author riverplant
 *
 */
public class FutureInAction {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		/**
		 * 使用jdk自带的future
		 */
		ExecutorService executorService = Executors.newSingleThreadExecutor();//单线程的线程池
		java.util.concurrent.Future<String> jdkfuture = executorService.submit(() -> {
			try {
				Thread.sleep(10000);
				return "finish";
			} catch (Exception e) {
				return "error";
			}
		});

			//jdkfuture.get(10,TimeUnit.MICROSECONDS);//只等待10秒钟
			while(!jdkfuture.isDone()) {
				Thread.sleep(10);
			}
			System.out.println(jdkfuture.get());
			executorService.shutdown();

		
		//****************************使用自定义future******************************************
		Future<String> future = invoke(() -> {
			try {
				Thread.sleep(10000);
				/**
				 * private interface Callable<T> { T action();// 做了一个动作返回一个我想要的结果给我 }
				 */
				return "finish";
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
		});
		while (!future.isDone()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(future.get());
	}

	/**
	 * 异步执行
	 * 
	 * @param callable
	 * @return
	 */
	private static <T> Future<T> invoke(Callable<T> callable) {
		AtomicReference<T> result = new AtomicReference<>();
		AtomicBoolean flag = new AtomicBoolean(false);
		Thread t = new Thread(() -> {
			T value = callable.action();// 通过T action()获得一个值
			result.set(value);
			flag.set(true);
		});
		t.start();
		Future<T> future = new Future<T>() {
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

		};
		return future;
	}// invoke

	private interface Future<T> {
		T get();

		boolean isDone();
	}

	private interface Callable<T> {
		T action();// 做了一个动作返回一个我想要的结果给我
	}
}
