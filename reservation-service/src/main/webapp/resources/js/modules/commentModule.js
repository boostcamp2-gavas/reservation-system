

var CommentModule = (function(){
	var HOST = "";
	var productId;
	

	
	
	function getComment() {
		var url = HOST + "/api/comment/" + productId;
		var data = {
				start: 0,
				amount: 3
		}
		
		getCommentAjax = $.ajax({
			url: url,
			type: "GET",
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			data: data
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
		var url = HOST + "/commentImg/" + commentId;
		
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
		
		var setting = {
				root: $(".section_popup"),
				visualImgSize: 414,
				visualImgNum: $ul.children().length,
				isAutoRoll: false,
				isScrollEnd: true,
				btnPreElement: $(".btn_prev"),
				btnNxtElement: $(".btn_nxt"),
				printPositionElement:$(".section_popup .figure_pagination .num:first-child")
		}
		var visualModule = VisualModule(setting);
		visualModule.init();
		
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
	
		init: function(id) {
			productId = id;
			//getComment();
			initButton();
		}
	}
	
	
})();