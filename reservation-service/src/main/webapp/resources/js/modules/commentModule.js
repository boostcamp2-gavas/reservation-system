

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
		//console.log(response);
		var source = $("#layer-content").html();
		var template = Handlebars.compile(source);
		var data = {
				items : response
		};
		var html = template(data);
		$(".over-hidden ul").find("li").remove();
		$(".over-hidden ul").append(html);
		
		$("#photoviewer .figure_pagination .num.off span").html(response.length);	
		
		var setting = {
				root: $("#photoviewer"),
				visualImgSize: 414,
				visualImgNum: response.length,
				isAutoRoll: false,
				isScrollEnd: true,
				btnPreElement: $("#photoviewer .btn_prev"),
				btnNxtElement: $("#photoviewer .btn_nxt"),
				printPositionElement:$("#photoviewer .figure_pagination .num:first-child")
		}
		var visualModule = VisualModule(setting);
		visualModule.init();
		
		if(response.length <= 1) {
			$("#photoviewer .prev").css("visibility", "hidden");
			$("#photoviewer .nxt").css("visibility", "hidden");
		} else {
			$("#photoviewer .prev").css("visibility", "");
			$("#photoviewer .nxt").css("visibility", "");
		}
		
	}
	
	function initButton() {
		$(".list_short_review").on("click", ".thumb", function(event){
			
			$("#photoviewer").removeClass("_none");
			event.preventDefault(); 
			event.stopPropagation();
			var commentId = $(event.target).data("comment");
			getVisual(commentId);
			//console.log("hello?");
		});
		
		$("#photoviewer .close").on("click", function(event){
			$("#photoviewer").addClass("_none");
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