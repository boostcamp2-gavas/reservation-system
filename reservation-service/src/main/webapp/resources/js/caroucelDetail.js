// slide 와 관련된 부분  [Caroucel]

// 클릭만 동작하는 부분 

	var carouceldetail = (function(){
		var $point = $(".num:first"),
		currentPoint = Number($point.text());
		
		// 객체 생성
		// 교집합으로 구성하고  Prototype 상속을 활용
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
				}
		
		}
		
	})();
