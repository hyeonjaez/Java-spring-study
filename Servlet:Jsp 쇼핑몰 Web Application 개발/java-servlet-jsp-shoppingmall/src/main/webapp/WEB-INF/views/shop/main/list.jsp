<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Product List</h2>
<table>
    <tr>
        <th>Product ID</th>
        <th>Product Name</th>
    </tr>
    <c:forEach var="product" items="${productPage.content}">
        <tr>
            <td>${product.productID}</td>
            <td>${product.modelName}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
