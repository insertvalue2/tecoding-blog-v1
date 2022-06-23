package com.tecoding.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tecoding.blog.auth.PricipalDetailService;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것 

@Configuration // 빈등록(IoC)
@EnableWebSecurity // 시큐리티 필터로 등록이 된다(필터 커스텀)
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증 처리를 미리 체크 하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PricipalDetailService pricipalDetailService;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// 1
	@Bean // IoC 가 된다 (필요할 때 가져와서 사용하면 된다.)
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	// 시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데
	// 해당 password가 뭘로 해시가 되었는지 알려줘야 한다.
	// 같은 해시로 암호화해서 DB에 있는 해시와 비교할 수 있다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 1. userDetailsService 들어갈 Object 만들어 주어야 한다.
		// 2. passwordEncoder 우리가 사용하는 해시 함수를 알려 줘야 한다.

		auth.userDetailsService(pricipalDetailService).passwordEncoder(encodePWD());
	}

	// 2 추후 처리
	// /auth/login_form", "auth/join_form -> /auth/**
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // csrf 토큰 비활성화 (테스트시만 적용)
				.authorizeHttpRequests().antMatchers("/dummy/**", "/auth/**", "/", "/js/**", "/css/**", "/image/**")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/auth/login_form")
				.loginProcessingUrl("/auth/loginProc") // 뷰 단에 설정 주소와 일치만 하면 된다.
				.defaultSuccessUrl("/");
		// .failureUrl(null);
		// 스프링 시큐리티가 해당 주소로 요청이 오는 로그인을 가로채서 대신 로그인해 준다.
		// 만들어야할 클래스가 있다. UserDetails type 에 Object를 만들어 줘야 한다.
	}

}
