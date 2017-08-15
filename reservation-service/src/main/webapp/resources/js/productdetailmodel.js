var $ = require('../node_modules/jquery/dist/jquery');

var ProductDetailModel = (function () {

    function getDetail() {
        var url = "/api/products/" + $('#gavas').data('productid') + "/details"

        function getDetails(fp) {
            $.ajax(url).then(function (data) {
                fp(data);
            })
        }

        return {
            getDetails: getDetails
        }
    }

    return {
        getDetail: getDetail
    }
})();

module.exports = ProductDetailModel;