// 사용하지 않는 파일 

/* 
		초기 앞 2개 img를 뒤에 붙여, 전체 길이를 구함 . 해당 길이로
	 	[예시]
	 	순환 로직을 작성. A B C D => A B C D A B 
					0 1 2 3    0 1 2 3 4 5
		실행 방법 
					0에서 왼쪽 클릭시, 4로 이동하여 왼쪽으로 이동.
					4에서 오른쪽 클릭시, 0으로 이동하여 오른쪽으로 이동 하는 로직 
*/

// 이렇게 해야 다른 js 에서 사용가능
var caroucel;

$(document).ready(function(){
	/*
	 * @{values}
	 * carc_setting : 동작에 필요한 변수들의 집합
	 * this :  함수들의 집합
	 * init : 실행에 필요한 변수들 초기화 
	 */	
	caroucel = caroucel || {};
	
	caroucel = (function(){
		var $ul = $(".visual_img"),
		carc_setting = {},
		slidAutoEvent = {},
		init_firstImg ='',
		init_seconImg ='';
		
		
		// 사용할 변수 들 
		carc_setting = {
				total_length : 0,
				current_length : 0,
				moveLength : 0,
				maxLength : 0,
				imgLength : 0,
				startAuto : 0,
				autoSlid_ID : 0
		};
		
		
		
		
		// Jquery에서 OuterHtml이 없기에, 구현 
		// 해당 함수는 초기 앞 2개의 li를 구할때 사용 .
		function OuterHtml(url){
			return url.clone().wrapAll("<div/>").parent().html();
		}
		
		// 초기에 한번만 사용될  함수
		(function setMaxFunc(){
			for(var i =0; i < $ul.children().length -1; ++i){
				carc_setting.maxLength += $ul.children().eq(i).width();
			}
		})();
		
		(function initFunc(){
			init_first = OuterHtml($ul.children().eq(0));
			init_secon = OuterHtml($ul.children().eq(1));
			$ul.append(init_first).append(init_secon);
			carc_setting.total_length = $ul.children().length - 1 ;
		})();	
			
			
	
		 function caroucelRight(event){	
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
		  function caroucelLeft(){
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
				caroucelLeftClick : function leftClickEvent(event,clear){
										//this.clear();
										if(clear !=undefined){
											this.clear();
										}
										caroucelLeft();
									},
				
				caroucelRightClick : function rightClickEvent(event,clear){
										
										if(clear !=undefined){
											this.clear();
										}
										caroucelRight();
									},
									
				clear : function clearfunc(){
					clearInterval(carc_setting.autoSlid_ID);
					clearTimeout(carc_setting.startAuto);
					carc_setting.startAuto =  setTimeout(this.autoSlide,2000); 	
				
				},
				autoSlide : function (){
					carc_setting.autoSlid_ID = setInterval(caroucelRight, 2000);
				},
				setWidth : function(size){
					carc_setting.imgLength = size;
				}
		}
		
	})();
	// autoSlide 부분이 조금 난감 .

});