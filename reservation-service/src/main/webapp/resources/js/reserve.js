(function($, Booking, Handlebars){
    "use strict";
    window.reservation = window.reservation || {};
		window.reservation.reserve = (function(){
      var priceTypeArea;
      var $priceTypeArea;
      var objBooking;
      var plusBtn;
      var minusBtn;
      var $sum;
      var sum;
      var $form;
      var $bookingBtn;
      var $agreeCheckBox;
      var $tel;
      var $email;
      var $requiredCheck;

      // price-type
      var ADULT;
      var YOUTH;
      var CHILD;

      // template
      var countTemplate;
      var countTemplateObj;
      var typeToStringObj;
      var $countTemplateTarget;

      //regex
      var regTel;
      var regEmail;

      function init() {
        priceTypeArea = "._priceTypeArea";
        $priceTypeArea = $(priceTypeArea);
        objBooking = {};
        plusBtn = "._plus";
        minusBtn = "._minus";
        $sum = $("._sum");
        sum = 0;
        $form = $("#form");
        $bookingBtn = $("._bookingBtn");
        $agreeCheckBox = $("._agree");
        $tel = $("#tel");
        $email = $("#email");
        $requiredCheck = $("._required");

        ADULT = 1;
        YOUTH = 2;
        CHILD = 3;
        countTemplateObj = {
          adult : "",
          youth : "",
          child : ""
        };
        typeToStringObj = {};
        typeToStringObj[ADULT] = "adult";
        typeToStringObj[YOUTH] = "youth";
        typeToStringObj[CHILD] = "child";
        countTemplate = Handlebars.compile($("#count-template").html());
        $countTemplateTarget = $("._countTemplateTarget");

        regTel = /^\d{2,3}-\d{3,4}-\d{4}$/;
        regEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

        $priceTypeArea.each(function(index, element) {
          objBooking[$(this).data("price-type")]= new Booking(priceTypeArea+"[data-price-type="+$(this).data("price-type")+"]");
        });

        addEvntHandling();
      }

      function addEvntHandling() {
        $(plusBtn).on("click", amountBtnHandling);
        $(minusBtn).on("click", amountBtnHandling);

        $requiredCheck.on("keyup", checkEssentialValidation.bind(null, false));
        $agreeCheckBox.on("click", function(e) {
          checkEssentialValidation(false);
        });
        $form.on("submit", submitHandling);

        $(".btn_agreement").on("click", function(e) {
          e.preventDefault();
          if($(this).closest(".agreement").hasClass("open")) {
            $(this).closest(".agreement").removeClass("open");
            $(this).find("._arw").addClass("fn-down2")
                                 .removeClass("fn-up2");
          }
          else {
            $(this).closest(".agreement").addClass("open");
            $(this).find("._arw").removeClass("fn-down2")
                                 .addClass("fn-up2");
          }
        });
      }

      function submitHandling(e) {
        if(!checkEssentialValidation(true)) {
          return false;
          //이메일은 입력 안해도 되지만 했으면 유효성검사
        } else if ($email.val()!=="") {
        		console.log($email.val());
          return isValidEmail($email.val(),true);
        }
        $tel.val($tel.val().split("-").join(""));
      }

      function amountBtnHandling(e) {
        calcTotal();
        checkEssentialValidation(false);
      }

      function calcTotal() {
        sum = 0;

        $.each( objBooking, function(key, value) {
          var amount = value.getAmount();
          sum += amount;
          countTemplateObj[typeToStringObj[key]] = amount;
        })

        $sum.text(sum);
        $countTemplateTarget.text(countTemplate(countTemplateObj))
      }

      function isBlank() {
        var isBlank = false;

        $requiredCheck.each(function(index, element) {
          var input = $(this).val();
          if(input.length===0 || !input.trim()) {
            return !(isBlank = true);
          }
        })
        return isBlank;
      }

      function isValidTel(strTel) {
        if(regTel.test(strTel)){
          return true;
        }
        return false;
      }

      function isValidEmail(strEmail, isShowMessage) {
        var valid = true;
        
        if(!regEmail.test(strEmail)) {
          valid = false;
        }

        if(!valid && isShowMessage) {
          alert("이메일을 확인해주세요.");
        }

        return valid;
      }

      function checkEssentialValidation(isShowMessage, e) {
        $bookingBtn.addClass("disable");
        var msg = "";
        var valid = true;
        if(sum <= 0) {
          msg = "1개이상 예약부탁드립니다.";
          valid = false;
        }
        else if(isBlank()) {
          msg = "필수입력사항을 입력해주세요.";
          valid = false;

        } else if(!isValidTel($tel.val())) {
          msg = "전화번호를 확인해주세요.";
          valid = false;

        } else if(!$agreeCheckBox.prop("checked")) {
          msg = "이용약관을 동의해주세요~";
          valid = false;
        }
        else {
          $bookingBtn.removeClass("disable");
          valid = true;
        }

        if(isShowMessage && msg !== "")
          alert(msg);

        return valid;
      }

      return {
        init: init
      }
    })();
})(jQuery, window.reservation.Booking, Handlebars)
