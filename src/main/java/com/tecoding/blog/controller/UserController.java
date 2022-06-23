package com.tecoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용 (인증이 필요 없는 곳에는 auth)
// 그냥 주소가 / 이면 index.jsp 허용 
// static 이하  있는 /js/**, /css/**, /image/** 


@Controller
//@RequestMapping("/user") // 주소 변경  
public class UserController {

	@GetMapping("/auth/join_form")
	public String joinForm() {
		return "user/join_form";
	}

	@GetMapping("/auth/login_form")
	public String loginForm() {
		return "user/login_form";
	}
	
	// @AuthenticationPrincipal ... Authentication 객체를 가져 온다.
	// public String updateForm(@AuthenticationPrincipal PrincipalDetail pricipal ) {
	@GetMapping("/user/update_form")
	public String updateForm() {
		return "user/update_form";
	}

}
