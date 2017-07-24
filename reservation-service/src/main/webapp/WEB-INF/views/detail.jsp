<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/detail.css" rel="stylesheet">
</head>

<body>
<input type="hidden" id="productId" value="${productId}">
<input type="hidden" id="sales_flag" value="${detailInfo.salesFlag}">
<input type="hidden" id="sales_end" value="${detailInfo.salesEnd}">
    <div id="container">
        <c:import url="/WEB-INF/views/header.jsp" />
        <div class="ct main">
            <div>
                <div class="section_visual">
                    <header>
                        <h1 class="logo">
                            <a href="http://www.naver.com/" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                            <a href="/" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
                        </h1>
                        <a href="/mvMyPage" class="btn_my"> <span title="내 예약">MY</span> </a>
                    </header>
                    <div class="pagination">
                        <div class="bg_pagination"></div>
                        <div class="figure_pagination">
                            <span class="num imgCurrent">1</span>
                            <span class="num off">/ <span class="imgNum">${fn:length(productImage)} </span></span>
                        </div>
                    </div>
                    <div class="group_visual">
                        <div>
                            <div class="container_visual" style="width: 414px;">
                                <ul class="visual_img product_banner_image">
<c:forEach var="image" items="${productImage}" varStatus="status">
                               			<li class="imgList item" style="width: 414px;"> 
                               			<img alt="${image.fileName}" class="img_thumb" src="${image.saveFileName}">
                               			 <span class="img_bg"></span>
                                   		 <div class="visual_txt">
	<c:if test="${status.index eq 0}">
                                       		<div class="visual_txt_inn">
                                           		<h2 class="visual_txt_tit">
                                           			<span>${image.productName}</span>
                                          		</h2>
                                           		<p class="visual_txt_dsc">${image.description}</p>
                                       		</div>
	</c:if>
                                   		</div>
                               		</li>
</c:forEach>
                                </ul>
                            </div>
                            <div class="imgBtn prev">
                                <div class="prev_inn">
                                    <a href="#" class="btn_prev" title="이전">
                                        <!-- [D] 첫 이미지 이면 off 클래스 추가 -->
                                        <i class="spr_book2 ico_arr6_lt off"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="imgBtn nxt">
                                <div class="nxt_inn">
                                    <a href="#" class="btn_nxt" title="다음">
                                        <i class="spr_book2 ico_arr6_rt"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="group_btn_goto">
                        <a class="btn_goto_home" title="홈페이지" href="${detailInfo.homepage}" target="siteUrl"> <i class="fn fn-home1"></i> </a>
                        <a class="btn_goto_tel" title="전화" href="tel:${detailInfo.tel}"> <i class="fn fn-call1"></i> </a>
						<a class="btn_goto_mail" title="이메일" href="mailto:${detailInfo.email}"> <i class="fn fn-mail1"></i> </a>
                        <a href="#" class="btn_goto_path" title="길찾기"> <i class="fn fn-path-find1"></i> </a>
                        <a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
                    </div>
                </div>
                <div class="section_store_details">
                    <!-- [D] 펼쳐보기 클릭 시 store_details에 close3 제거 -->
                    <div class="store_details close3">
                        <p class="dsc">
                            ${detailInfo.content}<br><br>
                            ${detailInfo.displayStart} - ${detailInfo.displayEnd}<br>
                            ${detailInfo.observationTime}<br><br>
                        </p>
                    </div>
                    <!-- [D] 토글 상황에 따라 bk_more에 display:none 추가 -->
                    <a href="#" class="bk_more _open"> 
                    	<span class="bk_more_txt">펼쳐보기</span> 
                    	<i class="fn fn-down2"></i> 
                    </a>
                    <a href="#" class="bk_more _close" style="display: none;"> 
                    	<span class="bk_more_txt">접기</span> 
                    	<i class="fn fn-up2"></i> 
                    </a>
                </div>
<c:if test="${detailInfo.event ne null}">
                <div class="section_event">
                    <div class="event_info_box">
                        <div class="event_info_tit">
                            <h4 class="in_tit"> <i class="spr_book ico_evt"></i> <span>이벤트 정보</span> </h4>
                        </div>
                        <div class="event_info">
                            <div class="in_dsc">${detailInfo.event}</div>
                        </div>
                    </div>
                </div>
