var ProductModel = (function(){
    var productCashedData = {};
    /*
         productCashedData[url].offset 이런식으로 할 수가 없더라고요.. 생각해보면 ket : val 이런식으로 오브젝트에 저장되는데
         그렇게 저장된 오브젝트에 다시 변수를 생성한다는게 불가능한 것으로 보이는데 제 생각이 아닌거면 수정 부탁드립니다
    */
    var offsetCashedData = {};
    var categoryId;
    var changeEvent = 0;
    var offset;
    
    /*
        일단 제 해결방안은 체인지 이벤트만 발생시켜서 offset만 변경하는 방법입니다.
        다시 말해서, 탭 이동 이벤트가 발생하면 changeEvent 변수값을 1로 변경해주고 캐시된 데이터가 있으면 그대로 보여주고 다시 changeEvent값을 0으로 바꿔주는 방법입니다.
        그리고 더보기 행위를 통해 네트워크 요청이 발생되면 changeEvent값을 0으로 병경하여 더보기를 계속 진행할 수 있도록 하는 방법입니다.
     */

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

    function toggleChangeEventVal(){
        changeEvent === 0 ? changeEvent = 1 : changeEvent = 0;
    }

    return {
        getProduct : getProduct,
        setCategoryId : setCategoryId,
        toggleChangeEventVal : toggleChangeEventVal
    }

})();