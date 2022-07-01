<%@ attribute name="pageTitle" %>
<%@ tag language="java" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags" %>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../../css/print.css" media="print" />
    <link rel="stylesheet" href="../../../css/style.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script src="../../scripts/script.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/ajax_sendProductInfo.js" type="text/javascript"></script>
    <title>${not empty pageTitle ? pageTitle  : ''}</title>
</head>
<body>
<jsp:doBody/>
</body>
