<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>

<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>
<script src="../../resources/js/sendAjaxRoute.js"></script>

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
            <div class="col-md-8 col-md-offset-2">
                <table class="shopping-cart">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${edit}">
                                    <label for="routeName">Route</label>
                                    <input type="text" class="form-control" name="routeName" id="routeName" placeholder="${route.name}" disabled/>
                                </c:when>
                                <c:otherwise>
                                    <label for="routeName">Add route name</label>
                                    <input type="text" class="form-control" name="routeName" id="routeName"
                                           placeholder="name"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <%-- Choose station --%>
                            <div class="col-md-4">
                                <label for="station">Choose station</label>
                                <select class="form-control" id="station" name="station">
                                    <c:forEach items="${stationList}" var="station">
                                        <option value="${station.id}"><c:out value="${station.name}"/></option>
                                    </c:forEach>
                                </select>
                            </div>

                            <%-- Date arrival --%>
                            <div class="col-md-4">
                                <label for="dateArrival">Date arrival</label>
                                <input class="form-control" type="datetime-local"
                                       id="dateArrival" title="dateArrival">
                            </div>

                            <%-- Date departure --%>
                            <div class="col-md-4">
                                <label for="dateDeparture">Date departure</label>
                                <input class="form-control" type="datetime-local"
                                       id="dateDeparture" title="dateDeparture">
                            </div>
                            <div class="col-md-4 col-md-offset-8">
                                <br>
                                <button class="btn-blue btn-block" onclick="sendRoutePointButtonClick()">Im button
                                </button>
                            </div>
                        </td>
                    </tr>
                </table>

                <%-- TABLE OF ROUTE POINTS FOR THIS ROUTE --%>
                <div id="tableContainer" class="grid-container">
                    <table class="tab-content">
                        <tr>
                            <td class="col-md-1">id</td>
                            <td class="col-md-2">station name</td>
                            <td class="col-md-2">date arrival</td>
                            <td class="col-md-2">date departure</td>
                        </tr>
                        <c:forEach items="${}" var="routePoint">
                            <tr>
                                <td>${routePoint.id}</td>
                                <td>${routePoint.station.name}</td>
                                <td>${routePoint.dateArrival}</td>
                                <td>${routePoint.dateDeparture}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
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