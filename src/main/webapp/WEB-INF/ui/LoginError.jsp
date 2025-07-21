<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ksiprus
  Date: 20.07.25
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Ошибка входа</title>
</head>
<body>
<h2>Ошибка входа</h2>
<c:if test="${not empty loginError}">
    <div style="color: red;">${loginError}</div>
</c:if>
<p><a href="${pageContext.request.contextPath}/go/signIn">Вернуться к форме входа</a></p>
</body>
</html>
