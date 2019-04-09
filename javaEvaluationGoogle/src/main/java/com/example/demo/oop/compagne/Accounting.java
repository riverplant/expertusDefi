package com.example.demo.oop.compagne;

public class Accounting {
    private BankEndPoint bank;
    
	public BankEndPoint getBank() {
		return bank;
	}

	public void setBank(BankEndPoint bank) {
		this.bank = bank;
	}

	void payAll() {
		Employee.loadAllEmployees();
		for(Employee employee :Employee.allEmployees ) {
			employee.getPaid(bank);
		}
	}
}
