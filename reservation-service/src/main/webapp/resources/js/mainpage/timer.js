var Timer = (function () {

    function timer(carousel) {
        var timeOut;
        var interval;

        function init() {
            interval = setInterval(carousel.moveToNext.bind(carousel), 2000);
        }

        function stopInterval() {
            clearInterval(interval);
            clearTimeout(timeOut);
            interval = null;
            timeOut = null;
        }

        function setTimeOut() {
            timeOut = setTimeout(init, 4000);
        }

        return {
            init: init,
            stopInterval: stopInterval,
            setTimeOut: setTimeOut
        }
    }

    return {
        getInstance: timer
    }
})();

module.exports = Timer;