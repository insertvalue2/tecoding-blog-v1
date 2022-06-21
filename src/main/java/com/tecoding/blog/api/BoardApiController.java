package com.tecoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tecoding.blog.auth.PrincipalDetail;
import com.tecoding.blog.dto.ResponseDto;
import com.tecoding.blog.model.Board;
import com.tecoding.blog.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;
	  
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, 
			@AuthenticationPrincipal PrincipalDetail detail) { 
		
		boardService.write(board, detail.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK, 1); 
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		boardService.deleteById(id);
		return new ResponseDto<Integer>(HttpStatus.OK, 1); 
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
		boardService.modifyBoard(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	
	
}


