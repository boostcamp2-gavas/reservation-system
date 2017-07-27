<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="/resources/css/style.css" rel="stylesheet">
    
    <style type="text/css">
    .none{
    	display:none;
    }
    
    </style>
</head>

<body>
    <div id="container">
        <jsp:include page="common/header.jsp"/>
        <hr>
        <div class="ct">
            <div class="section_my">
                <!-- 예약 현황 -->
                <div class="my_summary">
                    <ul class="summary_board">
                    
                        <li class="item">
                            <!--[D] 선택 후 .on 추가 link_summary_board -->
                            <a href="#" class="link_summary_board on"> <i class="spr_book2 ico_book2"></i> <em class="tit">전체</em> <span class="figure"></span> </a>
                        </li>
                        <li class="item">
                            <a href="#" class="link_summary_board"> <i class="spr_book2 ico_book_ss"></i> <em class="tit">이용예정</em> <span class="figure"></span> </a>
                        </li>
                        <li class="item">
                            <a href="#" class="link_summary_board"> <i class="spr_book2 ico_check"></i> <em class="tit">이용완료</em> <span class="figure"></span> </a>
                        </li>
                        <li class="item">
                            <a href="#" class="link_summary_board"> <i class="spr_book2 ico_back"></i> <em class="tit">취소·환불</em> <span class="figure"> </span> </a>
                        </li>
                    </ul>
                </div>
                <!--// 예약 현황 -->

                <!-- 내 예약 리스트 -->
                
                <div class="wrap_mylist">
          			<!-- 예약 리스트 없음 -->
	                <div class="err"> <i class="spr_book ico_info_nolist"></i>
	                    <h1 class="tit">예약 리스트가 없습니다</h1>
	                </div>
		                <!--// 예약 리스트 없음 -->
				    <ul class="list_cards">
					    <li class ="card expectation">
							
					    </li>
                        <!--[D] 예약확정: .confirmed, 취소된 예약&이용완료: .used 추가 card -->
                        
                        <li class="card confirmed">
							
                        </li>
                        
                        <li class="card used">
                           
                        </li>
                        
                        <li class="card used cancellation">
                           
                        </li>
                	</ul>
                </div>
                <!--// 내 예약 리스트 -->

            </div>
        </div>
        <hr>
    </div>
    <footer>
        <div class="gototop">
            <a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
        </div>
        <div id="footer" class="footer">
            <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
            <span class="copyright">© NAVER Corp.</span>
        </div>
    </footer>

    <!-- 취소 팝업 -->
    <!-- [D] 활성화 display:block, 아니오 버튼 or 닫기 버튼 클릭 시 숨김 display:none; -->
    <div class="popup_booking_wrapper none" >
        <div class="dimm_dark" style="display:block"></div>
        <div class="popup_booking refund">
            <h1 class="pop_tit">
                <span>서비스명/상품명</span>
                <small class="sm">2000.0.00.(월)2000.0.00.(일)</small>
            </h1>
            <div class="nomember_alert">
                <p>취소하시겠습니까?</p>
            </div>
            <div class="pop_bottom_btnarea">
                <div class="btn_gray">
                    <a href="#" class="btn_bottom"><span>아니오</span></a>
                </div>
                <div class="btn_green">
                    <a href="#" class="btn_bottom"><span>예</span></a>
                </div>
            </div>
            <!-- 닫기 -->
            <a href="#" class="popup_btn_close" title="close">
                <i class="spr_book2 ico_cls"></i>
            </a>
            <!--// 닫기 -->
        </div>
    </div>
    <!--// 취소 팝업 -->
    


<script id="reservation-content" type="text/x-handlebars-template">

