var $ = require('../../node_modules/jquery/dist/jquery');
var Jmoment = require('../../node_modules/moment-jdateformatparser/moment-jdateformatparser');
var RatingComponent = require('./ratingcomponent');

var ReviewInsert = (function () {
    var uploadFileList = [];
    var productId = $('#gavas').data('productid');
    var userId = $('#gavas').data('userid');

    function init() {
        initRating();
        bindOnTextArea();
        uploadImage();
        bindOnRegisterReviewBtn();
    }

    function bindOnTextArea () {
        $('.review_write_info').on('click',function () {
            $(this).hide();
            $('.review_textarea').focus();
        });

        $('.review_textarea').on('focusout',function () {
            if ($(this).val().length === 0){
                $('.review_write_info').show();
            }
        });

        $('.review_textarea').on('keyup',function(e){
            var $textArea = $(this);
            var $textCount = $('.guide_review span:first');
            $textCount.text($textArea.val().length);
        })
    }

    function initRating(){
        var rating = new RatingComponent($('.rating'));
        rating.on("score",function(e){
            $('.star_rank').removeClass("gray_star");
            $('.star_rank').text(e.score);
        });
    }

    function uploadImage(){
        var $eleFile = $("#reviewImageFileOpenInput");

        $(".lst_thumb").on("click",".ico_del",function (e) {
            deleteFileList(uploadFileList,e);
        });

        $eleFile.change(function(e){
            var fileList = this.files;
            var filesNum = fileList.length;
            var curFilesNum = $('.item').length;

            if(filesNum > 5 || curFilesNum === 6){
                alert("이미지는 최대 5장까지 업로드할 수 있습니다.");
            }

            showImgFile(fileList,uploadFileList,curFilesNum);
        });
    }

    function deleteFileList(uploadFileList,e){
        var deleteFileIndex = $(".ico_del").index(this)-1;
        uploadFileList.splice(deleteFileIndex,1);
        e.currentTarget.closest(".item").remove();
    }

    function showImgFile(fileList,uploadFileList,curFilesNum){
        var filesNum = fileList.length;
        for (var i = 0; i < filesNum; i++) {
            var file = fileList[i];
            var imageTypeJpeg = /^image\/jpeg/;
            var imageTypePng = /^image\/png/;

            if (!imageTypeJpeg.test(file.type) && !imageTypePng.test(file.type)) {
                alert("이미지 확장자는 .png, .jpeg만 가능합니다.")
                continue;
            }

            if (file.size > 1048576) {
                alert("파일 사이즈는 1mb보다 작아야 합니다.")
                continue;
            }

            if( curFilesNum > 5){
                return;
            }

            uploadFileList.push(file);

            readImgFile(file);

            curFilesNum++;
        }
    }

    function readImgFile(file){
        var img = new Image();
        img.width = 130;
        img.alt = "";
        img.file = file;
        $(img).addClass("item_thumb");

        var reader = new FileReader();

        reader.onload = (function(aImg) { return function(e) {
            aImg.src = e.target.result;
            var $html = $(".item").clone();
            $html.find("a").after(aImg);
            $($html.get(0)).appendTo(".lst_thumb");
        };})(img);

        reader.readAsDataURL(file);
    }

    function bindOnRegisterReviewBtn(){
        $('.box_bk_btn').on('click','.bk_btn',enrollReviewInfo);
    }

    function enrollReviewInfo(){
        var url = "/api/reviews";

        if ($('.review_textarea').val().length === 0){
            alert("리뷰내용을 입력해주세요");
            return;
        }
        if (userId === "") {
            alert("로그인 후 이용이 가능합니다.");
            return;
        }

        var formData = makeFormData(uploadFileList);

        $.ajax(url,{
            type: "POST",
            processData: false,
            contentType: false,
            data: formData
        }).then(function(data){
            alert("리뷰 등록을 완료하였습니다.");
            location.href="/reservations";
        }).fail(function () {
            console.log("fail");
        })
    }

    function makeFormData(uploadFileList){
        var formData = new FormData();
        var reviewData = JSON.stringify({
            "productId" : productId,
            "userId" : userId,
            "score" : $('.star_rank').text(),
            "comment" : $('.review_contents.write textarea').val(),
            "createDate" : Jmoment().formatWithJDF("yyyy-MM-dd'T'HH:mm:ss.SSSZ"),
            "modifyDate" : Jmoment().formatWithJDF("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        });

        formData.append('review', reviewData);
        uploadFileList.forEach(function(file){
            formData.append("files",file);
        })

        return formData;
    }

    return {
        init : init
    }
})();

module.exports = ReviewInsert;