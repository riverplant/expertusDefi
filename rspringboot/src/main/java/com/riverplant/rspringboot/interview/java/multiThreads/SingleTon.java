package com.riverplant.rspringboot.interview.java.multiThreads;

public class SingleTon {
private volatile static SingleTon instance;//创建一个volatile实例对象
private SingleTon() {}//私有化构造函数

public static SingleTon getInstance() {
	if(instance==null) {
		synchronized (SingleTon.class) {
			if(instance == null) {
				instance = new SingleTon();
			}
		}
	}
	return instance;
}
}
