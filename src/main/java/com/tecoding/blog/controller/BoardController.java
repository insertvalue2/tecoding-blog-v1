package com.tecoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	
	/**
	 * 시큐리티 로그인 인증이 완료되면 컨트롤러에서 
	 * 세션을 어떻게 찾을까? 
	 * @AuthenticationPrincipal PrincipalDetail principal
	 * @return
	 */
	@GetMapping({"", "/"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/board/save_form")
	public String saveForm() {
		return "/board/save_form";
	}
	
}