</c:if>
                <div class="section_btn"> 
                	<a href="#" class="bk_btn_reserve">
		                <button type="button" class="bk_btn">
			                <i class="fn fn-nbooking-calender2"></i> 
			                <span>예매하기</span> 
		                </button> 
	                </a>
                </div>
                <div class="section_review_list">
                    <div class="review_box">
                        <h3 class="title_h3">예매자 한줄평</h3>
                        <div class="short_review_area">
                            <div class="grade_area">
                                <!-- [D] 별점 graph_value는 퍼센트 환산하여 width 값을 넣어줌 -->
                                <span class="graph_mask"> <em class="graph_value" style="width:${commentMap.starPoint}% ;"></em> </span>
                                <strong class="text_value"> <span class="comment_score">${commentMap.scoreAverage}</span> <em class="total">5.0</em> </strong>
                                <span class="join_count"><em class="green">${commentMap.commentCount}건</em> 등록</span>
                            </div>
                            <ul class="list_short_review">
<c:forEach var="comment" items="${commentMap.commentList}" begin="0" end="2">
                                <li class="list_item">
                                    <div>
	<c:choose>
		<c:when test="${comment.fileName ne null}">
                                        <div class="review_area">
                                            <div class="thumb_area" data-comment_id="${comment.rucId}">
                                                <a href="#" class="thumb" title="이미지 크게 보기">
                                                	<img width="90" height="90" class="img_vertical_top" src="${comment.saveFileName}" alt="${coment.fileName}">
                                                </a> 
                                                <span class="img_count">${comment.imgCount}</span>
                                            </div>
                                            <h4 class="resoc_name">${comment.reservationName}</h4>
                                            <p class="review">${comment.comment}</p>
                                        </div>
		</c:when>
		<c:when test="${comment.fileName eq null}">
										<div class="review_area no_img">
											<h4 class="resoc_name">${comment.reservationName}</h4>
                                            <p class="review">${comment.comment}</p>
                                        </div>
		</c:when>
	</c:choose>
                                        <div class="info_area">
                                            <div class="review_info"> 
	                                            <span class="grade">${comment.score}</span> 
	                                            <span class="name">${comment.nickname}****</span> 
	                                            <span class="date">${comment.reservationDate} 방문</span> 
	                                        </div>
                                        </div>
                                    </div>
                                </li> 
</c:forEach>  
                            </ul>
                        </div>
                        <p class="guide"> 
                        	<i class="spr_book2 ico_bell"></i> 
                        	<span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span> 
                        </p>
                    </div>
<c:if test="${commentMap.commentCount >= 3}">
                    <a class="btn_review_more" href="#"> 
                    	<span>예매자 한줄평 더보기</span> 
                    	<i class="fn fn-forward1"></i> 
                    </a>
</c:if>
                </div>
                <div class="section_info_tab">
                    <!-- [D] tab 선택 시 anchor에 active 추가 -->
                    <ul class="info_tab_lst">
                        <li class="item _detail teb_info">
                            <a href="#" class="anchor active"> 
                            	<span>상세정보</span> 
                            </a>
                        </li>
                        <li class="item _path teb_info">
                            <a href="#" class="anchor"> 
                            	<span>오시는길</span> 
                            </a>
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
                                            ${detailInfo.content}
                                        </p>
                                    </li>
<c:if test="${NoticeImage ne null}">
	<c:forEach var="notice" items="${NoticeImage}">
                                    <li class="detail_info_lst"> 
                                    	<strong class="in_tit">[공지사항]</strong>
                                        <ul class="in_img_group">
                                            <li class="in_img_lst"> 
                                            	<img alt="${notice.fileName}" class="img_thumb lazy" src="" data-original="${notice.saveFileName}"> 
                                            </li>
                                        </ul>
                                    </li>
	</c:forEach>
