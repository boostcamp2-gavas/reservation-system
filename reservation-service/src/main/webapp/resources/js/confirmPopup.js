class ConfirmPopup extends eg.Component {
	constructor(root, messenger) {
		super();
		this.root = root;
		this.messenger = messenger;
		this.bindEvents();
	}
	bindEvents() {
		this.messenger.on('showPopup', this.pop.bind(this));
		this.root.on('click', '.popup_btn_close', this.root.hide.bind(this.root))
				 .on('click', '.btn_gray .btn_bottom', this.root.hide.bind(this.root))
				 .on('click', '.btn_green .btn_bottom', this.removeReservation.bind(this));
	}
	pop(cardObj) {
		this.card = cardObj.card;
		this.cardObj = cardObj;
		this.root.find('.pop_tit span').html(this.card.find('.tit').html());
		this.root.show();
	}
	removeReservation() {
		this.card.find('.booking_cancel').remove();
		$('#card_canceled').append(this.card);
		this.root.hide();
		this.cardObj.trigger('removed');
	}
}