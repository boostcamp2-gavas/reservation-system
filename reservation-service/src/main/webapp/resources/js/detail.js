(function ($, Rolling, eg) {
    "use strict";
    window.reservation = window.reservation || {};
    window.reservation.detail = (function() {
    // 헤더
    var $header;
    var headerModifierClass;
    // 제품 상세보기
    var $detailContentWrapper;
    var $detailContent;
    var toggleModifierClass;
    // 상세정보/오시는길 탭
    var $infoTabArea;
    // 코멘트 영역
    var $reviewArea;
    // 코멘트 이미지 플리킹(egjs)
    var commentImageflicking;
    // 코멘트 이미지 뷰어부분
    var $photoviwer;
    var $photo;
    var photoId;
    var rolling;
    var $lazyImg;
    var lazyImgClassName;
    var lazyImgScrollTop;

    function initVariable() {
      // 헤더
      $header = $("#header");
      headerModifierClass="transparent";
      // 제품 상세보기
      $detailContentWrapper = $("._detail_content_wrapper");
      $detailContent = $("._detail_content");
      toggleModifierClass = "close3";
      // 상세정보/오시는길 탭
      $infoTabArea = $("._info_tab_area");
      // 코멘트 영역
      $reviewArea = $("._reviewArea");
      // 코멘트 이미지 플리킹(egjs)
      commentImageflicking;
      // 코멘트 이미지 뷰어부분
      $photoviwer = $("#photoviwer");
      $photo = $("#photo");
      photoId = "#photo";
      lazyImgClassName = ".lazyImg";

      calcLazyImgPosition(); // 레이지로딩 이미지 jquery select,  높이 값 찾음
      //calcLazyImgePosition()부분이 위에 부분과 달리 함수를 호출하는 형식으로 작성하였는데
      //통일성이 좀 없는것 같아서 값을 반환하려고했는데, 2개( jqueryselector, 엘레멘트높이값)을
      //반환해야해서 그렇게 못했는데, 이렇게 해도 괜찮을까요?? 혹시 더 좋은 방법이 있을까요?

      rolling = new Rolling(".visual_img", ".item", {"prevBtn" : ".btn_prev", "nextBtn" : ".btn_nxt", "autoSlide" : false, "currentNumberSelector": "._ProductImageCurrentCount", "isTouch" : true });
    }

    function eventListen() {
      $infoTabArea.on("click", "._tab", infoTabClickListener);
      $detailContentWrapper.on("click", "._hinge", hingeClickListener);
      $reviewArea.on("click", "._commentThumb", commentThumbClickListener);
      $photoviwer.on("click", "._close", photoviewCloseListener);
      $(window).on("scroll", lazyLoadingHandler);

      //스크롤될 때 헤더에 투명도 추가/제거
      if (!$("body").height() < $(window).height()) {
        $(window).on("scroll", headerHandler);
      }
    }

    function headerHandler() {
        if ($(window).scrollTop() !== 0) {
            $header.removeClass(headerModifierClass);
        }
        else
            $header.addClass(headerModifierClass);
    }
    // 처음에 레이지 로딩이미지 계산 및
    // 스크롤 하면서 내리니까 레이지이미지 엘레멘트 중에서 첫번째부터 로딩 시킴
    // 1366 -> 아이패드 프로 세로크기
    function lazyLoadingHandler() {
        if (lazyImgScrollTop < $(window).scrollTop()+ 1366 ) {
            $lazyImg.attr("src" , $lazyImg.data('src'))
                    .removeClass(lazyImgClassName.slice(1))
                    .on("load",calcLazyImgPosition);
        }
    }
    // 레이지이미지중 맨위에꺼 위치 계산
    // 이미지가 로딩되서 레이지로딩할 이미지 위치 다시 계산
    function calcLazyImgPosition() {
      $lazyImg = $(lazyImgClassName).eq(0);

      if($lazyImg.length) {
        lazyImgScrollTop = $lazyImg.offset().top;
      }
    }

    function hingeClickListener(e) {
      e.preventDefault();
      $detailContentWrapper.find("._hinge").toggleClass("hide");
      $detailContent.toggleClass(toggleModifierClass);
      }

    function infoTabClickListener(e) {
      e.preventDefault();
      $infoTabArea.find("._tab").removeClass("active");
      $infoTabArea.find($(this)).addClass("active");

      $infoTabArea.find("._content").addClass("hide");
      $infoTabArea.find($(this).data("content")).removeClass("hide");
    }

    function commentThumbClickListener(e) {
      e.preventDefault();
      $photo.empty()
                 .append($(this).find("._img")
                 .clone()
                 .removeClass("hide"));
      $photoviwer.removeClass("hide");
      //스크롤 막기
      $("body").css("overflow","hidden");
      commentImageflicking = new eg.Flicking(photoId, {
            circular: true,
            defaultIndex: 0,
            duration: 300
        });
    }
    function photoviewCloseListener(e) {
        $photoviwer.addClass("hide");
        $("body").css("overflow","auto");
        commentImageflicking.destroy();
    }

    function init() {
      initVariable();
      eventListen();
    }


    return {
      init : init
    };

  })();

})(jQuery, window.reservation.Rolling, eg);
