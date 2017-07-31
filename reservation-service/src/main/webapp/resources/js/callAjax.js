function callAjax(url, method, data) {
	return new Promise(function(resolve, reject) {
		$.ajax({
		    method : method || 'get',
		    url : url,
		    data : data || null,
		    success : resolve,
		    error : reject || function(request, status, error ) {  
		    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    	}
		});
	});
}