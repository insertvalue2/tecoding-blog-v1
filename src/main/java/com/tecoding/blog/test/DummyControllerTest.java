package com.tecoding.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecoding.blog.model.RoleType;
import com.tecoding.blog.model.User;
import com.tecoding.blog.repository.UserRepository;

@RestController
@RequestMapping("/dummy")
public class DummyControllerTest {

	@Autowired
	private UserRepository userRepository;
	
	
	// http://localhost:9090/blog/dummy/user
	@GetMapping("user")
	public List<User> list() {
		return userRepository.findAll(); 
	}

	// {id} path-variable
	// http://localhost:9090/blog/dummy/user/3
	@GetMapping("/user/{id}")
	public User detail(@PathVariable int id) {
		// Optional : user 가 null 일 수 도 있다.
		// Optional 로 User 객체를 감싸서 가져오 겠다.
		// 1. get() null 이 리턴될 일이 없다.
		// 2. orElseGet
		// 3. orElseThrow
//		User user =  userRepository.findById(id).orElseGet(new Supplier<User>() {
//			@Override
//			public User get() {
//				return new User();
//			}
//		});
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
		});
		// 요청 web browser 
		// user 객체는 = 자바 object (변환 문자열) 
		// 스프링부트 = MessageConverter 응답시에 Jackson 라이브러리를 호출해서 
		// user 오브젝트가 json type 로 보이게 된다. 
		return user;
	}

	@PostMapping("/join")
	public String join(@RequestBody User user) {
		System.out.println("username : " + user.getUsername());
		System.out.println("pasword : " + user.getPassword());
		System.out.println("email : " + user.getEmail());

		System.out.println("id : " + user.getId());
		System.out.println("date : " + user.getCreateDate());
		System.out.println("role : " + user.getRole());

		user.setRole(RoleType.USER);
		userRepository.save(user);

		return "회원가입이 완료되었습니다";
	}
}
