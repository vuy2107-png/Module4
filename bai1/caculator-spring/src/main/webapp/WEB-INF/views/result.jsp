<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Kết quả tính toán</title>
</head>
<body>

<h2>Kết quả phép tính</h2>

<c:if test="${not empty result}">
    <p><strong>Kết quả:</strong> ${result}</p>
</c:if>

<c:if test="${not empty error}">
    <p style="color:red"><strong>Lỗi:</strong> ${error}</p>
</c:if>

<a href="/">Quay lại</a>

</body>
</html>
