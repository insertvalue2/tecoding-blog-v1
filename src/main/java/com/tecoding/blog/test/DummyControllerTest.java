package com.tecoding.blog.test;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	

	// email, password
	@Transactional // javax.transaction.Transactional
	@PutMapping("/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		//json 데이터를 요청 -> java Object(MessageConvert  의 Jackson 라이브러리가 변환해서 받아 준다)  
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("getEmail : " + requestUser.getEmail());

		//		1. 
//		requestUser.setId(id); 
//		requestUser.setUsername("abc"); 
//		userRepository.save(requestUser); // 실행하면 오류 !! (username : x) 
		// 강제로 넣어서 실행 잘못된 처리가 된다. update save를 잘 사용하지 않는다. 
		
		// 만약 처리 한다면 먼저 찾아 와야 한다.
		// 2. 
//		User user = userRepository.findById(id).orElseThrow(() -> {
//			return new IllegalIdentifierException("수정에 실패하였습니다.");
//		});
//		user.setPassword(requestUser.getPassword());
//		user.setEmail(requestUser.getEmail());
//		userRepository.save(user);
	
		// 3. 
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalIdentifierException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		// save 호출하지 않았는데 변경되엉 있다. 
		// 더티 체킹 
		// update 를 할 때는 save 함수를 호출하지 않고 @Transactional 을 상용한다. 
		
		return null; 
	}
	
	
	
	// http://localhost:9090/blog/dummy/users
	@GetMapping("/users")
	public List<User> list() {
		return userRepository.findAll();
	}

	// 한 페이지당 2 건의 데이터를 리턴
	// http://localhost:9090/blog/dummy/user?page=0 (요청은 0부터 시작한다)
	// List 타입에서 추후 Page 타입으로 변경
	// 다시 Page 타입에서 List 로 변경하기 
	@GetMapping("/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable) {
//		Page<User> pageUser = userRepository.findAll(pageable);
//		if(pageUser.isFirst()) {
//			
//		}
		// content 만 필요 하다면 
		List<User> users = userRepository.findAll(pageable).getContent();
		return users;
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
