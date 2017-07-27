// mainpage의 category 가져오는 js
$('document').ready(function(){
	var $ul = $(".tab_lst_min");
	var template = {},
	categoryEvent = {};
	
	
	template = function tmepFunc(index,contents){
		return ' <li class="item" data-category='+index+'>'+
		       	 	'<a class="anchor"> <span>'+contents+'</span> </a>'+
		        '</li>';
	}
	
	// category와 관련된 함수들을 객체로 묶음. 
	categoryEvent = {
			// 즉시 실행 함수로 저장 ?
			getCategory  : function getCategoryAjax(){
				$.ajax({
					  method: "GET",
					  url: "/categorys",
					  contentType: "application/json; charset=utf-8",
			          dataType: "json",
				}).then(categoryEvent.success , categoryEvent.fail);
			},
			clickEvent : function ClickEvent(){
				$(".anchor").removeClass("active");
				$(this).children().addClass("active");
			},
			success : function successFunc(data){
				$.each(data,function(index,data){
					$ul.append(template(data.id,data.name));
				})
				$(".anchor:last").addClass("last");
			},
			
			fail  : function failFunc(){
				alert("가져오기 실패");
			}
	};
	
	// 함수 실행 
	categoryEvent.getCategory();
	
	// Event 바인딩 
	$(document).on("click", ".tab_lst_min > .item" ,categoryEvent.clickEvent);

});


/* 지난번 진행했던 소스 
$(function() {

	// remove 클릭시 삭제
	var removefunc = function(){
		var $tr = $(this).parents("tr");
		var id = $tr.data("id"); 
		
		// message
		var success = function() { $tr.remove(); }
		var fail = function(){ alert("삭제에 실패하엿습니다.")};
		
		// Delete ajax
		$.ajax({
			  method: "DELETE",
			  contentType: "application/json; charset=utf-8",
			  url: "/categorys/"+id
		}).then(success , fail);
		
	};
	
	// 클릭시 해당 name이 input에 들어가고, 수정하기 버튼 보이도록
	var updateReady = function(){
		// click 된  tr을 통해 data-id 를 가져오고, name의 값을 input에 넣는다
		var $tr = $(this).parents("tr");
		var $name = $("input[name=name]");
		var id = $tr.data("id"); 
		var text = $tr.find(".name").text().trim();
		
		// 버튼 none,  a tag block
		$("#modify").css("display","block");
		$("#submit").css("display","none");
		// input[name=name] text 설정 및 data 설정
		$name.val(text);
		$name.data("id",id);
			
	};
	
	// 수정하기 a tag 클릭시 ajax로 전송하고 , reload
	var updateSend = function(){
		
		var name = $("input[name=name]").val();
		var id = $("input[name=name]").data("id");
		
		// put에 담을 객체 생성 
		var category = new Object();
		category.id = id;
		category.name = name;
		
		// message
		var success = function(data) {
			console.log(data);
		    alert("수정되었습니다.");
		    //location.reload();
		};
		
		var fail = function(){ alert("수정에 실패하엿습니다.")};
		
		//  Put ajax
		$.ajax({
			  method: "PUT",
			  url: "/categorys/"+id,
			  contentType: "application/json; charset=utf-8",
	          //dataType: "json",
			  data : JSON.stringify(category)
		}).then(success , fail); 
	};
	
	// 이벤트 바인딩 
	$(document).on("click",".remove", removefunc);
	$(document).on("click",".update", updateReady);
	$(document).on("click","#modify",updateSend);
	
});

*/