<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:page pageTitle="Add Employee">
    <div class="links-style">
        <c:if test="${not empty employee}">
            <a style="display: block; text-decoration: none" href="/employees">
                <button class="go-back">Go back</button>
            </a>

            <form:form modelAttribute="employee" action="/employee/add" method="post">
                <label>Login : </label>
                <input type="text" name="id"  value="${employee.id}" required/>
                <form:errors path="id" type="number" name="phoneNumber" cssStyle="background: #ff704d;border-radius: 5px"/>
                <br>
                <br>
                <label>name : </label>
                <input type="text" name="name" value="${employee.name}" required/>
                <form:errors path="name" type="number" name="phoneNumber" cssStyle="background: #ff704d;border-radius: 5px"/>
                <br>
                <br>
                <label>surname : </label>
                <input type="text" name="surname" value="${employee.surname}" required/>
                <form:errors path="surname" type="number" name="phoneNumber" cssStyle="background: #ff704d;border-radius: 5px"/>
                <br>
                <br>
                <label>password : </label>
                <input type="text" name="password" value="${employee.password}" required/>
                <form:errors path="password" type="number" name="phoneNumber" cssStyle="background: #ff704d;border-radius: 5px"/>
                <br>
                <br>
                <label>patronymic : </label>
                <input type="text" name="patronymic" value="${employee.patronymic}" required/>
                <form:errors path="patronymic" type="number" name="phoneNumber" cssStyle="background: #ff704d;border-radius: 5px"/>
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
                <form:input path="birthdate" type="date" name="birthdate" value="${employee.birthdate}" required="true"/>
                <br>
                <br>
                <label>Start date : </label>
                <form:input path="startDate" type="date" name="startDate" value="${employee.startDate}" required="true"/>
                <br>
                <br>
                <label>Salary : </label>
                <input type="number" name="salary" value="${employee.salary}" required/>
                <form:errors path="salary" type="number" name="phoneNumber" cssStyle="background: #ff704d;border-radius: 5px"/>
                <br>
                <br>
                <label>Phone : </label>
                <form:input path="phoneNumber" type="tel" name="phoneNumber"  value="${employee.phoneNumber}" />
                <form:errors path="phoneNumber" type="number" name="phoneNumber" cssStyle="background: #ff704d;border-radius: 5px"/>
                <br>
                <br>
                <label>Street : </label>
                <input type="text" name="street" value="${employee.street}" required/>
                <form:errors path="street" type="number" name="phoneNumber" cssStyle="background: #ff704d;border-radius: 5px"/>
                <br>
                <br>
                <label>City : </label>
                <input type="text" name="city" value="${employee.city}" required/>
                <form:errors path="city" type="number" name="phoneNumber" cssStyle="background: #ff704d;border-radius: 5px"/>
                <br>
                <br>
                <label>Zip-code : </label>
                <form:input path="zipCode" type="text" name="zipCode" value="${employee.zipCode}" required="true" />
                <form:errors path="zipCode" type="text" name="zipCode" cssStyle="background: #ff704d;border-radius: 5px"/>
                <button class="add-button" type="submit"> Add </button>

            </form:form>
        </c:if>
    </div>
</template:page>
