
/**
 * @Construct Caroucel()
 * @Comment
 * 초기 사용시 ul과 width를 받아서 진행.
 * rightClick 과 leftClick을 구현하고 있다.
 * @returns
 */
function Caroucel(){
	this.$ul ='$';
};

Caroucel.prototype.setting = {
		total_length : 0,
		maxLength : 0,
		imgLength : 0,
		moveLength :0
};


/**
 * @function rightClick / leftClick
 * @return boolean
 * 
 * @comment  
 * 	더이상 넘어갈 page가 없다면 false를, 
 * 	있다면 animator을 돌리고 true를 반환
 */
Caroucel.prototype.rightClick = function caroucelRight(){	
	if(this.current_length === this.setting.total_length){
		// 처음으로 돌아가는 코드
		return false;
	}else{
		// ul 의 자식중 current_length 번쨰 를 선택 .
		this.$ul.animate({"right": "+="+this.setting.imgLength}, "fast");
		this.setting.moveLength += this.setting.imgLength;
		++this.current_length;
		return true;
	}
};

Caroucel.prototype.leftClick =   function caroucelLeft(){
	if(this.current_length !== 0){
		// ul 의 자식중 current_length 번쨰 를 선택 .
		this.$ul.animate({"right": "-="+this.setting.imgLength}, "fast");
		this.setting.moveLength -= this.setting.imgLength;
		this.current_length --;
		return true;
	}else{
		return false;
	}
};

/**
 * @function setWidth 
 * @param int값인 size 를 받음
 * 
 * @function setUl
 * @param selector을 받음  -> $("") 형태
 * 
 * @comment 
 * Width 와 Ul을 입력받아 설정
 * 
 */
Caroucel.prototype.setInit = function(size){

	this.setting.imgLength = size;
	this.setting.total_length = this.$ul.children().length - 1
	this.currentPoint = 1;
};


