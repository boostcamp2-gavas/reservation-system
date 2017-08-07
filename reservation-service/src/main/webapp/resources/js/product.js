(function ($ , Handlebars) {
	"use strict";
	window.reservation = window.reservation || {};
	window.reservation.product = (function(){
		var APIURL;
		var COUNTURL;
		var item;
		var listTemplate;
		var queryObj;
		var categoryId;

		var $optionList;
		var $categoryCount;
		var $optionItems;
		var $productListBefore;
		var $productListRear;
		var $moreBtn;

		function eventListen() {
			$optionList.on("click", item, optionClickListener);
			$moreBtn.on("click", moreClickListenr);
		}

		function initVariable() {
			APIURL = "/products";
			COUNTURL = "/count";
			item = "._item";

			listTemplate = Handlebars.compile($("#productList-template").html());
			queryObj = {
				categoryId : "",
				offset : 0,
				size : 10
			};

			$optionList = $("._option_lst");
			$categoryCount = $("._categoryCount");
			$optionItems = $optionList.find(item);
			$productListBefore = $("#productListBefore");
			$productListRear = $("#productListRear");
			$moreBtn = $("._more");

		}

		// this는 클릭한 엘레멘트
		function optionClickListener(e) {
			e.preventDefault();
			resetLimit(queryObj);
			resetList();
			$moreBtn.removeClass("hide");

			var $Clicked = $(this);
			queryObj.categoryId = getData($Clicked, "category-id");

			reqGetList();
			reqGetCount();

			$optionItems.removeClass("active");
			$Clicked.addClass("active");
		}

		// more클릭리스너
		function moreClickListenr(e) {
			getMoreList();
		}

		function getMoreList() {
			upLimit(queryObj);
			reqGetList();
		}

		function resetList() {
			$productListBefore.empty();
			$productListRear.empty();
		}

		function reqGetList() {
			$.ajax({
				method: "get",
				url : APIURL+getQueryString(queryObj)
			}).done(getListCallback);
		}

		function reqGetCount() {
			$.ajax({
				method: "get",
				url : APIURL+COUNTURL+getQueryString(queryObj)
			}).done(editViewCount);
		}

		function editViewCount(res) {
			$categoryCount.html(res+"개");
		}

		function getListCallback(res) {
			appendToList(res);
			if(res.length<10)
				$moreBtn.addClass("hide");
		}

		function appendToList(res) {
			var half = Math.ceil(res.length/2);
			var list="";
			for(var i = 0; i < half; i++) {
				list +=listTemplate(res[i]);
			};
			$productListBefore.append(list);

			list="";
			for(var i = half; i< res.length; i++) {
				list +=listTemplate(res[i]);
			}
			$productListRear.append(list);
		}


		// 유틸로빼도될듯
		function getData($item, strType) {
			if(strType === "")
				return $item.data() ? $item.data() : "";
			else
				return $item.data(strType) ? $item.data(strType) : "";
		}

		function getQueryString(objQuery) {
			var queryString = "?";
			$.each(objQuery, function(key, value) {
				queryString+=key;
				queryString+="="+value;
				queryString+="&";
			});
			return queryString.slice(0, -1);
		}

		function resetLimit(objQuery) {
				objQuery.offset = 0;
		}

		function upLimit(objQuery) {
				objQuery.offset += 10;
		}
		// 유틸로빼도될듯


		function init() {
				initVariable();
				reqGetList();
				reqGetCount();
				eventListen();
		}
		return {
				init : init,
				getMoreList : getMoreList
		}

	})();

})(jQuery, Handlebars)
