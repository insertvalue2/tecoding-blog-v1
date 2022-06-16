package com.tecoding.blog.test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {
	
	@Test
	public void hashEncod() {
		String encPassword = new BCryptPasswordEncoder().encode("asd123");
		System.out.println("해시 : " +encPassword);
		// $2a$10$TCb3HNmsrSMbNITHdsv5kuk.2Ga2ll217BPG3iW48zFK8gWIuOOPG
		// $2a$10$v6U6rwyo6DeMaTiyTnlb1OAY0PTxciHIBg6vwOhG27FLR/eon76oy
	}
}
