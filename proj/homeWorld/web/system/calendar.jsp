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
		<br><br>
		<TITLE>
				选择日期 
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
		<html:form action="/SelectOne" >
		<center><H2>
		选择日期          
		</H2>
		<br><br>
		
年<select size="1" name="year" onchange="RefreshDay(this.form)">     
	<option>1995</option>
	<option>1996</option>
	<option>1997</option>
	<option>1998</option>
	<option>1999</option>
	<option>2000</option>
	<option>2001</option>
	<option>2002</option>
    <option>2003</option>
    <option>2004</option>
    <option>2005</option>
    <option>2006</option>
    <option>2007</option>
    <option>2008</option>
    <option>2009</option>
    <option>2010</option>
    <option>2011</option>
    <option>2012</option>
    <option>2013</option>
    <option>2014</option>
    <option>2015</option>
    <option>2016</option>
    <option>2017</option>
    <option>2018</option>
    <option>2019</option>
    <option>2020</option>
  </select>
  
  月<select size="1" name="month" onchange="RefreshDay(this.form)">                               
    <option>01</option>
    <option>02</option>
    <option>03</option>
    <option>04</option>
    <option>05</option>
    <option>06</option>
    <option>07</option>
    <option>08</option>
    <option>09</option>
    <option>10</option>
    <option>11</option>
    <option>12</option>
  </select>
  
  日<select size="1" name="day">                               
	
  </select>

			<br>	<br>
		<input type="button" value="确定" name="b1" onclick ="change(this.form)">		
		<input type="button" value="取消" name="b2" onclick ="change2(this.form)"> 
			
</center>
		
			<script language="JavaScript">
	
			var dd=new Date();
			document.forms[0].year.selectedIndex=dd.getFullYear()-document.forms[0].year.options[0].text;
			document.forms[0].month.selectedIndex=(dd.getMonth()+1)-document.forms[0].month.options[0].text;
			RefreshDay(document.forms[0]);
			document.forms[0].day.selectedIndex=dd.getDate()-document.forms[0].day.options[0].text;
			
		    window.returnValue="";
		   
		   	function change(thisform){
		   	
				var temp=thisform.year.options[thisform.year.selectedIndex].text;
				
				temp=temp+"-";
				temp=temp+thisform.month.options[thisform.month.selectedIndex].text;
				temp=temp+"-";
				temp=temp+thisform.day.options[thisform.day.selectedIndex].text;
				window.returnValue=temp;
				window.close();
			}
			
			function change2(thisform){
			   window.close();
		  	   return;			
			}
			
			
		 /************** 刷新天数下拉框 *********************/	
		  function RefreshDay(thisform) {
		  
		  	var a=thisform.year.options[thisform.year.selectedIndex].text;
			var b= thisform.month.options[thisform.month.selectedIndex].text;
			
			var max=MonthLen(a,b);
		
			thisform.day.options.length=0;
			thisform.day.options.length=max;
			var ss="";
			
			for(var i=0;i<max;i++){
				ss="0"+(i+1);
				thisform.day.options[i].text=ss.substr(ss.length-2);
			}
				
		  }
		  
		/************** 取得月份共有几天 *********************/
		  function MonthLen(theYear, theMonth) {
		  
		   theMonth--;
		   var oneDay = 1000 * 60 * 60 * 24;
		   var thisMonth = new Date(theYear, theMonth, 1);
		   var nextMonth = new Date(theYear, theMonth + 1, 1);
		   var len = Math.ceil((nextMonth.getTime() - thisMonth.getTime())/oneDay);
		   return len;
		   
		  }
		
			</script>			
		</html:form>
	</BODY>
</html:html>
