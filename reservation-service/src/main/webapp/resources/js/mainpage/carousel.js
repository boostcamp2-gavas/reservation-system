var extend = require('../util');
var egCommponent = require('../../node_modules/@egjs/component/dist/component');
var $ = require('../../node_modules/jquery/dist/jquery');

var Carousel = extend(egCommponent, {

    init: function (root) {
        this.$root = root;
        this.$carouselRoot = this.$root.find('.visual_img');
        var $li = this.$root.find('li');
        this.liCount = $li.length;
        this.arrange = $li.width();
        this.index = 1;

        this.carouselReady();
        this.bindOnClick();
    },

    carouselReady: function () {
        var firstChild = this.$root.find('li:first').clone();
        var lastChild = this.$root.find('li:last').clone();
        this.$carouselRoot.prepend(lastChild);
        this.$carouselRoot.append(firstChild);
        this.$carouselRoot.css({"left": "-=" + this.arrange + "px"});
    },

    moveToPrev: function () {
        if (this.$carouselRoot.is(":animated")) {
            return false;
        }

        if (this.index <= 1) {
            this.$carouselRoot.css({"left": "-=" + (this.arrange * (this.liCount)) + "px"});
            this.index = this.liCount;
        } else {
            this.index--;
        }

        this.$carouselRoot.animate({"left": "+=" + this.arrange + "px"}, {
            duration: "normal"
        });

        this.trigger("clickBtn",{curNum : this.index});
    },

    moveToNext: function () {
        if (this.$carouselRoot.is(":animated")) {
            return false;
        }

        if (this.index >= this.liCount) {
            this.$carouselRoot.css({"left": "+=" + (this.arrange * (this.liCount)) + "px"});
            this.index = 0;
        }

        this.$carouselRoot.animate({"left": "-=" + this.arrange + "px"}, {
            duration: "normal"
        });

        this.index++;

        this.trigger("clickBtn",{curNum : this.index});
    },

    bindOnClick: function () {
        this.$root.find('.prev_inn').on('click', this.moveToPrevTrigger.bind(this));
        this.$root.find('.nxt_inn').on('click', this.moveToNextTrigger.bind(this));

        this.$root.find('.prev_e').on('mouseenter', '.prev_inn', function () {
            this.trigger("stopTimer");
        }.bind(this));

        this.$root.find('.nxt_e').on('mouseenter', '.nxt_inn', function () {
            this.trigger("stopTimer");
        }.bind(this));
    },

    moveToPrevTrigger: function (e) {
        e.preventDefault();
        this.moveToPrev();
    },

    moveToNextTrigger: function (e) {
        e.preventDefault();
        this.moveToNext();
    }
});

module.exports = Carousel;
