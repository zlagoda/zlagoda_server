<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %> <%@ taglib prefix="template"
tagdir="/WEB-INF/tags" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>

<template:page pageTitle="Cart">
  <c:if test="${not empty param.noSuchElement}">
    <script>
      alert(getURLParameter("noSuchElement"));
      location.href = "/cashier/cart";
    </script>
  </c:if>
  <div>
    <table class="table">
      <tr class="table__row table__row_header">
        <th class="table__cell table__cell_header">UPC</th>
        <th class="table__cell table__cell_header">Name</th>
        <th class="table__cell table__cell_header">Price</th>
        <th class="table__cell table__cell_header">Amount</th>
        <th
          class="table__cell table__cell_header table__cell-button hidden-print"
        ></th>
      </tr>
      <c:forEach items="${cart}" var="cartItem">
        <tr class="table__row table__row_cells">
          <td class="table__cell">${cartItem.UPC}</td>
          <td class="table__cell">${cartItem.name}</td>
          <td class="table__cell">${cartItem.price}</td>
          <td class="table__cell">${cartItem.amount}</td>
          <td class="table__cell table__cell-button hidden-print">
            <form:form modelAttribute="deleteProduct" action="/cashier/cart/remove" method="post">
              <input name="UPC" type="hidden" value="${cartItem.UPC}" />
              <button type="submit" onclick="return confirm('Are you sure?')">
                Delete
              </button>
            </form:form>
          </td>
        </tr>
      </c:forEach>
    </table>
    <c:if test="${empty cart}">
      <p>Seems like there are no products.</p>
    </c:if>
  </div>
  <form:form
    modelAttribute="cardNumber"
    action="/cashier/cart/customer-card"
    method="post"
  >
    <input
      placeholder="Number of card"
      name="cardNumber"
      type="text"
      value=""
    />
    <button type="submit">Add customer card</button>
  </form:form>
  <c:if test="${not empty customer}">
    <h4>Discount: ${customer.percent}%</h4>
  </c:if>
  <h4>Total price: <span id="total-price-item">0</span></h4>
  <h4>VAT: <span id="vat-item">0</span></h4>
  <h4>Total: <span id="total-item">0</span></h4>
  <form:form action="/cashier/cart/create" method="post" onSubmit="return checkEmptyCart()">
    <button type="submit">Confirt purchase</button>
  </form:form>
  <script>
    setTotal(${empty customer ? 0 : customer.percent});
  </script>
</template:page>
