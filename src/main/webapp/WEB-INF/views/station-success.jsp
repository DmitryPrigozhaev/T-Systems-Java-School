<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>

message : ${success}
<br/>
<br/>
Назад <a href="<c:url value='admin-all-stations' />">All stations</a>

<jsp:include page="footer.jsp"/>