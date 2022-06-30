<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:page pageTitle="Edit Employee">
    <div class="links-style">
        <c:if test="${not empty employee}">
            <a style="display: block; text-decoration: none" href="/employees">
                <button class="go-back">Go back</button>
            </a>

            <form:form modelAttribute="employee" action="/employee/edit" method="post">

                <input type="text" name="id" hidden value="${employee.id}"/>
                <label>name : </label>
                <input type="text" name="name" value="${employee.name}"/>
                <br>
                <br>
                <label>surname : </label>
                <input type="text" name="surname" value="${employee.surname}"/>
                <br>
                <br>
                <label>patronymic : </label>
                <input type="text" name="patronymic" value="${employee.patronymic}"/>
                <br>
                <br>
                <label>role : </label>
                <form:select cssClass="input-registration" path="role" name="role">
                    <c:forEach var="role" items="${roles}">
                        <form:option value="${role}">${role}</form:option>
                    </c:forEach>
                </form:select>
                <br>
                <br>
                <label>Birth date : </label>
                <input type="date" name="birthdate" value="${employee.birthdate}"/>
                <br>
                <br>
                <label>Start date : </label>
                <input type="date" name="startDate" value="${employee.startDate}"/>
                <br>
                <br>
                <label>Salary : </label>
                <input type="number" name="salary" value="${employee.salary}"/>
                <br>
                <br>
                <label>Phone : </label>
                <form:input path="phoneNumber" type="tel" name="phoneNumber"  value="${employee.phoneNumber}" />
                <c:if test="${not empty invalidPhone}"><label style="background: #ff704d;border-radius: 5px">${invalidPhone}</label></c:if>
                <form:errors type="number" name="phoneNumber" cssStyle="background: #ff704d;border-radius: 5px"/>
                <br>
                <br>
                <label>Street : </label>
                <input type="text" name="street" value="${employee.street}"/>
                <br>
                <br>
                <label>City : </label>
                <input type="text" name="city" value="${employee.city}"/>
                <br>
                <br>
                <label>Zip-code : </label>
                <input type="text" name="zipCode" value="${employee.zipCode}"/>
                <button class="add-button" type="submit"> Edit </button>

            </form:form>
        </c:if>
    </div>
</template:page>