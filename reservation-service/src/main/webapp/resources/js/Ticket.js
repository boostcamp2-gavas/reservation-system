/**
 * 
 */
<<<<<<< HEAD

	function Ticket($qty,price){
=======
$(function(){
	
	var $qty = $(".qty");
	
	var ticketArray = $qty.map(function(i,v){
		var ticket = new Ticket($(v));
		ticket.on("change",function(){
			var totalCount = 0;
			ticketArray.each(function(i,v){
				totalCount += v.getCount();
			});
			console.log(totalCount);
			$(".tickat_count").text(totalCount);
		});
		return ticket;
	});
});

	function Ticket($qty){
		// 여기에 on을 등록하는 방향으로 해도 됨 
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a
		this.$qty = $qty;
		this.$text = $qty.find(".count_control_input[type = tel]");
		this.$total_price = $qty.find(".total_price");
		this.total_price =parseInt(this.$total_price.text());
		this.price = parseInt($qty.find(".price").text().replace(/[^\d]+/g,''));
		this.count = 0;
<<<<<<< HEAD
	}		
	
	Ticket.prototype = new	eg.Component();		
	Ticket.prototype.constructor =	Ticket;	
	
	Ticket.prototype.totalCount = 0;

	Ticket.prototype.init = function(){
		this.on("plus",this.plus);
		this.on("minus",this.minus);
	};
	
	Ticket.prototype.changeTotal = function(){
		$(".tickat_count").text(this.totalCount);
=======
		
		// function 을 재정의 하여 구현하는 방법이 존재.
		// 이렇게 구현할 경우, 생성될때마다 eg를 받아야하는 단점이 존재한다. 
		// 이렇게 사용하는 것보단 extends를 사용하는게 좋음 
		
		var component = new eg.Component();
		this.on = function(name,fp){
			component.on(name,fp);
		}
		
		this.off = function(name,fp){
			component.off(name,fp);
		}
		
		this.trigger = function(name,option){
			component.trigger(name,option);
		}
		
		this.$qty.on("click",".ico_plus3",this.plus.bind(this));
		this.$qty.on("click",".ico_minus3",this.minus.bind(this));
		
	}		
	
		
	Ticket.prototype.constructor =	Ticket;	
	
	Ticket.prototype.changeText = function(){
		this.$text.val(this.count);
		if(this.count !== 0){
			this.$qty.find(".ico_minus3").removeClass("disabled");
		}else{
			this.$qty.find(".ico_minus3").addClass("disabled");
		}
>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a
	};
	
	Ticket.prototype.minus = function(){
		--this.count;
<<<<<<< HEAD
		// 이렇게 안하고 this.~ 으로 하면 객체 내부에 totalCount를 선언해버림 
		--Ticket.prototype.totalCount
		this.$text.val(this.count);
		this.$total_price.text(this.total_price-=this.price);
		if(this.count ===0){
			this.$qty.find(".ico_minus3").addClass("disabled");
		}
		this.changeTotal();
	};	
	
	Ticket.prototype.plus = function(){	
		Ticket.prototype.totalCount++
		++this.count
		this.$text.val(this.count);
		this.$total_price.text(this.total_price+=this.price);
		if(this.count !==0){
			this.$qty.find(".ico_minus3").removeClass("disabled");
		}
		this.changeTotal();
	};	
	
	var TicketModule = (function(){
		var Ticket = [];
		return {
			minus : function(){
				if($(this).hasClass("disabled")){
					return;
				}
				var index = $(".qty .ico_minus3").index($(this));
				Ticket[index].trigger("minus");
			},
			plus : function(){
				if($(this).hasClass("disabled")){
					return;
				}
				var index = $(".qty .ico_plus3").index($(this));
				Ticket[index].trigger("plus");
			},
			init: function(ticket){
				Ticket = ticket;
				// 이벤트 init 
				ticket[0].init();
				$(".ico_minus3").on("click",this.minus);
				$(".ico_plus3").on("click",this.plus);
			}
		}
	})();
=======
		if(this.count ===-1){
			++this.count;
			return;
		}
		--Ticket.prototype.totalCount;
		this.$total_price.text(this.total_price-=this.price);
		this.changeText();
		
		this.trigger("change");
	};	
	
	Ticket.prototype.plus = function(){	
		++Ticket.prototype.totalCount;
		++this.count;
		this.$total_price.text(this.total_price+=this.price);
		this.changeText();
		this.trigger("change");
	};	
	
	Ticket.prototype.getCount = function(){
		return this.count;
	}
	

>>>>>>> 0d95395487ea32084ee49af481f7933ef7c9a78a
