define("myreservation", ["Handlebars"], function(Handlebars) {
	"use strict";
	var APIURL = "/users/reservation";
	var CANCEL_URL = "/cancellation";

	var $myReservationList;
	var reservationItem;
	var $emptyList;

	var $btnFilters;
	var $btnAll;
	var $btnSchedule;
	var $btnCompletion;
	var $btnCancellationAndRefund;
	var $btnFilterStatus;

	var $allCount;
	var $schduleCount;
	var $completionCount;
	var $cancellationAndRefundCount;

	var btnRemoveCancellation;
	var btnReservationCancel;

	var $cancellationLayerPopup;
	var popupCancel;
	var popupConfirm;
	var popupClose;

	var objType;

	var reservationAskingListTemplate;
	var reservationConfirmListTemplate;
	var reservationCompletionListTemplate;
	var reservationCancellationAndRefundTemplate;
	var cancellationPopupTemplate;

	function initVariable(objReservationType) {
		$myReservationList = $("._myReservationList");
		reservationItem = "._reservationItem";
		$emptyList = $("._emptyList");

		$btnFilters = $("._btnFilter");
		$btnAll = $("._btnAll");
		$btnSchedule = $("._btnSchedule");
		$btnCompletion = $("._btnCompletion");
		$btnCancellationAndRefund = $("._btnCancellationAndRefund");
		$btnFilterStatus = $btnAll;

		$allCount = $("._all");
		$schduleCount = $("._schedule");
		$completionCount = $("._completion");
		$cancellationAndRefundCount = $("._cancellationAndRefund");

		btnRemoveCancellation = "._btnRemoveCancellation";
		btnReservationCancel = "._btnReservationCancel";

		$cancellationLayerPopup = $("._cancellationLayerPopup");
		popupCancel = "._cancel";
		popupConfirm = "._confirm";
		popupClose = "._close";

		objType = objReservationType;


		reservationAskingListTemplate = Handlebars.compile($("#reservationList-asking-template").html());
		reservationConfirmListTemplate = Handlebars.compile($("#reservationList-confirm-template").html());
		reservationCompletionListTemplate = Handlebars.compile($("#reservationList-completion-template").html());
		reservationCancellationAndRefundTemplate = Handlebars.compile($("#reservationList-cancellationAndRefund-template").html());
		cancellationPopupTemplate = Handlebars.compile($("#cancellationPopup-template").html());
  	}

	function addEventHandling() {

	    $btnAll.on("click", function(e) {
          toggleFilterBtn.call(this, e);
		  removeMyReservationList();
	      reqReservationList();
	      reqReservationCount();
	    });

	    $btnSchedule.on("click", function(e) {
	      toggleFilterBtn.call(this, e);
		  removeMyReservationList();
	      reqReservationList(objType.ASKING);
	      reqReservationList(objType.CONFIRM);
	      reqReservationCount();
	    });

	    $btnCompletion.on("click", function(e) {
	      toggleFilterBtn.call(this, e);
		  removeMyReservationList();
	      reqReservationList(objType.COMPLETION);
	      reqReservationCount();
	    });

	    $btnCancellationAndRefund.on("click", function(e) {
	      toggleFilterBtn.call(this, e);
		  removeMyReservationList();
	      reqReservationList(objType.CANCELLATION);
	      reqReservationList(objType.REFUND);
	      reqReservationCount();
	    });

	    $myReservationList.on("click", btnReservationCancel, showCancellationPopup);
	    $myReservationList.on("click", btnRemoveCancellation, reqRemoveCancellation);
		$cancellationLayerPopup.on("click", popupCancel, hideCancellationPopup);
		$cancellationLayerPopup.on("click", popupClose, hideCancellationPopup);
		$cancellationLayerPopup.on("click", popupConfirm, cancelReservationHandling);
  	}
	function removeMyReservationList() {
		$myReservationList.empty();
		$emptyList.removeClass("hide");
	}

	function reqReservationList(type) {
		type = type || "";
		$.ajax({
		  url  : APIURL,
		  data : {
		    "type" : type
		  }
		}).done(makeReservationList);
	}

	function makeReservationList(res) {
		var arrAsking = [];
		var arrConfirm = [];
		var arrCompletion = [];
		var arrCancelledAndRefund = [];
		var strListElem = "";
		if(res.length !==0) {
			for(var i =0; i<res.length; i++) {
				var type = res[i].reservationType;
				if(type===objType.ASKING )
					arrAsking.push(res[i]);
				else if(type===objType.CONFIRM)
					arrConfirm.push(res[i]);
				else if(type===objType.COMPLETION)
					arrCompletion.push(res[i]);
				else if(type===objType.REFUND || type === objType.CANCELLATION)
					arrCancelledAndRefund.push(res[i]);
			}
			if(arrAsking.length > 0)
				strListElem += reservationAskingListTemplate({"reservationLists":arrAsking});
			if(arrConfirm.length > 0)
				strListElem += reservationConfirmListTemplate({"reservationLists":arrConfirm});
			if(arrCompletion.length > 0 )
			  	strListElem += reservationCompletionListTemplate({"reservationLists":arrCompletion})
			if(arrCancelledAndRefund.length > 0)
			  	strListElem += reservationCancellationAndRefundTemplate({"reservationLists":arrCancelledAndRefund})
		}
		renderReservationList(strListElem);
	}

	function renderReservationList(strListElem) {
		if(strListElem !== "") {
			$emptyList.addClass("hide");
			$myReservationList.append(strListElem);
		}
	}

	function reqReservationCount() {
		$.ajax({
		  url : APIURL+"/count"
		}).done(function(res) {
		    $allCount.text(res.total);
		    $schduleCount.text(res.schedule);
		    $completionCount.text(res.completion);
		    $cancellationAndRefundCount.text(res.cancellationAndRefund);
		});
	}

	function toggleFilterBtn(e) {
		e.preventDefault();
		$btnFilters.removeClass("on");
		$(this).addClass("on");
		$btnFilterStatus = $(this);
	}

	function reqRemoveCancellation() {
		$.ajax({
		  method : "delete",
		  url : APIURL+"/"+objType.CANCELLATION
		}).done(refreshCallback)
	}

	function reqCancelReservation(id) {
		$.ajax({
		  method : "put",
		  url : APIURL+CANCEL_URL+"/"+id
		}).done(refreshCallback)
	}

	function refreshCallback() {
		$btnFilterStatus.trigger("click");
	}

	function showCancellationPopup(e) {
		var $item = $(this).parents(reservationItem);
		var strElem = cancellationPopupTemplate(
						  {
							"id" : $item.data("id"),
							"name": $item.data("name"),
							"reservationDate" : $item.data("reservationDate")
						  }
				  	  );

		$cancellationLayerPopup.html(strElem);
		$cancellationLayerPopup.removeClass("hide");
	}

	function hideCancellationPopup(e) {
		e.preventDefault();
		$cancellationLayerPopup.addClass("hide");
	}

	function cancelReservationHandling(e) {
		hideCancellationPopup(e);
		reqCancelReservation($(this).data("id"));
	}

	function init(objReservationType) {
		initVariable(objReservationType);
		addEventHandling();

		reqReservationList();
		reqReservationCount();
  	}

	return {
		init : init
	}

});
