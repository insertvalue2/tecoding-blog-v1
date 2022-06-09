<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>
<!-- 부트스트랩 form 찾아서 가져 오기 -->
<div class="container">
	<!-- <form action="user/join" method="post">
		예전 방식 (get, post 만 사용가능 : js을 이용할 예정) 
	  -->
	<form>
		
		<div class="form-group">
			<label for="username">username:</label>
			<input type="text" class="form-control" placeholder="Enter username" id="username" />
		</div>
	
		<div class="form-group">
			<label for="password">Password:</label>
			<input type="password" class="form-control" placeholder="Enter password" id="password" />
		</div>
		
			
		<div class="form-group">
			<label for="email">Email address:</label>
			<input type="email" class="form-control" placeholder="Enter email" id="email" />
		</div>
	
		
		<div class="form-group form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox" /> Remember me </label>
		</div>
		<button id="btn-save" type="button" class="btn btn-primary">회원가입완료</button>
	</form>
</div>
<script src="/blog/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>
