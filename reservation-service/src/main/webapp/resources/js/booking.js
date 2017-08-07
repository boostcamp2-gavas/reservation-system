(function($, eg) {
		"use strict";
		window.reservation = window.reservation || {};
		window.reservation.Booking = (function(){
			Booking.prototype = new eg.Component();
			Booking.prototype.constructor = Booking;

      function Booking(priceTypeArea) {
    		this.priceTypeArea = priceTypeArea;
    		this.$priceTypeArea = $(priceTypeArea);
    		this.amount = 0;
    		this.data = this.$priceTypeArea.data();
    		this.price = this.data.price;
    		this.priceType = this.data.priceType;
        this.$minusBtn = this.$priceTypeArea.find(Booking.prototype.config.minusBtn);
        this.$plusBtn = this.$priceTypeArea.find(Booking.prototype.config.plusBtn);
        this.$amount = this.$priceTypeArea.find(Booking.prototype.config.amount);
        this.$priceSection = this.$priceTypeArea.find(Booking.prototype.config.priceSection);
        this.$price = this.$priceTypeArea.find(Booking.prototype.config.price);

    		this.addEventHandling();
    	};

    	Booking.prototype.addEventHandling = function() {
        this.$minusBtn.on("click", this.minusBtnHandling.bind(this))
        this.$plusBtn.on("click", this.plusBtnHandling.bind(this))
    	}

      Booking.prototype.minusBtnHandling = function(e) {
        e.preventDefault();
        if(this.amount > 0) {
          this.amount--;

          this.printAmountAndPrice();

          if(this.amount==0) {
            this.toggleClassesForView();
          }
        }
      }

      Booking.prototype.plusBtnHandling = function(e) {
        e.preventDefault();
        this.amount++;

        this.printAmountAndPrice();

        if(this.amount==1) {
          this.toggleClassesForView();
        }
      }

      Booking.prototype.printAmountAndPrice = function() {
        this.$amount.val(this.amount);
        this.$price.text((this.amount*this.price).toLocaleString());
      }

      Booking.prototype.toggleClassesForView = function() {
        this.$amount.toggleClass(Booking.prototype.config.amountModifier);
        this.$priceSection.toggleClass(Booking.prototype.config.priceSectionModifier);
        this.$minusBtn.toggleClass(Booking.prototype.config.btnModifier);
      }

      Booking.prototype.getAmount = function() {
        return this.amount;
      }

    	Booking.prototype.config = {
    		priceTypeArea : "._priceTypeArea",
    		minusBtn : "._minus",
    		plusBtn : "._plus",
    		btnModifier : "disabled",
    		amount : "._amount",
        amountModifier :"disabled",
    		priceSection : "._priceSection",
    		priceSectionModifier : "on_color",
        price : "._price"
    	};

      return Booking;
    })();

})(jQuery, eg);
