<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="components/header.jsp"/>
<jsp:include page="components/navbar.jsp" flush="true"/>

<%-- JQuery --%>
<script src="https://code.jquery.com/jquery-3.3.1.js"
        integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>
<script src="resources/js/AjaxFindStationSchedule.js"></script>

<%-- Search Schedule --%>
<div class="section section-breadcrumbs center-block">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-md-offset-1 form-inline">
                <div class="form-group">
                    <h1>
                        <label for="stationFromName">Station name: </label>
                        <input id="stationFromName" type="text" class="form-control input-lg" placeholder="station">
                    </h1>
                </div>
                <div class="form-group">
                    <h1>
                        <label for="date"> </label>
                        <input id="date" type="date" class="form-control input-lg">
                    </h1>
                </div>
                <button class="btn btn-lg btn-facebook-login" onclick="findStationSchedule()">search</button>
            </div>
        </div>
    </div>
</div>

<%-- Station Schedule --%>
<div class="section">

    <%-- DOWNLOAD ANIMATION --%>
    <div class="container" style="text-align: center">
        <div>
            <img id="imgLoad" src="resources/img/bx_loader.gif"/>
        </div>
    </div>

    <%-- CONTAINER FOR SCHEDULE --%>
    <div id="scheduleContainer" class="container"></div>

</div>

<%-- TEMPLATE FOR SCHEDULE --%>
<template id="schedule-head">
    <div class="col-md-12 scoreboard-head">
        <div class="col-md-1">N</div>
        <div class="col-md-5">Route</div>
        <div class="col-md-2">Arrival</div>
        <div class="col-md-2">Departure</div>
        <div class="col-md-2">Stay</div>
    </div>
    <%-- SCHEDULE BODY --%>
    <div id="target"></div>
</template>

<%-- ROW TEMPLATE FOR SCHEDULE BODY --%>
<template id="schedule-body">
    <div class="col-md-12 scoreboard-body">
        <div class="col-md-1" id="trainNumber"></div>
        <div class="col-md-5" id="route"></div>
        <div class="col-md-2" id="arrival"></div>
        <div class="col-md-2" id="departure"></div>
        <div class="col-md-2" id="stay"></div>
    </div>
</template>

<%--&lt;%&ndash; TEMPLATE FOR SCHEDULE &ndash;%&gt;--%>
<%--<template id="schedule">--%>
    <%--<table class="table table-sm scoreboard">--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th>№</th>--%>
            <%--<th>Route</th>--%>
            <%--<th>Arrival</th>--%>
            <%--<th>Departure</th>--%>
            <%--<th>Stay</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>

        <%--&lt;%&ndash; TABLE BODY&ndash;%&gt;--%>
        <%--<tbody id="tableBody"></tbody>--%>

    <%--</table>--%>
<%--</template>--%>

<%--&lt;%&ndash; ROW TEMPLATE FOR SCHEDULE TABLE &ndash;%&gt;--%>
<%--<template id="tableRow">--%>
    <%--<tr>--%>
        <%--<th id="trainNumber" scope="row"></th>--%>
        <%--<td id="route"></td>--%>
        <%--<td id="arrival"></td>--%>
        <%--<td id="departure"></td>--%>
        <%--<td id="stay"></td>--%>
    <%--</tr>--%>
<%--</template>--%>

<script>
    document.getElementById('date').valueAsDate = new Date();
</script>

<jsp:include page="components/footer.jsp"/>