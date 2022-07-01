package com.tecoding.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecoding.blog.model.Board;
import com.tecoding.blog.repository.BoardRepository;
import com.tecoding.blog.service.BoardService;


/**
 *  스프링이 com.tencoding.controller 패키지 이하를 스캔해서 모든 자바 파일을
 * 	 메모리에 new 하는 것은 아니고 특정 어노테이션 붙어 있는 클래스 파일들을 
 * 	 new 해서 (IoC) 스프링 컨테이너에 관리해 준다.
 */
@RestController
@RequestMapping("/test")
public class BlogControllerTest {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/hello")
	String hello() {
		return "<h1>hello spring boot </h1>";
	}
	
	
	@GetMapping("/xssTest") 
	public String xssTest(Board board) {
		System.out.println("board : " + board);
		
		return "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Insert title here</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "\r\n"
				+ board.getTitle()
				+ "</body>\r\n"
				+ "</html>";
	}
}
