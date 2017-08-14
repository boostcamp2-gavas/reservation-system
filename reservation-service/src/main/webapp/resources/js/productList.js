var ProductListModule = (function () {

    return function(){
        var activeAnchorIndex = 0;
        var categorySource = $("#category-template").html();
        var categoryTemplate = Handlebars.compile(categorySource);

        var productSource = $("#product-template").html();
        var productTemplate = Handlebars.compile(productSource);

        var _window = $(window);

        function init() {
            $('.event_tab_lst.tab_lst_min').on('click', 'li', bindTabElement);
            getCategoryList();

            ProductModel.setCategoryId(activeAnchorIndex);
            ProductModel.getProduct(false, function (data) {
                addProductList('html', data);
            });

            bindScrollUpdateProduct();
        }

        function bindTabElement(ele) {
            $(".event_tab_lst li[data-category='" + activeAnchorIndex + "'] a").removeClass('active');
            $(ele.currentTarget).find('.anchor').addClass("active");
            activeAnchorIndex = $(ele.currentTarget).data("category");

            ProductModel.setCategoryId(activeAnchorIndex);
            ProductModel.getProduct(true, function (data) {
                addProductList('html', data);
            });
        }

        function bindScrollUpdateProduct() {
            _window.scroll(function () {
                if (_window.scrollTop() === $(document).height() - _window.height()) {
                    ProductModel.getProduct(false, function (data) {
                        addProductList('append', data);
                    });
                }
            });
        }

        function getCategoryList() {
            $.ajax("/api/categories").then(function (data) {
                addCategoryList(data)
                $(".event_tab_lst li[data-category='"+data[data.length-1].id+"'] a").addClass('last');
            });
        }

        function addCategoryList(data) {
            var context = {'data': data};
            var html = categoryTemplate(context);
            $('.event_tab_lst.tab_lst_min').append(html);
        }

        function addProductList(type, data) {
            var left = [];
            var right = [];
            data.forEach(function (item, i) {
                if (i % 2 === 0) {
                    left.push(item);
                } else {
                    right.push(item);
                }
            })
            $('.lst_event_box.left_box')[type](productTemplate({'data': left}));
            $('.lst_event_box.right_box')[type](productTemplate({'data': right}));
        }

        return {
            init: init
        }
    }
})();