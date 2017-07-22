<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<input type="hidden" id="currentCategory" value="0">
<input type="hidden" id="moreCnt" value="0">
    <div id="container">
    	<c:import url="/WEB-INF/views/header.jsp" />
        <hr>
        <div class="event">
            <div class="section_visual">
                <div class="group_visual">
                    <div class="container_visual">
                        <div class="imgBtn prev_e">
                            <div class="prev_inn">
                                <a href="#" class="btn_pre_e" title="이전"> <i class="spr_book_event spr_event_pre">이전</i> </a>
                            </div>
                        </div>
                        <div class="imgBtn nxt_e">
                            <div class="nxt_inn">
                                <a href="#" class="btn_nxt_e" title="다음"> <i class="spr_book_event spr_event_nxt">다음</i> </a>
                            </div>
                        </div>
                        <div>
                            <div class="container_visual">
                                <!-- [D] 이전,다음 버튼을 클릭할때마다 캐러셀 형태로 순환 됨 --->
                                <ul class="visual_img">
                                    <li class="banner_list item" style="background-image: url(http://naverbooking.phinf.naver.net/20170209_66/1486628146913la6nC_JPEG/image.jpg); width: 338px;">
                                        <a href="#"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
                                            <div class="event_txt">
                                                <h4 class="event_txt_tit"></h4>
                                                <p class="event_txt_adr"></p>
                                                <p class="event_txt_dsc"></p>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="banner_list item" style="background-image: url(http://naverbooking.phinf.naver.net/20170119_48/1484802596907hmVDm_JPEG/image.jpg); width: 338px;">
                                        <a href="#"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
                                            <div class="event_txt">
                                                <h4 class="event_txt_tit">뮤지컬-김종욱찾기 네이버 예약</h4>
                                                <p class="event_txt_adr">대학로 쁘띠첼씨어터</p>
                                                <p class="event_txt_dsc">네이버 예매시, 손크림/발크림(중 래덤)을 드립니다</p>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="banner_list item" style="background-image: url(http://naverbooking.phinf.naver.net/20170209_66/1486628146913la6nC_JPEG/image.jpg); width: 338px;">
                                        <a href="#"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
                                            <div class="event_txt">
                                                <h4 class="event_txt_tit"></h4>
                                                <p class="event_txt_adr"></p>
                                                <p class="event_txt_dsc"></p>
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <span class="nxt_fix"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="section_event_tab">
                <ul class="event_tab_lst tab_lst_min">
                	<li class="cate_list item" data-category="0">
                        <a class="anchor"> <span>전체</span> </a>
                    </li>
<c:forEach var="category" items="${category}">
					<li class="cate_list item" data-category="${category.id}">
                        <a class="anchor"> <span>${category.name}</span> </a>
                    </li>
</c:forEach>
                </ul>
            </div>
            <div class="section_event_lst">
                <p class="event_lst_txt">바로 예매 가능한 전시, 공연, 행사가 <span class="pink">${productCount}개</span> 있습니다</p>
                <div class="wrap_event_box">
                    <!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->
                    <ul class="lst_event_box">
                    	<script id="product_template" type="text/x-handlebars-template">
							{{#productList}}
								<li class="product_list item">
                    	        <a href="/mvDetail/{{productId}}" class="item_book">
                        	        <div class="item_preview"> 
                            	    	<img alt="{{fileName}}" class="img_thumb" src="{{saveFileName}}">
                                		<span class="img_border"></span> 
                              	  </div>
                     	           <div class="event_txt">
                        	            <h4 class="event_txt_tit"> 
                            	        	<span>{{productName}}</span>
                                	    	<small class="sm">{{placeName}}</small>
                                	    </h4>
                                	    <p class="event_txt_dsc">{{description}}</p>
                                	</div>
                            	</a>
                        	</li>
							{{/productList}}
						</script>
                    </ul>
                    <ul class="lst_event_box">
                    </ul>
                    <!-- 더보기 -->
                    <div class="more">
                        <button class="btn btnMore"><span>더보기</span></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:import url="/WEB-INF/views/footer.jsp" />
</body>
<script src="/resources/js/node_modules/jquery/dist/jquery.js"></script>
<!-- <script src="/resources/js/handlebars-v4.0.5.js"></script> -->
<script src="/resources/js/node_modules/handlebars/dist/handlebars.js"></script>
<script src="/resources/js/modules.js"></script>
<script src="/resources/js/main.js"></script>
</html>
