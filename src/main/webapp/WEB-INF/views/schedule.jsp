<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="components/header.jsp"/>
<jsp:include page="components/navbar.jsp" flush="true"/>
<jsp:include page="components/found_trains.jsp"/>

<script src="resources/js/AjaxFindTrain.js"></script>

<!-- Page Title -->
<div class="section section-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Schedule</h1>
            </div>
        </div>
    </div>
</div>

<%-- Search Train --%>
<div class="section section-breadcrumbs center-block">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-md-offset-1 form-inline">
                <div class="form-group">
                    <h1>
                        <label for="stationFromName"> </label>
                        <input id="stationFromName" name="stationFromName" type="text" class="form-control input-lg"
                               placeholder="station from">
                    </h1>
                </div>
                <div class="form-group">
                    <h1>
                        <label for="stationToName"> </label>
                        <input role="menuitem" id="stationToName" name="stationToName" type="search"
                               class="form-control input-lg" placeholder="station to">
                    </h1>
                </div>
                <div class="form-group">
                    <h1>
                        <label for="dateStr"> </label>
                        <input id="dateStr" name="dateStr" type="date" class="form-control input-lg">
                    </h1>
                </div>
                <button class="btn btn-lg btn-facebook-login" onclick="findScheduleButtonClick()">search</button>
            </div>
        </div>
    </div>
</div>

<!-- Train List -->
<div class="section">

    <%-- DOWNLOAD ANIMATION --%>
    <div class="container" style="text-align: center">
        <div>
            <img id="imgLoad" src="resources/img/bx_loader.gif"/>
        </div>
    </div>

    <%-- CONTAINER FOR TRAINS --%>
    <div id="foundTrains" class="container"></div>

</div>
<jsp:include page="components/footer.jsp"/>