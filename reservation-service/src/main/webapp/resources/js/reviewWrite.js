define("reviewWrite", ["Handlebars", "rating"], function(Handlebars, Rating) {
	"use strict";

	function ReviewWrite() {
		this.initVariable();
		this.addEventHandling();
	}

	ReviewWrite.prototype.initVariable = function() {
		this.APIURL = "/reviews/api";
		this.IMG_URL = "/images";
		this.THUMB_MAX_COUNT = 5;
		this.MAX_COMMENT_LENGTH = 400;
		this.MIN_COMMENT_LENGTH = 5;
		this.$enrollForm = $("._enrollForm");
		this.$commentLabel = $("#commentLabel");
		this.$commentText = $("#comment");
		this.$imageInput = $("#reviewImageFileInput");
		this.$thumbnailUploadWrapper= $("._uploadWrapper");
		this.$thumbnailUploadList = $("._thumbnailUploadList");
		this.thumbItem = "._thumb";
		this.thumbnailUploadCount = 0;
		this.$numOfText = $("._numOfText");
		this.$score = $("._score");

		this.regCheckNoWhitespace = /.*\S+.*/;
		this.commentThumbnailTemplate = Handlebars.compile($("#commentWrite-thumbnail-template").html());

		this.rating = new Rating("._rating");
	}

	ReviewWrite.prototype.addEventHandling = function() {
		this.$enrollForm.on("submit", this.submitCommentHandling.bind(this));
		this.$commentLabel.on("click", function(e){
			$(this).addClass("hide");
		});

		this.$commentText.on("focusout", this.commentFocusOutHandling.bind(this));
		this.$commentText.on("keyup", this.keyUpHandling.bind(this));

		this.$imageInput.on("change", this.imageInputChangeHandling.bind(this));
		this.$thumbnailUploadWrapper.on("click", ".anchor", this.thumbnailDeleteHandling.bind(this));
		this.rating.on("change", this.ratingChangeCallback.bind(this))
	}

	ReviewWrite.prototype.reqCreateImageFiles = function(formData){
		$.ajax({
			url : this.APIURL + this.IMG_URL,
			data : formData,
			type : "POST",
			contentType : false,
			processData : false
		}).done(this.createImageFilesDoneHandling.bind(this))
		  .fail(this.ajaxFailHandling.bind(this))
		  .always(this.createImageFilesAlwaysHandling.bind(this));
	}

	ReviewWrite.prototype.createImageFilesDoneHandling = function(data, textStatus, xhr) {
		this.thumbnailUploadCount++;
		var strCommentThumbnailTemplate = "";
		for(var i=0; i<data.length; i++){
			strCommentThumbnailTemplate += this.commentThumbnailTemplate(data[i]);
		}
		this.$thumbnailUploadList.append(strCommentThumbnailTemplate);
	}

	ReviewWrite.prototype.ajaxFailHandling = function(jqXHR){
		alert(jqXHR.responseJSON.message);
	}

	ReviewWrite.prototype.createImageFilesAlwaysHandling = function() {
		this.$imageInput.val("");
	}

	ReviewWrite.prototype.submitCommentHandling = function(e){
		if(!this.checkCommentValidation()){
				e.preventDefault();
		}
	}

	ReviewWrite.prototype.checkCommentValidation = function(){
		var strEnteredComment = this.$commentText.val();
		if(this.isBlank(strEnteredComment)){
			alert("공백만 입력하면 안돼요");
			return false;
		}
		if(strEnteredComment.length<=this.MIN_COMMENT_LENGTH){
			alert("최소 "+this.MIN_COMMENT_LENGTH+"자 이상 리뷰남겨주세요");
			return false;
		}
		return true;
	}

	ReviewWrite.prototype.commentFocusOutHandling = function(e) {
		var strEnteredComment = $(e.currentTarget).val();
		if(this.isBlank(strEnteredComment)){
			this.$commentLabel.removeClass("hide");
		}
	}

	ReviewWrite.prototype.isBlank = function(str) {
		return !this.regCheckNoWhitespace.test(str);
	}

	ReviewWrite.prototype.thumbnailDeleteHandling = function(e) {
		e.preventDefault();
		var $thumbDeleteBtn = $(e.currentTarget);
		var $thumbItem = $thumbDeleteBtn.closest(this.thumbItem);
		var fileId = $thumbItem.data("id");

		this.reqDeleteImageFile($thumbItem, fileId);
	}

	ReviewWrite.prototype.reqDeleteImageFile = function($thumbItem, fileId){
		$.ajax({
			url : this.APIURL+this.IMG_URL+"/"+fileId,
			type : "DELETE"
		}).done(function(res){
			$thumbItem.remove();
		}).fail(this.ajaxFailHandling.bind(this));
	}

	ReviewWrite.prototype.imageInputChangeHandling = function(e){
		var currentCount = this.thumbnailUploadCount + this.$imageInput[0].files.length;

		if(currentCount>this.THUMB_MAX_COUNT) {
			alert("이미지 등록은 최대"+this.THUMB_MAX_COUNT+"개 입니다.");
			return false;
		} else {
			var formData = new FormData();
			$.each(this.$imageInput[0].files, function(i, file) {
				formData.append("images", file);
			});

			this.reqCreateImageFiles(formData);
		}
	}

	ReviewWrite.prototype.keyUpHandling = function() {
		var strEnteredComment = this.$commentText.val();
		if(strEnteredComment.length > this.MAX_COMMENT_LENGTH){
			alert(this.MAX_COMMENT_LENGTH+"자 초과");
			strEnteredComment = strEnteredComment.substring(0, this.MAX_COMMENT_LENGTH);
			this.$commentText.val(strEnteredComment);
		}
		this.$numOfText.text(strEnteredComment.length);
	}

	ReviewWrite.prototype.ratingChangeCallback = function(res) {
		this.$score.text(res.score);
		this.$score.removeClass("gray_star");
	}

	return ReviewWrite;
});
