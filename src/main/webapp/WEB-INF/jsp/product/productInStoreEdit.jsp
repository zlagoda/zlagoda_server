<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib
        prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template"
           tagdir="/WEB-INF/tags" %>

<template:page pageTitle="Edit Category">
    <form:form
            modelAttribute="productInStore"
            action="/manager/products-in-store/edit"
            method="post"
    >
        <div class="links-style">
        <input type="text" name="id" hidden value="${productInStore.UPC}"/>
        <form:input path="UPC" type="text" name="UPC" placeholder="UPC" required ="true"/>
        <form:errors path="UPC" type="text" name="UPC" cssStyle="background: #ff704d;border-radius: 5px"/>
        <select name="productId">
            <c:forEach items="${products}" var="productVar">
                <c:choose>
                    <c:when test="${productVar.id == productInStore.productId}">
                        <option value="${productVar.id}" selected="selected">${productVar.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${productVar.id}">${productVar.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <form:errors path="productId" type="text" name="UPC" cssStyle="background: #ff704d;border-radius: 5px"/>
        <input type="checkbox" ${productInStore.promotional ? "checked" : ""} value="true" name="promotional">Promotional</input>
        <form:input path="price" type="number" name="price" min="0" step="0.01" value="${productInStore.price}" placeholder="Price" required ="true"/>
        <form:input path="amount" type="number" min="0" name="amount" placeholder="Amount" required ="true"/>
        <button type="submit">Apply</button>
    </form:form>
    <a href="/products-in-store">
        <button>Go back</button>
    </a>
    </div>
</template:page>