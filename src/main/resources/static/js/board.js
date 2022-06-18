let index = {
	init: function() {
		$("#btn-save").bind("click", () => { 
			this.save(); 
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
	}
}

index.init();
