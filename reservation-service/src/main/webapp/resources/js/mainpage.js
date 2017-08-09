/**
 * Created by ODOL on 2017. 7. 10..
 */
(function (window) {
	var taet = "teasdasd";
})(window);

var commonFunctions = {

    bindEventOnClick: function ($wrapperDom, $targetDom, func) {
        $($wrapperDom).on('click', $target, func);
    },
    ajaxReturn: function (uri, method, data) {
        return $.ajax({
            url: uri,
            method: method,
            data: data
        })
    }

}


var headDivFunctions = {

    init: function() {
        var $logo = $(".logo");
        var $event = $(".event");
    }
}

var sectionEvent = {
    init: function() {
        var $event = $(".event");

    }
}
