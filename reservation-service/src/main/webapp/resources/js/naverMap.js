function naverMap(address){
	var map = new naver.maps.Map('map');
	var myaddress = address;;// 도로명 주소나 지번 주소만 가능 (건물명
											// 불가!!!!)
	    
	naver.maps.Service.geocode({address: myaddress}, function(status, response) {
		if (status !== naver.maps.Service.Status.OK) {
			return alert(myaddress + '의 검색 결과가 없거나 기타 네트워크 에러');
		}
		var result = response.result;
		
		var location  = "https://openapi.naver.com/v1/map/staticmap.bin?clientId=w0YSpFZqo6SXUXy5itSy&url=localhost&center="+result.items[0].point.x+","+result.items[0].point.y+"&level=11&w=300&h=300&baselayer=default&markers="+result.items[0].point.x+","+result.items[0].point.y;
		var hrefLocation = "http://map.naver.com/?lng="+result.items[0].point.x+"&pinTitle=${detail.name}&level=2&pinType=SITE&lat="+result.items[0].point.y+"&enc=utf8";
		
		$(".store_map").attr("src",location); 
		$(".store_location").attr("href",hrefLocation);
		       
	});
}

