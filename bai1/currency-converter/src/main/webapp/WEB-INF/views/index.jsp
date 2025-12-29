<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Chuyển đổi tiền tệ</title>
</head>
<body>

<h2>Chuyển đổi USD → VNĐ</h2>

<form action="convert" method="post">
    Tỉ giá:
    <input type="number" step="0.01" name="rate" required><br><br>

    USD:
    <input type="number" step="0.01" name="usd" required><br><br>

    <button type="submit">Chuyển đổi</button>
</form>

<c:if test="${not empty vnd}">
    <h3>Kết quả: ${vnd} VNĐ</h3>
</c:if>

</body>
</html>
