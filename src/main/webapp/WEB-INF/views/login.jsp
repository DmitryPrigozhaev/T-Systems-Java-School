<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp" flush="true"/>

<!-- Page Title -->
<div class="section section-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Login</h1>
            </div>
        </div>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="basic-login">
                    <%-- ОБЫЧНАЯ ФОРМА --%>
                    <%--<form role="form">--%>
                        <%--<div class="form-group">--%>
                            <%--<label for="login-username"><i class="icon-user"></i> <b>Username or Email</b></label>--%>
                            <%--<input class="form-control" id="login-username" type="text" placeholder="">--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                            <%--<label for="login-password"><i class="icon-lock"></i> <b>Password</b></label>--%>
                            <%--<input class="form-control" id="login-password" type="password" placeholder="">--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="checkbox">--%>
                                <%--<input type="checkbox"> Remember me--%>
                            <%--</label>--%>
                            <%--<a href="page-password-reset.html" class="forgot-password">Forgot password?</a>--%>
                            <%--<button type="submit" class="btn pull-right">Login</button>--%>
                            <%--<div class="clearfix"></div>--%>
                        <%--</div>--%>
                    <%--</form>--%>
                        <form:form id="loginForm" method="post" action="login" modelAttribute="loginBean">
                            <form:label path="username">Enter your user-name</form:label>
                            <form:input id="username" name="username" path="username" /><br>
                            <form:label path="username">Please enter your password</form:label>
                            <form:password id="password" name="password" path="password" /><br>
                            <input type="submit" value="Submit" />
                        </form:form>

                </div>
            </div>
        </div>

        <div class="col-md-4 social-login col-md-offset-4">
            <%--<div class="clearfix"></div>--%>
            <div class="not-member">
                <p>Not a member? <a href="register">Register here</a></p>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" flush="true"/>