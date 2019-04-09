package com.example.demo.oop.compagne.desginpattern.task;
public abstract class LoggingRunnable implements Runnable{
   protected abstract void doRun();
	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		doRun();
		// TODO Auto-generated method stub
		System.out.println(("Task finished,Elapsed time:")
				+ (System.currentTimeMillis() - startTime));
	}
}
