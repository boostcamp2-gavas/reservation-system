define("rating", [], function() {
	"use strict";
 	return eg.Class.extend(eg.Component, {

        construct : function(wrapSelector) {
            this.initVariable(wrapSelector);
            this.addEventHandling();
        },

        initVariable : function(wrapSelector) {
            this.$wrapSelector = $(wrapSelector);
            this.$btns = this.$wrapSelector.find("input[type='radio']");
            this.btnModifier = "checked";
            this.score = 0;
        },

        getScore : function() {
          return this.score;
        },

        addEventHandling : function() {
          this.$wrapSelector.on("click", "._radio", this.scoreClickHandling.bind(this));
        },

        scoreClickHandling : function (e) {
            this.$btns.removeClass(this.btnModifier);
            this.score = $(e.currentTarget).val();

            for(var i=0; i< this.score; i++) {
              this.$btns.eq(i).addClass(this.btnModifier);
            }
            this.trigger("change", { score : this.getScore() });
        }

    });

});
