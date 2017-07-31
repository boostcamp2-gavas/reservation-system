$(document).ready(function() {
	
	var rating = new Rating( $(".ct_wrap") );
	rating.init();
	
	var reviewContents = new ReviewContents( $(".ct_wrap") );
	reviewContents.init();
	
	var formModule = new FormModule(rating, reviewContents);
	
	$(".bk_btn").on("click", (function(){
		formModule.postComment();
	}).bind(this));
	
	formModule.on("postCommentOnload", formModule.postFile);

	
});



function Rating(root) {
	this.score = 3;
	this.root = root;
	
}
Rating.prototype = new eg.Component;
Rating.prototype.constructer = Rating;

Rating.prototype.update = function () {
	var score = this.score;
	var i;
	
	if(score >= 1){
		
		for(i=1; i<=score; i++) {
			this.root.find(".rating_rdo[value=" + i + "]").addClass("checked").attr("checked","checked").prop("checked",true);
			
		}
		
		for(; i<=5; i++) {	
			this.root.find(".rating_rdo[value=" + i + "]").removeClass("checked").removeAttr("checked").prop("checked",false);
			
		}
		
		this.root.find(".star_rank").html(score);
		
	} else {
		this.root.find(".rating_rdo").removeClass("checked").attr("checked","unchecked");
		this.root.find(".rating_rdo[value='0']").addClass("checked").attr("checked","checked");
		this.root.find(".star_rank").html("0");
	}
}

Rating.prototype.init = function() {
	
	this.root.on("click", ".rating_rdo", (function(e) {
		e.preventDefault();
		this.score = $(e.currentTarget).attr("value");
		this.update();
	}).bind(this));
	
	this.update();
} 

Rating.prototype.getScore = function() {
	return this.score;
}

//Rating ends



//ReviewContents start
function ReviewContents(root) {
	this.root = root;
	this.fileList = new Array();
	this.resultList = new Array();
	this.index = 0;
	this.fileListlengh = 0;
}
ReviewContents.prototype = new eg.Component;
ReviewContents.prototype.constructer = ReviewContents;

ReviewContents.prototype.init = function() {
	
	this.root.on("click", ".review_write_info", this.textareaFocusOn.bind(this));
	this.root.on("focusout", ".review_textarea", this.textareaFocusOut.bind(this));
	this.root.on("keyup", this.lengthUpdate.bind(this));
	this.fileUpload();
}



ReviewContents.prototype.textareaFocusOn  = function(e) {
	e.preventDefault();
	this.root.find(".review_write_info").hide();
	this.root.find(".review_textarea").focus();

}

ReviewContents.prototype.textareaFocusOut  = function(e) {
	e.preventDefault();
	var text = this.root.find(".review_textarea").val();
	if(text == null || text.length == 0 ){
		this.root.find(".review_write_info").show();
	}

}

ReviewContents.prototype.lengthUpdate = function(e) {
	e.preventDefault();
	var length = this.root.find(".review_textarea").val().length;
	this.root.find(".guide_review span:first-child").html(length);
}


ReviewContents.prototype.fileUpload = function() {
	var fileBlock = this.root.find("#reviewImageFileOpenInput");
	
	this.root.find(".review_photos").on("click", ".spr_book.ico_del", deleteImage.bind(this));

	fileBlock.change( (function(fileBlock) { // this: ReviewContents	
		var files = fileBlock.prop("files");

		for(var i=0; i < files.length; i++) { 
			
			if( this.fileListlengh + i >= 5) {
				console.log("max image quantity is 5.")
				break;
			}
			
			var file = files[i];
		    var reader = new FileReader();   
		    reader.readAsDataURL(file);
		    
		    reader.onload = addImage.bind(this, file);    
		}
		
	}).bind(this, fileBlock));
	
	
	function addImage(file, event) {
		const MAX_FILE_SIZE = 1024 * 1024;
		
    	var reader = event.currentTarget;
    	var result = reader.result;
    	if(file.size > MAX_FILE_SIZE) {
    		console.log("file size is too big.");
    		return;
    	}
    	
    	var source = $("#review_photos_template").html();
		var template = Handlebars.compile(source);
		var data = {
				index : this.index,
				imgSrc : result,
				imgAlt : file.name		
		}
		var html = template(data);
		this.root.find(".lst_thumb").append(html);
		
		this.fileList.push(file);
		this.resultList.push(result)
		
		this.index++;
		this.fileListlengh++;
		console.log(this.fileList);
	}

	
	function deleteImage(e) {
		e.preventDefault();
		var index = $(e.currentTarget).parent().parent().data("index");
		this.root.find(".review_photos .item[data-index='"+index+"']").remove();
		this.fileList[index] = null;
		this.resultList[index] = null;
		console.log(this.fileList);
		console.log(this.resultList);
		this.fileListlengh--;
	}
}	// ReviewContents.prototype.fileUpload ends.


ReviewContents.prototype.getText = function() {
	return this.root.find(".review_textarea").val();
}


ReviewContents.prototype.getFileData = function() {
	
	var files = new Array();
	var dataUrls = new Array();
	
	for (var i=0; i < this.fileList.length; i++) {
		var file = this.fileList[i];
		var dataUrl = this.resultList[i];
		
		if(file != null && dataUrl != null){
			files.push(file);
			dataUrls.push(dataUrl);
		}
	}
	var fileData = {files: files, dataUrls: dataUrls};
	console.log(fileData);
	return fileData;
}
// ReviewContents ends


function FormModule(ratingComponent, commentComponent) 
{
	this.ratingComponent = ratingComponent;
	this.commentComponent = commentComponent;
}
FormModule.prototype = new eg.Component();
FormModule.prototype.constructer = FormModule;


FormModule.prototype.postComment = function() {
	
	var reservationInfo = getReservationInfo();
	
	var score = this.ratingComponent.getScore();
	var comment = this.commentComponent.getText();
	
	var commentLength = comment.length;
	
	if(commentLength < 5) {
		alert("코멘트를 5자 이상 입력해주요.");
		return;
	}
	
	var data = new FormData();
	
	data.append("productId", reservationInfo.productId);
	data.append("userId", reservationInfo.userId);
	data.append("score", score);
	data.append("comment", comment);
	
	var url = "/comment";
	
	var request = new XMLHttpRequest();
	request.open("POST", url);
	request.send(data);

	request.onload = (function(event) {
		console.log(event);
		this.trigger("postCommentOnload", event);
	}).bind(this);

}

FormModule.prototype.postFile = function(prevOnloadEvent) {
	console.log("postFile:");
	console.log(prevOnloadEvent);
	
	if(this.commentComponent.fileListlengh <= 0) {
		console.log("there is no file.");
		location.href = "/review/"+getReservationInfo().productId;
		return;
	}
	
	var commentId = prevOnloadEvent.currentTarget.response;
	if(commentId == null) {
		return;
	}
	
	var fileData = this.commentComponent.getFileData();
	var files = fileData.files;
	//var dataUrls = fileData.dataUrls;
	var formData = new FormData();
	
	formData.append("commentId", commentId);
	
	for (var i in files) {
		console.log(files[i]);
		formData.append("files", files[i]);
	}

	
	var url = "/img";
	
	$.ajax({
        url: url,
        type: "POST",
        xhr: function() {
            var myXhr = $.ajaxSettings.xhr();
            return myXhr;
        },
        success: function (data) {
            alert("Data Uploaded: "+data);
        },
        data: formData,
        cache: false,
        contentType: false,
        processData: false
    }).always(function(){
    	location.href = "/review/"+getReservationInfo().productId;
    });
}





