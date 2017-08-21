$ = require('../../node_modules/jquery/dist/jquery');

var TotalCommentInfoModel = (function () {

    var totalCommentInfoCash = {};

    function getTotalCommentInfo(fp){
        var url = "/api/products/"+$('#gavas').data('productid')+"/usercommnets?commentid=0&limit=4";

        if (totalCommentInfoCash[url] != null) {
            fp(data);
        } else {
            $.ajax(url).then(function(data){
                totalCommentInfoCash[url] = data;
                fp(data);
            },function(){
                console.log("hi");
            })
        }
    }

    return {
        getTotalCommentInfo : getTotalCommentInfo
    }

})();

module.exports = TotalCommentInfoModel;