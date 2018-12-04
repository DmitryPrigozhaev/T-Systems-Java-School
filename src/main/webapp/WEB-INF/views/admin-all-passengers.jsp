<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="components/header.jsp"/>
<jsp:include page="components/navbar.jsp" flush="true"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<%-- JQuery --%>
<script src="https://code.jquery.com/jquery-3.3.1.js"
        integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>
<script src="../../resources/js/AjaxFindPassengers.js"></script>

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

<%-- PASSENGER PANEL --%>
<div class="section">
    <div class="container">
        <div class="row" style="margin-bottom: 15px">
            <div class="col-md-7 col-md-offset-3">
                <label class="form-group" for="routeName">Choose train: </label>
                <div>
                    <select class="form-control" id="routeName" name="routeName">
                        <c:forEach items="${trainDtoList}" var="trainDto">
                            <option id="trainNumber" value="${trainDto.number}">
                                train №<c:out value="${trainDto.number}"/>
                                «<c:out value="${trainDto.routeName}"/>»
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-3 col-md-offset-9">
                    <br>
                    <button class="btn-blue btn-block" onclick="getPassenger()">Select</button>
                </div>
            </div>
        </div>

        <%-- DOWNLOAD ANIMATION --%>
        <div class="container" style="text-align: center">
            <div>
                <img id="imgLoad" src="../../resources/img/bx_loader.gif"/>
            </div>
        </div>

        <%-- USER CONTAINER --%>
        <div id="userContainer"></div>

    </div>
</div>

<%-- TEMPLATE FOR USER TABLE --%>
<<template id="userTable">
<table class="table table-sm">
    <thead>
    <tr>
        <th>#</th>
        <th>Email</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Birthdate</th>
    </tr>
    </thead>

    <%-- TABLE BODY--%>
    <tbody id="tableBody"></tbody>

</table>
</template>

<%-- TEMPLATE FOR USER ROW FOR USER TABLE --%>
<template id="tableRow">
    <tr>
        <th id="number" scope="row"></th>
        <td id="email"></td>
        <td id="firstName"></td>
        <td id="lastName"></td>
        <td id="birthdate"></td>
    </tr>
</template>

<jsp:include page="components/footer.jsp" flush="true"/>
