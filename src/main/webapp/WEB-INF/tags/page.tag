<%@ attribute name="pageTitle" %>
<%@ tag language="java" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<template:master pageTitle="${pageTitle}">
    <sec:authorize access="isAuthenticated()">
        <template:sidebar/>
    </sec:authorize>
    <jsp:doBody/>
</template:master>

