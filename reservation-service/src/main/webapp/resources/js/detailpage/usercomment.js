$ = require('../../node_modules/jquery/dist/jquery');
totlTotalCommentInfoModel = require('./totalcommentinfomodel');
moment = require('../../node_modules/moment/moment');
Handlebars = require('../../node_modules/handlebars/dist/handlebars');
var LayerPopUp = require('../layerpopup');

var UserComment = (function(){
    var source = $("#comment-template").html();
    var template = Handlebars.compile(source);

    var commentImageSoruce = $("#commentImage-template").html();
    var commentImageTemplate = Handlebars.compile(commentImageSoruce);

    function init(){
        totalCommentInfo();
        LayerPopUp.init('photoviwer');
    }

    function totalCommentInfo(){
        totlTotalCommentInfoModel.getTotalCommentInfo(function (data) {
            showUserComment(data);
            bindOnClickUserCommentImage();
        });
    }

    function showUserComment(data){
        var $commentRoot = $('.list_short_review');
        data.forEach(function (item,i) {
            if( i === 3) {
                bindOnReviewPage();
            } else {
                appenUserComment($commentRoot,item);
            }
        });
    }

    function bindOnReviewPage(){
        $('.btn_review_more').show();
        $('.btn_review_more').on('click',function(e){
            e.preventDefault();
            console.log("next");
        });
    }

    function appenUserComment($root, item){
        $root.append(template(
            {id : item.id, comment : item.comment, fileId : item.fileId, fileCount : item.fileCount,
                nickName : item.nickName, score : item.score, createDate : moment(item.createDate).format('YYYY-MM-DD')}));
    }

    function bindOnClickUserCommentImage(){
        var $commentImageRoot = $('.detail_img');
        $('.thumb_area').on('click','.thumb',function(e){
            e.preventDefault();

            var commentId = $(this).closest('li').data('comment');
            totlTotalCommentInfoModel.getUserCommentImage(commentId, function(data){
                if(!LayerPopUp.checkOpen()) {
                    addCommentLi(data,$commentImageRoot);
                }

                LayerPopUp.open();
            });
        })
    }

    function addCommentLi(data, $root) {
        if(data !== false) {
            var fileIdList = [];

            data.forEach(function (item) {
                var fileIdObject = {};
                fileIdObject["fileId"] = item;
                fileIdList.push(fileIdObject);
            });

            $root.append(commentImageTemplate({fileData: fileIdList}));
        }
    }

    return {
        init : init
    }

})();

module.exports = UserComment;