var $ = require('../../node_modules/jquery/dist/jquery');
var Handlebars = require('../../node_modules/handlebars/dist/handlebars');

var TicketView = (function(){
    var ticketSource = $("#ticket-template").html();
    var ticketTemplate = Handlebars.compile(ticketSource);

    function setTicketInfomation(data){
        var productPriceList = data.productPriceInfoDtoList;
        var type = ["어린이", "청소년", "성인"];

        var item = [];
        productPriceList.forEach(function(value, index){
            var discountPrice = value.price * (1 - value.discountRate);
            item[index] = {
                "ticketId" : "ticket"+(index+1),
                "priceType" : value.priceType,
                "type" : type[index],
                "discountPrice" : discountPrice,
                "pricePretty" : value.price.toLocaleString(),
                "discountPricePretty" : discountPrice.toLocaleString(),
                "discountRate" : value.discountRate*100
            }
        });

        $('.ticket_body').append(ticketTemplate(item));
    }

    return {
        setTicketInfomation : setTicketInfomation
    }

})();

module.exports = TicketView;