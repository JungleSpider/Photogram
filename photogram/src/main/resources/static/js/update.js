// (1) 회원정보 수정
function update(userId,evnet) {
	event.preventDefault(); //폼태크 엑션을 막기
	let data = $("#profileUpdate").serialize(); //
	
	console.log(data);
	
	$.ajax({
		type:"put",
		url:"/api/user/" + userId,
		data: data,
    	contentType: "application/x-www-form-urlencoded; charset=utf-8",
    	dataType: "json",
	}).done(res=>{ //HttpStatus 상태코드가 200번대이면 성공
		console.log("update 성공");
		location.href="/user/"+userId;
	}).fail(error=>{//HttpStatus 상태코드가 200아니면 실패
		if(error.data == null){
			alert(error.responseJSON.message);
		}else{
			alert(JSON.stringify(error.responseJSON.data));
		}
		
	});
}