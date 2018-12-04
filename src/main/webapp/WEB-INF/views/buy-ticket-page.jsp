<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
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


<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-7">
                <div class="basic-login">
                    <form:form method="POST" class="form-horizontal" action="buy-ticket">
                        <%-- EMAIL --%>
                        <div class="form-group">
                            <label for="email" class="col-sm-4 control-label">Email</label>
                            <div class="col-sm-8">
                                <input type="email" name="userEmail" class="form-control" id="email"
                                       placeholder="Email" value="${userDto.email}">
                            </div>
                        </div>
                        <%-- FIRST NAME --%>
                        <div class="form-group">
                            <label for="firstName" class="col-sm-4 control-label">First Name</label>
                            <div class="col-sm-8">
                                <input type="text" name="firstName" class="form-control" id="firstName"
                                       placeholder="First Name" value="${userDto.firstName}">
                            </div>
                        </div>
                        <%-- LAST NAME --%>
                        <div class="form-group">
                            <label for="lastName" class="col-sm-4 control-label">Last Name</label>
                            <div class="col-sm-8">
                                <input type="text" name="lastName" class="form-control" id="lastName"
                                       placeholder="Last Name" value="${userDto.lastName}">
                            </div>
                        </div>
                        <%-- DATE OF BIRTHDAY--%>
                        <div class="form-group">
                            <label for="birthDate" class="col-sm-4 control-label">Birth Date</label>
                            <div class="col-sm-8">
                                <input type="date" name="birthDate" class="form-control" id="birthDate"
                                       placeholder="Birth Date" value="${userDto.birthDate}">
                            </div>
                        </div>
                        <%-- ROUTE NAME --%>
                        <div class="form-group">
                            <label for="routeName" class="col-sm-4 control-label">Route name</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="routeName" name="routeName"
                                       value="${ticketDto.routeName}">
                            </div>
                        </div>
                        <%-- TRAIN NUMBER --%>
                        <div class="form-group">
                            <label for="trainNumber" class="col-sm-4 control-label">Train number</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="trainNumber" name="trainNumber"
                                       value="${ticketDto.trainNumber}">
                            </div>
                        </div>
                        <%-- CARRIAGE NUMBER --%>
                        <div class="form-group">
                            <label for="carriageNumber" class="col-sm-4 control-label">Carriage number</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="carriageNumber" name="carriageNumber"
                                       value="${ticketDto.carriageNumber}">
                            </div>
                        </div>
                        <%-- SEAT NUMBER --%>
                        <div class="form-group">
                            <label for="seatNumber" class="col-sm-4 control-label">Seat number</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="seatNumber" name="seatNumber"
                                       value="${ticketDto.seatNumber}">
                            </div>
                        </div>
                        <%-- STATION DEPARTURE --%>
                        <div class="form-group">
                            <label for="stationFromName" class="col-sm-4 control-label">Station departure</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="stationFromName" name="stationFromName"
                                       value="${ticketDto.stationFromName}">
                            </div>
                        </div>
                        <%-- STATION ARRIVAL --%>
                        <div class="form-group">
                            <label for="stationToName" class="col-sm-4 control-label">Station arrival</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="stationToName" name="stationToName"
                                       value="${ticketDto.stationToName}">
                            </div>
                        </div>

                        <%-- BUTTON --%>
                        <div class="form-group">
                            <button type="submit" class="btn pull-right success" style="margin-right: 25px">
                                Buy ticket
                            </button>
                            <div class="clearfix"></div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="components/footer.jsp"/>