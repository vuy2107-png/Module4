<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Mailbox Settings</title>
</head>
<body>
<h3>Cấu hình Hòm Mail</h3>

<form:form modelAttribute="config" action="/settings" method="post">
    <table>
        <tr>
            <td>Ngôn ngữ:</td>
            <td>
                <form:select path="language">
                    <form:options items="${languages}"/>
                </form:select>
            </td>
        </tr>

        <tr>
            <td>Kích thước trang:</td>
            <td>
                <form:select path="pageSize">
                    <form:options items="${pageSizes}"/>
                </form:select>
            </td>
        </tr>

        <tr>
            <td>Lọc Spam:</td>
            <td>
                <form:checkbox path="spamFilter"/>
            </td>
        </tr>

        <tr>
            <td>Chữ ký:</td>
            <td>
                <form:textarea path="signature"/>
            </td>
        </tr>

        <tr>
            <td></td>
            <td><input type="submit" value="Cập nhật Cấu hình"></td>
        </tr>
    </table>
</form:form>
</body>
</html>