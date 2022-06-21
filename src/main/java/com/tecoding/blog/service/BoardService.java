package com.tecoding.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tecoding.blog.model.Board;
import com.tecoding.blog.model.User;
import com.tecoding.blog.repository.BoardRepository;

@Service
public class BoardService  {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void write(Board board, User user) { // title, content 
		
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional
	public Page<Board> getBoardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	@Transactional
	public Board boardDetail(int boardId) {
		return boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("해당글을 찾을 수 없습니다");
		});
	}
	
	@Transactional
	public void deleteById(int boardId) {
		boardRepository.deleteById(boardId);
	}
	
	
	@Transactional
	public void modifyBoard(int id, Board requestBoard) {
		// 영속화 먼저 
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당글을 찾을 수 없습니다");
		}); // 영속화 완료 
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수 종료시 Service 가 종료될 때 트랜잭션이 종료 된다. // 더티 체킹 - 자동 업데이트 (flush) 
	}
	
}
