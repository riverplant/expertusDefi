package com.example.demo.oop.compagne.desginpattern.task.decorator;
/**
 * 
 * @author riverplant
 *
 */
public class LoggingRunnable implements Runnable {
    private final Runnable innerRunnalbe;
    /**
     * @param innerRunnalbe
     */
	public LoggingRunnable(Runnable innerRunnalbe) {
		this.innerRunnalbe = innerRunnalbe;
	}
	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		System.out.println("Task started at "+ startTime);
		innerRunnalbe.run();
		System.out.println("Task finished,Elapsed time: "+ (System.currentTimeMillis()-startTime));
	}

}
