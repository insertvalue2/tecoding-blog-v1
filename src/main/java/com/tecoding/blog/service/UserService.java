package com.tecoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecoding.blog.model.User;
import com.tecoding.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean을 등록해 준다. (Ioc를 해준다)
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	// org.springframework.transaction.annotation.Transactional;
	@Transactional
	public int saveUser(User user) {
		try {
			userRepository.save(user);
		} catch (Exception e) {
			return -1; 
		}
		
		return 1;
	}
	
//	// select할 때 트랜잭션 시작, 종료시에 트랜잭션 종료(정합성) 
//	@Transactional(readOnly = true) 
//	public User login(User user) {
//		// repository에 가서 로그인을 위한 함수를 만들기 
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
	
}
