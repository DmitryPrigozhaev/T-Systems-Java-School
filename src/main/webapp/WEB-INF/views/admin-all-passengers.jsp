<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp" flush="true"/>

<%-- TODO --%>

<!-- Page Title -->
<div class="section section-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>All Passengers</h1>
            </div>
        </div>
    </div>
</div>

<h2>List of Employees</h2>
<table>
    <tr>
        <td>id</td><td>email</td><td>password</td><td>first name</td><td>last name</td><td>birth date</td><td>role</td>
    </tr>
    <c:forEach items="${userList}}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.birthDate}</td>
            <td>${user.role}</td>
            <td><a href="<c:url value='edit-${user.email}-user' />">${user.email}</a></td>
            <td><a href="<c:url value='delete-${user.email}-user' />">delete</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="<c:url value='new' />">Add New User</a>


<jsp:include page="footer.jsp" flush="true"/>
