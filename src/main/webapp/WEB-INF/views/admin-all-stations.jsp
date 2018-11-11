<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="components/header.jsp"/>
<jsp:include page="components/navbar.jsp" flush="true"/>

<!-- Page Title -->
<div class="section section-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>All Stations</h1>
            </div>
        </div>
    </div>
</div>

<!-- Stations List -->
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <table class="shopping-cart">
                    <!-- ЗАГОЛОВКИ ТАБЛИЦЫ -->
                    <tr>
                        <td>
                            <%--<div class="col-sm-4">--%>
                                ID
                            <%--</div>--%>
                        </td>
                        <td>
                            <%--<div class="col-md-5">--%>
                                    Station Name
                            <%--</div>--%>
                        </td>
                        <td class="col-md-2"></td>
                    </tr>
                    <!-- КОНЕЦ ЗАГОЛОВКОВ -->

                    <c:forEach items="${stationList}" var="station">
                        <tr>
                            <td>
                                <%--<div class="col-md-4">--%>
                                        ${station.id}
                                <%--</div>--%>
                            </td>
                            <td>
                                <%--<div class="col-md-4">--%>
                                        ${station.name}
                                <%--</div>--%>
                            </td>
                            <td class="actions">
                                <a href="<c:url value='edit-${station.name}-station' />" class="btn btn-xs btn-grey"><i
                                        class="glyphicon glyphicon-pencil"></i></a>
                                <a href="<c:url value='delete-${station.name}-station' />"
                                   class="btn btn-xs btn-grey"><i class="glyphicon glyphicon-trash"></i></a>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
            <div class="col-md2 col-md-offset-1">
                <form action="<c:url value='new-station' />">
                    <button type="submit" class="btn pull-right">Add new station</button>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="components/footer.jsp" flush="true"/>