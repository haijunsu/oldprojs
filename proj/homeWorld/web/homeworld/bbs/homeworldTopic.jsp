
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
			<logic:equal name="homeworldTopicForm" property='page' value="0">	
				通告录入
			</logic:equal>
			<logic:equal name="homeworldTopicForm" property='page' value="1">	
				通告修改
			</logic:equal>
		</TITLE>
	</HEAD>
	<BODY>
		<html:errors property="errormessage"/>
			<html:form action="/homeworldTopic">
			<html:hidden property="page"/>
			<html:hidden property="flag"/>
			<html:hidden property="nowdate"/>
			<html:hidden property="nowpage"/>
			<html:hidden property="nowoncepage"/>
			<center><H2>
				<logic:equal name="homeworldTopicForm" property='page' value="0">	
				<html:hidden property="freeformstate" value="3"/>
					通告录入
				</logic:equal>
				<logic:equal name="homeworldTopicForm" property='page' value="1">	
					<html:hidden property="freeformstate" value="0"/>
					通告修改
				</logic:equal>
			</H2></center>
			<%
			int i_temp=0;
			String temp="";
			%>
		    <table align="center" width="50%" cellspacing="0" cellpadding="1" class="table1border">
				<tbody >
					<tr align="center">
						<td class="table1title" nowrap>标题</td>
						<td class="table1text" nowrap colspan="2">
						<input type="text" name="topicc"  size="70" class="n1left" onfocus="this.select()"  onchange="freeformchange(this.form)" 
								 value="<bean:write name='homeworldTopicForm' property='topicc'/>"> 				            			            
						<input type="hidden" name="topid" value="<bean:write name='homeworldTopicForm' property='topid'/>"> 				            				            
						<input type="hidden" name="seq" value="<bean:write name='homeworldTopicForm' property='seq'/>"> 				            				            		 
						</td>
					</tr><tr>  
						<td class="table1title" nowrap>通告类型</td>
						<td>
						<logic:present name="homeworldTopicForm" property="readioshow" >
							<logic:iterate id="readioshow" name="homeworldTopicForm" property="readioshow" indexId="index">
								<%
								i_temp=i_temp+1;
								temp="0"+i_temp;
								temp=temp.substring(temp.length()-2);
								%>
								<logic:equal name="index" value="6">										
									</td><td>
								</logic:equal>
								&nbsp;&nbsp;
								
								<html:radio property="bbskind"  onclick="freeformchange(this.form)" value='<%=temp%>' />				
								
								<img src="<%=request.getContextPath()%>/img/BBS/mood<%=temp%>.gif " WIDTH=19 HEIGHT=19 ALT='<bean:write name="homeworldTopicForm" property='<%="readioshow[" + index + "]"%>'/>'>
							</logic:iterate>
						</logic:present>
						</td>
					</tr><tr>  
						<td class="table1title" nowrap>内容</td>
						<td class="table1text" nowrap colspan="2">
						
						<textarea  name="contentc" COLS="68" ROWS="15"  maxlength="10"  onchange="freeformchange(this.form)" ><bean:write name='homeworldTopicForm' property='contentc'/></textarea>
						
						</td>
					</tr><tr>  
						<td class="table1title" nowrap>生效始日期</td>
						<td class="table1text" nowrap colspan="2">
						<input type="text" name="effbeg"  size="70" maxlength="10" class="n1left" onfocus="this.select()" readonly="true" onclick="change(this)" 
								 value="<bean:write name='homeworldTopicForm' property='effbeg'/>"> 				            			            
						</td>
					</tr><tr>  
						<td class="table1title" nowrap>生效止日期</td>
						<td class="table1text" nowrap colspan="2">
						<input type="text" name="effend"  size="70" maxlength="10" class="n1left" onfocus="this.select()"  readonly="true" onclick="change(this)" 
								 value="<bean:write name='homeworldTopicForm' property='effend'/>"> 				            			            
						</td>
					</tr><tr>  
						<td class="table1title" nowrap>操作人</td>
						<td class="table1text" nowrap colspan="2">
						<input type="text" name="operatorshow" size="70" class='n2leftnone' readonly='true' value="<bean:write name='homeworldTopicForm' property='operatorshow'/>"> 				            				            
						<input type="hidden" name="operatorid" value="<bean:write name='homeworldTopicForm' property='operatorid'/>"> 				            				            
						</td>	
					</tr><tr>  
						<td class="table1title" nowrap>操作日期</td>
						<td class="table1text" nowrap colspan="2">
						<input type="text" name="operdate" class='n2leftnone' size="70" readonly="true" value="<bean:write name='homeworldTopicForm' property='operdate'/>"> 				            				            
						</td>	
					</tr><tr>  
						<td class="table1title" nowrap rowspan="3">通告所属组</td>
					 	<logic:present name="homeworldTopicForm" property="bbsgroup" >
					        	<td colspan="6" nowrap>&nbsp;
					        	<logic:iterate id="bbsgroupshow" name="homeworldTopicForm" property="bbsgroupshow" indexId="indexrow">
					        		<logic:equal name="indexrow" value="5">										
					    				&nbsp;&nbsp;</td></tr>    								
				    					<tr><td  colspan="6" nowrap>&nbsp;
									</logic:equal>
							        &nbsp;&nbsp;<%=bbsgroupshow%>
							        <html:multibox property="bbsgroup" onclick="freeformchange(this.form)"> <%= "" + indexrow%> </html:multibox>						
								</logic:iterate>     
				        	</td>
						</logic:present>
					</tr>
		 		</tbody>
			</table>
			
			<br>
			<br>
			
			<center>
			<html:button property="butcommit" onclick ="submitform(this.form,'commit')">提交</html:button> 
			
			<logic:equal name="homeworldTopicForm" property='page' value="1">	
				<html:button property="butcommit" onclick ="delform(this.form,'del')">删除</html:button> 
			</logic:equal>
			
			<html:button property="butcommit" onclick ="returnform(this.form)">返回</html:button> 
			
			</center>
		</html:form>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			
		<script language="JavaScript">
			if(document.forms[0].flag.value=='del'){	
				window.location="searchbbs.do?queryid=DQ_TOPIC&page=2&actiondo=homeworldTopic.do&nowoncepage="+document.forms[0].nowoncepage.value+"&nowpage="+document.forms[0].nowpage.value;
			}
			//修改
			function returnform(thisform){
				window.location="searchbbs.do?queryid=DQ_TOPIC&page=2&actiondo=homeworldTopic.do&nowoncepage="+thisform.nowoncepage.value+"&nowpage="+thisform.nowpage.value;
			}
			
			function change(thiscell){
				var select=window.showModalDialog("<%=request.getContextPath()%>/system/calendar.jsp","","dialogHeight:300px;dialogWidth:400px " );
				if (select!=""){
					thiscell.value=select;
					freeformchange(thiscell.form);
				}			
			}
			
			//校验提交函数
			function delform(thisform,aflag) {		
					if (confirm("真的想删除么？")){
						thisform.flag.value=aflag;
						thisform.flag.value="del";
						thisform.submit();
					}
				return;
			}
			
			
			//校验提交函数
			function validateSubmit(form) {		
				if (validField(form,1) && validField(form,4)) {	
				
					if(form.effbeg.value < form.nowdate.value){
						//alert("生效始日期 应不小于 当前日期！");
						//return;
					}
					if(form.effend.value < form.nowdate.value){
						alert("生效止日期 应不小于 当前日期！");
						return;
					}
					
					if(form.effbeg.value > form.effend.value){
						alert("生效止日期 应大于 生效始日期");
						return;
					}
					form.submit();
				}
				return;
			}
			
			//修改
			function xgform(thisform){
				if(saveChange(thisform,"xg") == 0){
					thisform.flag.value="xgexic";
					thisform.submit();
				}
			}
			
			//录入
			function lrform(thisform){
				if(saveChange(thisform,"lr") == 0){
					thisform.flag.value="lrexic";
					thisform.submit();
				}
			}

			//必录校验参数(列名,提示内容)
			function required () { 
				this.aa = new Array("topicc",   "标题 不能为空！");
				this.ab = new Array("contentc", "内容 不能为空！");
				this.ac = new Array("effbeg",   "生效始日期 不能为空！");
				this.ad = new Array("effend",   "生效止日期 不能为空！");
			} 
			
			//日期校验参数(列名,提示内容)
			function dates () { 
				this.aa = new Array("effbeg", "生效始日期 格式不正确！");
				this.ab = new Array("effend", "生效止日期 格式不正确！");
			} 
			//比较校验参数(列名,提示内容,参数)
			function compares () { 
				this.aa = new Array("effbeg","生效始日期 应大于当前日期！",'<bean:write name="homeworldTopicForm" property="nowdate"/>');
				this.ab = new Array("effend","生效止日期 应大于当前日期！",'<bean:write name="homeworldTopicForm" property="nowdate"/>');
			} 
			
		</script>

		
	</BODY>
</html:html>
