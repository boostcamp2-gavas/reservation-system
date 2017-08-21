$ = require('../../node_modules/jquery/dist/jquery');

var TotalCommentInfoModel = (function () {

    var totalCommentInfoCash = {};

    function getTotalCommentInfo(fp){
        var url = "/api/products/"+$('#gavas').data('productid')+"/usercommnets?commentid=0&limit=4";

        if (totalCommentInfoCash[url] != null) {
            fp(totalCommentInfoCash[url]);
        } else {
            $.ajax(url).then(function(data){
                totalCommentInfoCash[url] = data;
                fp(data);
            });
        }
    }

    function getUserCommentImage(id, fp) {
        var url = "/api/usercomments/"+id+"/images";

        if (totalCommentInfoCash[url] != null) {
            fp(false);
        } else {
            $.ajax(url).then(function(data){
                totalCommentInfoCash[url] = data;
                fp(data);
            });
        }
    }

    return {
        getTotalCommentInfo : getTotalCommentInfo,
        getUserCommentImage : getUserCommentImage
    }

})();

module.exports = TotalCommentInfoModel;