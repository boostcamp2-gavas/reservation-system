$(document).ready(function() {
	$('.header').addClass('fade');
	
	$(':checkbox[id="chk3"]').change(function(){
		checkInfo();
	});
	

});
var countQty = 0;
var maximun = 10; // maximum 10이라 가정

class QtyCount extends eg.Component {
<<<<<<< HEAD
	constructor(qty) {
		super();
		this.qty = qty;
		}
	minus(obj, index, price, max) {
=======
	constructor(info) {
		super();
		this.qty = info.qty;
		this.index = info.index;
		this.price = info.price;
		this.max = info.max;
		
		this.on("cntQty", this.cntQty);
		}
	minus(obj) {
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
		if(this.qty == 1) {
			obj.addClass('disabled');
			obj.next().addClass('disabled');
			obj.parent().siblings().removeClass('on_color');
		}
		else if(this.qty == 0) {
			return;
		}
<<<<<<< HEAD
		else if(this.qty == max) {
=======
		else if(this.qty == this.max) {
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
			obj.nextAll().removeClass('disabled');
		}
		
		this.qty--;
		obj.next().val(this.qty);
<<<<<<< HEAD
		$('.total_price:eq('+index+')').html((this.qty * price).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
		countQty--;
		
		this.on("cntQty", this.cntQty);
		this.trigger("cntQty");
	}
	plus(obj, index, price, max) {
=======
		$('.total_price:eq('+this.index+')').html((this.qty * this.price).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
		countQty--;
		
		this.trigger("cntQty");
	}
	plus(obj) {
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
		if(this.qty == 0) {
			obj.prevAll().removeClass('disabled');
			obj.parent().siblings().addClass('on_color');
		}
<<<<<<< HEAD
		else if(this.qty == max-1) {
			obj.addClass('disabled');
		}
		else if(this.qty == max) {
=======
		else if(this.qty == this.max-1) {
			obj.addClass('disabled');
		}
		else if(this.qty == this.max) {
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
			return;
		}
		
		this.qty++;
		obj.prev().val(this.qty);
<<<<<<< HEAD
		$('.total_price:eq('+index+')').html((this.qty * price).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
		countQty++;
		
		this.on("cntQty", this.cntQty);
=======
		$('.total_price:eq('+this.index+')').html((this.qty * this.price).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
		countQty++;
		
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
		this.trigger("cntQty");
	}
	cntQty() {
		$('#qty_count').html(countQty);
<<<<<<< HEAD
=======
		$('#ticket_count_'+this.index).val(this.qty);
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
	}
};

var qtyCount = [];

manageQty();
function manageQty() {
	for(var i=0; i<$('.qty').length; i++) {
<<<<<<< HEAD
		qtyCount[i] = new QtyCount(0);
=======
		qtyCount[i] = new QtyCount({
			qty : 0,
			index : i,
			price : $('.price:eq('+i+')').data('price'),
			max : maximun
		});
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
	}
};

$('.ico_minus3').click(function(){
	event.preventDefault(); 
	var index = $(".qty .ico_minus3").index($(this));
<<<<<<< HEAD
	var $price = $('.price:eq('+index+')').data('price');
	
	qtyCount[index].minus($(this), index, $price, maximun);
=======
	
	qtyCount[index].minus($(this));
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
});
$('.ico_plus3').click(function(){
	event.preventDefault(); 
	var index = $(".qty .ico_plus3").index($(this));
<<<<<<< HEAD
	var $price = $('.price:eq('+index+')').data('price');
	
	qtyCount[index].plus($(this), index, $price, maximun);
=======
	
	qtyCount[index].plus($(this));
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
});

function checkInfo() {
	var userName = $('#name').val();
	var userTel = $('#tel').val();
	var userEmail = $('#email').val();

	var telTest = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
	var emailTest = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	
	if(userName === '') {
		alert('예매자를 입력해주세요.');
		$('#name').focus();
	}
	else if(userTel === '') {
		alert('연락처를 입력해주세요.');
		$('#tel').focus();
	}
	else if(!telTest.test(userTel)) {
	    alert("잘못된 휴대폰 번호입니다. 숫자, - 를 포함한 숫자만 입력하세요.");
	    $('#tel').focus();
	}
	else if(!emailTest.test(userEmail)) {
		alert("잘못된 이메일입니다. 다시 입력해주세요.");
		$('#email').focus();
	}
	else {
		if($('#chk3').is(':checked')) 
			$('.bk_btn_wrap').removeClass('disable');
		else
			$('.bk_btn_wrap').addClass('disable');
		return;
	}
	$('#chk3').prop('checked', false);
}

$('.btn_agreement').click(function(){
	event.preventDefault(); 
	var index = $(".agreement .btn_agreement").index($(this));
	index++;
	$('.agreement:eq('+index+')').addClass('open');
});

$('.bk_btn').click(function(){
<<<<<<< HEAD
	var $countInfo = "";
	
	for(var i=0; i<$('.qty').length; i++) {
		$countInfo += $('.count_control_input:eq('+i+')').val() + '-';
	}
	
	$('#count_info').val($countInfo);
	$('.form_horizontal').attr('action', '/reserve?productId='+$('#productId').val());
	$('.form_horizontal').submit();
})
=======
//	var reservationInfo = JSON.stringify($(".form_horizontal").serializeObject());
	var obj = {
			productId : $('#productId').val(),
			reservationName : "test",
			generalTicketCount : $('#ticket_count_0').val(),
			youthTicketCount : $('#ticket_count_1').val(),
			childTicketCount : $('#ticket_count_2').val(),
			reservationName : $('#name').val(),
			reservationTel : $('#tel').val(),
			reservationEmail : $('#email').val(),
			reservationType : 0
	}
	var reservationInfo = JSON.stringify(obj);

	$.ajax({
		type : "POST",
		url : $(".form_horizontal").attr('action'),
		data : reservationInfo,
		contentType : "application/json",
		success : function(data){
			location.href='/mvMyPage';
		},
		error : function(request,status,error){
			alert("code:"+request.status+"\n"+"error:"+error);
		}
	});
})

>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
