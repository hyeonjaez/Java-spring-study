<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>관리자 페이지</title>
</head>
<body>


<% String username = (String) session.getAttribute("username");

%>

<h1>관리자 페이지</h1>
<p>안녕하세요, <%= username %>님!</p><br>

<a href="/admin/product.do">상품 관리</a><br>
<br>
<a href="/admin/user.do">회원 관리</a>
<br>
<br>


<a href="/logout.do">로그아웃</a>

</body>
</html>