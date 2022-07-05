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

    <a href="/customer/add">
        <button class="hidden-print">Add</button>
    </a>

    <form class="hidden-print" id="searchFormCustomer" onSubmit="event.preventDefault(); searchCustomers();">
    <input type="search" placeholder="Name, phone or card number" name="name">
    <label>
        From
        <input type="number" placeholder="From" min="0" max="100" name="from" value="0">
    </label>
    <label>
        To
        <input type="number" placeholder="To" min="0" max="100" name="to" value="100">
    </label>
    <button type="submit">Шукати</button>
    </form>
    <div>
        <table class="table">
            <tr class="table__row table__row_header">
                <th class="table__cell table__cell_header">Name</th>
                <th class="table__cell table__cell_header">Phone number</th>
                <th class="table__cell table__cell_header">Card number</th>
                <th class="table__cell table__cell_header">Address</th>
                <th class="table__cell table__cell_header">Discount</th>
                <th class="table__cell table__cell_header hidden-print">
                </th><sec:authorize access="hasRole('MANAGER')">
                <th class="table__cell table__cell_header hidden-print"></th>
                </sec:authorize>
            </tr>
            <c:forEach items="${customerCards}" var="customerCard">
                <tr class="table__row table__row_cells">
                    <td class="table__cell">${customerCard.surname} ${customerCard.name} ${customerCard.patronymic}</td>
                    <td class="table__cell">${customerCard.phoneNumber}</td>
                    <td class="table__cell">${customerCard.number}</td>
                    <td class="table__cell">${customerCard.city} ${customerCard.street} ${customerCard.zipCode}</td>
                    <td class="table__cell">${customerCard.percent}%</td>
                    <td class="table__cell hidden-print">
                        <a href="/customer/${customerCard.number}">Edit</a>
                    </td>
                    <sec:authorize access="hasRole('MANAGER')">
                    <td class="table__cell hidden-print">
                        <a href="/customer/delete/${customerCard.number}" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${empty customerCards}">
            <p>Seems like there are no employee records.</p>
        </c:if>
    </div>
</template:page>
