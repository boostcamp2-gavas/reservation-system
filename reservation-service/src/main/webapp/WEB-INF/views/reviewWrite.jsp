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
	<div id="container">
		<c:import url="/WEB-INF/views/header.jsp" />
		<div class="ct">
			<div class="ct_wrap">
				<div class="top_title review_header">
					<a class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i> </a>
					<h2><span class="title">${reserveName}</span></h2>
				</div>
				<!-- 리뷰 별점 -->
				<div class="write_act">
					<p class="title_star">별점과 이용경험을 남겨주세요.</p>
					<div class="review_rating rating_point">
						<div class="rating">
							<input type="checkbox" name="rating1" value="0" class="rating_rdo first_star" title="0점">
							<span class="span hide"></span>
							<!-- [D] 해당 별점이 선택될 때 그 점수 이하의 input radio는 checked 클래스 추기 -->
							<input type="checkbox" name="rating2" value="1" class="rating_rdo" title="1점">
							<span class="span"></span>
							<input type="checkbox" name="rating3" value="2" class="rating_rdo" title="2점">
							<span class="span"></span>
							<input type="checkbox" name="rating4" value="3" class="rating_rdo" title="3점" >
							<span class="span"></span>
							<input type="checkbox" name="rating5" value="4" class="rating_rdo" title="4점">
							<span class="span"></span>
							<input type="checkbox" name="rating6" value="5" class="rating_rdo" title="5점">
							<span class="span"></span>
							<!-- [D] 0점일 때 gray_star 추기 -->
							<span class="star_rank gray_star">0</span>
						</div>
					</div>
				</div>
				<!-- //리뷰 별점 -->

				<!-- 리뷰 입력 -->
				<div class="review_contents write">
					<!-- [D] review_write_info 클릭 시 자신을 숨기고 review_textarea 에 focus를 보낸다. -->
					<a class="review_write_info">
						<span class="middot">
							실 사용자의 리뷰는 상품명의 더 나은 서비스 제공과 다른 사용자들의 선택에 큰 도움이 됩니다.
						</span><br>
						<span class="middot">
							소중한 리뷰에 대한 감사로 네이버페이 포인트 500원을 적립해드립니다.
						</span>
						<span class="left_space">(단, 리뷰 포인트는 ID 당 1일 최대 5건까지 지급됩니다.)</span>
					</a>
					<textarea cols="30" rows="10" class="review_textarea"></textarea>
				</div>
				<!-- //리뷰 입력 -->

				<!-- 리뷰 작성 푸터 -->
				<div class="review_write_footer_wrap">
					<div class="review_write_footer">
						<label class="btn_upload" for="reviewImageFileOpenInput">
							<i class="fn fn-image1" aria-hidden="true"></i>
							<span class="text_add_photo">사진 추가</span>
						</label>
						<input type="file" class="hidden_input" id="reviewImageFileOpenInput" accept="image/*" multiple>
						<div class="guide_review">
							<span id="current_char">0</span>/400
							<span>(최소5자이상)</span>
						</div>
					</div>

					<!-- 리뷰 포토 -->
					<div class="review_photos review_photos_write">
						<div class="item_preview_thumbs">
							<ul class="lst_thumb">
								
								
							</ul>
						</div>
					</div>
					<!-- //리뷰 포토 -->

				</div>
				<!-- //리뷰 작성 푸터 -->

				<!-- 리뷰 등록 -->
				<div class="box_bk_btn">
					<button class="bk_btn"><span class="btn_txt">리뷰 등록</span></button>
				</div>
				<!-- //리뷰 등록 -->
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/views/footer.jsp" />
	<script src="/resources/js/node_modules/jquery/dist/jquery.js"></script>
	<script src="/resources/js/node_modules/handlebars/dist/handlebars.js"></script>
	<script src="/resources/js/node_modules/@egjs/component/dist/component.js"></script>
	<script src="/resources/js/messenger.js"></script>
	<script src="/resources/js/writerFooter.js"></script>
	<script src="/resources/js/textArea.js"></script>
	<script src="/resources/js/util.js"></script>
    <script id="thumb_nail_tempalte" type="text/x-handlebars-template">
        {{#items}}
        <li class="item">
            <a class="anchor">
                <span class="spr_book ico_del">삭제</span>
            </a>
            <img src="{{url}}" width="130" alt="" class="item_thumb">
            <span class="img_border"></span>
        </li>
        {{/items}}
    </script>
    <script>
        $(function() {
        	$('.header').addClass('fade');
        	
            var messenger = new Messenger();
            var rating = new Rating($('div.rating'), messenger);
            var textArea = new TextArea($('div.review_contents'), messenger);
            var thumbTemplate = Handlebars.compile($('#thumb_nail_tempalte').html());
            var writerFooter = new WriterFooter($('div.review_write_footer_wrap'), messenger, thumbTemplate);
            $('a.btn_back').on('click', function() {
                window.history.back();
            });
        });
    </script>
</body>
</html>