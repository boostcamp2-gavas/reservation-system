class TextArea extends eg.Component {
    constructor(root, messenger) {
        super();
        this.root = root;
        this.firstC = root.children().first();
        this.lastC = root.children().last();
        this.minChar = 5;
        this.maxChar = 400;
        this.currentChar = 0;
        this.charPanel = root.siblings('div.review_write_footer_wrap').find('#current_char');
        this.messenger = messenger;
        this.bindEvents();
    }
    bindEvents() {
        this.firstC.on('click', this.convertToWriter.bind(this));
        this.lastC.on('keyup', this.countChar.bind(this))
                  .on('blur', this.convertToInfo.bind(this));
        this.messenger.on('checkComment', this.commentLoad.bind(this));
    }
    convertToWriter(e) {
        this.firstC.hide();
        this.lastC.focus();
    }
    countChar(e) {
        this.currentChar = e.target.textLength;
        this.charPanel.html(this.currentChar);
        if(this.currentChar > this.maxChar) {
            alert('400자를 초과할 수 없습니다.');
        }
    }
    convertToInfo(e) {
        if(this.currentChar == 0) {
            this.firstC.show();
        }
    }
    commentLoad() {
        if(this.currentChar >= this.minChar && this.currentChar <= this.maxChar) {
            this.messenger.formData.append('comment', this.lastC.val());
            this.messenger.trigger('submit');
        } else {
            alert('글자수를 맞춰주세요');
        }
    }
}