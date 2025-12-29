<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Bức ảnh của ngày</title>
</head>
<body>

<h2>Thêm ảnh mới</h2>
<form method="post" action="picture">
    Tác giả: <input type="text" name="author" required><br>
    URL ảnh: <input type="text" name="url" required><br>
    <button type="submit">Lưu</button>
</form>

<hr>

<h2>Danh sách ảnh</h2>

<c:forEach items="${pictures}" var="p">
    <p>
        <b>${p.author}</b><br>
        <img src="${p.url}" width="200"><br>
        ❤️ ${p.likes}
    </p>
</c:forEach>

</body>
</html>
