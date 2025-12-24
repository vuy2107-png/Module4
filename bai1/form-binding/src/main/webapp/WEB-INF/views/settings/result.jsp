<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cấu hình đã Lưu</title>
</head>
<body>
<h3>Cấu hình đã được lưu thành công!</h3>

<p>Ngôn ngữ: ${config.language}</p>
<p>Kích thước trang: ${config.pageSize}</p>
<p>Lọc Spam: ${config.spamFilter ? 'Đã bật' : 'Đã tắt'}</p>
<p>Chữ ký:</p>
<pre>${config.signature}</pre>

<p><a href="/settings">Quay lại trang cấu hình</a></p>
</body>
</html>