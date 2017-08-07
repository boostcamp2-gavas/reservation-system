<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">


<jsp:include page="inc/common/head.jsp">
			<jsp:param value="네이버 예약" name="title"/>
</jsp:include>

<body>
    <div id="container">
        <jsp:include page="inc/common/header.jsp"/>
        <hr>
        <div class="ct">
            <div class="section_my">
                <!-- 예약 현황 -->
                <div class="my_summary">
                    <ul class="summary_board">
                        <li class="item">
                            <!--[D] 선택 후 .on 추가 link_summary_board -->
                            <a href="#" class="link_summary_board on _btnAll _btnFilter"> <i class="spr_book2 ico_book2"></i> <em class="tit">전체</em> <span class="figure _all">0</span> </a>
                        </li>
                        <li class="item">
                            <a href="#" class="link_summary_board _btnSchedule _btnFilter"> <i class="spr_book2 ico_book_ss"></i> <em class="tit">이용예정</em> <span class="figure _schedule">0</span> </a>
                        </li>
                        <li class="item">
                            <a href="#" class="link_summary_board _btnCompletion _btnFilter"> <i class="spr_book2 ico_check"></i> <em class="tit">이용완료</em> <span class="figure _completion">0</span> </a>
                        </li>
                        <li class="item">
                            <a href="#" class="link_summary_board _btnCancellationAndRefund _btnFilter"> <i class="spr_book2 ico_back"></i> <em class="tit">취소·환불</em> <span class="figure _cancellationAndRefund">0</span> </a>
                        </li>
                    </ul>
                </div>
                <!--// 예약 현황 -->

                <!-- 내 예약 리스트 -->
                <div class="wrap_mylist">
                    <ul class="list_cards _myReservationList" ng-if="bookedLists.length > 0">

                    </ul>
                </div>
                <!--// 내 예약 리스트 -->

                <!-- 예약 리스트 없음 -->
                <div class="err _emptyList"> <i class="spr_book ico_info_nolist"></i>
                    <h1 class="tit">예약 리스트가 없습니다</h1>
                </div>
                <!--// 예약 리스트 없음 -->
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
    <div class="popup_booking_wrapper hide _cancellationLayerPopup">
    </div>
    <!--// 취소 팝업 -->

</body>
<script id="cancellationPopup-template" type="text/x-handlebars-template">
<div class="dimm_dark" style="display:block"></div>
<div class="popup_booking refund">
    <h1 class="pop_tit">
        <span>{{name}}</span>
        <small class="sm">{{reservationDate}}</small>
    </h1>
    <div class="nomember_alert">
        <p>취소하시겠습니까?</p>
    </div>
    <div class="pop_bottom_btnarea">
        <div class="btn_gray">
            <a href="#" class="btn_bottom _cancel"><span>아니오</span></a>
        </div>
        <div class="btn_green">
            <a href="#" class="btn_bottom _confirm" data-id={{id}}><span>예</span></a>
        </div>
    </div>
    <!-- 닫기 -->
    <a href="#" class="popup_btn_close _close" title="close">
        <i class="spr_book2 ico_cls"><span class="blind">닫기</span></i>
    </a>
    <!--// 닫기 -->
