<%@ tag language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header style="background:  #ff9966; height: 50px">
    <div class="vertical-center">
        <a href="/" class="links-style">Home</a>
        <sec:authorize access="isAuthenticated()">

        <a href="/products" class="links-style">Products</a>

        <sec:authorize access="hasRole('MANAGER')">
        <a href="/employees" class="links-style">Employees</a>
        </sec:authorize>

        <a href="/customers" class="links-style">Customers</a>
        <a href="/check" class="links-style">Checks</a>
        <a href="/me" class="links-style">About Me</a>
        <a href="/logout" class="links-style">Log out </a>
        <h1 class="user-name"> User : <sec:authentication property="principal.employee.name"/></h1>
        <h1 class="user-name"> Role : <sec:authentication property="principal.employee.role"/></h1>

        </sec:authorize>

        <sec:authorize access="!isAuthenticated()">
            <a href="/login" class="links-style">Log in</a>
        </sec:authorize>
    </div>
</header>
