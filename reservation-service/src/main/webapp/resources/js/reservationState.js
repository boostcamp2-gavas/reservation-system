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
	console.log(btn);
	 return btn;
});

var ReservationState = (function(){

	var templateSource = $("#reservation-content").html(),
	Template = Handlebars.compile(templateSource),
	expectationLength =0,
	confirmedLength = 0,
	usedLength =0,
	cancellationLength = 0;
	
	var $all = $(".figure").eq(0),
	$expectation =$(".figure").eq(1),
	$usedLength = $(".figure").eq(2),
	$cancellation = $(".figure").eq(3);
	
	function loading(type,$card,_menubar,_icon,_btns){
		var max = 0;
		// 받아온 data의 길이로 max를 설정하고, return 받기 위해  비동기를 막아 두었습니다. 
		$.ajax({
			method : "GET",
			url : "/reservation/type/"+type,
			  async: false
		}).done(function(data) {
			if (data.length === 0) {
				$(".err").remove();
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
	
	

	
	function menuClickEvent(eq){
		console.log($(".figure").not(eq));
		
	}
	
	return{
		init : function(){
			// ajax 값 호출 
			expectationLength =loading(0,$('.expectation'),"예약 신청중","ico_clock","취소");
			confirmedLength =loading(1,$('.confirmed'),"예약 확정","ico_check2","취소");
			usedLength =loading(2,$('.used:first'),"이용 완료","ico_check2","예매자 리뷰 남기기");
			cancellationLength =loading(3,$('.used:last'),"취소된 예약","ico_cancel","");
			
			// menu bar 초기화 
			$expectation.text(expectationLength);
			$usedLength.text(usedLength);
			$cancellation.text(cancellationLength);
			$all.text(expectationLength+usedLength+cancellationLength);
		
			// menu bar 이벤트 등록
			$all.on("click",function(){
				alert("test")
			});
			
		}
	}
})();