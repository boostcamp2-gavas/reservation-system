$(document).ready(function() {
	$('.header').addClass('fade');
	
	$(':checkbox[id="chk3"]').change(function(){
		checkInfo();
	});
	

});
var countQty = 0;
var maximun = 10; // maximum 10이라 가정

class QtyCount extends eg.Component {
	constructor(info) {
		super();
		this.qty = info.qty;
		this.index = info.index;
		this.price = info.price;
		this.max = info.max;
		
		this.on("cntQty", this.cntQty);
		}
	minus(obj) {
		if(this.qty == 1) {
			obj.addClass('disabled');
			obj.next().addClass('disabled');
			obj.parent().siblings().removeClass('on_color');
		}
		else if(this.qty == 0) {
			return;
		}
		else if(this.qty == this.max) {
			obj.nextAll().removeClass('disabled');
		}
		
		this.qty--;
		obj.next().val(this.qty);
		$('.total_price:eq('+this.index+')').html((this.qty * this.price).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
		countQty--;
		
		this.trigger("cntQty");
	}
	plus(obj) {
		if(this.qty == 0) {
			obj.prevAll().removeClass('disabled');
			obj.parent().siblings().addClass('on_color');
		}
		else if(this.qty == this.max-1) {
			obj.addClass('disabled');
		}
		else if(this.qty == this.max) {
			return;
		}
		
		this.qty++;
		obj.prev().val(this.qty);
		$('.total_price:eq('+this.index+')').html((this.qty * this.price).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
		countQty++;
		
		this.trigger("cntQty");
	}
	cntQty() {
		$('#qty_count').html(countQty);
		$('#ticket_count_'+this.index).val(this.qty);
	}
};

var qtyCount = [];

manageQty();
function manageQty() {
	for(var i=0; i<$('.qty').length; i++) {
		qtyCount[i] = new QtyCount({
			qty : 0,
			index : i,
			price : $('.price:eq('+i+')').data('price'),
			max : maximun
		});
	}
};

$('.ico_minus3').click(function(){
	event.preventDefault(); 
	var index = $(".qty .ico_minus3").index($(this));
	
	qtyCount[index].minus($(this));
});
$('.ico_plus3').click(function(){
	event.preventDefault(); 
	var index = $(".qty .ico_plus3").index($(this));
	
	qtyCount[index].plus($(this));
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
	$('.form_horizontal').attr('action', '/reserve?productId='+$('#productId').val());
	$('.form_horizontal').submit();
})