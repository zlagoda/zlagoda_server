<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib
        prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"
           uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template"
           tagdir="/WEB-INF/tags" %>

<template:page pageTitle="Customer Cards">
    <h6 class="print-header">Customer Cards</h6>
    <div>
        <table class="table">
            <tr class="table__row table__row_header">
                <th class="table__cell table__cell_header">Name</th>
                <th class="table__cell table__cell_header">Phone number</th>
                <th class="table__cell table__cell_header">Number</th>
                <th class="table__cell table__cell_header">Address</th>
                <th class="table__cell table__cell_header">Discount</th>
                <th class="table__cell table__cell_header hidden-print"></th>
                <th class="table__cell table__cell_header hidden-print"></th>
            </tr>
            <c:forEach items="${customerCards}" var="customerCard">
                <tr class="table__row">
                <tr>
                    <td class="table__cell">${customerCard.name} ${customerCard.surname} ${customerCard.patronymic}</td>
                    <td class="table__cell">${customerCard.phoneNumber}</td>
                    <td class="table__cell">${customerCard.number}</td>
                    <td class="table__cell">${customerCard.city}${customerCard.street}${customerCard.zipCode}</td>
                    <td class="table__cell">${customerCard.percent}</td>
                    <td class="table__cell hidden-print">
                        <a href="/customer/${customerCard.number}">Edit</a>
                    </td>
                    <td class="table__cell hidden-print">
                        <a href="/customer/delete/${customerCard.number}" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${empty customerCards}">
            <p>Seems like there are no employee records.</p>
        </c:if>
    </div>
    <button class="hidden-print" onclick="print()">Print</button>
    <a href="/customer/add">
        <button class="hidden-print">Add</button>
    </a>
</template:page>
