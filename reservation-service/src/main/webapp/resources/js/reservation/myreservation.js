// var $ = require('../../node_modules/jquery/dist/jquery');
// var Handlebars = require('../../node_modules/handlebars/dist/handlebars');
// var ReservationModel = require('./reservationModel');
// var Moment = require('../../node_modules/moment/moment');
//
//
//
// //
// // var MyReservation = (function () {
// //
// //     var id = $(".section_my").data("userid");
// //
// //     var cardSoruce = $("#card-template").html();
// //     var cardTemplate = Handlebars.compile(cardSoruce);
// //
// //     var usedCardSource = $("#usedCard-template").html();
// //     var usedCardTemplate = Handlebars.compile(usedCardSource);
// //
// //     var canceledCardSource = $("#canceledCard-template").html();
// //     var canceledCardTemplate = Handlebars.compile(canceledCardSource);
// //
// //     var cancelJsonData;
// //     var cancelTargetDiv;
// //     var tapCount = [, 0, 0, 0, 0];
// //     var cancelTap;
// //     var $card = $('.card');
// //
// //     var TYPE_NUM = 4;
// //     var TAP_TOGGLE = {
// //         "전체": [true, true, true, true],
// //         "이용예정": [true, true, false, false],
// //         "이용완료": [false, false, true, false],
// //         "취소·환불": [false, false, false, true]
// //     }
// //     var TAP_ROOT = [
// //         $card.eq(0),
// //         $card.eq(1),
// //         $card.eq(2),
// //         $card.eq(3)];
// //     var CARD_TYPE = {
// //         reserveCancel: '예약 신청중',
// //         reservedCancel: '예약 확정',
// //         writeReview: '이용 완료',
// //         canceldReservation: "취소된 예약"
// //     };
// //     var TYPE_ROOT = [,
// //         $card.eq(0).find('.link_booking_details').eq(0),
// //         $card.eq(1).find('.link_booking_details').eq(0),
// //         $card.eq(2).find('.link_booking_details').eq(0),
// //         $card.eq(3).find('.link_booking_details').eq(0)];
// //     var PRICE_TYPE = [
// //         '일반 : ',
// //         '청소년 : ',
// //         '어린이 : ',
// //         "합계 : "];
// //     var TEMPLATE_TYPE = [,
// //         cardTemplate,
// //         cardTemplate,
// //         usedCardTemplate,
// //         canceledCardTemplate];
// //
// //     function init() {
// //         var getData = ReservationModel.getReservation();
// //         getData.then(function (data) {
// //             addReservationList(data)
// //         });
// //         bindClickingTab();
// //         bindLayerBtn('.popup_booking_wrapper');
// //     }
// //
// //     function addReservationList(data) {
// //         data.forEach(function (item) {
// //             console.log(item);
// //             var reservationType = item.reservationType;
// //             var start = Moment(item.displayStart).format('YYYY-MM-DD HH:mm');
// //             var end = Moment(item.displayEnd).format('YYYY-MM-DD HH:mm');
// //             var date = start[0] + ' ~ ' + end[0];
// //             var generalTicketCount = getTicketCount(item.generalTicketCount);
// //             var youthTicketCount = getTicketCount(item.youthTicketCount);
// //             var childTicketCount = getTicketCount(item.childTicketCount);
// //             var totalCount = generalTicketCount + youthTicketCount + childTicketCount;
// //
// //             addArticle(TYPE_ROOT[reservationType], TEMPLATE_TYPE[reservationType], {
// //                 productId: item.productId,
// //                 no: item.id,
// //                 name: item.name,
// //                 date: date,
// //                 content: PRICE_TYPE[0] + generalTicketCount + ' ' + PRICE_TYPE[1] + youthTicketCount + ' ' + PRICE_TYPE[2] + childTicketCount + ' ' + PRICE_TYPE[3] + totalCount,
// //                 product: item.name,
// //                 company: item.placeName,
// //                 price: item.totalPrice
// //             });
// //             tapCount[reservationType]++;
// //         });
// //
// //         changeTabNum({
// //             all: tapCount[1] + tapCount[2] + tapCount[3] + tapCount[4],
// //             expe: tapCount[1] + tapCount[2],
// //             fin: tapCount[3],
// //             cancel: tapCount[4]
// //         });
// //
// //         bindOnCancelBtn();
// //
// //         bindOnClickReviewWriteBtn();
// //     }
// //
// //     function getTicketCount(ticket) {
// //         if (ticket !== null) {
// //             return ticket;
// //         } else {
// //             return 0;
// //         }
// //     }
// //
// //     function bindClickingTab() {
// //         $('.summary_board li').on('click', function () {
// //             $('.summary_board li').find('.link_summary_board').removeClass('on');
// //             $(this).find('.link_summary_board').addClass('on');
// //
// //             var type = $(this).find('.tit').text();
// //
// //             TAP_ROOT.forEach(function ($tab, i) {
// //                 var display = TAP_TOGGLE[type][i];
// //                 $tab.toggle(display);
// //             })
// //         });
// //     }
// //
// //     function changeTabNum(option) {
// //         var $figure = $('.summary_board .figure');
// //         $figure.eq(0).text(option.all);
// //         $figure.eq(1).text(option.expe);
// //         $figure.eq(2).text(option.fin);
// //         $figure.eq(3).text(option.cancel);
// //     }
// //
// //     function addArticle(root, template, option) {
// //         var context = {
// //             productId: option.productId,
// //             no: option.no,
// //             name: option.name,
// //             date: option.date,
// //             content: option.content,
// //             product: option.product,
// //             company: option.company,
// //             price: option.price
// //         };
// //
// //         var html = template(context);
// //
// //         root.show();
// //
// //         root.after($(html));
// //     }
// //
// //     function bindOnCancelBtn() {
// //         $('.card_item .booking_cancel').on('click', function () {
// //             var id = $(this).closest('.card_detail').find('.booking_number span').text();
// //             var name = $(this).closest('.card_detail').find('.tit').text();
// //             var date = $(this).closest('.card_detail').find('ul li .item_dsc').eq(0).text();
// //             var type = $(this).closest('.card').find('.link_booking_details .tit').eq(0).text();
// //             cancelTargetDiv = $(this).closest('.card_item');
// //
// //             if (type === CARD_TYPE.reserveCancel) {
// //                 cancelJsonData = JSON.stringify({'id': id, 'reservationType': TYPE_NUM});
// //                 cancelTap = 1;
// //                 layerOpen('.popup_booking_wrapper', name, date);
// //             } else if (type === CARD_TYPE.reservedCancel) {
// //                 cancelJsonData = JSON.stringify({'id': id, 'reservationType': TYPE_NUM});
// //                 cancelTap = 2;
// //                 layerOpen('.popup_booking_wrapper', name, date);
// //             }
// //         });
// //     }
// //
// //     function layerOpen(el, name, date) {
// //         var temp = $(el);
// //
// //         temp.find('.pop_tit span').text(name);
// //         temp.find('.pop_tit .sm').text(date);
// //
// //         temp.fadeIn();
// //     }
// //
// //     function bindLayerBtn(layer) {
// //         $(layer).find('.btn_gray').on('click', function () {
// //             $(layer).fadeOut();
// //         })
// //
// //         $(layer).find('.popup_btn_close').on('click', function () {
// //             $(layer).fadeOut();
// //         })
// //
// //         $(layer).find('.popup_btn_close').on('click', function () {
// //             $(layer).fadeOut();
// //         })
// //
// //         $(layer).find('.btn_green').on('click', function () {
// //             var getData = ReservationModel.updateReservationType(cancelJsonData);
// //             getData.then(function () {
// //                 cancelTargetDiv.find('.booking_cancel').hide();
// //
// //                 var clone = cancelTargetDiv.clone();
// //                 cancelTargetDiv.remove();
// //
// //                 $('.card').eq(3).find('.link_booking_details').eq(0).after(clone);
// //
// //                 tapCount[cancelTap]--;
// //                 tapCount[4]++;
// //
// //                 changeTabNum({
// //                     all: tapCount[1] + tapCount[2] + tapCount[3] + tapCount[4],
// //                     expe: tapCount[1] + tapCount[2],
// //                     fin: tapCount[3],
// //                     cancel: tapCount[4]
// //                 });
// //
// //                 $(layer).fadeOut();
// //             })
// //         });
// //     }
// //
// //
// //     function bindOnClickReviewWriteBtn() {
// //         $('.finished article .btn').on('click', function () {
// //             var productId = $(this
// ).data("productid");
// //             location.href = '/reviewWrite/' + productId;
// //         });
// //     }
// //
// //     return {
// //         init: init
// //     }
// // })();
//
// module.exports = MyReservation;
