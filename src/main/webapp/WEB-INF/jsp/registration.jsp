<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Registration page</title>
</head>
<body style="margin: 0; padding: 0;">
<style>
    .registration {
        width: 50%;
        text-align: center;
        background: orange;
        border-radius: 5px;
        padding: 30px;
        position: absolute;
        top: 50%;
        left: 50%;
        font-family: "Trebuchet MS", Helvetica, sans-serif;
        font-size: 20px;
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }

    .input {
        margin-right: 10px;
        padding: 8px;
        border: none;
        border-bottom: 1px solid #ccc;
        width: 10%;
    }

    .go-back {
        display: inline-block;
        background-color: #f4511e;
        border: none;
        color: white;
        padding: 16px 32px;
        text-align: center;
        font-size: 16px;
        margin: 4px 2px;
        opacity: 0.6;
        transition: 0.3s;
        text-decoration: none;
        cursor: pointer;
    }

    .go-back:hover {
        opacity: 1
    }
</style>
<jsp:include page="header/main_header.jsp"/>
<div class="registration">
    <form:form
            modelAttribute="customerDTO"
            cssStyle="align-self: center"
            action="/registration"
            method="post">
        <label>Enter login</label>
        <form:input cssClass="input" path="login" type="email" required="true" placeholder="example@gmail.com"/>
        <form:errors path="login" cssStyle="background: indianred;border-radius: 5px"/>
        <br>
        <br>
        <label>Enter Password</label>
        <form:input cssClass="input" path="password" type="password" required="true"/>
        <br>
        <br>
        <label>Enter First Name (Only Letters)</label>
        <form:input cssClass="input" path="firstName" type="text" placeholder="Ivan" required="true"/>
        <form:errors path="firstName" cssStyle="background: indianred;border-radius: 5px"/>
        <br>
        <br>
        <label>Enter Last Name (Only Letters)</label>
        <form:input cssClass="input" path="lastName" type="text" placeholder="Volkov" required="true"/>
        <form:errors path="lastName" cssStyle="background: indianred;border-radius: 5px"/>
        <br>
        <br>
        <label>Select Gender</label>
        <form:select cssClass="input" path="gender" name="gender">
            <c:forEach var="genderValue" items="${genderValues}">
                <form:option value="${genderValue}">${genderValue}</form:option>
            </c:forEach>
        </form:select>
        <form:errors path="gender" cssStyle="background: indianred;border-radius: 5px"/>
        <br>
        <br>
        <label>Enter Birthday in format "dd-MM-yyyy"</label>
        <form:input cssClass="input" path="birthDay" type="text" required="true" placeholder="08-12-2002"/>
        <form:errors path="birthDay" cssStyle="background: indianred;border-radius: 5px"/>
        <br>
        <br>
        <label>Enter phone (only 10 digits)</label>
        <form:input cssClass="input" path="phone" type="number" required="true" placeholder="**********"/>
        <form:errors path="phone" cssStyle="background: indianred;border-radius: 5px"/>
        <br>
        <br>
        <input class="input" type="submit" value="Sign Up">
    </form:form>
    <a style="display: block" href="/login">
        <button class="go-back">Go Back</button>
    </a>
</div>
</body>
</html>
