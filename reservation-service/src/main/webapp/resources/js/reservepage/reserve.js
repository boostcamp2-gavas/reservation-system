var $ = require('../../node_modules/jquery/dist/jquery');
var Moment = require('../../node_modules/moment/moment');
var ProductReserve = require('./productreserve');
var TicketView = require('./ticketview');
var Ticket = require('./ticket');
var ReserveUser = require('./reserveuser');
var ProductReserveModel = require('./productreservemodel');

(function () {
    var productReserveModel = ProductReserveModel.getInstance();
    var reserveUser;
    var tickets = [];
    productReserveModel.setMomentConfig();
    productReserveModel.getReserves(afterGetReserveData);

    function afterGetReserveData(data, salesDuration){
        ProductReserve.init(data, salesDuration);
        TicketView.setTicketInfomation(data);
        reserveUser = new ReserveUser($('.section_booking_form'), {"salesDuration":salesDuration});
        
        controllTicket();
        reserveUserController();
        bindBookingAgreement();
        bindReserveBtn();
        
    }

    function controllTicket(){
        $('.qty').each(function(index,v){
            var ticket = new Ticket("#ticket" + (index + 1));
            tickets.push(ticket);

            ticket.on('changeTicket', function(obj){
                var ticketTotalCount = 0;
                tickets.forEach(function(ticket){
                    ticketTotalCount += ticket.getTicketAmount();
                });
                
                reserveUser.setInlineText(ticketTotalCount);
            });
        });
    }

    function reserveUserController(){
        reserveUser.on('changeBooking', function(reserve){
            var inspect = reserve.state.boolean;
            var data = reserve.state.value;
            
            if(inspect.name && inspect.tel && inspect.email && inspect.agreement && inspect.ticketTotalCount) {
                $('.box_bk_btn .bk_btn_wrap').removeClass("disable");
            } else {
                $('.box_bk_btn .bk_btn_wrap').addClass("disable");
            }
        });
    }

    function bindBookingAgreement(){
        $('.section_booking_agreement').on('click', '.btn_agreement', function(e){
            e.preventDefault();
            $(this).closest('.agreement').toggleClass('open');
            var $arrowBtn = $(this).find('.fn');

            if($arrowBtn.hasClass('fn-down2')) {
                $arrowBtn.removeClass('fn-down2').addClass('fn-up2');
            } else {
                $arrowBtn.removeClass('fn-up2').addClass('fn-down2');
            }
        });
    }

    function bindReserveBtn(){
        $('.box_bk_btn .bk_btn_wrap').on('click', function(){
            if(!$(this).hasClass("disable")){
                var ticketscounts = [];
                tickets.forEach(function(value){
                    ticketscounts[value.priceType] = value.getTicketAmount();
                });

                var user = reserveUser.state.value;
                var data = {
                    "productId" : $('#gavas').data('productid'),
                    "generalTicketCount" : ticketscounts[3],
                    "youthTicketCount" : ticketscounts[2],
                    "childTicketCount" : ticketscounts[1],
                    "reservationName" : $('.title').text(),
                    "reservationTel" : user.tel,
                    "reservationEmail" : user.email,
                    "reservationDate" : Moment().format('YYYY-MM-DD hh:mm:ss'),
                    "reservationType" : 1,
                };
                productReserveModel.writeReservation(data, function(response){
                    console.log(response);
                });
            }
        });
    }

})();