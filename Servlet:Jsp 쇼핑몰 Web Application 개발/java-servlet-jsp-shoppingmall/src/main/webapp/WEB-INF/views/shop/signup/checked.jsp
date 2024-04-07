<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Id 중복 체크</h2>

<form action="/signup/checkIdAction.do" method="post">
    <label for="user_Id">아이디:</label>
    <input type="text" id="user_Id" name="user_Id" required><br>

    <input type="submit" value="중복 체크">
</form>
</body>
</html>
