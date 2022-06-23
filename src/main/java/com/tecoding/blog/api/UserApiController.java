package com.tecoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tecoding.blog.auth.PrincipalDetail;
import com.tecoding.blog.dto.ResponseDto;
import com.tecoding.blog.model.User;
import com.tecoding.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@Autowired(required = true)
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email
		int result = userService.saveUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK, result); // 자바 Object를 JSON 으로 변환 해서 리턴
	}

	// @RequestBody body 가 없으면 json 데이터를 못 받음
	// key=value 형식만 받을 수 있고 MIME TYPE : x-www-form-urlencoded
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user, @AuthenticationPrincipal PrincipalDetail principal) {
		userService.updateUser(user);
		// 여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음.
		// 하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줄 것임.
		// 세션 등록
		
		// 강제로 Authentication 객체를 만들고 SecurityContext 안에 강제로 집어 넣으면 된다. 
		
		// UsernamePasswordAuthenticationToken 객체 생성시 
		// 인증과 인가란 무엇인가? 인증 절차를 거친후 인가 절차를 진행!
		//- principal : 아이디 (Object)  (스크립트단에서 username을 직접 가져 오자) 
		//- credential : 비밀번호
		
		// 1. Authentication 객체 생성
		// 2. AuthenticationManager 메모리에 올려서 authenticate 메서드에 Authentication 객체를 저장한다. 
		// 3. SecurityContextHolder.getContext().setAuthentication 에 Authentication 객체 집어 넣기 
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
				
		return new ResponseDto<Integer>(HttpStatus.OK, 1); // 자바 Object를 JSON 으로 변환 해서 리턴
	}

}
