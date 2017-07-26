class ReservationList extends eg.Component {
	constructor(root, messenger) {
		super();
		this.root = root;
		this.messenger = messenger;
		this.childrenLength = root.children('.card_item').length;
		this.bindEvents();
	}
	bindEvents() {
		this.root.on('click', '.booking_cancel', this.showPopup.bind(this));
		this.on('removed', this.zeroCheck);
		this.messenger.on('reCount', this.reCount.bind(this));
	}
	showPopup(e) {
		this.card = $(e.target).closest('.card_item');
		var cardHead = this.card.closest('li.card');
		if(cardHead.prop('id') === 'requesting' || cardHead.prop('id') === 'card_confirmed') {
			this.messenger.trigger('showPopup', this);
		}
	}
	reCount() {
		this.childrenLength = this.root.children('.card_item').length;
	}
	zeroCheck() {
		this.messenger.trigger('reCount');
		if(this.childrenLength == 0) {
			this.root.hide();
		}
	}
}