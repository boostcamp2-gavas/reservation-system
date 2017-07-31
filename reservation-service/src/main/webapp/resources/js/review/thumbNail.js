var ThumbApp = (function() {
	var	x = 0;
	var point = 0;
	var imgCount = 0;
	var template;
	var moveGapX;
	var touchStartPointX;
	function init() {
		template = Handlebars.compile($('#popup_layer_template').html());
		bindEvents();
	}
	function bindEvents() {
		$('.thumb').on('click', popupViewer);
		$('#layer')
			.on('click', '.com_img_btn.close', hideViewer)
			.on('click', '.com_img_btn.nxt', clickNext)
			.on('click', '.com_img_btn.prev', clickPrev);
		$('#photoviewer')
			.on('click', toggleBtnVisible)
			.on('touchstart', '.wrapper img', viewerTouchStart)
			.on('touchmove', '.wrapper img', viewerTouchMove)
			.on('touchend', '.wrapper img', viewerTouchEnd);
	}
	function hideViewer(e) {
		e.stopPropagation();
		$(e.target).parents('#photoviewer').hide();
	}
	function clickNext(e) {
		e.stopPropagation();
		$('.sub_layer').removeClass('touch');
		next();
	}
	function clickPrev(e) {
		e.stopPropagation();
		$('.sub_layer').removeClass('touch');
		prev();
	}
	function next() {
		if(point >= imgCount - 1) { return; }
		x = ++point;
		translateLayer(x);
	}
	function prev() {
		if(point <= 0) { return; }
		x = --point;
		translateLayer(x);
	}
	function translateLayer(x) {
		$.each($('.sub_layer'), function() {
			var t = 100 * x--;
			$(this).css('transform', 'translateX(' + (-t) + '%)');
		});
	}
	function toggleBtnVisible() {
		$('.btn_wrapper').toggleClass('invisible');
	} 
	$(window).resize(function() {
		var winHeight = $(window).height();
		var winWidth = $(window).width();
		$.each($('.wrapper img'), function() {
			$(this).css({
				'max-height': winHeight,
				'max-width': winWidth
			});
			$(this).parent().css({
				'height': winHeight,
				'width': winWidth,
				'margin-top': -this.height / 2,
				'margin-left': -this.width / 2
			});
		});
		imgCount = $('.sub_layer').length;
	});
	function popupViewer(e){
		e.preventDefault();
		point = 0;
		var url = '/productInfo/commentImage?commentId=' + $(e.target).data('comment-id');
		callAjax(url).then(imgLoad);
        $('#photoviewer').fadeTo("fast",1);    
	}
	function imgLoad(res) {
		var arr = res.imageList.map(function(v, i) {
    		return {
    			fileId: v.fileId,
    			tranx: i * 100
    		}
    	});
    	var data = {items: arr}
    	$('#layer').html(template(data));
    	$('.wrapper img').on('load', function(){
    		$(window).resize();
    	});
	}
	function viewerTouchStart(e) {
		toggleBtnVisible();
		$('.sub_layer').addClass('touch');
		var event = e.originalEvent;
		moveGapX = 0;
		touchStartPointX = event.touches[0].screenX;
		e.preventDefault();
	}
	function viewerTouchMove(e) {
		var event = e.originalEvent;
		$(e.target).css('transform', 'translateX(' + -moveGapX + 'px)');
		moveGapX = touchStartPointX - event.touches[0].screenX;
		e.preventDefault();
	}
	function viewerTouchEnd(e) {
		if(moveGapX < -50) {
			$('.com_img_btn').hide();
			prev();
		}
		if(moveGapX > 50) {
			$('.com_img_btn').hide();
			next();
		}
		if(moveGapX == 0) {
			$('.com_img_btn').show();
		}
		$(e.target).css('transform', 'translateX(0px)');
		e.preventDefault();
	}
	$('#layer').on('click', function(e) {
		$('.com_img_btn').show();
	});
	return {
		init: init
	}
})();