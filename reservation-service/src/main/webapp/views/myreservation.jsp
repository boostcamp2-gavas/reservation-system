<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                            <a href="#" class="link_summary_board on"> <i class="spr_book2 ico_book2"></i> <em class="tit">전체</em> <span class="figure">${fn:length(allData)}</span> </a>
                        </li>
                        <li class="item">
                            <a href="#" class="link_summary_board"> <i class="spr_book2 ico_book_ss"></i> <em class="tit">이용예정</em> <span class="figure">${length.expectation + length.decision}</span> </a>
                        </li>
                        <li class="item">
                            <a href="#" class="link_summary_board"> <i class="spr_book2 ico_check"></i> <em class="tit">이용완료</em> <span class="figure">${length.end}</span> </a>
                        </li>
                        <li class="item">
                            <a href="#" class="link_summary_board"> <i class="spr_book2 ico_back"></i> <em class="tit">취소·환불</em> <span class="figure"> ${length.cancellation}</span> </a>
                        </li>
                    </ul>
                </div>
                <!--// 예약 현황 -->

                <!-- 내 예약 리스트 -->
                
                <div class="wrap_mylist">
                	<c:choose>
                
					    <c:when test="${allData eq null }">
				          	<!-- 예약 리스트 없음 -->
			                <div class="err"> <i class="spr_book ico_info_nolist"></i>
			                    <h1 class="tit">예약 리스트가 없습니다</h1>
			                </div>
			                <!--// 예약 리스트 없음 -->
					    </c:when>
					    <c:otherwise>
					    <ul class="list_cards">
	                        <!--[D] 예약확정: .confirmed, 취소된 예약&이용완료: .used 추가 card -->
	                        
					        <li class="card">
					        	<c:if test="${length.expectation !=0 }">
									<div class=link_booking_details>
			                            <div class="card_header">
			                                <div class="left"></div>
			                                <div class="middle">
			                                    <!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book2 -->
			                                    <i class="spr_book2 ico_clock"></i>
			                                    <span class="tit">예약 신청중</span>
			                                </div>
			                                <div class="right"></div>
			                            </div>
									</div>
								</c:if>
								<c:forEach items="${allData}" var="list" varStatus="status">
									<c:if test="${list.reservationType ==0 }">
										<article class="card_item">
				                            <a href="#" class="link_booking_details">
				                                <div class="card_body">
				                                    <div class="left"></div>
				                                    <div class="middle">
				                                        <div class="card_detail" data-id = "${list.id}" data-type ="${list.reservationType}">
				                                            <em class="booking_number">No.${list.id}</em>
				                                            <h4 class="tit">${list.name}</h4>
				                                            <ul class="detail">
				                                                <li class="item">
				                                                    <span class="item_tit">일정</span>
				                                                    <em class="item_dsc">
				                                                    <fmt:formatDate value="${list.displayStart }" pattern="yy.MM.dd (E)" type="date"/>
							                                         ~           
				                                                    <fmt:formatDate value="${list.displayEnd }" pattern="yy.MM.dd (E)" type="date"/>
																	</em>
				                                                </li>
				                                                <li class="item">
				                                                    <span class="item_tit">내역</span>
				                                                    <em class="item_dsc">
				                                                    <c:if test="${list.generalTicketCount !=0 }">
				                                                   		 일반 : (${ list.generalTicketCount}) 
				                                                    </c:if>
				                                                    <c:if test="${list.youthTicketCount !=0 }">
				                                                   		청소년:  (${ list.youthTicketCount})  
				                                                    </c:if>
				                                                     <c:if test="${list.childTicketCount !=0 }">
				                                                   		어린이:  (${ list.childTicketCount})
				                                                    </c:if>
				                                                   		- 합계 : (${ list.generalTicketCount +list.youthTicketCount+list.childTicketCount })
																	</em>
				                                                </li>
				                                                <li class="item">
				                                                    <span class="item_tit">업체</span>
				                                                    <em class="item_dsc">
																		${list.name}
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
				                                            <div class="booking_cancel">
				                                                <button class="btn"><span>취소</span></button>
				                                            </div>
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
									</c:if>
								</c:forEach>
	                        </li>
	                        <li class="card confirmed">
	                       	 <c:if test="${length.decision !=0 }">
	                            <div class="link_booking_details">
	                                <div class="card_header">
	                                    <div class="left"></div>
	                                    <div class="middle">
	                                        <!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
	                                        <i class="spr_book2 ico_check2"></i>
	                                        <span class="tit">예약 확정</span>
	                                    </div>
	                                    <div class="right"></div>
	                                </div>
								</div>
								</c:if>
								<c:forEach items="${allData}" var="list" varStatus="status">
									<c:if test="${list.reservationType ==1 }">
										<article class="card_item">
				                            <a href="#" class="link_booking_details">
				                                <div class="card_body">
				                                    <div class="left"></div>
				                                    <div class="middle">
				                                        <div class="card_detail" data-id = "${list.id}" data-type ="${list.reservationType}">
				                                            <em class="booking_number">No.${list.id}</em>
				                                            <h4 class="tit">${list.name}</h4>
				                                            <ul class="detail">
				                                                <li class="item">
				                                                    <span class="item_tit">일정</span>
				                                                    <em class="item_dsc">
																	 <fmt:formatDate value="${list.displayStart }" pattern="yy.MM.dd (E)" type="date"/>
							                                         ~           
				                                                    <fmt:formatDate value="${list.displayEnd }" pattern="yy.MM.dd (E)" type="date"/>
				                                                    
																	</em>
				                                                </li>
				                                                <li class="item">
				                                                    <span class="item_tit">내역</span>
				                                                    <em class="item_dsc">
				                                                    <c:if test="${list.generalTicketCount !=0 }">
				                                                   		 일반 : (${ list.generalTicketCount}) 
				                                                    </c:if>
				                                                    <c:if test="${list.youthTicketCount !=0 }">
				                                                   		청소년:  (${ list.youthTicketCount})  
				                                                    </c:if>
				                                                     <c:if test="${list.childTicketCount !=0 }">
				                                                   		어린이:  (${ list.childTicketCount})
				                                                    </c:if>
				                                                   		- 합계 : (${ list.generalTicketCount +list.youthTicketCount+list.childTicketCount })
																	</em>
				                                                </li>
				                                               
				                                                <li class="item">
				                                                    <span class="item_tit">업체</span>
				                                                    <em class="item_dsc">
																		업체명이 없습니다.
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
				                                            <div class="booking_cancel">
				                                                <button class="btn"><span>취소</span></button>
				                                            </div>
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
									</c:if>
								</c:forEach>
								
	                        </li>
	                        <li class="card used">
	                         	<c:if test="${length.end !=0 }">
		                            <div class="link_booking_details">
		                                <div class="card_header">
		                                    <div class="left"></div>
		                                    <div class="middle">
		                                        <!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
		                                        <i class="spr_book2 ico_check2"></i>
		                                        <span class="tit">이용 완료</span>
		                                    </div>
		                                    <div class="right"></div>
		                                </div>
									</div>
								</c:if>
								<c:forEach items="${allData}" var="list" varStatus="status">
									<c:if test="${list.reservationType == 2 }">
										<article class="card_item">
											<a href="#" class="link_booking_details">
				                                <div class="card_body">
				                                    <div class="left"></div>
				                                    <div class="middle">
				                                        <div class="card_detail" data-id = "${list.id}" data-type ="${list.reservationType}">
				                                            <em class="booking_number">No.0000000</em>
				                                            <h4 class="tit">서비스명/상품명</h4>
				                                            <ul class="detail">
				                                                <li class="item">
				                                                    <span class="item_tit">일정</span>
				                                                    <em class="item_dsc">
																  	<fmt:formatDate value="${list.displayStart }" pattern="yy.MM.dd (E)" type="date"/>
							                                         ~           
				                                                    <fmt:formatDate value="${list.displayEnd }" pattern="yy.MM.dd (E)" type="date"/>
				                                                    
																	</em>
				                                                </li>
				                                                <li class="item">
				                                                    <span class="item_tit">내역</span>
				                                                     <em class="item_dsc">
				                                                    <c:if test="${list.generalTicketCount !=0 }">
				                                                   		 일반 : (${ list.generalTicketCount}) 
				                                                    </c:if>
				                                                    <c:if test="${list.youthTicketCount !=0 }">
				                                                   		청소년:  (${ list.youthTicketCount})  
				                                                    </c:if>
				                                                     <c:if test="${list.childTicketCount !=0 }">
				                                                   		어린이:  (${ list.childTicketCount})
				                                                    </c:if>
				                                                   		- 합계 : (${ list.generalTicketCount +list.youthTicketCount+list.childTicketCount })
																	</em>
				                                                </li>
				                                              
				                                                <li class="item">
				                                                    <span class="item_tit">업체</span>
				                                                    <em class="item_dsc">
																		업체명이 없습니다.
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
				                                            <div class="booking_cancel">
				                                                <button class="btn"><span>예매자 리뷰 남기기</span></button>
				                                            </div>
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
										</article>
									</c:if>
								</c:forEach>
	                        </li>
	                        <li class="card cancel">
	                        	<c:if test="${length.cancellation != 0 }">
		                            <div class="link_booking_details">
		                                <div class="card_header">
		                                    <div class="left"></div>
		                                    <div class="middle">
		                                        <!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
		                                        <i class="spr_book2 ico_cancel"></i>
		                                        <span class="tit">취소된 예약</span>
		                                    </div>
		                                    <div class="right"></div>
		                                </div>
									</div>
								</c:if>
								<c:forEach items="${allData}" var="list" varStatus="status">
									<c:if test="${list.reservationType == 3 }">
									<article class="card_item">
										<a href="#" class="link_booking_details">
			                                <div class="card_body">
			                                    <div class="left"></div>
			                                    <div class="middle">
			                                        <div class="card_detail" data-id = "${list.id}" data-type ="${list.reservationType}">
			                                            <em class="booking_number">No.0000000</em>
			                                            <h4 class="tit">서비스명/상품명</h4>
			                                            <ul class="detail">
			                                                <li class="item">
			                                                    <span class="item_tit">일정</span>
			                                                    <em class="item_dsc">
																   <fmt:formatDate value="${list.displayStart }" pattern="yy.MM.dd (E)" type="date"/>
							                                         ~           
				                                                    <fmt:formatDate value="${list.displayEnd }" pattern="yy.MM.dd (E)" type="date"/>
				                                                    
																</em>
			                                                </li>
			                                                <li class="item">
			                                                    <span class="item_tit">내역</span>
			                                                    <em class="item_dsc">
				                                                    <c:if test="${list.generalTicketCount !=0 }">
				                                                   		 일반 : (${ list.generalTicketCount}) 
				                                                    </c:if>
				                                                    <c:if test="${list.youthTicketCount !=0 }">
				                                                   		청소년:  (${ list.youthTicketCount})  
				                                                    </c:if>
				                                                     <c:if test="${list.childTicketCount !=0 }">
				                                                   		어린이:  (${ list.childTicketCount})
				                                                    </c:if>
				                                                   		- 합계 : (${ list.generalTicketCount +list.youthTicketCount+list.childTicketCount })
																	</em>
			                                                </li>
			                                                <li class="item">
			                                                    <span class="item_tit">업체</span>
			                                                    <em class="item_dsc">
																	업체명이 없습니다.
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
									</article>
									</c:if>
								</c:forEach>
	                        </li>
	                        </ul>
						    </c:otherwise>
						</c:choose>
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
<script src="/resources/js/node_modules/jquery/dist/jquery.js"></script>
<script>

	var $article;
	var $cardDetail ;
	$(".btn").on("click",function(event){
		event.preventDefault();
		$cardDetail = $(this).parents(".card_detail"),
		$popoup = $(".popup_booking_wrapper");
		var productId = $cardDetail.data("id"),
		type = $cardDetail.data("type");
		if(type ===0){
			$article = $cardDetail.parents("article");
			$popoup.removeClass("none");
			// id값을 인자로 넘길 방법이 없어, id로 설정하고 가져오는 방식
			$popoup.data("id",productId);
		}else if(type ===2){
			alert("리뷰남기기로 이동");
		}
	
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
		var html;
		if(data){
			$(".cancel").append(outerHtml($article));
			$cardDetail.remove();
			$(".popup_booking_wrapper").addClass("none");
			// 잠깐 쉬고 할일 :: 
			// count 도 감소 시켜야된다.
			// 취소가 일어났을떄,  예약 신청이 0이면 menubar 또한 사라지도록 진행해야 된다. 
			
			alert("취소 되었습니다.");
		}else{
			alert("예상치 못한 에러가 ...");
		}
	}
	
	// 취소 버튼과 x 버튼 누르면 안보이게 진행
	$(".btn_gray, .popup_btn_close").on("click",function(event){
		event.preventDefault();
		$(".popup_booking_wrapper").addClass("none");
		console.log(html);
	})
	
	
</script>

</body>

</html>