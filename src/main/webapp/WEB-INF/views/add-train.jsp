<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="components/header.jsp"/>
<jsp:include page="components/navbar.jsp"/>

<!-- Page Title -->
<div class="section section-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <c:choose>
                    <c:when test="${edit}">
                        <h1>Edit train</h1>
                    </c:when>
                    <c:otherwise>
                        <h1>Register a new train</h1>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <table class="shopping-cart">
                    <form:form name="add-new-train" action="" method="post">
                        <%-- TRAIN NUMBER--%>
                        <div class="form-group">
                            <c:choose>
                                <c:when test="${edit}">
                                    <label for="number">Number of train</label>
                                    <input type="text" class="form-control" name="number" id="number"
                                           placeholder="${train.number}" disabled/>
                                </c:when>
                                <c:otherwise>
                                    <label for="number">Set train number</label>
                                    <input type="text" class="form-control" name="number" id="number"
                                           placeholder="number"/>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <%-- ROUTE --%>
                        <div class="form-group">
                            <label for="routeId">Choose route</label>
                            <select class="form-control" id="routeId" name="routeId">
                                <c:forEach items="${routeList}" var="route">
                                    <option value="${route.id}"><c:out value="${route.name}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <%-- NUMBER OF CARRIAGES --%>
                        <div class="form-group">
                            <label for="numberOfCarriages">Set number of carriages</label>
                            <input type="text" class="form-control" name="numberOfCarriages" id="numberOfCarriages"
                                   placeholder="numberOfCarriages"/>
                        </div>
                        <%-- REGISTER / UPDATE --%>
                        <c:choose>
                            <c:when test="${edit}">
                                <input type="submit" value="Update"/>
                            </c:when>
                            <c:otherwise>
                                <input type="submit" value="Register"/>
                            </c:otherwise>
                        </c:choose>
                    </form:form>
                </table>

            </div>
            <div class="col-md2 col-md-offset-1">
                <form action="<c:url value='admin-all-trains' />">
                    <button type="submit" class="btn pull-right">All trains</button>
                </form>
            </div>
        </div>
    </div>
</div>

<br/>
<br/>

<jsp:include page="components/footer.jsp"/>