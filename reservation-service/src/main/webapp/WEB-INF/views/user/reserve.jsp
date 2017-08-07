<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value = "${productReservation.displayStart }" var="start" />
<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value = "${productReservation.displayEnd }" var="end" />
<!DOCTYPE html>
<html lang="ko">

<jsp:include page="inc/common/head.jsp">
			<jsp:param value="네이버 예약" name="title"/>
</jsp:include>

<body>
    <div id="container">
        <!-- [D] 예약하기로 들어오면 header에 fade 클래스 추가로 숨김 -->
        <jsp:include page="inc/common/header.jsp">
        		<jsp:param value="fade" name="visual"/>
        </jsp:include>
        <div class="ct">
            <div class="ct_wrap">
                <div class="top_title">
                    <a href="#" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i> </a>
                    <h2><span class="title">${productReservation.name }</span></h2>
                </div>
                <div class="group_visual">
                    <div class="container_visual" style="width: 414px;">
                        <ul class="visual_img">
                            <li class="item" style="width: 414px;"> <img alt="" class="img_thumb" src="https://ssl.phinf.net/naverbooking/20170217_264/1487312141947lTddT_JPEG/%B3%D7%C0%CC%B9%F6.jpg?type=ff1242_816"> <span class="img_bg"></span>
                                <div class="preview_txt">
                                    <h2 class="preview_txt_tit">${productReservation.name }</h2>
                                    <em class="preview_txt_dsc">
	                                    <fmt:formatDate pattern = "yyyy.M.d.(E)" value = "${start }" />
	                                    ~
                                    	   <fmt:formatDate pattern = "yyyy.M.d.(E)" value = "${end }" />
                                    </em>
                               </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="section_store_details">
                    <div class="store_details">
                        <h3 class="in_tit">${productReservation.name }</h3>
                        <p class="dsc">
                            장소 : ${productReservation.placeName }<br> 기간 : <fmt:formatDate pattern = "yyyy.M.d.(E)" value = "${start }" /> ~ <fmt:formatDate pattern = "yyyy.M.d.(E)" value = "${end }" />
                        </p>
                        <h3 class="in_tit">관람시간</h3>
                        <p class="dsc">
                            ${productReservation.observationTime }
                        </p>
                        <h3 class="in_tit">요금</h3>
                        <p class="dsc">
                        		<c:if test="${ !empty productReservation.productPriceList }">
                        			<c:forEach var="item" items= "${productReservation.productPriceList}" varStatus="status">
                        				<c:choose>
                        					<c:when test="${item.priceType==1 }">
                        					성인(만 19~64세) <fmt:formatNumber value="${item.price }" pattern="#,###" />원
                        						<fmt:formatNumber value="${item.price * (1-item.discountRate )}" pattern="#,###" var="adultPrice"/>
                        						<c:if test="${item.discountRate ne 0 }">
                        							-> ${adultPrice }원
                        						</c:if>
                        					</c:when>
                        					<c:when test="${item.priceType==2 }">
                        					<br> 청소년(만 13~18세) <fmt:formatNumber value="${item.price }" pattern="#,###" />원
                        						<fmt:formatNumber value="${item.price * (1-item.discountRate )}" pattern="#,###" var="youthPrice"/>
                        						<c:if test="${item.discountRate ne 0 }">
                        							-> ${youthPrice }원
                       						</c:if>
                        					</c:when>
                        					<c:when test="${item.priceType==3 }">
                        					<br> 어린이(만 4~12세) <fmt:formatNumber value="${item.price }" pattern="#,###" />원
                        						<fmt:formatNumber value="${item.price * (1-item.discountRate )}" pattern="#,###" var="childPrice"/>
                        						<c:if test="${item.discountRate ne 0 }">
                        							-> ${childPrice }원
                      						</c:if>
                        					</c:when>
                        				</c:choose>
                        			</c:forEach>
                        		</c:if>
                        </p>
                    </div>
                </div>
                <form class="form_horizontal" method="post" action="${url}" style="padding: 0" id="form">
                  <div class="section_booking_ticket _priceTypeWrap">
                      <div class="ticket_body">
                      	   <c:if test="${ !empty productReservation.productPriceList }">
                      			<c:forEach var="item" items= "${productReservation.productPriceList}" varStatus="status">
                      				<c:choose>
                      					<c:when test="${item.priceType==1 }">
                      						<div class="qty _priceTypeArea" data-price-type=${item.priceType } data-price="${item.price * (1-item.discountRate )}">
  				                            <div class="count_control">
  				                                <!-- [D] 수량이 최소 값이 일때 ico_minus3, count_control_input에 disabled 각각 추가, 수량이 최대 값일 때는 ico_plus3에 disabled 추가 -->
  				                                <div class="clearfix">
  				                                    <a href="#" class="btn_plus_minus spr_book2 ico_minus3 disabled _minus" title="빼기"> </a>
  				                                    <input type="tel" class="count_control_input disabled _amount" value="0" readonly title="수량" name="generalTicketCount">
  				                                    <a href="#" class="btn_plus_minus spr_book2 ico_plus3 _plus" title="더하기">
  				                                    </a>
  				                                </div>
  				                                <!-- [D] 금액이 0 이상이면 individual_price에 on_color 추가 -->
  				                                <div class="individual_price _priceSection"><span class="total_price _price">0</span><span class="price_type">원</span></div>
  				                            </div>
  				                            <div class="qty_info_icon">
  				                            		<strong class="product_amount"> <span>성인</span> </strong>
  				                            		<strong class="product_price">
  				                            			<span class="price">${adultPrice }</span> <span class="price_type">원</span>
  				                            		</strong>
  				                            		<em class="product_dsc">${adultPrice }원
  				                            			<c:if test="${item.discountRate ne 0 }">
  				                            				(${item.discountRate*100 }% 할인가)
  			                            				</c:if>
  			                            			</em>
  			                            		</div>
  				                        </div>
                      					</c:when>
                      					<c:when test="${item.priceType==2 }">
                      						<div class="qty _priceTypeArea" data-price-type=${item.priceType } data-price="${item.price * (1-item.discountRate )}">
  				                            <div class="count_control">
  				                                <!-- [D] 수량이 최소 값이 일때 ico_minus3, count_control_input에 disabled 각각 추가, 수량이 최대 값일 때는 ico_plus3에 disabled 추가 -->
  				                                <div class="clearfix">
  				                                    <a href="#" class="btn_plus_minus spr_book2 ico_minus3 disabled _minus" title="빼기"> </a>
  				                                    <input type="tel" class="count_control_input disabled _amount" value="0" readonly title="수량" name="youthTicketCount">
  				                                    <a href="#" class="btn_plus_minus spr_book2 ico_plus3 _plus" title="더하기">
  				                                    </a>
  				                                </div>
  				                                <!-- [D] 금액이 0 이상이면 individual_price에 on_color 추가 -->
  				                                <div class="individual_price _priceSection"><span class="total_price _price">0</span><span class="price_type">원</span></div>
  				                            </div>
  				                            <div class="qty_info_icon">
  				                            		<strong class="product_amount"> <span>청소년</span> </strong>
  				                            		<strong class="product_price">
  				                            			<span class="price">${youthPrice }</span> <span class="price_type">원</span>
  				                            		</strong>
  				                            		<em class="product_dsc">${youthPrice }원
  				                            			<c:if test="${item.discountRate ne 0 }">
  				                            				(${item.discountRate*100 }% 할인가)
  			                            				</c:if>
  			                            			</em>
  			                            		</div>
  				                        </div>
                      					</c:when>
                      					<c:when test="${item.priceType==3 }">
                      						<div class="qty _priceTypeArea" data-price-type=${item.priceType } data-price="${item.price * (1-item.discountRate )}">
  				                            <div class="count_control">
  				                                <!-- [D] 수량이 최소 값이 일때 ico_minus3, count_control_input에 disabled 각각 추가, 수량이 최대 값일 때는 ico_plus3에 disabled 추가 -->
  				                                <div class="clearfix">
  				                                    <a href="#" class="btn_plus_minus spr_book2 ico_minus3 disabled _minus" title="빼기"> </a>
  				                                    <input type="tel" class="count_control_input disabled _amount" value="0" readonly title="수량" name="childTicketCount">
  				                                    <a href="#" class="btn_plus_minus spr_book2 ico_plus3 _plus" title="더하기">
  				                                    </a>
  				                                </div>
  				                                <!-- [D] 금액이 0 이상이면 individual_price에 on_color 추가 -->
  				                                <div class="individual_price _priceSection"><span class="total_price _price">0</span><span class="price_type">원</span></div>
  				                            </div>
  				                            <div class="qty_info_icon">
  				                            		<strong class="product_amount"> <span>어린이</span> </strong>
  				                            		<strong class="product_price">
  				                            			<span class="price">${childPrice }</span> <span class="price_type">원</span>
  				                            		</strong>
  				                            		<em class="product_dsc">${childPrice }원
  				                            			<c:if test="${item.discountRate ne 0 }">
  				                            				(${item.discountRate*100 }% 할인가)
  			                            				</c:if>
  			                            			</em>
  			                            		</div>
  				                        </div>
                      					</c:when>
                      				</c:choose>
                      			</c:forEach>
                   	   </c:if>
                         <!--  <div class="qty">
                              <div class="count_control">
                                  [D] 수량이 최소 값이 일때 ico_minus3, count_control_input에 disabled 각각 추가, 수량이 최대 값일 때는 ico_plus3에 disabled 추가
                                  <div class="clearfix">
                                      <a href="#" class="btn_plus_minus spr_book2 ico_minus3 disabled" title="빼기"> </a> <input type="tel" class="count_control_input disabled" value="0" readonly title="수량">
                                      <a href="#" class="btn_plus_minus spr_book2 ico_plus3" title="더하기">
                                      </a>
                                  </div>
                                  [D] 금액이 0 이상이면 individual_price에 on_color 추가
                                  <div class="individual_price"><span class="total_price">123,000</span><span class="price_type">원</span></div>
                              </div>
                              <div class="qty_info_icon"> <strong class="product_amount"> <span>성인</span> </strong> <strong class="product_price"> <span class="price">10,200</span> <span class="price_type">원</span> </strong> <em class="product_dsc">10,200원 (15% 할인가)</em> </div>
                          </div>
                          <div class="qty">
                              <div class="count_control">
                                  <div class="clearfix">
                                      <a href="#" class="btn_plus_minus spr_book2 ico_minus3" title="빼기"> </a> <input type="tel" class="count_control_input" value="10" readonly title="수량">
                                      <a href="#" class="btn_plus_minus spr_book2 ico_plus3" title="더하기">
                                      </a>
                                  </div>
                                  <div class="individual_price on_color"><span class="total_price">123,000</span><span class="price_type">원</span></div>
                              </div>
                              <div class="qty_info_icon"> <strong class="product_amount"> <span>유아</span> </strong> <strong class="product_price"> <span class="price">6,800</span> <span class="price_type">원</span> </strong> <em class="product_dsc">6,800원 (15% 할인가)</em> </div>
                          </div>
                          <div class="qty">
                              <div class="count_control">
                                  <div class="clearfix">
                                      <a href="#" class="btn_plus_minus spr_book2 ico_minus3" title="빼기"> </a> <input type="tel" class="count_control_input" value="3" readonly title="수량">
                                      <a href="#" class="btn_plus_minus spr_book2 ico_plus3" title="더하기">
                                      </a>
                                  </div>
                                  <div class="individual_price on_color"><span class="total_price">123,000</span><span class="price_type">원</span></div>
                              </div>
                              <div class="qty_info_icon"> <strong class="product_amount"> <span>세트1</span> </strong> <strong class="product_price"> <span class="price">20,000</span> <span class="price_type">원</span> </strong> <em class="product_dsc">2인 관람권 (17% 할인가)</em> </div>
                          </div>
                          <div class="qty">
                              <div class="count_control">
                                  <div class="clearfix">
                                      <a href="#" class="btn_plus_minus spr_book2 ico_minus3" title="빼기"> </a> <input type="tel" class="count_control_input" value="3" readonly title="수량">
                                      <a href="#" class="btn_plus_minus spr_book2 ico_plus3" title="더하기">
                                      </a>
                                  </div>
                                  <div class="individual_price on_color"><span class="total_price">123,000</span><span class="price_type">원</span></div>
                              </div>
                              <div class="qty_info_icon"> <strong class="product_amount"> <span>청소년</span> </strong> <strong class="product_price"> <span class="price">8,500</span> <span class="price_type">원</span> </strong> <em class="product_dsc">8,500원 (15% 할인가)</em> </div>
                          </div> -->
                      </div>
                  </div>
                  <div class="section_booking_form">
                      <div class="booking_form_wrap">
                          <div class="form_wrap">
                              <h3 class="out_tit">예매자 정보</h3>
                              <div class="agreement_nessasary help_txt"> <span class="spr_book ico_nessasary"></span> <span>필수입력</span> </div>
                                  <div style="padding: 15px 20px 0">
                                    <div class="inline_form"> <label class="label" for="name"> <span class="spr_book ico_nessasary">필수</span> <span>예매자</span> </label>
                                        <div class="inline_control"> <input type="text" id="name" class="text _required" value="${loginInfo.userName }" maxlength="17" name="reservationName"> </div>
                                    </div>
                                    <div class="inline_form"> <label class="label" for="tel"> <span class="spr_book ico_nessasary">필수</span> <span>연락처</span> </label>
                                        <div class="inline_control"> <input type="tel" id="tel" class="tel _required" value="${loginInfo.tel }" placeholder="휴대폰 입력 시 예매내역 문자발송" name="reservationTel"> </div>
                                    </div>
                                    <div class="inline_form"> <label class="label" for="email">  <span>이메일</span> </label>
                                        <div class="inline_control"> <input type="email" id="email" class="email" value="${loginInfo.email }" maxlength="50" name="reservationEmail"> </div>
                                    </div>
                                    <div class="inline_form last"> <label class="label" for="message">예매내용</label>
                                        <div class="inline_control">
                                            <p class="inline_txt selected">
                                              <fmt:formatDate pattern = "yyyy.M.d.(E)" value = "${start }" /> ~ <fmt:formatDate pattern = "yyyy.M.d.(E)" value = "${end }" />,
                                              총 <em class="_sum">0</em>매
                                              <span class="_countTemplateTarget"></span>
                                            </p>
                                        </div>
                                    </div>
                                  </div>
                          </div>
                      </div>
                      <div class="section_booking_agreement">
                          <div class="agreement all"> <input type="checkbox" id="chk3" class="chk_agree _agree"> <label for="chk3" class="label chk_txt_label"> <span>이용자 약관 전체동의</span> </label>
                              <div class="agreement_nessasary">
                                  <span>필수동의</span> </div>
                          </div>
                          <!-- [D] 약관 보기 클릭 시 agreement에 open 클래스 추가 -->
                          <div class="agreement"> <span class="chk_txt_span"> <i class="spr_book ico_arr_ipc2"></i> <span>개인정보 수집 및 이용 동의</span> </span>
                              <a href="#" class="btn_agreement"> <span class="btn_text">보기</span> <i class="fn fn-down2 _arw"></i> </a>
                              <div class="useragreement_details">&lt;개인정보 수집 및 이용 동의&gt;<br><br> 1. 수집항목 : [필수] 이름, 연락처, [선택] 이메일주소<br><br> 2. 수집 및 이용목적 : 사업자회원과 예약이용자의 원활한 거래 진행, 고객상담, 불만처리 등 민원 처리, 분쟁조정 해결을 위한 기록보존, 네이버 예약 이용 후 리뷰작성에 따른 네이버페이 포인트 지급 및 관련 안내<br><br> 3. 보관기간<br> - 회원탈퇴 등
                                  개인정보 이용목적 달성 시까지 보관<br> - 단, 상법 및 ‘전자상거래 등에서의 소비자 보호에 관한 법률’ 등 관련 법령에 의하여 일정 기간 보관이 필요한 경우에는 해당 기간 동안 보관함<br><br> 4. 동의 거부권 등에 대한 고지: 정보주체는 개인정보의 수집 및 이용 동의를 거부할 권리가 있으나, 이 경우 상품 및 서비스 예약이 제한될 수 있습니다.<br></div>
                          </div>
                          <!-- [D] 약관 보기 클릭 시 agreement에 open 클래스 추가 -->
                          <div class="agreement"> <span class="chk_txt_span"> <i class="spr_book ico_arr_ipc2"></i> <span>개인정보 제3자 제공 동의</span> </span>
                              <a href="#" class="btn_agreement"> <span class="btn_text">보기</span> <i class="fn fn-down2 _arw"></i> </a>
                              <div class="useragreement_details custom_details_wrap">
                                  <div class="custom_details">&lt;개인정보 제3자 제공 동의&gt;<br><br> 1. 개인정보를 제공받는 자 : 미디어앤아트<br><br> 2. 제공하는 개인정보 항목 : [필수] 네이버 아이디, 이름, 연락처 [선택] 이메일 주소<br><br> 3. 개인정보를 제공받는 자의 이용목적 : 사업자회원과 예약이용자의 원활한 거래 진행, 고객상담, 불만처리 등 민원 처리, 서비스 이용에 따른 설문조사 및 혜택 제공, 분쟁조정
                                      해결을 위한 기록보존<br><br> 4. 개인정보를 제공받는 자의 개인정보 보유 및 이용기간 : 개인정보 이용목적 달성 시 까지 보관합니다.<br><br> 5. 동의 거부권 등에 대한 고지 : 정보주체는 개인정보 제공 동의를 거부할 권리가 있으나, 이 경우 상품 및 서비스 예약이 제한될 수 있습니다.<br></div>
                              </div>
                          </div>
                      </div>
                  </div>
                  <div class="box_bk_btn">
                      <!-- [D] 약관 전체 동의가 되면 disable 제거 -->
                      <div class="bk_btn_wrap disable _bookingBtn"> <button type="submit" class="bk_btn"> <i class="spr_book ico_naver_s"></i>  <span>예약하기</span> </button> </div>
                  </div>
                </form>
            </div>
        </div>
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
</body>
<script id="count-template" type="text/x-handlebars-template">
    {{#if adult}}
    성인 ({{adult}}),
    {{/if}}
    {{#if youth}}
    청소년 ({{youth}}),
    {{/if}}
    {{#if child}}
    어린이 ({{child}})
    {{/if}}
</script>
<script>
  (function($, reserve){
      "use strict";
      reserve.init();
  })(jQuery, window.reservation.reserve)
</script>
</html>
