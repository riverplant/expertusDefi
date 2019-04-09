package com.riverplant.rspringboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.riverplant.rspringboot.beans.User;

//@Controller
@RestController//使用该注解不需要方法上再写@ResponseBody
@RequestMapping(value = "/user")
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @RequestMapping(value = "/home",method=RequestMethod.GET)
    @ResponseBody
	public String home() {
		
    	return "user home";
	}
    //@ResponseBody
    @GetMapping("/show")
    public String show() {
    	
    	return "get user show";
    }
    
    @GetMapping("/help")
    public String help() {
    	
    	throw new IllegalArgumentException("args is empty");
    }
    /**
     * @RequestParam("username") String usernam:将http中的请求参数username赋值给usernam
     * @param username
     * @param password
     * @return
     */
   // @ResponseBody
    @PostMapping("/create")
    public String create(@RequestParam(value = "username",defaultValue="admin") String username,@RequestParam(value="password",required=false)String password) {
    	
    	return "user create:username="+username+" || password="+password;
    }
    /**
     * @GetMapping("/{id:\\d+}"):只能传数字
     * @param id
     * @return
     */
    //@ResponseBody
    @GetMapping("/{id:\\d+}")
    public User display(@PathVariable("id")String id) {
    	return new User(id,"user"+id);
    }
    /**
     * 注入HttpServletRequest
     * @param req
     * @return
     */
    //@ResponseBody
    @GetMapping("/getIp")
    public String getIp(HttpServletRequest req) {
    	
    	return "getIp "+req.getRemoteHost();
    }
    
}
