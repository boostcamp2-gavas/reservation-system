$(document).ready(function() {
	setRolling = setInterval(bannerRolling_nxt, 2000);
	manageCategory();
	getProducInfo(categoryFlag, 0, start);
});
var start = 0;
var categoryFlag = false;


// 배너 관리
var setRolling;
var timeCheck=null;

var test = function autoRolling() {
	// 상단 배너 자동 롤링
	setRolling = setInterval(bannerRolling_nxt, 2000);
}

var cnt = 0;
var size = $('.banner_list').outerWidth();
var len = $('.banner_list').length;
//var len = $('.visual_img').children().length-2;

$('.visual_img').css('width', len*size);

function bannerRolling_nxt() { // 배너 오른쪽 이동
	cnt++;
	if(cnt > len){
		cnt = 1;
	}
	var move = cnt%len;
	$('.banner_list').animate({'left': -(move*size)+'px'}, 'normal');
	

/*	cnt++;
	$('.banner_list').animate({'left': -(cnt*size)+'px'}, 'normal');
	if(cnt == 2) {
		cnt = 0;
		$('.banner_list').animate({'left': 0+'px'}, 0);
	}*/
};

function bannerRolling_pre() {// 배너 왼쪽 이동
	cnt--;
	if(cnt <= 0){
		cnt = 3;
	}
	var move = cnt%len;
	$('.banner_list').animate({'left': -(move*size)+'px'}, 'normal');
	
/*	cnt--;
	var move = cnt%len;
	//if(move < 0) move *= -1;
	if(cnt == 0) {
		cnt = len-1;
	}
	else {
		$('.banner_list').animate({'left': +(cnt*size)+'px'}, 'normal');
	}*/
}
$('.btnBanner ').bind('click', function() {
	var element = $(this).children();
	var btn = element.attr('class')
	
	clearEvent();
	
	if(btn == 'nxt_inn') {
		bannerRolling_nxt();
	}
	else {
		bannerRolling_pre();
	}
	timeCheck = setTimeout(test, 4000);
});

function clearEvent() {
	// 이벤트 해제
	clearInterval(setRolling);
	clearTimeout(timeCheck);
}



// 카테고리 선택 관리
function manageCategory() {
	$('.cate_list:last').find('a').addClass('last');
	$('.cate_list:first').find('a').addClass('active');
}

$('.cate_list').click(function(){
	categoryFlag = false;
	$('#currentCategory').val($(this).data('category'));
	addActiveClass($(this));
	getProducInfo(categoryFlag, $('#currentCategory').val(), start);
});

function addActiveClass(obj) {
	// e, obj 차이?
	var element = obj.find('a'); 
	element.addClass('active');
	
	obj.siblings().find('a').removeClass('active');
	
	if(element.hasClass('last')){
		element.removeClass('last')
	}
	else {
		$('.cate_list:last').find('a').addClass('last');
	}
};


// 상품 리스트 관리
function getProducInfo(flag, categoryId, start) {
	
	if(flag == false)	// '더보기'가 아닐 경우 0번째부터 가져옴
		start = 0;

	var urlInfo = "/productInfo";
	var dataInfo = "";

	if(categoryId == 0 || categoryId == undefined) {	// 전체보기 상품
		urlInfo += "/all/"+start;
		dataInfo = "start="+start;
	}
	else {	// 카테고리별 상품
		urlInfo += "/category/"+categoryId+"/start/"+start;
		dataInfo = "categoryId="+categoryId+"&start="+start;
	}
	
	$.ajax({
	    url : urlInfo,
	    type : "GET",
	    data : dataInfo,
	    success: function(data) {
	    	countProduct(data.productCount);
	    	
	    	if(data.productList.length == 0)
	    		alert("더이상 상품이 없습니다!");
	    	else 
	    		productAppend(data.productList);
	    },
	    error: function(request,status,error){
	        alert("code:"+request.status+"\n"+"error:"+error);
		}
	 
	}); 	
}

$('.btnMore').click(function(){
	categoryFlag = true;
	start++;
	getProducInfo(categoryFlag, $('#currentCategory').val(), start);
})

function countProduct(count) {
	$('.event_lst_txt .pink').html(count+'개');
}

function productAppend(result) {
	
	var source = $("#product_template").html();
	var template = Handlebars.compile(source);
	
	if(categoryFlag == false)	// '더보기'가 아닐 경우 리스트 비움
		$('.lst_event_box li').remove();
	
	$.each(result, function(index, product){
		var data = {productList : product};
		var html = template(data);
		
		if(index % 2 == 0)
			$(".lst_event_box:first").append(html);
		else // 짝수
			$(".lst_event_box:last").append(html);
	});	
}
