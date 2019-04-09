package com.example.demo.superpoint;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.example.demo.oop.compagne.desginpattern.task.decorator.CodingTask;

/**
 * 多线程
 * @author riverplant
 *
 */
public class ExecutorTest {
public static void main(String[] args) throws InterruptedException, ExecutionException {
	/**corePoolSize:
	 * maximumPoolSize:
	 * keepAliveTime:
	 * unit:TimeUnit
	 * workQueue: BlockingQueue<Runnable>  任务队列
	 */
//	ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, 
//			maximumPoolSize, keepAliveTime, unit, workQueue)
	ExecutorService executor = Executors.newFixedThreadPool(3);//固定30个线程池
	List<Future<?>> taskResults = new LinkedList<>();
	//public interface Callable<V> { V call()} 和runnable比它有返回值
	for(int i=0;i<10;i++) {
		taskResults.add(executor.submit(new CodingTask(i+1))) ;
	}
	System.out.println("10 tasks dispatched successfully");
	for(Future<?> task :taskResults) {
		task.get();//通过Future可以获得很多的方法
	}
	System.out.println("all tasks are finished");
	executor.shutdown();//该方法会保证所有的任务结束后调用
}
/**
 * 反转String字符串内容
 * @param str
 * @return
 */
public String getRrenverse(String str) {
	
	int leng = str.length();
	if(str.length() == 1) return str;
	
	String left = str.substring(0, leng/2);
	String right = str.substring(leng/2, leng);
	
	return getRrenverse(right)+getRrenverse(left);
 }
}
