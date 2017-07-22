$(function() {
	
	rolling.setting();
	rolling.setRollings();

	category.setting();
	category.manageCategory();
	
	getProducts.setting();
	getProducts.getProducInfo(false, 0, 0);
	
	
	$('.imgBtn ').click(function() {
		var element = $(this).children();
		var btn = element.attr('class');

		rolling.clear();
		if(btn == 'nxt_inn') {
			rolling.rollingNxt();
		}
		else {
			rolling.rollingPre();
		}
		rolling.setTimerID(setTimeout(rolling.autoRolling, 2000));
	});

	$('.cate_list').click(function(){
		$('#currentCategory').val($(this).data('category'));
		$('#moreCnt').val(0);
		
		category.addActiveClass($(this));
		getProducts.getProducInfo(false, $('#currentCategory').val(), 0);
	});
	
	function getMoreInfo() {
		var moreCnt = parseInt($('#moreCnt').val());
		moreCnt += 1;
		$('#moreCnt').val(moreCnt);

		getProducts.getProducInfo(true, $('#currentCategory').val(), moreCnt-1);
	}
	
	$('.btnMore').click(function(){
		getMoreInfo();
	})
	
	$(window).scroll(function() {
	    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
	    	getMoreInfo();
	    }
	});
	
});
