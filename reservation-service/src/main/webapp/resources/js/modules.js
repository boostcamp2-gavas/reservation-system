<<<<<<< HEAD
var rolling = (function(ul) {
	var setRolling=0,
	 	timeCheck=0,
		rollingList,
	    cnt = 0,
		size,
		len,
		$ul = ul;
	$ul.css('width', len*size);


	return {
		init : function(newUl) {
			$ul = newUl;
			rolling.setting();
		},
		setting : function() {
			rollingList = $ul.find('li');
			size = rollingList.outerWidth();
			len = rollingList.length;
		},
		rollingNxt : function() {	// 오른쪽
			cnt++;
			if(cnt > len){
				cnt = 1;
			}
			var move = cnt%len;
			rollingList.animate({'left': -(move*size)+'px'}, 'normal');
		},
		rollingPre : function() {	// 왼쪽
			cnt--;
			if(cnt <= 0){
				cnt = 3;
			}
			var move = cnt%len;
			rollingList.animate({'left': -(move*size)+'px'}, 'normal');
		},
		autoRolling : function() {
			// 상단 배너 자동 롤
			setRolling = setInterval(rolling.rollingNxt, 2000);
		},
		clear :function() {
			// 이벤트 해제
			clearInterval(setRolling);
			clearTimeout(timeCheck);
		},
		setRollings : function(){
			setRolling = setInterval(rolling.rollingNxt, 2000);
		},
		setTimerID:function(value){
			timeCheck = value;
		}
		
	};
})($('.visual_img'));


var category = (function(list) {
	var firstCategory,
		lastCategory;
	
	return {
		setting : function() {
			firstCategory = list.first().find('a');
			lastCategory = list.last().find('a');
		},
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


var getProducts = (function(countArea, ul) {
	var start,
		categoryFlag,
		countData,
		handlebarTemplate;
	
	return {
		setting : function() {
			start = 0;
			categoryFlag = false;
			countData= countArea;
			handlebarTemplate = ul.find('script');
		},
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
			    	getProducts.countProduct(data.productCount);
			    	
			    	if(data.productList.length == 0)
			    		alert("더이상 상품이 없습니다!");
			    	else 
			    		getProducts.productAppend(flag, data.productList);
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



=======
var Rolling = (function(ul) {
	var setRolling=0,
	 	timeCheck=0,
		rollingList,
	    cnt = 0,
		size,
		len,
		$ul = ul;
	$ul.css('width', len*size);


	return {
		init : function(newUl) {
			$ul = newUl;
			Rolling.setting();
		},
		setting : function() {
			rollingList = $ul.find('li');
			size = rollingList.outerWidth();
			len = rollingList.length;
		},
		rollingNxt : function() {	// 오른쪽
			cnt++;
			if(cnt > len){
				cnt = 1;
			}
			var move = cnt%len;
			rollingList.animate({'left': -(move*size)+'px'}, 'normal');
		},
		rollingPre : function() {	// 왼쪽
			cnt--;
			if(cnt <= 0){
				cnt = 3;
			}
			var move = cnt%len;
			rollingList.animate({'left': -(move*size)+'px'}, 'normal');
		},
		autoRolling : function() {
			// 상단 배너 자동 롤
			setRolling = setInterval(Rolling.rollingNxt, 2000);
		},
		clear :function() {
			// 이벤트 해제
			clearInterval(setRolling);
			clearTimeout(timeCheck);
		},
		setRollings : function(){
			setRolling = setInterval(Rolling.rollingNxt, 2000);
		},
		setTimerID:function(value){
			timeCheck = value;
		}
		
	};
})($('.visual_img'));


var category = (function(list) {
	var firstCategory,
		lastCategory;
	
	return {
		setting : function() {
			firstCategory = list.first().find('a');
			lastCategory = list.last().find('a');
		},
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


var ProductList = (function(countArea, ul) {
	var start,
		categoryFlag,
		countData,
		handlebarTemplate,
        gettingInfo;
	
	return {
		setting : function() {
			start = 0;
			categoryFlag = false;
			countData= countArea;
			handlebarTemplate = ul.find('script');
            gettingInfo = false;
		},
		getProducInfo : function(flag, categoryId, start) {
			if(flag == false)	// '더보기'가 아닐 경우 0번째부터 가져옴
				start = 0;
			else
				start++;

			var urlInfo = "/api/productInfo";
			var dataInfo = "";

			if(categoryId == 0 || categoryId == undefined) {	// 전체보기 상품
				urlInfo += "/all?start="+start;
				dataInfo = "start="+start;
			}
			else {	// 카테고리별 상품
				urlInfo += "/category?categoryId="+categoryId+"&start="+start;
				dataInfo = "categoryId="+categoryId+"&start="+start;
			}
            gettingInfo = true;
			$.ajax({
			    url : urlInfo,
			    type : "GET",
			    data : dataInfo,
			    success: function(data) {
			    	ProductList.countProduct(data.productCount);
			    	
			    	if(data.productList.length != 0) { 
			    		ProductList.productAppend(flag, data.productList);
			    	}

			    	var str = $('.event_lst_txt .pink').text();
                    var val = parseInt(str.replace('개',''));
                    var moreCnt = parseInt($('#moreCnt').val());
                    if(parseInt(val / 10) <= moreCnt) {
                        $('.btnMore').addClass('invisible');
                    } else {
                        $('.btnMore').removeClass('invisible');
                    }
                    gettingInfo = false;
			    },
			    error:function(request,status,error){
			        alert("code:"+request.status+"\n"+"error:"+error);
                    gettingInfo = false;
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
		},
        getGettingSatus : function() {
            return gettingInfo;
        }
	};
})($('.event_lst_txt .pink'), $('.lst_event_box'));



>>>>>>> 675e75dfc3b5ee0e722079d046479cafa81aa8d7
