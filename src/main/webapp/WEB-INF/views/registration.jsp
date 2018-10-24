<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 25.10.2018
  Time: 1:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Регистрация нового пользователя</title>

    <style>
        .error {
            color: #ff0000;
        }
    </style>

</head>
<body>

<h2>Форма регистрации</h2>
<form:form method="POST" modelAttribute="user">
    <form:input type="hidden" path="id" id="id"/>
    <table>
        <tr>
            <td><label for="email">Email: </label></td>
            <td><form:input path="email" id="email"/></td>
            <td><form:errors path="email" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="first_name">First name: </label></td>
            <td><form:input path="first_name" id="first_name"/></td>
            <td><form:errors path="first_name" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="last_name">Last name: </label></td>
            <td><form:input path="last_name" id="last_name"/></td>
            <td><form:errors path="last_name" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="birth_date">Birth date: </label></td>
            <td><form:input path="birth_date" id="birth_date"/></td>
            <td><form:errors path="birth_date" cssClass="error"/></td>
        </tr>

        <tr>
            <td colspan="4">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update"/>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Register"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
</form:form>
<br/>
<br/>
Назад <a href="<c:url value='/list' />">Список всех пользователей</a>

</body>
<br>
</html>
