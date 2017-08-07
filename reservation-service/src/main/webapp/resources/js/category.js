(function($) {
    "use strict";
    window.reservation = window.reservation || {};
    window.reservation.category = (function() {
      //카테고리 리스트
      var categoryList = "._categoryList";
      var categoryItem = "._item";
      var name = "._name";
      var modifyBtn = "._modify";
      var destroyBtn = "._destroy";
      var editInput = "._editInput";
      // 카테고리 이름 수정 중일 때
      var EDITING = "editing";
      var APIURL = "/admin/categories/";
      // 이벤트 리스닝
      function eventListen(){

      	  $(categoryList).on("click", modifyBtn, modifyListener)
      	  				.on("click", destroyBtn, destroyListener)
      	  				.on("keyup", editInput, modifyEnterListener);

        $(document).ajaxError(function (event, xhr, ajaxOptions, thrownError) {
              console.log(xhr);
              alert("일시적 오류가 발생하였습니다.");
        });
      }


      // 삭제 버튼 클릭시 카테고리 1개 지우는 이벤트 리스너
      function destroyListener(e) {
        var $item = $(this).parents(categoryItem);

        reqDestroy($item);
      }

      // 카테고리 삭제하는 요청보내는 함수
      function reqDestroy($item) {
        $.ajax({
          method: "delete",
          url : APIURL+getId($item),
          headers: {
            "Content-Type" :"application/json"}
        }).done(function( res ) {
          viewRemove($item);
        });
      }

      function viewRemove($item) {
        $item.slideUp("slow", function(){ $(this).remove()});
      }

      // 수정 버튼 클릭시 카테고리 1개 수정하는 이벤트 리스너
      function modifyListener(e) {
        var $item = $(this).parents(categoryItem);
        modify($item);
      }
      // 수정 입력 input 엔터시 수정하는 이벤트 리스너
      function modifyEnterListener(e) {
        if(e.which == 13) {
          var $item = $(this).parents(categoryItem);
          modify($item);
        }
      }

      function modify($item) {
        if(getStatus($item)===EDITING) {
          if(!checkValidation($item))
            return false;
          reqModify($item);
        }
        else {
          setStatus($item, EDITING);
          viewToggleModifyInput($item);
        }
      }

      // 데이터 유효성 검사하기
      // 유효하면 true반환,
      // 유효하지않으면 false반환
      function checkValidation($item) {
        return isBlank($item.find(editInput)) ? false : true;
      }

      // 입력 인풋 빈값 검사하기
      // 빈값이면 true 반환,
      // 빈값 아니면 false반환
      function isBlank($inputTarget) {
          var strTrimmed = $.trim($inputTarget.val());
          if(strTrimmed === "") {
            alert("빈값은 입력이 불가합니다.");
            $inputTarget.focus();
            return true;
          }
          return false;
      }

      // 수정 input창 사라지고 나타내기
      function viewToggleModifyInput($item) {
        $item.find(name).toggleClass("hide");
        $item.find(editInput).toggleClass("hide").val('');
      }

      // 수정 요청 보내기
      function reqModify($item) {
        var strToBeName = $item.find(editInput).val();

        $.ajax({
          method: "put",
          url : APIURL+getId($item),
          headers: {
            "Content-Type" :"application/json",
            },
          data : JSON.stringify({ name : strToBeName})
        }).done(function( res ) {
          $item.find(name).html(strToBeName);
          viewToggleModifyInput($item);
          setStatus($item, "");
        });
      }
      // 카테고리 id 가져오기
      function getId($this) {
        return $this.data("id");
      }
      // 카테고리 상태 변경
      function setStatus($this, strStatus) {
        $this.data("status", strStatus);
      }
      // 카테고리 상태 가져오기
      function getStatus($this) {
        return $this.data("status");
      }

      function showErrorMessage() {
        alert("일시적 오류가 발생하였습니다.");
      }
      // 초기화 함수
      function init() {
        eventListen();
      }

      return {
        init : init
      };
    })();

})(jQuery)
