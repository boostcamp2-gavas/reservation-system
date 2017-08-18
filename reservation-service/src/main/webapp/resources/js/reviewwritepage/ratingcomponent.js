var $ = require('../../node_modules/jquery/dist/jquery');
var extend = require('../util');
var egCommponent = require('../../node_modules/@egjs/component/dist/component');

var RatingComponent = extend(egCommponent,{
    init : function (root) {
        this.$root = root;
        this.$checkBoxArr = this.$root.find('.rating_rdo');
        this.cur = 0;
        this.$root.on('click','.rating_rdo',this.bindOnCheckBox.bind(this));
    },

    bindOnCheckBox : function (e) {
        this.cur = parseInt($(e.currentTarget).val());
        this.$checkBoxArr.prop("checked",false);
        this.$checkBoxArr.slice(0,this.cur+1).prop("checked", true);
        this.trigger("score",{score:this.cur});
    }
});

module.exports = RatingComponent;