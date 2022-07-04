package com.tecoding.blog.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tecoding.blog.auth.PrincipalDetail;
import com.tecoding.blog.dto.RequestFileDto;
import com.tecoding.blog.model.Image;
import com.tecoding.blog.repository.ImageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StoryService {

	private final ImageRepository imageRepository;

	@Value("${file.path}")
	private String uploadFolder;

	@Transactional
	public Page<Image> getImageList(Pageable pageable) {
		return imageRepository.findAll(pageable);
	}

	public void imageUpload(RequestFileDto fileDto, PrincipalDetail principalDetails) {
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" + fileDto.getFile().getOriginalFilename();
		System.out.println("파일명 : " + imageFileName);

		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		System.out.println("파일패스 : " + imageFilePath);
		try {
			Files.write(imageFilePath, fileDto.getFile().getBytes());

			// 1. Image 저장
			Image image = fileDto.toEntity(imageFileName, principalDetails.getUser());
			imageRepository.save(image);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
