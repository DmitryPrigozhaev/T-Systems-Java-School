<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="components/header.jsp"/>
<jsp:include page="components/navbar.jsp"/>

<!-- Page Title -->
<div class="section section-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Buy ticket</h1>
            </div>
        </div>
    </div>
</div>

<%-- INFORMATION PANEL --%>
<div class="section">
    <div class="container">
        <div class="row">
            <c:choose>
                <c:when test="${error}">
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i>Cannot buy ticket: </i></h3>
                        </div>
                        <div class="panel-body">
                            <h4 style="margin: 10px">${message}</h4>
                            <button class="btn pull-right success" href="find_train" style="margin-right: 25px">
                                BACK
                            </button>
                        </div>
                    </div>
                </c:when>
                <c:when test="${success}">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i>Congratulations!</i></h3>
                        </div>
                        <div class="panel-body">
                            <h4 style="margin: 10px">${message}</h4>
                            <button class="btn pull-right success" href="account" style="margin-right: 25px">
                                OK
                            </button>
                        </div>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">

            <%-- TICKET BODY --%>
            <div class="section-white col-md-12" style="margin-bottom: 15px; border-radius: 10px;">
                <div>
                    <div class="col-md-1" style="margin-top: 10px">
                        <img src="resources/img/train-icon.png" style="width: 50px; height: 45px"/>
                    </div>
                    <div class="col-md-8" style="margin-top: 20px">
                        <span class="h2 text-muted">Some Railway Company</span>
                    </div>
                    <div class="col-md-2" style="margin-top: 20px">
                        <span class="h2">${ticketDto.price}$</span>
                    </div>
                    <div class="col-md-12">
                        <hr>
                    </div>
                    <div class="col-md-12">
                        <div class="col-md-3 h2" style="border-right: #848484"><b>Passenger</b></div>
                        <div class="col-md-3 h2" style="border-right: #848484"><b>Train </b></div>
                        <div class="col-md-3 h2" style="border-right: #848484"><b>Departure</b></div>
                        <div class="col-md-3 h2" style="border-right: #848484"><b>Arrival </b></div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="col-md-12">
                        <div class="col-md-3">
                            <span>${userDto.firstName}</span><br>
                            <span>${userDto.lastName}</span><br>
                            <span>${userDto.birthDate}</span><br>
                        </div>
                        <div class="col-md-3">
                            <span>«${ticketDto.routeName}»</span><br>
                            <span>train </span><b>№<span>${ticketDto.trainNumber}</span></b><br>
                            <span>carriage </span><b>№<span>${ticketDto.carriageNumber}</span></b><br>
                            <span>seat </span><b>№<span>${ticketDto.seatNumber}</span></b><br>
                        </div>
                        <div class="col-md-3">
                            <span>station </span><b><span>${ticketDto.stationFromName}</span></b><br>
                            <span>date </span><b><span>${ticketDto.datetimeDeparture.substring(0, 10)}</span></b><br>
                            <span>time </span><b><span>${ticketDto.datetimeDeparture.substring(11, 16)}</span></b><br>
                        </div>
                        <div class="col-md-3">
                            <span>station </span><b><span>${ticketDto.stationToName}</span></b><br>
                            <span>date </span><b><span>${ticketDto.datetimeArrival.substring(0, 10)}</span></b><br>
                            <span>time </span><b><span>${ticketDto.datetimeArrival.substring(11, 16)}</span></b><br>
                        </div>
                        <div class="col-md-12">
                            <hr>
                        </div>
                    </div>
                </div>
            </div>

            <%-- BUTTON --%>
            <form:form method="POST" class="form-horizontal" action="buy-ticket">
                <div hidden>
                    <label for="userEmail" class="col-sm-4 control-label">Email</label>
                    <input id="userEmail" type="email" name="userEmail" class="form-control"
                           value="${userDto.email}">
                    <label for="firstName" class="col-sm-4 control-label">First Name</label>
                    <input id="firstName" type="text" name="firstName" class="form-control"
                           value="${userDto.firstName}">
                    <label for="lastName" class="col-sm-4 control-label">Last Name</label>
                    <input id="lastName" type="text" name="lastName" class="form-control"
                           value="${userDto.lastName}">
                    <label for="birthDate" class="col-sm-4 control-label">Birth Date</label>
                    <input id="birthDate" type="date" name="birthDate" class="form-control"
                           value="${userDto.birthDate}">
                    <label for="routeName" class="col-sm-4 control-label">Route name</label>
                    <input id="routeName" type="text" class="form-control" name="routeName"
                           value="${ticketDto.routeName}">
                    <label for="trainNumber" class="col-sm-4 control-label">Train number</label>
                    <input id="trainNumber" type="text" class="form-control" name="trainNumber"
                           value="${ticketDto.trainNumber}">
                    <label for="carriageNumber" class="col-sm-4 control-label">Carriage number</label>
                    <input id="carriageNumber" type="text" class="form-control" name="carriageNumber"
                           value="${ticketDto.carriageNumber}">
                    <label for="seatNumber" class="col-sm-4 control-label">Seat number</label>
                    <input id="seatNumber" type="text" class="form-control" name="seatNumber"
                           value="${ticketDto.seatNumber}">
                    <label for="stationFromName" class="col-sm-4 control-label">Station departure</label>
                    <input id="stationFromName" type="text" class="form-control" name="stationFromName"
                           value="${ticketDto.stationFromName}">
                    <label for="stationToName" class="col-sm-4 control-label">Station arrival</label>
                    <input id="stationToName" type="text" class="form-control" name="stationToName"
                           value="${ticketDto.stationToName}">
                    <label for="datetimeDeparture" class="col-sm-4 control-label">Datetime departure</label>
                    <input id="datetimeDeparture" type="text" class="form-control" name="datetimeDeparture"
                           value="${ticketDto.datetimeDeparture}">
                    <label for="datetimeArrival" class="col-sm-4 control-label">Datetime arrival</label>
                    <input id="datetimeArrival" type="text" class="form-control" name="datetimeArrival"
                           value="${ticketDto.datetimeArrival}">
                </div>
                <div class="form-group">
                    <button class="btn pull-right success" style="margin-right: 25px">
                        Buy ticket
                    </button>
                    <div class="clearfix"></div>
                </div>
            </form:form>

        </div>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>