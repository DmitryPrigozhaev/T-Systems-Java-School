<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="components/header.jsp"/>
<jsp:include page="components/navbar.jsp" flush="true"/>

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

                    <c:choose>
                        <c:when test="${error}">
                            <div class="panel panel-danger">
                                <div class="panel-body">
                                    <h3 class="panel-title">Invalid email or password</h3>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>

                    <form id="loginForm" method="post" action='login'>
                        <div class="form-group has-feedback">
                            <label for="email"><i class="icon-user"></i> <b>Email</b></label>
                            <input class="form-control" name="email" id="email" type="text" placeholder="">
                        </div>
                        <div class="form-group">
                            <label for="password"><i class="icon-lock"></i> <b>Password</b></label>
                            <input class="form-control" name="password" id="password" type="password" placeholder="">
                        </div>
                        <div class="form-group">
                            <label class="checkbox">
                                <input type="checkbox"> Remember me
                            </label>
                            <a href="page-password-reset.html" class="forgot-password">Forgot password?</a>
                            <button type="submit" class="btn pull-right">Login</button>
                            <div class="clearfix"></div>
                        </div>
                    </form>

                </div>
            </div>
        </div>

        <div class="col-md-4 social-login col-md-offset-4">
            <%--<div class="clearfix"></div>--%>
            <div class="not-member">
                <p>Not a member? <a href="registration">Register here</a></p>
            </div>
        </div>
    </div>
</div>

<jsp:include page="components/footer.jsp" flush="true"/>