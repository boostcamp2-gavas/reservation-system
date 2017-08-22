var $ = require('../node_modules/jquery/dist/jquery');
var egComponent = require('../node_modules/@egjs/component/dist/component');
var extend = require('./util');

var FlickingComponent = extend(egComponent, {

    init: function (ele) {
        this.root = ele;
        this.ele = ele.find('li');
        this.num = 1;
        this.slideWidth = this.ele.width();
        this.slideCount = this.ele.length;
        this.touchStartY = 0;
        this.touchStartX = 0;
        this.moveDx = 0;
        this.curLiPosition = 0;

        this.bindOnFlikingBtn();
    },

    flickingStart : function (e) {
        if (e.type === 'touchstart' && e.changedTouches.length === 1) {
            this.touchStartX = e.changedTouches[0].pageX;
            this.touchStartY = e.changedTouches[0].pageY;
        }
        e.preventDefault();
    },

    flickingMove : function (e) {
        var dragDistance = 0;
        var scrollDistance = 0;

        if (e.type === 'touchmove' && e.changedTouches.length === 1) {
            dragDistance = e.changedTouches[0].pageX - this.touchStartX;
            scrollDistance = e.changedTouches[0].pageY - this.touchStartY;
            this.moveDx = ( dragDistance / this.slideWidth ) * 100;
            var fakeMove = 0;

            if(Math.abs(dragDistance) > this.slideWidth){
                dragDistance = (dragDistance < 0) ? - this.slideWidth : + this.slideWidth;
            }
            fakeMove = this.curLiPosition + dragDistance;

            if(dragDistance > 0){
                if (this.num !== 1) {
                    if(Math.abs(dragDistance) > Math.abs(scrollDistance)){
                        this.root.stop(true,true).animate({left: fakeMove},500);
                    }
                } else {
                    this.root.stop(true,true).animate({left: this.curLiPosition + 10},500);
                    this.moveDx = 0;
                }
            } else{
                if (this.num < this.slideCount) {
                    if(Math.abs(dragDistance) > Math.abs(scrollDistance)){
                        this.root.stop(true,true).animate({left: fakeMove},500);
                    }
                } else {
                    this.root.stop(true,true).animate({left: this.curLiPosition - 10},500);
                    this.moveDx = 0;
                }
            }
        }
    },

    flickingEnd : function (e) {

        if (e.type === 'touchend' && e.changedTouches.length === 1) {
            if(Math.abs(this.moveDx) > 30){
                if(this.moveDx > 0){
                    if (this.num !== 1) {
                        this.root.stop(true,true).animate({left:this.curLiPosition + this.slideWidth});
                        this.num = this.num - 1;
                        this.curLiPosition += this.slideWidth;
                    }
                }
                else {
                    if(this.num < this.slideCount){
                        this.root.stop(true,true).animate({left:this.curLiPosition - this.slideWidth});
                        this.num = this.num + 1;
                        this.curLiPosition -= this.slideWidth;
                    }
                }
            } else{
                this.root.stop(true,true).animate({left:this.curLiPosition},500);
            }

            this.touchStartY = 0;
            this.touchStartX = 0;
            this.moveDx = 0;
            this.moveSum = 0;

            e.preventDefault();
        }
    },

    moveToPrev: function (e) {
        e.preventDefault();

        if (this.root.is(":animated")) {
            return false;
        }

        if (this.num > 1) {
            this.root.animate({"left": "+=" + this.slideWidth + "px"}, {
                duration: "normal"
            });
            this.num--;
            this.trigger("flick",{curNum : this.num});
        }
    },

    moveToNext: function (e) {
        e.preventDefault();

        if (this.root.is(":animated")) {
            return false;
        }

        if(this.num < this.slideCount){
            this.root.animate({"left": "-=" + this.slideWidth + "px"}, {
                duration: "normal"
            });
            this.num++;
            this.trigger("flick",{curNum : this.num});
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
            this.trigger("flick", {curNum: this.num});
        }.bind(this))

        $('.prev_inn').on('click', this.moveToPrev.bind(this));
        $('.nxt_inn').on('click', this.moveToNext.bind(this));
    },

    getSlideCount : function(){
        return this.slideCount;
    }
});

module.exports = FlickingComponent;