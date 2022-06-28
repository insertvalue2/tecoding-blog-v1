<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include file="../layout/header.jsp" %>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

<div class="container">
	<button class="btn bg-secondary" onclick="history.back();">돌아가기</button>
	
	<c:if test="${board.user.id == principal.user.id }">
		<a href="/board/${board.id}/update_form" class="btn btn-warning">수정</a>
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
	
	<div class="card">
		<div>
			<div class="card-body"><textArea class="form-control" rows="1"></textArea></div>
			<div class="card-footer"><button class="btn btn-primary">등록 </button></div>
		</div>
	</div>
	<br/>
	
	<div class="card">
		<div class="card-header">댓글 리스트</div>
	 </div>
	 <ul class="list-group" id="reply--box">
	 	<c:forEach var="reply" items="${board.replys}">
	 		<li class="list-group-item d-flex justify-content-between" id="reply--1">
	 		<div>${reply.content}</div>
	 		<div class="d-flex ">
	 			<div class="font-italic">${reply.user.username} :&nbsp; tenCoding &nbsp;</div>
	 			<button class="bage">삭제</button>
	 		</div>
 		</li>
	 	</c:forEach>
	</ul>
	 
</div>



<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>
