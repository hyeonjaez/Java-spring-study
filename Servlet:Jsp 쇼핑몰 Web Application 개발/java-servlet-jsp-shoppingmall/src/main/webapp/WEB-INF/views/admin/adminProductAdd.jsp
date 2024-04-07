<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/admin/addProduct.do" method="post" enctype="multipart/form-data">

    <label for="modelName">modelName</label><br>
    <input type="text" id="modelName" name="modelName" required><br>

    <label for="modelNumber">modelNumber</label><br>
    <input type="text" id="modelNumber" name="modelNumber" required><br>

    <label for="productImage">productImage</label><br>
    <input type="file" id="productImage" name="productImage"><br>

    <label for="unitCost">unitCost</label><br>
    <input type="number" id="unitCost" name="unitCost" required><br>

    <label>Category</label><br>
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
    <label for="description">description</label><br>
    <input type="text" id="description" name="description" required><br>

    <input type="submit" value="등록" id="submitButton">
</form>
</body>
</html>
