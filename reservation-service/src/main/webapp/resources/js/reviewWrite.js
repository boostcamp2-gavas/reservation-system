$(document).ready(function() {
	
	var rating = new Rating( $(".ct_wrap") );
	rating.init();
	
	var reviewContents = new ReviewContents( $(".ct_wrap") );
	reviewContents.init();
	
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
	this.root.on("focusout", ".review_contents", this.textareaFocusOut.bind(this));
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
		this.root.find(".review_textarea").focus();
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
	
	var fileData = new Array();
	
	for (var i=0; i < this.fileList.length; i++) {
		var file = this.fileList[i];
		var dataUrl = this.resultList[i];
		
		if(file != null && dataUrl != null){
			var data = {
					"file": file,
					"dataUrl": dataUrl,
				}
			filedata.push(data);
		}
	}
	console.log(fileData);
	return fileData;
}
// ReviewContents ends


function FormModule(root) {
	this.formData = new FormData();
}
FormModule.prototype = new eg.Component();
FormModule.prototype.constructer = FormModule;

FormModule.prototype.append

FormModule.prototype.doPost = function() {
	
}




