var ProductListModule = (function (){

    var activeAnchorIndex = 0;
    var categorySoruce = $("#category-template").html();
    var categoryTemplate = Handlebars.compile(categorySoruce);

    var productSoruce = $("#product-template").html();
    var productTemplate = Handlebars.compile(productSoruce);

    function init(){
        $('.event_tab_lst.tab_lst_min').on('click','li',bindTabElement);
        getCategoryList();

        ProductModel.setCategoryId(activeAnchorIndex);
        ProductModel.getProduct(function(data){
            addProductList('html',data);
        });

        bindScrollUpdateProduct();
    }

    function bindTabElement(ele){
        $(".event_tab_lst li[data-category='"+activeAnchorIndex+"'] a").removeClass('active');
        $(ele.currentTarget).find('.anchor').addClass("active");
        activeAnchorIndex = $(ele.currentTarget).data("category");

        ProductModel.setCategoryId(activeAnchorIndex);
        ProductModel.setChangeEventVal(1);
        ProductModel.getProduct(function(data){
            addProductList('html',data);
        });
    }

    function bindScrollUpdateProduct(){
        $(window).scroll(function(){
            if ($(window).scrollTop() === $(document).height() - $(window).height()) {
                ProductModel.getProduct(function(data){
                    addProductList('append',data);
                });

            }
        });
    }

    function getCategoryList(){
        $.ajax("/api/categories").then(function(data){
            addCategoryList(data)
        });
    }

    function addCategoryList(data){
        var context = {'data': data};
        var html = categoryTemplate(context);
        $('.event_tab_lst.tab_lst_min').append(html);
    }

    function addProductList(type,data){
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
        init : init
    }
})();