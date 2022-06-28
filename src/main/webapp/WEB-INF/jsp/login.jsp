<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:page pageTitle="Login">
    <div class="login">
        <form:form
                action="/login"
                method="post">
            <div class="label-input">
                <label>Enter User Name : </label>
                <input class="input-login" type="text" name="username" style="padding: 5px">
            </div>
            <div class="label-input">
                <label>Enter Password : </label>
                <input class="input-login" type="password" name="password" style="padding: 5px">
            </div>
            <c:if test="${param.error != null}">
                <p style="background: indianred;border-radius: 5px; color: white">Invalid login or password. Please
                    try
                    again.</p>
            </c:if>
            <input class="login-btn" type="submit" value="Login">
        </form:form>
        <br>
        <div>
            <a href="/" style="text-decoration: none;">
                <button class="go-back">Go Back</button>
            </a>
            <a href="/registration">
                <button class="reg-btn">Create Account</button>
            </a>
        </div>
    </div>
</template:page>