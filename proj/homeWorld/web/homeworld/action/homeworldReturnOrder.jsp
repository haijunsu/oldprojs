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
			家世界连锁商业集团有限公司
		</TITLE>
	</HEAD>
    <body>
    
	<html:errors property="errormessage"/>
	 <html:form action="homeworldReturnOrder" >
	 
	
	
    <br><center><H2>定单回复</H2></center><center>
	 
	 
	         <html:hidden property="queryid" />
             <html:hidden property="selectwhere" />
             
              <html:hidden property="epoflg" />
              <html:hidden property="flag" />
			  <html:hidden property="freeformstate" />
             <html:hidden property="nowdate"/>
             <html:hidden property="epoqdt"/>
             <html:hidden property="message"/>
                 
    <%
    	String change1="";
    	String change2="";
    	String change3="";
    %>

    <logic:equal  name="homeworldReturnOrderForm"  property="epoflg" value="0">										
	
    <%
    	change1="onclick='change(this)'";
    	change2="onclick='change2(this.form)'";
    	change3="onclick='change3(this.form)'";
    %>
	
	</logic:equal>
	
	<input type="hidden" name="timeshow" value='00:00'>  <input type="hidden" name="timeid" value='选择时间'> 
	<input type="hidden" name="timeshow" value='00:00'>  <input type="hidden" name="timeid" value='零点零分'> 
	<input type="hidden" name="timeshow" value='00:30'>  <input type="hidden" name="timeid" value='零点三十分'> 
	<input type="hidden" name="timeshow" value='01:00'>  <input type="hidden" name="timeid" value='一点零分'> 
	<input type="hidden" name="timeshow" value='01:30'>  <input type="hidden" name="timeid" value='一点三十分'> 
	<input type="hidden" name="timeshow" value='02:00'>  <input type="hidden" name="timeid" value='两点零分'> 
	<input type="hidden" name="timeshow" value='02:30'>  <input type="hidden" name="timeid" value='两点三十分'> 
	<input type="hidden" name="timeshow" value='03:00'>  <input type="hidden" name="timeid" value='三点零分'> 
	<input type="hidden" name="timeshow" value='03:30'>  <input type="hidden" name="timeid" value='三点三十分'> 
	<input type="hidden" name="timeshow" value='04:00'>  <input type="hidden" name="timeid" value='四点零分'> 
	<input type="hidden" name="timeshow" value='04:30'>  <input type="hidden" name="timeid" value='四点三十分'> 
	<input type="hidden" name="timeshow" value='05:00'>  <input type="hidden" name="timeid" value='五点零分'> 
	<input type="hidden" name="timeshow" value='05:30'>  <input type="hidden" name="timeid" value='五点三十分'> 
	<input type="hidden" name="timeshow" value='06:00'>  <input type="hidden" name="timeid" value='六点零分'> 
	<input type="hidden" name="timeshow" value='06:30'>  <input type="hidden" name="timeid" value='六点三十分'> 
	<input type="hidden" name="timeshow" value='07:00'>  <input type="hidden" name="timeid" value='七点零分'> 
	<input type="hidden" name="timeshow" value='07:30'>  <input type="hidden" name="timeid" value='七点三十分'> 
	<input type="hidden" name="timeshow" value='08:00'>  <input type="hidden" name="timeid" value='八点零分'> 
	<input type="hidden" name="timeshow" value='08:30'>  <input type="hidden" name="timeid" value='八点三十分'> 
	<input type="hidden" name="timeshow" value='09:00'>  <input type="hidden" name="timeid" value='九点零分'> 
	<input type="hidden" name="timeshow" value='09:30'>  <input type="hidden" name="timeid" value='九点三十分'> 
	<input type="hidden" name="timeshow" value='10:00'>  <input type="hidden" name="timeid" value='十点零分'> 
	<input type="hidden" name="timeshow" value='10:30'>  <input type="hidden" name="timeid" value='十点三十分'> 
	<input type="hidden" name="timeshow" value='11:00'>  <input type="hidden" name="timeid" value='十一点零分'> 
	<input type="hidden" name="timeshow" value='11:30'>  <input type="hidden" name="timeid" value='十一点三十分'> 
	<input type="hidden" name="timeshow" value='12:00'>  <input type="hidden" name="timeid" value='十二点零分'> 
	<input type="hidden" name="timeshow" value='12:30'>  <input type="hidden" name="timeid" value='十二点三十分'> 
	<input type="hidden" name="timeshow" value='13:00'>  <input type="hidden" name="timeid" value='十三点零分'> 
	<input type="hidden" name="timeshow" value='13:30'>  <input type="hidden" name="timeid" value='十三点三十分'> 
	<input type="hidden" name="timeshow" value='14:00'>  <input type="hidden" name="timeid" value='十四点零分'> 
	<input type="hidden" name="timeshow" value='14:30'>  <input type="hidden" name="timeid" value='十四点三十分'> 
	<input type="hidden" name="timeshow" value='15:00'>  <input type="hidden" name="timeid" value='十五点零分'> 
	<input type="hidden" name="timeshow" value='15:30'>  <input type="hidden" name="timeid" value='十五点三十分'> 
	<input type="hidden" name="timeshow" value='16:00'>  <input type="hidden" name="timeid" value='十六点零分'> 
	<input type="hidden" name="timeshow" value='16:30'>  <input type="hidden" name="timeid" value='十六点三十分'> 
	<input type="hidden" name="timeshow" value='17:00'>  <input type="hidden" name="timeid" value='十七点零分'> 
	<input type="hidden" name="timeshow" value='17:30'>  <input type="hidden" name="timeid" value='十七点三十分'> 
	<input type="hidden" name="timeshow" value='18:00'>  <input type="hidden" name="timeid" value='十八点零分'> 
	<input type="hidden" name="timeshow" value='18:30'>  <input type="hidden" name="timeid" value='十八点三十分'> 
	<input type="hidden" name="timeshow" value='19:00'>  <input type="hidden" name="timeid" value='十九点零分'> 
	<input type="hidden" name="timeshow" value='19:30'>  <input type="hidden" name="timeid" value='十九点三十分'> 
	<input type="hidden" name="timeshow" value='20:00'>  <input type="hidden" name="timeid" value='二十点零分'> 
	<input type="hidden" name="timeshow" value='20:30'>  <input type="hidden" name="timeid" value='二十点三十分'> 
	<input type="hidden" name="timeshow" value='21:00'>  <input type="hidden" name="timeid" value='二十一点零分'> 
	<input type="hidden" name="timeshow" value='21:30'>  <input type="hidden" name="timeid" value='二十一点三十分'> 
	<input type="hidden" name="timeshow" value='22:00'>  <input type="hidden" name="timeid" value='二十二点零分'> 
	<input type="hidden" name="timeshow" value='22:30'>  <input type="hidden" name="timeid" value='二十二点三十分'> 
	<input type="hidden" name="timeshow" value='23:00'>  <input type="hidden" name="timeid" value='二十三点零分'> 
	<input type="hidden" name="timeshow" value='23:30'>  <input type="hidden" name="timeid" value='二十三点三十分'> 
	
     <table align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
		<thead>
		<tr align="center">		
        <td class="table1title"  nowrap>定单编号</td>
        <td class="table1title"  nowrap>商店</td>
        <td class="table1title"  nowrap>商店名</td>
        <td class="table1title"  nowrap>期待收货日期</td>
        <td class="table1title"  nowrap>收货日期</td>
        <td class="table1title"  nowrap>收货起时间</td>
        <td class="table1title"  nowrap>收货止时间</td>
        <td class="table1title"  nowrap>操作人</td>
        <td class="table1title"  nowrap>操作日期</td>
        <td class="table1title"  nowrap>操作时间</td>
		</tr>
		</thead>
    	<tbody id="DynData">	 
    	<tr align="center">
    	
		<td class="table2textrighttd" nowrap> <input type="text" name="eponum"  size="10" class="n2centernone" readonly="true"
            value="<bean:write name='homeworldReturnOrderForm' property='eponum'/>" > 
        </td>
		<td class="table2textrighttd" nowrap> <input type="text" name="epostr"  size="6" class="n2centernone" readonly="true"
            value="<bean:write name='homeworldReturnOrderForm' property='epostr'/>" > 
        </td>
		<td class="table2textrighttd" nowrap> <input type="text" name="epostn"  size="12" class="n2centernone" readonly="true"
            value="<bean:write name='homeworldReturnOrderForm' property='epostn'/>" > 
        </td>
		<td class="table2textrighttd" nowrap> <input type="text" name="eposdt"  size="10" class="n2centernone" readonly="true"
            value="<bean:write name='homeworldReturnOrderForm' property='eposdt'/>" > 
        </td>
		<td class="table2textrighttd" nowrap> <input type="text" name="epordt"  size="10" class="n1right" readonly='true'  <%=change1%>
            value="<bean:write name='homeworldReturnOrderForm' property='epordt'/>" > 
        </td>
		<td class="table2textrighttd" nowrap> <input type="text" name="epordtmshow"  size="8" class="n1right" readonly='true'  <%=change2%>
            value="<bean:write name='homeworldReturnOrderForm' property='epordtmshow'/>" > 
             <input type="hidden" name="epordtmid" > 
        </td>
		<td class="table2textrighttd" nowrap> <input type="text" name="epordtm2show"  size="8" class="n1right" readonly='true' <%=change3%>
            value="<bean:write name='homeworldReturnOrderForm' property='epordtm2show'/>" > 
            <input type="hidden" name="epordtm2id" > 
        </td>
		<td class="table2textrighttd" nowrap> <input type="text" name="operatorshow"  size="8" class="n2centernone" readonly="true"
            value="<bean:write name='homeworldReturnOrderForm' property='operatorshow'/>" > 
            <input type="hidden" name="operatorid" value="<bean:write name='homeworldReturnOrderForm' property='operatorid'/>" > 
        </td>
		<td class="table2textrighttd" nowrap> <input type="text" name="operdate"  size="10" class="n2centernone" readonly="true"
            value="<bean:write name='homeworldReturnOrderForm' property='operdate'/>" > 
        </td>
		<td class="table2textrighttd" nowrap> <input type="text" name="opertime"  size="8" class="n2centernone" readonly="true"
            value="<bean:write name='homeworldReturnOrderForm' property='opertime'/>" > 
        </td>
     </tr>
     </table>
     <br>
     
    <center>     
    
    <logic:equal  name="homeworldReturnOrderForm"  property="epoflg" value="0">										
	     <html:button property="button1" onclick="submitform(this.form)">提交</html:button>
	</logic:equal>
    
        <html:button property="button2" onclick="returnform1(this.form)">返回订单</html:button>         
      
    </center>
   </html:form>


   <script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
   <script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
   <script language="Javascript">
   
	if(document.forms[0].message.value=="1"){
		alert("保存成功！！");
	}
   
	function change2(thisform){
	     selectvalue(thisform,'','epordtm','time');
		 freeformchange(thisform);
	}
	
	function change3(thisform){
	    selectvalue(thisform,'','epordtm2','time');
	  freeformchange(thisform);
	}
	
	function change(thiscell){
		var select=window.showModalDialog("<%=request.getContextPath()%>/system/calendar.jsp","","dialogHeight:300px;dialogWidth:400px " );
		if (select!=""){
			thiscell.value=select;
			freeformchange(thiscell.form);
		}			
	}
	
    function submitform(thisform){
		 if(thisform.epordt.value < thisform.nowdate.value){
			alert("收货日期 应大于等于 当前日期！");
			return;
		}
		
		
		 if(thisform.epordt.value > thisform.epoqdt.value){
			alert("收货日期 应小于等于 取消日期！");
			return;
		}
		
		 if(thisform.epordtmshow.value > thisform.epordtm2show.value){
			alert("收货止时间 应不小于 收货起时间！");
			return;
		}
		

		
		thisform.flag.value="subomit";
        thisform.submit();
        return;
    }
      
  function returnform1(thisform){
          var url="homeworldOrder.do?queryid="+thisform.queryid.value+"&selectwhere=" + thisform.selectwhere.value +""
          window.location=url;
  }
  
   </script>
 </body>
</html:html>
