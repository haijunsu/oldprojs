<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
%>
<html:html locale="true">

<HEAD>

<META HTTP-EQUIV="Cache-Control" CONTENT="No-cache">
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<META name="XIAOAI" content="Eclipse">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>查询条件</TITLE>
<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			

<script language="Javascript1.2">

	function callfun(flaga){
		if (flaga=='search'){
			//CommSearchForm.submit();
		}
		CommSearchForm.flag.value=flaga;
//		alert (CommSearchForm.flag.value);
		CommSearchForm.submit();
	}
	
				//选择代码函数
	function selectfield(thistext,fields){
		if(selectvalue(thistext,fields)){
		}
	}

	function callpagenext(s_nowpage){
//		alert ("向后");
		CommSearchForm.nowpage.value=s_nowpage;
//		alert (CommSearchForm.nowpage.value);
		CommSearchForm.nowoncepage.value=CommSearchForm.nowoncepage.value * 1 + 1;
//		alert (CommSearchForm.nowoncepage.value);
		CommSearchForm.submit();
	}
	function callpageprew(s_nowpage){
//		alert ("向前");
		CommSearchForm.nowpage.value=s_nowpage;
//		alert (CommSearchForm.nowpage.value);
		CommSearchForm.nowoncepage.value=CommSearchForm.nowoncepage.value * 1 - 1;
//		alert (CommSearchForm.nowoncepage.value);
		CommSearchForm.submit();
	}
	function callpagenow(s_nowpage){
//		alert ("当前页");
		CommSearchForm.nowpage.value=s_nowpage;
//		alert (CommSearchForm.nowpage.value);
//		CommSearchForm.nowoncepage.value=CommSearchForm.nowoncepage.value * 1 - 1;
//		alert (CommSearchForm.nowoncepage.value);
		CommSearchForm.submit();
	}
	
</script>

</HEAD>
<BODY>
<center><H2><bean:write name="CommSearchForm" property="title"/>查询</H2></center>
<%java.util.Calendar c =java.util.Calendar.getInstance();%>
<%String s_now = "";%>
<%s_now = Integer.toString(c.get(java.util.Calendar.YEAR));%>
<%s_now = s_now + "-";%>
<%s_now = s_now + Integer.toString(c.get(java.util.Calendar.MONTH) + 1);%>
<%s_now = s_now + "-";%>
<%s_now = s_now + Integer.toString(c.get(java.util.Calendar.DATE));%>
<%s_now = s_now + " ";%>
<%s_now = s_now + Integer.toString(c.get(java.util.Calendar.HOUR_OF_DAY));%>
<%s_now = s_now + ":";%>
<%s_now = s_now + Integer.toString(c.get(java.util.Calendar.MINUTE));%>
<%s_now = s_now + ":";%>
<%s_now = s_now + Integer.toString(c.get(java.util.Calendar.SECOND));%>
<%s_now = s_now + ".";%>
<%s_now = s_now + Integer.toString(c.get(java.util.Calendar.MILLISECOND));%>
<CENTER><% out.println(s_now); %></CENTER>
<jsp:useBean  id="csf" scope="request" class="commsearch.CommSearchForm"/>



