<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>main page</title>

</head>
<body>
<script type="text/javascript" src="../../resources/node_modules/jquery/dist/jquery.js"></script>
<h1>Admin page</h1>
<hr>

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

	<%--<form method="" action="/hello">--%>
    <%--이름 : <input type="text" name="name"><br>--%>
    <%--<input type="submit" value="확인">--%>
	<%--</form>--%>
</body>
<script type="text/javascript">
(function(){
        getList();
//    	init();
        function putCategory(name) {
            $.ajax({
                url: "/api/categories",
                type: "POST",
                contentType:"application/json; charset=UTF-8",
                dataType:"json",
                data: JSON.stringify({"name":name}),
                success: function(data) {

                    $('#mytable > tbody:last').append('<tr><td class = id>'+data.id+'</td><td class = name>'+data.name+ '</td><td class = up>' + '<input class ='+ "update" +'type="text"> <button class =' + "upbtn" +">"+"변경"+'</button>'+'</td><td>' + "<button id="+ "delete" +">"+"삭제하기"+"</button>" + '</td></tr>');
                    alert("카테고리 추가를 완료했습니다.");

                }
            });
        }

        function updateCategory(name,id) {
            $.ajax({
                url: "/api/categories",
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
                url: "/api/categories",
                type: "GET",
                contentType:"application/json; charset=UTF-8",
                dataType:"json",
                success: function(data) {
                    for(var i in data)
                        $('#mytable > tbody:last').append('<tr><td class = id>'+data[i].id+'</td><td class = name>'+data[i].name+ '</td><td class = up>' + '<input class ='+ "update" +'type="text"> <button class =' + "upbtn"+">"+"변경"+'</button>'+'</td><td>' + "<button class="+ "delete" + ">"+"삭제하기"+"</button>" + '</td></tr>');
                    init();
                }
            });
        }

        function deleteList(i) {
            $.ajax({
                url: "/api/categories/"+i,
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

        $('.put').on('click',function(){
            $('#mytable > tbody:last').append('<tr><td class = id>'+"12"+'</td><td class = name>'+"aaa"+ '</td><td class = up>' + '<input class ='+ "update" +'type="text"> <button class =' + "upbtn" +">"+"변경"+'</button>'+'</td><td>' + "<button class="+ "delete" +">"+"삭제하기"+"</button>" + '</td></tr>');
        console.log("afasf");
            ////            var name = $('.cate').val();
////            putCategory(name);
		});

        function init() {
//            $('.put').on("click",function(){//추가 버튼
//                console.log("afasf");
////            var name = $('.cate').val();
////            putCategory(name);
//            });
            $('#mytable').on("click",".delete",function() {// 삭제 버튼
                console.log("afasf");
                var id = $(this).closest("tr").find(".id").text();
                deleteList(id);
            });

            $('#mytable').on("click",".upbtn", function() { //업데이트 버
                console.log("afasf");
                var id = $(this).closest("tr").find(".id").text();
                var name = $(this).closest("tr").find(".up > input").val();
                updateCategory(name,id);
                $(this).closest("tr").find(".name").text(name);
            });
		}
})();
</script>
</html>
