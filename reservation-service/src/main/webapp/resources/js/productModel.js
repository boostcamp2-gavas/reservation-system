var ProductModel = (function(){
    var productCashedData = {};
    var categoryId;
    var offset;

    function getProduct(flag,fp){
        var url = '/api/categories/'+categoryId+'/products?offsetId=';
        if(productCashedData[url] != null && productCashedData[url].offset === 0){
            fp(productCashedData[url]);
            productCashedData[url].offset = data[data.length-1].id;
        } else {
            offset = productCashedData[url].offset || 0;

            $.ajax(url+offset).then(function(data){
                fp(data);
                if(productCashedData[url].offset === 0) {
                    productCashedData[url].data = data;
                }
                productCashedData[url].offset = data[data.length-1].id;
            })
        }
    }

    function setCategoryId(catId, offId){
        categoryId = id;
        offId = offId;
    }

    return {
        getProduct : getProduct,
        setCategoryId : setCategoryId
    }

})();