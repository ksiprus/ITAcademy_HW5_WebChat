<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: ksiprus
  Date: 16.07.25
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head><title>Мои сообщения</title></head>
<body>
<h2>Мои сообщения</h2>
<c:forEach var="msg" items="${messages}">
  <div>
    <b>${msg.sentAt}</b> — <b>${msg.senderId}</b>: ${msg.text}
  </div>
</c:forEach>
<a href="ui/user/message.jsp">Отправить сообщение</a>
</body>
</html>
