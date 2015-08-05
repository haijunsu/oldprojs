<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
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
		<META HTTP-EQUIV="Content-Style-Type" content="text/css">
		<META name="GENERATOR" content="IBM WebSphere Studio">
		<LINK href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css">
		
		<TITLE>
				选择画面
		</TITLE>
	</HEAD>
	<BODY>
		<form>
		<script language="javascript" >	
		
			var thisform = window.dialogArguments;
			
			var fieldid = thisform.flag.value +"id" ;
			
			var fieldshow = thisform.flag.value +"show" ;
			
			window.returnValue="";
			

			if((thisform[fieldid]==null) || (thisform[fieldid].length==null)){
				window.close();
			}
				
			if(thisform[fieldid].length==2){
				window.returnValue=thisform[fieldid][1].value;
				if (thisform[fieldshow]!=null){
					window.returnValue=window.returnValue+"&"+thisform[fieldshow][1].value;
				}
				
				window.close();
			}
			
			
		
			document.write("<br><center><H2>"+thisform[fieldid][0].value+"</H2></center>");
			
			document.write("<table align='center' width='50%' cellspacing='0' cellpadding='1' class='table2border'>");
			document.write("<tbody id='DynData'>");
			
			for (var intFor = 1; intFor < thisform[fieldid].length; intFor++) {
				document.write("<tr align='center'>");
				document.write("<td class='table2textcentertd'><input type='text' name='rowID' size='16' readonly class='nright' value='"+thisform[fieldid][intFor].value+"' ></td>" );
				if (thisform[fieldshow]!=null)
					document.write("<td class='table2textcentertd'><input type='text' name='rowshow' size='16' readonly class='nright' value='"+thisform[fieldshow][intFor].value+"' ></td>" );
				
				document.write("<td><input type='button' name='butselect' value='选择' onclick ='exitform(this.form,"+ (intFor - 1) +")'></td>" );
				document.write("</tr>");
			}
			
			document.write("</tbody></table><br>");
			document.write("<center><input type='button' name='butesc' value='退出' onclick ='window.close()'></center><br>");
			
			
			

		</script>
		</form>
	</BODY>
	
	<script language="javascript" >
		
		//选择函数
		function exitform(thisform,index){
			
			window.returnValue=thisform.rowID[index].value;
			if (thisform.rowshow!=null){
				window.returnValue=window.returnValue+"&"+thisform.rowshow[index].value;
			}	
			window.close();
		}				
		
	</script>
</html:html>
