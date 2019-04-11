package com.riverplant.expertus.defi.support;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.client.HttpClientErrorException.MethodNotAllowed;

/**
 * 
 * @author riverplant
 *
 */
@RestControllerAdvice
public class ExceptionHandlerController {
	
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String,Object> handleException(RuntimeException exception){
    	Map<String,Object> result = new HashMap<>();
    	result.put("result", "fail");
    	result.put("errMsg", exception.getMessage());
    	return result;
	}
    
    @ExceptionHandler(MethodNotAllowed.class)
    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	public Map<String,Object> handleException(MethodNotAllowed exception){
    	Map<String,Object> result = new HashMap<>();
    	result.put("result", "fail");
    	result.put("errMsg", exception.getMessage());
    	return result;
	}
    
    @ExceptionHandler(Forbidden.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
	public Map<String,Object> handleException(Forbidden exception){
    	Map<String,Object> result = new HashMap<>();
    	result.put("result", "fail");
    	result.put("errMsg", exception.getMessage());
    	return result;
	}
}
