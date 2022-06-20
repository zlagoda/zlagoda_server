<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header style="background: bisque; height: 50px">
    <style>
        .links-style {
            padding: 3px;
            border-radius: 5px;
            text-decoration: none;
            background: aliceblue;
            font: small-caps bold 24px/1 sans-serif;
            margin: 30px;
        }

        .user-name {
            padding: 3px;
            border-radius: 5px;
            text-decoration: none;
            background: aliceblue;
            font: small-caps bold 24px/1 sans-serif;
            margin: 30px 30px 30px -10px;
        }

        .vertical-center {
            display: flex;
            margin: 0;
            position: relative;
            top: 50%;
            -ms-transform: translateY(-50%);
            transform: translateY(-50%);
        }
    </style>
    <div class="vertical-center">
        <a href="/" class="links-style">Home</a>
        <a href="/categories" class="links-style">Categories</a>
        <a href="/cart" class="links-style">Cart</a>
        <sec:authorize access="!isAuthenticated()">
            <a href="/login" class="links-style">Log in</a>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <a href="/logout" class="links-style">Log out </a>
            <h1 class="user-name"> Logged-in User : <sec:authentication property="principal.customer.firstName"/></h1>
        </sec:authorize>
        <a class="links-style" href="/registration">Create Account</a>
    </div>
</header>