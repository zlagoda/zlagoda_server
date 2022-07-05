<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib
        prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template"
           tagdir="/WEB-INF/tags" %>

<template:page pageTitle="Check Info">
    <div class="container , text-style-products">
        <h2>Number : ${check.number}</h2>
        <h3>Employee : ${check.employee.id}</h3>
        <c:if test="${not empty check.card.number}">
        <h3>Customer ID : ${check.card.number}</h3>
        </c:if>
        <c:if test="${empty check.card.number}">
            <h3>Customer ID : NULL</h3>
        </c:if>
        <h3>Print Date : ${check.printDate}</h3>
        <h3>Total Sum : ${check.totalSum}</h3>
        <h3>VAT :${check.VAT}</h3>
        <a  href="/checks">
            <button class="go-back">Go back</button>
        </a>
    </div>

</template:page>
