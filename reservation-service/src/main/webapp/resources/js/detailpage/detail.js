var $ = require('../../node_modules/jquery/dist/jquery');
var ProductDetail = require('./productdetail');
var LazyLoad = require('./lazyload');
var NaverMap = require('./navermap');

(function () {
    ProductDetail.init();
    ProductDetail.showProductDetail();

    var lazyLoad = LazyLoad.getInstance();
    lazyLoad.init($('.detail_info_lst.lazy_section'));

    NaverMap.showMap();
})();