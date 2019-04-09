package com.example.demo.oop.compagne.desginpattern.task;
/**
 * 
 * @author riverplant
 *
 */
public class CodingTask2 extends TransctionalRunnable{
	@Override
	protected void doRun() {
		// TODO Auto-generated method stub
				System.out.println("Writing code.");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException();
				}
	}

}
