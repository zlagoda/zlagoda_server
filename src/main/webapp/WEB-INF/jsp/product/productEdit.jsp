<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %> <%@ taglib prefix="template"
tagdir="/WEB-INF/tags" %>

<template:page pageTitle="Edit Category">
  <form:form
    id="productEditForm"
    modelAttribute="product"
    action="/manager/product/edit"
    method="post"
  >
    <input type="text" name="id" hidden value="${product.id}"/>
    <input type="text" name="name" value="${product.name}" placeholder="Product name" required/>
    <textarea name="characteristics" form="productEditForm" placeholder="Describe product" required>${product.characteristics}</textarea>
    <select name="categoryId">
      <c:forEach items="${categories}" var="category">
      <c:choose>
        <c:when test="${category.id == product.category.id}">
          <option value="${category.id}" selected="selected">${category.name}</option>
        </c:when>    
        <c:otherwise>
          <option value="${category.id}">${category.name}</option>
        </c:otherwise>
      </c:choose>
      </c:forEach>
    </select> 
    <button type="submit">Apply</button>
  </form:form>
  <a href="/products">
    <button>Go back</button>
  </a>
</template:page>