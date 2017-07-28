class WriterFooter extends eg.Component {
    constructor(root, messenger, template) {
        super();
        this.root = root;
        this.ul = root.find('ul.lst_thumb');
        this.messenger = messenger;
        this.template = template;
        this.files = [];
        this.bindEvents();
    }
    bindEvents() {
        this.root.on('change', 'input#reviewImageFileOpenInput', this.fileControl.bind(this))
                 .on('click', 'span.spr_book', this.delThumb.bind(this));
        this.messenger.on('submit', this.fileLoad.bind(this));
    }
    fileControl(e) {
        var fileList = e.target.files;
        var typeReg = /jpeg|png/;
        var arr = [];
        for(var i = 0; i < fileList.length; i++) {
            var type = String(fileList[i].type).split('/')[1];
            var size = fileList[i].size;
            if(!typeReg.test(type)) {
                alert('jpg, png 파일만 등록 가능합니다.');
            } else if (size > (1024 * 1024)) {
                alert('파일 용량은 1MB를 넘을 수 없습니다.');
            } else {
                var url = window.URL.createObjectURL(fileList[i]);
                var name = fileList[i].name;
                var obj = {
                    url : url,
                    name : name
                }
                arr.push(obj);
                this.files.push(fileList[i]);
            }
        }
        var obj = {
            items : arr
        }
        this.ul.append(this.template(obj));
    }
    delThumb(e) {
        var target = $(e.target);
        var index = this.getIndex(e.target);
        this.files.splice(index, 1);
        target.closest('li.item').remove();
    }
    getIndex(target) {
        var root = $(target).closest('ul');
        var children = root.find('span.spr_book');
        for(var i = 0; i < children.length; i++) {
            if(children[i] === target) {
                return i;
            }
        }
    }
    fileLoad() {
        for(var i = 0; i < this.files.length; i++) {
            this.messenger.formData.append('commentImg', this.files[i]);
        }
        var theThis = this;
        $.ajax({
            url: '/comments/write',
            processData: false,
            contentType: false,
            data: this.messenger.formData,
            method: 'post',
            success: function(response){
                alert("업로드 성공!!");
                location.href = '/mvMyPage';
            },
            error : function(request, status, error ) { 
                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    }
}