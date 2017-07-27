class WriterFooter extends eg.Component {
    constructor(root, messenger, template) {
        super();
        this.root = root;
        this.messenger = messenger;
        this.template = template;
        this.bindEvents();
    }
    bindEvents() {
        this.root.on('change', 'input#reviewImageFileOpenInput', this.fileControl.bind(this))
                 .on('click', 'span.spr_book', this.delThumb.bind(this));
    }
    fileControl(e) {
        var fileList = e.target.files;
        var type = '';
        var size = 0;
        var typeReg = /jpeg|png/;
        var arr = [];
        var url = '';
        for(var i = 0; i < fileList.length; i++) {
            type = String(fileList[i].type).split('/')[1];
            size = fileList[i].size;
            if(!typeReg.test(type)) {
                alert('jpg, png 파일만 등록 가능합니다.');
            } else if (size > (1024 * 1024)) {
                alert('파일 용량은 1MB를 넘을 수 없습니다.');
            } else {
                url = window.URL.createObjectURL(fileList[i]);
                arr.push(url);
            }
        }
        arr = arr.map(function(v, i) {
           return {
               url : v
           } 
        });
        var obj = {
            items : arr
        }
        this.root.find('ul.lst_thumb').append(this.template(obj));
    }
    delThumb(e) {
        var target = $(e.target);
        target.closest('li.item').remove();
    }
}