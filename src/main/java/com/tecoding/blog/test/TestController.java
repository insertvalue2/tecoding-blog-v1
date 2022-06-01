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
		return "/home.html";
	}

	@GetMapping("/temp/img")
	public String tempimg() {
		return "/abc.png";
	}

}
