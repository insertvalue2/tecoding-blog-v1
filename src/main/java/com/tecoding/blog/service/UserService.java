package com.tecoding.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecoding.blog.model.User;
import com.tecoding.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean을 등록해 준다. (Ioc를 해준다)
@Service
public class UserService {
	/**
	 * 서비스가 필요한 이유 
	 * 트랜잭션 관리 (c - s  - d ) 
	 * 
	 * 송금 서비스 
	 * 홍길동(5만)이 임꺽정에게 송금 (2만) 
	 * 1. 홍길동 금액 update() -- commit 
	 * 2. 임꺽정 금액 update() -- commit 
	 * 
	 * 하나의 기능 + 하나의 기능 을 묶어서 단위의 기능을 만들어 내기 위해 필요하다. 
	 * 
	 * 하나의 기능 -> 서비스 
	 * 하나의 기능 + 하나의 기능 -> 서버스 
	 * 
	 * 트랜잭션 처리
	 * 
	 * Repository -> C R U D 
	 */
	@Autowired
	private UserRepository userRepository; 

	// import javax.transaction.Transactional;
	@Transactional
	public int saveUser(User user) {
		try {
			userRepository.save(user);
			return 1; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
}




