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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

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

	@ColumnDefault("0") // int 형 , String 홑 따옴표
	private int count; // 조회수

//	private int userId; // 누가 글을 작성했는지 알아야 한다.
	// DB는 오브젝트롤 저장할 수 없다. FK를 사용한다.
	// Many = Board, User = One
	// Board 정보를 가지고 올때 무조건 들오 와 !  fetch = FetchType.EAGER 
	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "userId") // 연관관계를 맺어 주어야 한다.
	private User userId;

	// 연관관계 주인 설명시 추가
	// private Reply reply; <-- 하지만 한개의 게시글에 답글을 여러건이 될 수 있다.
	// one : 하나으 게시글은 Many 여러건의 댓글을 가질 수 있다.
	// mappedBy 오즈젝트가 생성 될 때 가지고만 와달라고 부탁
	// mappedBy 연관관계의 주인이 아니다 (난 FK가 아니다)
	// DB 에 컬럼을 만들지 마시오 !!!
	// mappedBy = "board"  board 는 Reply 테이블에 필드 이름이다. 
	@OneToMany(mappedBy = "board" , fetch = FetchType.LAZY)
	private List<Reply> reply;
	// @JoinColumn(name ="replyId") <-- 필요 없다. FK (테이블에 만들어 지면 안됨)
	// 1정규화가 깨짐

	@CreationTimestamp
	private Timestamp createDate;

}
