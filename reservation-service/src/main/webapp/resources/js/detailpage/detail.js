var $ = require('../../node_modules/jquery/dist/jquery');
var ProductDetail = require('./productdetail');
var UserComment = require('./usercomment');
var LazyLoad = require('./lazyload');
var NaverMap = require('./navermap');
var FlickingComponent = require('../newflickingcomponent');
var ProductDetailModel = require('./productdetailmodel');


(function () {
    ProductDetail.init();

    var productDetailModel = ProductDetailModel.getDetail();
    productDetailModel.getDetails(function (data) {
        ProductDetail.showProductDetail(data);

        var flicking = new FlickingComponent($('.visual_img'));
        flicking.on("flick",function (e) {
            ProductDetail.changeSlideCountByFlicking(e.curNum);
        });
    });

    UserComment.init();

    var lazyLoad = LazyLoad.getInstance();
    lazyLoad.init($('.detail_info_lst.lazy_section'));

    NaverMap.showMap();
})();