package com.tecoding.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auth_increment
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리 <html> 태그가 썩여서 디자인
	
	private int count; // 조회수
 
	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "userId") // 연관관계를 맺어 주어야 한다.
	private User user;

	// mappedBy 연관 관계의 주인이 아니다(테이블에 FK를 만들지 마!) 
	@OneToMany(mappedBy = "board" , fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"board"}) //Reply 안에 있는 board getter를 무시(호출안됨)
	@OrderBy("id desc")
	private List<Reply> replys;

	@CreationTimestamp
	private Timestamp createDate;

}
