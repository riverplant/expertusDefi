package com.example.demo.oop.compagne;
import java.util.Arrays;
import java.util.LinkedList;
import com.example.demo.oop.compagne.desginpattern.Engineer;
import com.example.demo.oop.compagne.extend.Manager;

public class Tester {	
public static void main(String[] args) {
	 Employee emp1 = new Employee("john",10000,new Engineer());
	 
	 Employee emp2 = new Employee("Mary",20000,new Engineer());
	
	 LinkedList<Employee> employees = new LinkedList<Employee>();
	 employees.add(emp1);
	 employees.add(emp2);
	 for(Employee emp : employees) {
		 System.out.println(emp);
	 }
	 
	 //提升Mary为manager
	 
	 emp2.setRole(new Manager(Arrays.asList(emp1)));
	 for(Employee emp : employees) {
		 System.out.println(emp);
	 }
	 System.out.println("开始测试...........");
	 emp1.doWork();
	 emp2.doWork();
}}