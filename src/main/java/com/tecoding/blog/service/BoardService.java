package com.tecoding.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tecoding.blog.dto.ReplySaveRequestDto;
import com.tecoding.blog.model.Board;
import com.tecoding.blog.model.User;
import com.tecoding.blog.repository.BoardRepository;
import com.tecoding.blog.repository.ReplyRepository;

@Service
public class BoardService  {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
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
	
//	@Transactional
//	public void writeReply(User user, int boardId, Reply requestReply) { // title, content 
//		Board board =  boardRepository.findById(boardId).orElseThrow(() -> {
//			return new IllegalArgumentException("댓글 쓰기 실패 : 게시글이 존재 하지 않습니다");
//		});
//		requestReply.setUser(user);
//		requestReply.setBoard(board);
//		replyRepository.save(requestReply);
//	}
//	
	@Transactional
	public void writeReply(ReplySaveRequestDto replySaveRequestDto) {
		int result = replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
		System.out.println("BoardService : "+result);
	}
	
	@Transactional
	public void deleteReplyById(int replyId) {
		replyRepository.deleteById(replyId);
	}
	
}
