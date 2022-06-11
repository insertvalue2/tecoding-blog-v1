package com.tecoding.blog.handler;

import org.hibernate.hql.internal.ast.ErrorReporter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tecoding.blog.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalEceptionHandler {
	
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<?> execption(Exception e) {
		log.info("=======================");
		log.info("예외발생 : " + e.getCause());
		log.info("error class : {} ", e.getCause());
		log.info("error msg : {} ", e.getMessage());
		log.info("=======================");
		
		new ErrorResponse();
		ErrorResponse errorResponse = ErrorResponse
				.builder()
				.statusCode(HttpStatus.BAD_REQUEST.toString())
				.code(HttpStatus.BAD_REQUEST.value())
				.message(e.getMessage())
				.build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public String illegalArgumentException(IllegalArgumentException e) {
		return "<h1>" + e.getMessage()+"</h1>";
	} 
	
}
