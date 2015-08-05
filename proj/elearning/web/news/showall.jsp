<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@page import="com.htyz.*"%>
<jsp:useBean id="news" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="code" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="eds" scope="request" class="com.htyz.util.BeanDateFormat"/>
<html>
<head>
<title>E_elearning新闻中心</title>
<link rel="stylesheet" href="news.css" type="text/css">
<script language="JAVASCRIPT">
function shownews(myurl)
{
window.open(myurl,'_blank','width=600,height=450,scrollbars=yes,resizable=yes,center:yes');
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" >

<table width="98%" border="1" cellspacing="0" cellpadding="5" bordercolor="#EEEEEE" bordercolorlight="#FFFFFF" bordercolordark="#CCCCCC">
  <%if( !(request.getParameter("type")==null)){
        news.executeSelect("select * from t_news where news_head='1' and news_status!='0' and news_type='"+request.getParameter("type")+"' order by news_date desc");
   }else{
        news.executeSelect("select * from t_news where news_head='1' and news_status!='0' order by news_date desc");
   }
%>
  <tr>
    <td width="180"  valign="top"> 
      <table width="100%" border="0" cellpadding="0" cellspacing="5">
        <tr> 
          <td align="center" width="180"> 
            <table width="250" height="60" background="<%=request.getContextPath()%>/images/hotnews.gif" border="0"><tr><td>&nbsp</td></tr></table>
          </td>
        </tr>

	<tr><td bgcolor="#000000" height="1"></td></tr>

	<%for (int i=0; i<10&&i<news.getRowCount(); i++)
		{%>
        <tr> 
          <td height="20"><span class="homepagesub">
		  <%if(!news.getFieldValue("news_img",i).equals("")){%>
			  <img src="<%=news.getFieldValue("news_img",i)%>" width="66" height="66" align="left" border="1"> 
		  <%}else{%>
			  <img src="<%=request.getContextPath()%>/news/upload/noimg.gif" width="66" height="66" align="left" border="1"> 
		  <%}%>
			<a href="javascript:shownews('newsin.jsp?newsid=<%=news.getFieldValue("news_id",i)%>&title=<%=news.getFieldValue("news_title",i)%>');"><font color=blue><%=news.getFieldValue("news_title",i)%></font></a> 
            <br>
            <%=news.getFieldValue("news_content",i).length()>80?news.getFieldValue("news_content",i).substring(0,80)+"... ...":news.getFieldValue("news_content",i)%> 
	    </span>
         </td>
        </tr>
		<tr><td><img src="<%=request.getContextPath()%>/images/news_xi.gif" width="250" height="1"></td></tr>
		<tr> 
          <td height="5"></td>
        </tr>
	<%}%>
      </table>
    </td>
    <td height="20" valign="top"> 
	<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#6687BA">
        <%if( !(request.getParameter("type")==null)){
		news.executeSelect("select * from t_news where news_status!='0' and news_type='"+request.getParameter("type")+"' order by news_type,news_date desc");
	}else{
		news.executeSelect("select * from t_news where news_status!='0' order by news_type,news_date desc");
	}
	String tmptype="";
	int numperpage=1;
        int isfirst=1;
	for (int i=0;i<news.getRowCount();i++){ // For循环开始
	   if(!news.getFieldValue("news_type",i).equals(tmptype)){%>
		<%code.executeSelect("select * from t_code where code_id='0008' and code_value='"+news.getFieldValue("news_type",i)+"'");
		if(isfirst==0) { isfirst=1;%>
			</table>
		</td>
	</tr>

<%}
isfirst=0;%>
	<tr>
		<td colspan=3>
		<table width="100%" height="20" border="0" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/images/bg11.gif">
              		<tr> 
                		<td> 　<font color="#006699">&nbsp&nbsp&nbsp&nbsp&nbsp<b><%=code.getFieldValue("code_namec",0)%></b></td>
                		<td colspan=2> 
                  		<div align="right"> 
		                    <a href="showall.jsp?type=<%=news.getFieldValue("news_type",i)%>" target="_blank">更多新闻...</a>
                    　 		</div>
			</tr>
		</table>
		</td>
	</tr>
      
        <tr>
		<td bgcolor="#FFFFFF" colspan=3> 
			<table width=100% bgcolor="#FFFFFF" border=0>
				<tr>
				  <td height="23"  bgcolor="#FFFFFF"> 
			            □&nbsp&nbsp&nbsp&nbsp<a href="javascript:shownews('newsin.jsp?newsid=<%=news.getFieldValue("news_id",i)%>&title=<%=news.getFieldValue("news_title",i)%>');"><%=news.getFieldValue("news_title",i)%></a>&nbsp&nbsp<span class="time">(<%=eds.format(news.getFieldValue("news_date",i),3)%>)</span>
			          </td>
				  <td align="right" bgcolor="#FFFFFF"><%=news.getFieldValue("news_count",i)%>&nbsp;次点击</td>
			        </tr>

        <%
	tmptype=news.getFieldValue("news_type",i);
	numperpage=1;
	  }else{
	  	numperpage+=1;
		  if(numperpage>10&&(request.getParameter("type")==null)){continue;}else{%>
		    		<tr>
		          	  <td height="23"  bgcolor="#FFFFFF"> 
		            		□&nbsp&nbsp&nbsp&nbsp<a href="javascript:shownews('newsin.jsp?newsid=<%=news.getFieldValue("news_id",i)%>&title=<%=news.getFieldValue("news_title",i)%>');"><%=news.getFieldValue("news_title",i)%></a>&nbsp&nbsp<span class="time">(<%=eds.format(news.getFieldValue("news_date",i),3)%>)</span>
		          	  </td>
			  	  <td height="23" align="right" bgcolor="#FFFFFF"><%=news.getFieldValue("news_count",i)%>&nbsp;次点击</td>
		    		</tr>

        <%}
	   }

	   }   //For循环结束 %>
      </table>
  </td>
  </tr>
</table>
<br>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td bgcolor="#000000" height="1"></td>
  </tr>
</table>
<div align="center"><span class="copyright"><br>北京环天宇正信息科技有限公司 二零零二年<br>(C)copyright:www.huantian.com</span></div>
</body>
</html>

