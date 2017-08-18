var $ = require('../../node_modules/jquery/dist/jquery');
var egComponent = require('../../node_modules/@egjs/component/dist/component.js');
var util = require('../util.js');

var Ticket = util(egComponent,{
    init : function(id) {
        this.ticketEle = $(id);
        this.countEle = $(id).find(".count_control_input");
        this.price = $(id).find(".product_price").data("price");
        this.priceType = $(id).data("pricetype");
        this.maxCount = 10;
        this.bindTicketEvent();
    },

    bindTicketEvent : function(){
        this.ticketEle.on('click', '.ico_plus3', this.plus.bind(this));
        this.ticketEle.on('click', '.ico_minus3', this.minus.bind(this));
    },

    plus : function(e){
        e.preventDefault();
        var currentCount = this.getTicketAmount();
        
        if(currentCount < this.maxCount){
            var currentCount = currentCount + 1;
            this.countEle.val(currentCount);
            var $parentTarget = $(e.currentTarget).closest('.clearfix');

            if(currentCount === this.maxCount) {
                $(e.currentTarget).addClass("disabled");
            } else {
                $(e.currentTarget).removeClass("disabled");
            }
            $parentTarget.find('.count_control_input').removeClass('disabled');
            $parentTarget.find('.ico_minus3').removeClass('disabled');
        }
        this.setTicketPrice();
        this.trigger('changeTicket', {
            "ticketCount" : this.getTicketAmount()
        });
    },

    minus : function(e){
        e.preventDefault();
        var currentCount = this.getTicketAmount();

        if(currentCount > 0) {
            var currentCount = currentCount - 1;
            this.countEle.val(currentCount);
            var $parentTarget = $(e.currentTarget).closest('.clearfix');
            
            if(currentCount === 0) {
                $(e.currentTarget).addClass("disabled");
                $parentTarget.find('.count_control_input').addClass("disabled");
            } else {
                $(e.currentTarget).removeClass("disabled");
                $parentTarget.find('.count_control_input').removeClass('disabled');
            }
            $parentTarget.find('.ico_plus3').removeClass('disabled');   
        }
        this.setTicketPrice();
        this.trigger('changeTicket', {
            "ticketCount" : this.getTicketAmount()
        });
    },

    getTicketAmount : function(){
        return parseInt(this.countEle.val());
    },

    getTotalPrice : function(){
        return this.getTicketAmount() * this.price;
    },

    

    setTicketPrice : function(){
        this.ticketEle.find('.total_price').text(this.getTotalPrice().toLocaleString());
    }

});

module.exports = Ticket;