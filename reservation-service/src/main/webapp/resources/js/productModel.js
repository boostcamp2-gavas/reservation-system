var ProductModel = (function(){
    var productCashedData = {};
    var offsetCashedData = {};
    var categoryId;
    var changeEvent = 0;
    var offset;

    function getProduct(fp){
        var url = '/api/categories/'+categoryId+'/products?offsetId=';

        if(productCashedData[url] != null && changeEvent ===1){
            fp(productCashedData[url]);
            changeEvent = 0;
        } else {
            offset = offsetCashedData[url] || 0;
            changeEvent = 0;

            $.ajax(url+offset).then(function(data){
                fp(data);
                if(offset === 0) {
                    productCashedData[url] = data;
                }
                offsetCashedData[url] = data[data.length-1].id;
            })
        }
    }

    function setCategoryId(id){
        categoryId = id;
    }

    function setChangeEventVal(val){
        changeEvent = val;
    }

    return {
        getProduct : getProduct,
        setCategoryId : setCategoryId,
        setChangeEventVal : setChangeEventVal
    }

})();