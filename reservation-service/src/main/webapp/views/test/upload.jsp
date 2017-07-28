<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>file등록 폼</title>
</head>
<body>
<form method="post" action="/files" enctype="multipart/form-data">
    title : <input type="text" name="title"><br>
    <input type="file" name="file"><br>
    <input type="file" name="file"><br>
    <input type="submit" value="등록">
</form>
</body>
</html>