package com.example.java8.future.CompletableFuture;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/**
 * 通过回调接口Handler来避免等待
 * 
 * @author riverplant
 *
 */
public class MyCompletableFutureInAction2 {
	public static void main(String[] args) {
		
		Myfuture2<String> future = invoke(()->{
			try {
				Thread.sleep(10000L);
				return "test success...";
			} catch (Exception e) {
				e.printStackTrace();
				return "test error...";
			}
		} );
		
		future.setMyCompletalbe(new MyCompleteble2<String>() {

			@Override
			public void complete(String t) {
				System.out.println(t);//完成后回调
				System.out.println(future.get());
				System.out.println(future.isdone());
			}

			@Override
			public void exception(Throwable cause) {
				System.out.println("error");
				cause.printStackTrace();
			}
			
		});
		
		System.out.println("执行其它的业务逻辑...........");
		System.out.println(future.get());
		System.out.println(future.isdone());
	}

	public static <T> Myfuture2<T> invoke(Mycallable2<T> callable){
		AtomicBoolean isdone = new AtomicBoolean(false);
		AtomicReference<T> result = new AtomicReference<T>();
		
		Myfuture2<T> future = new Myfuture2<T>(){
            private MyCompleteble2<T> myCompleteble;
			@Override
			public T get() {
				return result.get();
			}

			@Override
			public boolean isdone() {
				return isdone.get();
			}

			@Override
			public void setMyCompletalbe(MyCompleteble2<T> myCompleteble2) {
				this.myCompleteble = myCompleteble2;
				
			}

			@Override
			public MyCompleteble2<T> getMyCompleteble2() {
				return myCompleteble;
			}
			
		};//future
		Thread t = new Thread(()->{
			try {
				T value = callable.Action();
				result.set(value);
				isdone.set(true);
				if(future.getMyCompleteble2()!=null)future.getMyCompleteble2().complete(value);	
			} catch (Exception e) {
				if(future.getMyCompleteble2()!=null)future.getMyCompleteble2().exception(e);
			}
		});
		t.start();
		return future;
	}
	private interface Myfuture2<T>{
		T get();
		boolean isdone();
		void setMyCompletalbe(MyCompleteble2<T> myCompleteble2);
		MyCompleteble2<T> getMyCompleteble2();
	}
	/**
	 * 该接口用于回调
	 * @author riverplant
	 *
	 * @param <T>
	 */
	private interface MyCompleteble2<T>{
		void complete(T t);
		void exception(Throwable cause);
	}
	private interface Mycallable2<T>{
		T Action();
	}
}
