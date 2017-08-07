<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<jsp:include page="inc/common/head.jsp">
			<jsp:param value="네이버 예약" name="title"/>
</jsp:include>

<body>
	<div id="container">
		<jsp:include page="inc/common/header.jsp"/>
		<div class="ct">
			<div class="ct_wrap">
				<div class="top_title review_header">
					<a href="#" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i> </a>
					<h2><span class="title">클림트 인사이드</span></h2>
				</div>
				<!-- 리뷰 별점 -->
				<form action="/reviews/form" method="post" class="_enrollForm">
					<input type="hidden" name="productId" value="${param.productId}" >
					<div class="write_act">
						<p class="title_star">별점과 이용경험을 남겨주세요.</p>
						<div class="review_rating rating_point">
							<div class="rating _rating">
								<input type="radio" name="score" value="0" class="rating_rdo first_star _radio" title="0점"checked>
								<span class="span hide"></span>
								<!-- [D] 해당 별점이 선택될 때 그 점수 이하의 input radio는 checked 클래스 추기 -->
								<input type="radio" name="score" value="1" class="rating_rdo _radio" title="1점">
								<span class="span"></span>
								<input type="radio" name="score" value="2" class="rating_rdo _radio" title="2점">
								<span class="span"></span>
								<input type="radio" name="score" value="3" class="rating_rdo _radio" title="3점" >
								<span class="span"></span>
								<input type="radio" name="score" value="4" class="rating_rdo _radio" title="4점">
								<span class="span"></span>
								<input type="radio" name="score" value="5" class="rating_rdo _radio" title="5점">
								<span class="span"></span>
								<!-- [D] 0점일 때 gray_star 추기 -->
								<span class="star_rank gray_star _score">0</span>
							</div>
						</div>
					</div>
					<!-- //리뷰 별점 -->

					<!-- 리뷰 입력 -->
					<div class="review_contents write">
						<!-- [D] review_write_info 클릭 시 자신을 숨기고 review_textarea 에 focus를 보낸다. -->
						<label class="review_write_info" for="comment" id="commentLabel">
							<span class="middot">
								실 사용자의 리뷰는 상품명의 더 나은 서비스 제공과 다른 사용자들의 선택에 큰 도움이 됩니다.
							</span><br>
							<span class="middot">
								소중한 리뷰에 대한 감사로 네이버페이 포인트 500원을 적립해드립니다.
							</span>
							<span class="left_space">(단, 리뷰 포인트는 ID 당 1일 최대 5건까지 지급됩니다.)</span>
						</label>
						<textarea cols="30" rows="10" name="comment" class="review_textarea" id="comment"></textarea>
					</div>
					<!-- //리뷰 입력 -->

				<!-- 리뷰 작성 푸터 -->
					<div class="review_write_footer_wrap">
						<div class="review_write_footer">
							<label class="btn_upload" for="reviewImageFileOpenInput">
								<i class="fn fn-image1" aria-hidden="true"></i>
								<span class="text_add_photo">사진 추가</span>
							</label>
							<input type="file" class="hidden_input" id="reviewImageFileInput" accept="image/*" multiple >
							<div class="guide_review">
								<span class="_numOfText">0</span>/400
								<span>(최소5자이상)</span>
							</div>
						</div>

						<!-- 리뷰 포토 -->
						<div class="review_photos review_photos_write _uploadWrapper">
							<div class="item_preview_thumbs">
								<ul class="lst_thumb _thumbnailUploadList">
									<!--handlebars template insert-->


								</ul>
							</div>
						</div>
						<!-- //리뷰 포토 -->
					</div>
					<!-- //리뷰 작성 푸터 -->

					<!-- 리뷰 등록 -->
					<div class="box_bk_btn">
						<button class="bk_btn" type="submit"><span class="btn_txt">리뷰 등록</span></button>
					</div>
					<!-- //리뷰 등록 -->
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
    <script id="commentWrite-thumbnail-template" type="text/x-handlebars-template">
		<li class="item _thumb" data-id ="{{id}}">
			<a href="#" class="anchor">
				<span class="spr_book ico_del">삭제</span>
			</a>
			<img src="/imgresources{{saveFileName}}" width="130" alt="" class="item_thumb">
			<span class="img_border"></span>
			<input type="hidden" name="fileIdList" value="{{id}}">
		</li>
	</script>
	<script>
    require(['reviewWrite'], function(ReviewWrite) {
        "use strict";
		var reviewWrite = new ReviewWrite();
    });

	</script>

</body>

</html>
