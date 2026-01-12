<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Calculator</title>
</head>
<body>

<h2>Simple Calculator</h2>

<form action="calculate" method="post">
    <input type="number" name="a" required>
    <input type="number" name="b" required>

    <select name="operator">
        <option value="add">+</option>
        <option value="sub">-</option>
        <option value="mul">*</option>
        <option value="div">/</option>
    </select>

    <button type="submit">Calculate</button>
</form>

<c:if test="${not empty result}">
    <h3>Kết quả: ${result}</h3>
</c:if>

<c:if test="${not empty error}">
    <h3 style="color:red">${error}</h3>
</c:if>

</body>
</html>
