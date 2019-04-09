package com.riverplant.rspringboot.interview.spring.AOP;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 切面
 * @author riverplant
 * @Before:前置通知
 * @AfterReturning:后置通知
 * @AfterThrowing:异常通知
 * @After:最终通知
 * @Around:环绕通知
 */
@Aspect
@Component
public class RequestLoadAspect {
	private static final Logger log = LoggerFactory.getLogger(RequestLoadAspect.class);
	/**
	 * 整个表达式可以分为五个部分：
 1、execution(): 表达式主体。
 2、第一个*号：表示返回类型，*号表示所有的类型。
 3、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.riverplant.rspringboot.controller包、
        子孙包下所有类的方法。
 4、第二个*号：表示类名，*号表示所有的类
 5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
	 */
	@Pointcut("execution(public * com.riverplant.rspringboot.controller..*.*(..))")//切入点
    public void webLog() {
		
	}
	
	@Before("webLog()")
	public void deBefore(JoinPoint joinPoint) {
		//接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		log.info("URL:"+request.getRequestURL().toString());
		log.info("IP:"+request.getRemoteAddr());
	}
	/**
	 * 
	 * @param returValue：Controller方法调用后返回的内容
	 */
	@AfterReturning(returning= "returValue", pointcut = "webLog()")
	public  void doAfterReturning(Object returValue) {//
		//处理完请求，返回内容
		log.info("response: "+returValue);
	}
}
