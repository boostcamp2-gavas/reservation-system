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
				}
		}
		
	})();
