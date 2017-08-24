requirejs.config({
    baseUrl: '/resources',
    paths: {
        jquery: '/resources/node_modules/jquery/dist/jquery',
        handlebars: '/resources/node_modules/handlebars/dist/handlebars.amd',
        component: '/resources/node_modules/@egjs/component/dist/component'
    }
});

requirejs(['jquery','js/list','js/slider'], function( $, List, Slider) {

	var slider;

	function init(){
		List.init();
		slider = new Slider(".group_visual",{
			max : 3
		});
		eventBind();
	}

	function eventBind(){
		slider.on("change",change);
	};

	function change(e){
		var categoryID = e.index;
		List.active($("[data-category='"+categoryID+"'] a"));
	}


	init();

});
