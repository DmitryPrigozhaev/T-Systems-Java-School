<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>

<!-- Page Title -->
<div class="section section-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Register a new train</h1>
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
                        <div class="form-group">
                            <label for="routeId">Choose route</label>
                            <select class="form-control" id="routeId" name="routeId">
                                <c:forEach items="${routeList}" var="route">
                                    <option value="${route.id}"><c:out value="${route.name}"/></option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <input type="text" class="form-control" name="number" id="number" placeholder="number"/>
                        </div>

                        <div class="form-group">
                            <input type="text" class="form-control" name="numberOfCarriages" id="numberOfCarriages"
                                   placeholder="numberOfCarriages"/>
                        </div>

                        <%--<button type="submit" class="btn btn-primary">Add</button>--%>
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

<jsp:include page="footer.jsp"/>