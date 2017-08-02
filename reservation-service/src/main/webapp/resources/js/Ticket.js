/**
 * 
 */
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
		this.$qty = $qty;
		this.$text = $qty.find(".count_control_input[type = tel]");
		this.$total_price = $qty.find(".total_price");
		this.total_price =parseInt(this.$total_price.text());
		this.price = parseInt($qty.find(".price").text().replace(/[^\d]+/g,''));
		this.count = 0;
		
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
	};
	
	Ticket.prototype.minus = function(){
		--this.count;
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
	

