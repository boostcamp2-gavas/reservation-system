var $ = require('../../node_modules/jquery/dist/jquery');
var Moment = require('../../node_modules/moment/moment');

var ProductReserveModel = (function () {
    var instance;
    
    function getReserve() {
        function setMomentConfig() {
            Moment.updateLocale('ko', {
                weekdays: ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"],
                weekdaysShort: ["일", "월", "화", "수", "목", "금", "토"],
            });
        }

        function getSalesDuration(data) {
            var startDatePretty = Moment(data.salesStart).format("YYYY.M.DD.(ddd)");
            var endDatePretty = Moment(data.salesEnd).format("YYYY.M.DD.(ddd)");
            salesDuration = startDatePretty + "~" + endDatePretty;
            return salesDuration;
        }

        function getReserves(fp) {
            var url = "/api/products/" + $('#gavas').data('productid') + "/reservainformation";
            $.ajax(url).then(function(data) {
                var salesDuration = getSalesDuration(data);
                fp(data, salesDuration);
            });
        }

        function writeReservation(data, fp) {
            console.log(data);
            var url = "/api/reservations";
            $.ajax(url,{
                method : "POST",
                data : JSON.stringify(data),
                contentType : "application/json"
            }).then(function(data,status, jqXHR) {
                fp(jqXHR.status);
            }, function(jqXHR){
                fp(jqXHR.status);
            });
        }

        return {
            setMomentConfig : setMomentConfig,
            getReserves: getReserves,
            writeReservation : writeReservation
        }
    }

    return {
        getInstance: function(){
            if(instance !== undefined){
                return instance;
            }
            instance = getReserve();
            return instance;
        }
    }
})();

module.exports = ProductReserveModel;