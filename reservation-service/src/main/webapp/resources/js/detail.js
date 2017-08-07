define("detail", ["rolling"], function(Rolling) {
    "use strict";
    return (function() {
        // 헤더
        var $header;
        var headerModifierClass;
        // 제품 상세보기
        var $detailContentWrapper;
        var $detailContent;
        var toggleModifierClass;
        // 상세정보/오시는길 탭
        var $infoTabArea;

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
          lazyImgClassName = ".lazyImg";

          calcLazyImgPosition(); // 레이지로딩 이미지 jquery select,  높이 값 찾음
          //calcLazyImgePosition()부분이 위에 부분과 달리 함수를 호출하는 형식으로 작성하였는데
          //통일성이 좀 없는것 같아서 값을 반환하려고했는데, 2개( jqueryselector, 엘레멘트높이값)을
          //반환해야해서 그렇게 못했는데, 이렇게 해도 괜찮을까요?? 혹시 더 좋은 방법이 있을까요?

          rolling = new Rolling(".visual_img", ".item", {
              "prevBtn" : ".btn_prev",
              "nextBtn" : ".btn_nxt",
              "autoSlide" : false,
              "currentNumberSelector": "._ProductImageCurrentCount",
              "isTouch" : true
          });
        }

        function eventListen() {
          $infoTabArea.on("click", "._tab", infoTabClickListener);
          $detailContentWrapper.on("click", "._hinge", hingeClickListener);
          $(window).on("scroll", lazyLoadingHandler);
          $(window).on("scroll", headerHandler);

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
        function lazyLoadingHandler() {
            if (lazyImgScrollTop < $(window).scrollTop()+ $(window).height()+50 ) {
                $lazyImg.attr("src" , $lazyImg.data("src"))
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

        function init() {
          initVariable();
          eventListen();
        }


        return {
          init : init
        };

        })();
});
