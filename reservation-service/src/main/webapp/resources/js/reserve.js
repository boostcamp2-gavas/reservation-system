define("reserve", ["ticket", "Handlebars"], function(Ticket, Handlebars) {
    "use strict";
    var arrTicket;
    var sum;
    var $sum;
    var sumPrice;
    var $sumPrice;
    var $bookingBtn;
    var $agreeCheckBox;
    var $tel;
    var $email;
    var $requiredInputs;

    var countTemplate;

    var regTel;
    var regEmail;

    function init() {

        arrTicket = [];
        sum = 0;
        $sum = $("._sum");
        sumPrice = 0;
        $sumPrice = $("._sumPrice");
        $bookingBtn = $("._bookingBtn");
        $agreeCheckBox = $("._agree");
        $tel = $("#tel");
        $email = $("#email");
        $requiredInputs = $("._required");

        countTemplate = Handlebars.compile($("#count-template").html());

        regTel = /^\d{2,3}-\d{3,4}-\d{4}$/;
        regEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

        addEvntHandling();
    }

    function addEvntHandling() {

        $requiredInputs.on("keyup", checkEssentialValidation.bind(null, false));
        $agreeCheckBox.on("click", function(e){
            checkEssentialValidation(false)
        });
        $("#form").on("submit", submitHandling);

        $(".btn_agreement").on("click", function(e) {
            e.preventDefault();
            var $this = $(this);
            var isOpened = $this.closest(".agreement").hasClass("open");
            $this.closest(".agreement").toggleClass("open", !isOpened);
            $this.find("._arw").toggleClass("fn-down2", isOpened)
                                 .toggleClass("fn-up2", !isOpened);
        });

        $("._priceTypeArea").each(function(index, element) {
            var ticket = new Ticket("._priceTypeArea[data-price-type="+$(this).data("price-type")+"]")
            arrTicket.push(ticket);
            ticket.on("change", function(e){
            sum = 0;
            sumPrice = 0;
        	arrTicket.forEach(function(ticket) {
        		sum += ticket.getAmount();
                sumPrice += ticket.getPrice();
        	});
        	$sum.text(sum);
            $sumPrice.text(sumPrice.toLocaleString());
            checkEssentialValidation(false);
          });
        });
    }

    function submitHandling(e) {
    if(!checkEssentialValidation(true)) {
        return false;
    }
    if ($email.val()!=="") {
        return isValidEmail($email.val(), true);
    }
        return true;
    }

    function isBlank() {
        $requiredInputs.each(function(index, element) {
            var input = $(this).val().trim();
            if(input.length===0) {
                return true;
            }
        })
        return false;
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

    function checkEssentialValidation(isShowMessage) {
        $bookingBtn.addClass("disable");
        var msg = "";
        var valid = true;

        if(sum <= 0) {
            msg = "1개이상 예약부탁드립니다.";
            valid = false;
        }


        if(isBlank()) {
            msg = "필수입력사항을 입력해주세요.";
            valid = false;
        }

        if(!isValidTel($tel.val())) {
            msg = "전화번호를 확인해주세요. 예) 010-2434-3434";
            valid = false;

        }

        if(!$agreeCheckBox.prop("checked")) {
            msg = "이용약관을 동의해주세요~";
            valid = false;
        }

        if (isShowMessage &&  msg !== "") {
            alert(msg);
        }

        if(valid) {
            $bookingBtn.removeClass("disable");
        }

        return valid;
    }

    return {
        init: init
    }
});
