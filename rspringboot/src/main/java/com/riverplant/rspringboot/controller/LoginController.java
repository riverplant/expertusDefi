package com.riverplant.rspringboot.controller;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class LoginController {
	Logger log = LoggerFactory.getLogger(getClass());
	
    @PostMapping("/login")
	public String login(@RequestParam(value="username",defaultValue="admin")String username,@RequestParam(value="password",defaultValue="admin")String password) {
    	System.out.println(username+"loging at "+LocalDateTime.now().toString());
    	if(username.equals(password)) {
    		return "loginSuccess";//表示在/WEB-INF/JSP下找loginSuccess.jsp
    	}
    	return "loginFaild";//表示在/WEB-INF/JSP下找loginFaild.jsp
	}
   
    
    @GetMapping("/login")
    public String loginIndex(Model model) {
    	model.addAttribute("username", "tester1");
    	return "login";
    }
    
    @GetMapping("/index")
    public String Index(Model model) {
    	model.addAttribute("username", "tester1");
    	return "index";
    }
	
}
