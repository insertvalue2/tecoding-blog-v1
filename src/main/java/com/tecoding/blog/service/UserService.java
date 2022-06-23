package com.tecoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecoding.blog.model.RoleType;
import com.tecoding.blog.model.User;
import com.tecoding.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean을 등록해 준다. (Ioc를 해준다)
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	// org.springframework.transaction.annotation.Transactional;
	@Transactional
	public int saveUser(User user) {
		try {
			String rawPassword = user.getPassword(); // 원문
			String encPassword = encoder.encode(rawPassword);
			user.setPassword(encPassword);
			user.setRole(RoleType.USER);
			userRepository.save(user);

		} catch (Exception e) {
			return -1;
		}

		return 1;
	}

 
	@Transactional
	public void updateUser(User user) {
		// 수정시에는 영속성 컨텍스트에 User 오브젝트를 영속화시키고, 영속된 오브젝트를 수정
		// select를 해서 User 오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려 준다 (더티체킹)
		User userEntity = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원찾기실패");
		});
		String rawPassword = user.getPassword();
		String hashPassword = encoder.encode(rawPassword);
		userEntity.setPassword(hashPassword);
		userEntity.setEmail(user.getEmail());
		// 메서드 종료시 - 서비스 종료 - 트랜잭션 종료 - flush, commit 이 자동으로 된다.
		// 영속화된 userEntity 객체의 변화가 감지되면 더티체킹이 되어 update 문을 날려 준다.
	
	}

}
