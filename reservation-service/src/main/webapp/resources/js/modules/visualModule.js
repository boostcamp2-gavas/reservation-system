const VISUAL_IMG_SIZE = 414;
const VISUAL_IMG_NUM = 2;

var VisualModule_ = (function(){

	var instance;

	function makeModule() {

		var root = ""; // moduleClass : the ancestor class of this module

		// private variables
		var position_num = 0;
		var autoRollId;
		var stopRollId;
		var visualImgNum;
		var visualImgSize;
		var autoRolling = autoRoll;
		var endFlag = 1;
		var btnPreElement= $( root + " .btn_pre_e");
		var btnNxtElement= $( root + " .btn_nxt_e");


		// module
		var touchHandler;

		// init
		function init(){
			$( root + ".visual_img").animate( { left: - position_num * visualImgSize + "px" }, 0);
			btnSetting();
			position_num = 0;
			if(autoRolling != null) { autoRolling(); }
			touchHandler = touchHandle();
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
			goPrev();
			stopRoll();
			event.preventDefault();
			event.stopPropagation();
			//console.log(position_num);
		}

		function btn_nxt_eClick(event) {
			goNext();
			stopRoll();
			event.preventDefault();
			event.stopPropagation();
			//console.log(position_num);
		}

		function goPrev() {
			if(position_num == 0 && endFlag != 0 ) {
				position_num = visualImgNum - 1 ;
				$( root + ".visual_img").animate({left: -visualImgNum* visualImgSize  + "px" }, 0);
				$( root + ".visual_img").animate( { left: - position_num * visualImgSize + "px" }, 'slow');

			} else if (position_num > 0) {
				position_num = position_num - 1;
				$( root + ".visual_img").animate( { left: - position_num * visualImgSize + "px" }, 'slow');

			} else {
				$( root + ".visual_img").animate( { left: - position_num * visualImgSize + "px" }, 'slow');
			}
		}

		function goNext() {
			if(position_num == visualImgNum -1 && endFlag != 0) {
				position_num = 0;
				$( root + ".visual_img").animate( { left: "-=" + visualImgSize + "px" }, 'slow');
				$( root + ".visual_img").animate({left: "0px" }, 0);
				////console.log(position_num);
			} else if(position_num < visualImgNum -1) {
				position_num = position_num + 1;
				$( root + ".visual_img").animate( { left: - position_num * visualImgSize + "px" }, 'slow');
				////console.log(position_num);
			} else{
				$( root + ".visual_img").animate( { left: - position_num * visualImgSize + "px" }, 'slow');
			}
		}

		function autoRoll() {
			autoRollId = setInterval(goNext, 2000);
		}

		function stopRoll() {
			clearTimeout(stopRollId);
			clearInterval(autoRollId);
			stopRollId = setTimeout(autoRolling, 4000);
		}

		function printPositionNum(printObject) {
			printObject.html(position_num+1);
		}


		function touchHandle() {
			var positionStart;

			var touchXStart;
			var touchXPrev;
			var touchTimePrev;
			var touchXEnd;

			var touchX;
			var moveX;

			$(root+".visual_img").off("touchstart");
			$(root+".visual_img").off("touchmove");
			$(root+".visual_img").off("touchend");

			$(root+".visual_img").on("touchstart", function(event) {
				touchX = event.originalEvent.touches[0].pageX;
				touchXStart = touchX;
				touchXPrev = touchX;

				touchTime = event.originalEvent.timeStamp;
				touchTimePrev = touchTime;

				positionStart = - position_num * visualImgSize + "px";
			});


			$(root+".visual_img").on("touchmove", function(event) {
				touchX = event.originalEvent.touches[0].pageX;
				moveX = touchX - touchXPrev;

				touchTime = event.originalEvent.timeStamp;
				moveTime = touchTimePrev - touchTime;

				$(root+".visual_img").animate({left : "+=" + moveX + "px" }, moveTime);

				touchXPrev = touchX;
				touchTimePrev = touchTime;
			});

			$(root+".visual_img").on("touchend", function(event) {
				var totalmoveX = touchX - touchXStart;
				var touchLimit = visualImgSize / 3;

				if( totalmoveX > touchLimit ) {
					btnPreElement.trigger("click");
				}
				else if ( totalmoveX < - touchLimit ) {

					btnNxtElement.trigger("click");
				}
				else {
					$(root+".visual_img").animate({left : positionStart }, 'fast');

				}
			});

		}


		return {
			init: init,

			setVisualImgNum: function(num) {
				visualImgNum = num;
			},

			setVisualImgSize: function(num) {
				visualImgSize = num;
			},

			setModuleClass: function(moduleClass) {
				if(moduleClass == null || moduleClass == "") {
					root = "";
				} else {
					root = "." + moduleClass + " ";
				}

			},

			setAutoRoll: function(isAutoRoll) {
				if(isAutoRoll) {
					autoRolling = autoRoll;
				} else {
					clearTimeout(stopRollId);
					clearInterval(autoRollId);
					autoRolling = null;
				}
			},

			setButton: function (btnPreElementIn, btnNxtElementIn) {
				btnPreElement = btnPreElementIn;
				btnNxtElement = btnNxtElementIn;
				btnSetting();
			},

			setScrollEndFlag: function (flag) {
				endFlag = flag;
			},

			getPosition: function() {
				return position_num;
			},

			initPrintPositionHandler: function(printObject) {
				printPositionNum(printObject);
				btnPreElement.on("click", printPositionNum.bind(this, printObject));
				btnNxtElement.on("click", printPositionNum.bind(this, printObject));
			}


		};


	};

	return {

	    getInstance: function () {


	        instance = makeModule();


	      return instance;
	    }

	};

})();

