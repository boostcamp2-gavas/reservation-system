// slide 와 관련된 부분  [Caroucel]

// 클릭만 동작하는 부분 

	var carouceldetail2 = (function(){
		var $point = $(".num:first"),
		currentPoint = Number($point.text());
		
		// 객체 생성
		var caroucel = new Caroucel();		
		return {
				caroucelLeftClick : function leftClickEvent(event){
					if(caroucel.leftClick()){
						// true 면 -1 
						$point.text(--currentPoint);
						return true;
					}
					return false;
				},
				
				caroucelRightClick : function rightClickEvent(event){
					if(caroucel.rightClick()){
						// true 면 +1 
						$point.text(++currentPoint);
						return true;
					}
					return false;
				},
									
				setWidth : function(size){
					caroucel.setWidth(size);
				},
				getWidth: function(){
					return caroucel.carc_setting.imgLength;
				},
				setUl : function(ul){
					caroucel.setUl(ul);
				}
		
		}
		
	})();
