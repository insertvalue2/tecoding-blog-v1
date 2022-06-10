package com.tecoding.blog.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 같은 변수이름으로 데이터 타입을 다르게 사용해야 될 때는 제네릭 프로그래밍을 생각하자.! 
public class ResponseDto<T> {
	HttpStatus status; 
	T data; 
}
