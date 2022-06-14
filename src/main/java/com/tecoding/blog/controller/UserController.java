package com.tecoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/join_form")
	public String joinForm() {
		return "user/join_form"; 
	}
	
	@GetMapping("/login_form")
	public String loginForm() {
		return "user/login_form"; 
	}
	
	
	
}
