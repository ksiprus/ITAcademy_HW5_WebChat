<%--
  Created by IntelliJ IDEA.
  User: ksiprus
  Date: 16.07.25
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Сообщение</title>
</head>
<body>
<h2>Отправить сообщение пользователю</h2>

<c:if test="${not empty requestScope.error}">
    <div style="color: red;">${requestScope.error}</div>
</c:if>

<form method="POST" action="${pageContext.request.contextPath}/ui/user/message">
    Кому (логин): <input type="text" name="to" required><br>
    Текст: <textarea name="text" required></textarea><br>
    <input type="submit" value="Отправить">
</form>
<a href="${pageContext.request.contextPath}/ui/user/chats">Мои сообщения</a>
</body>
</html>
