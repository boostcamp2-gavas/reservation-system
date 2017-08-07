/**
 * 
 */

	var DetailModule = (function(){
		var $btn =$(".bk_btn"), 
		config = $btn.data("config"),
		$span = $(".bk_btn > span");
		
		var productState  = function(id){
			if(!config){
				$span.text("예매하기");
				$btn.on("click",function(){
					location.href='/product/reservation/'+id;
				});
			}else if(config === 1){
				$span.text("매진");
			}else{
				$span.text("판매기간 종료");
			}
		}
		
		var scrollEvent  = function(){
			 if ($(window).scrollTop() >= $(document).height() - $(window).height()) {
				 var $img = $(".in_img_lst:last > .img_thumb");
				 var data =  $img.data("lazy-image");
				 $img.attr("src",data);
			 }
		 }
		
		var reviewMoreBtn = function(reviewCount){
			if(reviewCount<3){
				$(".btn_review_more").addClass('hide');
			}
		}
		
		return {
			init : function(reviewCount,id,score){
				productState(id);
				$(document).scroll(scrollEvent);
				reviewMoreBtn(reviewCount);
				$(".graph_value").css("width",(score * 20)+"%");
				$(".btn_review_more").on("click",function(event){
					event.preventDefault();
					location.href = "/product/"+getProductId()+"/review"
				});
			}
		}
	})();