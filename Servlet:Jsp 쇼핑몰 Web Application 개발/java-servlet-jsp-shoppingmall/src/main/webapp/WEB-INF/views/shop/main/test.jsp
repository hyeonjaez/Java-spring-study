<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%--<html>--%>
<%--<head>--%>
<%--    <title>Product List</title>--%>
<%--    <style>--%>
<%--        body {--%>
<%--            font-family: 'Arial', sans-serif;--%>
<%--            margin: 20px;--%>
<%--            padding: 20px;--%>
<%--        }--%>

<%--        h2 {--%>
<%--            color: #333;--%>
<%--        }--%>

<%--        ul {--%>
<%--            list-style: none;--%>
<%--            padding: 0;--%>
<%--        }--%>

<%--        li {--%>
<%--            display: inline-block;--%>
<%--            margin-right: 10px;--%>
<%--        }--%>

<%--        table {--%>
<%--            width: 100%;--%>
<%--            border-collapse: collapse;--%>
<%--            margin-top: 20px;--%>
<%--        }--%>

<%--        th, td {--%>
<%--            border: 1px solid #ddd;--%>
<%--            padding: 8px;--%>
<%--            text-align: left;--%>
<%--        }--%>

<%--        th {--%>
<%--            background-color: #f2f2f2;--%>
<%--        }--%>

<%--        img {--%>
<%--            max-width: 100%;--%>
<%--            height: auto;--%>
<%--            border: 1px solid #ddd;--%>
<%--        }--%>

<%--        a {--%>
<%--            text-decoration: none;--%>
<%--            color: #333;--%>
<%--        }--%>

<%--        a:hover {--%>
<%--            color: #009688;--%>
<%--        }--%>

<%--        strong {--%>
<%--            font-weight: bold;--%>
<%--            color: #009688;--%>
<%--        }--%>

<%--        div.pagination {--%>
<%--            margin-top: 20px;--%>
<%--        }--%>

<%--        div.pagination a {--%>
<%--            color: #333;--%>
<%--            padding: 8px 16px;--%>
<%--            text-decoration: none;--%>
<%--        }--%>

<%--        div.pagination a.active {--%>
<%--            background-color: #009688;--%>
<%--            color: white;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>

<%--<h2>Product List</h2>--%>


<%--<ul>--%>
<%--    <c:forEach var="category" items="${categoryList}">--%>
<%--        <li><a href="/page.do?categoryNumber=${category.categoryId}">${category.categoryName}</a></li>--%>
<%--    </c:forEach>--%>
<%--</ul>--%>


<%--<c:if test="${not empty category}">--%>
<%--    <h3>Selected Category: ${category.categoryName}</h3>--%>
<%--</c:if>--%>


<%--<table>--%>
<%--    <tr>--%>
<%--        <th>Product ID</th>--%>
<%--        <th>Product Name</th>--%>
<%--        <th>Product Image</th>--%>
<%--        <th>Product UnitCost</th>--%>
<%--        <th>상세 정보</th>--%>
<%--    </tr>--%>

<%--    <c:forEach var="product" items="${productPage.content}">--%>
<%--        <tr>--%>
<%--            <td>${product.productId}</td>--%>
<%--            <td>${product.modelName}</td>--%>
<%--            <td><img src="${product.productImage}" alt="상품이미지"></td>--%>
<%--            <td>${product.unitCost}</td>--%>
<%--            <td><a href="/productInformation.do?productId=${product.productId}">상세정보</a></td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>


<%--<div class="pagination">--%>
<%--    <c:forEach var="page" begin="1" end="${productPage.totalPage}">--%>
<%--        <c:choose>--%>
<%--            <c:when test="${page eq pageNumber}">--%>
<%--                <a href="#" class="active">${page}</a>--%>
<%--            </c:when>--%>
<%--            <c:otherwise>--%>
<%--                <a href="/page.do?categoryNumber=${categoryNumber}&pageNumber=${page}">${page}</a>--%>
<%--            </c:otherwise>--%>
<%--        </c:choose>--%>
<%--    </c:forEach>--%>
<%--</div>--%>

<%--</body>--%>
<%--</html>--%>
