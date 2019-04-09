package com.riverplant.expertus.defi.repostory;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.riverplant.expertus.defi.BaseTest;
import com.riverplant.expertus.defi.interfaces.EmployeeRepository;

public class RepostoryTest extends BaseTest{

	@Autowired
	private EmployeeRepository employeeRepository;
	@Test
	public void test1() {
		employeeRepository.findByfirstName("david");
	}
}
