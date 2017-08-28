var $ = require('../../node_modules/jquery/dist/jquery');
var totalCommentInfoModel = require('./totalcommentinfomodel');
var moment = require('../../node_modules/moment/moment');
var Handlebars = require('../../node_modules/handlebars/dist/handlebars');
var LayerPopUp = require('../layerpopup');

var UserComment = (function(){
    var $window = $(window);
    var $document = $(document);
    var source = $("#comment-template").html();
    var template = Handlebars.compile(source);
    var lastCommentId = 0;

    var commentImageSource = $("#commentImage-template").html();
    var commentImageTemplate = Handlebars.compile(commentImageSource);

    function init(){
        totalCommentInfo(lastCommentId, 4);
        LayerPopUp.init('photoveiwer');
    }

    function initMoreTotalComment(start, limit) {
        totalCommentInfoModel.getTotalCommentInfo(start, limit, function (data) {
            bindScrollUpdateProduct();
            showMoreUserComment(data);
            bindOnClickUserCommentImage();
            LayerPopUp.init('photoveiwer');
        });
    }

    function scrollCallback() {
        if ($window.scrollTop() === $document.height() - $window.height()) {
            totalCommentInfoModel.getTotalCommentInfo(lastCommentId, 10, function (data) {
                showMoreUserComment(data);
                bindOnClickUserCommentImage();
            });
        }
    }

    function bindScrollUpdateProduct() {
        $window.on("scroll", scrollCallback);
    }

    function preventScrollEvent(length) {
        if( length < 10 ) {
            $window.off("scroll", scrollCallback);
        }
    }

    function showMoreUserComment(data) {
        preventScrollEvent(data.length);
        var $root = $('.list_short_review');
        data.forEach(function (item) {
           appendUserComment($root, item);
           lastCommentId = item.id;
        });

    }

    function totalCommentInfo(start, limit){
        totalCommentInfoModel.getTotalCommentInfo(start, limit, function (data) {
            showUserComment(data);
            bindOnClickUserCommentImage();
        });
    }

    function showUserComment(data) {
        preventScrollEvent(data.length);
        var $commentRoot = $('.list_short_review');
        data.forEach(function (item,i) {
            if( i === 3) {
                bindOnReviewPage();
            } else {
                appendUserComment($commentRoot,item);
            }
        });
    }

    function bindOnReviewPage(){
        $('.btn_review_more').show();
        $('.btn_review_more').on('click',function(e){
            e.preventDefault();
            location.href = "/details/" + $('#gavas').data('productid') + "/comments";
        });
    }

    function appendUserComment($root, item){
        $root.append(template(
            {id : item.id, comment : item.comment, fileId : item.fileId, fileCount : item.fileCount,
                nickName : item.nickName, score : item.score, createDate : moment(item.createDate).format('YYYY-MM-DD')}));
    }

    function bindOnClickUserCommentImage(){
        var $commentImageRoot = $('.detail_img');
        $('.thumb_area').on('click','.thumb',function(e){
            e.preventDefault();

            var commentId = $(this).closest('li').data('comment');
            totalCommentInfoModel.getUserCommentImage(commentId, function(data){
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
        init : init,
        initMore : initMoreTotalComment
    }

})();

module.exports = UserComment;