var ProductModel = require('./productModel');
var Handlebars = require('../../node_modules/handlebars/dist/handlebars');
var $ = require('../../node_modules/jquery/dist/jquery');

var ProductListModule = (function () {

    function showProductList() {
        var activeAnchorIndex = 0;
        var categorySource = $("#category-template").html();
        var categoryTemplate = Handlebars.compile(categorySource);

        var productSource = $("#product-template").html();
        var productTemplate = Handlebars.compile(productSource);
        var $window = $(window);
        var $document = $(document);

        function init() {
            $('.event_tab_lst.tab_lst_min').on('click', 'li', bindTabElement);
            getCategoryList();

            ProductModel.setCategoryId(activeAnchorIndex);
            ProductModel.getProduct(false, function (data) {
                addProductList('html', data);
            });

            ProductModel.getProductCount(function (data) {
                addProductListCount(data);
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

            ProductModel.getProductCount(function (data) {
                addProductListCount(data);
            });
        }

        function bindScrollUpdateProduct() {
            $(window).scroll(function () {
                if ($window.scrollTop() === $document.height() - $window.height()) {
                    ProductModel.getProduct(false, function (data) {
                        addProductList('append', data);
                    });
                }
            });
        }

        function getCategoryList() {
            $.ajax("/api/categories").then(function (data) {
                addCategoryList(data);
                $(".event_tab_lst li[data-category='" + data[data.length - 1].id + "'] a").addClass('last');
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
            });
            $('.lst_event_box.left_box')[type](productTemplate({'data': left}));
            $('.lst_event_box.right_box')[type](productTemplate({'data': right}));
        }

        function addProductListCount(data) {
            $('.event_lst_txt .pink').text(data + "개");
        }

        return {
            init: init
        }
    }

    return {
        getInstance: showProductList
    }
})();

module.exports = ProductListModule;