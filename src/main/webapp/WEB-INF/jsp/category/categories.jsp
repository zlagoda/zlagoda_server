<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %> <%@ taglib prefix="template"
tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<template:page pageTitle="Categories">
  <c:if test="${not empty param.constraintError}">
    <script>
      alert(getURLParameter("constraintError"));
      location.href = "/manager/categories";
    </script>
  </c:if>
  <div class="hidden-print">
    <button onclick="print()">Print</button>
    <div>
      <form:form modelAttribute="newCategory" action="/manager/categories/new" method="post">
        <input type="text" name="name" required="required" placeholder="Input new category name">
        <button type="submit">Add</button>
      </form:form>
    </div>
  </div>
  <h6 class="print-header">Categories</h6>
  <div>
    <table class="table">
      <tr class="table__row table__row_header">
        <th class="table__cell table__cell_header">Name</th>
        <th class="table__cell table__cell_header">Amount</th>
        <th class="table__cell table__cell_header">Total Price</th>
        <th class="table__cell table__cell_header hidden-print"></th>
        <th class="table__cell table__cell_header table__cell-button hidden-print"></th>
      </tr>
    <c:forEach items="${categoryDTOS}" var="category">
<tr class="table__row">
      <tr>
        <td class="table__cell">${category.catName}</td>
        <td class="table__cell">${category.totalAmount}</td>
        <td class="table__cell">${category.totalPrice}</td>
        <td class="table__cell table__cell-button hidden-print">
          <a href="/manager/categories/edit/${category.id}">Edit</a>
        </td>
        <td class="table__cell table__cell-button hidden-print">
          <form:form modelAttribute="deleteCategory" action="/manager/categories/delete" method="post">
            <input name="id" type="hidden" value="${category.id}"/>
            <button type="submit" onclick="return confirm('Are you sure?')">Delete</button>
          </form:form>
        </td>
      </tr>
    </c:forEach>
    </table>
    <c:if test="${empty categoryDTOS}">
        <p>Seems like there are no categories.</p>
    </c:if>
  </div>
</template:page>