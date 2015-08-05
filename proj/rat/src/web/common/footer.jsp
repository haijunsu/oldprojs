<%@ include file="/common/include.jsp" %>

    <div id="divider"><div></div></div>
    <span class="left">Version @APPVERSION@ |
        <span id="validators">
            <a href="http://validator.w3.org/check?uri=referer">XHTML Valid</a> |
            <a href="http://jigsaw.w3.org/css-validator/validator-uri.html">CSS Valid</a>
        </span>
        <c:if test="${pageContext.request.remoteUser != null}">
        | <fmt:message key="user.status"/> <authz:authentication operation="fullName"/>
        </c:if>
    </span>
    <span class="right">
        &copy; @COPYRIGHT-YEAR@
    </span>
    <!-- Built on @BUILD-TIME@ -->
