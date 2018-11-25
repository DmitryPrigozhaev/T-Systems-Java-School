<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="components/header.jsp"/>
<jsp:include page="components/navbar.jsp" flush="true"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<script src="https://code.jquery.com/jquery-3.3.1.js"
        integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>
<script src="../../resources/js/AjaxFindTrain.js"></script>

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
                <%--<form class="form-inline">--%>
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
                <%--</form>--%>
            </div>
        </div>
    </div>
</div>

<!-- Train List -->
<div class="section">
    <div id="foundTrains" class="container">

        <%-- DOWNLOAD ANIMATION --%>
        <div class="col-md-offset-5">
            <img src="../../resources/img/bx_loader.gif" id="imgLoad"/>
        </div>

        <%-- FOUND TRAINS --%>
        <template class="row">
            <div class="section-white col-md-12" style="margin-bottom: 15px">
                <div>
                    <div class="col-md-10">
                        <div class="col-md-12" style="margin: 15px">
                            <span class="text-muted h4">train </span>
                            <b class="h3">№
                                <span>number</span>
                                «<span>сообщение</span>»
                            </b>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 15px">
                            <div class="col-md-4">
                                <span class="h2 text-danger">20:00</span>
                                <br>
                                <span class="h4">12.12.2018</span>
                                <br>
                                <span class="h2 text-primary">Tver</span>
                            </div>
                            <div class="col-md-4">
                                <span><i class="glyphicon glyphicon-arrow-right"></i></span>
                                <span class="h4">6h 30min</span>
                                <span><i class="glyphicon glyphicon-arrow-right"></i></span>
                            </div>
                            <div class="col-md-4">
                                <span class="h2 text-danger">23:40</span>
                                <br>
                                <span class="h4">12.12.2018</span>
                                <br>
                                <span class="h2 text-primary">Saint-Petersburg</span>
                            </div>
                        </div>
                        <div class=""></div>
                    </div>
                    <div class="col-md-2">
                        <div class="col-md-12" style="margin: 15px">
                            <span class="text-muted h4">Route</span>
                        </div>
                        <div>
                            <button class="btn btn-xs btn-block btn-grey" data-toggle="collapse" data-target="#target">
                                Эконом-класс
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </template>

        <div id="target" class="collapse row" aria-expanded="false">
            <div class="section-white col-md-12" style="margin-bottom: 15px">
                <button class="btn btn-grey btn-lg" style="margin: 15px;">
                    Вагон 1
                </button>
            </div>
        </div>

    </div>
</div>
<jsp:include page="components/footer.jsp"/>