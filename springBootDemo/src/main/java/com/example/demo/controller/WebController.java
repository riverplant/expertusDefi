package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
//不能使用@RestController,否则将无法返回视图
@Controller
@RequestMapping("/web")
public class WebController {
	
	private static final Logger logger = LoggerFactory.getLogger(WebController.class);
	/**
	 * 返回视图/templates/index.ftl
	 * @return
	 */
	@RequestMapping("/index")
	public String index(ModelMap map) {
		logger.info("这里是WebController");
		map.put("title", "hello world");
		return "index";//不要加上'/'
	}
}
