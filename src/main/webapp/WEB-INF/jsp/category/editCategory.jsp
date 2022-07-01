<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %> <%@ taglib prefix="template"
tagdir="/WEB-INF/tags" %>

<template:page pageTitle="Edit Category">
  <form:form
    modelAttribute="category"
    action="/manager/categories/edit"
    method="post"
  >
    <input type="text" name="id" hidden value="${category.id}" />
    <input type="text" name="name" value="${category.name}" />
    <button type="submit">Edit</button>
  </form:form>
  <a href="/manager/categories">
    <button>Go back</button>
  </a>
</template:page>
