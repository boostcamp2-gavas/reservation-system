function StarPoint(root) {
	this.score = 3;
	this.root = root;
}
StarPoint.prototype = new eg.Component;
StarPoint.prototype.constructer = StarPoint;

StarPoint.prototype.update = function (score) {
	var i;
	if(score >= 1){
		for(i=1; i<=score; i++) {
			root.find(".rating_rdo[value='" + i + "']").addClass("checked").attr("checked","checked");
		}
		for(i=score+1; i<=5; i++) {
			root.find(".rating_rdo[value='" + i + "']").removeClass("checked").attr("checked","unchecked");
		}
		root.find(".star_rank gray_star").html(score);
	} else {
		root.find(".rating_rdo").removeClass("checked").attr("checked","unchecked");
		root.find(".rating_rdo[value='0']").addClass("checked").attr("checked","checked");
		root.find(".star_rank gray_star").html("0");
	}
}

StarPoint.prototype.init = function() {
	root.on("click", ".rating_rdo", (function(e) {
		 var score = $(e.currentTarget).attr("value");
		 this.update(score);
	}).bind(this));
}


$(document).ready(function() {
	
	starPoint = new StarPoint( $(".review_rating") );
});


