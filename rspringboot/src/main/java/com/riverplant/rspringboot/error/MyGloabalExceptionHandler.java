package com.riverplant.rspringboot.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 自定义处理异常
 * @author riverplant
 *
 */
@ControllerAdvice
public class MyGloabalExceptionHandler {
    @ExceptionHandler(value=Exception.class)
    @ResponseBody
	public String errorHandler(Exception e) {
		return "gloval error"+e.getMessage()+"属于"+e.getClass().getName();
	}
}
