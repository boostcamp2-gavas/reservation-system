requirejs.config({
    //By default load any module IDs from js/lib
    baseUrl: '/resources/js/',
    //except, if the module ID starts with "app",
    //load it from the js/app directory. paths
    //config is relative to the baseUrl, and
    //never includes a ".js" extension since
    //the paths config could be for a directory.
    paths : {
        'jquery' : 'node_modules/jquery/dist/jquery',
        'handlebars' : 'node_modules/handlebars/dist/handlebars',
        'egjs' : 'node_modules/egjs/dist/pkgd/eg.pkgd.min'
    },
    // AMD를 지원하지 않는 외부 라이브러리를 모듈로 사용할 수 있게 한다.
   shim: {
       'egjs' : { // Modernizr 라이브러리
           exports: 'egjs'
       }
   }

});
