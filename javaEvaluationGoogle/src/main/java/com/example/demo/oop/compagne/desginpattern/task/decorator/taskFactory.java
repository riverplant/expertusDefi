package com.example.demo.oop.compagne.desginpattern.task.decorator;

import com.example.demo.oop.compagne.desginpattern.task.abstractfactorypattern.AbstractFactory;

/**
 * task = taskFactory.createCodingTask();
 * @author riverplant
 *
 */
public class taskFactory implements AbstractFactory{
	private taskFactory() {
		
	}
	private static taskFactory instance = null;
	public static taskFactory getInstance() {
		if (instance == null) {
			synchronized (taskFactory.class) {
				if (instance == null) {
					instance = new taskFactory();
				}
			}
		}//if

		return instance;
	}
	/**
	 * 
	 * @return
	 */
 public CodingTask createCodingTask() {
	 return new CodingTask(0);
 }
 
 public LoggingRunnable createLoggingTask() {
	 return new LoggingRunnable(createTransctionalTask());
 }
 
 public  TransactionalRunnable createTransctionalTask() {
	 return new TransactionalRunnable(createCodingTask());
 }
}
