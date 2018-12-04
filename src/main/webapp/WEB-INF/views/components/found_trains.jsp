<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%-- FOUND TRAINS --%>
<template id="train">
    <div class="row">
        <div class="section-white col-md-12" style="margin-bottom: 15px; border-radius: 10px;">
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
                        <button id="economClassButton" class="btn btn-xs btn-block btn-grey" data-toggle="collapse"
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
        <div class="col-md-2">
            <button id="carriageButton" class="btn btn-grey btn-lg" data-toggle="collapse" data-target="#carriage_id"
                    style="margin: 5px;" onclick="findTicketsByCarriage(this.id)">
                <span>Carriage </span><span id="carrNumber"></span>
            </button>
        </div>

        <%-- CARRIAGE OPTION ICON --%>
        <div class="col-md-2" style="margin-top: 8px">
            <img src="../../../resources/img/wc.png" class="carriage-option"
                 alt="Bio toilet" title="Bio toilet"/>
            <img src="../../../resources/img/conditioner.png" class="carriage-option"
                 alt="Air conditioning works in summer" title="Air conditioning works in summer"/>
            <img src="../../../resources/img/linens.png" class="carriage-option"
                 alt="Bed linen" title="Bed linen"/>
        </div>

        <%-- CARRIAGE IMG --%>
        <div id="carriage_id" class="collapse row col-md-12" aria-expanded="false" style="margin: 20px">
            <div class="section-white">
                <div class="carriage-map">
                    <svg viewBox="0 0 2660 400">
                        <path id="place_1" class="place" d="m 556.15251,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_2" class="place" d="m 556.15251,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_3" class="place" d="m 670.27882,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_4" class="place" d="m 670.27882,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_5" class="place" d="m 732.86417,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_6" class="place" d="m 732.86417,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_7" class="place" d="m 845.23021,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_8" class="place" d="m 845.23021,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_9" class="place" d="m 907.81556,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_10" class="place" d="m 907.81556,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>

                        <path id="place_11" class="place" d="m 1021.5245,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_12" class="place" d="m 1021.5245,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_13" class="place" d="m 1084.1098,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_14" class="place" d="m 1084.1098,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_15" class="place" d="m 1196.4759,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_16" class="place" d="m 1196.4759,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_17" class="place" d="m 1259.0613,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_18" class="place" d="m 1259.0613,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_19" class="place" d="m 1371.5987,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_20" class="place" d="m 1371.5987,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>

                        <path id="place_21" class="place" d="m 1434.184,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_22" class="place" d="m 1434.184,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_23" class="place" d="m 1546.9343,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_24" class="place" d="m 1546.9343,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_25" class="place" d="m 1609.5197,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_26" class="place" d="m 1609.5197,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_27" class="place" d="m 1720.7275,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_28" class="place" d="m 1720.7275,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_29" class="place" d="m 1783.3129,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_30" class="place" d="m 1783.3129,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>

                        <path id="place_31" class="place" d="m 1895.8503,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_32" class="place" d="m 1895.8503,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_33" class="place" d="m 1958.4356,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_34" class="place" d="m 1958.4356,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_35" class="place" d="m 2071.1859,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_36" class="place" d="m 2071.1859,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_37" class="place" d="m 2133.7713,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_38" class="place" d="m 2133.7713,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_39" class="place" d="m 2247.4499,152.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                        <path id="place_40" class="place" d="m 2247.4499,58.4567 h 60 v 91 h -60 z"
                              fill="#008000"></path>
                    </svg>

                    <img class="carriage-img" src="../../../resources/img/carriage.png"/>

                </div>
            </div>

            <div class="section" style="text-align: center">
                <div class="text">
                    Selected place: <span id="selectedPlace"></span>
                </div>
            </div>
            <div class="section" style="text-align: center">
                <div class="btn-block">
                    <form:form action="buy-ticket-page" method="POST">
                        <div hidden>
                            <label for="hiddenTrainNumber">trainNumber</label>
                            <input id="hiddenTrainNumber" type="text" name="trainNumber">
                            <label for="hiddenCarriageNumber">carriageNumber</label>
                            <input id="hiddenCarriageNumber" type="text" name="carriageNumber">
                            <label for="hiddenSeatNumber">seatNumber</label>
                            <input id="hiddenSeatNumber" type="text" name="seatNumber">
                            <label for="hiddenRouteName">routeName</label>
                            <input id="hiddenRouteName" type="text" name="routeName">
                            <label for="hiddenStationFromName">stationFromName</label>
                            <input id="hiddenStationFromName" type="text" name="stationFromName">
                            <label for="hiddenStationToName">stationToName</label>
                            <input id="hiddenStationToName" type="text" name="stationToName">
                        </div>
                        <button id="buyTicket" class="btn btn-grey">
                            Buy ticket
                        </button>
                    </form:form>
                </div>
            </div>
        </div>

    </div>
</template>