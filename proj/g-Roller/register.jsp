<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri='/WEB-INF/taglib/struts-tiles.tld' prefix='tiles' %>

<tiles:insert page="/tiles/template.jsp" >
  <tiles:put name="title" value="ÓÃ»§×¢²á"/>	
  <tiles:put name="top" value="/tiles/topContent.jsp" />
  <tiles:put name="header" value="/tiles/headerContent.jsp" />
  <tiles:put name="menu" value="/tiles/menuContent.jsp" />
  <tiles:put name="content" value="/registerContent.jsp" />
  <tiles:put name="footer" value="/tiles/footerContent.jsp" />
</tiles:insert>