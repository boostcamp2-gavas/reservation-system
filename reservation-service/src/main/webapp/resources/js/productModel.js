var $ = require('../node_modules/jquery/dist/jquery');

var ProductModel = (function(){
    var productCachedData = {};
    var categoryId;

    function getProduct(isChangedCategory, fp){
        var url = '/api/categories/'+categoryId+'/products?offsetId=';

        if(productCachedData[url] !== undefined && isChangedCategory){
            var data = productCachedData[url].data;
            fp(data);
            productCachedData[url].offset = data[data.length-1].id;
        } else {
            var offset = productCachedData[url] ? productCachedData[url].offset : 0;

            $.ajax(url+offset).then(function(data){
                if(data.length !== 0){
                    fp(data);
<<<<<<< HEAD

                    var temporalCachedData = {
                        data : data,
                        offset : data[data.length-1].id
                    };

=======
>>>>>>> babded834d2e62875d2ca2a180efa0a65c1986f6
                    if(offset === 0) {
                        productCachedData[url] = {
                            data : data,
                            offset : data[data.length-1].id
                        };
                    } else{
                        productCachedData[url].offset = data[data.length-1].id;
                    }
                }
            });
        }
    }

    function getProductCount(fp){
        var url = '/api/categories/'+categoryId+'/productscount';

        if(productCachedData[url] !== undefined){
            fp(productCachedData[url].count);
        } else {
            $.ajax(url).then(function(data) {
                fp(data);
                productCachedData[url] = {
                    count : data
                }
            });
        }

    }

    function setCategoryId(id){
        categoryId = id;
    }

    return {
        getProduct : getProduct,
        getProductCount : getProductCount,
        setCategoryId : setCategoryId
    }

})();

module.exports = ProductModel;