</c:if>
<c:if test="${InfoImage ne null}">
	<c:forEach var="info" items="${InfoImage}">
                                    <li class="detail_info_lst"> 
                                    	<strong class="in_tit">[공연정보]</strong>
                                        <ul class="in_img_group">
                                            <li class="in_img_lst"> 
                                            	<img alt="123${info.fileName}" class="img_thumb lazy" src="" data-original="${info.saveFileName}"> 
                                            </li>
                                        </ul>
                                    </li>
	</c:forEach>
</c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- [D] 오시는길 외 다른 탭 선택 시 detail_location에 hide 추가 -->
                    <div class="detail_location hide">
                        <div class="box_store_info no_topline">
                            <a href="#" class="store_location" title="지도웹으로 연결">
                                <img class="store_map img_thumb" alt="map" src="">
                                <span class="img_border"></span>
                                <span class="btn_map">
                                	<i class="spr_book2 ico_mapview"></i>
                                </span>
                            </a>
                            <h3 class="store_name">${detailInfo.productName}</h3>
                            <div class="store_info">
                                <div class="store_addr_wrap">
                                    <span class="fn fn-pin2"></span>
                                    <p class="store_addr store_addr_bold">${detailInfo.placeStreet}</p>
                                    <p class="store_addr">
                                        <span class="addr_old">지번</span>
                                        <span class="addr_old_detail">${detailInfo.placeLot}</span>
                                    </p>
                                    <p class="store_addr addr_detail">${detailInfo.placeName}</p>
                                </div>
                                <div class="lst_store_info_wrap">
                                    <ul class="lst_store_info">
                                        <li class="item"> 
	                                        <span class="item_lt"> 
	                                        	<i class="fn fn-call2"></i> 
	                                        	<span class="sr_only">전화번호</span> 
	                                        </span> 
	                                        <span class="item_rt"> 
	                                        	<a href="tel:02-548-0597" class="store_tel">${detailInfo.tel}</a>
	                                        </span> 
                                        </li>
                                    </ul>
                                </div>
                            </div>
							<!-- [D] 모바일 브라우저에서 접근 시 column2 추가와 btn_navigation 요소 추가 -->
                            <div class="bottom_common_path column2">
                                <a href="#" class="btn_path"> 
                                	<i class="fn fn-path-find2"></i> 
                                	<span>길찾기</span> 
                                </a>
								<a hewf="#" class="btn_navigation before"> 
									<i class="fn fn-navigation2"></i> 
									<span>내비게이션</span> 
								</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:import url="/WEB-INF/views/footer.jsp" />
    <div id="photoviwer" class="hidden">
    	<div class="group_visual group_frame">
    		<div>
    			<div id="btnClose" align="left">
    				<img src="/resources/img/close.png">
    			</div>
			</div>
			<div>
				<div class="container_visual image-popup-ab">
					<ul class="visual_img comment_popup_img">
						<script id="comment_image_template" type="text/x-handlebars-template">
							{{#commentImageList}} 
                				<li class="popImgList item"> 
                					<img alt="{{fileName}}" class="img_thumb" src="{{saveFileName}}">
                				 	<span class="img_bg"></span>
                    			 	<div class="visual_txt">
                    				</div>
                				</li>
							{{/commentImageList}}
						</script> 
					</ul>
				</div>
				<div class="popImgBtn prev">
					<div class="prev_inn">
						<a href="#" class="btn_prev" title="이전">
							<!-- [D] 첫 이미지 이면 off 클래스 추가 -->
							<i class="spr_book2 ico_arr6_lt off"></i>
						</a>
					</div>
				</div>
				<div class="popImgBtn nxt">
					<div class="nxt_inn">
						<a href="#" class="btn_nxt" title="다음">
							<i class="spr_book2 ico_arr6_rt"></i>
						</a>
					</div>
 				</div>
			</div>
		</div>
    </div>
</body>
<script src="/resources/js/node_modules/jquery/dist/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.lazyload/1.9.1/jquery.lazyload.js"></script>
<script src="/resources/js/node_modules/handlebars/dist/handlebars.js"></script>
<script src="/resources/js/modules.js"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=eGDuy2NMeDv1C1QCsPGF&submodules=geocoder"></script>
<script src="/resources/js/detail.js"></script>
</html>
