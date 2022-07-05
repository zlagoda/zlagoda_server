<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib
        prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"
           uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template"
           tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<template:page pageTitle="Customer Cards">
    <h6 class="print-header">Customer Cards</h6>
    <sec:authorize access="hasRole('MANAGER')">
        <button class="hidden-print" onclick="print()">Print</button>
    </sec:authorize>
    <div>
        <table class="table">
            <tr class="table__row table__row_header">
                <th class="table__cell table__cell_header">Name</th>
                <th class="table__cell table__cell_header">Money spent</th>
                <th class="table__cell table__cell_header">Card number</th>
            </tr>
            <c:forEach items="${customerCardDTOS}" var="customerCard">
                <tr class="table__row table__row_cells">
                    <td class="table__cell">${customerCard.surname} ${customerCard.name} ${customerCard.patronymic}</td>
                    <td class="table__cell">${customerCard.checkSum}</td>
                    <td class="table__cell">${customerCard.cardNumber}</td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${empty customerCardDTOS}">
            <p>Seems like there are no employee records.</p>
        </c:if>
    </div>
</template:page>
