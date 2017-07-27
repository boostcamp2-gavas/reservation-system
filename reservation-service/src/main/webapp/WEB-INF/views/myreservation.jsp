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
    <style>
        .invisible {
            display: none
        }
    </style>
</head>

<body>
    <div id="container">
        <c:import url="/WEB-INF/views/header.jsp" />
        <hr>
        <div class="ct">
            <div class="section_my">
                <!-- 예약 현황 -->
                <c:set var="totalCnt" value="0"></c:set>
                <c:forEach var="cntStatus" items="${reservationStatus }">
                	<c:if test="${cntStatus.reservationType eq 'REQUESTING' }">
                		<c:set var="requestCnt" value="${cntStatus.cnt }"></c:set>
                	</c:if>
                	<c:if test="${cntStatus.reservationType eq 'DUE' }">
                		<c:set var="dueCnt" value="${cntStatus.cnt }"></c:set>
                	</c:if>
                	<c:if test="${cntStatus.reservationType eq 'USED' }">
                		<c:set var="usedCnt" value="${cntStatus.cnt }"></c:set>
                	</c:if>
                	<c:if test="${cntStatus.reservationType eq 'REFUND_CANCEL' }">
                		<c:set var="canceledCnt" value="${cntStatus.cnt }"></c:set>
                	</c:if>
                	<c:set var="tmp" value="${totalCnt }"></c:set>
                	<c:set var="totalCnt" value="${tmp + cntStatus.cnt }"></c:set>
                </c:forEach>
               <div class="my_summary">
                    <ul class="summary_board">
                   		<li class="item">
                           	<!--[D] 선택 후 .on 추가 link_summary_board -->
                           	<a class="link_summary_board on"> <i class="spr_book2 ico_book2"></i> <em class="tit">전체</em> <span class="figure" id="total_count"><c:out value="${totalCnt }" default="0"></c:out></span> </a>
                       	</li>
                       	<li class="item">
                            <a class="link_summary_board"> <i class="spr_book2 ico_book_ss"></i> <em class="tit">이용예정</em> <span class="figure" id="due_count"><c:out value="${requestCnt + dueCnt }" default="0"></c:out></span> </a>
                        </li>
                        <li class="item">
                        	<a class="link_summary_board"> <i class="spr_book2 ico_check"></i> <em class="tit">이용완료</em> <span class="figure" id="used_count"><c:out value="${usedCnt }" default="0"></c:out></span> </a>
                    	</li>
                    	<li class="item">
                            <a class="link_summary_board"> <i class="spr_book2 ico_back"></i> <em class="tit">취소·환불</em> <span class="figure" id="canceled_count"><c:out value="${canceledCnt}" default="0"></c:out></span> </a>
                        </li>
                    </ul>
                </div>
                <!--// 예약 현황 -->

                <!-- 내 예약 리스트 -->
                <div class="wrap_mylist">
                    <ul class="list_cards" ng-if="bookedLists.length > 0">
                        <!--[D] 예약확정: .confirmed, 취소된 예약&이용완료: .used 추가 card -->
                        <c:forEach var="ritem" items="${reservation }" varStatus="status">
                        <c:if test="${status.index == 0 or (status.index > 0 and reservation[status.index-1].reservationType ne ritem.reservationType )}">
	                        <c:if test="${ritem.reservationType == 'REQUESTING' }">
	                        <li class="card" id="requesting">
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
							<c:if test="${ritem.reservationType == 'DUE' }">
							<li class="card confirmed" id="card_confirmed">
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
							<c:if test="${ritem.reservationType == 'USED' }">
							<li class="card used" id="card_used">
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
							<c:if test="${ritem.reservationType == 'REFUND_CANCEL' }">
							<li class="card used" id="card_canceled">
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
                        	</c:if>
                        	<article class="card_item">
	                            <a class="link_booking_details">
	                                <div class="card_body">
	                                    <div class="left"></div>
	                                    <div class="middle">
	                                        <div class="card_detail">
	                                            <em class="booking_number" data-reservation-id="${ritem.id }">No.<fmt:formatNumber value="${ritem.id }" pattern="00000000"></fmt:formatNumber></em>
	                                            <h4 class="tit">${ritem.productName }</h4>
	                                            <ul class="detail">
	                                                <li class="item">
	                                                    <span class="item_tit">일정</span>
	                                                    <em class="item_dsc" id="item_schedule">
	                                                    	<fmt:formatDate value="${ritem.displayStart }" pattern="yyyy.MM.dd.(E)"/> ~ <fmt:formatDate value="${ritem.displayEnd}" pattern="yyyy.MM.dd.(E)"/>
														</em>
	                                                </li>
													<!-- 	                                                
													<li class="item">
	                                                    <span class="item_tit">내역</span>
	                                                    <em class="item_dsc">
															내역이 없습니다.
														</em>
	                                                </li> 
	                                                -->
	                                                <li class="item">
	                                                    <span class="item_tit">상품</span>
	                                                    <em class="item_dsc">
															<c:if test="${ritem.generalTicketCount != 0}">성인(${ritem.generalTicketCount})</c:if>
															<c:if test="${ritem.youthTicketCount != 0}">청소년(${ritem.youthTicketCount})</c:if>
															<c:if test="${ritem.childTicketCount != 0}">어린이(${ritem.childTicketCount})</c:if>
														</em>
	                                                </li>
	                                                <li class="item">
	                                                    <span class="item_tit">업체</span>
	                                                    <em class="item_dsc">
															${ritem.placeName }
														</em>
	                                                </li>
	                                            </ul>
	                                            <div class="price_summary">
	                                                <span class="price_tit">결제 예정금액</span>
	                                                <em class="price_amount">
	                                                	<span><fmt:formatNumber value="${ritem.totalPrice }" type="number"></fmt:formatNumber></span>
														<span class="unit">원</span>
													</em>
	                                            </div>
	                                            <!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
	                                            <c:if test="${ritem.reservationType == 'REQUESTING' or ritem.reservationType == 'DUE' }">
	                                            <div class="booking_cancel">
	                                                <button class="btn"><span>취소</span></button>
	                                            </div>
	                                            </c:if>
	                                            <c:if test="${ritem.reservationType == 'USED' }">
	                                            <div class="booking_cancel">
	                                                <button class="btn"><span>예매자 리뷰 남기기</span></button>
	                                            </div>
	                                            </c:if>
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
								<a class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기" href=""></a>
							</article>
							<c:if test="${status.index == reservation.size()-1 or (status.index < reservation.size()-1 and reservation[status.index+1].reservationType ne ritem.reservationType )}">
                        	</li>
                        	</c:if>
                        </c:forEach>
                    </ul>
                </div>  
                <!--// 내 예약 리스트 -->

                <!-- 예약 리스트 없음 -->
                
                <div class="err <c:if test="${reservation.size() != 0 }">invisible</c:if>"> <i class="spr_book ico_info_nolist"></i>
                    <h1 class="tit">예약 리스트가 없습니다</h1>
                </div>
                
                <!--// 예약 리스트 없음 -->
            </div>
        </div>
        <hr>
    </div>
    <c:import url="/WEB-INF/views/footer.jsp" />

    <!-- 취소 팝업 -->
    <!-- [D] 활성화 display:block, 아니오 버튼 or 닫기 버튼 클릭 시 숨김 display:none; -->
    <div class="popup_booking_wrapper" style="display:none;">
        <div class="dimm_dark" style="display:block"></div>
        <div class="popup_booking refund">
            <h1 class="pop_tit">
                <span>test</span>
                <small class="sm">2000.0.00.(월)2000.0.00.(일)</small>
            </h1>
            <div class="nomember_alert">
                <p>취소하시겠습니까?</p>
            </div>
            <div class="pop_bottom_btnarea">
                <div class="btn_gray">
                    <a class="btn_bottom"><span>아니오</span></a>
                </div>
                <div class="btn_green">
                    <a class="btn_bottom"><span>예</span></a>
                </div>
            </div>
            <!-- 닫기 -->
            <a class="popup_btn_close" title="close">
                <i class="spr_book2 ico_cls"></i>
            </a>
            <!--// 닫기 -->
        </div>
    </div>
    <!--// 취소 팝업 -->
	<script src="/resources/js/node_modules/jquery/dist/jquery.js"></script>
	<script src="/resources/js/node_modules/handlebars/dist/handlebars.js"></script>
	<script src="/resources/js/node_modules/@egjs/component/dist/component.js"></script>
	<script src="/resources/js/callAjax.js"></script>
	<script src="/resources/js/messenger.js"></script>
	<script src="/resources/js/confirmPopup.js"></script>
	<script src="/resources/js/summary.js"></script>
	<script src="/resources/js/reservationList.js"></script>
	<script>
	$(function() {
		var messenger = new Messenger();
		var popup = new ConfirmPopup($('.popup_booking_wrapper'), messenger);
		var requesting = new ReservationList($('#requesting'), messenger);
		var confirmed = new ReservationList($('#card_confirmed'), messenger);
		var used = new ReservationList($('#card_used'), messenger);
		var canceled = new ReservationList($('#card_canceled'), messenger);
		var opt = {
				obj0 : requesting,
				obj1 : confirmed,
				obj2 : used,
				obj3 : canceled,
				messenger : messenger
		}
		var summary = new Summary($('.my_summary'), opt);
	});
	</script>
</body>

</html>