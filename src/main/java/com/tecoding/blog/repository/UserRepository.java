package com.tecoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecoding.blog.model.User;

// DAO  Data Access Object 
// User 테이블을 관리하는 User primary key : Integer 
// Bean 으로 등록 될 수 있나요? --> 스프링에서 IoC 에서 객체를 가지고 있나요? 
// 자동으로 빈으로 등록이 된다. @Repository //생략 가능 
public interface UserRepository extends JpaRepository<User, Integer>{
		
	
}
