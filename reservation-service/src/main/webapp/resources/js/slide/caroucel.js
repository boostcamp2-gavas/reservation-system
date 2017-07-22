

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

	console.log(this.current_length);
	console.log(this.total_length);
	if(this.current_length === this.total_length){
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
	this.total_length = this.$ul.children().length - 1
	this.currentPoint = 1;
};

// 클릭만 동작하는 부분 + touch 
	
function CaroucelTouch($ul,$point){
	this.$point = $point;
	this.$ul = $ul;
	if($ul){
		this.total_length =$ul.children().length - 1 ;
	}
	this.current_length = 0;
	if($point){
		this.currentPoint = Number($point.text());
	}
	
	this.start_x = 0;
	this.save_a = 0;
	this.move_dx = 0;
	this.touchstartEvent = function(event){};
	this.touchendEvent = function(event){};
	this.touchmoveEvent = function(event){};
	
	this.caroucelLeftClick = function(){
		if(this.leftClick()){
			 this.save_a -= this.setting.imgLength;	
			 // main 쪽에선 point 가 없으므로.. 
			 if(this.$point){
				 this.$point.text(--this.currentPoint);
			 }
			return true;
		}
		return false;
	};
	
	this.caroucelRightClick = function(){
		if(this.rightClick()){
			 this.save_a += this.setting.imgLength;
			 if(this.$point){
				 this.$point.text(++this.currentPoint);
			 }
			return true;
		}
		return false;
	};
	
	this.touchstartEvent = function(event){
		this.start_x =event.originalEvent.changedTouches[0].screenX;
	}
 	

	this.touchendEvent = function(){
	     if(this.move_dx >50 ){
			 this.caroucelLeftClick()
		 }else if(this.move_dx < -50 ){
			 this.caroucelRightClick()
		 }
	     // 움직인 만큼 반대로 돌림 
	     this.$ul.animate({"right": "+="+this.move_dx }, 0);	 
		// 다시 초기화 	     
	     this.move_x = 0,
	     this.move_dx = 0;
	};
	
	this.touchmoveEvent = function(event){
		this.move_dx = event.originalEvent.changedTouches[0].screenX-this.start_x;
		this.$ul.animate({"right": this.save_a-this.move_dx},0);
	};
	
}




function CaroucelPopup($ul,$point){
	this.$point = $point;
	this.$ul = $ul;
	this.currentPoint = Number($point.text());
	templateSource = $("#layer-content").html(),
	leftTemplate = Handlebars.compile(templateSource);
	
	this.getLayerImg = function(data) {
		if (data.length === 0) {
			alert("없는 이미지 ");
		}else{
			main ='html',
			Items = {
				items : []
			}
			for (var i = 0, max = data.length; i < max; ++i) {
				Items.items.push(data[i]);
			}
			main = leftTemplate(Items);
			console.log(this);
			this.$ul.append(main);
		}
	};
};

// OuterHtml 기능 
function outerHtml(url){
	return url.clone().wrapAll("<div/>").parent().html();
}

function AutoCaaroucel($ul){
	this.$ul = $ul;
	this.init_secon = '',
	this.init_first = '',
	this.startAuto = 0,
	this.autoSlid_ID =0,
	this.current_length = 0;

	this.init_first = outerHtml($ul.children().eq(0));
	this.init_secon = outerHtml($ul.children().eq(1));
	this.$ul.append(this.init_first).append(this.init_secon);
	
	this.clearfunc = function(){
		clearInterval(this.autoSlid_ID);
		clearTimeout(this.startAuto);
		// 스스로 return 값을 가르킴 
		this.startAuto =  setTimeout(this.autoSlide.bind(this),2000); 	
	};
	
	this.autoSlide = function(){
		this.autoSlid_ID = setInterval(this.caroucelRightClick.bind(this), 2000);
	}
	
	this.caroucelLeftClick =function(){
		this.clearfunc();
		if(this.current_length !== 0){
			// ul 의 자식중 current_length 번쨰 를 선택 .
			this.$ul.animate({"right": "-="+this.setting.imgLength}, "slow");
			this.setting.moveLength -= this.setting.imgLength;
			this.current_length --;
		}else{
			this.$ul.animate({"right": this.setting.imgLength*2}, 0);
			this.$ul.animate({"right": "-="+this.setting.imgLength}, "slow");
			this.setting.moveLength = this.setting.imgLength;
			this.current_length = 1;
		}
	}
	
	this.caroucelRightClick = function(){
		this.clearfunc();
		if(this.current_length === this.total_length-1){
			this.$ul.animate({"right": 0}, 0);
			this.$ul.animate({"right": "+="+this.setting.imgLength}, "slow");
			
			this.setting.moveLength = this.setting.imgLength;
			this.current_length =1;
			// 처음으로 돌아가는 코드
		}else{
			// ul 의 자식중 current_length 번쨰 를 선택 .
			this.$ul.animate({"right": "+="+this.setting.imgLength}, "slow");
			this.setting.moveLength += this.setting.imgLength;
			++this.current_length;
		}
	}
	
	
}


CaroucelTouch.prototype = new Caroucel();
CaroucelTouch.prototype.constructor = CaroucelTouch;

CaroucelPopup.prototype = new CaroucelTouch();
CaroucelPopup.prototype.constructor = CaroucelPopup;

AutoCaaroucel.prototype = new Caroucel();
AutoCaaroucel.prototype.constructor = AutoCaaroucel;


var CarocelDetail = (function(){
	var touch = {};
	var caroucel = new Caroucel();
	return{
		init : function(touch,length){
			var $ul = touch.$ul;
			var $pre =  $ul.parents(".group_visual").find(".prev_inn");
			var $nxt =  $ul.parents(".group_visual").find(".nxt_inn");
			var len = length || 414;
			touch.setInit(len);
			
			if(touch.constructor !== AutoCaaroucel){
				$ul.on("touchend",touch.touchendEvent.bind(touch)); 
				$ul.on("touchstart",touch.touchstartEvent.bind(touch)); 
				$ul.on("touchmove",touch.touchmoveEvent.bind(touch)); 
			}
		
			$pre.on("click",touch.caroucelLeftClick.bind(touch));
			$nxt.on("click",touch.caroucelRightClick.bind(touch));
		},
		destroy : function($ul){
			var $pre =  $ul.parents(".group_visual").find(".prev_inn");
			var $nxt =  $ul.parents(".group_visual").find(".nxt_inn");
			
			$ul.off("touchstart");
			$ul.off("touchend");
			$ul.off("touchmove");
			$ul.animate({"right": 0}, 0);
			$pre.off("click");
			$nxt.off("click");
			
		}
	}
})();
