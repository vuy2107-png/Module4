<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh Sách Bài Hát</title>
</head>
<body>
<h3>Danh Sách Bài Hát Đã Upload</h3>

<% String message = (String) request.getAttribute("message");
    if (message != null) { %>
<p style="color: green;"><%= message %></p>
<% } %>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Tên Bài Hát</th>
        <th>Nghệ Sĩ</th>
        <th>Thể Loại</th>
        <th>Tên File Server</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="song" items="${songs}">
        <tr>
            <td>${song.id}</td>
            <td>${song.name}</td>
            <td>${song.artist}</td>
            <td>${song.genre}</td>
            <td>${song.filePath}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a href="/songs/upload">Upload thêm bài hát</a></p>
</body>
</html>