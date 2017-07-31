

var CommentModule_ = (function(){
	var HOST = "http://localhost:8080";
	var productId;
	
	
	
	function getComment() {
		var url = HOST + "/api/comment/comments/" + productId;
		
		getCommentAjax = $.ajax({
			url: url,
			type: "GET",
			dataType: "json"
		})
		.done(
				showComment.bind(this)
		)
		.fail(function() {
			//console.log("error");
		});
		
	}
	
	function showComment(response) {

		var source = $("#list_short_review_template").html();
		var template = Handlebars.compile(source);
		var comments = new Array();
		
		
		$.each(response, function(index, element) {
			var t = new Date(element.modify_date);
			var date = t.getFullYear() + "-" + (t.getMonth()+1) + "-" + t.getDate();
			element.modifDate = date;
			comments.push(element);
			
			if(element.imageCount == 0  || element.imageCount == null) {
				element.display = "display: none;";
			}

		});
		var data = {
				"comments": comments
		};
		//console.log(data);
		var html = template(data);
		
		$(".list_short_review").append(html);


	}
	
	function getVisual(commentId) {
		var url = HOST + "/api/comment/images/" + commentId;
		
		getVisualAjax = $.ajax({
			url: url,
			type: "GET",
			dataType: "json"
		})
		.done(
				showVisual.bind(this)
		)
		.fail(function() {
			//console.log("error");
		});
	}
	
	function showVisual(response) {
		
		var source = $("#container_popup_template").html();
		var template = Handlebars.compile(source);
		var data = {commentVisual: response};
		var html = template(data);
		$(".section_popup ul").find("li").remove();
		$(".section_popup ul").append(html);
		
		$(".section_popup .figure_pagination .num.off span").html(response.length);	
		
		
		
		var VisualModulePopup = VisualModule_.getInstance();
		//setting
			VisualModulePopup.setVisualImgSize(VISUAL_IMG_SIZE);
			VisualModulePopup.setVisualImgNum(response.length);
			VisualModulePopup.setAutoRoll(false);
			VisualModulePopup.setScrollEndFlag(0);
			VisualModulePopup.setModuleClass("section_popup");
			VisualModulePopup.setButton($('.section_popup .btn_prev'), $('.section_popup .btn_nxt'));
			VisualModulePopup.init();
			VisualModulePopup.initPrintPositionHandler( $(".section_popup .figure_pagination .num:first-child") );
		
		
		if(response.length <= 1) {
			$(".section_popup .prev").css("visibility", "hidden");
			$(".section_popup .nxt").css("visibility", "hidden");
		} else {
			$(".section_popup .prev").css("visibility", "");
			$(".section_popup .nxt").css("visibility", "");
		}
		
	}
	
	function initButton() {
		$(".list_short_review").on("click", ".thumb", function(event){
			
			$("#photoviewer").css({display: "inline-block"});
			event.preventDefault(); 
			event.stopPropagation();
			var commentId = $(event.target).data("comment");
			getVisual(commentId);
		});
		
		$(".photoviewer_closer").on("click", function(event){
			$("#photoviewer").css({display: "none"});
			event.preventDefault(); 
			event.stopPropagation();
		});
		
	}
	
	return {
		setProductId: function(id) {
			productId = id;
		},
	
		load: function() {
			getComment();
			initButton();
		}
	}
	
	
})();