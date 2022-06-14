package com.tecoding.blog.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecoding.blog.dto.ResponseDto;
import com.tecoding.blog.model.RoleType;
import com.tecoding.blog.model.User;
import com.tecoding.blog.service.UserService;

@RestController
@RequestMapping("/api")
public class UserApiController {
	
	// DI 
	@Autowired 
	private UserService userService;
	
	// 로그인시 2번째 방법 (스프링이 IoC(Bean등록) 할때 가지고 있다.   
	@Autowired
	private HttpSession session; 
	
	@PostMapping("/user")
	public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email 
		// 실제로 DB에 insert 하고 아래에 리턴 처리
		user.setRole(RoleType.USER);
		int result = userService.saveUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK, result); // 자바 Object를 JSON 으로 변환 해서 리턴 
	}
	
	@PostMapping("/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
	public ResponseDto<Integer> login(@RequestBody User user) {
		System.out.println("UserApicController : login 호출됨");
		User principal = userService.login(user); // principal (접근주체) 
		if(principal != null) {
			session.setAttribute("principal", principal);
			// header.jsp (추가) JSTL TAG LIB 이용  (jstl tutorial) 검색 
			// https://www.tutorialspoint.com/jsp/jsp_standard_tag_library.htm
		}
		
		return new ResponseDto<Integer>(HttpStatus.OK, 1); 
	}
	
}
