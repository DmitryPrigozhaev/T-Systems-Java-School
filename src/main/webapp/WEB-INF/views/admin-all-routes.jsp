<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="components/header.jsp"/>
<jsp:include page="components/navbar.jsp" flush="true"/>

<!-- Page Title -->
<div class="section section-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>All Routes</h1>
            </div>
        </div>
    </div>
</div>

<!-- Routes List -->
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-9 col-md-offset-1">
                <table class="shopping-cart">

                    <!-- Table Headers -->
                    <tr>
                        <td class="col-md-1">ID</td>
                        <td class="col-md-6">Name</td>
                        <td class="col-md-1"></td>
                    </tr>
                    <!-- End Table Headers -->

                    <c:forEach items="${routeDtoList}" var="route">
                        <tr>
                            <td class="col-md-1">
                                    ${route.id}
                            </td>
                            <td class="col-md-6">
                                <button class="btn btn-xs btn-block" data-toggle="collapse" data-target="#${route.id}">
                                        ${route.name}
                                </button>
                                <div id="${route.id}" class="collapse">
                                    <table class="tab-content">
                                        <tr>
                                            <td class="col-md-1">id</td>
                                            <td class="col-md-2">station name</td>
                                            <td class="col-md-2">date arrival</td>
                                            <td class="col-md-2">date departure</td>
                                        </tr>
                                        <c:forEach items="${route.routePointDtoList}" var="routePoint">
                                            <tr>
                                                <td>${routePoint.id}</td>
                                                <td>${routePoint.stationName}</td>
                                                <td>${routePoint.dateArrival}</td>
                                                <td>${routePoint.dateDeparture}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>

                            <td class="actions col-md-1">
                                <a href="<c:url value='edit-${route.name}-route' />" class="btn btn-xs btn-grey"><i
                                        class="glyphicon glyphicon-pencil"></i></a>
                                <a href="<c:url value='delete-${route.name}-route' />"
                                   class="btn btn-xs btn-grey"><i class="glyphicon glyphicon-trash"></i></a>
                            </td>
                        </tr>


                    </c:forEach>

                </table>
            </div>
            <div class="col-md2 col-md-offset-1">
                <form action="<c:url value='new-route' />">
                    <button type="submit" class="btn pull-right">Add new route</button>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="components/footer.jsp" flush="true"/>
