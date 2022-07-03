<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>
<!-- 부트스트랩 form 찾아서 가져 오기 -->
<div class="container">
	<!-- /blog/api/login : 사용안함 -->
	<!-- !! controller loginProc 를 만들지 않음(스프링 시큐리티가 가로채기 ) -->
	<!--  form 반드시 key 값을 name 속성을 명시 해야 한다. -->
	<!--  /auth/loginProc 주소는 마음대로 지정 가능 대신 필터에 사용한 주소와 일치 해야 한다. -->
	<form action="/auth/loginProc" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<div class="form-group">
			<label for="username">username:</label>
			<input type="text" class="form-control" placeholder="Enter username" name="username" id="username" />
		</div>
	
			
		<div class="form-group">
			<label for="password">Password:</label>
			<input type="password" class="form-control" placeholder="Enter password" name="password" id="password" />
		</div>
		
		<div class="form-group form-check">
			<label class="form-check-label">
			<input class="form-check-input" type="checkbox" /> Remember me </label>
		</div>
		<button type="submit" id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=141b8184eb9a5187f47d408f956643c0&redirect_uri=http://localhost:9090/auth/kakao/callback&response_type=code"> <img src="/image/kakao_login.png" width="74" height="38"></a>		
	</form>
	
	
	
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>

