class Rating extends eg.Component {
    constructor(root, messenger) {
        super();
        this.root = root;
        this.childrenInput = root.children('input');
        this.scorePanel = root.children('span.star_rank');
        this.starScore = 0;
        this.messenger = messenger;
        this.bindEvents();
    }
    bindEvents() {
        this.root.children('input').on('click', this.rate.bind(this));
        this.messenger.on('submit', this.scoreLoad.bind(this));
    }
    rate(e) {
        var target = $(e.target);
        this.starScore = parseInt(target.val());
        if(target.val() > 0) {
            this.scorePanel.removeClass('gray_star');
        }
        this.scorePanel.html(this.starScore);
        for(var i = 0; i <= this.starScore; i++) {
            $(this.childrenInput[i]).prop('checked', true);
        }        
        for(var j = this.starScore + 1; j <= 5; j++) {
            $(this.childrenInput[j]).prop('checked', false);
        }
    }
    score() {
        return this.starScore;
    }
    scoreLoad() {
        this.messenger.formData.append('score', this.starScore);
    }
}