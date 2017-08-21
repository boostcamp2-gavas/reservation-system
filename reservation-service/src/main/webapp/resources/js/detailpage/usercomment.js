$ = require('../../node_modules/jquery/dist/jquery');
totlTotalCommentInfoModel = require('./totalcommentinfomodel');
moment = require('../../node_modules/moment/moment');
Handlebars = require('../../node_modules/handlebars/dist/handlebars');

var UserComment = (function(){
    var source = $("#comment-template").html();
    var template = Handlebars.compile(source);

    function init(){
        totalCommentInfo();
    }

    function totalCommentInfo(){
        totlTotalCommentInfoModel.getTotalCommentInfo(function (data) {
            showUserComment(data);
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

    return {
        init : init
    }

})();

module.exports = UserComment;