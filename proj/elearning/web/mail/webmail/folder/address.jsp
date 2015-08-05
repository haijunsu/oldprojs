<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="eds" scope="request" class="com.htyz.util.BeanDateFormat"/>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="ust" scope="session" class="system.userStyle"/>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<%
String navigation = (String)request.getAttribute("navigation");
String catalog = (String)request.getAttribute("catalog");
String action = (String)request.getAttribute("action");
String queryItem = (String)request.getAttribute("queryItem");
String order = (String)request.getAttribute("order");
String filter = (String)request.getAttribute("filter");
%>
<HTML><HEAD><TITLE></TITLE>
<STYLE type=text/css>A:link {
	COLOR: black; TEXT-DECORATION: none
}
A:visited {
	COLOR: black; TEXT-DECORATION: none
}
A:active {
	COLOR: black; TEXT-DECORATION: none
}
A:hover {
	COLOR: black; TEXT-DECORATION: none
}
TD {
	FONT-FAMILY: Verdana; FONT-SIZE: 9pt
}
TH {
	FONT-FAMILY: Verdana; FONT-SIZE: 9pt
}
INPUT {
	FONT-FAMILY: Verdana; FONT-SIZE: 9pt
}
SELECT {
	FONT-FAMILY: Verdana; FONT-SIZE: 9pt
}
.menuStyle {
	FONT-FAMILY: Arial,Geneva,Verdana,Helvetica; FONT-SIZE: 9pt; FONT-WEIGHT: bold; TEXT-DECORATION: none
}
</STYLE>

<script language="JavaScript">
<!--
function del()
	{		
		var count=false,sum=0,str;
		for (i=0;i<form1.r.length;i++)
			{
				if (form1.r[i].checked) 
				{
					count=true;
					sum=sum+1;
				}
			}
		
		if(sum>1)
			{
				str="你确定要删除这"+sum+"个文件夹吗?";
			}
		else
			{
				str="你确定要删除该文件夹吗?";
			}
		if (count)
			{
				if (confirm(str))					
				{
					document.form1.action.value=2;
					document.form1.submit();
				}
				return true;
			}
		else 
			alert("请选择文件夹!");
		return false;
	}
