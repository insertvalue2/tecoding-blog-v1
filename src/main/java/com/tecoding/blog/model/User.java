package com.tecoding.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity // User 클래스가 자동으로 MySQL 에 테이블이 생성 된다. 
public class User {
	
	@Id // Primary key 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	// 프로젝트에서 연결된 DB의 넘버링 전략을 따라 간다.  
	private int id; // auto_increment (전략) 
	@Column(nullable = false, length = 30)
	private String username; // 사용자 아이디 
	@Column(nullable = false, length = 100) // 12345 --> 해쉬 (비밀번호 암호화) 
	private String password; 
	@Column(nullable = false, length = 50)
	private String email; 
	
	@ColumnDefault("'user'") // 문자라는 것을 알려 줘야 한다.(' ') 
	private String role; // Enum 권장 : admin, user, manager (도메인 설정 가능 : 범위)  
	
	@CreationTimestamp // 시간이 자동으로 입력이 된다. 
	private Timestamp createDate; 
	
}
