package com.tecoding.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${file.path}")
	private String uploadFolder;

	/**
	 * ResourceHttpRequestHandler
	 * ResourceHandlerRegistry는 ResourceHttpRequestHandler를 설정하여 클래스패스, 
	 * WAR, 파일시스템에 존재하는 정적 자원을 제공하기 위해서 사용된다. 
	 * 
	 * 보통 외부 URL 경로를 설정한다.
	 *  그런 다음 외부 URL 경로를 내부적으로 리소스가 실제로 위치한 경로에 매핑한다. 
	 *  
	 *  PathResourceResolver
	 *  가장 간단한 ResourceResolver로 URL 패턴을 가지고 리소스를 찾는다. 
	 *  사실 ResourceChainRegistration에
	 *   어떠한 ResourceResolver도 추가하지 않았다면
	 *   PathResourceResolver를 기본으로 사용한다.
	 *  
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		registry.addResourceHandler("/upload/**") // url 패턴 : /upload/파일명 -> 낚아챔
				.addResourceLocations("file:///" + uploadFolder) // 실제 물리적인 경로
				.setCachePeriod(60 * 10 * 6) // 캐시의 지속시간 설정(초)
				.resourceChain(true) // 리소스 찾는것을 최적화하기 위해서
				.addResolver(new PathResourceResolver());
	}

	@Bean
	public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {
		FilterRegistrationBean<XssEscapeServletFilter> filterRegistration = new FilterRegistrationBean<>();
		filterRegistration.setFilter(new XssEscapeServletFilter());
		filterRegistration.setOrder(1);
		filterRegistration.addUrlPatterns("/*");

		return filterRegistration;
	}
}