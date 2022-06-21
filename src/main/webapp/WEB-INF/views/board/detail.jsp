<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include file="../layout/header.jsp" %>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

<div class="container">
	<button class="btn bg-secondary" onclick="history.back();">돌아가기</button>
	<button class="btn btn-warning" id="btn-update">수정</button>
	<c:if test="${board.user.id == principal.user.id }">
		<button class="btn btn-danger" id="btn-delete">삭제</button>
	</c:if>
	<br />
	<br />
	<div>
		글 번호 : <span id="board-id"><i>${board.id}</i></span><br/>
		글 작성자 : <span id=""><i>${board.user.username}</i></span>
	</div>
	<br />
	<br />
	<div class="form-group m-2">
		<h3>${board.title}</h3>
	</div>
	<hr />
	<div class="form-group m-2">
		<p>${board.content}</p>
	</div>
	<br />
	<hr />
	<br />
</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>
