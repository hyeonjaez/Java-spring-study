<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Mypage Edit</title>

</head>
<body>

<form method="post" action="/mypage/editUserInformationPost.do">
    <label>아이디</label><br>
    <input type="text" name="userId" id="userId" value="${userId}" readonly><br>

    <label>이름</label><br>
    <input type="text" name="userName" id="userName" value="${userName}" required><br>

    <label>패스워드</label><br>
    <input type="text" name="userPassword" id="userPassword" value="${userPassword}" required><br>
    <br>

    <label>주소</label>
    <div id="address-container">
        <c:forEach var="addr" items="${address}">
            <div>
                <input type="text" name="addressName" value="${addr.addressName}" class="address-input" required>
                <button type="button" onclick="removeAddressField(this.parentNode)">주소 삭제</button>
            </div>
        </c:forEach>
    </div>

    <button type="button" onclick="addAddressField()">주소 추가</button>

    <input type="submit">
</form>

<script>
    function removeAddressField(addressField) {
        var addressContainer = document.getElementById("address-container");
        if (addressContainer.childElementCount > 1) {
            addressContainer.removeChild(addressField);
        } else {
            alert("최소 한 개의 주소는 유지되어야 합니다.");
        }
    }

    function addAddressField() {
        var addressContainer = document.getElementById("address-container");
        var newAddressInput = document.createElement("input");
        newAddressInput.type = "text";
        newAddressInput.name = "addressName";
        newAddressInput.className = "address-input";
        newAddressInput.required = true;

        var removeButton = document.createElement("button");
        removeButton.type = "button";
        removeButton.textContent = "주소 삭제";
        removeButton.onclick = function () {
            removeAddressField(newAddressInput);
        };

        var addressDiv = document.createElement("div");
        addressDiv.appendChild(newAddressInput);
        addressDiv.appendChild(removeButton);

        addressContainer.appendChild(addressDiv);
    }
</script>

</body>
</html>
