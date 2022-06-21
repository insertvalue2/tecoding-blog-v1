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
			content: $("#content").val(),
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
	}
}

index.init();
