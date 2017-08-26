<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>main page</title>

</head>
<body>

   <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
   <script type="text/javascript">

		getList();

   	   function putCategory(name) {
		  $.ajax({
		    url: "./categories",
		    type: "POST",
		    contentType:"application/json; charset=UTF-8",
		    dataType:"json",
		    data: JSON.stringify({"name":name}),
		    success: function(data) {

		    		$('#mytable > tbody:last').append('<tr><td class = id>'+data.id+'</td><td class = name>'+data.name+ '</td><td class = up>' + '<input class ='+ "update" +'type="text"> <button id =' + "upbtn" +">"+"변경"+'</button>'+'</td><td>' + "<button id="+ "delete" +">"+"삭제하기"+"</button>" + '</td></tr>');
		    		alert("카테고리 추가를 완료했습니다.");

		     }
		  });
		  }

   		function updateCategory(name,id) {
		  $.ajax({
		    url: "./categories",
		    type: "PUT",
		    contentType:"application/json; charset=UTF-8",
		    dataType:"json",
		    data: JSON.stringify({"name":name, "id":id}),
		    success: function(data) {

		    		alert("카테고리 업데이를 완료하였습니다.");

		     }
		  });
		  }

		  function getList() {
			  $.ajax({
			   url: "./categories",
			   type: "GET",
			   contentType:"application/json; charset=UTF-8",
			   dataType:"json",
			   success: function(data) {

				   for(var i in data)
				   $('#mytable > tbody:last').append('<tr><td class = id>'+data[i].id+'</td><td class = name>'+data[i].name+ '</td><td class = up>' + '<input class ='+ "update" +'type="text"> <button id =' + "upbtn"+">"+"변경"+'</button>'+'</td><td>' + "<button id="+ "delete" + ">"+"삭제하기"+"</button>" + '</td></tr>');

			   }
			  });
			  }

		  function deleteList(i) {
			  $.ajax({
			    url: "./categories/"+i,
			    type: "DELETE",
			    contentType:"application/json; charset=UTF-8",
			    success: function(){
            deleteListdom();
          }
			  });
			  }

        function deleteListdom()
        {
          $(this).closest("tr").remove();
          alert("카테고리 삭제를 완료하였습니다.");
        }


	$('.put').live("click",function() {//추가 버튼

	    var name = $('.cate').val();
	    putCategory(name);
	  });

	  $('#delete').live("click",function() {// 삭제 버튼
		  var id = $(this).closest("tr").find(".id").text();
		  deleteList(id);

	  });

	  $('#upbtn').live("click", function() { //업데이트 버
		  	var id = $(this).closest("tr").find(".id").text();
		  	var name = $(this).closest("tr").find(".up > input").val();
		  	updateCategory(name,id);
		  	$(this).closest("tr").find(".name").text(name);
	  });

</script>

<h1>category page</h1>
<hr>
<br><br>

카테고리 추가<br><br>

    이름 : <input class = "cate" type="text"> <input type="button" class="put" value="추가">
    <br>

	<hr>

	<table id="mytable" border="1" cellspacing="3">
	  <tr>
	    <th>ID</th>
	    <th>이름</th>
	    <th>수정</th>
	    <th>삭제</th>
	  </tr>
	  <tbody></tbody>
	</table>

	<form method="" action="/hello">
    이름 : <input type="text" name="name"><br>
    <input type="submit" value="확인">

</form>
</body>
</html>
