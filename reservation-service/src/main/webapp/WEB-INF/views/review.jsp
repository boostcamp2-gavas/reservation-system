<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="/resources/css/style.css" rel="stylesheet">
</head>

<body>
    <div id="container">
		<!-- [D] 예약하기로 들어오면 header에 fade 클래스 추가로 숨김 -->
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="#" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
					<a href="#" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
				</h1>
				<a href="#" class="btn_my"> <span title="내 예약">MY</span> </a>
			</header>
		</div>
        <div class="ct">
            <div class="wrap_review_list">
                <div class="review_header">
                    <div class="top_title gr">
                        <a href="history.back()" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i> </a>
                        <h2><a class="title" href="#">${reviewList[0].productName}</a></h2>
                    </div>
                </div>
                <div class="section_review_list">
                    <div class="review_box">
                        <h3 class="title_h3">예매자 한줄평</h3>
                        <div class="short_review_area">
                            <div class="grade_area"> 
                            	<span class="graph_mask"> 
                            		<em class="graph_value" style="width: ${reviewInfo.starPoint }%;"></em> 
                            	</span> 
                            	<strong class="text_value"> 
                            		<span>
                            			<fmt:formatNumber value="${reviewInfo.scoreAverage }" pattern="0.0"></fmt:formatNumber>
                            		</span> 
                            		<em class="total">5.0</em> 
                            	</strong> 
                            	<span class="join_count">
                            		<em class="green">${reviewInfo.commentCount }건</em> 등록
                            	</span>
                            </div>
                            <ul class="list_short_review" data-comment-count="${reviewInfo.commentCount }" data-product-id="${productId}">
								<c:forEach var="ri" items="${reviewList }">
                            	<li class="list_item">
                                    <div>
                                    	<c:if test="${ri.fileId eq null }">
                                        <div class="review_area no_img">
                                            <h4 class="resoc_name">${reviewList[0].productName}</h4>
                                            <p class="review">${ri.comment }</p>
                                        </div>
                                    	</c:if>
                                    	<c:if test="${ri.fileId ne null }">
                                    	<div class="review_area">
                                            <div class="thumb_area">
                                                <a href="#" class="thumb" title="이미지 크게 보기"> 
                                                	<img width="90" height="90" class="img_vertical_top" src="/files/${ri.fileId }" alt="리뷰이미지"> 
                                                </a> 
                                                <span class="img_count">${ri.imgCount}</span>
                                            </div>
                                            <h4 class="resoc_name">${ri.productName}</h4>
                                            <p class="review">${ri.comment }</p>
                                        </div>
                                    	</c:if>
                                        <div class="info_area">
                                            <div class="review_info"> 
                                            	<span class="grade">${ri.score }</span> 
                                            	<span class="name">${ri.nickname }</span> 
                                            	<span class="date">
                                            		<fmt:formatDate value="${ri.reservationDate }" pattern="yyyy.MM.dd." /> 방문</span> </div>
                                        </div>
                                    </div>
                                </li>                          	
								</c:forEach>
                            </ul>
                        </div>
                        <p class="guide"> <i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span> </p>
                    </div>
                </div>
            </div>
        </div>
        <hr> </div>
		<c:import url="/WEB-INF/views/footer.jsp" />
        <div id="photoviewer">
            <div class="layer" id="layer"></div>
        </div>
    <script id="comment_list_template" type="text/x-handlebars-template">
        {{#commentList}}
        <li class="list_item">
            <div>
                <div class="review_area">
                    {{#if fileId}}
                    <div class="thumb_area">
                        <a href="#" class="thumb" title="이미지 크게 보기"> 
                            <img width="90" height="90" class="img_vertical_top" src="/files/{{fileId }}" alt="리뷰이미지"> 
                        </a> 
                        <span class="img_count">{{imgCount}}</span>
                    </div>
                    {{/if}}
                    <h4 class="resoc_name">{{productName}}</h4>
                    <p class="review">{{comment }}</p>
                </div>
                <div class="info_area">
                    <div class="review_info"> 
                        <span class="grade">{{score }}</span> 
                        <span class="name">{{nickname }}</span> 
                        <span class="date">{{reservationDate}} 방문</span> 
                    </div>
                </div>
            </div>
        </li> 
        {{/commentList}}
    </script>
	<script id="popup_layer_template" type="text/x-handlebars-template">
		{{#item}}
		<div class="sub_layer" style="transform: translateX({{tranx}}%)">
    		<div class="wrapper">
				<img src="/files/{{fileId}}">
    		</div>
			<div class="btn_wrapper">
	    		<button class="com_img_btn close">X</button>
    			<button class="com_img_btn prev"><</button><button class="com_img_btn nxt">></button>
			</div>
    	</div>
		{{/item}}
	</script>
    <script src="/resources/js/node_modules/jquery/dist/jquery.min.js"></script>
	<script src="/resources/js/node_modules/handlebars/dist/handlebars.min.js"></script>
	<script src="/resources/js/node_modules/@egjs/component/dist/component.min.js"></script>
	<script src="/resources/js/messenger.js"></script>    
	<script src="/resources/js/callAjax.js"></script>    
	<script src="/resources/js/review/commentList.js"></script>    
	<script src="/resources/js/review/thumbNail.js"></script>    
    <script>
        $(function() {
            CommentList.init($('ul.list_short_review'));
        });
    </script>
</body>
