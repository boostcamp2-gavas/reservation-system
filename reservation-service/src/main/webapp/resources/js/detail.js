$(document).ready(function() {
	rolling.setting();
	$('.header').addClass('fade');
	
	$('.lazy').lazyload();
	getMap();
});

var startTouchX = null; 
var moveTouchX = null; 


$('.visual_img').bind('touchstart',function (e) {
	event.preventDefault(); 
    var e = event.originalEvent; 
    startTouchX = event.touches[0].pageX; 

});
$('.visual_img').bind('touchmove',function (e) {
	event.preventDefault(); 
    var e = event.originalEvent; 
    moveTouchX = event.touches[0].pageX; 
 
});
$('.visual_img').bind('touchend',function (e) {
	var temp;
	temp = startTouchX-moveTouchX;
	if(temp > 0) {
		imgBtnNxt();
	}
	else {
		imgBtnPre();
	}
});


var imgCount = 1;
var $imgNum = parseInt($('.imgNum').text());

$('.imgBtn ').click(function() {
	var element = $(this).children();
	var btn = element.attr('class');

	if(btn == 'nxt_inn') {
		imgBtnNxt();
	}
	else {
		imgBtnPre();
	}
	imgBtnManage();
});

function imgBtnNxt() {
	if(imgCount == $imgNum)
		return;
	
	$('.imgCurrent').html(++imgCount);
	rolling.rollingNxt();
}

function imgBtnPre() {
	if(imgCount == 1)
		return;
	
	$('.imgCurrent').html(--imgCount);		
	rolling.rollingPre();
}

function imgBtnManage(){
	if (imgCount == 1) {
		$('.ico_arr6_lt').addClass('off');
	}
	else if(imgCount == $imgNum) {
		$('.ico_arr6_lt').removeClass('off');
		$('.ico_arr6_rt').addClass('off');
	}
	else {
		$('.ico_arr6_lt').removeClass('off');
		$('.ico_arr6_rt').removeClass('off');
	}
}

$('.bk_more').click(function() {
	if($(this).hasClass('_open')) { // 펼쳐보기
		$('.store_details').removeClass('close3');
	}
	else { // 접기
		$('.store_details').addClass('close3');
	}
	$(this).css('display', 'none');
	$(this).siblings().css('display', 'block');
});

$('.bk_btn').click(function() {
	var today = getToday();
	
	if($('#sales_flag').val() == 0)
		alert("매진입니다.");
	else if($('#sales_end').val() < today)
		alert("판매기간이 종료되었습니다.");
	else
		$('.bk_btn_reserve').attr('href', '/reserve?productId='+$('#productId').val());
});

function getToday() {
	var date = new Date(); 
	var year = date.getFullYear(); 
	var month = new String(date.getMonth()+1); 
	var day = new String(date.getDate()); 

	// 한자리수일 경우 0을 채워준다. 
	if(month.length == 1){ 
	  month = "0" + month; 
	} 
	if(day.length == 1){ 
	  day = "0" + day; 
	} 
	
	var today = year + "-" + month + "-" + day;
	return today;
}

$('.thumb_area').click(function() {
	event.preventDefault();
	var commentId = $(this).data('comment_id');
	
	$.ajax({
		url : "/productInfo/commentImage/commentId="+commentId,
		data : "commentId="+commentId,
		type : "GET",
		success : function(data){
			viewCommentImage(data);
		},
		error : function(request,status,error){
			alert("code:"+request.status+"\n"+"error:"+error);
		}
	});
})

function viewCommentImage(data) {
	$('#photoviwer').removeClass('hidden');
	
	$('.comment_popup_img').find('li').remove();
	
	var source = $('#comment_image_template').html();
	var template = Handlebars.compile(source);
	
	$.each(data.imageList, function(index, image){
		var data = {commentImageList : image};
		var html = template(data);
		
		$('.comment_popup_img').append(html);
	});
}

$('#btnClose').click(function() {
    $('#photoviwer').addClass('hidden');
});


var popImgCount = 1;

$('.popImgBtn ').click(function() {
	event.preventDefault();
	rolling.init($('.comment_popup_img'));
	
	var element = $(this).children();
	var btn = element.attr('class');

	if(btn == 'nxt_inn') {
		popimgBtnNxt();
	}
	else {
		popimgBtnPre();
	}
});

function popimgBtnNxt() {
	if(popImgCount == 2)
		return;
	
	popImgCount++;
	rolling.rollingNxt();
}

function popimgBtnPre() {
	if(popImgCount == 1)
		return;
	
	popImgCount--;
	// 수정하기
	rolling.rollingPre();
}


$('.teb_info').click(function() {
	$(this).find('a').addClass('active');
	$(this).siblings().find('a').removeClass('active');
	event.preventDefault();
	
	if($(this).hasClass('_detail')) { // 상세정보
		$('.detail_area_wrap').removeClass('hide');
		$('.detail_location').addClass('hide');
	}
	else { // 오시는 길
		$('.detail_area_wrap').addClass('hide');
		$('.detail_location').removeClass('hide');
	}
});

function getMap() {
	var myaddress = $('.store_addr_bold').text();// 도로명 주소나 지번 주소만 가능 (건물명 불가!!!!)
    naver.maps.Service.geocode({address: myaddress}, function(status, response) {
        if (status !== naver.maps.Service.Status.OK) {
            return alert(myaddress + '의 검색 결과가 없거나 기타 네트워크 에러');
        }
        var result = response.result;
        // 검색 결과 갯수: result.total
        // 첫번째 결과 결과 주소: result.items[0].address
        // 첫번째 검색 결과 좌표: result.items[0].point.y, result.items[0].point.x
        var myaddr = new naver.maps.Point(result.items[0].point.x, result.items[0].point.y);
        
        var mapSrc = "https://openapi.naver.com/v1/map/staticmap.bin?clientId=eGDuy2NMeDv1C1QCsPGF&url=http://localhost:8080&crs=EPSG:4326&center="+result.items[0].point.x+","+result.items[0].point.y+"&level=11&w=300&h=300&baselayer=default&markers="+result.items[0].point.x+","+result.items[0].point.y;
        $('.store_map').attr('src', mapSrc);
        var mapUrl = "http://map.naver.com/?lng="+result.items[0].point.x+"&pinTitle="+$('.addr_detail').text()+"&level=2&pinType=SITE&lat="+result.items[0].point.y+"&enc=utf8";
        $('.store_location').attr('href', mapUrl);
    });
}