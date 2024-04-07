<%@ page import="com.nhnacademy.shoppingmall.product.domain.Product" %>
<%@ page import="com.nhnacademy.shoppingmall.product.domain.Category" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>product_edit</title>
</head>
<body>
<h2>Product Edit</h2>
<%
    Product product = (Product) request.getSession(false).getAttribute("product");
    List<Integer> categoryId = (List<Integer>) request.getAttribute("categoryProductList");
%>
<form method="post" action="/admin/editProduct.do">
    <label>물건 아이디</label><br>
    <input type="text" name="productId" id="productId" value="<%=product.getProductId()%>" readonly><br>

    <label for="modelName">물건 이름</label>
    <br>
    <input type="text" name="modelName" id="modelName" value="<%=product.getModelName()%>" required><br>

    <label for="modelNumber">물건 넘버</label>
    <br>
    <input type="text" name="modelNumber" id="modelNumber" value="<%=product.getModelNumber()%>" required><br>

    <label for="productImage">물건 사진 url</label>
    <br>
    <input type="text" name="productImage" id="productImage" value="<%=product.getProductImage()%>" required><br>

    <label for="unitCost">물건 가격</label>
    <br>
    <input type="number" name="unitCost" id="unitCost" value="<%=product.getUnitCost()%>" required><br>

    <label>카테 고리</label><br>
    <c:forEach var="category" items="${categoryList}">
        <label><input type="checkbox" name="category" value="${category.categoryId}"
                      onclick="selectLimit()">${category.categoryName}</label>
    </c:forEach>

    <script>
        function selectLimit() {
            let checkboxes = document.querySelectorAll('input[name="category"]:checked');

            if (checkboxes.length > 3) {
                alert("최대 3개의 카테고리만 선택할 수 있습니다.");
                checkboxes[checkboxes.length - 1].checked = false;
            }
        }
    </script>
    <br>

    <label for="description">물건 설명</label><br>
    <input type="text" name="description" id="description" value="<%=product.getDescription()%>" required><br>

    <input type="submit" value="수정" id="editButton">
</form>
<br>
<form method="post" action="/admin/productDelete.do">
    <input type="submit" value="삭제" id="deleteButton">
</form>


</body>
</html>
