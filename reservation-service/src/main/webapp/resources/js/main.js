$(function() {
	
	Rolling.setting();
	Rolling.setRollings();

	category.setting();
	category.manageCategory();
	
	ProductList.setting();
	ProductList.getProducInfo(false, 0, 0);
	
	
	$('.imgBtn ').click(function() {
		var element = $(this).children();

		Rolling.clear();
		if(element.hasClass('nxt_inn')) {
			Rolling.rollingNxt();
		}
		else {
			Rolling.rollingPre();
		}
		Rolling.setTimerID(setTimeout(Rolling.autoRolling, 2000));
	});

	$('.cate_list').click(function(){
		$('#currentCategory').val($(this).data('category'));
		$('#moreCnt').val(0);
		
		$('#btnMore').css('display', 'block');
		category.addActiveClass($(this));
		ProductList.getProducInfo(false, $('#currentCategory').val(), 0);
	});
	
	function getMoreInfo() {
		var moreCnt = parseInt($('#moreCnt').val());
		moreCnt += 1;
		$('#moreCnt').val(moreCnt);

		ProductList.getProducInfo(true, $('#currentCategory').val(), moreCnt-1);
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

