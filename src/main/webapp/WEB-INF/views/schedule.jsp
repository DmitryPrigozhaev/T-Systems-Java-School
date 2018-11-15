<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="components/header.jsp"/>
<jsp:include page="components/navbar.jsp" flush="true"/>

<!-- Page Title -->
<div class="section section-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Schedule</h1>
            </div>
        </div>
    </div>
</div>

<%-- Search Schedule--%>
<jsp:include page="components/search_train.jsp"/>
<%-- End of Search Schedule--%>

<jsp:include page="components/footer.jsp"/>