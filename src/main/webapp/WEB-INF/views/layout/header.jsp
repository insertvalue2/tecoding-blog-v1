<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>tencoding</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta id="_csrf" name="${_csrf.parameterName}" content="${_csrf.token}" />
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
</head>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
<!-- slim 버전에서 $.ajax 를 사용할 수 없어 추가 선언  -->
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<!--  
		<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
		 -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/">Home</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<c:choose>
					<c:when test="${empty principal}">
						<li class="nav-item"><a class="nav-link" href="/auth/login_form">로그인</a></li>
						<li class="nav-item"><a class="nav-link" href="/auth/join_form">회원가입</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href="/board/save_form">글쓰기</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/update_form">회원정보</a></li>
						<li class="nav-item">
							<!-- 
									CSRF가 활성화된 경우 로그아웃 요청도 기본적으로 POST여야 한다. 즉, POST "/logout"이 필요하다.
									※ POST가 default인 이유? 사용자를 어플리케이션에서 강제로 로그아웃시키는 CSRF 공격을 방지하기 위함.
								 --> <a class="nav-link" href="/logout">로그아웃</a>
						</li>
					</c:otherwise>
				</c:choose>
				<li class="nav-item dropdown"><a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown" role="button" aria-expanded="false"> STORY</a>
					<ul class="dropdown-menu" role="menu">
						<li class="nav-item "><a class="dropdown-item " href="/story/home"><span class="text-primary"><i class="fas fa-cloud">&nbsp;&nbsp;</i>show story
							</span></a></li>
						<li class="nav-item"><a class="dropdown-item d-flex justify-content-between"  href="/story/upload"><span class="text-primary"><i class="fas fa-file">&nbsp;&nbsp;</i>upload story</span></a></li>
					</ul></li>
			</ul>
		</div>
	</nav>

</body>
</html>
