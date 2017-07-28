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
		this.root.find(".star_rank gray_star").html("0");
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



function ReviewContents(root) {
	this.root = root;
	this.fileList = new Array();
}
ReviewContents.prototype = new eg.Component;
ReviewContents.prototype.constructer = ReviewContents;

ReviewContents.prototype.init = function() {
	
	this.root.on("click", ".review_write_info", this.textareaFocus.bind(this));
	this.root.on("keyup", this.lengthUpdate.bind(this));
	this.fileUpload();
}



ReviewContents.prototype.textareaFocus  = function(e) {
	e.preventDefault();
	this.root.find(".review_write_info").hide();
	this.root.find(".review_textarea").focus();
}

ReviewContents.prototype.lengthUpdate = function(e) {
	e.preventDefault();
	var length = this.root.find(".review_textarea").val().length;
	this.root.find(".guide_review span:first-child").html(length);
}


ReviewContents.prototype.fileUpload = function() {
	var fileBlock = this.root.find("#reviewImageFileOpenInput");
	var index = 0;
	
	this.root.find(".review_photos").on("click", ".spr_book.ico_del", deleteImage.bind(this));
	
	
	
	fileBlock.change( (function(fileBlock, event) {
		
		var files = fileBlock.prop("files");
		for(var index=0; index < files.length; index++) { // this: ReviewContents
			
			var file = files[index];
			
			this.fileList.push(file);
			
			
		    var reader = new FileReader();
		    
		    reader.readAsDataURL(file);
		    console.log(reader);
		    
		    reader.onload = (function() {   // this == reader
		    	
		    	var source = $("#review_photos_template").html();
				var template = Handlebars.compile(source);
				var data = {
						index : index,
						imgSrc : this.result,
						imgAlt : this.name		
				}
				var html = template(data);
				
				$(".lst_thumb").append(html);
				index++;
		    });		    

		}
		
		 
		 
	}).bind(this, fileBlock));
	
	function addImage() {
		
	}
	
	function deleteImage(e) {
		e.preventDefault();
		var index = $(e.currentTarget).parent().parent().data("index");
		this.root.find(".review_photos .item[data-index='"+index+"']").remove();
		delete this.fileList[index];
	}
	

}




$(document).ready(function() {
	
	var rating = new Rating( $(".ct_wrap") );
	rating.init();
	
	var reviewContents = new ReviewContents( $(".ct_wrap") );
	reviewContents.init();
	
});


