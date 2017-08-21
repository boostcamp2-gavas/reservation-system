var $ = require('../node_modules/jquery/dist/jquery');
var FlickingComponent = require('./flickingcomponent');

var LayerPopUp = (function(){

    var cur_num = 1;
    var slide_width;
    var slide_count;
    var isOpen = 0;
    var flickingModule = null;
    var root;

    function layerInit(el){
        root = el;

        $('.btn-r').on('click', '.pbtn', function (e) {
            e.preventDefault();
            if (cur_num != 1) {
                $(".detail_img").animate({"left": "+=" + slide_width + "px"}, "slow");
                cur_num--;
                flickingModule.minusCurNum();
            }
        });

        $('.btn-r').on('click', '.nbtn', function (e) {
            e.preventDefault();
            if (cur_num != slide_count) {
                $(".detail_img").animate({"left": "-=" + slide_width + "px"}, "slow");
                cur_num++;
                flickingModule.plusCurNum();
            }
        });


        $('.btn-r').on('click', '.cbtn', function (e) {
            e.preventDefault();
            isOpen = 0;
            cur_num = 1;
            flickingModule.flush();
            $('#photoviwer').fadeOut();
        });
    }

    function addfliking(){
        if (flickingModule === null) {
            flickingModule = new FlickingComponent($('.detail_img'));
            flickingModule.on('flick', function (e) {
                cur_num = e.curNum;
            })
        }
    }

    function layerOpen() {
        isOpen = 1;
        slide_width = $('.detail_img > li').outerWidth();
        slide_count = $('.detail_img > li').length;
        var temp = $('#' + root);

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

        addfliking();
    }

    function checkOpen(){
        return isOpen || false;
    }

    return {
        init : layerInit,
        open : layerOpen,
        checkOpen : checkOpen
    }

})();

module.exports = LayerPopUp;