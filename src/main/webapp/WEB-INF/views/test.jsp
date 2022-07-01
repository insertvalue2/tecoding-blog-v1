<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	
	
</head>
<body>
	
	<form action="/test/xssTest"  method="get">
		<input name="title" value ="''">
		<input name="content" value="xss 테스트 글입니다. <img src='test'>">
		<button type="submit"> 등록 </button>
	</form>
	
</body>
</html>