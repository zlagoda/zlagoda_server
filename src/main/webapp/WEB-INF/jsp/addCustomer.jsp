<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:page pageTitle="Add Customer">
    <div class="links-style">
        <c:if test="${not empty customerCard}">
            <a style="display: block; text-decoration: none" href="/customers">
                <button class="go-back">Go back</button>
            </a>

            <form:form modelAttribute="customerCard" action="/customer/add" method="post">
                <label>Number : </label>
                <input type="text" name="number"  value="${customerCard.number}" required/>
                <form:errors path="number" type="number" name="phoneNumber" cssStyle="background: #ff704d;border-radius: 5px"/>
                <br>
                <br>
                <label>name : </label>
                <input type="text" name="name" value="${customerCard.name}" required/>
                <form:errors path="name" type="number" name="phoneNumber" cssStyle="background: #ff704d;border-radius: 5px"/>
                <br>
                <br>
                <label>surname : </label>
                <input type="text" name="surname" value="${customerCard.surname}" required/>
                <form:errors path="surname" type="number" name="phoneNumber" cssStyle="background: #ff704d;border-radius: 5px"/>
                <br>
                <br>
                <label>patronymic : </label>
                <input type="text" name="patronymic" value="${customerCard.patronymic}"/>
                <form:errors path="patronymic" type="number" name="phoneNumber" cssStyle="background: #ff704d;border-radius: 5px"/>
                <br>
                <br>
                <label>Phone : </label>
                <form:input path="phoneNumber" type="tel" name="phoneNumber"  value="${customerCard.phoneNumber}" />
                <form:errors path="phoneNumber" type="number" name="phoneNumber" cssStyle="background: #ff704d;border-radius: 5px"/>
                <br>
                <br>
                <label>Street : </label>
                <input type="text" name="street" value="${customerCard.street}">
                <form:errors path="street" type="number" name="phoneNumber" cssStyle="background: #ff704d;border-radius: 5px"/>
                <br>
                <br>
                <label>City : </label>
                <input type="text" name="city" value="${customerCard.city}"/>
                <form:errors path="city" type="number" name="phoneNumber" cssStyle="background: #ff704d;border-radius: 5px"/>
                <br>
                <br>
                <label>Zip-code : </label>
                <form:input path="zipCode" type="text" name="zipCode" value="${customerCard.zipCode}"/>
                <form:errors path="zipCode" type="text" name="zipCode" cssStyle="background: #ff704d;border-radius: 5px"/>
                <br>
                <br>
                <label>Percent : </label>
                <input type="number" name="percent" value="${customerCard.percent}" required/>
                <form:errors path="percent" type="number" name="phoneNumber" cssStyle="background: #ff704d;border-radius: 5px"/>
                <button class="add-button" type="submit"> Add </button>

            </form:form>
        </c:if>
    </div>
</template:page>