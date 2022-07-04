<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<!--사진 업로드페이지 중앙배치-->
<main class="container py-5">
	<div>
		<form class="m-5" enctype="multipart/form-data" method="post" action="/story/image/upload">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<div class="custom-file">
				<input type="file" name="file" class="custom-file-input" id="customFile" required="required">
				<label class="custom-file-label " for="customFile">파일을 선택해주세요</label>
			</div>

			<div class="input-group mt-2">
				<div class="input-group-prepend">
					<span class="input-group-text">스토리 설명</span>
				</div>
				<input type="text" name="storyText" class="form-control" required="required">
			</div>

			<div class="input-group mt-3">
				<button type="submit" class="btn btn-info">사진업로드</button>
			</div>
		</form>
	</div>

</main>
<br />
<br />
<script>
	$(".custom-file-input").bind(
			"change",
			function() {
				let fileName = $(this).val().split("\\").pop();

				$(this).siblings(".custom-file-label").addClass("selected")
						.html(fileName);
			});
</script>



<%@ include file="../layout/footer.jsp"%>
