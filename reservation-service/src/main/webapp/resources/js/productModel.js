var ProductModel = (function(){
    var productCachedData = {};
    /*
         지현  : productCashedData[url].offset 이런식으로 할 수가 없더라고요.. 생각해보면 ket : val 이런식으로 오브젝트에 저장되는데
         그렇게 저장된 오브젝트에 다시 변수를 생성한다는게 불가능한 것으로 보이는데 제 생각이 아닌거면 수정 부탁드립니다
         석호 : 어거지로 하려면 productCashedData[].data, .offset이렇게 하면 될텐데 바꾸려니까 엄청 더러워지고 가독성이 거의 지옥으로 가더라고 니가 잘 짠거 같음
    */
    var categoryId;

    /*
        지현 : 일단 제 해결방안은 체인지 이벤트만 발생시켜서 offset만 변경하는 방법입니다.
        다시 말해서, 탭 이동 이벤트가 발생하면 changeEvent 변수값을 1로 변경해주고 캐시된 데이터가 있으면 그대로 보여주고 다시 changeEvent값을 0으로 바꿔주는 방법입니다.
        그리고 더보기 행위를 통해 네트워크 요청이 발생되면 changeEvent값을 0으로 병경하여 더보기를 계속 진행할 수 있도록 했습니다.

        석호 : 결국 현재 요청이 새 요청이냐 다시 캐시된 데이터를 불러오냐를 구분짓는 건데 일단 offset이라는 변수롤 새로 사용할 필요는 없는거 같아서 기존의 캐시된 offset array를 재사용하도록 작성해 봤어
        그리고, 여기 안에서 event라는 flag를 사용하고 0,1 만 활용하기 때문에 그걸 boolean으로 바꿔서 작성해 봤는데, 쭉 보니 이걸 이 모듈에서 굳이 상태를 저장할 필요가 있나 싶더라고
        요청이 더보기 요청이냐 카테고리를 넘기냐는 건데, 프로덕트 모듈이 이 요청에 대한 데이터를 캐싱하면
        의존성이 프로덕트 리스트와 모델이 서로 상호작용하는 구조로 가는거 같아서(프로덕트 모델은 프로덕트에 대한 데이터 캐싱과 서버통신만을 맡는다고 생각해, 이 모델이 리스트의 상태를 알면 안되지 않을까?)
        사용자 입장인 리스트에서 요청을 구분 지어 보내는게 좀 더 이상적이지 않나 싶어서 getProduct를 호출하는 곳에서 새 데이터를 캐싱할꺼냐 안할꺼냐를 선택해 주는 형태로 바꿔봤어
        한번 읽어보고 생각이 다르면 다시 작성해보자
     */

    /*
        석호 :  추가적으로 지금 예외가 발생하는데 더보기에서 더이상 프로덕트가 없는데 서버로 요청을 하니 계속 exception이 떨어지는데 이걸 어떻게 해결해야 할까
        이건 익셉션이 아니라 의도된 상황이라는게 문제인데 이걸 어떻게 핸들링 하는지 한번 고민해 봐야 할거 같음
        지금 떠오르는 생각은
         1. 이 상황에 맞는 exception을 만들어서 throw해주고 클라에 리턴되는건 아무것도 없는 형태
         2. 마찬가지로 throw하는데 더이상 데이터가 없는 것이니까 특정 리턴값을 통해 클라에 알리고 더보기 버튼을 숨기는 형태
         두개가 떠오르네
         2번으로 하면 클라에서도 다시 캐시된 데이터를 읽을때 더보기 버튼을 show()해주고 해당하는 로직이 필요할것 같음
     */

    function getProduct(flag, fp){
        var url = '/api/categories/'+categoryId+'/products?offsetId=';

        if(productCachedData[url] !== undefined && flag){
            var data = productCachedData[url].data;
            fp(data);
            productCachedData[url].offset = data[data.length-1].id;
        } else {
            var offset = productCachedData[url] ? productCachedData[url].offset : 0;

            $.ajax(url+offset).then(function(data){
                if(data.length !== 0){
                    fp(data);
                    var temporalCachedData = {
                        data : data,
                        offset : data[data.length-1].id
                    };
                    if(offset === 0) {
                        productCachedData[url] = temporalCachedData;
                    } else{
                        productCachedData[url].offset = temporalCachedData.offset;
                    }
                }
            });
        }
    }

    function setCategoryId(id){
        categoryId = id;
    }

    return {
        getProduct : getProduct,
        setCategoryId : setCategoryId
    }

})();