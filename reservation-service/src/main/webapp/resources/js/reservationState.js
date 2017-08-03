
function MenuBar($item){
	this.$item = $item;
	this.$item.on("click",this.menuClickEvent.bind(this));
}

MenuBar.prototype = new eg.Component;
MenuBar.prototype.constructor = MenuBar;

MenuBar.prototype.menuClickEvent = 	function menuClickEvent(event){
	event.preventDefault();
	$(".link_summary_board ").not(this.$item).removeClass("on");
	this.$item.addClass("on");
	// trigger을 써야되나 ?
};

var templateSource = $("#reservation-content").html(),
Template = Handlebars.compile(templateSource);

function MainContents($card){
	this.$card = $("."+$card);
	this.type = this.$card.data("type");
	this.loadContents.call(this);
}

MainContents.prototype = new eg.Component;
MainContents.prototype.contructor = MainContents;

MainContents.prototype.loadContents = function(){
	var $card = this.$card;
	console.log($card);
	$.ajax({
		method : "GET",
		url : "/api/reservation/type/"+this.type
	}).done(function(data) {
		
		if (data.length !== 0) {
			var item = {
					reservation : [],
					menubar : [{ menubar : _menubar, icon : _icon}]
			};
			for (var i = 0, max = data.length; i < max; ++i) {
				item.reservation.push(data[i]);
			}
			//expectationLength += max ;
			var html = Template(item);
			$card.append(html);
		}
	});

}





/*var ReservationState = (function(){
	// 이 부분은 Hendlebar 설정 부분 
	var templateSource = $("#reservation-content").html(),
	Template = Handlebars.compile(templateSource);
	
	// 상단 bar의 contents들을 저장하고 있음. 
	var $all = $(".link_summary_board").eq(0),
	$expectation =$(".link_summary_board ").eq(1),
	$usedLength = $(".link_summary_board ").eq(2),
	$cancellation = $(".link_summary_board").eq(3);
	
	// li 부분을 들고 있음 
	var $allCards = $(".card"),
	$usedCard = $(".used:first"),
	$expectationCard = $(".expectation, .confirmed"),
	$cancellationCard = $(".cancellation");

	var $article =null;
	var $cardDetail ;
	var $cardHeader;

	// enum 같은 느낌으로 사용.
	// ENUM 을 이렇게 사용하는게 맞는지 의문이 생김.

	
	// 취소하겠냐는 확인을 보여주는 event 
	function confirmCancellationBtn(event){
		event.preventDefault();
		var reservation_info =0,
		$popoup;
		
		$cardDetail = $(this).parents(".card_detail");
		$article = $cardDetail.parents("article");
		
		reservation_info = $cardDetail.data("id");
		$popoup = $(".popup_booking_wrapper");

		$popoup.removeClass("none");
		$popoup.data("id",reservation_info);
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
			
			$(".popup_booking_wrapper").addClass("none");
			$article.remove();	
		
			// count 변경 및 cancellation 가져오는 작업 
			expectationCount = $expectation.find(".figure").text();
			$expectation.find(".figure").text(--expectationCount);
			$('.used:last').children().remove();
			loading(3,$('.used:last'),"취소된 예약","ico_cancel","");
			cancellationCount = $cancellation.find(".figure").text();
			$cancellation.find(".figure").text(++cancellationCount);
			
			alert("취소 되었습니다.");
		}else{
			alert("예상치 못한 에러가 ...");
		}
	}
	
	
	function loading(type,$card,_menubar,_icon,_btns){
		
		$.ajax({
			method : "GET",
			url : "/api/reservation/type/"+type
		}).done(function(data) {
			if (data.length !== 0) {
				var item = {
						reservation : [],
						menubar : [{ menubar : _menubar, icon : _icon}]
				};
				for (var i = 0, max = data.length; i < max; ++i) {
					data[i].btns = _btns
					item.reservation.push(data[i]);
				}
				//expectationLength += max ;
				var html = Template(item);
				$card.append(html);
			}
		});
	}
	
	
	

	
	return{
		
		init : function(){
			
			// ajax 값 호출 
			// 여기 까진 만족 ok 
			loading(0,$('.expectation'),"예약 신청중","ico_clock","취소");
			loading(1,$('.confirmed'),"예약 확정","ico_check2","취소");
			loading(2,$('.used:first'),"이용 완료","ico_check2","예매자 리뷰 남기기");
			loading(3,$('.used:last'),"취소된 예약","ico_cancel","");
			
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
			});
			$(".btn_green").on("click",cancellationBtn);
			$(".expectation, .confirmed").on("click",".booking_cancel .btn",confirmCancellationBtn);
			
			$(".used:first").on("click",".booking_cancel .btn",function(event){
				// 이동하기 
				event.preventDefault();
				var reservation_info =0;
				$cardDetail = $(this).parents(".card_detail");
				reservation_info = $cardDetail.data("id");
				location.href = "/product/"+reservation_info+"/review-write";
			});
		},
		isEmpty : function(){
			var txt = $all.find(".figure:first").text();
			if(parseInt(txt)===0){
				$(".err").removeClass("none");
			}
		}
		
	}
})();*/
