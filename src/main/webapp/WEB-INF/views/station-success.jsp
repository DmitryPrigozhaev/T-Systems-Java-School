<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="components/header.jsp"/>
<jsp:include page="components/navbar.jsp"/>

message : ${success}
<br/>
<br/>
Назад <a href="<c:url value='admin-all-stations' />">All stations</a>

<jsp:include page="components/footer.jsp"/>