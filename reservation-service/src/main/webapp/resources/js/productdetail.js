var $ = require('../node_modules/jquery/dist/jquery');
var Handlebars = require('../node_modules/handlebars/dist/handlebars');
var ProductDetailModel = require('./productdetailmodel');
var Moment = require('../node_modules/moment/moment');

var ProductDetail = (function () {
    var productDetailModel = ProductDetailModel.getDetail();
    var source = $("#detailImage-template").html();
    var template = Handlebars.compile(source);

    function init(){
        bindOnClickMoreBtn();
    }

    function bindOnClickMoreBtn(){
        $('.bk_more').on('click',showMoreContent);
    }

    function showMoreContent(){
        $('.bk_more._open').toggle();
        $('.bk_more._close').toggle();
        $('.section_store_details .store_details').toggleClass('close3');
    }

    function showProductDetail() {
        productDetailModel.getDetails(function (data) {
            writeProductDetail(data);
            setProductDetailImagre(data);
            validateTicketing(data);
        });
    }

    function writeProductDetail(data) {
        $('.store_details.close3 .dsc').html(data.description.replace(/\n/g, '<br>'));
        $('.event_info .in_dsc').html(data.event.replace(/\n/g, '<br>'));
        $('.store_addr.store_addr_bold').text(data.placeStreet);
        $('.addr_old_detail').text(data.placeLot);
        $('.store_addr.addr_detail').text(data.placeName);
        $('.group_btn_goto .btn_goto_tel').attr('href', 'tel:' + data.tel);
        $('.group_btn_goto .btn_goto_home').attr('href', data.homepage);
        $('.group_btn_goto .btn_goto_mail').attr('href', 'mailto:' + data.email);
    }

    function setProductDetailImagre(data) {
        var name = data.name;
        data.fileIdList.forEach(function (item) {
            $('.visual_img').append(template({name: name, fileId: item}));
        })
    }

    function validateTicketing(data) {
        var saleEnd = Moment(data.salesEnd).format('YYYY-MM-DD HH:mm');
        var currentTime = Moment().format('YYYY-MM-DD HH:mm');
        if (data.salesFlag){
            $('.section_btn .bk_btn span').text('매진')
        } else {
            if (Moment(currentTime).isAfter(saleEnd)) {
                $('.section_btn .bk_btn span').text('판매기간 종료')
            } else {
                $('.section_btn').on('click','.bk_btn',function(){
                   location.href = "";
                });
            }
        }
    }

    return {
        init : init,
        showProductDetail: showProductDetail
    }

})();

module.exports = ProductDetail;