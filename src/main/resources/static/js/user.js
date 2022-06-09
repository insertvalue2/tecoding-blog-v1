let index = {
	init: function() {
		$("#btn-save").bind("click", () => {
			alert("click btn-save run");
			this.save(); 
		});
	},
	
	save: function() {
		let data = {
			username: $("#username").val(), 
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		//console.log(data);
		//$.ajax().done().fail(); 
		// ajax 통신을 이용해서 3개의 파라메터를 json 변경해서 insert 요청 
		// data -> js object --> json 형식에 문자열로 변경 
	}
	
}

index.init(); 