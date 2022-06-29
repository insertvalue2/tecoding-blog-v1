let index = {
	init: function() {
		$("#btn-save").bind("click", () => { 
			this.save(); 
		});
		
		$("#btn-delete").bind("click", () => { 
			this.deleteById();
		});
		
		$("#btn-update").bind("click", () => { 
			this.update(); 
		});
		
		$("#btn-reply-save").bind("click", () => { 
			this.replySave(); 
		});
	},
	save: function() {
		let data = {
			title: $("#title").val(), 
			content: $("#content").val(),
		};
		
		$.ajax({
			type: "POST", 
			url: "/api/board",
			data: JSON.stringify(data),  
			contentType: "application/json; charset=utf-8", 
			dataType: "json" 
		}).done(function(data, textStatus, xhr) {
			if(data.status)
			alert("글쓰기가 완료 되었습니다");
			location.href = "/";
		}).fail(function(error) {
			alert("글쓰기에 실패 하였습니다");
		});
	},
	
	deleteById: function() {
		let id = 	$("#board-id").text(); 
		$.ajax({
			type: "DELETE", 
			url: "/api/board/" + id
		}).done(function(data, textStatus, xhr) {
			if(data.status)
			alert("삭제가 완료 되었습니다");
			location.href = "/";
		}).fail(function(error) {
			alert("글쓰기에 실패 하였습니다");
		});
	},
	
	update: function() {
		let boardId = $("#id").val();  
		
		let data = {
			title: $("#title").val(), 
			content: $("#reply-content").val(),
		};
		
		$.ajax({
			type: "PUT", 
			url: "/api/board/" + boardId,
			data: JSON.stringify(data),  
			contentType: "application/json; charset=utf-8", 
			dataType: "json", 
			async : false
		}).done(function(data, textStatus, xhr) {
			console.log(data);
			console.log(textStatus);
			console.log(xhr);
			if(data.status)
			alert("글 수정이 완료 되었습니다");
			//location.href = "/";
		}).fail(function(error) {
			alert("글쓰기에 실패 하였습니다");
		});
	}, 
	
	replySave: function() {
		// 데이터 가져 오기 (boardId : 해당 게시글에 아이디)
		let data = {
			boardId: $("#board-id").text(), 
			content: $("#reply-content").val()
		}
		
		// `` 백틱 (자바스크립트 변수를 문자열 안에 넣어서 사용할 수 있다) 
		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`, 
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8", 
			dataType: "json" 
		})
		.done(function(response) {
			if(response.status) {
				addReplyElement(response.data);
			} 
		})
		.fail(function(error) {
			alert("댓글 작성에 실패하였습니다.");
		}); 
	},
	
	replyDelete : function(boardId, replyId){
			$.ajax({ 
				type: "DELETE",
				url: `/api/board/${boardId}/reply/${replyId}`,
				dataType: "json"
			}).done(function(resp){
				alert("댓글삭제 성공");
				location.href = `/board/${boardId}`;
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		}
}

function addReplyElement(reply) {
	let childElement = `<li class="list-group-item d-flex justify-content-between" id="reply--${reply.id}" >
				<div>${reply.content}</div>
				<div class="d-flex">
					<div>작성자 : ${reply.user.username}&nbsp;&nbsp;</div>  
					<button class="badge badge-danger">삭제</button>
				</div>
			</li>`;
	$("#reply--box").prepend(childElement);
	$("#reply-content").val("");		
}

index.init();
