package com.tecoding.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tecoding.blog.auth.PrincipalDetail;

@Controller
public class BoardController {
	
	/**
	 * 시큐리티 로그인 인증이 완료되면 컨트롤러에서 
	 * 세션을 어떻게 찾을까? 
	 * 
	 * @return
	 */
	@GetMapping({"", "/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principal) {
		if (principal != null) {
			System.out.println("principal : " + principal.getUsername());
		}
		return "index";
	}
	
}
