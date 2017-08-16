var navermap = (function(){

    function showMap(){
        naver.maps.Service.geocode({
            address: '공원로 6'
        }, function(status, response) {
            if (status !== naver.maps.Service.Status.OK) {
                return alert('Something wrong!');
            }

            var result = response.result, // 검색 결과의 컨테이너
                items = result.items; // 검색 결과의 배열s

            var x = parseFloat(items[0].point['x']);
            var y = parseFloat(items[0].point['y']);

            var map = new naver.maps.Map('map', {
                center: new naver.maps.LatLng(y, x),
                zoom: 10
            });

            var marker = new naver.maps.Marker({
                position: new naver.maps.LatLng(y, x),
                map: map
            });
        });
    }

    return {
        showMap : showMap
    }
})();

module.exports = navermap;