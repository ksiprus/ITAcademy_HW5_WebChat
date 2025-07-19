<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ksiprus
  Date: 16.07.25
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
</head>
<body>
<h2>Регистрация пользователя</h2>
<form method="POST" action="${pageContext.request.contextPath}/api/user">
    Логин: <input type="text" name="login" required><br>
    Пароль: <input type="password" name="password" required><br>
    ФИО: <input type="text" name="name" required><br>
    Дата рождения: <input type="date" name="birthDate" required><br>
    <input type="submit" value="Зарегистрироваться">
</form>
<c:if test="${not empty error}">
    <div style="color: red;">${error}</div>
</c:if>
</body>
</html>
