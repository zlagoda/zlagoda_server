<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:page pageTitle="Home">
  <div class="view-container view-container_flex_center">
    <div class="home-login home-login_flex_center">
      <h1 class="home-login__welcome">Welcome to ZLAGODA</h1>
      <sec:authorize access="!isAuthenticated()">
        <a class="home-login__button" href="/login">Log In</a>
      </sec:authorize>
    </div>
  </div>
</template:page>
