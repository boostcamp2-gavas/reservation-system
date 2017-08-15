var ProductListModule = require('./productList');
var Carousel = require('./carousel');
var $ = require('../node_modules/jquery/dist/jquery');
var Timer = require('./timer');

(function (){
    var productListModule = ProductListModule.getInstance();
    var carousel = new Carousel($('.group_visual'));
    var timer = Timer.getInstance(carousel);

    timer.init();

    carousel.on("stopTimer",function(){
        timer.stopInterval();
        timer.setTimeOut();
    });

    carousel.on("clickBtn",function() {
        timer.stopInterval();
        timer.setTimeOut();
    });

    productListModule.init();

    viewProductDetail();

    function viewProductDetail() {
        var productId;
        var url = 'details/';

        $('.wrap_event_box').on('click','.item',function(){
            productId = $(this).data('productid');
            location.href = url+productId;
        })
    }
})();