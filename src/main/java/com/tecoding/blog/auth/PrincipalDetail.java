package com.tecoding.blog.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tecoding.blog.model.User;

import lombok.Data;

/**
 * 
 * @author tenco
 *	스프링 시큐리티가 로그인 요청을 가로채서
 * 로그인을 행하고 완료 되면 UserDetails 타입의 
 * 오브젝트를 시큐리티의 고유한 세션 저장소에 저장을 해준다.
 * PrincipalDetail (우리가 만든 녀셕이 저장된다)
 *   
 */
@Data
public class PrincipalDetail implements UserDetails{
	/**
	 *  모든 Class는 UID를 가지고 있는데
	 *  Class의 내용이 변경되면 UID값 역시 같이 바뀌어 버립니다.
	 *  직렬화하여 통신하고 UID값으로 통신한게 정상인지 확인하는데
	 *  그 값이 바뀌게 되면 다른 Class로 인식을 해버리게 됩니다.
	 *  이를 방지하기 위해 고유값으로 미리 명시를 해주는
	 *  부분이 바로 "private static final long serialVersionUID"
	 *  이 부분이 되게습니다.
	 */
	private static final long serialVersionUID = 1L;
	
	private User user; // 콤포지션  
	
	public PrincipalDetail(User user) {
		this.user = user; 
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getPassword();
	}
	
	
	/**
	 * isAccountNonExpired
	 * 계정이 만료되지 않았는지 리턴한다.
	 *  (true: 만료 안됨) 
	 *  (false: 계정이 만료된 유저) 
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	/**
	 *  계정 잠김 여부 확인
	 *  NonLocked
	 *   
	 *  true : 사용가능
	 *  false : 잠김  (로그인이 안됨)
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	/**
	 * 비밀번호 만료 여부를 알려준다. 
	 * isCredentialsNonExpired
	 * true  : 사용가능 
	 * false : 만료됨  (로그인이 안됨)   
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	/**
	 *  계정 활성화 여부 확인 
	 *  isEnabled
	 *   
	 *  true : 사용가능 (활성화)
	 *  false : 잠김  (로그인이 안됨)
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	/**
	 * 계정의 권한을 반환한다. 
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<GrantedAuthority>();
//		collectors.add(new GrantedAuthority() {
//			
//		    private static final long serialVersionUID = 1L;
//		    
//			@Override
//			public String getAuthority() {
//				// "ROLE_" 스프링 시큐리티 규칙 꼭 넣어줘야 된다. 
//				// "ROLE_USER", "ROLE_ADMIN" 
//				return "ROLE_" + user.getRole();
//			}
//		});
		collectors.add(() -> {return "ROLE_" + user.getRole();});
		return collectors;
	}
	
}
