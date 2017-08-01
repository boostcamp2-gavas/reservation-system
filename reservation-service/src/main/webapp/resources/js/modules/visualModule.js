
/*setting = {
root:
visualImgSize:
visualImgNum:
isAutoRoll:
isScrollEnd:
btnPreElement:
btnNxtElement:
printPositionElement:
}*/
function VisualModule(setting) {


	function makeModule(setting) {
		
		//initiallize
		var root = setting.root;
		var visualImgSize = setting.visualImgSize;
		var visualImgNum = setting.visualImgNum;
		var isAutoRoll = setting.isAutoRoll;
		var isScrollEnd = setting.isScrollEnd;
		var btnPreElement = setting.btnPreElement;
		var btnNxtElement = setting.btnNxtElement;
		var printPositionElement = setting.printPositionElement;
		
		
		var  position_num = 0;
		var  autoRollId = null;
		var  stopRollId = null;
		
		//default setting
		if(visualImgSize == null) { visualImgSize = 414; }
		if(visualImgNum == null) { visualImgNum = 2; }
		if(isAutoRoll == null) { isAutoRoll = false; }
		if(isScrollEnd == null) { isScrollEnd = 1; }
		if(btnPreElement == null) { btnPreElement = root.find(".btn_pre_e"); }
		if(btnPreElement == null) { btnPreElement = root.find(".btn_nxt_e"); }


		// module
		var touchHandler;

		// init
		function init(){
			root.find(".visual_img").animate( { left: - position_num * visualImgSize + "px" }, 0);
			btnSetting();
			position_num = 0;
			if(!isAutoRoll) { autoRoll = function(){}; }
			autoRoll();
			touchHandler = touchHandle();
			printPositionElement.html(position_num+1);
		}


		// methods:
		// btnSetting, btn_pre_eClick, btn_nxt_eClick, goPrev, goNext, autoRoll, stopRoll
		function btnSetting() {
			btnPreElement.off("click");
			btnNxtElement.off("click");
			btnPreElement.on("click", btn_pre_eClick);
			btnNxtElement.on("click", btn_nxt_eClick);
		}

		function btn_pre_eClick(event) {
			event.preventDefault();
			event.stopPropagation();
			goPrev();
			stopRoll();
			printPositionElement.html(position_num+1);
			//console.log(position_num);
		}

		function btn_nxt_eClick(event) {
			event.preventDefault();
			event.stopPropagation();
			goNext();
			stopRoll();
			printPositionElement.html(position_num+1);
			//console.log(position_num);
		}

		function goPrev() {
			if(position_num == 0 && !isScrollEnd ) {
				position_num = visualImgNum - 1 ;
				root.find(".visual_img").animate({left: -visualImgNum* visualImgSize  + "px" }, 0);
				root.find(".visual_img").animate( { left: - position_num * visualImgSize + "px" }, 'slow');

			} else if (position_num > 0) {
				position_num = position_num - 1;
				root.find(".visual_img").animate( { left: - position_num * visualImgSize + "px" }, 'slow');

			} else {
				root.find(".visual_img").animate( { left: - position_num * visualImgSize + "px" }, 'slow');
			}
		}

		function goNext() {
			
			if(position_num == visualImgNum -1 && !isScrollEnd) {
				position_num = 0;
				root.find(".visual_img").animate( { left: "-=" + visualImgSize + "px" }, 'slow');
				root.find(".visual_img").animate({left: "0px" }, 0);
				////console.log(position_num);
			} else if(position_num < visualImgNum -1) {
				position_num = position_num + 1;
				root.find(".visual_img").animate( { left: - position_num * visualImgSize + "px" }, 'slow');
				////console.log(position_num);
			} else{
				root.find(".visual_img").animate( { left: - position_num * visualImgSize + "px" }, 'slow');
			}
		}

		function autoRoll() {
			autoRollId = setInterval(goNext, 2000);
		}

		function stopRoll() {
			clearTimeout(stopRollId);
			clearInterval(autoRollId);
			stopRollId = setTimeout(autoRoll, 4000);
		}




		function touchHandle() {
			var positionStart;

			var touchXStart;
			var touchXPrev;
			var touchTimePrev;
			var touchXEnd;

			var touchX;
			var moveX;

			root.find(".visual_img").off("touchstart");
			root.find(".visual_img").off("touchmove");
			root.find(".visual_img").off("touchend");

			root.find(".visual_img").on("touchstart", touchStartHandle );
			root.find(".visual_img").on("touchmove", touchMoveHandle );
			root.find(".visual_img").on("touchend", touchEndHandle );

		}
		
		function touchStartHandle(event) {
			touchX = event.originalEvent.touches[0].pageX;
			touchXStart = touchX;
			touchXPrev = touchX;

			touchTime = event.originalEvent.timeStamp;
			touchTimePrev = touchTime;

			positionStart = - position_num * visualImgSize + "px";
		}
		
		function touchMoveHandle(event) {
			touchX = event.originalEvent.touches[0].pageX;
			moveX = touchX - touchXPrev;

			touchTime = event.originalEvent.timeStamp;
			moveTime = touchTimePrev - touchTime;

			root.find(".visual_img").animate({left : "+=" + moveX + "px" }, moveTime);

			touchXPrev = touchX;
			touchTimePrev = touchTime;
		}
		
		function touchEndHandle(event) {
			var totalmoveX = touchX - touchXStart;
			var touchLimit = visualImgSize / 3;

			if( totalmoveX > touchLimit ) {
				btnPreElement.trigger("click");
				
			}
			else if ( totalmoveX < - touchLimit ) {

				btnNxtElement.trigger("click");
			}
			else {
				root.find(".visual_img").animate({left : positionStart }, 'fast');

			}
		}


		return {
			init: init

		};


	}
	
	
	var instance = makeModule(setting);
	return instance;



}
