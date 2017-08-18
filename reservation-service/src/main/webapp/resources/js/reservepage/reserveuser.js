var $ = require('../../node_modules/jquery/dist/jquery');
var egComponent = require('../../node_modules/@egjs/component/dist/component.js');
var util = require('../util.js');

var ReserveUser = util(egComponent, {
    init : function($rootElement, option){
        this.setDefaultOption(option);
        this.$inline = $rootElement.find('.form_horizontal');
        this.$name = this.$inline.find('#name');
        this.$tel = this.$inline.find('#tel');
        this.$email = this.$inline.find('#email');
        this.$inlineTxt = this.$inline.find('.inline_txt');
        this.$agreement = $rootElement.find('#chk3');
        this.state = {
            "boolean" : {
                "name" : undefined,
                "tel" : undefined,
                "email" : undefined,
                "ticketTotalCount" : undefined,
                "agreement" : undefined
            },
            "value" : {}
        };
        this.setInlineText(this.option.ticketTotalCount);

        this.inspectName();
        this.inspectTelNumber();
        this.inspectEmailNumber();
        this.bindEventList();
    },

    bindEventList : function() {
        this.$name.on('keyup', this.inspectName.bind(this));
        this.$tel.on('keyup', this.inspectTelNumber.bind(this));
        this.$email.on('keyup', this.inspectEmailNumber.bind(this));
        this.$agreement.on('click', this.inspectAgreementCheckBox.bind(this));
    },

    changeState : function(prop, item) {
        this.state.boolean[prop] = item.boolean;
        this.state.value[prop] = item.value;
        this.triggerChagneOfState();
    },

    triggerChagneOfState : function() {
        this.trigger('changeBooking', {
            "state" : this.state
        });
    },

    inspectName : function() {
        var name = this.$name.val();
        var isInspected = false;

        if(name !== "") {
            isInspected = true;
        }
        this.changeState("name", {
            "boolean" : isInspected,
            "value" : name
        });
    },

    inspectTelNumber : function() {
        var regExp = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/;
        var tel = this.$tel.val();
        var isInspected = false;

        if(regExp.test(tel)) {
            isInspected = true;
        }
        this.changeState("tel", {
            "boolean" : isInspected,
            "value" : tel
        });
    },

    inspectEmailNumber : function() {
        var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
        var email = this.$email.val();
        var isInspected = false;
        
        if(regExp.test(email)){
            isInspected = true;
        }
        this.changeState("email", {
            "boolean" : isInspected,
            "value" : email
        });
    },

    inspectTicketTotalCount : function() {
        var ticketTotalCount = this.option.ticketTotalCount;
        var isInspected = false;

        if(this.option.ticketTotalCount > 0){
            isInspected = true;
        }
        this.changeState("ticketTotalCount", {
            "boolean" : isInspected,
            "value" : ticketTotalCount
        });
    },

    inspectAgreementCheckBox : function() {
        var isInspected = false;
        if(this.$agreement.prop("checked")){
            isInspected = true;
        }
        this.changeState("agreement", {
            "boolean" : isInspected
        });
    },
    
    setInlineText : function(ticketTotalCount){
        this.$inlineTxt.text(this.option.salesDuration + ', 총 '+ ticketTotalCount +'매');
        this.option.ticketTotalCount = ticketTotalCount;
        this.inspectTicketTotalCount();
    },

    setDefaultOption : function (option){
        option = option||{};

        this.option = {
            "salesDuration" : "2017.7.01.(토)~2017.8.01.(화)",
            "ticketTotalCount" : "0"
        };

        if(option.salesDuration !== undefined){
            this.option.salesDuration = option.salesDuration;
        }

        if(option.ticketTotalCount !== undefined){
            this.option.ticketTotalCount = option.ticketTotalCount;
        }
    }
});

module.exports = ReserveUser;