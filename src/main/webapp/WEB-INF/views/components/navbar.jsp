<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>

<!-- Navigation & Logo-->

<div class="mainmenu-wrapper">
    <div class="container">
        <div class="menuextras">
            <div class="extras">
                <ul>
                    <%-- SHOPPING CART --%>
                    <li class="shopping-cart-items"><i class="glyphicon glyphicon-shopping-cart icon-white"></i>
                        <a href="shopping-cart"><b>3 items</b></a>
                    </li>
                    <%-- LANGUAGES --%>
                    <li>
                        <div class="dropdown choose-country">
                            <a class="#" data-toggle="dropdown" href="#">
                                <img src="../../../resources/img/flags/gb.png" alt="Great Britain"> UK</a>
                            <ul class="dropdown-menu" role="menu">
                                <li role="menuitem"><a href="#"><img src="../../../resources/img/flags/ru.png"
                                                                     alt="Russia"> RU</a></li>
                                <li role="menuitem"><a href="#"><img src="../../../resources/img/flags/de.png"
                                                                     alt="Germany"> DE</a></li>
                            </ul>
                        </div>
                    </li>
                    <%-- LOGIN / LOGOUT --%>
                    <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')">
                        <li><a href="/logout">Logout</a></li>
                    </sec:authorize>
                    <sec:authorize access="!(hasRole('ROLE_ADMIN') || hasRole('ROLE_MANAGER') || hasRole('ROLE_USER'))">
                        <li><a href="login">Login</a></li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
        <nav id="mainmenu" class="mainmenu">
            <ul>
                <%-- LOGO --%>
                <li class="logo-wrapper">
                    <a href="index"><img src="../../../resources/img/railwaycompany-logo.png"
                                         alt="Information system of a railway company"></a></li>
                <%-- HOME --%>
                <li class="active">
                    <a href="index">Home</a>
                </li>
                <%-- SCHEDULE --%>
                <li>
                    <a href="schedule">Schedule</a>
                </li>
                <%-- TICKETS --%>
                <li>
                    <a href="tickets">Tickets</a>
                </li>
                <%-- ACCOUNT --%>
                <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')">
                    <li>
                        <a href="account">Account</a>
                    </li>
                </sec:authorize>
                <%-- ADMIN PANEL --%>
                <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')">
                    <li class="has-submenu">
                        <a href="#">Manager Panel +</a>
                        <div class="mainmenu-submenu">
                            <div class="mainmenu-submenu-inner">
                                <div>
                                    <h4>Trains</h4>
                                    <ul>
                                        <li><a href="admin-all-trains">All trains</a></li>
                                    </ul>
                                    <h4>Stations</h4>
                                    <ul>
                                        <li><a href="admin-all-stations">All stations</a></li>
                                    </ul>
                                    <h4>Routes</h4>
                                    <ul>
                                        <li><a href="admin-all-routes">All routes</a></li>
                                    </ul>
                                    <h4>Passengers</h4>
                                    <ul>
                                        <li><a href="admin-all-passengers">All passengers</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </li>
                </sec:authorize>
            </ul>
        </nav>
    </div>
</div>