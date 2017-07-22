<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<head>
<link rel="shortcut icon" href="data:image/x-icon;," type="image/x-icon"> 
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="/resources/css/style.css" rel="stylesheet">
    <style>
		
		._none{
			/* visibility:hidden; */
			display : none;
		}
		
		.layer{
			background : black;
			position: fixed;
  			top: 0px;
  			left: 0px;
  			width : 100%;
  			height: 100%;
  		}
		
		.align-right{
			text-align: right;
		}		
		
		.close{
		    font-size: 35px;
		    color : white;
		    z-index : -1;
		}
		
		.loaction_top{
			top : 5% !important;
		} 
		
		.layer .visual_img{
			margin : auto;
			width : 414px;
			height :100%;
		}
		
		.over-hidden{
			width : 414px;
		 	overflow: hidden;
		 	margin: auto;
		}
		
	</style>
	
</head>

<body>
<div id="container">
    <div class="ct main">
        <div>
            <div class="section_visual">
                <header>
                    <h1 class="logo">
                        <a href="/" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                        <a href="/" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
                    </h1>
                    <a class="btn_my"> <span title="내 예약">MY</span> </a>
                </header>
                <div class="pagination">
                    <div class="bg_pagination"></div>
                    <div class="figure_pagination">
                        <span class="num">1</span>
                        <span class="num off">/ <span> ${fn:length(img)}</span></span>
                    </div>
                </div>
                <div class="group_visual">
                    <div>
                        <div class="container_visual" style="width: 414px;">
                            <ul class="visual_img">
                            <c:forEach items="${img}" var="list" varStatus="status">
                                <li class="item" style="width: 414px;"> <img alt="" class="img_thumb" src="/${list.fileId}"> <span class="img_bg"></span>
                                    <!-- if 문을 추가. 이렇게 하면 매번 if문 체크를 해야되는데 ...   -->
                                    <c:if test="${status.index == 0}">
					    			<div class="visual_txt">
                                         <div class="visual_txt_inn">
                                             <h2 class="visual_txt_tit"> <span>${detail.name}</span> </h2>
                                             <p class="visual_txt_dsc">${detail.description}</p>
                                         </div>
                                     </div>
									</c:if>
                                </li>
							</c:forEach>
                            </ul>
                        </div>
                        <div class="prev">
                            <div class="prev_inn">
                                <a href="#" class="btn_prev" title="이전">
                                    <!-- [D] 첫 이미지 이면 off 클래스 추가 -->
                                    <i class="spr_book2 ico_arr6_lt off"></i>
                                </a>
                            </div>
                        </div>
                        <div class="nxt">
                            <div class="nxt_inn">
                                <a href="#" class="btn_nxt" title="다음">
                                    <i class="spr_book2 ico_arr6_rt"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="group_btn_goto">
                    <a class="btn_goto_home" title="홈페이지" href="${detail.homepage}" target="siteUrl"> <i class="fn fn-home1"></i> </a>
                    <a class="btn_goto_tel" title="전화" href="tel:${detail.tel }"> <i class="fn fn-call1"></i> </a>
		<a class="btn_goto_mail" title="이메일" href="mailto:${detail.email}"> <i class="fn fn-mail1"></i> </a>
                    <a href="#" class="btn_goto_path" title="${detail.placeName}"> <i class="fn fn-path-find1"></i> </a>
                    <a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
                </div>
            </div>
            <div class="section_store_details">
                <!-- [D] 펼쳐보기 클릭 시 store_details에 close3 제거 -->
                <div class="store_details close3">
                    <p class="dsc">
                    ${detail.content }
                    </p>
                </div>
                <!-- [D] 토글 상황에 따라 bk_more에 display:none 추가 -->
                <a  class="bk_more _open"> <span class="bk_more_txt">펼쳐보기</span> <i class="fn fn-down2"></i> </a>
                <a  class="bk_more _close _none" > <span class="bk_more_txt">접기</span> <i class="fn fn-up2"></i> </a>
            </div>
            <div class="section_event">
                <div class="event_info_box">
                    <div class="event_info_tit">
                        <h4 class="in_tit"> <i class="spr_book ico_evt"></i> <span>이벤트 정보</span> </h4>
                    </div>
                    <div class="event_info">
                        <div class="in_dsc"> ${detail.event }</div>
                    </div>
                </div>
            </div>
            <div class="section_btn"> 
	            <button type="button" class="bk_btn" data-config = '${detail.salesFlag}'> 
	            <i class="fn fn-nbooking-calender2"></i>
	            	<span></span> 
	            </button> 
            </div>
            <div class="section_review_list">
                <div class="review_box">
                    <h3 class="title_h3">예매자 한줄평</h3>
                    <div class="short_review_area">
                        <div class="grade_area">
                            <!-- [D] 별점 graph_value는 퍼센트 환산하여 width 값을 넣어줌 -->
                            <span class="graph_mask"> <em class="graph_value"></em> </span>
                            <strong class="text_value"> <span>${avg.avgScore  }</span> <em class="total">5.0</em> </strong>
                            <span class="join_count"><em class="green">${avg.amountOfCount  }건</em> 등록</span>
                        </div>
                        <ul class="list_short_review">
                       
                        <c:forEach items="${comment}" var="list">
                          <li class="list_item">
                                <div>
                                    <div class="review_area">
                                        <div class="thumb_area" >
                                            <a  class="thumb" title="이미지 크게 보기" data-id="${list.id}"> <img width="90" height="90" class="img_vertical_top" src='/${list.fileId }' alt="리뷰이미지"> </a> <span class="img_count">${list.count}</span>                                                </div>
                                        <h4 class="resoc_name">${detail.name}</h4>
                                        <p class="review">${list.comment }</p>
                                    </div>
                                    <div class="info_area">
                                        <div class="review_info"> <span class="grade">${list.score }</span> <span class="name">${list.nickname }</span> <span class="date">${list.createDate } 방문</span> </div>
                                    </div>
                                </div>
				</li>
                         </c:forEach>
                        </ul>
                    </div>
                    <p class="guide"> <i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span> </p>
                </div>
                <a class="btn_review_more" href="#"> <span>예매자 한줄평 더보기</span> <i class="fn fn-forward1"></i> </a>
            </div>
            <div class="section_info_tab">
                <!-- [D] tab 선택 시 anchor에 active 추가 -->
                <ul class="info_tab_lst">
                    <li class="item  _detail">
                        <a class="anchor active"> <span>상세정보</span> </a>
                    </li>
                    <li class="item _path">
                        <a class="anchor"> <span>오시는길</span> </a>
                    </li>
                </ul>
                <!-- [D] 상세정보 외 다른 탭 선택 시 detail_area_wrap에 hide 추가 -->
                <div class="detail_area_wrap">
                    <div class="detail_area">
                        <div class="detail_info">
                            <h3 class="blind">상세정보</h3>
                            <ul class="detail_info_group">
                                <li class="detail_info_lst">
                                    <strong class="in_tit">[소개]</strong>
                                    <p class="in_dsc">
                                     	${detail.content }
                                    </p>
                                </li>
                                <li class="detail_info_lst"> <strong class="in_tit">[공지사항]</strong>
                                    <ul class="in_img_group">
                                        <li class="in_img_lst"> <img alt="" class="img_thumb" src="https://ssl.phinf.net/naverbooking/20170131_238/14858250829398Pnx6_JPEG/%B0%F8%C1%F6%BB%E7%C7%D7.jpg?type=a1000"> </li>
                                    </ul>
                                </li>
                                <li class="detail_info_lst"> <strong class="in_tit">[공연정보]</strong>
                                    <ul class="in_img_group">
                                        <li class="in_img_lst"> <img alt="" class="img_thumb" data-lazy-image="https://ssl.phinf.net/naverbooking/20170131_255/1485825099482NmYMe_JPEG/%B0%F8%BF%AC%C1%A4%BA%B8.jpg?type=a1000"> </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- [D] 오시는길 외 다른 탭 선택 시 detail_location에 hide 추가 -->
                <div class="detail_location hide">
                    <div class="box_store_info no_topline">
                        <a href="#" class="store_location" title="지도웹으로 연결" target = "_blank">
                        <img id="map" class="store_map img_thumb" alt="map">
                            <span class="img_border"></span>
                            <span class="btn_map"><i class="spr_book2 ico_mapview"></i></span>
                        </a>
                        <h3 class="store_name">${detail.name }</h3>
                        <div class="store_info">
                            <div class="store_addr_wrap">
                                <span class="fn fn-pin2"></span>
                                <p class="store_addr store_addr_bold">${detail.placeStreet } </p>
                                <p class="store_addr">
                                    <span class="addr_old">지번</span>
                                    <span class="addr_old_detail">${detail.placeLot}</span>
                                </p>
                                <p class="store_addr addr_detail">${detail.placeName}</p>
                            </div>
                            <div class="lst_store_info_wrap">
                                <ul class="lst_store_info">
                                    <li class="item"> <span class="item_lt"> <i class="fn fn-call2"></i> <span class="sr_only">전화번호</span> </span> <span class="item_rt"> <a href="tel:${detail.tel }" class="store_tel">${detail.tel }</a></span> </li>
                                </ul>
                            </div>
                        </div>
			<!-- [D] 모바일 브라우저에서 접근 시 column2 추가와 btn_navigation 요소 추가 -->
                        <div class="bottom_common_path column2">
                            <a href="#" class="btn_path"> <i class="fn fn-path-find2"></i> <span>길찾기</span> </a>
				<a hewf="#" class="btn_navigation before"> <i class="fn fn-navigation2"></i> <span>내비게이션</span> </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<footer>
    <div class="gototop">
        <a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
    </div>
    <div class="footer">
        <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
        <span class="copyright">© NAVER Corp.</span>
    </div>
