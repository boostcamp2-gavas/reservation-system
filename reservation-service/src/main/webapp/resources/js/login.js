$(function() {
	$('#naver_id_login').trigger('click');
	
	
});

$('#naver_id_login').bind("click", function(){ 
	
	// 네이버아디디로로그인 초기화 Script 
		var naver_id_login = new naver_id_login("eGDuy2NMeDv1C1QCsPGF", "http://localhost:8080/mvMyPage");
		var state = naver_id_login.getUniqState();
		naver_id_login.setButton("green", 3,40);
		naver_id_login.setDomain("//localhost:8080");
		/* 	naver_id_login.setState(state);*/
		//naver_id_login.setPopup(); 
		naver_id_login.init_naver_id_login();
		


		// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
		function naverSignInCallback() {
			// naver_id_login.getProfileData('프로필항목명');
			// 프로필 항목은 개발가이드를 참고하시기 바랍니다.
			alert(naver_id_login.getProfileData('email'));
			alert(naver_id_login.getProfileData('nickname'));
			//alert(naver_id_login.getProfileData('age'));
		}

		// 네이버 사용자 프로필 조회
		naver_id_login.get_naver_userprofile("naverSignInCallback()");

	
});

