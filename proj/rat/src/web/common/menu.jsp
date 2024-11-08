<%@ include file="/common/include.jsp"%>

<menu:useMenuDisplayer name="Velocity"
	config="/common/menutpl/cssHorizontalMenu.vm"
	permissions="rolesAdapter">
	<ul id="primary-nav" class="menuList">
		<li class="pad">&nbsp;</li>
		<c:if test="${empty pageContext.request.remoteUser}">
			<li><a href="<c:url value="/common/login.jsp"/>" class="current"> <fmt:message
				key="login.title" /></a></li>
		</c:if>
		<menu:displayMenu name="MainMenu" />
		<menu:displayMenu name="UserMenu" />
		<menu:displayMenu name="FileUpload" />
		<menu:displayMenu name="AdminMenu" />
		<menu:displayMenu name="Logout" />
	</ul>
</menu:useMenuDisplayer>
