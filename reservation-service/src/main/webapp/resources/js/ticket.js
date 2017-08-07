define("ticket", [], function() {
		"use strict";
		return eg.Class.extend(eg.Component, {

			construct : function(priceTypeArea) {
				this.$priceTypeArea = $(priceTypeArea);
				this.amount = 0;

				var data = this.$priceTypeArea.data();
				this.price = data.price;
				this.priceType = data.priceType;

		        this.$minusBtn = this.$priceTypeArea.find(this.config.minusBtn);
		        this.$plusBtn = this.$priceTypeArea.find(this.config.plusBtn);
		        this.$amount = this.$priceTypeArea.find(this.config.amount);
		        this.$priceSection = this.$priceTypeArea.find(this.config.priceSection);
		        this.$price = this.$priceTypeArea.find(this.config.price);

	    		this.addEventHandling();
			},

			addEventHandling : function() {
	        	this.$minusBtn.on("click", this.minusBtnHandling.bind(this))
	        	this.$plusBtn.on("click", this.plusBtnHandling.bind(this))
			},

			minusBtnHandling : function(e) {
	          e.preventDefault();
	          if(this.amount > 0) {
	            this.amount--;

	            if(this.amount==0) {
	              this.toggleClassesForView();
	            }

				this.printAmountAndPrice();
				this.trigger("change");
	          }
		  	},

			plusBtnHandling : function(e) {
	          e.preventDefault();
	          this.amount++;

	          if(this.amount==1) {
	            this.toggleClassesForView();
	          }

			  this.printAmountAndPrice();
			  this.trigger("change");
		  	},

			printAmountAndPrice : function() {
	          this.$amount.val(this.getAmount());
	          this.$price.text(this.getPrice().toLocaleString());
		  	},

			toggleClassesForView : function() {
	          this.$amount.toggleClass(this.config.amountModifier);
	          this.$priceSection.toggleClass(this.config.priceSectionModifier);
	          this.$minusBtn.toggleClass(this.config.btnModifier);
		  	},

			getAmount : function() {
	          return this.amount;
		  	},

			getPrice : function() {
			  return this.amount * this.price;
			},

			config : {
	    		minusBtn : "._minus",
	    		plusBtn : "._plus",
	    		btnModifier : "disabled",
	    		amount : "._amount",
	    		amountModifier :"disabled",
	    		priceSection : "._priceSection",
	    		priceSectionModifier : "on_color",
	        	price : "._price"
	    	}

		});

});
