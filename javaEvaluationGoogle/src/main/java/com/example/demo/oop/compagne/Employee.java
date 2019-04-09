package com.example.demo.oop.compagne;

import java.util.List;
import java.util.Objects;

import com.example.demo.oop.compagne.desginpattern.Role;

public class Employee {
static List<Employee> allEmployees;
private final String name;
private final int slary;
private Role role;

public Role getRole() {
	return role;
}

public void setRole(Role role) {
	this.role = role;
}

public Employee(String name, int slary,Role role) {
	super();
	this.name = name;
	this.slary = slary;
	this.role = role;
}

@Override
public int hashCode() {
	return Objects.hash(this.name,this.slary,this.role);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Employee other = (Employee) obj;

	return Objects.equals(this.name, other.name) &&
			Objects.equals(this.slary, other.slary)&&
			Objects.equals(this.role, other.role);//1.7之後的功能
}
@Override
public String toString() {
	return "Employee [name=" + name 
			+ ", slary=" + slary 
			+ ", role=" + role + "]";
}

public static List<Employee> getAllEmployees() {
	return allEmployees;
}

public static void setAllEmployees(List<Employee> allEmployees) {
	Employee.allEmployees = allEmployees;
}

public String getName() {
	return name;
}



public int getSlary() {
	return slary;
}

public void doWork() {
	role.doWork();
}

public void getPaid(BankEndPoint bank) {
	bank.payment(this.name, this.slary);
}
/**
 * Package private
 */
static void loadAllEmployees() {
	/**
	 * load all employee from database to allEmployees
	 */
}

}