{{# menubar}}
<div class=link_booking_details>
	<div class="card_header">
		<div class="left"></div>
			<div class="middle">
				<i class="spr_book2 {{icon}}"></i>
				<span class="tit">{{menubar}}</span>
			</div>
		<div class="right"></div>
	</div>
</div>
{{/menubar}}

{{#reservation}}
<article class="card_item">
	<a href="#" class="link_booking_details">
		<div class="card_body">
			<div class="left"></div>
			<div class="middle">
				<div class="card_detail" data-id = {{id}}  >
					<em class="booking_number">No. {{id}}</em>
					<h4 class="tit">{{name}}</h4>
					<ul class="detail">
						<li class="item">
							<span class="item_tit">일정</span>
							<em class="item_dsc">
								{{timeStamp displayStart}} ~{{timeStamp displayEnd}}
							</em>
						</li>
						<li class="item">
							<span class="item_tit">내역</span>
							<em class="item_dsc">
							  {{exit '일반' generalTicketCount}}  {{exit '청소년' youthTicketCount}}   {{exit '어린이' childTicketCount}}  -  합계 ({{plus generalTicketCount youthTicketCount  childTicketCount}})
 							</em>
						</li>
						<li class="item">
							<span class="item_tit">업체</span>
							<em class="item_dsc">
								{{name}}
							</em>
						</li>
					</ul>
					<div class="price_summary">
						<span class="price_tit">결제 예정금액</span>
						<em class="price_amount">
						<span>000,000,000</span>
						<span class="unit">원</span>
						</em>
					</div>
					<!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
					{{#if btns}}
					<div class="booking_cancel">
						<button class="btn"><span>{{btnText btns}}</span></button>
					</div>
					{{/if}}
				</div>
			</div>
			<div class="right"></div>
		</div>
	<div class="card_footer">
		<div class="left"></div>
		<div class="middle"></div>
		<div class="right"></div>
	</div>
	</a>
	<a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
</article>	
{{/reservation}}
</script>	


    
<script src="/resources/js/node_modules/jquery/dist/jquery.js"></script>

<!--  Handlebar -->
<script src="/resources/js/node_modules/handlebars/dist/handlebars.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>


<script src="/resources/js/reservationState.js"></script>

<script>
	// 모듈화 진행해야함. 
	var $article;
	var $cardDetail ;
	var $cardHeader;
	
	var $expectation = $(".link_summary_board > .figure").eq(1);
	var $cancellation = $(".link_summary_board > .figure").eq(3);
	$(".expectation, .confirmed").on("click",".booking_cancel .btn",function(event){
		event.preventDefault();
		$cardDetail = $(this).parents(".card_detail"),
		$popoup = $(".popup_booking_wrapper");
		var productId = $cardDetail.data("id"),
		$article = $cardDetail.parents("article");
		console.log($article);
		$popoup.removeClass("none");
		// id값을 인자로 넘길 방법이 없어, id로 설정하고 가져오는 방식
		$popoup.data("id",productId);
	
	});
	
	
	$(".btn_green").on("click",function(event){
		event.preventDefault();
		var id = $(".popup_booking_wrapper").data("id");
		// card_item 
		$.ajax({
			  method: "Delete",
			  url: "/reservation/"+id
		}).done(success); 
		
	})
	
	function outerHtml(url){
		return url.clone().wrapAll("<div/>").parent().html();
	}
	
	function success(data){
		var expectationCount;
		var cancellationCount;
		
		if(data){
			$cardHeader = $article.siblings(".link_booking_details");
			if($article.siblings().length == 1 ){
			 	$cardHeader.remove();
			}
			$(".cancellation").append(outerHtml($article));
			$(".popup_booking_wrapper").addClass("none");
			$article.remove();	
			// count 변경하는 작업 
			expectationCount = $expectation.text();
			cancellationCount = $cancellation.text();
			$expectation.text(--expectationCount);
			$cancellation.text(++cancellationCount)
			alert("취소 되었습니다.");
		}else{
			alert("예상치 못한 에러가 ...");
		}
	}
	
	// 취소 버튼과 x 버튼 누르면 안보이게 진행
	$(".btn_gray, .popup_btn_close").on("click",function(event){
		event.preventDefault();
		$(".popup_booking_wrapper").addClass("none");
	})
	
	var module = ReservationState;
	module.init();
	
</script>

<script>

moment.locale('ko', {
    weekdays: ["일요일","월요일","화요일","수요일","목요일","금요일","토요일"],
    weekdaysShort: ["일","월","화","수","목","금","토"],
});


// 할일 :: 모둘화 진행
//
var test ;
/* $(function(){
	// helper 정의 
	Handlebars.registerHelper('exit', function (string,value) {
		if(value){
			 return string +' ('+ value+')';
		}
		return '';
	});
	
	Handlebars.registerHelper('plus', function (first,second,third) {
		  return first+ second + third;
	});
	
	
	
		
	Handlebars.registerHelper("timeStamp", function(timestamp) {
		  if (moment) {
		    // can use other formats like 'lll' too
		    return  moment(timestamp).format("YYYY.DD.MM (ddd)");
		  }
		  else {
		    return datetime;
		  }
	});
	
	Handlebars.registerHelper("btnText", function(btn) {
		console.log(btn);
		 return btn;
	});
	
	var templateSource = $("#reservation-content").html();
	var expectationLength =0,
	confirmedLength = 0,
	usedLength =0,
	cancellationLength = 0;
	var Template = Handlebars.compile(templateSource);
	$.ajax({
		method : "GET",
		url : "/reservation/type/0"
	}).done(function(data) {
		if (data.length === 0) {
			$(".err").remove();
		}else{
			var item = {
					reservation : [],
					menubar : [{ menubar : "예약 신청중", icon : "ico_clock"}]
			};
			for (var i = 0, max = data.length; i < max; ++i) {
				data[i].btns = "취소"
				item.reservation.push(data[i]);
			}
			expectationLength += max ;
			var html = Template(item);
			$('.expectation').append(html);
		}
	});
	

	$.ajax({
		method : "GET",
		url : "/reservation/type/1"
	}).done(function(data) {
		if (data.length === 0) {
		 	$(".err").remove();
		}else{
			var item = {
					reservation : [],
					menubar : [{ menubar : "예약 확정", icon : "ico_check2"}]
			};
			for (var i = 0, max = data.length; i < max; ++i) {
				data[i].btns = "취소"
				item.reservation.push(data[i]);
			}
			expectationLength += max;
			var html = Template(item);
			$('.confirmed').append(html);
		}
	});
	
	
	$.ajax({
		method : "GET",
		url : "/reservation/type/2"
	}).done(function(data) {
		if (data.length === 0) {
			$(".err").remove();
		}else{
			var item = {
					reservation : [],
					menubar : [{ menubar : "이용 완료", icon : "ico_check2" }]
			};
			for (var i = 0, max = data.length; i < max; ++i) {
				data[i].btns = "예매자 리뷰 남기기"
				item.reservation.push(data[i]);
			}
			usedLength =max;
	
			var html = Template(item);
			$('.used:first').append(html);
		}
	});
	
	
	$.ajax({
		method : "GET",
		url : "/reservation/type/3"
	}).done(function(data) {
		if (data.length === 0) {
			$(".err").remove();
		}else{
			var item = {
					reservation : [],
					menubar : [{ menubar : "취소된 예약",icon : "ico_cancel"}]
			};
			for (var i = 0, max = data.length; i < max; ++i) {
				item.reservation.push(data[i]);
			}
			cancellationLength = max;
			var html = Template(item);
			$('.used:last').append(html);
		}
	});
	
	
	test = function count(){
		// 이부분은 모듈화 할떄 뺼 것. 
		var $all = $(".figure").eq(0),
		$expectation =$(".figure").eq(1).text(expectationLength),
		$usedLength = $(".figure").eq(2).text(usedLength),
		$cancellation = $(".figure").eq(3).text(cancellationLength)	;
		$all.text(expectationLength+usedLength+cancellationLength);
		$expectation.text(expectationLength);
		$usedLength.text(usedLength);
		$cancellation.text(cancellationLength);
		
		$all.on("click",function(){
			alert("test")
		});
		
		$expectation.on("click",function(){
			alert("test2")
		});
	}
	
});
 */
</script>

</body>

</html>