/**
 * 
 */
$(function(){
	var $qty = $(".qty");
	
	var ticketArray = $qty.map(function(v,i){
		return new Ticket($(i));
	});
	
	ticketArray[0].on("plus",ticketArray[0].plus);
	ticketArray[0].on("minus",ticketArray[0].minus);
	
	TicketModule.init(ticketArray);
});

	function Ticket($qty){
		this.$qty = $qty;
		this.$text = $qty.find(".count_control_input[type = tel]");
		this.$total_price = $qty.find(".total_price");
		this.total_price =parseInt(this.$total_price.text());
		this.price = parseInt($qty.find(".price").text().replace(/[^\d]+/g,''));
		this.count = 0;
	}		
	
	Ticket.prototype = new	eg.Component();		
	Ticket.prototype.constructor =	Ticket;	
	
	Ticket.prototype.totalCount = 0;
	
	Ticket.prototype.changeCount = function(){
		this.changeTotal();
	}
	
	Ticket.prototype.changeText = function(){
		this.$text.val(this.count);
		$(".tickat_count").text(this.totalCount);
		if(this.count !== 0){
			this.$qty.find(".ico_minus3").removeClass("disabled");
		}else{
			this.$qty.find(".ico_minus3").addClass("disabled");
		}
	};
	
	Ticket.prototype.minus = function(){
		--Ticket.prototype.totalCount;
		--this.count;
		this.$total_price.text(this.total_price-=this.price);
		this.changeText();
	};	
	
	Ticket.prototype.plus = function(){	
		++Ticket.prototype.totalCount;
		++this.count
		this.$total_price.text(this.total_price+=this.price);
		this.changeText();
	};	
	
	var TicketModule = (function(){
		var ticket = [];
		return {
			minus : function(){
				if($(this).hasClass("disabled")){
					return;
				}
				var index = $(".qty .ico_minus3").index($(this));
				ticket[index].trigger("minus");
			},
			plus : function(){
				var index = $(".qty .ico_plus3").index($(this));
				ticket[index].trigger("plus");
			},
			init: function(ticketArray){
				ticket = ticketArray;
				$(".ico_plus3").on("click",this.plus);
				$(".ico_minus3").on("click",this.minus);
				
			}
		}
	})();
