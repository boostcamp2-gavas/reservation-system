$(document).ready(function() {
	
	var product = product || {};
	
	// 즉시 실행함수로 묶음.
	product = (function(){
		var category = {},
		values = {};
		id =0,
		start = 0,
		templateSource= {},
		leftTemplate =function(){};
		//func = funcion(){};
		
		// compile은 한번만 하면 된다.
		// compile를 한 부분을 올릴 수 도 있음. (handlebar 쪽에 method를 제공함)
		templateSource = $("#product-content").html();
		leftTemplate = Handlebars.compile(templateSource);
		
		return {
			countEvent : function(){
				id = $(this).data("category") || 0;
				$.ajax({
					method : "GET",
					url : "/product/counter/" + id,
					contentType : "application/json; charset=utf-8",
					dataType : "json",
				}).done(function(data) {
					$("span.pink").text(data);
				});
			},
			productEven : function() {
				id = $(this).data("category") || 0;
				start = 0;
				$('.lst_event_box').empty();
				product.contenstsLoad();
			},
			contenstsLoad : function () {
				// 전역변수로 limt 를 갖고잇어야하나?
				var sendData = {
					"start" : start
				};
				$.ajax({
					method : "GET",
					url : "/product/category/" + id + "/start/" + start,
					contentType : "application/json; charset=utf-8",
					dataType : "json"
				}).done(function(data) {
					// 핸들바 템플릿 컴파일
					if (data.length === 0) {
						alert("더이상 데이터가 존재하지 않습니다. ");
					}else{
						var leftItem = {
							items : []
						}, rightItem = {
							items : []
						};
						
						
						
						for (var i = 0, max = data.length; i < max; ++i) {
							if (i % 2 === 0) {
								leftItem.items.push(data[i]);
							} else {
								rightItem.items.push(data[i]);
							}
						}
						var left = leftTemplate(leftItem);
						// html 과 data가 mapping된걸 반환함
						var right = leftTemplate(rightItem);
						// 생성된 HTML을 DOM에 주입
			
						$('.lst_event_box:first').append(left);
						$('.lst_event_box').eq(1).append(right);
						start += 10;
					}
				});
			},
			ScrollEvent : function() {
				 if ($(window).scrollTop() >= $(document).height() - $(window).height()) {
					 this.contenstsLoad();
				}
			}
		}
		
	})();
	

	// 초기 함수 호출 
	product.productEven();
	product.countEvent();
	


	// click 한 element의  this를 넘겨주고 싶은데, 원하는 동작이 안나와서 인자로 this를 넘김 
	$(document).on("click", "ul.tab_lst_min  > li.item", function(event) {
		product.countEvent.call(this);
		product.productEven.call(this);
	});
	

	$(document).scroll(product.ScrollEvent.bind(product));
		
	//$(".btn").on("click", function() { contenstsLoad(countValue.id,countValue.start);});
	
	// 펭지ㅣ 로딩시 수행 
	
});