<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home page</title>
</head>
<body style="margin: 0; padding: 0;">
<style>
    .center {
        margin: 0;
        position: absolute;
        top: 50%;
        left: 50%;
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }

    .title {
        color: aliceblue;
        padding: 60px;
        border-radius: 5px;
        text-decoration: none;
        background: sandybrown;
        font-family: "Trebuchet MS", Helvetica, sans-serif;
    }

    .greeting {
        position: absolute;
        top: 30%;
        left: 50%;
        font-family: "Trebuchet MS", Helvetica, sans-serif;
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }
    .greeting {
        position: absolute;
        top: 30%;
        left: 50%;
        font-family: "Trebuchet MS", Helvetica, sans-serif;
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }
</style>
<jsp:include page="header/main_header.jsp"/>
<sec:authorize access="isAuthenticated()">
    <div class="greeting">
        <h1>Hello ,
            <sec:authentication property="principal.customer.firstName"/>
        </h1>
    </div>
</sec:authorize>
<h1 class="center title">Welcome to our shop !</h1>
</body>
</html>