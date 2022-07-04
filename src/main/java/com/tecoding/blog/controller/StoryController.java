package com.tecoding.blog.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecoding.blog.auth.PrincipalDetail;
import com.tecoding.blog.dto.RequestFileDto;
import com.tecoding.blog.model.Image;
import com.tecoding.blog.service.StoryService;

@Controller
@RequestMapping("/story")
public class StoryController {

	@Autowired
	StoryService storyService;
	
	@GetMapping("/home")
	public String storyHome(Model model, @PageableDefault(size = 4, sort = "id", direction = Direction.DESC) Pageable pageable) {
		Page<Image>  imagePage =  storyService.getImageList(pageable);
		model.addAttribute("imagePage", imagePage);
		
		return "/story/home";
	}
	
	@GetMapping("/upload")
	public String storyUpload() {
		return "/story/upload";
	}
	
	@PostMapping("/image/upload")
	@ResponseBody
// 1 	
//	public String storyImageUpload(MultipartFile file, String storyText ) {
	public String storyImageUpload(RequestFileDto fileDto, @AuthenticationPrincipal PrincipalDetail principalDetail) throws IllegalArgumentException {
		
//		System.out.println("getContentType : " + fileDto.getFile().getContentType());
//		System.out.println("getName : " + fileDto.getFile().getName());
//		System.out.println("getOriginalFilename : " + fileDto.getFile().getOriginalFilename());
//		System.out.println("getSize : " + fileDto.getFile().getSize());
//		System.out.println("stroy Text : " + fileDto.getStoryText());
//		try {
//			System.out.println("getBytes : " + fileDto.getFile().getBytes().toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		// 1 byte -> 1000byte(KB) -> 1,000,000(MB)
		// -> 1,000,000,000(GB) -> 1,000,000,000,000(TB) 

		// 1 Bit = 2진수 (0과 1)
		// 1 Byte (1바이트) = 8bit (8비트)
		// 1 KB (킬로바이트) = 2의 10승 바이트, 2를 10번 곱한 것 = 1024 byte
		// 단위 환산 
		// 1 byte -> 1024 byte(KB) -> 1024 KB(MB) -> 1024 MB (GB) -> 1024 GB(TB)
		
		/**
		 * 1키로바이트(KByte)가 1,024바이트다. 
		 * 왜 컴퓨터에서는 1,000배가 아니라 1,024배일까?
		 * 컴퓨터가 1,000배보다는 
		 * 1,024배를 훨씬 빨리 계산하기 때문에,
		 * 좀더 빠른 속도를 얻기 위해서 1,024배로 약속한 것이다.
		 * 
		 * 컴퓨터는  2진수로 계산하는 것이 가장 편하고 빠르기 때문에 모든
		 * 숫자는 2진수 단위로 관리한다.
		 * 그래서 컴퓨터는 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024와 
		 * 같이 2의 제곱으로 된 단위를 사용한다.
		 */
		storyService.imageUpload(fileDto, principalDetail);
		return "redirect:/";
	}
	
}