//设置位置
function place2() {
    parent.parent.parent.top1.form1.textfield2.value="电子邮件\-\>邮件夹\-\>地址本";
}
var scrtxt="您可以在这里添加你所要的地址!";
  var lentxt=scrtxt.length;
  var width=100;
  var pos=1-width;
  
  function scroll() {
  pos++;
  
  var scroller="";
  if (pos==lentxt) {
    pos=1-width;
  }
  if (pos<0) {
    for (var i=1; i<=Math.abs(pos); i++) {
      scroller=scroller+" ";}
    scroller=scroller+scrtxt.substring(0,width-i+1);
  }
  else {
    scroller=scroller+scrtxt.substring(pos,width+pos);
  }
  window.status = scroller;
  setTimeout("scroll()",150);
  }
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && document.getElementById) x=document.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
</HEAD>
<BODY leftMargin=0 topmarging="0" onLoad="place2();scroll();return true;"
style="BORDER-BOTTOM: 0px outset; BORDER-LEFT: 0px outset; BORDER-RIGHT: 0px outset; BORDER-TOP: 0px outset" 
topMargin=0 >
<table bgcolor=#ECEAE6 border=1 bordercolordark=#808080 
      bordercolorlight=#ffffff cellpadding=0 cellspacing=0 width="100%" height="20">
  <tbody> 
  <tr bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6> 
    <td noWrap width="5"> 
      <table border=0 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
            cellpadding=0 cellspacing=0 height=22>
        <tbody> 
        <tr> 
          <td align=middle bgcolor=#ECEAE6 bordercolordark=#ECEAE6 
                bordercolorlight=#ECEAE6 noWrap valign=center><img height=22 
                  src="/elearning/mail/image/toolbar1.gif" width=5></td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td align=absmiddle noWrap width="50"> 
      <table border=1 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
            cellpadding=0 cellspacing=0 height=22 
			onClick="window.location.href='/elearning/mail/webmail/folder/personrcd.htm'"
            onMouseDown="javascript:this.borderColorDark='#ffffff';this.borderColorLight='#808080'" 
            onMouseOut="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6';" 
            onMouseOver="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
            onMouseUp="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6'" 
            style="CURSOR: hand" width="100%">
        <tbody> 
        <tr> 
          <td align=center bgcolor=#ECEAE6 bordercolordark=#ECEAE6 
                bordercolorlight=#ECEAE6 noWrap valign=center>添　加</td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td noWrap width="4"> 
      <table border=0 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
            cellpadding=0 cellspacing=0 height=22>
        <tbody> 
        <tr> 
          <td align=middle bgcolor=#ECEAE6 bordercolordark=#ECEAE6 
                bordercolorlight=#ECEAE6 noWrap valign=center><img height=22 
                  src="/elearning/mail/image/toolbar2.gif" width=4></td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td align=absmiddle noWrap width="50"> 
      <table border=1 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
            cellpadding=0 cellspacing=0 height=22 
			onClick="window.location.href='/elearning/mail/webmail/folder/addgrp.htm'"
            onMouseDown="javascript:this.borderColorDark='#ffffff';this.borderColorLight='#808080'" 
            onMouseOut="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6';" 
            onMouseOver="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
            onMouseUp="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6'" 
            style="CURSOR: hand" width="100%">
        <tbody> 
        <tr> 
          <td align=center bgcolor=#ECEAE6 bordercolordark=#ECEAE6 
                bordercolorlight=#ECEAE6 noWrap valign=center>添加组</td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td align=absmiddle noWrap width="5"><img height=22 
                  src="/elearning/mail/image/toolbar2.gif" width=4></td>
    <td align=absmiddle noWrap width="50"> 
      <table border=1 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
            cellpadding=0 cellspacing=0 height=22 
			onClick="window.location.href='/elearning/mail/webmail/folder/b_personrcd.htm'"
            onMouseDown="javascript:this.borderColorDark='#ffffff';this.borderColorLight='#808080'" 
            onMouseOut="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6';" 
            onMouseOver="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
            onMouseUp="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6'" 
            style="CURSOR: hand" width="100%">
        <tbody> 
        <tr> 
          <td align=center bgcolor=#ECEAE6 bordercolordark=#ECEAE6 
                bordercolorlight=#ECEAE6 noWrap valign=center>编　辑</td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td align=absmiddle noWrap width="5"><img height=22 
                  src="/elearning/mail/image/toolbar2.gif" width=4></td>
    <td align=absmiddle noWrap width="50"> 
      <table border=1 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
            cellpadding=0 cellspacing=0 height=22 
			onclick="del()"
            onMouseDown="javascript:this.borderColorDark='#ffffff';this.borderColorLight='#808080'" 
            onMouseOut="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6';" 
            onMouseOver="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
            onMouseUp="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6'" 
            style="CURSOR: hand" width="100%">
        <tbody> 
        <tr> 
          <td align=center bgcolor=#ECEAE6 bordercolordark=#ECEAE6 
                bordercolorlight=#ECEAE6 noWrap valign=center>删　除</td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td align=absmiddle noWrap width="5"><img height=22 
                  src="/elearning/mail/image/toolbar2.gif" width=4></td>
    <td align=absmiddle noWrap width="50"> 
      <table border=1 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
            cellpadding=0 cellspacing=0 height=22 
			onClick="window.location.href='/elearning/mail/webmail/folder/srchmember.htm'"
            onMouseDown="javascript:this.borderColorDark='#ffffff';this.borderColorLight='#808080'" 
            onMouseOut="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6';" 
            onMouseOver="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
            onMouseUp="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6'" 
            style="CURSOR: hand" width="100%">
        <tbody> 
        <tr> 
          <td align=center bgcolor=#ECEAE6 bordercolordark=#ECEAE6 
                bordercolorlight=#ECEAE6 noWrap valign=center>查　找</td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td noWrap width="5"> 
      <table border=0 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
            cellpadding=0 cellspacing=0 height=22>
        <tbody> 
        <tr> 
          <td align=middle bgcolor=#ECEAE6 bordercolordark=#ECEAE6 
                bordercolorlight=#ECEAE6 noWrap valign=center><img height=22 
                  src="/elearning/mail/image/toolbar1.gif" width=5></td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td align=absmiddle noWrap>&nbsp; </td>
    <td align=absmiddle noWrap width="9"> 
      <table border=1 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
            cellpadding=0 cellspacing=0 height=22 
            onMouseDown="javascript:this.borderColorDark='#ffffff';this.borderColorLight='#808080'" 
            onMouseOut="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6';" 
            onMouseOver="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
            onMouseUp="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6'" 
            style="CURSOR: hand">
        <tbody> 
        <tr> 
          <td align=middle bgcolor=#ECEAE6 bordercolordark=#ECEAE6 
                bordercolorlight=#ECEAE6 noWrap valign=center> 
            <table border=0 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
            cellpadding=0 cellspacing=0 height=22>
              <tbody> 
              <tr> 
                <td align=middle bgcolor=#ECEAE6 bordercolordark=#ECEAE6 
                bordercolorlight=#ECEAE6 noWrap valign=center><img height=22 
                  src="/elearning/mail/image/toolbar1.gif" width=5></td>
              </tr>
              </tbody> 
            </table>
          </td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td align=absmiddle noWrap width="30"> 
      <table border=1 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
            cellpadding=0 cellspacing=0 height=22 
            onMouseDown="javascript:this.borderColorDark='#ffffff';this.borderColorLight='#808080'" 
            onMouseOut="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6';" 
            onMouseOver="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
            onMouseUp="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6'" 
            style="CURSOR: hand" width="100%">
        <tbody> 
        <tr> 
          <td align=center bgcolor=#ECEAE6 bordercolordark=#ECEAE6 
                bordercolorlight=#ECEAE6 noWrap valign=center><a href="javascript:firstPage()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image15','','/elearning/mail/image/back2.gif',1)"><img name="Image15" border="0" src="/elearning/mail/image/back1.gif" width="20" height="15" alt="首页"></a></td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td align=absmiddle noWrap width="30"> 
      <table border=1 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
            cellpadding=0 cellspacing=0 height=22 
            onMouseDown="javascript:this.borderColorDark='#ffffff';this.borderColorLight='#808080'" 
            onMouseOut="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6';" 
            onMouseOver="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
            onMouseUp="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6'" 
            style="CURSOR: hand" width="100%">
        <tbody> 
        <tr> 
          <td align=center bgcolor=#ECEAE6 bordercolordark=#ECEAE6 
                bordercolorlight=#ECEAE6 noWrap valign=center><a href="javascript:backPage()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image16','','/elearning/mail/image/backward2.gif',1)"><img name="Image16" border="0" src="/elearning/mail/image/backward1.gif" width="20" height="15" alt="上一页"></a></td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td align=absmiddle noWrap width="30"> 
      <table border=1 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
            cellpadding=0 cellspacing=0 height=22 
            onMouseDown="javascript:this.borderColorDark='#ffffff';this.borderColorLight='#808080'" 
            onMouseOut="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6';" 
            onMouseOver="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
            onMouseUp="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6'" 
            style="CURSOR: hand" width="100%">
        <tbody> 
        <tr> 
          <td align=center bgcolor=#ECEAE6 bordercolordark=#ECEAE6 
                bordercolorlight=#ECEAE6 noWrap valign=center><a href="javascript:nextPage()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image17','','/elearning/mail/image/forward2.gif',1)"><img name="Image17" border="0" src="/elearning/mail/image/forward1.gif" width="20" height="15" alt="下一页"></a></td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td align=absmiddle noWrap width="30"> 
      <table border=1 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
            cellpadding=0 cellspacing=0 height=22 
            onMouseDown="javascript:this.borderColorDark='#ffffff';this.borderColorLight='#808080'" 
            onMouseOut="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6';" 
            onMouseOver="javascript:this.borderColorDark='#808080';this.borderColorLight='#ffffff';" 
            onMouseUp="javascript:this.borderColorDark='#ECEAE6';this.borderColorLight='#ECEAE6'" 
            style="CURSOR: hand" width="100%">
        <tbody> 
        <tr> 
          <td align=center bgcolor=#ECEAE6 bordercolordark=#ECEAE6 
                bordercolorlight=#ECEAE6 noWrap valign=center><a href="javascript:lastPage()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image18','','/elearning/mail/image/front2.gif',1)"><img name="Image18" border="0" src="/elearning/mail/image/front1.gif" width="20" height="15" alt="尾页"></a></td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td align=absmiddle noWrap width="5"> 
      <table border=0 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
            cellpadding=0 cellspacing=0 height=22>
        <tbody> 
        <tr> 
          <td align=middle bgcolor=#ECEAE6 bordercolordark=#ECEAE6 
                bordercolorlight=#ECEAE6 noWrap valign=center><img height=22 
                  src="/elearning/mail/image/toolbar1.gif" width=5></td>
        </tr>
        </tbody> 
      </table>
    </td>
  </tr>
  </tbody> 
