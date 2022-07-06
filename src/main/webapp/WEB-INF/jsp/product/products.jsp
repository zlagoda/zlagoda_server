<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %> <%@ taglib prefix="template"
tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<template:page pageTitle="Products">
  <c:if test="${not empty param.constraintError}">
    <script>
      alert(getURLParameter("constraintError"));
      location.href = "/products";
    </script>
  </c:if>
  <sec:authorize access="hasRole('MANAGER')">
  <div class="hidden-print">
    <button onclick="print()">Print</button>
  </div>
  <div class="hidden-print">
    <a href="/manager/product/0">
      <button>Add new</button>
    </a>
  </div>
  </sec:authorize>
  <h6 class="print-header">Products</h6>
  <form class="hidden-print" id="searchFormProduct" onSubmit="event.preventDefault(); searchProducts();">
    <c:forEach items="${categories}" var="category">
      <span>
        <label>
          ${category.name}
          <input type="checkbox" name="category" value="${category.name}">
        </label>|
      </span>
    </c:forEach>
    <button type="submit">Шукати</button>
  </form>
  <div>
    <table class="table">
      <tr class="table__row table__row_header">
        <th class="table__cell table__cell_header">Name</th>
        <th class="table__cell table__cell_header">Characteristics</th>
        <th class="table__cell table__cell_header">Category</th>
        <sec:authorize access="hasRole('MANAGER')">
        <th class="table__cell table__cell_header table__cell-button hidden-print"></th>
        <th class="table__cell table__cell_header table__cell-button hidden-print"></th>
        </sec:authorize>
      </tr>
    <c:forEach items="${products}" var="product">
      <tr class="table__row table__row_cells">
        <td class="table__cell">${product.name}</td>
        <td class="table__cell">${product.characteristics}</td>
        <td class="table__cell">${product.category.name}</td>
        <sec:authorize access="hasRole('MANAGER')">
        <td class="table__cell table__cell-button hidden-print">
          <a href="/manager/product/${product.id}">Edit</a>
        </td>
        <td class="table__cell table__cell-button hidden-print">
          <form:form modelAttribute="deleteProduct" action="/manager/product/delete" method="post">
            <input name="id" type="hidden" value="${product.id}"/>
            <button type="submit" onclick="return confirm('Are you sure?')">Delete</button>
          </form:form>
        </td>
        </sec:authorize>
      </tr>
    </c:forEach>
    </table>
    <c:if test="${empty products}">
        <p>Seems like there are no products.</p>
    </c:if>
  </div>
</template:page>