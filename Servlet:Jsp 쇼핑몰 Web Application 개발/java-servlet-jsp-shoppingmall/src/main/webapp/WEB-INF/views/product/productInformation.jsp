<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.nhnacademy.shoppingmall.product.domain.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Information</title>

</head>
<body>

<h2>${product.modelName}</h2>

<img src="${product.productImage}" alt="상품이미지">

<br>
<ol>
    <li>${product.unitCost}원</li>
    <li>모델 number : ${product.modelNumber}</li>
    <li>
        <label>카테고리: </label>
        <c:forEach var="category" items="${categoryList}">
            <span>${category.categoryName}</span>
        </c:forEach>
    </li>
    <li>설명 : ${product.description}</li>
</ol>

<form action="/shoppingcart/addProduct.do" method="post">
    <input type="hidden" name="productId" value="${product.productId}">
    <button type="submit" name="productId" value="${product.productId}">Cart Add</button>
</form>

</body>
</html>