<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>

<!-- Page Title -->
<div class="section section-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Register a new route</h1>
            </div>
        </div>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-7 col-md-offset-2">
                <table class="shopping-cart">
                    <form:form name="add-new-route" action="" method="post">
                        <tr>
                            <td>
                                    <%-- Add route --%>
                                    <%--<div class="form-group">--%>
                                <label for="routeId">Choose route</label>
                                <select class="form-control" id="routeId" name="routeId">
                                    <c:forEach items="${routeList}" var="route">
                                        <option value="${route.id}"><c:out value="${route.name}"/></option>
                                    </c:forEach>
                                </select>
                                    <%--</div>--%>
                            </td>
                        </tr>
                        <%-- Add station --%>
                        <tr>
                            <td>
                                <table>
                                    <thead>
                                    <tr>
                                        <td class="col-md-2">
                                            <label for="station">Choose station</label>
                                        </td>
                                        <td class="col-md-2">
                                            <label for="station">Date arrival</label>
                                        </td>
                                        <td class="col-md-2">
                                            <label for="station">Date departure</label>
                                        </td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                            <%-- Choose station --%>
                                        <td class="col-md-2">
                                            <select class="form-control" id="station" name="station">
                                                <c:forEach items="${stationList}" var="station">
                                                    <option value="${station.id}"><c:out
                                                            value="${station.name}"/></option>
                                                </c:forEach>
                                            </select>
                                        </td>

                                            <%-- Date arrival --%>
                                        <td class="col-md-2">
                                            <div class="col-xs-10">
                                                <input class="form-control" type="datetime-local"
                                                       id="dateArrival">
                                            </div>
                                        </td>

                                            <%-- Date departure --%>
                                        <td class="col-md-2">
                                            <div class="col-xs-10">
                                                <input class="form-control" type="datetime-local"
                                                       id="dateDeparture">
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>

                    </form:form>
                </table>

            </div>
            <div class="col-md2 col-md-offset-1">
                <form action="<c:url value='admin-all-routes' />">
                    <button type="submit" class="btn pull-right">All routes</button>
                </form>
            </div>
        </div>
    </div>
</div>

<br/>
<br/>

<jsp:include page="footer.jsp"/>