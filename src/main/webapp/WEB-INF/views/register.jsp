<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp" flush="true"/>

<!-- Page Title -->
<div class="section section-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Register</h1>
            </div>
        </div>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-7">
                <div class="basic-login">
                    <form:form method="POST" modelAttribute="user" class="form-horizontal" data-toggle="validator">
                        <%-- EMAIL --%>
                        <div class="form-group">
                            <form:label for="email" path="email" cssClass="col-sm3 control-label">Email</form:label>
                            <label for="Email" class="col-sm-3 control-label">Email</label>
                            <div class="col-sm-8">
                                <input type="email" class="form-control" id="Email" placeholder="Email">
                            </div>
                        </div>
                        <%-- PASSWORD --%>
                        <div class="form-group">
                            <label for="Password" class="col-sm-3 control-label">Password</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="Password" placeholder="Password">
                            </div>
                        </div>
                        <%-- TODO CONFIRM PASSWORD --%>
                        <%-- FIRST NAME --%>
                        <div class="form-group">
                            <label for="FirstName" class="col-sm-3 control-label">First Name</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="FirstName" placeholder="First Name">
                            </div>
                        </div>
                        <%-- LAST NAME --%>
                        <div class="form-group">
                            <label for="LastName" class="col-sm-3 control-label">Last Name</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="LastName" placeholder="Last Name">
                            </div>
                        </div>
                        <%-- DATE OF BIRTHDAY--%>
                        <div class="form-group">
                            <label for="BirthDate" class="col-sm-3 control-label">Birth Date</label>
                            <div class="col-sm-8">
                                <input type="date" class="form-control" id="BirthDate" placeholder="Birth Date">
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
            <div class="col-md-4 col-md-offset-1">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i>I agree with the following:</i></h3>
                    </div>
                    <div class="panel-body">
                        <ul>
                            <li>registration data is provided by me voluntarily;<br><br></li>
                            <li>for the purposes of additional protection against fraudulent
                                actions, the registration data indicated by me may be transferred
                                to the bank engaged in payment transactions.</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" flush="true"/>