<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Result</title>
</head>
<body>

<h2>Gia vị đã chọn:</h2>

<c:choose>
    <c:when test="${empty condiment}">
        <p>Không chọn gia vị nào</p>
    </c:when>
    <c:otherwise>
        <ul>
            <c:forEach items="${condiment}" var="c">
                <li>${c}</li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>

</body>
</html>
