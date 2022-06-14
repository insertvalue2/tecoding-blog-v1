let index = {
	 
	init: function() {
		// let _this = this;	
		$("#btn-save").bind("click", () => { 
			// function() {}, ()=> this.를 바인딩 하기 위해 사용한다.
			//alert("click btn-save run");
			this.save(); 
		});
		
		$("#btn-login").bind("click", () => { 
			this.login(); 
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
			url: "/blog/api/user",
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
			location.href = "/blog";
		}).fail(function(error) {
			alert("회원가입에 실패 하였습니다");
			//alert(JSON.stringify(error));
		});
		// 1 
		// 2  20초 이후 콜백(비동기적) 하다가 돌아 간다. 
		// 3   
	},
	//test: 10
	login: function() {
		let data = {
			username: $("#username").val(), 
			password: $("#password").val()
			
		};
		
		// ajax 호출시 default가 비동기 호출 
		$.ajax({
			// 회원가입 수행 요청
			type: "POST", 
			url: "/blog/api/user/login",
			data: JSON.stringify(data), // http body 데이터 
			contentType: "application/json; charset=utf-8", // 보낼때 데이터 타입
			dataType: "json" 
			// 응답이 왔을 때 기본 데이터 타입 (Buffered 문자열) ==> javaScript Object 변경해준다. 
		}).done(function(data, textStatus, xhr) {
			alert("로그인이 완료 되었습니다");
			location.href = "/blog";
		}).fail(function(error) {
			alert("로그인에 실패 하였습니다");
			//alert(JSON.stringify(error));
		});
	},
}

index.init();
//console.log(index.test); 