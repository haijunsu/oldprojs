<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312" %>
<html:html locale="true">
	
	<%
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
	%>
	<HEAD>
		<META HTTP-EQUIV="Cache-Control" CONTENT="No-cache">
		<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
		
		<META HTTP-EQUIV="Content-Type" content="text/html; charset=GB2312">
		<META HTTP-EQUIV="Content-Style-Type" content="text/css">
		<META name="GENERATOR" content="IBM WebSphere Studio">
		<LINK href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css">
		<TITLE>
			回填模块	
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/SelectField">
		    <html:hidden property="cellcount" />
		    <html:hidden property="mark" />

			<center><H2>
				<bean:write name="SelectFieldForm" property="qtitle"/>
			</H2></center>
					    
		    <table id="reTable" align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
				<thead>

					<logic:present name="SelectFieldForm" property="title" >
					  <tr>
					  <th ></th>
   					  <logic:iterate id="title" name="SelectFieldForm" property="title" indexId="index">
   					  
	   					  	<logic:equal name="SelectFieldForm" property='<%="hiddend[" + index + "]"%>' value="1">	
						  		<th class="table2titletd"  nowrap><%=title%></th>
	   						</logic:equal>
							
							
					  </logic:iterate>
					  </tr>
					</logic:present>

				</thead>
				<tbody id="DynData">
				
					<logic:present name="SelectFieldForm" property="show" >
					  <logic:iterate id="rowshow" name="SelectFieldForm" property="show" indexId="indexrow">
					    <tr align="center">
						  <td><input type="button" name="butnext" value="选择" onclick ="changeform(this)"></td>
				       	  <logic:iterate id="col" name="SelectFieldForm" property='<%="show[" + indexrow + "]"%>' indexId="indexcol">
				
			   				  <logic:equal name="SelectFieldForm" property='<%="hiddend[" + indexcol + "]"%>' value="1">	
							       	  <td class="table2textcentertd" nowrap><input type="text" name="show" class="n2centernone" size=<bean:write name="SelectFieldForm" property='<%="size[" + indexcol + "]"%>'/> readonly="true" value='<%=col%>' ></td>
		   						</logic:equal>
			   				  <logic:equal name="SelectFieldForm" property='<%="hiddend[" + indexcol + "]"%>' value="0">	
							       	 <input type="hidden" name="show"  value='<%=col%>' >
		   						</logic:equal>
				
						  </logic:iterate>
						  
					    </tr>
					  </logic:iterate>
					</logic:present>
		 		</tbody>
			</table>
			<br>
			<br>
			<center><html:button property="butlast" onclick ="lastform(this.form)">退出</html:button> </center>
		      
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
		<script language="JavaScript">
			
			window.returnValue="";
			if(document.getElementById("mark").value=="1")
				window.returnValue="null";
				
			var show=document.getElementsByName("show");
			var cellcount=document.getElementById("cellcount").value;
			if((show[0]!=null)&&(show[cellcount]==null)){
				for(var i=0;i<cellcount;i++){
					returnValue=returnValue+"` "+show(i).value;	
				}			
				window.returnValue=returnValue.substring(2);
				window.close();
			}
				//alert(window.returnValue);
			function changeform(thisfield){
				var cellcount=thisfield.form.cellcount.value;
				var row=thisfield.parentNode.parentNode.rowIndex;			
				var num=(row-1)*cellcount;
				var returnValue="";
				for(var i=0;i<cellcount;i++){
					returnValue=returnValue+"` "+thisfield.form.show(num*1+i).value;
				}
				window.returnValue=returnValue.substring(2);
				window.close();
			}		
			
			function lastform(thisform){
				window.close();
			}
		</script>
		</html:form>
		
	</BODY>
</html:html>
