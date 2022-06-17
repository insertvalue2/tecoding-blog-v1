package com.tecoding.blog.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tecoding.blog.model.User;
import com.tecoding.blog.repository.UserRepository;

@Service
public class PricipalDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository; 
	
	/**
	 * 스프링이 로그인 요청을 가로챌 때 username, password 를 2개 가로채는데 
	 * password 부분은 알아서 처리하고 username이 DB 있는지만 확인해 주면 된다.
	 * 확인은 아래 함수에서 한다.  
	 * 
	 * 시큐리티 로그인 처리시 해당 메서드가 동작하게 된다. 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User principal = userRepository.findByUsername(username)
				.orElseThrow(() -> {
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다 :" + username);
				});
		System.out.println(": principal L :" + principal.toString());
		return new PrincipalDetail(principal); // 시큐리티 세션에 유저 정보가 저장이 된다. 
	}
	
}
