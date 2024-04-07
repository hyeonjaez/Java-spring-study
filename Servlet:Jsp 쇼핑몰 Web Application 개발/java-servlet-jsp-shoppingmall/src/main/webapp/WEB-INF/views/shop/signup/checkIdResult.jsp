<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>아이디 중복 체크 결과</title>
</head>
<body>
<% if (request.getAttribute("isDuplicate") != null && (boolean) request.getAttribute("isDuplicate")) { %>
<p>이 아이디는 이미 사용 중입니다. 다른 아이디를 입력하세요.</p>
<% } else { %>
<p>사용할수 있습니다. 아이디: <%= session.getAttribute("user_Id") %>
</p>
<button onclick="closePopup();">USE</button>
<% } %>

<script>
    function closePopup() {
        if (!<%= (boolean) request.getAttribute("isDuplicate") %>) {
            window.opener.document.getElementById('user_Id').value = '<%= session.getAttribute("user_Id") %>';
        }
        window.close();
    }
</script>
</body>
</html>