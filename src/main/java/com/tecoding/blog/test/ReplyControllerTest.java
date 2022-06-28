package com.tecoding.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tecoding.blog.model.Board;
import com.tecoding.blog.model.Reply;
import com.tecoding.blog.repository.BoardRepository;
import com.tecoding.blog.repository.ReplyRepository;

@RestController
public class ReplyControllerTest {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@GetMapping("/test/board/{id}")
	public Board getBoard(@PathVariable int id) {
		// jackson 라이브러리 실행될 때(오브젝트로 파싱)  json을 파싱해서 모델에 getter 을 호출 
		return boardRepository.findById(id).get(); 
	}
	
	/**
	 * board 를 호출했을 때 Reply에 포함된 board를 무시하는 것이고 
	 * Reply 를 호출 했을 때는 무시하지 않는다. 
	 * 
	 * detail.jsp 에서 reply.board 를 호출하는 순간 무한 참조가 일어 난다.(stack overflow 발생)
	 * 하지만 호출하지 않았기 때문에 오류 발생하지 않음 
	 */
	@GetMapping("/test/reply")
	public List<Reply> getReply() {
		return replyRepository.findAll(); 
	}
}
