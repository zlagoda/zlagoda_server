<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib
        prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template"
           tagdir="/WEB-INF/tags" %>

<template:page pageTitle="Check info">
    <c:if test="${not empty param.constraintError}">
        <script>
            alert(getURLParameter("constraintError"));
            location.href = "/checks";
        </script>
    </c:if>
    <div class="hidden-print">
        <button onclick="print()">Print</button>
    </div>
    <h6 class="print-header">Check number: ${check.number}</h6>
    <div>
        <table class="table">
            <tr class="table__row table__row_header">
                <th class="table__cell table__cell_header">UPC</th>
                <th class="table__cell table__cell_header">Name</th>
                <th class="table__cell table__cell_header">Price</th>
                <th class="table__cell table__cell_header">Amount</th>
            </tr>
            <c:forEach items="${check.products}" var="product">
                <tr class="table__row table__row_cells">
                    <td class="table__cell">${product.UPC}</td>
                    <td class="table__cell">${product.name}</td>
                    <td class="table__cell">${product.price}</td>
                    <td class="table__cell">${product.amount}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div>
        <h6>Total sum: ${check.totalSum}</h6>
        <h6>VAT: ${check.VAT}</h6>
        <h6>Date: ${check.printDate}</h6>
        <c:if test="${not empty check.card.number}">
            <h6>Customer card: ${check.card.number}</h6>
        </c:if>
        <h6>Cashier: ${check.employee.surname} ${check.employee.name} ${check.employee.patronymic}</h6>
    </div>
    <a class="hidden-print" href="/manager/checks">
        <h4>Go back</h4>
    </a>
</template:page>