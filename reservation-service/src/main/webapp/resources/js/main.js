$(function() {
	
	bannerModule.setting();
	bannerModule.setRollings();

	categoryModule.manageCategory();
	productListModule.getProducInfo(false, 0, 0);
	
	
	$('.btnBanner ').click(function() {
		var element = $(this).children();
		var btn = element.attr('class')
		bannerModule.clear();
		
		if(btn == 'nxt_inn') {
			bannerModule.bannerRolling_nxt();
		}
		else {
			bannerModule.bannerRolling_pre();
		}
		bannerModule.setTimecheck(setTimeout(bannerModule.test, 2000));
	});

	$('.cate_list').click(function(){
		$('#currentCategory').val($(this).data('category'));
		$('#moreCnt').val(0);
		
		categoryModule.addActiveClass($(this));
		productListModule.getProducInfo(false, $('#currentCategory').val(), 0);
	});
	
	function getMoreInfo() {
		var moreCnt = Number($('#moreCnt').val());
		moreCnt += 1;
		$('#moreCnt').val(moreCnt);

		productListModule.getProducInfo(true, $('#currentCategory').val(), moreCnt-1);
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
