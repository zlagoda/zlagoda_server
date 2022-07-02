<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %> <%@ taglib prefix="template"
tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<template:page pageTitle="Products in store">
  <c:if test="${not empty param.constraintError}">
    <script>
      alert(getURLParameter("constraintError"));
      location.href = "/products-in-store";
    </script>
  </c:if>
  <div class="hidden-print">
    <button onclick="print()">Print</button>
  </div>
  <div class="hidden-print">
    <a href="/manager/products-in-store/0">
      <button>Add new</button>
    </a>
  </div>
  <h6 class="print-header">Products in store</h6>
  <div>
    <table class="table">
      <tr class="table__row table__row_header">
        <th class="table__cell table__cell_header">UPC</th>
        <th class="table__cell table__cell_header">Promotional</th>
        <th class="table__cell table__cell_header">Name</th>
        <th class="table__cell table__cell_header">Price</th>
        <th class="table__cell table__cell_header">Amount</th>
        <th class="table__cell table__cell_header">Category</th>
        <th class="table__cell table__cell_header table__cell-button hidden-print"></th>
        <th class="table__cell table__cell_header table__cell-button hidden-print"></th>
      </tr>
    <c:forEach items="${products}" var="product">
<tr class="table__row">
      <tr>
        <td class="table__cell">${product.UPC}</td>
        <td class="table__cell">${product.promotional ? "Yes" : "No"}</td>
        <td class="table__cell">${product.product.name}</td>
        <td class="table__cell">${product.price}</td>
        <td class="table__cell">${product.amount}</td>
        <td class="table__cell">${product.product.category.name}</td>
        <td class="table__cell table__cell-button hidden-print">
          <a href="/manager/products-in-store/${product.UPC}">Edit</a>
        </td>
        <td class="table__cell table__cell-button hidden-print">
          <form:form modelAttribute="deleteProduct" action="/manager/products-in-store/delete" method="post">
            <input name="UPC" type="hidden" value="${product.UPC}"/>
            <button type="submit" onclick="return confirm('Are you sure?')">Delete</button>
          </form:form>
        </td>
      </tr>
    </c:forEach>
    </table>
    <c:if test="${empty products}">
        <p>Seems like there are no products.</p>
    </c:if>
  </div>
</template:page>