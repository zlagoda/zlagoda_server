<%@ attribute name="pageTitle" %>
<%@ tag language="java" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:master pageTitle="${pageTitle}">
    <template:header/>
    <jsp:doBody/>
    <template:footer/>
</template:master>

