package com.tecoding.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class TecoblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(TecoblogApplication.class, args);
	}

}
