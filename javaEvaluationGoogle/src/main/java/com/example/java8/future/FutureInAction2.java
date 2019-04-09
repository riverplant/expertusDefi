package com.example.java8.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * java自定义Future
 * 
 * @author riverplant
 *
 */
public class FutureInAction2 {

	public static void main(String[] args) {	
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<String> future = executorService.submit(()->{
					try {
						Thread.sleep(10000L);
						return "I am finished";
					} catch (InterruptedException e) {
						e.printStackTrace();
						return "I am error";
					}
				});	//Future
		/**
		 * 这里可以继续进行业务操作，不会出线卡顿
		 */
		
		while(!future.isDone()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		try {
//			/**
//			 * 只等待10秒钟
//			 */
//			String value = future.get(10,TimeUnit.MICROSECONDS);//获得Future返回的结果,在得到结果前线程会卡住
//		
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
		try {
			String value = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.shutdown();
	}// main
  
}
