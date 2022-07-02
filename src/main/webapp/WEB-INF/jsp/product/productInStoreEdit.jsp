<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %> <%@ taglib prefix="template"
tagdir="/WEB-INF/tags" %>

<template:page pageTitle="Edit Category">
  <form:form
    modelAttribute="product"
    action="/manager/products-in-store/edit"
    method="post"
  >
    <h1>${productInStore}</h1>
    <h1>${products}</h1>
    <input type="text" name="id" hidden value="${productInStore.UPC}"/>
    <input type="text" name="UPC" value="${productInStore.UPC}"/>
    <select name="productId">
      <c:forEach items="${products}" var="product">
      <c:choose>
        <c:when test="${product.id == productInStore.product.id}">
          <option value="${product.id}" selected="selected">${product.name}</option>
        </c:when>    
        <c:otherwise>
          <option value="${product.id}">${product.name}</option>
        </c:otherwise>
      </c:choose>
      </c:forEach>
    </select> 
    <input type="checkbox" ${productInStore.promotional ? "checked" : ""} value="true" name="promotional">Promotional</input>
    <input type="number" name="price" min="0" step="0.01" value="${productInStore.price}"/>
    <input type="number" min="0" name="amount" value="${productInStore.amount}"/>
    <button type="submit">Apply</button>
  </form:form>
  <a href="/products-in-store">
    <button>Go back</button>
  </a>
</template:page>