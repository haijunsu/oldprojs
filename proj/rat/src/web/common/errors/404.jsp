<%@ include file="/common/include.jsp" %>

<page:applyDecorator name="default">

<title><fmt:message key="404.title"/></title>
<content tag="heading"><fmt:message key="404.title"/></content>

<p>
    <fmt:message key="404.message">
        <fmt:param><c:url value="/mainMenu.html"/></fmt:param>
    </fmt:message>
</p>
<p style="text-align: center; margin-top: 20px">
    <img style="border: 0"
        src="<c:url value="/common/images/404.jpg"/>"
        alt="Emerald Lake - Western Canada" />
</p>
</page:applyDecorator>