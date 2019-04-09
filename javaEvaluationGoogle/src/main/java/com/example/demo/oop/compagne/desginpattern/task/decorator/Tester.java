package com.example.demo.oop.compagne.desginpattern.task.decorator;

import com.example.demo.oop.compagne.desginpattern.task.abstractfactorypattern.AbstractFactory;

/**
 * 
 * @author riverplant
 * decorator pattern 装饰模式
 */
public class Tester {
public static void main(String[] args) {
	taskFactory factory = taskFactory.getInstance();
	factory.createLoggingTask().run();
// new LoggingRunnable(
//		 new TransactionalRunnable(
//				 new CodingTask())).run();
}
}
