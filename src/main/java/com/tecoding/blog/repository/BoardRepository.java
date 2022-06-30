package com.tecoding.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tecoding.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	// select * from board where title like '%a%';
	Page<Board> findByTitleContaining(String searchValue, Pageable pageable);
}