</div>
</script>
<%-- 신청 중 --%>
<script id="reservationList-asking-template" type="text/x-handlebars-template">
  <li class="card">
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
    {{#each reservationLists}}
      <article class="card_item _reservationItem" data-id="{{id}}" data-name="{{name}}" data-reservation-date="{{reservationDate}}">
          <div class="card_body">
              <div class="left"></div>
              <div class="middle">
                  <div class="card_detail">
                      <em class="booking_number">No.{{id}}</em>
                      <h4 class="tit">{{name}}</h4>
                      <ul class="detail">
                          <li class="item">
                              <span class="item_tit">일정</span>
                              <em class="item_dsc">
                                                {{reservationDate}}
                              </em>
                          </li>
                          <li class="item">
                              <span class="item_tit">내역</span>
                              <em class="item_dsc">
                                    {{#if generalTicketCount}}
                                    성인 ({{generalTicketCount}}),
                                    {{/if}}
                                    {{#if youthTicketCount}}
                                    청소년 ({{youthTicketCount}}),
                                    {{/if}}
                                    {{#if childTicketCount}}
                                    어린이 ({{childTicketCount}})
                                    {{/if}}
                              </em>
                          </li>
                          <li class="item">
                            <span class="item_tit">업체</span>
                            <em class="item_dsc">
                              {{name}}
                            </em>
                          </li>
                      </ul>
                      <!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
                      <div class="booking_cancel">
                          <button class="btn _btnReservationCancel"><span>취소</span></button>
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
        <a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
      </article>
    {{/each}}
  </li>
</script>

<script id="reservationList-confirm-template" type="text/x-handlebars-template">
  <li class="card confirmed">
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
      {{#each reservationLists}}
        <article class="card_item _reservationItem" data-id="{{id}}" data-name="{{name}}" data-reservation-date="{{reservationDate}}">
            <div class="card_body">
                <div class="left"></div>
                <div class="middle">
                    <div class="card_detail">
                        <em class="booking_number">No.{{id}}</em>
                        <h4 class="tit">{{name}}</h4>
                        <ul class="detail">
                            <li class="item">
                                <span class="item_tit">일정</span>
                                <em class="item_dsc">
                                                  {{reservationDate}}
                                </em>
                            </li>
                            <li class="item">
                                <span class="item_tit">내역</span>
                                <em class="item_dsc">
                                      {{#if generalTicketCount}}
                                      성인 ({{generalTicketCount}}),
                                      {{/if}}
                                      {{#if youthTicketCount}}
                                      청소년 ({{youthTicketCount}}),
                                      {{/if}}
                                      {{#if childTicketCount}}
                                      어린이 ({{childTicketCount}})
                                      {{/if}}
                                </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">업체</span>
                              <em class="item_dsc">
                                {{name}}
                              </em>
                            </li>
                        </ul>
                        <!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
                        <div class="booking_cancel">
                            <button class="btn _btnReservationCancel"><span>취소</span></button>
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
          <a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
        </article>
      {{/each}}
    </li>
</script>

<script id="reservationList-completion-template" type="text/x-handlebars-template">
  <li class="card used">
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
    {{#each reservationLists}}
      <article class="card_item _reservationItem" data-id="{{id}}" data-name="{{name}}" data-reservation-date="{{reservationDate}}">
          <div class="card_body">
              <div class="left"></div>
              <div class="middle">
                  <div class="card_detail">
                      <em class="booking_number">No.{{id}}</em>
                      <h4 class="tit">{{name}}</h4>
                      <ul class="detail">
                          <li class="item">
                              <span class="item_tit">일정</span>
                              <em class="item_dsc">
                                                {{reservationDate}}
                              </em>
                          </li>
                          <li class="item">
                              <span class="item_tit">내역</span>
                              <em class="item_dsc">
                                    {{#if generalTicketCount}}
                                    성인 ({{generalTicketCount}}),
                                    {{/if}}
                                    {{#if youthTicketCount}}
                                    청소년 ({{youthTicketCount}}),
                                    {{/if}}
                                    {{#if childTicketCount}}
                                    어린이 ({{childTicketCount}})
                                    {{/if}}
                              </em>
                          </li>
                          <li class="item">
                            <span class="item_tit">업체</span>
                            <em class="item_dsc">
                              {{name}}
                            </em>
                          </li>
                      </ul>
                      <div class="booking_cancel">
                          <a href="/reviews/form?productId={{productId}}" class="link"><span>예매자 리뷰 남기기</span></a>
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
        <a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
      </article>
    {{/each}}
  </li>
</script>

<script id="reservationList-cancellationAndRefund-template" type="text/x-handlebars-template">
  <li class="card used">
    <div class="link_booking_details">
        <div class="card_header">
            <div class="left"></div>
            <div class="middle">
                <!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
                <button type="button" class="_btnRemoveCancellation">
                  <i class="spr_book2 ico_cancel"></i>
                  <span class="tit">취소된 예약</span>
                </button>
            </div>
            <div class="right"></div>
        </div>
    </div>
    {{#each reservationLists}}
      <article class="card_item _reservationItem" data-id="{{id}}" data-name="{{name}}" data-reservation-date="{{reservationDate}}">
          <div class="card_body">
              <div class="left"></div>
              <div class="middle">
                  <div class="card_detail">
                      <em class="booking_number">No.{{id}}</em>
                      <h4 class="tit">{{name}}</h4>
                      <ul class="detail">
                          <li class="item">
                              <span class="item_tit">일정</span>
                              <em class="item_dsc">
                                                {{reservationDate}}
                              </em>
                          </li>
                          <li class="item">
                              <span class="item_tit">내역</span>
                              <em class="item_dsc">
                                    {{#if generalTicketCount}}
                                    성인 ({{generalTicketCount}}),
                                    {{/if}}
                                    {{#if youthTicketCount}}
                                    청소년 ({{youthTicketCount}}),
                                    {{/if}}
                                    {{#if childTicketCount}}
                                    어린이 ({{childTicketCount}})
                                    {{/if}}
                              </em>
                          </li>
                          <li class="item">
                            <span class="item_tit">업체</span>
                            <em class="item_dsc">
                              {{name}}
                            </em>
                          </li>
                      </ul>
                  </div>
              </div>
              <div class="right"></div>
          </div>
          <div class="card_footer">
              <div class="left"></div>
              <div class="middle"></div>
              <div class="right"></div>
          </div>
        <a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
      </article>
    {{/each}}
  </li>
</script>

<script>
    require(['myreservation'], function(Myreservation) {
        "use strict";
        var objReservationType = {
           "ASKING" : ${asking},
           "CONFIRM" : ${confirm},
           "COMPLETION" : ${completion},
           "CANCELLATION" : ${cancellation},
           "REFUND" : ${refund}
        }
        Myreservation.init(objReservationType);
    });
</script>
</html>
