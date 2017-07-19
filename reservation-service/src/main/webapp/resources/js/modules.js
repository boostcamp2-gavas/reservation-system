var bannerModule = (function(ul) {
	var setRolling=0,
	 	timeCheck=0,
		bannerList,
	    cnt = 0,
		size,
		len,
		$ul = ul;
	$ul.css('width', len*size);


	return {
		init : function(newUl) {
			$ul = newUl;
			bannerModule.setting();
		},
		setting : function() {
			bannerList = $ul.find('li');
			size = bannerList.outerWidth();
			len = bannerList.length;
		},
		bannerRolling_nxt : function() {	// 오른쪽
			cnt++;
			if(cnt > len){
				cnt = 1;
			}
			var move = cnt%len;
			bannerList.animate({'left': -(move*size)+'px'}, 'normal');
		},
		bannerRolling_pre : function() {	// 왼쪽
			cnt--;
			if(cnt <= 0){
				cnt = 3;
			}
			var move = cnt%len;
			bannerList.animate({'left': -(move*size)+'px'}, 'normal');
		},
		test : function autoRolling() {
			// 상단 배너 자동 롤
			setRolling = setInterval(bannerModule.bannerRolling_nxt, 2000);
		},
		clear :function() {
			// 이벤트 해제
			clearInterval(setRolling);
			clearTimeout(timeCheck);
		},
		setRollings : function(){
			setRolling = setInterval(bannerModule.bannerRolling_nxt, 2000);
		},
		setTimecheck:function(value){
			timeCheck = value;
		}
		
	};
})($('.visual_img'));


var categoryModule = (function(list) {
	var firstCategory = list.first().find('a');
	var lastCategory = list.last().find('a');
	
	return {
		manageCategory : function() {
			firstCategory.addClass('active');
			lastCategory.addClass('last');
		},
		addActiveClass : function(obj) {
			var element = obj.find('a'); 
			element.addClass('active');
			
			obj.siblings().find('a').removeClass('active');
			
			if(element.hasClass('last')){
				element.removeClass('last')
			}
			else {
				lastCategory.addClass('last');
			}
		}
	};
})($('.cate_list'));


var productListModule = (function(countArea, ul) {
	var start = 0;
	var categoryFlag = false;
	var countData= countArea;
	var handlebarTemplate = ul.find('script');
	
	return {
		getProducInfo : function(flag, categoryId, start) {
			if(flag == false)	// '더보기'가 아닐 경우 0번째부터 가져옴
				start = 0;
			else
				start++;

			var urlInfo = "/productInfo";
			var dataInfo = "";

			if(categoryId == 0 || categoryId == undefined) {	// 전체보기 상품
				urlInfo += "/all/"+start;
				dataInfo = "start="+start;
			}
			else {	// 카테고리별 상품
				urlInfo += "/category/"+categoryId+"/start/"+start;
				dataInfo = "categoryId="+categoryId+"&start="+start;
			}
			
			$.ajax({
			    url : urlInfo,
			    type : "GET",
			    data : dataInfo,
			    success: function(data) {
			    	productListModule.countProduct(data.productCount);
			    	
			    	if(data.productList.length == 0)
			    		alert("더이상 상품이 없습니다!");
			    	else 
			    		productListModule.productAppend(flag, data.productList);
			    },
			    error:function(request,status,error){
			        alert("code:"+request.status+"\n"+"error:"+error);
				}
			 
			}); 	
		},
		countProduct : function(count) {
			countData.html(count+'개');
		},
		productAppend : function(flag, result) {
			var source = handlebarTemplate.html();
			var template = Handlebars.compile(source);
			
			if(flag == false)	// '더보기'가 아닐 경우 리스트 비움
				ul.find('li').remove();
			
			$.each(result, function(index, product){
				var data = {productList : product};
				var html = template(data);
				
				if(index % 2 == 0)
					ul.first().append(html);
				else // 짝수
					ul.last().append(html);
			});	
		}
	};
})($('.event_lst_txt .pink'), $('.lst_event_box'));



