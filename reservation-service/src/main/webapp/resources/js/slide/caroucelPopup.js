// slide 와 관련된 부분  [Caroucel]

// 클릭만 동작하는 부분 
	var CaroucelPopup = (function(){
		var $point = $(".num.popup"),
		currentPoint = Number($point.text());
		var caroucel = new Caroucel();		
		
		return {
				caroucelLeftClick : function leftClickEvent(){
					if(caroucel.leftClick()){
						$point.text(--currentPoint);
						return true;
					}
					return false;
				},
				
				caroucelRightClick : function rightClickEvent(){
					if(caroucel.rightClick()){
						$point.text(++currentPoint);
						return true;
					}
					return false;
				},
				init : function($ul,size){
					caroucel.setUl($ul);
					caroucel.setWidth(size);
					caroucel.setting.current_length=0;
					currentPoint =1;
					$point.text(currentPoint);
				}, 
				getLayerImg : function(data) {
					if (data.length === 0) {
						alert("없는 이미지 ");
					}else{
						var leftTemplate,
						main ='html',
						Items = {
							items : []
						}
						
						leftTemplate = Handlebars.compile(templateSource);
						for (var i = 0, max = data.length; i < max; ++i) {
							Items.items.puh(data[i]);
						}
						main = leftTemplate(Items);
						// 생성된 HTML을 DOM에 주입
			
						caroucel.$ul.append(main);
					}
				}
		}
		
	})();
