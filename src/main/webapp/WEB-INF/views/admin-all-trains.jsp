<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp" flush="true"/>

<%-- TODO --%>

<!-- Page Title -->
<div class="section section-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>All Trains</h1>
            </div>
        </div>
    </div>
</div>

<!-- Train List -->
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
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
                            Route
                            <%--</div>--%>
                        </td>
                        <td>
                            Train number
                        </td>
                        <td>
                            Number of carriages
                        </td>
                        <td class="col-md-2"></td>
                    </tr>
                    <!-- КОНЕЦ ЗАГОЛОВКОВ -->

                    <c:forEach items="${trainList}" var="train">
                        <tr>
                            <td>
                                    ${train.id}
                            </td>
                            <td>
                                    ${train.route.name}
                            </td>
                            <td>
                                    ${train.number}
                            </td>
                            <td>
                                    ${train.numberOfCarriages}
                            </td>
                            <td class="actions">
                                <a href="<c:url value='edit-${train.number}-train' />" class="btn btn-xs btn-grey"><i
                                        class="glyphicon glyphicon-pencil"></i></a>
                                <a href="<c:url value='delete-${train.number}-train' />"
                                   class="btn btn-xs btn-grey"><i class="glyphicon glyphicon-trash"></i></a>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
            <div class="col-md2 col-md-offset-1">
                <form action="<c:url value='new-train' />">
                    <button type="submit" class="btn pull-right">Add new train</button>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" flush="true"/>