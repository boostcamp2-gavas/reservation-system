var CommentList = (function() {
    var root;
    var productId;
    var commentCount;
    var page = 1;
    var template;
    function init(pRoot) {
        root = pRoot;
        productId = pRoot.data('product-id');
        commentCount = root.data('comment-count');
        template = Handlebars.compile($('#comment_list_template').html());
        bindEvents();
    }
    function bindEvents() {
        $(window).scroll(check);
    }
    function check() {
        if(commentCount > page * 10) {
            var maxHeight = $(document).height();
            var currentScroll = $(window).scrollTop() + $(window).height();
            if(maxHeight - currentScroll < 30) {
                var url = '/api/comments/' + productId + '?start=' + page;
                callAjax(url).then(display)
            }
        }
    }
    function display(res) {
        page++;
        console.log(res);
        root.append(template(res));
    }
    return {
        init: init
    }
})();