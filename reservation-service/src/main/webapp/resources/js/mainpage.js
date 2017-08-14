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
})();