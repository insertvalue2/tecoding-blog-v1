<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
	<!-- 다시 select 할 필요 없이 세션이 있는 정보를 가지고 온다 	
		  	principalDetail class 확인 	 -->
	<form action="#" method="post">
		<input type="hidden" id="id" value="${principal.user.id}">
		<div class="form-group">
			<label for="username">username:</label>
			<input type="text" value="${principal.user.username}" name="username" class="form-control" placeholder="Enter username" id="username" readonly="readonly" />
		</div>
		
		<c:if test="${empty principal.user.oauth}">
			<div class="form-group">
				<label for="password">Password</label> 
				<input type="password" class="form-control" placeholder="Enter password" id="password">
			</div>
		</c:if>
				
		<div class="form-group">
			<label for="email">Email address:</label>
			<input type="email" value="${principal.user.email}"  name="email" class="form-control" placeholder="Enter email" id="email" />
		</div>
		<button id="btn-update" type="button" class="btn btn-primary">회원수정완료</button>
	</form>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>
