package com.example.demo.oop.compagne.desginpattern.task.decorator;
public class CodingTask implements Runnable {
	
	private final int employeeId;
	
	public CodingTask(int employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public void run() {
		System.out.println("employeeId" +employeeId+"Writing code...");
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		System.out.println("employeeId" +employeeId+"finished");
	}

}
