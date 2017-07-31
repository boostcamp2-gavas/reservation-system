var CommentList = (function() {
    var root;
    var productId;
    var page = 1;
    function init(pRoot) {
        root = pRoot;
        productId = rPoot.data('product-id');
        bindEvents();
    }
    function bindEvents() {
        $(window).scroll(loadList);
    }
    function loadList() {
        if(root.data('comment-count') > page * 10) {
            var maxHeight = $(document).height();
            var currentScroll = $(window).scrollTop() + $(window).height();
            if(maxHeight - currentScroll < 30) {
                var url = '/comments/' + 
            }
        }
    }
    return {
        init: init
    }
})();