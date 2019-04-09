package com.riverplant.rspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * 
 * @author riverplant
 *
 */
@Controller
public class AccountController {
    @GetMapping("/ref")
	public String reg() {
		return "ref";
	}
    
    @GetMapping("/logout")
   	public String logout(Model model) {
    	model.addAttribute("username", "root");
    	model.addAttribute("logout", "true");
   		return "logout";
   	}
}
