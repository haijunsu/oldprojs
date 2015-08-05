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


<TITLE>订单查询</TITLE>
<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
<script language="javascript" src="<%=request.getContextPath()%>/commjs/calendar.js"></script>
<script language="Javascript1.2">
			
	var CalendarWebControl = new atCalendarControl();
	function clicka(thiscell){
		//选择日期
//		thiscell.value="";
//		CalendarWebControl.show(thiscell,thiscell.value);
		var select=window.showModalDialog("<%=request.getContextPath()%>/system/calendar.jsp","","dialogHeight:300px;dialogWidth:400px " );
		if (select!=""){
			thiscell.value=select;
			//freeformchange(thiscell.form);
		}	else {
			thiscell.value="";
		}		
	}   
	
	function tdclick(thiscell){
		if(thiscell.checked){
			thiscell.form.checkthing.value=thiscell.form.checkthing.value+"`"+ thiscell.value;
		}else{
			thiscell.form.checkthing.value= thiscell.form.checkthing.value.replace("`"+thiscell.value,"")  ;
		}
//		alert (thiscell.form.checkthing.value);
	}
	
	
	
	
	function callfunfp() {
		var st = "";
		st = trim(document.forms(0).checkthing.value);
		if (st==""){
		
		}else {
			//调用下一级了！
			document.forms(0).selectwhere.value = st.substring(1);
			//alert (document.forms(0).selectwhere.value);
			//换“`”
			document.forms(0).selectwhere.value = document.forms(0).selectwhere.value.replace(/`/g," ) OR (")  ;
			//换单引号
			document.forms(0).selectwhere.value = document.forms(0).selectwhere.value.replace(/&#39;/g,"'");
			document.forms(0).selectwhere.value =  "(" + document.forms(0).selectwhere.value  + ")"
			//加入一个标志为“1”
//			document.forms(0).selectwhere.value =  "(" + document.forms(0).selectwhere.value + ") AND eipflg='1'" 
			//alert (document.forms(0).selectwhere.value);

			//document.forms(0).selectwhere.value=st;
			document.forms(0).action=document.forms(0).actiondo.value;
			document.forms(0).target="_blank";
			document.forms(0).submit();
			document.forms(0).action="searchorder.do";
			document.forms(0).selectwhere.value="";
			document.forms(0).target="";
			
		}

		//SearchOrderForm.submit();
	}
	function callEpyhdrnext(stra,strb){
		if (strb=="FP"){
			//明细
			document.forms(0).selectwhere.value=stra;
			document.forms(0).action="homeworldEpydtl.do";
			document.forms(0).target="_blank";
	//		document.forms(0).flag.value="";
			document.forms(0).submit();
			document.forms(0).action=document.forms(0).actiondo.value;
			document.forms(0).selectwhere.value="";
			document.forms(0).target="";
		}
		if (strb=="FY"){
			//费用
			document.forms(0).selectwhere.value=stra;
			document.forms(0).action="homeworldEpyfee.do";
			document.forms(0).target="_blank";
	//		document.forms(0).flag.value="";
			document.forms(0).submit();
			document.forms(0).action=document.forms(0).actiondo.value;
			document.forms(0).selectwhere.value="";
			document.forms(0).target="";

		}
	}
	function clickb(thisa){
		//下拉窗口选择后，改一下条件
		SearchOrderForm.searchthing4.value = thisa.value;
		//alert(SearchOrderForm.searchthing4.value);
	}   
	function callchoosenext(swhere){
	
		//交易类型后的对应的类型
		//交易类型后的对应的类型
		var strLen=0,k=0;
		strLen=swhere.length-2;
		var pos;
		var newStr='';
		var tempStr='';
		var v=new Array(2);
   		 for(k=strLen;k>0;k--){
			newStr=newStr+swhere.substr(k,1);
		}	
	
	   	for(k=0;k<2;k++){
	   		pos=newStr.indexOf("'");
	   		if(k==1){
	   		    newStr=newStr.substr(pos+1);
	   		    pos=newStr.indexOf("'");
	   		}    
			tempStr=newStr.substr(0,pos);
			v[k]='';
			for(var j=tempStr.length;j>-1;j--){
				v[k]=v[k]+tempStr.substr(j,1);
			}
	   		if(k==0) newStr=newStr.substr(pos+1);
	
	   	}
	   	
		var s_temp="";
		s_temp = document.forms(0).queryid.value;

		if (v[0]=='31'){
			//收货
			document.forms(0).queryid.value = "DQ_ERVHDR"
			document.forms(0).selectwhere.value="ERVNUM='" +v[1] +"'";
			document.forms(0).action="homeworldErvhdr.do";
		}
		if (v[0]=='51'){
			//返厂
			document.forms(0).queryid.value = "DQ_ERTHDR"
			document.forms(0).selectwhere.value="ERTNUM='" +v[1] +"'";
			document.forms(0).action="homeworldErthdr.do";
		}
		if (v[0]=='431'){
			//收货调整
			document.forms(0).queryid.value = "DQ_ERJHDR"
			document.forms(0).selectwhere.value="ERJNUM='" +v[1] +"'";
			document.forms(0).action="homeworldErjhdr.do";
		}


		document.forms(0).target="_blank";
//		document.forms(0).flag.value="";
		document.forms(0).submit();
		document.forms(0).queryid.value = s_temp;
		document.forms(0).action="searchorder.do";
		document.forms(0).selectwhere.value="";
		document.forms(0).target="";

	
	}
	
	function callfunnext(arg){
		document.forms(0).selectwhere.value=arg;
		document.forms(0).action=document.forms(0).actiondo.value;
		document.forms(0).target="_blank";
//		document.forms(0).flag.value="";
		document.forms(0).submit();
		document.forms(0).action="searchorder.do";
		document.forms(0).selectwhere.value="";
		document.forms(0).target="";
	}
	function callfunERJRNM(arg){
		var s_temp="";
		document.forms(0).selectwhere.value="ERVNUM='"+arg+"'";
		document.forms(0).action="homeworldErvhdr.do";
		document.forms(0).target="_blank";
		s_temp = document.forms(0).queryid.value;
		document.forms(0).queryid.value="DQ_ERVHDR";
		
//		document.forms(0).flag.value="";
		document.forms(0).submit();
		document.forms(0).action="searchorder.do";
		document.forms(0).selectwhere.value="";
		document.forms(0).target="";
		document.forms(0).queryid.value="DQ_ERJHDR";
	}
			
	function callfunorder(order){
		SearchOrderForm.order.value=order;
		callfun("1");
	}			
	function callfun(flaga){
		//输入新的查询条件了
		if (SearchOrderForm.searchthing2 == null) {
		} else {
			//有日期时作的处理
			var temp=SearchOrderForm.searchthing2.value;
			temp=temp.replace("-","");
			temp=temp.replace("-","");
			temp=temp.replace("20","");
			SearchOrderForm.searchthing2.value=temp;
		}
		SearchOrderForm.checkthing.value="";
		SearchOrderForm.flag.value=flaga;
		SearchOrderForm.nowoncepage.value=flaga;
		SearchOrderForm.nowpage.value=flaga;
		SearchOrderForm.submit();
	}
	function calloncepagenext(s_nowpage){
		SearchOrderForm.nowpage.value=s_nowpage;
		SearchOrderForm.nowoncepage.value=SearchOrderForm.nowoncepage.value * 1 + 1;
		SearchOrderForm.submit();
	}
	function calloncepageprew(s_nowpage){
		SearchOrderForm.nowpage.value=s_nowpage;
		SearchOrderForm.nowoncepage.value=SearchOrderForm.nowoncepage.value * 1 - 1;
		SearchOrderForm.submit();
	}
	function callpagenow(s_nowpage){
		SearchOrderForm.nowpage.value=s_nowpage;
		SearchOrderForm.submit();
	}
	function calldelete(){
		SearchOrderForm.flag.value="DELETE";
//		alert(SearchOrderForm.flag.value);
		//SearchOrderForm.deleteflag
		var m =document.forms(0).deleteflag;

		if (m.length==null){
			if (m.checked==true){
					SearchOrderForm.submit();
			}
		} else {

			for (var i=0;i<m.length;i++)
			{
				if (m[i].checked==true){
					//alert(m[i].checked);
					SearchOrderForm.submit();
					break;
				}
			}
		}
	}
	function checkAll(str)
	{
//		if(thiscell.checked){
//			thiscell.form.checkthing.value=thiscell.form.checkthing.value+"`"+ thiscell.value;
//		}else{
//			thiscell.form.checkthing.value= thiscell.form.checkthing.value.replace("`"+thiscell.value,"")  ;
//		}

	  if (SearchOrderForm.queryid.value=="DQ_EIPDTL" ) {
	  
		  var a = document.getElementsByName(str);
		  var n = a.length;
		  var aa = window.event.srcElement.checked
		  
		  for (var i=0; i<n; i++){
		  	
			  if (aa){
			  	//全选
			  	if (a[i].checked == aa){
			  	
			  	}else {
			  		a[i].checked = aa;
				  	SearchOrderForm.checkthing.value=SearchOrderForm.checkthing.value+"`"+ a[i].value;
				}
			  } else {
			  	//全不选
			  	a[i].checked = aa;
			  	SearchOrderForm.checkthing.value= SearchOrderForm.checkthing.value.replace("`"+a[i].value,"")  ;
			  }
		  }
//		  SearchOrderForm.checkthing.value="";

	  } else {
		  var a = document.getElementsByName(str);
		  var n = a.length;
		  for (var i=0; i<n; i++){
		  	a[i].checked = window.event.srcElement.checked;
		  }

	  }
	}
	
</script>

</HEAD>
<BODY>
<html:form action="/searchorder">

	<html:hidden property="flag" value="search"/>
	<html:hidden property="page" />
	<html:hidden property="searchid" />
	<html:hidden property="queryid" />
	<html:hidden property="queryiddis" />
	<html:hidden property="actiondo" />
	<html:hidden property="nowpage" />
	<html:hidden property="nowoncepage" />
	<html:hidden property="selectwhere" />
	<html:hidden property="checkthing" />
	<html:hidden property="order" />
		
	<%String s_class="";%>
	<%String s_link="";%>
	<%String pname="";%>
	<%String pname2="";%>
	<%String s_img="";%>
	<% int i_lookin=0;%>	
	<% String s_lookin="";%>
	
	
	<logic:present name="SearchOrderForm" property="data">
		<logic:iterate id="row" name="SearchOrderForm" property="datakey" indexId="index">
			<input type="hidden" name="datakey" value="<%=row%>">
		</logic:iterate>
	</logic:present>
	
	<logic:equal name="SearchOrderForm" property="superuser" value="">
		<logic:notEqual name="SearchOrderForm" property="searchkey1" value="">
			<html:hidden property="searchkey1" />
			<bean:write name="SearchOrderForm" property="searchtitle1"/>
			<html:hidden property="searchtitle1" />
			<html:text property="searchthing1" size="10"/>
		</logic:notEqual>
		<logic:notEqual name="SearchOrderForm" property="searchkey2" value="">
			<html:hidden property="searchkey2" />
			<bean:write name="SearchOrderForm" property="searchtitle2"/>
			<html:hidden property="searchtitle2" />
			<html:text property="searchthing2" size="10" readonly="true" onclick="clicka(this)"/>
		</logic:notEqual>
		<logic:notEqual name="SearchOrderForm" property="searchkey3" value="">
			<html:hidden property="searchkey3" />
			<bean:write name="SearchOrderForm" property="searchtitle3"/>
			<html:hidden property="searchtitle3" />
			<html:text property="searchthing3" size="10"/>
		</logic:notEqual>
		<logic:notEqual name="SearchOrderForm" property="searchkey4" value="">
			<logic:equal name="SearchOrderForm" property="queryid" value="DQ_EIPDTL">
				<bean:write name="SearchOrderForm" property="searchtitle4"/>
				<select size="1" name="<bean:write name="SearchOrderForm" property="searchkey4"/>" onchange = "clickb(this)">
					<logic:equal name="SearchOrderForm" property="searchthing4" value="">
						<option selected value="">全部</option>
						<option value="31">收货</option>
						<option value="51">返厂</option>
						<option value="431">收货调整</option>
					</logic:equal>
	
					<logic:equal name="SearchOrderForm" property="searchthing4" value="31">
						<option value="">全部</option>
						<option selected value="31">收货</option>
						<option value="51">返厂</option>
						<option value="431">收货调整</option>
					</logic:equal>
					<logic:equal name="SearchOrderForm" property="searchthing4" value="51">
						<option value="">全部</option>
						<option value="31">收货</option>
						<option selected  value="51">返厂</option>
						<option value="431">收货调整</option>
					</logic:equal>
					<logic:equal name="SearchOrderForm" property="searchthing4" value="431">
						<option value="">全部</option>				
						<option value="31">收货</option>
						<option value="51">返厂</option>
						<option selected  value="431">收货调整</option>
					</logic:equal>
				</select>
				
				<html:hidden property="searchkey4" />
				<html:hidden property="searchtitle4" />
				<logic:equal name="SearchOrderForm" property="searchkey4" value="">		
					<html:hidden property="searchthing4" value="31"/>
				</logic:equal>
				<logic:notEqual name="SearchOrderForm" property="searchkey4" value="">		
					<html:hidden property="searchthing4"/>
				</logic:notEqual>
			</logic:equal>
		</logic:notEqual>
		<logic:notEqual name="SearchOrderForm" property="searchkey5" value="">
			<html:hidden property="searchkey5" />
			<bean:write name="SearchOrderForm" property="searchtitle5"/>
			<html:hidden property="searchtitle5" />
			<html:text property="searchthing5" size="10"/>
		</logic:notEqual>
	</logic:equal>
	<logic:notEqual name="SearchOrderForm" property="superuser" value="">
		供货商<html:text property="superuserthing" />
	</logic:notEqual>
		<a href="javascript:callfun('1')">
			<img border="0" src="<%=request.getContextPath()%>/img/searchbutton.gif" width="52" height="22">
		</a>
					
<HR>
			<logic:notPresent name="SearchOrderForm" property="data">

				<logic:equal name="SearchOrderForm" property="superuser" value="">
					<center>查询无结果</center>
				</logic:equal>
				<logic:notEqual name="SearchOrderForm" property="superuser" value="">
					<logic:equal name="SearchOrderForm" property="superuserthing" value="">
						<center>请输入要查询供货商</center>
					</logic:equal>					
					<logic:notEqual name="SearchOrderForm" property="superuserthing" value="">
						<center>查询无结果</center>
					</logic:notEqual>
				</logic:notEqual>
			</logic:notPresent>
			
			<logic:present name="SearchOrderForm" property="data">

				<logic:present name="SearchOrderForm" property="pagelist">
					
					<TABLE>
					<TR>

						<TD class="textright" NOWRAP>
							当前第<bean:write name="SearchOrderForm" property="nowpage"/>页 &nbsp;
						</TD>
						<logic:present name="SearchOrderForm" property="prewOncePage">
							<TD class="textright" NOWRAP>
								<A href=javascript:calloncepageprew('<bean:write name="SearchOrderForm" property="prewOncePage"/>')>
									[上<bean:write name="SearchOrderForm" property="oncepage"/>页]&nbsp;
								</A>	
							</TD>
						</logic:present>
						<logic:iterate id="row" name="SearchOrderForm" property="pagelist" indexId="index">
							<TD class="textright" NOWRAP>
								<A href=javascript:callpagenow('<bean:write name="row" property="list"/>')>
								<!--A href='<bean:write name="row" property="list"/>'-->
									第<bean:write name="row" property="number"/>页
								</A>	
							</TD>
						</logic:iterate>
						<logic:present name="SearchOrderForm" property="nextOncePage">
							<TD class="textright" NOWRAP>
								<A href=javascript:calloncepagenext('<bean:write name="SearchOrderForm" property="nextOncePage"/>')>
									[下<bean:write name="SearchOrderForm" property="oncepage"/>页]&nbsp;
								</A>	
							</TD>
						</logic:present>
						<TD>
							&nbsp
						</TD>
						<logic:equal name="SearchOrderForm" property="queryid" value="DQ_ERVHDR">
							<TD>
								<a href="javascript:calldelete()">
									<img border="0" src="<%=request.getContextPath()%>/img/delete.jpg">
								</a>				
							</TD>
						</logic:equal>
						<logic:equal name="SearchOrderForm" property="queryid" value="DQ_ERTHDR">
							<TD>
								<a href="javascript:calldelete()">
									<img border="0" src="<%=request.getContextPath()%>/img/delete.jpg">
								</a>				
							</TD>
						</logic:equal>
						<logic:equal name="SearchOrderForm" property="queryid" value="DQ_ERJHDR">
							<TD>
								<a href="javascript:calldelete()">
									<img border="0" src="<%=request.getContextPath()%>/img/delete.jpg">
								</a>				
							</TD>
						</logic:equal>
						<logic:equal name="SearchOrderForm" property="queryid" value="DQ_EIPDTL">
							<TD>
								<input type="button" name="fp" value="输入发票信息" onclick="callfunfp()">
							</TD>
							<TD>
								<input type="button" name="reset" value="重置" onclick="checkAll('checkthings')">
							</TD>
						</logic:equal>
					</TR>
					</TABLE>
				</logic:present>

				<BR>
				<logic:present name="SearchOrderForm" property="lookincount">
					<bean:write name="SearchOrderForm" property="lookincount"/>
				</logic:present>


						<logic:equal name="SearchOrderForm" property="queryid" value="DQ_EIPDTL">
							<p align="right">
								<input type="checkbox" name="checkall" onclick="checkAll('checkthings')">全选/取消全选（当前页）
							</p>
						</logic:equal>

				<TABLE borderColor=#000033 height=25 cellSpacing=0 cellPadding=0 width="97%" align=center bgColor=#8888b0 background="" border=1>
					<TR align="center">
						<logic:iterate id="row" name="SearchOrderForm" property="datatitle" indexId="index">
							<TD class="hometitle" NOWRAP>
								<A href=javascript:callfunorder('<bean:write name="SearchOrderForm" property='<%="fields[" + index + "]"%>'/>')>
									<bean:write name="SearchOrderForm" property='<%="datatitle[" + index + "]"%>'/>
								</A>
							</TD>
						</logic:iterate>

						<logic:equal name="SearchOrderForm" property="queryid" value="DQ_EPYHDR">
							<TD class="hometitle" NOWRAP>
								发票信息
							</TD>
							<TD class="hometitle" NOWRAP>
								费用信息
							</TD>
						</logic:equal>


						<logic:equal name="SearchOrderForm" property="queryid" value="DQ_EPOHDR">
							<TD class="hometitle" NOWRAP>
								是否回复
							</TD>
						</logic:equal>
						<logic:present name="SearchOrderForm" property="lookin">
							<TD class="hometitle" NOWRAP>
								是否已阅
							</TD>
						</logic:present>
						<logic:equal name="SearchOrderForm" property="queryid" value="DQ_ERVHDR">
							<TD class="hometitle" NOWRAP>
								删除标志
							</TD>
						</logic:equal>
						<logic:equal name="SearchOrderForm" property="queryid" value="DQ_ERTHDR">
							<TD class="hometitle" NOWRAP>
								删除标志
							</TD>
						</logic:equal>
						<logic:equal name="SearchOrderForm" property="queryid" value="DQ_ERJHDR">
							<TD class="hometitle" NOWRAP>
								删除标志
							</TD>
						</logic:equal>
						<logic:equal name="SearchOrderForm" property="queryid" value="DQ_EIPDTL">
							<TD class="hometitle" NOWRAP>
								开票?
							</TD>
						</logic:equal>

					</TR>
					<logic:iterate id="row" name="SearchOrderForm" property="data" indexId="indexrow">
						
						<%s_class="hometextold";%>
						<logic:present name="SearchOrderForm" property="lookin">
							<logic:equal name="SearchOrderForm" property='<%="lookin[" + indexrow + "]"%>' value="1">
								<%s_class="hometext";%>
							</logic:equal>
							<logic:equal name="SearchOrderForm" property='<%="lookin[" + indexrow + "]"%>' value="0">
								<%s_class="hometextold";%>
							</logic:equal>
						</logic:present>
								
						<TR align="center" >
							<%pname = "data[" + indexrow + "]";%>
							<% i_lookin=-1;%>
							<logic:iterate id="col" name="SearchOrderForm" property="<%=pname%>" indexId="indexcol">
								<% if (col.equals("")) {%>
									<TD class="<%=s_class%>" NOWRAP>&nbsp</TD>
								<% } else {%>
									<logic:equal name="SearchOrderForm" property="queryid" value="DQ_EPOHDR">
											<% s_img = "" + indexcol + "";%>
											<% if (s_img.equals("6")) {%>
												<% if (col.equals("1")) {%>
													<TD class="<%=s_class%>" NOWRAP>
														<img src="<%=request.getContextPath()%>/img/del.gif" ALT='取消订单标志'>
													</TD>
												<% } else {%>
													<% if (col.equals("2")) {%>
														<TD class="<%=s_class%>" NOWRAP>
															<img src="<%=request.getContextPath()%>/img/endsend.gif " ALT='已送货标志'>
														</TD>
													<% } else {%>
														<TD class="<%=s_class%>" NOWRAP>&nbsp</TD>
													<%}%>	
												<%}%>											
											<% } else {%>
												<% if (s_img.equals("2")) {%>								
													<TD class="<%=s_class%>" NOWRAP>
														<%s_link="datakey[" + indexrow + "]";%>
														<A href= javascript:callfunnext("<bean:write name='SearchOrderForm' property='<%=s_link%>'/>") >
															<%=col%>
														</A>
													</TD>
												<% } else {%>
													<TD class="<%=s_class%>" NOWRAP><%=col%></TD>
												<%}%>
											<%}%>
									</logic:equal>
									<logic:equal name="SearchOrderForm" property="queryid" value="DQ_ERTHDR">
										<% s_img = "" + indexcol + "";%>
										<% if (s_img.equals("1")) {%>
											<TD class="<%=s_class%>">
												<%s_link="datakey[" + indexrow + "]";%>
												<A href= javascript:callfunnext("<bean:write name='SearchOrderForm' property='<%=s_link%>'/>") >
													<%=col%>
												</A>
											</TD>
										<%} else {%>
											<% if (s_img.equals("4")) {%>
												<% if (col.equals("Z")) {%>
													<TD class="<%=s_class%>" NOWRAP>已处理</TD>
												<%} else {%>
													<TD class="<%=s_class%>" NOWRAP>未处理</TD>												
												<%}%>	
											<%} else {%>	
												<TD class="<%=s_class%>" NOWRAP><%=col%></TD>
											<%}%>
										<%}%>
									</logic:equal>
									<logic:equal name="SearchOrderForm" property="queryid" value="DQ_ERVHDR">
										<% s_img = "" + indexcol + "";%>
										<% if (s_img.equals("1")) {%>
											<TD class="<%=s_class%>">
												<%s_link="datakey[" + indexrow + "]";%>
												<A href= javascript:callfunnext("<bean:write name='SearchOrderForm' property='<%=s_link%>'/>") >
													<%=col%>
												</A>
											</TD>
										<%} else {%>	
											<TD class="<%=s_class%>" NOWRAP><%=col%></TD>
										<%}%>
									</logic:equal>
									<logic:equal name="SearchOrderForm" property="queryid" value="DQ_ERJHDR">
										<% s_img = "" + indexcol + "";%>
										<% if (s_img.equals("2")) {%>
											<TD class="<%=s_class%>">
												<%s_link="datakey[" + indexrow + "]";%>
												<A href= javascript:callfunnext("<bean:write name='SearchOrderForm' property='<%=s_link%>'/>") title=收货调整单>
													<%=col%>
												</A>
											</TD>
										<%} else {%>	
											<% if (s_img.equals("1")) {%>
												<TD class="<%=s_class%>">
													<A href= javascript:callfunERJRNM("<%=col%>") title=收货单>
														<%=col%>
													</A>
												</TD>
											<%} else {%>	
												<TD class="<%=s_class%>" NOWRAP><%=col%></TD>
											<%}%>	
										<%}%>
									</logic:equal>
									<logic:equal name="SearchOrderForm" property="queryid" value="DQ_EIPDTL">
										<% s_img = "" + indexcol + "";%>
										<% if (s_img.equals("1")) {%>
											<TD class="<%=s_class%>">
												<%s_link="datakey[" + indexrow + "]";%>
												<A href= 'javascript:callchoosenext("<bean:write name="SearchOrderForm" property="<%=s_link%>"/>")' >
													<%=col%>
												</A>
											</TD>
										<%} else {%>	
											<TD class="<%=s_class%>" NOWRAP><%=col%></TD>
										<%}%>
									</logic:equal>
									<logic:equal name="SearchOrderForm" property="queryid" value="DQ_EPYHDR">
										<% s_img = "" + indexcol + "";%>
											<TD class="<%=s_class%>" NOWRAP><%=col%></TD>
									</logic:equal>
									<logic:equal name="SearchOrderForm" property="queryid" value="DQ_EPYRJT">
										<% s_img = "" + indexcol + "";%>

											<TD class="<%=s_class%>" NOWRAP><%=col%></TD>

									</logic:equal>
									<logic:equal name="SearchOrderForm" property="queryid" value="DQ_EIVDTL">
										<% s_img = "" + indexcol + "";%>
										<% if (s_img.equals("0")) {%>
											<TD class="<%=s_class%>">
												<%s_link="datakey[" + indexrow + "]";%>
												<A href= 'javascript:callfunnext("<bean:write name="SearchOrderForm" property="<%=s_link%>"/>")' >
													<%=col%>
												</A>
											</TD>
										<%} else {%>	
											<TD class="<%=s_class%>" NOWRAP><%=col%></TD>
										<%}%>
									</logic:equal>
									
									
								<%}%>
							</logic:iterate>

								<logic:equal name="SearchOrderForm" property="queryid" value="DQ_EPYHDR">
									<TD class="<%=s_class%>">
										<%s_link="datakey[" + indexrow + "]";%>
										<A href= 'javascript:callEpyhdrnext("<bean:write name="SearchOrderForm" property="<%=s_link%>"/>","FP")' >
											详细
										</A>
									</TD>
								</logic:equal>
								<logic:present name="SearchOrderForm" property="returnin">
									<logic:equal name="SearchOrderForm" property="queryid" value="DQ_EPOHDR">
										<TD  class="<%=s_class%>" NOWRAP>
											<logic:equal name="SearchOrderForm" property='<%="returnin[" + indexrow + "]"%>' value="1">
												已回
											</logic:equal>
											<logic:equal name="SearchOrderForm" property='<%="returnin[" + indexrow + "]"%>' value="0">
												未回
											</logic:equal>
										</TD>
									</logic:equal>
									<logic:equal name="SearchOrderForm" property="queryid" value="DQ_EPYHDR">									
										<TD  class="<%=s_class%>" NOWRAP>
											<logic:equal name="SearchOrderForm" property='<%="returnin[" + indexrow + "]"%>' value="1">
												<%s_link="datakey[" + indexrow + "]";%>
												<A href= 'javascript:callEpyhdrnext("<bean:write name="SearchOrderForm" property="<%=s_link%>"/>","FY")' >
													详细
												</A>
											</logic:equal>
											<logic:equal name="SearchOrderForm" property='<%="returnin[" + indexrow + "]"%>' value="0">
												无
											</logic:equal>
										</TD>
									</logic:equal>
								</logic:present>

								<logic:present name="SearchOrderForm" property="lookin">
									<TD  class="<%=s_class%>" NOWRAP>
										<logic:equal name="SearchOrderForm" property='<%="lookin[" + indexrow + "]"%>' value="1">
											已阅
										</logic:equal>
										<logic:equal name="SearchOrderForm" property='<%="lookin[" + indexrow + "]"%>' value="0">
											未阅
										</logic:equal>
									</TD>
								</logic:present>

								<logic:notEqual name="SearchOrderForm" property="queryid" value="DQ_EPOHDR">
								<logic:notEqual name="SearchOrderForm" property="queryid" value="DQ_EPYHDR">
								<logic:notEqual name="SearchOrderForm" property="queryid" value="DQ_EPYRJT">
								<logic:notEqual name="SearchOrderForm" property="queryid" value="DQ_EIVDTL">
									<logic:equal    name="SearchOrderForm" property="queryid" value="DQ_EIPDTL">
										<TD class="<%=s_class%>" NOWRAP>
											<html:multibox name="SearchOrderForm" onclick="tdclick(this)" property="checkthings" > <bean:write name="SearchOrderForm" property="<%=s_link%>"/>   </html:multibox>
										</TD>
									</logic:equal>
									<logic:notEqual name="SearchOrderForm" property="queryid" value="DQ_EIPDTL">
										<TD class="<%=s_class%>" NOWRAP>
											<html:multibox name="SearchOrderForm" property="deleteflag" ><bean:write name="SearchOrderForm" property="<%=s_link%>"/></html:multibox>						
										</TD>
									</logic:notEqual>
								</logic:notEqual>									
								</logic:notEqual>									
								</logic:notEqual>
								</logic:notEqual>
								
							</TR>
					</logic:iterate>
					
				</TABLE>
				<center><bean:write name="SearchOrderForm" property="endtitle"/></center>
			</logic:present>

	
</html:form>
</BODY>
</html:html>
