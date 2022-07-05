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
    <form class="hidden-print" id="searchFormCheck">
    <select id="selectCheckFilter" name="filterSelect">
      <option value="1">Category</option>
      <option value="2">Products</option>
      <option value="3">Employees</option>
    </select>
      <div id="1checkSearch">
      <c:forEach items="${categories}" var="category">
        <span>
        <label>
          ${category.name}
          <input type="checkbox" name="category" value="${category.id}">
        </label>|
        </span>
      </c:forEach>
      <label>
            Only this
          <input type="checkbox" name="only" value="true">
        </label>
      </div>
      <div id="2checkSearch">
      <c:forEach items="${products}" var="product">
        <span>
        <label>
          ${product.name}
          <input type="checkbox" name="product" value="${product.id}">
        </label>|
        </span>
      </c:forEach>
      </div>
      <div id="3checkSearch">
        <select name="employee">
          <c:forEach items="${employees}" var="employee">
            <option value="${employee.id}">${employee.surname} ${employee.name} ${employee.patronymic}</option>
          </c:forEach>
        </select>
      </div> 
      <button type="submit">Search</button>
    </form>
    <br>
    <form onSubmit="event.preventDefault(); filterChecksByDate();">
      <label>From</label>
      <input type="datetime-local" id="timeFromCheck">
      <br>
      <label>To</label>
      <input type="datetime-local" id="timeToCheck">
      <br>
      <button type="submit">Filter</button>
    </form>
    <div>
        <table class="table">
            <tr class="table__row table__row_header">
                <th class="table__cell table__cell_header">Number</th>
                <th class="table__cell table__cell_header">Employee</th>
                <th class="table__cell table__cell_header">Customer Card</th>
                <th class="table__cell table__cell_header">Print date</th>
                <th class="table__cell table__cell_header" id="totalSumField">Total</th>
                <th class="table__cell table__cell_header" id="vatSumField">Vat</th>
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
