<%@ include file="/common/include.jsp"%>

<head>
    <title><fmt:message key="label.login.title"/></title>
    <content tag="heading"><fmt:message key="label.login.heading"/></content>
    <meta name="menu" content="Login"/>
    <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/common/styles/simplicity/layout-1col.css'/>" />
</head>
<body id="login"/>

<%-- Include the login form --%>
<jsp:include page="/common/loginForm.jsp"/>

<p><fmt:message key="label.login.passwordHint"/></p>
