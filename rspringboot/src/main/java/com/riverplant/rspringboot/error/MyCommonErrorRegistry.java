package com.riverplant.rspringboot.error;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
/**
 * 自定义错误处理类，通过实现ErrorPageRegistrar接口
 * @author riverplant
 *
 */
//@Component
public class MyCommonErrorRegistry implements ErrorPageRegistrar {

	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
		ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
		ErrorPage args = new ErrorPage(IllegalArgumentException.class, "/args.html");
		registry.addErrorPages(e404,e500,args);
	}

}
