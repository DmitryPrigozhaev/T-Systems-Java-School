<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="components/header.jsp"/>
<jsp:include page="components/navbar.jsp" flush="true"/>

<!-- Page Title -->
<div class="section section-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Registration</h1>
            </div>
        </div>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-7">
                <div class="basic-login">
                    <form:form method="POST" modelAttribute="user" role="form" class="form-horizontal">
                        <%-- EMAIL --%>
                        <div class="form-group">
                            <label for="email" class="col-sm-4 control-label">Email</label>
                            <div class="col-sm-8">
                                <input type="email" name="email" class="form-control" id="email" placeholder="Email">
                            </div>
                        </div>
                        <%-- PASSWORD --%>
                        <div class="form-group">
                            <label for="password" class="col-sm-4 control-label">Password</label>
                            <div class="col-sm-8">
                                <input type="password" name="password" class="form-control" id="password" placeholder="Password">
                            </div>
                        </div>
                        <%-- CONFIRM PASSWORD --%>
                        <div class="form-group">
                            <label for="confirmPassword" class="col-sm-4 control-label">Confirm Password</label>
                            <div class="col-sm-8">
                                <input type="password" name="confirmPassword" class="form-control" id="confirmPassword"
                                       placeholder="Confirm Password">
                            </div>
                        </div>
                        <%-- FIRST NAME --%>
                        <div class="form-group">
                            <label for="firstName" class="col-sm-4 control-label">First Name</label>
                            <div class="col-sm-8">
                                <input type="text" name="firstName" class="form-control" id="firstName" placeholder="First Name">
                            </div>
                        </div>
                        <%-- LAST NAME --%>
                        <div class="form-group">
                            <label for="lastName" class="col-sm-4 control-label">Last Name</label>
                            <div class="col-sm-8">
                                <input type="text" name="lastName" class="form-control" id="lastName" placeholder="Last Name">
                            </div>
                        </div>
                        <%-- DATE OF BIRTHDAY--%>
                        <div class="form-group">
                            <label for="birthDate" class="col-sm-4 control-label">Birth Date</label>
                            <div class="col-sm-8">
                                <input type="date" name="birthDate" class="form-control" id="birthDate" placeholder="Birth Date">
                            </div>
                        </div>
                        <%-- BUTTON --%>
                        <div class="form-group">
                            <button type="submit" class="btn pull-right success" style="margin-right: 25px">Register</button>
                            <div class="clearfix"></div>
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
                            <li>registration data is provided by me voluntarily<br><br></li>
                            <li>for the purposes of additional protection against fraudulent
                                actions, the registration data indicated by me may be transferred
                                to the bank engaged in payment transactions
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="components/footer.jsp" flush="true"/>