<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
</head>
<body style="margin: 0; padding: 0;">
<style>
    .login {
        width: 50%;
        text-align: center;
        background: cornflowerblue;
        border-radius: 5px;
        padding: 30px;
        position: absolute;
        top: 30%;
        left: 50%;
        font-family: "Trebuchet MS", Helvetica, sans-serif;
        font-size: 30px;
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -30%);
    }

    .login-btn {
        padding: 8px;
        border: none;
        border-bottom: 1px solid #ccc;
        width: 20%;
    }

    .label-input {
        display: flex;
        margin: 10px;
    }

    .input {
        margin-left: 45px;
        padding: 8px;
        display: block;
        border: none;
        border-bottom: 1px solid #ccc;
        width: 20%;
    }

    .go-back {
        display: inline-block;
        background-color: #f4511e;
        border: none;
        color: white;
        padding: 16px 32px;
        text-align: center;
        font-size: 16px;
        margin: 4px 2px;
        opacity: 0.6;
        transition: 0.3s;
        text-decoration: none;
        cursor: pointer;
    }

    .go-back:hover {
        opacity: 1
    }
</style>
<jsp:include page="header/main_header.jsp"/>
<div class="login">
    <form:form
            action="/login"
            method="post">
        <div class="label-input">
            <label>Enter User Name</label>
            <input class="input" type="text" name="username" style="padding: 5px">
        </div>
        <div class="label-input">
            <label>Enter Password</label>
            <input class="input" type="password" name="password" style="padding: 5px">
        </div>
        <c:if test="${param.error != null}">
            <p style="background: indianred;border-radius: 5px; color: white">Invalid login or password. Please try
                again.</p>
        </c:if>
        <input class="login-btn" type="submit" value="Login">
    </form:form>
    <br>
    <a style="display: block" href="/">
        <button class="go-back">Go Back</button>
    </a>
    <label> Want to create account ?</label>
    <a style="display: block" href="/registration">
        <button class="login-btn">Create Account</button>
    </a>
</div>
</body>
</html>