<%@ include file="/common/include.jsp"%>

<c:if test="${pageContext.request.locale.language != 'en'}">
    <div id="switchLocale"><a href="<c:url value='/?locale=en'/>"><fmt:message key="webapp.name"/> in English</a></div>
</c:if>

<div id="branding">
    <h1><a href="<c:url value="/"/>"><fmt:message key="webapp.name"/></a></h1>
</div>
<hr />

