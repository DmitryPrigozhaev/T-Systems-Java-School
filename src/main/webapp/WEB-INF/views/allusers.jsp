<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 25.10.2018
  Time: 1:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>Список пользователей</title>

    <style>
        tr:first-child{
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>

</head>

<body>
    <h2>Список пользователей</h2>
    <table>
        <tr>
            <td>Email</td><td>Password</td><td>First Name</td><td>Last Name</td><td>Birth Date</td><td>Role</td>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.birthDate}</td>
                <td>${user.role}</td>
                <td><a href="<c:url value='edit-${user.email}-user' />">edit</a> </td>
                <td><a href="<c:url value='delete-${user.email}-user' />">delete</a> </td>
            </tr>
        </c:forEach>
    </table>
    <br>
<a href="<c:url value='new' />">Add New User</a>
</body>
</html>
