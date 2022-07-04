<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags"%>
<template:page pageTitle="My Account">
    <div class="container , text-style-products">
        <h2>Login : ${employee.id}</h2>
        <h3>First name : ${employee.name}</h3>
        <h3>Last name :${employee.surname}</h3>
        <h3>Patronymic : ${employee.patronymic}</h3>
        <h2>Role : ${employee.role}</h2>
        <h2>Birthday : ${employee.birthdate}</h2>
        <h2>Start Date : ${employee.startDate}</h2>
        <h2>Phone : ${employee.phoneNumber}</h2>
        <h2>Salary : ${employee.salary}</h2>
        <h2>Street : ${employee.street}</h2>
        <h2>City : ${employee.city}</h2>
        <h2>Zip Code : ${employee.zipCode}</h2>
    </div>
</template:page>