</footer>
<div id="photoviwer" class="layer _none">
	<div class = "group_visual ">
		<div class = "align-right">
        	<a class ="close" >X</a>
        </div>
         <div class="pagination">
                    <div class="bg_pagination"></div>
                    <div class="figure_pagination">
                        <span class="num popup">1</span>
                        <span class="num off">/ <span> </span></span>
                    </div>
                </div>
        <div class ="over-hidden">   
            <ul class="visual_img" style="top: 5rem;">
            <!--  template  -->
            
            </ul>
        </div>
        <div class="prev loaction_top">
            <div class="prev_inn">
                <a  class="btn_prev" title="이전">
                    <!-- [D] 첫 이미지 이면 off 클래스 추가 -->
                    <i class="spr_book2 ico_arr6_lt off"></i>
                </a>
            </div>
        </div>
        <div class="nxt loaction_top">
            <div class="nxt_inn">
                <a  class="btn_nxt" title="다음">
                    <i class="spr_book2 ico_arr6_rt"></i>
                </a>
            </div>
        </div>
    </div>
</div>
	<script id="layer-content" type="text/x-handlebars-template">
                    {{#items}}
					<li class="item" style="width: 414px;"> 
                		<img alt="" class="img_thumb" src="/{{file_id}}">
               	 	</li>
					{{/items}}
 	</script>	
				
<!--  get JQuery   -->
<script src="//code.jquery.com/jquery.min.js"></script>

<!--  img Slide  -->
<script src="/resources/js/slide/caroucel.js"></script>


<!--  Handlebar -->
<script src="//cdn.jsdelivr.net/handlebarsjs/4.0.8/handlebars.min.js"></script>

<script src="/resources/js/loginCheck.js"></script>



<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=w0YSpFZqo6SXUXy5itSy&submodules=geocoder"></script>
<script src="/resources/js/naverMap.js"></script>
 
<script>

$(document).ready(function(){
	// 평점 view
	(function(){
		var reviewCount = '${avg.amountOfCount}';
		if(reviewCount<3){
			$(".btn_review_more").addClass('hide');
		}
	})();
	
	
	naverMap('${detail.placeLot}');
	
	var $ul = $(".visual_img:first"),
	$point = $(".num:first"),
	templateSource = $("#layer-content").html(),
	$ulPop = $(".visual_img:last"),
	$popupPoint = $(".num.popup");
	
	var touch = new CaroucelTouch($ul,$point);
	CarocelDetail.init(touch);
	
	
	// layer popup
	
	$(".thumb").on("click",function(){
		var comment = $(this).data("id"),
		caroucelPopup = {};
		caroucelPopup = new CaroucelPopup($ulPop,$popupPoint);
			
		$(".layer").removeClass("_none");
		$.ajax({
			method : "GET",
			url : "/commentImg/"+comment,
			contentType : "application/json; charset=utf-8",
			dataType : "json"
		}).done(caroucelPopup.getLayerImg.bind(caroucelPopup))
		.always(function(){
			// count 초기화 및 module로 이벤트 등록
			$popupPoint.text("1");
			$(".num.off:last > span").text($ulPop.children().length);
			CarocelDetail.init(caroucelPopup);
		});
	});	
	
	
	$(".close").on("click",function(){
		var $ul = $(".visual_img:last");
		$(".layer").addClass("_none");
		$ul.children(".item").remove();	
		CarocelDetail.destroy($ul);
	});
	
	$(".graph_value").css("width",('${avg.avgScore}' * 20)+"%");
	
	(function bkBtnCheck(){
		
		var $btn =$(".bk_btn"), 
		config = $btn.data("config"),
		$span = $(".bk_btn > span");
		
		if(!config){
			$span.text("예매하기");
			$btn.on("click",function(){
				location.href='/product/reservation/'+${detail.id};
			});
		}else if(config === 1){
			$span.text("매진");
		}else{
			$span.text("판매기간 종료");
		}
	})();
	
	
	//store_details
	// 재사용하지 않을거라 판단하여 모듈화를 진행하지 않았습니다. 
	
	$("._open").on("click", function(){
		$(".store_details").removeClass("close3");
		$("._open").addClass("_none");
		$("._close").removeClass("_none");
	});
	
	$("._close").on("click",function(){
		$(".store_details").addClass("close3");
		$("._close").addClass("_none");
		$("._open").removeClass("_none");
	});
	

	
	$("._detail").on("click",function(){
		$("._path>  a").removeClass("active");
		$("._detail> a").addClass("active");
		$(".detail_location").addClass("hide");
		$(".detail_area_wrap").removeClass("hide");
	});
	
	$("._path").on("click",function(){
		$("._detail> a").removeClass("active");
		$(".detail_location").removeClass("hide");
		$(".detail_area_wrap").addClass("hide");
		$("._path>  a").addClass("active");
	});
	
	
	//scroll
	// lazy 부분 
	 $(document).scroll(function(){
		 if ($(window).scrollTop() >= $(document).height() - $(window).height()) {
			 var $img = $(".in_img_lst:last > .img_thumb");
			 var data =  $img.data("lazy-image");
			 $img.attr("src",data);
		 }
	 });
});
	
	
</script>

</body>

</html>