</table>
<table bgcolor=#f6f6f6 border=0 bordercolordark=#808080 bordercolorlight=#ffffff 
cellpadding=0 cellspacing=1 height=20 width="100%">
  <tbody> 
  <form name="logHistory" method="post" action="query">
  <tr>
    <td noWrap width="5%" bgcolor="#FFFFFF">
      <table bgcolor=#ECEAE6 border=1 bordercolordark=#808080 
      bordercolorlight=#ffffff cellpadding=0 cellspacing=0 height=20 width="100%">
        <tbody> 
        <tr> 
          <td bgcolor=#ECEAE6 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
          noWrap align="center">&nbsp;</td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td noWrap width="5%" bgcolor="#FFFFFF"> 
      <table bgcolor=#ECEAE6 border=1 bordercolordark=#808080 
      bordercolorlight=#ffffff cellpadding=0 cellspacing=0 height=20 width="100%">
        <tbody> 
        <tr> 
          <td bgcolor=#ECEAE6 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
          noWrap align="center">姓 名</td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td noWrap width="15%" bgcolor="#FFFFFF"> 
      <table bgcolor=#ECEAE6 border=1 bordercolordark=#808080 
      bordercolorlight=#ffffff cellpadding=0 cellspacing=0 height=20  width="100%">
        <tbody> 
        <tr> 
          <td bgcolor=#ECEAE6 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
          noWrap align="center">公   司</td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td noWrap width="25%" bgcolor="#FFFFFF"> 
      <table bgcolor=#ECEAE6 border=1 bordercolordark=#808080 
      bordercolorlight=#ffffff cellpadding=0 cellspacing=0 height=20 width="100%">
        <tbody> 
        <tr> 
          <td bgcolor=#ECEAE6 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
          noWrap align="center">邮件地址</td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td noWrap width="15%" bgcolor="#FFFFFF"> 
      <table bgcolor=#ECEAE6 border=1 bordercolordark=#808080 
      bordercolorlight=#ffffff cellpadding=0 cellspacing=0 height=20  width="100%">
        <tbody> 
        <tr> 
          <td bgcolor=#ECEAE6 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
          noWrap align="center">电话</td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td noWrap width="15%" bgcolor="#FFFFFF"> 
      <table bgcolor=#ECEAE6 border=1 bordercolordark=#808080 
      bordercolorlight=#ffffff cellpadding=0 cellspacing=0 height=20 width="100%">
        <tbody> 
        <tr> 
          <td bgcolor=#ECEAE6 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
          noWrap align="center">邮编</td>
        </tr>
        </tbody> 
      </table>
    </td>
    <td noWrap width="25%" bgcolor="#FFFFFF"> 
      <table bgcolor=#ECEAE6 border=1 bordercolordark=#808080 
      bordercolorlight=#ffffff cellpadding=0 cellspacing=0 height=20  width="100%">
        <tbody> 
        <tr> 
          <td bgcolor=#ECEAE6 bordercolordark=#ECEAE6 bordercolorlight=#ECEAE6 
          noWrap align="center">住址</td>
        </tr>
        </tbody> 
      </table>
    </td>
  </tr>
 
 	<%
	String pageno = (String)request.getAttribute("pageno");
	if (pageno == null) pageno = "1";
	int listCount = Integer.parseInt(ust.getLines_in_page());
	int pageNum = Integer.parseInt(pageno);
	int maxCount = java.lang.Math.min(bgd.getRowCount(), (pageNum-1)*listCount+listCount);
	int i_count = (int)java.lang.Math.ceil((double)bgd.getRowCount()/listCount);
	for (int i=0; i<maxCount-(pageNum-1)*listCount; i++)
	{
	%>

    <tr onmouseout='this.style.backgroundColor="#ffffff"' 
  onmouseover='this.style.backgroundColor="#EFEFEF"' style="CURSOR: hand" bgcolor="#FFFFFF"> 
      <td noWrap align="center" height="16"> 
        <input type="checkbox" name="r" value="checkbox">
    </td>
      <td noWrap height="16" style="padding-left:15px"> <img src="/elearning/mail/image/personal.gif" height="20"> 
        <%=bgd.getFieldValue("name",i+(pageNum-1)*listCount)%>&nbsp;</td>
      <td noWrap align="center" height="16"><%=bgd.getFieldValue("company",i+(pageNum-1)*listCount)%>&nbsp;</td>
      <td noWrap style="padding-left:6px" height="16"><%=bgd.getFieldValue("email",i+(pageNum-1)*listCount)%>&nbsp;</td>
      <td noWrap align="center" height="16"><%=bgd.getFieldValue("tel",i+(pageNum-1)*listCount)%>&nbsp;</td>
      <td noWrap align="center" height="16"><%=bgd.getFieldValue("postcode",i+(pageNum-1)*listCount)%>&nbsp;</td>
      <td noWrap align="center" height="16"><%=bgd.getFieldValue("address",i+(pageNum-1)*listCount)%>&nbsp;</td>
  </tr>
<%}%>
<INPUT name="countInPage" type="hidden" id="countInPage" value="<%= maxCount-(pageNum-1)*listCount%>">
<INPUT name="order" type="hidden" id="order" value="<%= order%>">
<INPUT name="action" type="hidden" id="action" value="<%= action%>">
<INPUT name="pageno" type="hidden" id="pageno" value="<%= pageno%>">
<INPUT name="queryItem" type="hidden" id="queryItem" value="<%= queryItem%>">
</form>
  </tbody> 
</table>
<div id=menuDiv>
   
</div>
</BODY>
<script language="JavaScript">
<!--
function nextPage()
{
	if(parseInt(document.logHistory.pageno.value)>=<%= i_count%>) {
		document.logHistory.pageno.value = <%= i_count%>;
		alert("已经到最后一页了");
	}else{
		document.logHistory.pageno.value = parseInt(document.logHistory.pageno.value)+1;
	}
	document.logHistory.submit();
	return true;
}
function backPage()
{
	if((parseInt(document.logHistory.pageno.value)-1)<1) {
		document.logHistory.pageno.value = 1;
		alert("已经到第一页了");
	}else{
		document.logHistory.pageno.value = parseInt(document.logHistory.pageno.value)-1;
	}
	document.logHistory.submit();
	return true;
}
function lastPage()
{
	document.logHistory.pageno.value = <%=i_count%>;
	document.logHistory.submit();
	return true;
}
function firstPage()
{
	document.logHistory.pageno.value = 1;
	document.logHistory.submit();
	return true;
}
//-->
</script>
</HTML>
