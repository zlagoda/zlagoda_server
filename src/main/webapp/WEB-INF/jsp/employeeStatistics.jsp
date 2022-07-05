<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib
        prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"
           uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template"
           tagdir="/WEB-INF/tags" %>

<template:page pageTitle="Employees">
    <h6 class="print-header">Employees statistic</h6>
    <div>
        <table class="table">
            <tr class="table__row table__row_header">
                <th class="table__cell table__cell_header">Name</th>
                <th class="table__cell table__cell_header">Check amount</th>
                <th class="table__cell table__cell_header">Total check prices</th>
            </tr>
            <c:forEach items="${employeesStatistic}" var="employee">
                <tr class="table__row table__row_cells">
                    <td class="table__cell">${employee.surname} ${employee.name} ${employee.patronymic}</td>
                    <td class="table__cell">${employee.checksCount}</td>
                    <td class="table__cell">${employee.totalSum}</td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${empty employeesStatistic}">
            <p>Seems like there are no employee records.</p>
        </c:if>
    </div>
    <sec:authorize access="hasRole('MANAGER')">
        <button class="hidden-print" onclick="print()">Print</button>
    </sec:authorize>
</template:page>
