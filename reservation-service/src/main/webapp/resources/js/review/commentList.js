var CommentList = (function() {
    var root;
    var productId;
    var commentCount;
    var page = 1;
    var template;
    var isChecking = false;
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
            if(maxHeight - currentScroll < 30 && !isChecking) {
                isChecking = true;
                var url = '/api/comments/' + productId + '?start=' + page;
                callAjax(url).then(display, function(request, status, error) {
                    isChecking = false;
                    console.log(error);
                });
            }
        }
    }
    function display(res) {
        console.log(res);
        isChecking = false;
        page++;
        var obj = {
            items : res
        }
        root.append(template(obj));
    }
    return {
        init: init
    }
})();