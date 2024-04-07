<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
</head>
<body>
<h2>회원가입</h2>

<form action="/signupAction.do" method="post">
    <label for="user_Id">아이디</label><br>
    <input type="text" id="user_Id" name="user_Id" readonly required>
    <button type="button" onclick="openCheckIdPopup()">중복 체크</button>
    <br>

    <label for="username">사용자명</label><br>
    <input type="text" id="username" name="username" required oninput="enableInput()"><br>

    <label for="password">비밀번호</label><br>
    <input type="password" id="password" name="password" onkeyup=comparePassword() required oninput="enableInput()"><br>

    <label for="password_check">비밀번호 확인</label><br>
    <input type="password_check" id="password_check" name="password_check" onkeyup=comparePassword() required
           oninput="enableInput()">
    <span id="passwordMessage"></span><br>

    <label for="birth">생일(ex.19990808)</label><br>
    <input type="number" id="birth" name="birth" maxlength="8" placeholder="ex):20230101" required
           oninput="enableInput()"><br><br>

    <label>주소</label><br>
    <div id="addressContainer">
        <div>
            <input type="text" class="addressInput" name="addresses[]" required>
            <button type="button" onclick="removeAddress(this)">주소 삭제</button>
        </div>
    </div>
    <button type="button" onclick="addAddress()">주소 추가</button>

    <input type="submit" value="가입" id="submitButton" disabled>
</form>


<script>
    function enableInput() {
        var userId = document.getElementById("user_Id").value.trim();
        var username = document.getElementById("username").value.trim();
        var password = document.getElementById("password").value.trim();
        var confirmPassword = document.getElementById("password_check").value.trim();
        var birth = document.getElementById("birth").value.trim();
        var submitButton = document.getElementById("submitButton");

        if (userId !== "" && username !== "" && password !== "" && confirmPassword !== "" && birth !== "" && comparePassword()) {
            submitButton.disabled = false;
        } else {
            submitButton.disabled = true;
        }
    }


    function setUserId(userId) {
        document.getElementById('user_Id').value = userId;
    }

    function openCheckIdPopup() {
        var popupWindow = window.open("/signup/checkId.do", "CheckIdPopup", "width=500, height=600");

        window.handleDuplicateCheck = function (isDuplicate, userId) {
            var userIdField = document.getElementById("user_Id");

            setUserId(userId);
            popupWindow.close();
            enableInput();
        };
    }
</script>

<script>
    function comparePassword() {
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("password_check").value;

        if (password === confirmPassword) {
            document.getElementById("passwordMessage").innerHTML = "비밀번호 일치";
            return true;
        } else {
            document.getElementById("passwordMessage").innerHTML = "비밀번호 불일치";
            return false;
        }
    }

    function addAddress() {
        var container = document.getElementById("addressContainer");
        var newAddressDiv = document.createElement("div");
        var newAddressInput = document.createElement("input");
        var deleteButton = document.createElement("button");

        newAddressInput.type = "text";
        newAddressInput.className = "addressInput";
        newAddressInput.name = "addresses[]";
        newAddressInput.required = true;

        deleteButton.type = "button";
        deleteButton.textContent = "주소 삭제";
        deleteButton.onclick = function () {
            removeAddress(newAddressDiv);
        };

        newAddressDiv.appendChild(newAddressInput);
        newAddressDiv.appendChild(deleteButton);
        container.appendChild(newAddressDiv);
    }

    function removeAddress(addressDiv) {
        var container = document.getElementById("addressContainer");
        container.removeChild(addressDiv);
    }


</script>
</body>
</html>