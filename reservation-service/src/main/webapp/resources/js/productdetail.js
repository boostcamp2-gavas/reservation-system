var $ = require('../node_modules/jquery/dist/jquery');
var Handlebars = require('../node_modules/handlebars/dist/handlebars');
var ProductDetailModel = require('./productdetailmodel');
var Moment = require('../node_modules/moment/moment');

var ProductDetail = (function(){
    var productDetailModel = ProductDetailModel.getDetail();
    var source = $("#detailImage-template").html();
    var template = Handlebars.compile(source);

    function showProductDetail() {
        productDetailModel.getDetails(function(data){
            writeProductDetail(data);
            setProductDetailImagre(data);
        });
    }

    function writeProductDetail(data) {
        $('.store_details.close3 .dsc').html(data.description.replace(/\n/g, '<br>'));
        $('.event_info .in_dsc').html(data.event.replace(/\n/g, '<br>'));
        $('.store_addr.store_addr_bold').text(data.placeStreet);
        $('.addr_old_detail').text(data.placeLot);
        $('.store_addr.addr_detail').text(data.placeName);
        $('.group_btn_goto .btn_goto_tel').attr('href','tel:'+data.tel);
        $('.group_btn_goto .btn_goto_home').attr('href',data.homepage);
        $('.group_btn_goto .btn_goto_mail').attr('href','mailto:'+data.email);
        console.log(Moment(data.salesEnd).format('MM-DD-YYYY HH:mm'));
        console.log(Moment().format('MM-DD-YYYY HH:mm'));
    }

    function setProductDetailImagre(data){
        var name = data.name;
        data.fileIdList.forEach(function(item){
            $('.visual_img').append(template({name : name, fileId : item}));
        })
    }

    function currentDate(){
    }

    return {
        showProductDetail : showProductDetail
    }

})();

module.exports = ProductDetail;