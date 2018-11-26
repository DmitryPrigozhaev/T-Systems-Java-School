<%@ page contentType="text/html;charset=UTF-8" %>

<%-- FOUND TRAINS --%>
<template id="train">
    <div class="row">
        <div class="section-white col-md-12" style="margin-bottom: 15px">
            <div>
                <div class="col-md-10">
                    <div class="col-md-12" style="margin: 15px">
                        <span class="text-muted h4">train </span>
                        <b class="h3">№
                            <span id="number"></span>
                            «<span id="route"></span>»
                        </b>
                    </div>
                    <div class="col-md-12" style="margin-bottom: 15px">
                        <div class="col-md-4">
                            <span id="timeDeparture" class="h2 text-danger"></span>
                            <br>
                            <span id="dateDeparture" class="h4"></span>
                            <br>
                            <span id="stationDeparture" class="h2 text-primary"></span>
                        </div>
                        <div class="col-md-4">
                            <span><i class="glyphicon glyphicon-arrow-right"></i></span>
                            <span id="travelTimeH" class="h4"></span><span>h </span>
                            <span id="travelTimeM" class="h4"></span><span>min</span>
                            <span><i class="glyphicon glyphicon-arrow-right"></i></span>
                        </div>
                        <div class="col-md-4">
                            <span id="timeArrival" class="h2 text-danger"></span>
                            <br>
                            <span id="dateArrival" class="h4"></span>
                            <br>
                            <span id="stationArrival" class="h2 text-primary"></span>
                        </div>
                    </div>
                    <div class=""></div>
                </div>
                <div class="col-md-2">
                    <div class="col-md-12" style="margin: 15px">
                        <span class="text-muted h4">Route</span>
                    </div>
                    <div>
                        <button id="carButton" class="btn btn-xs btn-block btn-grey" data-toggle="collapse"
                                data-target="#target">
                            Econom class
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%-- CONTAINER FOR CARRIAGES --%>
    <div id="target" class="collapse row" aria-expanded="false"></div>

</template>

<%-- CARRIAGE TEMPLATE --%>
<template id="carriage">
    <div class="section-white col-md-12" style="margin-bottom: 15px">
        <button id="placeButton" class="btn btn-grey btn-lg" style="margin: 15px;">
            <span>Carriage </span><span id="carrNumber"></span>
        </button>
    </div>
</template>