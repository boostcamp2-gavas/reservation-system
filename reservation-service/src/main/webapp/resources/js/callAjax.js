function callAjax(url, method, data) {
	return $.ajax({
	    method : method || 'get',
	    url : url,
	    data : data || null,
	    error : function(request, status, error ) {  
	    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    	}
	});
}