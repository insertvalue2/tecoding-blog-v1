package com.tecoding.blog.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tecoding.blog.model.Board;
import com.tecoding.blog.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"", "/"})
	public String index(Model model, @PageableDefault(size = 4,
					sort = "id", direction = Direction.DESC) Pageable pageable) {
		Page<Board> boards = boardService.getBoardList(pageable);
		
		
		// 1. 현재 페이지 앞 뒤로 4칸씩 보이기
		// 2. 현재 페이지에 'active'하기
		// 3. 페이지 버튼 클릭 시 해당 페이지로 화면 이동하기
		
		
		//int startPage = boards.getPageable().getPageNumber() - 4;
	    //int endPage = boards.getPageable().getPageNumber() + 4;
	    
		// 현재 페이지 
		int nowPage = boards.getPageable().getPageNumber() + 1;
		// 블럭에 보여줄 시작 페이지
		int startPage = Math.max(nowPage - 2, 1); // 두 int 값 중 큰 값을 반환합니다.  결과는 정수 값에 가까운 인수입니다.
		// 블럭에 보여줄 마지막 페이지
		int endPage = Math.min(nowPage + 2, boards.getTotalPages());
		System.out.println("---------------------");
		log.info("현재 화면의  블록 숫자(현재 페이지) : {}", nowPage);
		log.info("현재 화면에서 보여질 블록 시작 번호: {}", startPage);
		log.info("현재 화면에서 보여질 블록에 마지막 번호 : {}", endPage);
		log.info("화면에 보여줄 총 게시 글 / 화면에 보여질 게시 글(총 페이지수)  : {}", boards.getTotalPages());
		System.out.println("---------------------");
		
		// 만약 현재 페이지가 5번에 있다면 앞 뒤로 4칸씩 보여야 
		// 하니까[ 1 2 3 4 '5' 6 7 8 9 10 ]이렇게 보여야 한다.
		// 하지만 만약 페이지가 6번까지만 있다면 10번이 아니라 마지막 페이지는 6번이 되어야 한다.
		 
		ArrayList<Integer> pageNumbers = new ArrayList<>();
		for(int i = startPage; i <= endPage; i++) {
				pageNumbers.add(i);
		}
		model.addAttribute("boards", boards);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageNumbers",  pageNumbers);
		
		return "index";   
	}
	
	@GetMapping("/board/save_form")
	public String saveForm() {
		return "/board/save_form";
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.boardDetail(id));
		return "/board/detail";
	}
	
	@GetMapping("/board/{id}/update_form")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.boardDetail(id));
		return "/board/update_form";
	}
	
}
