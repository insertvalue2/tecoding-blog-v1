<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="layout/header.jsp"%>

<div class="container">
	<div class="m-2 d-flex justify-content-end">
		<form class="form-inline" action="/board/search" method="get">
			<input type="text" class="form-control" placeholder="검색어를 입려하세요"
				name="searchValue" value="${fn:replace(searchTitle, '\\', '  ')}" id="search--text" />
			<button type="submit" class="btn btn-warning text-dark ml-2"
				id="search--title">TITLE</button>
		</form>
	</div>
	<br />

	<c:forEach var="board" items="${boards.content}">
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>
				<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</c:forEach>
</div>
<br />
<ul class="pagination justify-content-center">
	<c:set var="isDisabled" value="disabled"></c:set>
	<c:set var="isNotDisabled" value=""></c:set>
	<c:set var="isNowPage" value="active"></c:set>
	<li class="page-item ${boards.first ? isDisabled : isNotDisabled}"><a
		class="page-link" href="?page=${boards.number - 1}">Previous</a></li>
	<c:forEach var="num" items="${pageNumbers}">
		<c:choose>
			<c:when test="${boards.number + 1 eq num}">
				<li class="page-item ${isNowPage}"><a class="page-link"
					href="/board/search/?searchValue=${searchTitle}&page=${num - 1}">${num}</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link"
					href="/board/search/?searchValue=${searchTitle}&page=${num - 1}">${num}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<li class="page-item ${boards.last ? isDisabled : isNotDisabled}"><a
		class="page-link" href="?page=${boards.number + 1}">Next</a></li>
</ul>

<script src="/js/index.js"></script>
<%@ include file="layout/footer.jsp"%>