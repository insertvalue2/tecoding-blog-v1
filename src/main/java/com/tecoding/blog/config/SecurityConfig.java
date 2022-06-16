package com.tecoding.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것 

@Configuration // 빈등록(IoC)
@EnableWebSecurity // 시큐리티 필터로 등록이 된다(필터 커스텀) 
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증 처리를 미리 체크 하겠다. 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean // IoC 가 된다 (필요할 때 가져와서 사요하면 된다.) 
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder(); 
	}
	
	// /auth/login_form", "auth/join_form -> /auth/**
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf 토큰 비활성화 (테스트시만 적용) 
			.authorizeHttpRequests()
				.antMatchers("/auth/**", "/", "/js/**", "/css/**", "/image/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/login_form");
	}
	
}
