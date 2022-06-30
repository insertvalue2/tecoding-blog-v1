package com.tecoding.blog.controller;

import java.util.ArrayList;
import java.util.List;

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

	public static int PAGENATION_BLOCK_COUNT = 3; 
	
	@Autowired
	private BoardService boardService;

	@GetMapping({ "", "/" , "/board/search"})
	public String index(String searchValue, Model model,
			@PageableDefault(size = 4, sort = "id", direction = Direction.DESC) Pageable pageable) {
		
		// 검색 역슬래시 오류 처리  
		String searchTitle = searchValue == null ? "" : searchValue ;
		Page<Board> boards = boardService.searchBoard(searchTitle.replace("\\",  ""), pageable);
		  
		// 현재 페이지
		int nowPage = boards.getPageable().getPageNumber() + 1;
		// 블럭에 보여줄 시작 페이지
		int startPage = Math.max(nowPage - PAGENATION_BLOCK_COUNT, 1); // 두 int 값 중 큰 값을 반환합니다. 결과는 정수 값에 가까운 인수입니다.
		// 블럭에 보여줄 마지막 페이지
		int endPage = Math.min(nowPage + PAGENATION_BLOCK_COUNT, boards.getTotalPages());
	
		ArrayList<Integer> pageNumbers = new ArrayList<>();
		for (int i = startPage; i <= endPage; i++) {
			pageNumbers.add(i);
		}
		model.addAttribute("boards", boards);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageNumbers", pageNumbers);
		model.addAttribute("searchTitle", searchTitle);
		
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
	
	public static String stringReplace(String str){       
	    String match = "[^\uAC00-\uD7A30-9a-zA-Z]";
	    str = str.replaceAll(match, " ");
	    return str;
	}

}
