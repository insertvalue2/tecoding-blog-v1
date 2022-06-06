package com.tecoding.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tecoding.blog.model.RoleType;
import com.tecoding.blog.model.User;
import com.tecoding.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	// 2. 의존성 주입 (DI)
	@Autowired // DummyControllerTest 메모리에 로딩 UserRepository 로딩
	// @Autowired 하지 않으면 null
	private UserRepository userRepository;

	// 1.
	// @requestBody 생략
	// http://localhost:9090/blog/dummy/join
	// http 메세지 body에 데이터를 가지고 온다.
	@PostMapping("/dummy/join")
	public String join(@RequestBody User user) {
		System.out.println("username : " + user.getUsername());
		System.out.println("pasword : " + user.getPassword());
		System.out.println("email : " + user.getEmail());

		System.out.println("id : " + user.getId());
		System.out.println("date : " + user.getCreateDate());
		System.out.println("role : " + user.getRole());

		// 2.
		// user.setRole("user"); // 개발자들 실수 유발 가능
		user.setRole(RoleType.USER);
		userRepository.save(user);

		return "회원가입이 완료되었습니다";
	}
}
