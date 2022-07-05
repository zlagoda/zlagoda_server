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
    <h6 class="print-header">Employees</h6>
    <sec:authorize access="hasRole('MANAGER')">
        <button class="hidden-print" onclick="print()">Print</button>
    </sec:authorize>
    <form class="hidden-print" id="searchFromEmployee" onSubmit="event.preventDefault(); searchEmployee();">
    <input type="search" placeholder="Name" name="name">
    <label>
    <input type="checkbox" checked name="cashier" value="CASHIER">
        CASHIER
    </label>
    <label>
    <input type="checkbox" checked name="manager" value="MANAGER">
        MANAGER
    </label>
    <button type="submit">Шукати</button>
    </form>
    <div>
        <table class="table">
            <tr class="table__row table__row_header">
                <th class="table__cell table__cell_header">Name</th>
                <th class="table__cell table__cell_header">Phone number</th>
                <th class="table__cell table__cell_header">Role</th>
                <th class="table__cell table__cell_header">Salary</th>
                <th class="table__cell table__cell_header">Birthdate</th>
                <th class="table__cell table__cell_header">Start date</th>
                <th class="table__cell table__cell_header">Address</th>
                <th class="table__cell table__cell_header hidden-print"></th>
                <th class="table__cell table__cell_header hidden-print"></th>
            </tr>
            <c:forEach items="${employees}" var="employee">
                <tr class="table__row table__row_cells">
                    <td class="table__cell">${employee.surname} ${employee.name} ${employee.patronymic}</td>
                    <td class="table__cell">${employee.phoneNumber}</td>
                    <td class="table__cell">${employee.role}</td>
                    <td class="table__cell">${employee.salary}</td>
                    <td class="table__cell">${employee.birthdate}</td>
                    <td class="table__cell">${employee.startDate}</td>
                    <td class="table__cell">${employee.city}, ${employee.street}, ${employee.zipCode}</td>
                    <td class="table__cell hidden-print">
                        <a href="/employee/${employee.id}">Edit</a>
                    </td>
                    <td class="table__cell hidden-print">
                        <a href="/employee/delete/${employee.id}" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${empty employees}">
            <p>Seems like there are no employee records.</p>
        </c:if>
    </div>
    <button class="hidden-print" onclick="print()">Print</button>
    <a href="employee/add">
        <button class="hidden-print">Add</button>
    </a>
    <a href="employees/statistics">
        <h5>Statistics</h5>
    </a>
</template:page>
