<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:page pageTitle="Checks">
    <c:if test="${not empty param.constraintError}">
        <script>
            alert(getURLParameter("constraintError"));
            location.href = "/checks";
        </script>
    </c:if>
    <div class="hidden-print">
        <button onclick="print()">Print</button>
    </div>
    <h6 class="print-header">Checks</h6>
    <form class="hidden-print" id="searchFormCheck" onSubmit="event.preventDefault(); searchChecks();">
        <button type="submit">Шукати</button>
    </form>
    <div>
        <table class="table">
            <tr class="table__row table__row_header">
                <th class="table__cell table__cell_header">Number</th>
                <th class="table__cell table__cell_header">Employee</th>
                <th class="table__cell table__cell_header">Customer Card</th>
                <th class="table__cell table__cell_header">Print date</th>
                <th class="table__cell table__cell_header">Total</th>
                <th class="table__cell table__cell_header">Vat</th>
                <th class="table__cell table__cell_header table__cell-button hidden-print"></th>
            </tr>
            <c:forEach items="${checks}" var="check">
                <tr class="table__row table__row_cells">
                    <td class="table__cell">${check.number}</td>
                    <td class="table__cell">${check.employee.surname} ${check.employee.name} ${check.employee.patronymic}</td>
                    <td class="table__cell">${check.card.number}</td>
                    <td class="table__cell">${check.printDate}</td>
                    <td class="table__cell">${check.totalSum}</td>
                    <td class="table__cell">${check.VAT}</td>
                    <td class="table__cell table__cell-button hidden-print">
                        <a href="/check/${check.number}">Info</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${empty checks}">
            <p>Seems like there are no checks.</p>
        </c:if>
    </div>
</template:page>