<html:form action="/commsearch">

	<html:hidden property="flag" value="search"/>
	<html:hidden property="page" />
	<html:hidden property="searchid" />
	<html:hidden property="queryid" />
	<html:hidden property="queryiddis" />
	<html:hidden property="actiondo" />
	<html:hidden property="nowpage" />
	<html:hidden property="nowoncepage" />

	<%String s_link="";%>
	<%String pname="";%>
	<%String pname2="";%>
	
	<logic:present name="CommSearchForm" property="fieldsshow">
		<logic:iterate name="CommSearchForm" id="fieldsshow" property="fieldsshow" indexId="index">
			<input type="hidden" name="fieldsshow" value='<%=fieldsshow%>'> 
		</logic:iterate>
	</logic:present>
	<logic:present name="CommSearchForm" property="fieldsid">
		<logic:iterate name="CommSearchForm" id="fieldsid" property="fieldsid" indexId="index">
			<input type="hidden" name="fieldsid" value='<%=fieldsid%>'> 
		</logic:iterate>
	</logic:present>
	<logic:present name="CommSearchForm" property="fieldstype">
		<logic:iterate name="CommSearchForm" id="fieldstype" property="fieldstype" indexId="index">
			<input type="hidden" name="fieldstype" value='<%=fieldstype%>'> 
		</logic:iterate>
	</logic:present>
	<logic:present name="CommSearchForm" property="fieldskey">
		<logic:iterate name="CommSearchForm" id="fieldskey" property="fieldskey" indexId="index">
			<input type="hidden" name="fieldskey" value='<%=fieldskey%>'> 
		</logic:iterate>
	</logic:present>
	<logic:present name="CommSearchForm" property="conditionshow">
		<logic:iterate name="CommSearchForm" id="conditionshow" property="conditionshow" indexId="index">
			<input type="hidden" name="conditionshow" value='<%=conditionshow%>'> 
		</logic:iterate>
	</logic:present>
	<logic:present name="CommSearchForm" property="conditionid">
		<logic:iterate name="CommSearchForm" id="conditionid" property="conditionid" indexId="index">
			<input type="hidden" name="conditionid" value='<%=conditionid%>'> 
		</logic:iterate>
	</logic:present>
	<logic:present name="CommSearchForm" property="relationshow">
		<logic:iterate name="CommSearchForm" id="relationshow" property="relationshow" indexId="index">
			<input type="hidden" name="relationshow" value='<%=relationshow%>'> 
		</logic:iterate>
	</logic:present>
	<logic:present name="CommSearchForm" property="relationid">
		<logic:iterate name="CommSearchForm" id="relationid" property="relationid" indexId="index">
			<input type="hidden" name="relationid" value='<%=relationid%>'> 
		</logic:iterate>
	</logic:present>

	<logic:equal name="CommSearchForm" property="searchid" value="1">
		<TABLE  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
			<THEAD>
			<TR>
				<TH class="table1title">查询项目</TH>
				<TH class="table1title">查询条件</TH>
				<TH class="table1title">查询内容</TH>
			</TR>
			</THEAD>
			<TBODY id="DynData">
			<TR>
				<TD class="table2textrighttd" nowrap>
					<html:hidden property="fields1id"/>
					<html:text property="fields1show" onclick="selectvalue(this.form,'','fields1','fields')" />
				</TD>
				<TD class="table2textcentertd" nowrap>
					<html:hidden property="condition1id"/>			
					<html:text property="condition1show" onclick="selectvalue(this.form,'','condition1','condition')"/>
				</TD>
				<TD class="table2textlefttd" nowrap>
					<html:text property="thing1" styleClass="n2right"/>
				</TD>
			</TR>
			<TR>
				<TD  class="table2textcentertd" nowrap></TD>
				<TD class="table2textcentertd" nowrap>
					<html:hidden property="relation1id"/>
					<html:text property="relation1show" onclick="selectvalue(this.form,'','relation1','relation')"/>
				</TD>
				<TD  class="table2textcentertd" nowrap></TD>
			</TR>
			<TR>
				<TD class="table2textrighttd" nowrap>(
					<html:hidden property="fields2id"/>
					<html:text property="fields2show" onclick="selectvalue(this.form,'','fields2','fields')"/>
				</TD>
				<TD class="table2textcentertd" nowrap>
					<html:hidden property="condition2id"/>
					<html:text property="condition2show" onclick="selectvalue(this.form,'','condition2','condition')"/>
				</TD>
				<TD class="table2textlefttd" nowrap>
					<html:text property="thing2" styleClass="n2right"/>
				</TD>
			</TR>
			<TR>
				<TD class="table2textcentertd" nowrap></TD>
				<TD class="table2textcentertd" nowrap>
					<html:hidden property="relation2id"/>
					<html:text property="relation2show" onclick="selectvalue(this.form,'','relation2','relation')"/>
				</TD>
				<TD class="table2textcentertd" nowrap></TD>
			</TR>
			<TR>
				<TD class="table2textrighttd" nowrap>
					<html:hidden property="fields3id"/>
					<html:text property="fields3show" onclick="selectvalue(this.form,'','fields3','fields')"/>
				</TD>
				<TD class="table2textcentertd" nowrap>
					<html:hidden property="condition3id"/>
					<html:text property="condition3show" onclick="selectvalue(this.form,'','condition3','condition')"/>
				</TD>
				<TD class="table2textlefttd" nowrap>
					<html:text property="thing3" styleClass="n2right"/>)
				</TD>
			</TR>
			<TR>
				<TD class="table2textcentertd" nowrap></TD>
					<TD class="table2textcentertd" nowrap>
						<html:button property="buttoncommit" onclick ="callfun('search')">查询</html:button>
						<html:button property="buttonquit" onclick ="callfun('quit')">退出</html:button>
					</TD>
				<TD class="table2textcentertd" nowrap></TD>	
			</TR>
			</TBODY>
		</TABLE>
		<BR>
	
		<logic:equal name="CommSearchForm" property="page" value="2">
			<logic:notPresent name="CommSearchForm" property="data">
				<center>查询无结果</center>
			</logic:notPresent>
			<logic:present name="CommSearchForm" property="data">
				<Center><bean:write name="CommSearchForm" property="title"/>(<bean:write name="CommSearchForm" property="rowcount"/>)</Center>

				<logic:present name="CommSearchForm" property="pagelist">
					
					<TABLE>
					<TR>
					<TD>
						当前第<bean:write name="CommSearchForm" property="nowpage"/>页
					</TD>
					<logic:present name="CommSearchForm" property="prewOncePage">
						<TD>
							<A href=javascript:callpageprew('<bean:write name="CommSearchForm" property="prewOncePage"/>')>
								上十页
							</A>	
						</TD>
					</logic:present>
					<logic:iterate id="row" name="CommSearchForm" property="pagelist" indexId="index">
						<TD>
							<A href=javascript:callpagenow('<bean:write name="row" property="list"/>')>
							<!--A href='<bean:write name="row" property="list"/>'-->
								<bean:write name="row" property="number"/>
							</A>	
						</TD>
					</logic:iterate>
					<logic:present name="CommSearchForm" property="nextOncePage">
						<TD>
							<A href=javascript:callpagenext('<bean:write name="CommSearchForm" property="nextOncePage"/>')>
								下十页
							</A>	
						</TD>
					</logic:present>

					<TR>
					</TABLE>
				</logic:present>
				
								
				<TABLE  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
					<TR align="center">
						<logic:present name="CommSearchForm" property="datakey">
							<TD class="table2titletd">修改</TD>
						</logic:present>
						<logic:iterate id="row" name="CommSearchForm" property="datatitle" indexId="index">
							<TD class="table2titletd" NOWRAP>
								<bean:write name="CommSearchForm" property='<%="datatitle[" + index + "]"%>'/>
							</TD>
						</logic:iterate>
					</TR>
					<logic:iterate id="row" name="CommSearchForm" property="data" indexId="indexrow">
						<TR align="center">
							<logic:present name="CommSearchForm" property="datakey">
								<TD class="table2textcentertd" NOWRAP>
									<%s_link="datakey[" + indexrow + "]";%>
									<A href='<bean:write name="CommSearchForm" property='<%=s_link%>'/>'>
										修改
									</A>
								</TD>
							</logic:present>		
							<%pname = "data[" + indexrow + "]";%>
							<logic:iterate id="col" name="CommSearchForm" property="<%=pname%>" indexId="indexcol">
								<% if (col.equals("")) {%>
									<TD class="table2textrighttd" NOWRAP>&nbsp</TD>
								<% } else {%>
									<TD class="table2textrighttd" NOWRAP><%=col%></TD>
								<%}%>
							</logic:iterate>
	
							
						</TR>
					</logic:iterate>
					
				</TABLE>
				<center><bean:write name="CommSearchForm" property="endtitle"/></center>
			</logic:present>
		</logic:equal>
	</logic:equal>


	<logic:equal name="CommSearchForm" property="searchid" value="2">
		<logic:equal name="CommSearchForm" property="page" value="1">	
			<TABLE  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
				<THEAD>
				<TR>
					<TH class="table1title">查询项目</TH>
					<TH class="table1title">查询条件</TH>
					<TH class="table1title">查询内容</TH>
				</TR>
				</THEAD>
				<TBODY id="DynData">
				<TR>
					<TD class="table2textrighttd" nowrap>
						<html:hidden property="fields1id"/>
						<html:text property="fields1show" onclick="selectvalue(this.form,'','fields1','fields')" />
					</TD>
					<TD class="table2textcentertd" nowrap>
						<html:hidden property="condition1id"/>			
						<html:text property="condition1show" onclick="selectvalue(this.form,'','condition1','condition')"/>
					</TD>
					<TD class="table2textlefttd" nowrap>
						<html:text property="thing1" styleClass="n2right"/>
					</TD>
				</TR>
				<TR>
					<TD  class="table2textcentertd" nowrap></TD>
					<TD class="table2textcentertd" nowrap>
						<html:hidden property="relation1id"/>
						<html:text property="relation1show" onclick="selectvalue(this.form,'','relation1','relation')"/>
					</TD>
					<TD  class="table2textcentertd" nowrap></TD>
				</TR>
				<TR>
					<TD class="table2textrighttd" nowrap>(
						<html:hidden property="fields2id"/>
						<html:text property="fields2show" onclick="selectvalue(this.form,'','fields2','fields')"/>
					</TD>
					<TD class="table2textcentertd" nowrap>
						<html:hidden property="condition2id"/>
						<html:text property="condition2show" onclick="selectvalue(this.form,'','condition2','condition')"/>
					</TD>
					<TD class="table2textlefttd" nowrap>
						<html:text property="thing2" styleClass="n2right"/>
					</TD>
				</TR>
				<TR>
					<TD class="table2textcentertd" nowrap></TD>
					<TD class="table2textcentertd" nowrap>
						<html:hidden property="relation2id"/>
						<html:text property="relation2show" onclick="selectvalue(this.form,'','relation2','relation')"/>
					</TD>
					<TD class="table2textcentertd" nowrap></TD>
				</TR>
				<TR>
					<TD class="table2textrighttd" nowrap>
						<html:hidden property="fields3id"/>
						<html:text property="fields3show" onclick="selectvalue(this.form,'','fields3','fields')"/>
					</TD>
					<TD class="table2textcentertd" nowrap>
						<html:hidden property="condition3id"/>
						<html:text property="condition3show" onclick="selectvalue(this.form,'','condition3','condition')"/>
					</TD>
					<TD class="table2textlefttd" nowrap>
						<html:text property="thing3" styleClass="n2right"/>)
					</TD>
				</TR>
				<TR>
					<TD class="table2textcentertd" nowrap></TD>
						<TD class="table2textcentertd" nowrap>
							<html:button property="buttoncommit" onclick ="callfun('search')">查询</html:button>
							<html:button property="buttonquit" onclick ="callfun('quit')">退出</html:button>
						</TD>
					<TD class="table2textcentertd" nowrap></TD>	
				</TR>
				</TBODY>
			</TABLE>
			<BR>
		</logic:equal>
		
		<logic:equal name="CommSearchForm" property="page" value="2">
			<logic:notPresent name="CommSearchForm" property="data">
				<center>查询无结果</center>
			</logic:notPresent>
			<logic:present name="CommSearchForm" property="data">
			<Center><bean:write name="CommSearchForm" property="title"/>(<bean:write name="CommSearchForm" property="rowcount"/>)</Center>
			<TABLE  align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
				<TR align="center">
					<logic:iterate id="row" name="CommSearchForm" property="datatitle" indexId="index">
						<TD class="table2titletd" NOWRAP>
							<bean:write name="CommSearchForm" property='<%="datatitle[" + index + "]"%>'/>
						</TD>
					</logic:iterate>
					<logic:present name="CommSearchForm" property="datakey">
						<TD class="table2titletd">修改</TD>
					</logic:present>
				</TR>
				<logic:iterate id="row" name="CommSearchForm" property="data" indexId="indexrow">
					<TR align="center">
						<%pname = "data[" + indexrow + "]";%>
						<logic:iterate id="col" name="CommSearchForm" property="<%=pname%>" indexId="indexcol">
							<% if (col.equals("")) {%>
								<TD class="table2textrighttd" NOWRAP>&nbsp</TD>
							<% } else {%>
								<TD class="table2textrighttd" NOWRAP><%=col%></TD>
							<%}%>
						</logic:iterate>
						<logic:present name="CommSearchForm" property="datakey">
							<TD class="table2textcentertd" NOWRAP>
								<%s_link="datakey[" + indexrow + "]";%>
								<A href='<bean:write name="CommSearchForm" property='<%=s_link%>'/>'>
									修改
								</A>
							</TD>
						</logic:present>		
						
					</TR>
				</logic:iterate>
				
			</TABLE>
			<center><bean:write name="CommSearchForm" property="endtitle" filter="false"/></center>
			</logic:present>
		</logic:equal>
	</logic:equal>
	
</html:form>
</BODY>
</html:html>
