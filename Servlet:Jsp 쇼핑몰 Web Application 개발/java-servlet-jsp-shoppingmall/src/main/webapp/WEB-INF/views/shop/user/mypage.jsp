<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${userName}의 마이페이지</h1>


<label>아이디: ${userId}</label><br>
<label>이름: ${userName}</label><br>
<label>패스워드: ${userPassword}</label><br>
<label>생일: ${userBirth}</label><br>
<label>권한: ${userAuth}</label><br>
<label>포인트: ${userPoint}</label><br>
<label>생성일자: ${createdAt}</label><br>
<label>마지막 로그인 일자 : ${latestLoginAt}</label><br>

<h2>주소 정보</h2>

<table>
    <tr>
        <th>주소 정보</th>
    </tr>
    <c:forEach var="address" items="${address}">
        <tr>
            <td>주소: ${address.addressName}</td>
        </tr>
    </c:forEach>

</table>


<a href="/mypage/editUserInformation.do">회원 정보 수정</a>
<%--<a href=""></a>--%>
<%--<a href=""></a>--%>
<%--<a href=""></a>--%>
</body>
</html>
