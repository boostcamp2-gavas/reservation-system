class ReservationList extends eg.Component {
	constructor(root, messenger) {
		super();
		this.root = root;
        this.id = root.prop('id');
		this.messenger = messenger;
		this.childrenLength = root.children('.card_item').length;
		this.bindEvents();
	}
	bindEvents() {
		this.root.on('click', '.booking_cancel', this.showPopup.bind(this));
		this.on('removed', this.zeroCheck);
		this.messenger.on('reCount', this.reCount.bind(this))
                      .on('tab', this.toggle.bind(this));
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
    toggle(target) {
        switch(target.prop('id')) {
                case 'total_count':
                    if(this.childrenLength) {
                        this.root.show();
                    }
                    break;
                case 'due_count':
                    if(this.id === 'requesting' || this.id === 'card_confirmed') {
                        if(this.childrenLength) {
                            this.root.show();
                        }
                    } else {
                        this.root.hide();
                    }
                    break;
                case 'used_count':
                    if(this.id === 'card_used') {
                        if(this.childrenLength) {
                            this.root.show();
                        }
                    } else {
                        this.root.hide();
                    }
                    break;
                case 'canceled_count':
                    if(this.id === 'card_canceled') {
                        if(this.childrenLength) {
                            this.root.show();
                        }
                    } else {
                        this.root.hide();
                    }
                    break;
        }
        if(parseInt(target.text()) === 0) {
            $('div.err').removeClass('invisible');
        } else {
            $('div.err').addClass('invisible');
        }
    }
}