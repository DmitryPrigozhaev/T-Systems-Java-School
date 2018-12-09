<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="components/header.jsp"/>
<jsp:include page="components/navbar.jsp" flush="true"/>

<!-- Page Title -->
<div class="section section-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>${userDto.firstName} ${userDto.lastName}</h1>
            </div>
        </div>
    </div>
</div>
<!-- End Page Title -->

<div class="container bootstrap snippet">

    <div class="row">
        <div class="col-sm-3">

            <div class="text-center">
                <img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png" class="avatar img-circle img-thumbnail"
                     alt="avatar">
                <h6>Upload a different photo...</h6>
                <input type="file" class="text-center center-block file-upload">
            </div>

            <br>

            <%--<div class="panel panel-default">--%>
            <%--<div class="panel-heading">Website <i class="fa fa-link fa-1x"></i></div>--%>
            <%--<div class="panel-body"><a href="http://bootnipets.com">bootnipets.com</a></div>--%>
            <%--</div>--%>

            <%--<ul class="list-group">--%>
            <%--<li class="list-group-item text-muted">Activity <i class="fa fa-dashboard fa-1x"></i></li>--%>
            <%--<li class="list-group-item text-right"><span class="pull-left"><strong>Shares</strong></span> 125</li>--%>
            <%--<li class="list-group-item text-right"><span class="pull-left"><strong>Likes</strong></span> 13</li>--%>
            <%--<li class="list-group-item text-right"><span class="pull-left"><strong>Posts</strong></span> 37</li>--%>
            <%--<li class="list-group-item text-right"><span class="pull-left"><strong>Followers</strong></span> 78</li>--%>
            <%--</ul>--%>

            <%--<div class="panel panel-default">--%>
            <%--<div class="panel-heading">Social Media</div>--%>
            <%--<div class="panel-body">--%>
            <%--<i class="fa fa-facebook fa-2x"></i> <i class="fa fa-github fa-2x"></i> <i class="fa fa-twitter fa-2x"></i> <i class="fa fa-pinterest fa-2x"></i> <i class="fa fa-google-plus fa-2x"></i>--%>
            <%--</div>--%>
            <%--</div>--%>

        </div>
        <div class="col-sm-9">
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#home">Info</a></li>
                <li><a data-toggle="tab" href="#tickets">Tickets</a></li>
            </ul>

            <div class="tab-content">
                <div class="col-md-10 tab-pane active" id="home">
                    <hr>
                    <ul class="list-group">
                        <li class="list-group-item text-muted">Profile <i class="fa fa-dashboard fa-1x"></i></li>
                        <li class="list-group-item text-right"><span
                                class="pull-left"><strong>Email</strong></span> ${userDto.email}</li>
                        <li class="list-group-item text-right"><span
                                class="pull-left"><strong>First name</strong></span> ${userDto.firstName}</li>
                        <li class="list-group-item text-right"><span
                                class="pull-left"><strong>Last name</strong></span> ${userDto.lastName}</li>
                        <li class="list-group-item text-right"><span
                                class="pull-left"><strong>Birth date</strong></span> ${userDto.birthDate}</li>
                    </ul>
                    <hr>
                </div>

                <div class="tab-pane" id="tickets">

                    <hr>
                    <c:forEach items="${ticketDtoList}" var="ticketDto">
                        <div class="section-white col-md-12" style="margin-bottom: 15px; border-radius: 10px;">
                            <div>
                                <div class="col-md-12">
                                    <div class="col-md-4 h3"><b>Train </b></div>
                                    <div class="col-md-4 h3"><b>Departure</b></div>
                                    <div class="col-md-4 h3" style="border-right-width: medium; border-color: #848484"><b>Arrival </b></div>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="col-md-12">
                                    <div class="col-md-4">
                                        <span>«${ticketDto.routeName}»</span><br>
                                        <span>train </span><b>№<span>${ticketDto.trainNumber}</span></b><br>
                                        <span>carriage </span><b>№<span>${ticketDto.carriageNumber}</span></b><br>
                                        <span>seat </span><b>№<span>${ticketDto.seatNumber}</span></b><br>
                                    </div>
                                    <div class="col-md-4">
                                        <span>station </span><b><span>${ticketDto.stationFromName}</span></b><br>
                                        <span>date </span><b><span>${ticketDto.datetimeDeparture.substring(0, 10)}</span></b><br>
                                        <span>time </span><b><span>${ticketDto.datetimeDeparture.substring(11, 16)}</span></b><br>
                                    </div>
                                    <div class="col-md-4">
                                        <span>station </span><b><span>${ticketDto.stationToName}</span></b><br>
                                        <span>date </span><b><span>${ticketDto.datetimeArrival.substring(0, 10)}</span></b><br>
                                        <span>time </span><b><span>${ticketDto.datetimeArrival.substring(11, 16)}</span></b><br>
                                    </div>
                                    <div class="col-md-12" style="margin-top: 20px">
                                        <span class="h4 pull-right">ticket price: ${ticketDto.price}$</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="components/footer.jsp" flush="true"/>