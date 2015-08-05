<%@ page contentType="text/html;charset=GB2312" language="java" %>

<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>



<logic:notEmpty name="user">
	<a href="post.do?method=addPost">发帖</a>&nbsp;&nbsp;
	<a href="user.do?method=logoff">注销</a>
</logic:notEmpty>




<table cellspacing="1" cellpadding="5" class="listUser">
      <tr>
        <th scope="col" class="listUser">&nbsp;</th>
        <th scope="col" class="listUser">最近注册的用户</th>
      </tr>
	
	  <logic:iterate id="dto" name="dtos">
		  <tr>
			<td width="17" class="listUser">
				<img src="<%=request.getContextPath()%>/images/maleIcon.gif" width="17" height="14">
			</td>
			
			<td class="listUser">
				<a href='post.do?method=list&id=<bean:write name="dto" property="id"/>'>
					[ <bean:write name="dto" property="name"/> ] 的Blog
				</a>
			</td>
		  </tr>		  
	  </logic:iterate>
</table>
		
	


