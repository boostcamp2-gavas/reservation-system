// slide 와 관련된 부분  [Caroucel]

// 클릭만 동작하는 부분 

// 기능이 달라서 모듈로 사용할 수 없었습니다. (순환의 문제)
// prototype을 이용하여 함수를 재정의(정확히는 재정의가 되지않음) 하는 방향으로 구현하였습니다 .? 
// 의미가 있나 ..?  + 모듈로 사용할 수 없지 않나 ?
// 이렇게 하려면 caroucelSlide 도 생성자로 지정하여 진행해야 하지 않나 ? 
// 질문 할것. <<<

// 내부에 new를 사용하지 않는 방향으로 진행. 
// 그럼 어떻게 ? 
	var caroucelSlide = (function(){
		var $ul = $(".visual_img");
		var caroucel = new Caroucel(),
		carc_setting = caroucel.carc_setting,
		init_secon = '',
		init_first = '';
		function OuterHtml(url){
			return url.clone().wrapAll("<div/>").parent().html();
		}
		
		
		function clearfunc(){
			clearInterval(carc_setting.autoSlid_ID);
			clearTimeout(carc_setting.startAuto);
			// 스스로 return 값을 가르킴 
			carc_setting.startAuto =  setTimeout(caroucelSlide.autoSlide,2000); 	
		}
		
	
		// 실행하는 시점을 정할 수 없음. 
		// 제어 가능하도록 수정할 것.
		// 일반적으로 함수 내부에 즉시실행을 두진 않음 
		(function initFunc(){
			init_first = OuterHtml($ul.children().eq(0));
			init_secon = OuterHtml($ul.children().eq(1));
			$ul.append(init_first).append(init_secon);
			carc_setting.total_length = $ul.children().length - 1 ;
		})();	
		
			
		caroucel.caroucelRight = function caroucelRight(event){	
					if(carc_setting.current_length === carc_setting.total_length -1  ){
						$ul.animate({"right": 0}, 0);
						$ul.animate({"right": "+="+carc_setting.imgLength}, "slow");
						
						carc_setting.moveLength = carc_setting.imgLength;
						carc_setting.current_length =1;
						
						// 처음으로 돌아가는 코드
					}else{
						// ul 의 자식중 current_length 번쨰 를 선택 .
						$ul.animate({"right": "+="+carc_setting.imgLength}, "slow");
						carc_setting.moveLength += carc_setting.imgLength;
						++carc_setting.current_length;
					}
				}
		
		caroucel.caroucelLeft = function caroucelLeft(){
				if(carc_setting.current_length !== 0){
					// ul 의 자식중 current_length 번쨰 를 선택 .
					$ul.animate({"right": "-="+carc_setting.imgLength}, "slow");
					carc_setting.moveLength -= carc_setting.imgLength;
					carc_setting.current_length --;
			
				}else{
					$ul.animate({"right": carc_setting.imgLength*2}, 0);
					$ul.animate({"right": "-="+carc_setting.imgLength}, "slow");
					carc_setting.moveLength = carc_setting.imgLength;
					carc_setting.current_length = 1;
				}
			}

		
		return {
				caroucelLeftClick : function leftClickEvent(event){
					clearfunc();
					caroucel.caroucelLeft();
				},
				
				caroucelRightClick : function rightClickEvent(event){
					clearfunc();
					caroucel.caroucelRight();
				},
									
				setWidth : function(size){
					carc_setting.imgLength = size;
				},
				autoSlide : function (){
					carc_setting.autoSlid_ID = setInterval(caroucel.caroucelRight.bind(), 2000);
				}
		}
		
	})();
