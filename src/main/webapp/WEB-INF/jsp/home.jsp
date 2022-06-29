<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:page pageTitle="Home">
  <div class="view-container view-container_flex_center">
    <div class="home-welcome home-welcome_flex_center">
      <h1 class="home-welcome__welcome">Welcome to ZLAGODA</h1>
      <sec:authorize access="!isAuthenticated()">
        <a class="button" href="/login">Log In</a>
      </sec:authorize>
    </div>
  </div>
</template:page>
