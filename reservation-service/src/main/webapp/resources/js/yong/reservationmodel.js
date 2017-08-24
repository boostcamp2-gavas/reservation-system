define(['jquery'], function( $ ) {
	var categoryId;
	var pageNum;
	var cachedData = {};

	function getEvent(fp){
		var url = "/resources/api/"+categoryId+"-"+pageNum+".json";
		var data = cachedData[url];

		if(data){
			fp(data);
		}else{
			$.ajax(url).then(function(json){
				cachedData[url] = json;
				fp(json);
			});	
		}
		
	}

	function setInfo(id, num){

		categoryId = id;
		pageNum = num;
	}

	return {
		setInfo : setInfo,
		get : getEvent
	}
});
