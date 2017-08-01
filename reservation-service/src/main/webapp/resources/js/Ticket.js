/**
 * 
 */
$(function(){
	
	var $qty = $(".qty");
	
	var ticketArray = $qty.map(function(i,v){
		return new Ticket($(v));
	});
	
	ticketArray[0].on("change",function(){
		var totalCount = 0;
		ticketArray.each(function(i,v){
			totalCount += v.getCount();
		});
		$(".tickat_count").text(totalCount);
	});
	

	
});

	function Ticket($qty){
		// 여기에 on을 등록하는 방향으로 해도 됨 
		this.$qty = $qty;
		this.$text = $qty.find(".count_control_input[type = tel]");
		this.$total_price = $qty.find(".total_price");
		this.total_price =parseInt(this.$total_price.text());
		this.price = parseInt($qty.find(".price").text().replace(/[^\d]+/g,''));
		this.count = 0;
		
		this.$qty.on("click",".ico_plus3",this.plus.bind(this));
		this.$qty.on("click",".ico_minus3",this.minus.bind(this));
		
	}		
	
	Ticket.prototype = new	eg.Component();		
	Ticket.prototype.constructor =	Ticket;	
	
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
	

