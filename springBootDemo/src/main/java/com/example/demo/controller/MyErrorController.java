package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "error")
public class MyErrorController implements ErrorController {

	private static final Logger logger = LoggerFactory.getLogger(MyErrorController.class);

	@Override
	public String getErrorPath() {
		logger.info("出错，进入自定义错误控制器");
		//返回自定义路径
		return "error/error";
	}

	@RequestMapping
	public String error() {
		return getErrorPath();
	}
}
