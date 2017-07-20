<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
<script type="text/javascript">
	var array = [];
	
	var naver_id_login = new naver_id_login("w0YSpFZqo6SXUXy5itSy", "http://10.81.25.154/login");
	// 접근 토큰 값 출력
	alert(naver_id_login.oauthParams.access_token);
	// 네이버 사용자 프로필 조회
	naver_id_login.get_naver_userprofile("naverSignInCallback()");
	console.log(naver_id_login);
	// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
	function naverSignInCallback() {
		array = [naver_id_login.getProfileData('email'),
		naver_id_login.getProfileData('nickname'),
		naver_id_login.getProfileData('age')];
	}
	
	
	
	
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/login");
	
	// input으로 만드는 과정 
	array.forEach(function(){
		
	});
	
	//히든으로 값을 주입시킨다.
	for(var key in params) {
	    var hiddenField = document.createElement("input");
	    hiddenField.setAttribute("type", "hidden");
	    hiddenField.setAttribute("name", key);
	    hiddenField.setAttribute("value", params[key]);
	
	    form.appendChild(hiddenField);
	}
	
	document.body.appendChild(form);
	form.submit();


</script>
</html>