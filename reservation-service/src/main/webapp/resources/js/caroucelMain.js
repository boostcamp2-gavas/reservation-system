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
		init_secon = '',
		init_first = '',
		startAuto = 0,
		autoSlid_ID =0;
		
		function OuterHtml(url){
			return url.clone().wrapAll("<div/>").parent().html();
		}
		
		
		
	
			
		caroucelRight = function caroucelRight(event){	
					if(caroucel.setting.current_length === caroucel.setting.total_length -1  ){
						$ul.animate({"right": 0}, 0);
						$ul.animate({"right": "+="+caroucel.setting.imgLength}, "slow");
						
						caroucel.setting.moveLength = caroucel.setting.imgLength;
						caroucel.setting.current_length =1;
						
						// 처음으로 돌아가는 코드
					}else{
						// ul 의 자식중 current_length 번쨰 를 선택 .
						$ul.animate({"right": "+="+caroucel.setting.imgLength}, "slow");
						caroucel.setting.moveLength += caroucel.setting.imgLength;
						++caroucel.setting.current_length;
					}
				}
		
		caroucelLeft = function caroucelLeft(){
				if(caroucel.setting.current_length !== 0){
					// ul 의 자식중 current_length 번쨰 를 선택 .
					$ul.animate({"right": "-="+caroucel.setting.imgLength}, "slow");
					caroucel.setting.moveLength -= caroucel.setting.imgLength;
					caroucel.setting.current_length --;
			
				}else{
					$ul.animate({"right": caroucel.setting.imgLength*2}, 0);
					$ul.animate({"right": "-="+caroucel.setting.imgLength}, "slow");
					caroucel.setting.moveLength = caroucel.setting.imgLength;
					caroucel.setting.current_length = 1;
				}
			}

		
		return {
				init : function(size){
					init_first = OuterHtml($ul.children().eq(0));
					init_secon = OuterHtml($ul.children().eq(1));
					$ul.append(init_first).append(init_secon);
					caroucel.setting.total_length = $ul.children().length - 1 ;
					caroucel.setting.imgLength = size;
				},
				leftClick : function leftClickEvent(event){
					this.clearfunc();
					caroucelLeft();
				},
				
				rightClick : function rightClickEvent(event){
					this.clearfunc();
					caroucelRight();
				},
				autoSlide : function (){
					autoSlid_ID = setInterval(caroucelRight.bind(), 2000);
				},
				clearfunc : function(){
					clearInterval(autoSlid_ID);
					clearTimeout(startAuto);
					// 스스로 return 값을 가르킴 
					startAuto =  setTimeout(this.autoSlide,2000); 	
				}
		}
		
	})();
	
	caroucelSlide.prototype = new Caroucel();
	caroucelSlide.constructor = caroucelSlide;
	

