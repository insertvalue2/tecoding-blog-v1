package com.tecoding.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	// yml prefix... 파일 주석처리 후 확인
	// http://localhost:9090/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// 파일리턴 기본 경로 : src/main/resources/static
		// 리턴 경로 : /home.html
		// 전체 경로 : src/main/resources/static/home.html
		return "/home.html";
	}

	@GetMapping("/temp/img")
	public String tempimg() {
		return "/abc.png";
	}
	
	// yml 파일 주석 해제 
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		// prefix : /WEB-INF/views/
		// suffx : .jsp 
		// 전체 경로 : /WEB-INF/views/test/jsp
		return "test";
	}
	

}
