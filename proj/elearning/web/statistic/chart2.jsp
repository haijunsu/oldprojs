<%@ page contentType="text/html; charset=GB2312" %>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="bgda" scope="request" class="com.htyz.beanGetdata"/>

<jsp:useBean id="notbean" scope="session" class="com.htyz.notice.noticeBean" />

<%@ page import="com.htyz.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.htyz.notice.*"%>
<%@ page import="com.htyz.system.SystemProperties"%>
<%
String s_sql="",s_total="",class_id="",code_namec="" ;
int totalRowNum=0,count=0,totalcount=0,showcount=0;
String strAddsign = com.htyz.system.SystemProperties.getProperty("db.addsign");
String strSubstr = com.htyz.system.SystemProperties.getProperty("db.substr");

/*s_sql="select class_id,sum(peoples) count from t_class group by class_id order by count desc";*/


s_sql="select "+strSubstr+"("+"class_id"+",1,8) id"+",sum(peoples) count from t_class group by "+strSubstr+"("+"class_id"+",1,8)"+" order by count desc";

s_total="select "+strSubstr+"("+"a.class_id"+",1,8) id"+",count(a.class_id) as count from t_user_class a,t_user  b where a.user_id=b.user_id  group by "+strSubstr+"("+"a.class_id"+",1,8)"+" union select  "+strSubstr+"("+"a.class_id"+",1,8) id"+",count(a.class_id) as count from t_user b,t_user_class a where b.group_id=a.user_id group by "+strSubstr+"("+"a.class_id"+",1,8)"+"";
bgda.executeSelect(s_total);

bgd.executeSelect(s_sql);
totalRowNum=bgd.getRowCount();


%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns:v="urn:schemas-microsoft-com:vml">
<head>
<link REL="stylesheet" TYPE="text/css" HREF="../include/css/hongdicrm.css">
<style>
v\:* { behavior: url(#default#VML); }
</style>
<script SRC="chart.js"></script>
</HEAD>
<BODY>
<TABLE id=srcTb border=1 cellspacing="0" width="100%" cellpadding="0" bgcolor="#eeeeee">

<TR><TD>课程类名称(访问次数前10名)</TD><TD><INPUT TYPE="checkbox" NAME="col1">正在学习人数</TD><TD><INPUT TYPE="checkbox" NAME="col2">访问次数</TD></TR>
<%

Vector vec=new Vector();
userDO urdo = null;
vec=notbean.getcode();
String tmpid="";
for(int m=0;m<totalRowNum;m++)
{
 class_id=(bgd.getFieldValue("id",m));
 //out.println("the class_id==="+class_id);
 for(int i=0;i<bgda.getRowCount();i++)
 {
	 tmpid=bgda.getFieldValue("id",i);

	 if(tmpid.equals(class_id))
	 {
		 totalcount=Integer.parseInt(bgda.getFieldValue("count",i));
		 
		 break;
     }
 }
   
  for(int j=0;j<vec.size();j++)
  {
	 urdo = new userDO();
	 urdo = (userDO)vec.elementAt(j);
	 code_namec="测试类"+m;
	 if((urdo.getUser_id()).equals(class_id))
	 {
		 code_namec=urdo.getUser_name();
		
		 break;
	 }
   
   }
  
  count=Integer.parseInt(bgd.getFieldValue("count",m));
  //showcount=showcount+count;
 %>
  <TR><TD><INPUT TYPE="checkbox" NAME="row<%=m+1%>"><%=code_namec%></TD><TD><%=totalcount%></TD><TD><%=count%></TD></TR>
 <%
	 if(m==9)
	  break;
}
%>

</TABLE>
<table>
<tr>
<td><INPUT TYPE="button" value="线图" onclick="drawChart(0)"></td>
<td><INPUT TYPE="button" value="框图" onclick="drawChart(1)"></td>
<td><INPUT TYPE="button" value="饼图" onclick="drawChart(2)"></td>
<td><input TYPE="checkbox" NAME="ro" onClick="rotation=(rotation==0)?1:0;">旋转</td>
<td>&nbsp;<INPUT TYPE="button" onclick="decX(chartObj)" value="-">&nbsp;X轴&nbsp;<INPUT TYPE="button" onclick="addX(chartObj)" value="+">&nbsp;</td>
<td class="td">&nbsp;<INPUT TYPE="button" onclick="decY(chartObj)"value="-">&nbsp;Y轴&nbsp;<INPUT TYPE="button" onclick="addY(chartObj)" value="+">&nbsp;</td>	
</tr>
</table>
<div id="chartDiv"></div>
</BODY>
</HTML>
<script LANGUAGE="javascript">
<!--
var chartObj=null;
var titlArr = new Array();
var xStrArr = new Array();
var yValArr = new Array();
var rotation = 0;
function getArr(){
	titlArr.length=0;xStrArr.length=0;yValArr.length=0;
	var curObj = srcTb.firstChild.children;	
	var colNum = curObj[0].children.length;
	var rowNum = curObj.length;	
	for (var i=1;i<rowNum;i++) {
		if (eval("document.all.row"+i+".checked"))
			xStrArr[xStrArr.length] = curObj[i].children[0].innerText;
	}
	for (var i=1;i<colNum;i++) {		
		if (eval("document.all.col"+i+".checked"))
		{
			titlArr[titlArr.length]=curObj[0].children[i].innerText;		
			var tmpArr = new Array();
			for (var j=1;j<rowNum;j++) {
				if (eval("document.all.row"+j)!=null && eval("document.all.row"+j+".checked")){
					if (isNaN(parseFloat(curObj[j].children[i].innerText)))
						tmpArr[tmpArr.length]=0
					else
						tmpArr[tmpArr.length]=parseFloat(curObj[j].children[i].innerText);					
				}
			}		
			yValArr[yValArr.length] = tmpArr;
		}
	}
	if (rotation==1){
		var tmpAr=titlArr;
		titlArr = xStrArr;
		xStrArr = tmpAr;
		var tmpAr1 = new Array();
		for(var i=0;i<yValArr[0].length;i++){
			var tmpArr = new Array();
			for (var j=0;j<yValArr.length;j++){
				tmpArr[tmpArr.length]=yValArr[j][i];				
			}
			tmpAr1[tmpAr1.length] = tmpArr;
		}
		yValArr = tmpAr1;
	}	
}
function drawChart(chartType){
	getArr();
	chartDiv.innerHTML = "";
	if (yValArr.length>0 && xStrArr.length>0){
		chartObj=createChart(yValArr,xStrArr,titlArr,chartType,1,1,'学习统计图',chartDiv);				
		showChart_Ex(chartObj);		
	}
	else
		chartDiv.innerHTML = "<font size=\"2\">请选择需要作图的项目.</font>"	
}

//-->
</script>
