moment.locale('ko', {
    weekdays: ["일요일","월요일","화요일","수요일","목요일","금요일","토요일"],
    weekdaysShort: ["일","월","화","수","목","금","토"],
});


// 핸들바 helper 설정
Handlebars.registerHelper('exit', function (string,value) {
	if(value){
		 return string +' ('+ value+')';
	}
	return '';
});

Handlebars.registerHelper('plus', function (first,second,third) {
	  return first+ second + third;
});

Handlebars.registerHelper("timeStamp", function(timestamp) {
	  if (moment) {
	    // can use other formats like 'lll' too
	    return  moment(timestamp).format("YYYY.DD.MM (ddd)");
	  }
	  else {
	    return datetime;
	  }
});

Handlebars.registerHelper("btnText", function(btn) {
	 return btn;
});

var ReservationState = (function(){

	var templateSource = $("#reservation-content").html(),
	Template = Handlebars.compile(templateSource),
	expectationLength =0,
	confirmedLength = 0,
	usedLength =0,
	cancellationLength = 0;
	
	var $all = $(".link_summary_board ").eq(0),
	$expectation =$(".link_summary_board ").eq(1),
	$usedLength = $(".link_summary_board ").eq(2),
	$cancellation = $(".link_summary_board").eq(3);
	
	var $allCards = $(".card"),
	$usedCard = $(".used:first"),
	$expectationCard = $(".expectation, .confirmed"),
	$cancellationCard = $(".cancellation");
	
	
	var $article =null;
	var $cardDetail ;
	var $cardHeader;
	
	
	// enum 같은 느낌으로 사용.
	// ENUM 을 이렇게 사용하는게 맞는지 의문이 생김.
	var reservationTypeEnum = {
		    ALL_RESERVATION : 0,
		    EXPECTATION : 1,
		    END : 2,
		    CENCELLATION : 3
		}
	
	// 취소하겠냐는 확인을 보여주는 event 
	function confirmCancellationBtn(event){
		event.preventDefault();
		var productId =0,
		$popoup;
		
		$cardDetail = $(this).parents(".card_detail");
		$article = $cardDetail.parents("article");
		
		productId = $cardDetail.data("id");
		$popoup = $(".popup_booking_wrapper");

		$popoup.removeClass("none");
		$popoup.data("id",productId);
	}
	
	// 클릭시 취소됨 
	function cancellationBtn(event){
		event.preventDefault();
		var id = $(".popup_booking_wrapper").data("id");
		// card_item 
		$.ajax({
			  method: "Delete",
			  url: "/reservation/"+id
		}).done(cancellationSuccess); 
	}
	
	// outerHtml 구현 
	function outerHtml(url){
		return url.clone().wrapAll("<div/>").parent().html();
	}
	
	
	function cancellationSuccess(data){
		var expectationCount;
		var cancellationCount;
		
		if(data){
			$cardHeader = $article.siblings(".link_booking_details");
			if($article.siblings().length == 1 ){
			 	$cardHeader.remove();
			}
			// 삭제시 메뉴 bar 가 안보이는 현상 
			$(".cancellation").append(outerHtml($article));
			$(".popup_booking_wrapper").addClass("none");
			$article.remove();	
			// count 변경하는 작업 
			expectationCount = $expectation.find(".figure").text();
			cancellationCount = $cancellation.find(".figure").text();
			$expectation.find(".figure").text(--expectationCount);
			$cancellation.find(".figure").text(++cancellationCount)
			alert("취소 되었습니다.");
		}else{
			alert("예상치 못한 에러가 ...");
		}
	}
	

	
	
	


	
	function loading(type,$card,_menubar,_icon,_btns){
		var max = 0;
		// 받아온 data의 길이로 max를 설정하고, return 받기 위해  비동기를 막아 두었습니다. 
		$.ajax({
			method : "GET",
			url : "/reservation/type/"+type,
			  async: false
		}).done(function(data) {
			if (data.length === 0) {
				$(".err").addClass("none");
			}else{
				var item = {
						reservation : [],
						menubar : [{ menubar : _menubar, icon : _icon}]
				};
				max = data.length;
				for (var i = 0; i < max; ++i) {
					data[i].btns = _btns
					item.reservation.push(data[i]);
				}
				//expectationLength += max ;
				var html = Template(item);
				$card.append(html);
			}
		});
		return max;
	}
	
	
	
	// 함수가 조금 길지 않나 ... 
	function menuClickEvent(event){
		// 선택된 인자외에 다 none 처리 
		event.preventDefault();
		var index = $(".link_summary_board").index(this);
		$dumyCard = null;
		
		if(index === reservationTypeEnum.ALL_RESERVATION){
			// 0
			$allCards.removeClass("none");
			$dumy = $allCards;
		}else if(index === reservationTypeEnum.EXPECTATION){
			// 1
			$expectationCard.removeClass("none");
			$(".card:eq(2), .card:eq(3)").addClass("none");
			
			$dumy = $expectationCard;
		}else if(index === reservationTypeEnum.END){
			// 2
			$usedCard.removeClass("none");
			$(".card").not($usedCard).addClass("none");
			$dumy = $usedCard;
		}else if(index === reservationTypeEnum.CENCELLATION){
			// 3
			$allCards.not($cancellationCard).addClass("none");
			$cancellationCard.removeClass("none");
			$dumy = $cancellationCard;
		}
		if($dumy.children("article").length){
			$(".err").addClass("none");
		}else{
			$(".err").removeClass("none");
		}
	}
	
	return{
		init : function(){
			// ajax 값 호출 
			expectationLength =loading(0,$('.expectation'),"예약 신청중","ico_clock","취소");
			confirmedLength =loading(1,$('.confirmed'),"예약 확정","ico_check2","취소");
			usedLength =loading(2,$('.used:first'),"이용 완료","ico_check2","예매자 리뷰 남기기");
			cancellationLength =loading(3,$('.used:last'),"취소된 예약","ico_cancel","");
			
			// menu bar 초기화 
			console.log(expectationLength+confirmedLength);
			$expectation.find(".figure").text(expectationLength+confirmedLength);
			$usedLength.find(".figure").text(usedLength);
			$cancellation.find(".figure").text(cancellationLength);
			$all.find(".figure").text(expectationLength+usedLength+cancellationLength+confirmedLength);
		
			// menu bar 이벤트 등록
			$all.on("click",menuClickEvent.bind($all));
			$expectation.on("click",menuClickEvent.bind($expectation));
			$usedLength.on("click",menuClickEvent.bind($usedLength));
			$cancellation.on("click",menuClickEvent.bind($cancellation));
			
			// 버튼 취소 이벤트 
			// 취소 버튼과 x 버튼 누르면 안보이게 진행
			$(".btn_gray, .popup_btn_close").on("click",function(event){
				event.preventDefault();
				$(".popup_booking_wrapper").addClass("none");
			})
			$(".btn_green").on("click",cancellationBtn);
			$(".expectation, .confirmed").on("click",".booking_cancel .btn",confirmCancellationBtn);
		}
	}
})();
