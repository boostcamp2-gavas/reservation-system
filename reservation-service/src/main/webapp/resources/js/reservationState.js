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
	
	// enum 같은 느낌으로 사용.
	// ENUM 을 이렇게 사용하는게 맞는지 의문이 생김.
	var reservationTypeEnum = {
		    ALL_RESERVATION : 0,
		    EXPECTATION : 1,
		    END : 2,
		    CENCELLATION : 3
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
			$dumyCard = $allCards;
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
			// content가 없으면, 
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
			console.log(expectationLength);
			$expectation.find(".figure").text(expectationLength+confirmedLength);
			$usedLength.find(".figure").text(usedLength);
			$cancellation.find(".figure").text(cancellationLength);
			$all.find(".figure").text(expectationLength+usedLength+cancellationLength+confirmedLength);
		
			// menu bar 이벤트 등록
			$all.on("click",menuClickEvent.bind($all));
			$expectation.on("click",menuClickEvent.bind($expectation));
			$usedLength.on("click",menuClickEvent.bind($usedLength));
			$cancellation.on("click",menuClickEvent.bind($cancellation));
		}
	}
})();