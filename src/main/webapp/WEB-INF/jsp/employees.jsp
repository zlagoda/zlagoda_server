<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:page pageTitle="Employees">
    <div class="links-style , product-container">
        <c:forEach items="${employees}" var="employee">
            <div class="employee">
                <h2>${employee.name} ${employee.role}</h2>
                <a style="text-decoration: none ; background:  #c3d5d5; border-radius: 3px" href="/employee/${employee.id}">Edit/Add Info</a>
            </div>
        </c:forEach>

        <c:if test="${empty employees}"><p>Seems like there are no employee records.</p></c:if>

        <a style="display: block;" href="/">
            <button class="go-back">Go back</button>
        </a>
    </div>
</template:page>