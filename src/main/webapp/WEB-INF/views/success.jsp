<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 25.10.2018
  Time: 1:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Подтверждение регистрации</title>
</head>
<body>

message : ${success}
<br/>
<br/>
Назад <a href="<c:url value='list' />">Список всех пользователей</a>

</body>

</html>