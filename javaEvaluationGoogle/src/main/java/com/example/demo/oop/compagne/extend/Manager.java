package com.example.demo.oop.compagne.extend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.example.demo.oop.compagne.Employee;
import com.example.demo.oop.compagne.desginpattern.Role;

public class Manager implements Role {
	private final List<Employee> reporters;
    /**
     * 将reporters定义为final，必须在构造函数中初始化，之后不可以再修改
     * @param name
     * @param salary
     */
	public Manager(List<Employee> reporters) {
		
		//使用了Collections.unmodifiableList(reporters)
		//使用该方法必须将reporters拷一份newArrayList
		this.reporters = Collections.unmodifiableList(new ArrayList<>(reporters));
		
	}
  
	@Override
	public void doWork() {
		System.out.println("Dispatching work");
		Employee worker = selectReporter();
		worker.doWork();
	}

	private Employee selectReporter() {
		//selects a reporter to do work
     return reporters.get(0); 
	}
	
	@Override
	public String toString() {
		return "Manager";
	}
}
