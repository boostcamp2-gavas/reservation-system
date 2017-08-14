
var ProductListModule = require('./productList');
var Carousel = require('./carousel');
var $ = require('../node_modules/jquery/dist/jquery');

(function (){
    var productListModule = ProductListModule.getInstance();
    var carousel = new Carousel($('.group_visual'));
    productListModule.init();
})();