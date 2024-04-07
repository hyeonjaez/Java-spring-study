<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin User Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h2 {
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tbody tr:hover {
            background-color: #f5f5f5;
        }

        div.pagination {
            margin-top: 20px;
            display: flex;
            justify-content: center;
        }

        div.pagination a {
            padding: 8px 16px;
            text-decoration: none;
            color: #333;
            border: 1px solid #ddd;
            margin: 0 4px;
        }

        div.pagination a.active {
            background-color: #4CAF50;
            color: white;
            border: 1px solid #4CAF50;
        }
    </style>
</head>
<body>

<h2>Admin User Management</h2>

<c:if test="${not empty pageUser.content}">
    <table>
        <thead>
        <tr>
            <th>User ID</th>
            <th>User Name</th>
            <th>User Birth</th>
            <th>User Auth</th>
            <th>User Point</th>
            <th>CreatedAt</th>
            <th>LatestLoginAt</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${pageUser.content}">
            <tr>
                <td>${user.userId}</td>
                <td>${user.userName}</td>
                <td>${user.userBirth}</td>
                <td>${user.userAuth}</td>
                <td>${user.userPoint}</td>
                <td>${user.createdAt}</td>
                <td>${user.latestLoginAt}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="pagination">
        <c:forEach var="pageNumber" begin="1" end="${pageUser.totalPage}">
            <a href="?page=${pageNumber}">${pageNumber}</a>
        </c:forEach>
    </div>
</c:if>

</body>
</html>
