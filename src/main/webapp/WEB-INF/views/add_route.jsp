<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="components/header.jsp"/>
<jsp:include page="components/navbar.jsp"/>

<script src="https://code.jquery.com/jquery-3.3.1.js"
        integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>
<script src="../../resources/js/AjaxAddRoute.js"></script>

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
                                    <input type="text" class="form-control" id="routeName" value="${route.name}"
                                           placeholder="${route.name}" disabled/>
                                </c:when>
                                <c:otherwise>
                                    <label for="routeName">Add route name</label>
                                    <input type="text" class="form-control" id="routeName" placeholder="name"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <%-- Choose Station --%>
                            <div class="col-md-4">
                                <label for="stationName">Choose station</label>
                                <select class="form-control" id="stationName">
                                    <c:forEach items="${stationList}" var="station">
                                        <option value="${station.name}">${station.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <%-- Date Arrival --%>
                            <div class="col-md-4">
                                <label for="dateArrival">Date arrival</label>
                                <input class="form-control" type="datetime-local" id="dateArrival" title="dateArrival">
                            </div>

                            <%-- Date Departure --%>
                            <div class="col-md-4">
                                <label for="dateDeparture">Date departure</label>
                                <input class="form-control" type="datetime-local" id="dateDeparture"
                                       title="dateDeparture">
                            </div>
                            <div class="col-md-4 col-md-offset-8">
                                <br>
                                <c:choose>
                                    <c:when test="${edit}">
                                        <button class="btn-blue btn-block" onclick="editRoutePointButtonClick()">
                                            Add point
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn-blue btn-block" onclick="addRoutePointButtonClick()">
                                            Add point
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </td>
                    </tr>
                </table>

                <%-- TABLE OF ROUTE POINTS FOR THIS ROUTE --%>
                <div class="grid-container" id="tableContainer">
                     <%--insert items by id="tableContainer" --%>
                    <div class="col-md-offset-5">
                        <img src="../../resources/img/bx_loader.gif" id="imgLoad"/>
                    </div>
                </div>
                <%-- END TABLE OF ROUTE POINTS --%>

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

<jsp:include page="components/footer.jsp"/>