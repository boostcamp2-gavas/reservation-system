$(function() {
	
<<<<<<< HEAD
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
	
=======
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
	
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
	function getMoreInfo() {
		var moreCnt = parseInt($('#moreCnt').val());
		moreCnt += 1;
		$('#moreCnt').val(moreCnt);

<<<<<<< HEAD
		getProducts.getProducInfo(true, $('#currentCategory').val(), moreCnt-1);
=======
		ProductList.getProducInfo(true, $('#currentCategory').val(), moreCnt-1);
>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
	}
	
	$('.btnMore').click(function(){
		getMoreInfo();
	})
	
	$(window).scroll(function() {
<<<<<<< HEAD
	    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
	    	getMoreInfo();
	    }
	});
	
});
=======
        if(ProductList.getGettingSatus()) {
            return;
        }
		var maxHeight = $(document).height();
		var currentScroll = $(window).scrollTop() + $(window).height();
		if(maxHeight - currentScroll < 30) {
			if(!$('.btnMore').hasClass('invisible')) {getMoreInfo();}
		}
	});
	
});

>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
