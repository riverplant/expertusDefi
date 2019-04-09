package com.riverplant.rspringboot.interview.java.throwable;

import java.io.FileNotFoundException;

public class ErrorAndException {

	private void throwError() {
		throw new StackOverflowError();
	}
	
	private void throwRuntimeError() {
		throw new RuntimeException();
	}
	
	private void throwCheckedException(){
		try {
			throw new FileNotFoundException();
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}	
	}
}
