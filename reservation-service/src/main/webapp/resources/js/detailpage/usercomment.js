$ = require('../../node_modules/jquery/dist/jquery');
totlTotalCommentInfoModel = require('./totalcommentinfomodel');
moment = require('../../node_modules/moment/moment');
Handlebars = require('../../node_modules/handlebars/dist/handlebars');
var FlickingComponent = require('../flickingcomponent');

var UserComment = (function(){
    var source = $("#comment-template").html();
    var template = Handlebars.compile(source);

    var commentImageSoruce = $("#commentImage-template").html();
    var commentImageTemplate = Handlebars.compile(commentImageSoruce);

    var cur_num = 1;
    var slide_width;
    var slide_count;
    var isOpen = 0;
    var flickingModule;

    function init(){
        totalCommentInfo();

    }

    function totalCommentInfo(){
        totlTotalCommentInfoModel.getTotalCommentInfo(function (data) {
            showUserComment(data);
            bindOnClickUserCommentImage();
        });
    }

    function showUserComment(data){
        data.forEach(function (item,i) {
            if( i === 3) {
                console.log("more");
            } else {
                $('.list_short_review').append(template(
                    {id : item.id, comment : item.comment, fileId : item.fileId, fileCount : item.fileCount,
                    nickName : item.nickName, score : item.score, createDate : moment(item.createDate).format('YYYY-MM-DD')}));
            }
        });
    }

    function bindOnClickUserCommentImage(){
        $('.thumb_area').on('click','.thumb',function(e){
            e.preventDefault();
            var commentId = $(this).closest('li').data('comment');

            totlTotalCommentInfoModel.getUserCommentImage(commentId, function(data){

                if (isOpen === 0) {
                    addCommentLi(data.fileId);
                }

                layerOpen('photoviwer');

                addFlickingComponent();
            });
        })

        $('.btn-r').on('click', '.cbtn', function () {
            isOpen = 0;
            cur_num = 1;
            flickingModule.flush();
            $('.detail_img li').remove();
            $('#photoviwer').fadeOut();
        })
    }

    function addCommentLi(data) {
        $('.detail_img').append(commentImageTemplate({fileData: data}));
    }

    function layerOpen(el) {
        isOpen = 1;
        slide_width = $('.detail_img > li').outerWidth();
        slide_count = $('.detail_img > li').length;
        var temp = $('#' + el);

        temp.fadeIn();

        if (temp.outerHeight() < $(document).height()) {
            temp.css('margin-top', '-' + temp.outerHeight() / 2 + 'px');
        }
        else {
            temp.css('top', '0px');
        }

        if (temp.outerWidth() < $(document).width()) {
            temp.css('margin-left', '-' + temp.outerWidth() / 2 + 'px');
        }
        else {
            temp.css('left', '0px');
        }
    }

    function addFlickingComponent() {
        flickingModule = new FlickingComponent($('.detail_img'));
    }

    return {
        init : init
    }

})();

module.exports = UserComment;