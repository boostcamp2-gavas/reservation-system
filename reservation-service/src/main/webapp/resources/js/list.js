

define(['jquery','handlebars','js/reservationmodel'], function( $, Handlebars,ReservationModel) {

	var activeIndex = 1;
	var page = 1;
	var source   = $("#event-template").html();
	var template = Handlebars.compile(source);
	ReservationModel.setInfo(activeIndex, page);

	function init(){
		$(".event_tab_lst a").on("click", check);
		$(window).on("scroll",scrollUpdate);

		ReservationModel.get(function(json){
			makeEvent("html",json);
		});
	}

	function scrollUpdate(){
		var top = $(".more .btn").offset().top;
		var scrollY = window.scrollY;
		var windowHeight = window.innerHeight;
		if(windowHeight + scrollY > top - 100){
			page++;
			ReservationModel.setInfo(activeIndex, page);
			ReservationModel.get(function(json){
				makeEvent("append",json);
			});
		}
	}

	function check(e){
		active(e.currentTarget);
	}

	function active(ele){
		$(".event_tab_lst li[data-category='"+activeIndex+"'] a").removeClass("active");
		$(ele).addClass("active");
		activeIndex = $(ele).closest("li").data("category");
		ReservationModel.setInfo(activeIndex, page);
		ReservationModel.get(function(json){
			makeEvent("html",json);
		})
	}

	function makeEvent(type, json){
		var left = [];
		var right = [];
		var data = json.data;
		for(var i = 0, l = data.length; i < l; i++){
			if(i%2){
				left.push(data[i]);
			}else{
				right.push(data[i]);
			}
		}
		$(".lst_event_box.box_left")[type](template({data:left}));
		$(".lst_event_box.box_right")[type](template({data:right}));

		$(".event_lst_txt span.pink").html(json.count+"개");
	}

	return {
		init:init,
		active : active
	}
    
});
