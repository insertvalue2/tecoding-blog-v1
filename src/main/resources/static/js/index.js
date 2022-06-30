let index = {
	 
	init: function() {
		$("#search--title").bind("click", () => { 
			this.searchTitle(); 
		});
		
		$("#search--name").bind("click", () => { 
			this.searchName(); 
		});
	},
	
	// search, searchType
	searchTitle: function() {
		let searchType = $("#search--title").data("key");
		let searchValue = $("#search--text").val(); 
		
		console.log("searchType  : " + searchType);
		console.log("searchValue : " + searchValue);
		
		$.ajax({
			type: "GET", 
			url: `/board/search?searchType=${searchType}&searchValue=${searchValue}`,
			contentType: "x-www-form-urlencoded; charset=utf-8", // 보낼때 데이터 타입
			dataType : "json"	
		}).done(function(response){
			console.log(response);
		}).fail(function(error) {
			console.log(error);
		});
	},
	
	searchName: function() {
		let data = {
			id: $("#id").val(),
			username: $("#username").val(), 
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		$.ajax({
			type: "PUT", 
			url: "/user",
			data: JSON.stringify(data), // http body 데이터 
			contentType: "application/json; charset=utf-8", // 보낼때 데이터 타입
			dataType: "json" 
		}).done(function(data, textStatus, xhr) {
			if(data.status)
			alert("회원수정이 완료 되었습니다");
			location.href = "/";
		}).fail(function(error) {
			alert("회원수정에 실패 하였습니다");
		});
	}
	
}


index.init();
