let index = {
	 
	init: function() {
		$("#btn-save").bind("click", () => { 
			this.save(); 
		});
		
		$("#btn-update").bind("click", () => { 
			this.update(); 
		});
	},
	
	save: function() {
		let data = {
			username: $("#username").val(), 
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		// ajax 호출시 default가 비동기 호출 
		$.ajax({
			// 회원가입 수행 요청
			type: "POST", 
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http body 데이터 
			contentType: "application/json; charset=utf-8", // 보낼때 데이터 타입
			dataType: "json" 
			// 응답이 왔을 때 기본 데이터 타입 (Buffered 문자열) ==> javaScript Object 변경해준다. 
		}).done(function(data, textStatus, xhr) {
			console.log(xhr);
			console.log(textStatus);
			console.log(data);
			if(data.status)
			alert("회원가입이 완료 되었습니다");
			location.href = "/";
		}).fail(function(error) {
			alert("회원가입에 실패 하였습니다");
			
		});
	},
	
	update: function() {
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
