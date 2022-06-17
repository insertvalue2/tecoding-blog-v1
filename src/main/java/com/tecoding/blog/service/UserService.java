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

}
