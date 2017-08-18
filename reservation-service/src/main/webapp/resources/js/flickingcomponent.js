var $ = require('../node_modules/jquery/dist/jquery');
var egComponent = require('../node_modules/@egjs/component/dist/component');
var extend = require('./util');

var FlickingComponent = extend(egComponent, {

    init: function (ele) {
        this.root = ele;
        this.ele = ele.find('li');
        this.num = 1;
        this.slide_width = this.ele.outerWidth();
        this.slide_count = this.ele.length;
        this.touch_start_y = 0;
        this.touch_start_x = 0;
        this.save_x = 0;
        this.move_dx = 0;
        this.cur_dist = this.slide_width;
        this.move_sum = 0;
        this.curLiPosition;

        this.bindOnFlikingBtn();
    },

    flickingStart : function (e) {
        if (e.type === 'touchstart' && e.touches.length === 1) {
            this.touch_start_x = e.touches[0].pageX;
            this.touch_start_y = e.touches[0].pageY;
        }
        e.preventDefault();
    },

    flickingMove : function (e) {
        var drag_dist = 0;
        var scroll_dist = 0;
        this.curLiPosition = this.ele.closest("ul").position().left;

        if (e.type === 'touchmove' && e.touches.length === 1) {
            drag_dist = e.touches[0].pageX - this.touch_start_x;
            scroll_dist = e.touches[0].pageY - this.touch_start_y;
            this.move_dx = ( drag_dist / this.cur_dist ) * 100;
            this.move_sum += this.move_dx;

            if (this.ele.closest("ul").is(":animated")) {
                this.save_x = 0;
                this.touch_start_y = 0;
                this.touch_start_x = 0;
                this.move_dx = 0;
                this.move_sum = 0;

                return false;
            }

            if (Math.abs(drag_dist) > Math.abs(scroll_dist)) {
                if (this.curLiPosition > 0) {
                    this.save_x = 1;
                } else {
                    if (Math.abs(this.move_sum) < this.cur_dist) {
                        this.ele.closest("ul").css({"left": "+=" + this.move_dx + "px"});
                    } else {
                        this.save_x = 1;
                    }
                }
                e.preventDefault();
            }
        }
    },

    flickingEnd : function (e) {
        if (e.type === 'touchend' && e.touches.length === 0) {
            if (Math.abs(this.move_dx) > 8) {

                if (this.save_x > 0) {
                    this.curLiPosition = this.ele.closest("ul").position().left;
                    this.ele.closest("ul").animate({"left": "-=" + (this.curLiPosition + ((this.num - 1) * this.cur_dist)) + "px"}, "fast");

                    this.save_x = 0;
                    this.touch_start_y = 0;
                    this.touch_start_x = 0;
                    this.move_dx = 0;
                    this.move_sum = 0;

                    return false;
                }

                if (this.move_sum > 0) {

                    if (this.ele.closest("ul").is(":animated")) {
                        return false;
                    }

                    if (this.num != 1) {
                        //$('.figure_pagination > span:first').text(--curImgnum);
                        this.ele.closest("ul").animate({"left": "+=" + (this.cur_dist - this.move_sum) + "px"}, "slow");
                        this.num--;
                    }
                } else {
                    if (this.ele.closest("ul").is(":animated")) {
                        return false;
                    }

                    if (this.num != this.slide_count) {
                        this.ele.closest("ul").animate({"left": "-=" + (this.cur_dist + this.move_sum) + "px"}, "slow");
                        this.num++;
                    } else {
                        this.curLiPosition = this.ele.closest("ul").position().left;
                        this.ele.closest("ul").animate({"left": "-=" + (this.curLiPosition + ((this.num - 1) * this.cur_dist)) + "px"}, "fast");
                    }
                }
            } else {
                if (this.ele.closest("ul").is(":animated")) {
                    return false;
                }

                this.curLiPosition = this.ele.closest("ul").position().left;

                this.ele.closest("ul").animate({"left": "-=" + (this.curLiPosition + ((this.num - 1) * this.cur_dist)) + "px"}, "fast");
            }

            this.touch_start_y = 0;
            this.touch_start_x = 0;
            this.move_dx = 0;
            this.move_sum = 0;

            e.preventDefault();
        }
    },

    bindOnFlikingBtn : function(){
        this.root.bind('touchstart', function (e) {
            e.preventDefault();
            this.flickingStart(e);
        }.bind(this))

        this.root.bind('touchmove', function (e) {
            e.preventDefault();
            this.flickingMove(e);
        }.bind(this))

        this.root.bind('touchend', function (e) {
            e.preventDefault();
            this.flickingEnd(e);
            this.trigger("flick", {curDisplayNum: this.num});
        }.bind(this))
    },

    flush : function(){
        this.ele.closest("ul").animate({"left": "0px"}, "fast");
        this.num = 1;
        this.touch_start_y = 0;
        this.touch_start_x = 0;
        this.save_x = 0;
        this.save_y = 0;
        this.move_dx = 0;
        this.move_sum = 0;
    }
});

module.exports = FlickingComponent;