module.exports = {  
    context: __dirname + '/js', // 모듈 파일 폴더
    entry: { // 엔트리 파일 목록
        mainpage: './mainpage/mainpage.js',
        detail: './detailpage/detail.js',
        reserve: './reservepage/reserve.js',
        reservation: './reservation/reservation.js',
        reviewwrite: './reviewwritepage/reviewwrite.js'
    },
    output: {
        path: __dirname + '/dist', // 번들 파일 폴더
        filename: '[name]bundle.js' // 번들 파일 이름 규칙
    }
}