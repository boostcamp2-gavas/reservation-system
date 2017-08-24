

define(['jquery','component','js/util'], function( $, Component, util) {
	var Slider = util.extend(Component,{

		"init" : function (id, option) {
			this.option = option;
			this.option.max = option.max * -1;
			this.currentIndex = 0;
			this.moveRange = 338;
			this.id = id;
			this.eventBind(id);
		},

		eventBind:function(){
			$(this.id).on("click",".prev_e",this.prev.bind(this));
			$(this.id).on("click",".nxt_e",this.next.bind(this));
		},

		prev:function(id){
			if(this.currentIndex < 0){
				this.currentIndex++;
				$(this.id).find(".visual_img").animate({"left": (this.currentIndex * this.moveRange)+"px"},(function(){
					this.trigger("change",{
						index : this.currentIndex * -1
					});
				}).bind(this));	
			}
		},

		next:function(){
			if(this.currentIndex > this.option.max+1){
				this.currentIndex--;	
				$(this.id).find(".visual_img").animate({"left": (this.currentIndex * this.moveRange)+"px"},(function(){
					this.trigger("change",{
						index : this.currentIndex * -1
					});
				}).bind(this));	
			}
			
		}
	});

	return Slider;
});
