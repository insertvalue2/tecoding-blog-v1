package com.tecoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecoding.blog.model.Image;

public interface  ImageRepository extends  JpaRepository<Image, Integer>{

	
}
