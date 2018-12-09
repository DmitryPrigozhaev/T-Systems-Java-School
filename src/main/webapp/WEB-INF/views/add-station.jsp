<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="components/header.jsp"/>
<jsp:include page="components/navbar.jsp"/>

<!-- Page Title -->
<div class="section section-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <c:choose>
                    <c:when test="${edit}">
                        <h1>Edit station</h1>
                    </c:when>
                    <c:otherwise>
                        <h1>Register a new station</h1>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">

                <%-- INFORMATION PANEL --%>
                <c:choose>
                    <c:when test="${saveStationSuccess}">
                        <div class="panel panel-warning">
                            <h4 style="margin: 10px">${success}</h4>
                        </div>
                    </c:when>
                    <c:when test="${saveStationFailure}">
                        <div class="panel panel-warning">
                            <h4 style="margin: 10px">${failure}</h4>
                        </div>
                    </c:when>
                    <c:when test="${updateStationSuccess}">
                        <div class="panel panel-warning">
                            <h4 style="margin: 10px">${success}</h4>
                        </div>
                    </c:when>
                    <c:when test="${updateStationFailure}">
                        <div class="panel panel-warning">
                            <h4 style="margin: 10px">${failure}</h4>
                        </div>
                    </c:when>
                </c:choose>

                <%-- STATION ADD/EDIT PANEL --%>
                <table class="shopping-cart">
                    <tr>
                        <td>
                            <form:form method="POST" modelAttribute="station" cssClass="form-horizontal">
                                <form:input type="hidden" path="id" id="id"/>
                                <label for="name">Station name</label>
                                <form:input path="name" id="name" class="form-control" type="text"
                                            placeholder="Station name"/>
                                <c:choose>
                                    <c:when test="${edit}">
                                        <div class="col-md-4 col-md-offset-8">
                                            <br>
                                            <button class="btn-blue btn-block" value="Update">Update</button>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="col-md-4 col-md-offset-8">
                                            <br>
                                            <button class="btn-blue btn-block" value="Register">Create</button>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </form:form>
                        </td>
                    </tr>
                </table>

            </div>

            <%-- BUTTON RETURN TO ALL STATIONS --%>
            <div class="col-md2 col-md-offset-1">
                <form action="<c:url value='admin-all-stations' />">
                    <button type="submit" class="btn pull-right">All stations</button>
                </form>
            </div>

        </div>
    </div>
</div>

<jsp:include page="components/footer.jsp"/>