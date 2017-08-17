var $ = require('../../node_modules/jquery/dist/jquery');
var RatingComponent = require('./ratingcomponent');

var ReviewInsert = (function () {
    var uploadFileList = [];

    function init() {
        initRating();
        bindOnfocus();
        uploadImage();
    }

    function bindOnfocus () {
        $('.review_write_info').on('click',function () {
            $(this).hide();
            $('.review_textarea').focus();
        })

        $('.review_textarea').on('focusout',function () {
            if ($(this).val().length === 0){
                $('.review_write_info').show();
            }
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

            if( curFilesNum === 6){
                return;
            }

            readImgFile(uploadFileList,file);

            curFilesNum++;
        }
    }

    function readImgFile(uploadFileList,file){
        uploadFileList.push(file);

        var img = new Image();
        img.width = 130;
        img.alt = "";
        img.file = file;
        $(img).addClass("item_thumb");

        var reader = new FileReader();

        reader.onload = (function(aImg) { return function(e) {
            aImg.src = e.target.result;
            var html = $(".item").clone();
            html.find("a").after(aImg);
            $(html.get(0)).appendTo(".lst_thumb");
        };})(img);

        reader.readAsDataURL(file);
    }

    return {
        init : init
    }
})();

module.exports = ReviewInsert;