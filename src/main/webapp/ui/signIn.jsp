<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ksiprus
  Date: 16.07.25
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
</head>
<body>
<h2>Вход в систему</h2>
<form method="POST" action="${pageContext.request.contextPath}/ui/login">
    Логин: <input type="text" name="login" required><br> <!-- Добавлен атрибут required -->
    Пароль: <input type="password" name="password" required><br> <!-- Добавлен атрибут required -->
    <input type="submit" value="Войти">
</form>

<c:if test="${not empty error}">
    <div style="color: red;">${error}</div>
</c:if>
</body>
</html>
