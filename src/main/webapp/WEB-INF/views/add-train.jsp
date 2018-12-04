<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="components/header.jsp"/>
<jsp:include page="components/navbar.jsp"/>

<%-- JQuery --%>
<script src="https://code.jquery.com/jquery-3.3.1.js"
        integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>

<!-- Page Title -->
<div class="section section-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <c:choose>
                    <c:when test="${edit}">
                        <h1>Edit train</h1>
                    </c:when>
                    <c:otherwise>
                        <h1>Register a new train</h1>
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
                    <c:when test="${saveTrainSuccess}">
                        <div class="panel panel-warning">
                            <h4 style="margin: 10px">${success}</h4>
                        </div>
                    </c:when>
                    <c:when test="${saveTrainFailure}">
                        <div class="panel panel-warning">
                            <h4 style="margin: 10px">${failure}</h4>
                        </div>
                    </c:when>
                    <c:when test="${updateTrainSuccess}">
                        <div class="panel panel-warning">
                            <h4 style="margin: 10px">${success}</h4>
                        </div>
                    </c:when>
                    <c:when test="${updateTrainFailure}">
                        <div class="panel panel-warning">
                            <h4 style="margin: 10px">${failure}</h4>
                        </div>
                    </c:when>
                </c:choose>

                <%-- TRAIN ADD/EDIT PANEL --%>
                <form:form method="POST" name="new-train" modelAttribute="trainDto">
                    <c:choose>
                        <c:when test="${edit}">
                            <form:input type="hidden" path="id" id="id"/>
                        </c:when>
                    </c:choose>
                    <%-- TRAIN NUMBER--%>
                    <div class="form-group">
                        <c:choose>
                            <c:when test="${edit}">
                                <label for="number">Number of train</label>
                                <input type="text" class="form-control" name="number" id="number"
                                       value="${trainDto.number}" disabled/>
                            </c:when>
                            <c:otherwise>
                                <label for="number">Set train number</label>
                                <input type="text" class="form-control" name="number" id="number"
                                       placeholder="number"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <%-- NUMBER OF CARRIAGES --%>
                    <div class="form-group">
                        <label for="numberOfCarriages">Set number of carriages</label>
                        <input type="text" class="form-control" name="numberOfCarriages" id="numberOfCarriages"
                               placeholder="number of carriages" value="${trainDto.numberOfCarriages}"/>
                    </div>
                    <%-- ROUTE --%>
                    <div class="form-group">
                        <label for="routeName">Choose route</label>
                        <select class="form-control" id="routeName" name="routeName">
                            <c:forEach items="${routeList}" var="route">
                                <option value="${route.name}"><c:out value="${route.name}"/></option>
                            </c:forEach>
                        </select>
                        <div class="col-md-offset-10" style="margin-top: 5px">
                            <label for="emptyRoute">no route</label>
                            <input onclick="changeCheckbox()" type="checkbox" id="emptyRoute"/>
                            <script>
                                function changeCheckbox() {
                                    if ($("#emptyRoute").is(':checked')) {
                                        $("#routeName").prop('disabled', true);
                                    } else {
                                        $("#routeName").prop('disabled', false);
                                    }
                                }
                            </script>
                        </div>
                    </div>

                    <%-- REGISTER / UPDATE --%>
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Create"/>
                        </c:otherwise>
                    </c:choose>
                </form:form>
            </div>
            <div class="col-md2 col-md-offset-1">
                <form action="<c:url value='admin-all-trains' />">
                    <button type="submit" class="btn pull-right">All trains</button>
                </form>
            </div>
        </div>
    </div>
</div>

<br/>
<br/>

<jsp:include page="components/footer.jsp"/>