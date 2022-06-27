package com.tecoding.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User 클래스가 자동으로 MySQL 에 테이블이 생성 된다.
// @DynamicInsert // insert 시 null 필드를 제외 시켜 준다. 
public class User {
	
	@Id // Primary key 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	// 프로젝트에서 연결된 DB의 넘버링 전략을 따라 간다.  
	private int id; // auto_increment (전략) 
	
	@Column(nullable = false, length = 100, unique = true)
	private String username; // 사용자 아이디 
	
	@Column(nullable = false, length = 100) // 12345 --> 해쉬 (비밀번호 암호화) 
	private String password; 
	@Column(nullable = false, length = 50)
	private String email; 
	
//	@ColumnDefault("'user'") // 문자라는 것을 알려 줘야 한다.(' ') 
//	private String role; // Enum 권장 : admin, user, manager (도메인 설정 가능 : 범위)
	// DB 는 RoleType 데이터 타입이 없다. 
	@Enumerated(EnumType.STRING) // DB String 타입이라고 알려 줘야 한다. 
	private RoleType role; // Enum 권장 
	
	
	private String oauth; // kakao, google
	
	@CreationTimestamp // 시간이 자동으로 입력이 된다. 
	private Timestamp createDate; 
	
}
