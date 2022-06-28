package com.tecoding.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 200)
	private String content;

	// 누가 어느 게시글에 있는 답편인지 연관관계가 필요하다.
	// 하나의 게시글에는 여러개의 답변이 있을 수 있다.
	@JoinColumn(name = "boardId")
	@ManyToOne // 여러개의 답편은 하나의 게시글에 존재할 수 있다.
	@JsonIgnoreProperties({"replys", "user", "content", "userId", "id"})
	private Board board;
 
	// 하나더 필요 (답변을 누가 적었는지 알아야 한다.)
	// Replay <--> User (여러개의 답변은 하나의 유저가 사용할 수 있다)
	@ManyToOne
	@JoinColumn(name = "userId") // userId <-- reply 테이블에 컬럼 명이 된다.
	@JsonIgnoreProperties({"password", "id", "email", "role"})
	private User user;


	@CreationTimestamp
	private Timestamp createDate;

}
