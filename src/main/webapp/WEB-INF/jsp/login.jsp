<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:page pageTitle="Login">
    <div class="view-container view-container_flex_center">
        <div class="login-form-container"> 
            <form:form class="login-form login-form_flex_center" action="/login" method="post">
                <div class="login-form__input-block login-form__input-block_direction_column">
                <input class="login-form__input" type="text" name="username" placeholder="Username">
                <input class="login-form__input" type="password" name="password" placeholder="Password">
                <c:if test="${param.error != null}">
                    <p class="login-form__error">
                        Invalid login or password. Please try again.
                    </p>
                </c:if>
                </div>
                <div class="login-form__input-block login-form__input-block_direction_row">
                <input class="login-form__input button" type="submit" value="Log In">
                <a class="login-form__input button" href="/">
                    Go Back
                </a></div>
            </form:form>
    </div>
</template:page>