<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Shopping Cart</title>
    <style>
        table {
            border-collapse: collapse;
            width: 50%;
            margin: 20px auto;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        input[type="number"] {
            width: 40px;
        }

        .remove-btn {
            background-color: #ff6666;
            color: white;
            padding: 5px 10px;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>

<h2>Shopping Cart</h2>

<table>
    <thead>
    <tr>
        <th>Product ID</th>
        <th>Product Name</th>
        <th>Unit Cost</th>
        <th>Quantity</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${productList}">

        <tr>
            <td>${product.productId}</td>
            <td>${product.modelName}</td>
            <td>${product.unitCost}</td>
            <td>
                <input type="number" name="quantity" min="1">
            </td>
            <td>
                <form action="/shopping/productDelete.do" method="post">
                    <input type="hidden" name="productId" value="${product.productId}">
                    <button type="submit" class="remove-btn">Remove</button>
                </form>
            </td>
        </tr>
    </c:forEach>


    </tbody>
</table>

</body>
</html>