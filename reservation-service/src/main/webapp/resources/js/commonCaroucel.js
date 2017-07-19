// 생성자 정의 
// 교집합 형태로 수정할것. 
// 공통되는 부분은 Prototye으로 정의 하고 진행. 
// 너무 합집합으로 구성하긴 함 
function Caroucel(){

	var $ul = $(".visual_img:first");
	this.carc_setting = {};
	
	// 사용할 변수 들 
	this.carc_setting = {
			total_length : $ul.children().length - 1,
			current_length : 0,
			moveLength : 0,
			maxLength : 0,
			imgLength : 0,
			startAuto : 0,
			autoSlid_ID : 0
	};
	
	// startAuto / autoSlid_ID 쓸 필요는 없음 .
	// 초기에 한번만 사용될  함수

	for(var i =0; i < $ul.children().length -1; ++i){
		this.carc_setting.maxLength += $ul.children().eq(i).width();
	}
	
	// this를 사용하지 않고, prototype을 사용.
	this.leftClick =   function caroucelLeft(){
		if(this.carc_setting.current_length !== 0){
			// ul 의 자식중 current_length 번쨰 를 선택 .
			$ul.animate({"right": "-="+this.carc_setting.imgLength}, "fast");
			this.carc_setting.moveLength -= this.carc_setting.imgLength;
			this.carc_setting.current_length --;
			return true;
		}else{
			return false;
		}
	}
	
	this.rightClick =  function caroucelRight(event){	
		if(this.carc_setting.current_length === this.carc_setting.total_length){
			// 처음으로 돌아가는 코드
			return false;
		}else{
			// ul 의 자식중 current_length 번쨰 를 선택 .
			$ul.animate({"right": "+="+this.carc_setting.imgLength}, "fast");
			this.carc_setting.moveLength += this.carc_setting.imgLength;
			++this.carc_setting.current_length;
			return true;
		}
	}
	
	this.setWidth = function(size){
		this.carc_setting.imgLength = size;
	}
	
	this.setUl = function(ul){
		$ul = ul;
	}
};