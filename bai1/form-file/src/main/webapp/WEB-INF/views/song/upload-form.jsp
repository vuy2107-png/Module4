<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Upload Bài Hát</title>
</head>
<body>
<h3>Upload Bài Hát Mới</h3>

<% String message = (String) request.getAttribute("message");
    if (message != null) { %>
<p style="color: <%= message.startsWith("Lỗi") ? "red" : "green" %>;"><%= message %></p>
<% } %>

<form:form modelAttribute="songForm" action="/songs/upload" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Tên Bài Hát:</td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td>Nghệ Sĩ:</td>
            <td><form:input path="artist"/></td>
        </tr>
        <tr>
            <td>Thể Loại:</td>
            <td>
                <form:select path="genre">
                    <form:options items="${genres}"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>Chọn File Nhạc:</td>
            <td>
                <input type="file" name="songFile"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Upload Bài Hát"></td>
        </tr>
    </table>
</form:form>
</body>
</html>