package com.riverplant.rspringboot.interview.java.multiThreads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolDemo {
public static void main(String[] args) throws InterruptedException, ExecutionException {

	ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	Future<String> future = cachedThreadPool.submit(new MyCallable());
	if(!future.isDone()) {
		System.out.println("task has not finished,please wait!!");
	}//while
	try {
		System.out.println("task finished,return:"+future.get());	
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		cachedThreadPool.shutdown();//线程池必须关闭
	}
	//ftask.get()得到callable的返回值
}
}
