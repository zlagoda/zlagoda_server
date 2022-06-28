<%@ attribute name="pageTitle" %>
<%@ tag language="java" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>

<head>
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/ajax_sendProductInfo.js" type="text/javascript"></script>
    <title>${not empty pageTitle ? pageTitle  : ''}</title>
</head>
<body>
<jsp:doBody/>
</body>
