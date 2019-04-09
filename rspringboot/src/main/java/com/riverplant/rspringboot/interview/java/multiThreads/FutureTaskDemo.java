package com.riverplant.rspringboot.interview.java.multiThreads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String> ftask = new FutureTask<>(new MyCallable());
		new Thread(ftask).start();
		if(!ftask.isDone()) {
			System.out.println("task has not finished,please wait!!");
		}
		System.out.println("task finished,return:"+ftask.get());//ftask.get()得到callable的返回值
	//通过线程池获取返回值
	}
}
