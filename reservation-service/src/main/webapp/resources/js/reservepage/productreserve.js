var $ = require('../../node_modules/jquery/dist/jquery');
var Moment = require('../../node_modules/moment/moment');
var ProductReserveModel = require('./productreservemodel');

var ProductReserve = (function () {
    var productReserveModel = ProductReserveModel.getInstance();
    var $reserveTitle = $('.section_store_details .in_tit');
    var $reserveDsc = $('.section_store_details .dsc');
    
    function init(data, salesDuration) {
        bindHistotyBack();
        setProductReserveInfo(data, salesDuration);
    }

    function bindHistotyBack() {
        $('.top_title .btn_back').on('click', function () {
            history.back();
        });
    }

    function getProductReserveInfo() {
        productReserveModel.getReserves(function (data) {
            setProductReserveInfo(data);
        });
    }

    function setProductReserveInfo(data, salesDuration) {
        var productPriceList = data.productPriceInfoDtoList;
        setReserveMainInfo({
            "name": data.name,
            "minPrice": productPriceList[0].price,
            "salesDuration": salesDuration,
            "fileId": data.fileId
        });
        var priceText = makeJoinedPriceInfo(productPriceList);

        var topProductReserveInfo = [
            {"tit": data.name, "dsc": "장소 : " + data.name + "<br>" + "기간 : " + salesDuration},
            {"tit": "관람시간", "dsc": data.observationTime},
            {"tit": "요금", "dsc": priceText}
        ];

        topProductReserveInfo.forEach(function (value, index) {
            $reserveTitle.eq(index).text(value.tit);
            $reserveDsc.eq(index).html(value.dsc);
        });
    }

    function setReserveMainInfo(data) {
        $('.top_title .title').text(data.name);
        $('.preview_txt .preview_txt_tit').text(data.name);
        $('.preview_txt .preview_txt_dsc:first').text("￦" + data.minPrice.toLocaleString() + "~");
        $('.preview_txt .preview_txt_dsc:last').text(data.salesDuration);
        $('.visual_img .img_thumb').prop("src", "/api/file/" + data.fileId);
    }


    function makeJoinedPriceInfo(productPriceList) {
        var priceType = ["어린이(만 4~12세)", "청소년(만 13~18세)", "성인(만 19~64세)"];
        var priceArray = [];
        productPriceList.forEach(function (value, index) {
            priceArray[index] = priceType[index] + " " + value.price.toLocaleString() + "원";
        });
        var priceText = priceArray.join(" / ");
        return priceText;
    }

    return {
        init: init,
        setProductReserveInfo : setProductReserveInfo
    }

})();

module.exports = ProductReserve;