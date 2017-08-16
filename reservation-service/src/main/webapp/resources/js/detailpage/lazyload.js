var $ = require('../../node_modules/jquery/dist/jquery');

var LazyLoad = (function () {

    function LazyLoad() {
        var $srcRoot;
        var srcImage;
        var lazyTarget;

        var init = function ($root) {
            $srcRoot = $root.find('.in_img_group .img_thumb');
            srcImage = $srcRoot.data('lazy-image');
            lazyTarget = $root.get(0);
            bindScroll();
        };

        function bindScroll() {
            $(window).scroll(function () {
                if (isInViewport(lazyTarget)) {
                    $srcRoot.attr('src', srcImage);
                }
            });
        }


        function isInViewport(ele) {
            var rect = ele.getBoundingClientRect();

            return (
                rect.bottom >= 0 &&
                rect.right >= 0 &&
                rect.top <= (window.innerHeight || document.documentElement.clientHeight) &&
                rect.left <= (window.innerWidth || document.documentElement.clientWidth)
            );
        }

        return {
            init: init
        }

    }

    return {
        getInstance: LazyLoad
    }
})();

module.exports = LazyLoad;