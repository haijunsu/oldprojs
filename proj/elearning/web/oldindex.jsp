<%@ page contentType="text/html;charset=GB2312" %>
<%@page session="true"%>
<%@page import="com.htyz.*, elearn.bean_Menus"%>
<html>
<head>
<title>环天宇正 Ｅ－Ｌｅａｒｎｉｎｇ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="/elearning/3.css">
<style type="text/css">
<!--
BODY {SCROLLBAR-FACE-COLOR: #6a7f9a;
SCROLLBAR-HIGHLIGHT-COLOR: #000000;
SCROLLBAR-SHADOW-COLOR: #339966;
SCROLLBAR-3DLIGHT-COLOR: #cccccc;
SCROLLBAR-ARROW-COLOR: #000000;
SCROLLBAR-TRACK-COLOR: #6a7f9a;
SCROLLBAR-DARKSHADOW-COLOR: #000000}
-->
</style>
</head>

<body >
<table width="758" border="0" cellspacing="1" cellpadding="1" align="center" bgcolor="#000000">
<tr bgcolor="#FFFFFF">
<td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="32%"><img src="/elearning/logo.gif" width="221" height="80"></td>
<td width="68%">
            <div align="center"><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0" width="468" height="60">
                <param name=movie value="/elearning/images/banner.swf">
                <param name=quality value=high>
                <embed src="/elearning/images/banner.swf" quality=high pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="468" height="60">
                </embed> 
              </object></div>
</td>
</tr>
</table>
</td>
</tr>
<tr bgcolor="#6a7f9a">
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td width="495" height="32" class="jnfont6"> 
            <jsp:include page="/Menus.jsp"/>
            <jsp:include page="/MsgNotify.jsp"/>
          </td>
          <td width="194" height="32"> 
            <table width="65%" border="0" cellspacing="0" cellpadding="0" align="center">
              <tr> 
                <td width="38%"><img src="/elearning/images/niux-home.gif" width="40" height="33"></td>
                <td width="35%"><img src="/elearning/images/niux-f.gif" width="40" height="33" alt="加入收藏夹"></td>
                <td width="27%"><a href="mailto:39team@niux.com"></a><img src="/elearning/images/niux-mail.gif" width="40" height="33" alt="联系我们"></td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
</tr>
</table>
<table width="758" border="0" cellspacing="0" cellpadding="0" align="center">
<tr>
<td height="10">
</td>
</tr>
</table>
<table width="758" border="0" cellspacing="1" cellpadding="1" align="center" bgcolor="#000000">
<tr bgcolor="#6a7f9a">
<td height="415">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>&nbsp;</td>
</tr>
<tr>
<td>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" height="140">
<tr>
<td width="25%" height="110">
<table cellspacing=1 cellpadding=0 width="95%" align=center
bgcolor=#000000 border=0 height="100">
<tbody>
<tr bgcolor=#F0F0F0>
<td height=138>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
<table width=158 border=0>
<tbody>
<tr>
                                  <td align=middle width=31><img height=20
src="/elearning/images/more3.gif"
width=20></td>
                                  <td class=jnfont6 width=117> <b>E-learning 登录</b></td>
</tr>
</tbody>
</table>
</td>
</tr>
</table>
<table height=5 cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td></td>
</tr>
</tbody>
</table>
<table cellspacing=1 cellpadding=0 width="92%"
align=center bgcolor=#000000 border=0 height="97">
<tbody>
<tr bgcolor=#ffffff>
<td height="97"><%if (session.getAttribute("userid")==null||session.getAttribute("userid").equals("")){%>

<font color=#ccccff> </font><font color=#ccccff>
</font><font color=#ccccff> </font><font color=#ccccff>
</font>
<form  name="form1" action="servlet/elearn.bean_UserLogin" method="post">
<div align="center"><br>名<font
color=#d1d5ed class="jnfont6">称:</font>
                                    <input class=input1 size=11 id="userid" name="userid" >
<br>
<font color=#d1d5ed class="jnfont6">口令:</font>
                                    <input class=input1
type=password size=11  id="pass" name="pass">
<br><br>
                         
                                  <font color=#ccccff class="jnfont6">
                                  <input type="image" src="/elearning/images/btn_1.gif" alt="登陆">
                                  　<a href="#"><img src="/elearning/images/btn_2.gif" width="40" height="20" onClick=window.open("servlet/elearn.bean_UserProfiles?action=apply","_blank","top=0,left=0,width=400,height=450,status=no") alt="注册" border="0"></a> 
                                  </font> </div>
</form>
<%}
else{%>
<%=session.getAttribute("userid")%>，欢迎您登陆到这里！
<%}%>                           
</td>
</tr>
</tbody>
</table>
<table height=5 cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td></td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
</td>
<td width="47%" height="110">
<table cellspacing=0 cellpadding=0 width=95% align=center
border=0 height="100">
<tbody>
<tr>
<td width=1 bgcolor=#000000 rowspan=3><img height=1
src="新世纪广告人——网页制作.files/shim.gif" width=1></td>
<td bgcolor=#000000><img height=1
src="新世纪广告人——网页制作.files/shim.gif" width=1></td>
<td width=1 bgcolor=#000000 rowspan=3><img height=1
src="新世纪广告人——网页制作.files/shim.gif" width=1></td>
</tr>
<tr>
<td align=middle width=378>
<table cellspacing=0 cellpadding=0 width="100%"
align=center border=0>
<tbody>
<tr>
<td width=31 bgcolor="#F0F0F0">
<div align="center"><img src="/elearning/images/more01.gif" width="15" height="14"></div>
</td>
<td width=225
height=30 bgcolor="#F0F0F0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td class="jnfont6"><span class=p14>站 务 新 闻</span></td>
</tr>
</table>
</td>
<td width=62
height=30 bgcolor="#F0F0F0"><img src="/elearning/images/arrow01.gif" width="13" height="13">
<img src="/elearning/images/arrow02.gif" width="13" height="13">
</td>
</tr>
<tr>
<td colspan=3>
<table cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td width=10 bgcolor="#F0F0F0"><img
height=1 src="新世纪广告人——网页制作.files/shim.gif"
width=1></td>
<td>
<table cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td bgcolor=#000000 height=1 rowspan=4><img
height=1 src="新世纪广告人——网页制作.files/shim.gif"
width=1></td>
<td bgcolor=#000000 colspan=2><img height=1
src="新世纪广告人——网页制作.files/shim.gif" width=1></td>
<td bgcolor=#000000 height=1 rowspan=2><img
height=1 src="新世纪广告人——网页制作.files/shim.gif"
width=1></td>
</tr>
<tr>
<td bgcolor=#ffffff height=11>
<table cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td width=12 rowspan=2><img height=1
src="新世纪广告人——网页制作.files/shim.gif" width=1></td>
<td height=12><img height=1
src="新世纪广告人——网页制作.files/shim.gif"
width=1></td>
</tr>
<tr>
<td>
<table width="100%">
<tbody>
<tr>
<td width="9%"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="91%"><a
href="index3.htm">2001/09/13 狂派日访问IP已达到1000次。
</a></td>
</tr>
<tr>
<td width="9%"><img height=7
src="/elearning/images/ra.gif" width=7></td>
<td width="91%"><a
href="index3.htm">2001/09/09 第一个免费ASP空间申请通过。 </a></td>
</tr>
<tr>
<td width="9%"><img height=7
src="/elearning/images/ra.gif" width=7></td>
<td width="91%"><a
href="index3.htm">2001/09/05 技术论坛投入使用。</a></td>
</tr>
<tr>
<td width="9%"><img height=7
src="/elearning/images/ra.gif" width=7></td>
<td width="91%"><a
href="index3.htm">2001/09/03 狂派正试开通，欢迎来访。</a></td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
</td>
<td width=12 bgcolor=#ffffff height=11><img
height=1 src="新世纪广告人——网页制作.files/shim.gif"
width=1></td>
</tr>
<tr>
<td bgcolor=#ffffff height=12><img height=1
src="新世纪广告人——网页制作.files/shim.gif" width=1></td>
<td colspan=2 rowspan=2><img height=13
src="/elearning/images/htmlcn_01.GIF"
width=13></td>
</tr>
<tr>
<td bgcolor=#000000><img height=1
src="新世纪广告人——网页制作.files/shim.gif"
width=1></td>
</tr>
</tbody>
</table>
</td>
<td width=10 bgcolor="#F0F0F0"><img
height=1 src="新世纪广告人——网页制作.files/shim.gif"
width=1></td>
</tr>
</tbody>
</table>
</td>
</tr>
<tr bgcolor="#F0F0F0">
<td colspan=3
height=10><img height=1
src="新世纪广告人——网页制作.files/shim.gif"
width=1></td>
</tr>
</tbody>
</table>
</td>
</tr>
<tr>
<td width=1 bgcolor=#000000><img height=1
src="新世纪广告人——网页制作.files/shim.gif"
width=1></td>
</tr>
</tbody>
</table>
</td>
<td colspan="3" height="110" width="28%">
<table cellspacing=1 cellpadding=0 width="95%" align=center
bgcolor=#000000 border=0 height="100">
<tbody>
<tr bgcolor=#F0F0F0>
<td height=138>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
<table width=158 border=0>
<tbody>
<tr>
<td align=middle width=28><img height=20
src="/elearning/images/more3.gif"
width=20></td>
                                  <td class=jnfont6 width=120>本 站 公 告 板</td>
</tr>
</tbody>
</table>
</td>
</tr>
</table>
<table height=5 cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td></td>
</tr>
</tbody>
</table>
<table cellspacing=1 cellpadding=0 width="92%"
align=center bgcolor=#000000 border=0 height="97">
<tbody>
<tr bgcolor=#ffffff>
<td height="96"><font color=#ccccff> </font><font color=#ccccff>
</font><font color=#ccccff> </font><font color=#ccccff>
</font><font color=#ccccff>
<script language=JavaScript1.2>

var marqueewidth=120
var marqueeheight=95
var speed=1
var marqueecontents='<font color="#000000"> &nbsp 为您提供专业的电子<br>&nbsp;&nbsp;化教育方案！&nbsp;&nbsp;<br>&nbsp;&nbsp;................<br><br>&nbsp;&nbsp;为您的企业提供全<br>&nbsp;&nbsp;面的培训解决方案！<br>&nbsp;&nbsp;................<br><br>&nbsp;&nbsp;为您的企业建立科<br>&nbsp;&nbsp;学的培训体系！<br>&nbsp;&nbsp;................<br><br>&nbsp;&nbsp;环天宇正给您最广<br>&nbsp;&nbsp;阔的想像空间！<br>&nbsp;&nbsp;................<br>&nbsp;&nbsp;如有您的公司面临着<br>&nbsp;&nbsp;时代进步的挑战和企<br>&nbsp;&nbsp;业进步的设想！<br>&nbsp;&nbsp;我们的产品在最短<br>&nbsp;&nbsp;的时间内为您提供<br>&nbsp;&nbsp;最强大的解决方案<br>&nbsp;&nbsp;&nbsp;&nbsp;<b>E-learning</b><br>&nbsp;&nbsp;................<br><br>&nbsp;&nbsp;         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--环天宇正</font>'
if (document.all)
document.write('<marquee direction="up" scrollAmount='+speed+' style="width:'+marqueewidth+';height:'+marqueeheight+'">'+marqueecontents+'</marquee>')
function regenerate(){
window.location.reload()
}
function regenerate2(){
if (document.layers){
setTimeout("window.onresize=regenerate",450)
intializemarquee()
}
}
function intializemarquee(){
document.cmarquee01.document.cmarquee02.document.write(marqueecontents)
document.cmarquee01.document.cmarquee02.document.close()
thelength=document.cmarquee01.document.cmarquee02.document.height
scrollit()
}
function scrollit(){
if (document.cmarquee01.document.cmarquee02.top>=thelength*(-1)){
document.cmarquee01.document.cmarquee02.top-=speed
setTimeout("scrollit()",100)
}
else{
document.cmarquee01.document.cmarquee02.top=marqueeheight
scrollit()
}
}
window.onload=regenerate2
</script>
</font></td>
</tr>
</tbody>
</table>
<table height=5 cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td></td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</table>
</td>
</tr>
<tr>
<td>&nbsp;</td>
</tr>
<tr>
<td height="232">
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" height="140">
<tr>
<td width="25%" height="227">
<table cellspacing=1 cellpadding=0 width="95%" align=center
bgcolor=#000000 border=0 height="100">
<tbody>
<tr bgcolor=#F0F0F0>
<td height=226>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
<table width=158 border=0>
<tbody>
<tr>
<td align=middle width=31><img height=20
src="/elearning/images/more3.gif"
width=20></td>
                                  <td class=jnfont6 width=117> 每 周 酷 课 推 荐</td>
</tr>
</tbody>
</table>
</td>
</tr>
</table>
<table height=5 cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td></td>
</tr>
</tbody>
</table>
<table cellspacing=1 cellpadding=0 width="92%"
align=center bgcolor=#000000 border=0 height="97">
<tbody>
<tr bgcolor=#ffffff>
<td height="97"><font color=#ccccff> </font><font color=#ccccff>
</font><font color=#ccccff> </font>
<table width="96%" border="0" cellspacing="5" cellpadding="0" align="center">
<tr>
<td>
<div align="center"><img src="/elearning/images/goeway.jpg" width="133" height="100"></div>
</td>
</tr>
<tr>
                                  <td>课程名称: --Word三日通--</td>
</tr>
<tr>
                                  <td>课程作者: 谭三天</td>
</tr>
<tr>
                                  <td>
                                    <div align="right">课程介绍: 让你三日内完全<br>
                                      掌握  Word  2002<br>
                                      各种使用方法。</div>
                                  </td>
</tr>
<tr>
<td>推荐指数:<font color="#CC6600">☆☆☆☆☆</font></td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
<table height=5 cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td height="9"></td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
</td>
<td width="47%" height="227">
<table cellspacing=0 cellpadding=0 width=95% align=center
border=0 height="100">
<tbody>
<tr>
<td width=1 bgcolor=#000000 rowspan=3><img height=1
src="新世纪广告人——网页制作.files/shim.gif" width=1></td>
<td bgcolor=#000000><img height=1
src="新世纪广告人——网页制作.files/shim.gif" width=1></td>
<td width=1 bgcolor=#000000 rowspan=3><img height=1
src="新世纪广告人——网页制作.files/shim.gif" width=1></td>
</tr>
<tr>
<td align=middle width=378>
<table cellspacing=0 cellpadding=0 width="100%"
align=center border=0>
<tbody>
<tr>
<td width=31 bgcolor="#F0F0F0">
<div align="center"><img src="/elearning/images/more01.gif" width="15" height="14"></div>
</td>
<td colspan="2"
height=30 bgcolor="#F0F0F0" width=303>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
                                  <td class="jnfont6" width="54%">您已选的课程</td>
                                  <td class="jnfont6" width="46%">从<a href="#">这里</a>选新课</td>
</tr>
</table>
</td>
</tr>
<tr>
<td colspan=3>
<table cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td width=10 bgcolor="#F0F0F0"><img
height=1 src="新世纪广告人——网页制作.files/shim.gif"
width=1></td>
<td>
<table cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td bgcolor=#000000 height=1 rowspan=4><img
height=1 src="新世纪广告人——网页制作.files/shim.gif"
width=1></td>
<td bgcolor=#000000 colspan=2><img height=1
src="新世纪广告人——网页制作.files/shim.gif" width=1></td>
<td bgcolor=#000000 height=1 rowspan=2><img
height=1 src="新世纪广告人——网页制作.files/shim.gif"
width=1></td>
</tr>
<tr>
                                        <td bgcolor=#ffffff height=174>afasfasfasfdasfasfafasfasfasfasfasfasasfd 
                                        </td>
<td width=12 bgcolor=#ffffff height=174><img
height=1 src="新世纪广告人——网页制作.files/shim.gif"
width=1></td>
</tr>
<tr>
<td bgcolor=#ffffff height=12><img height=1
src="新世纪广告人——网页制作.files/shim.gif" width=1></td>
<td colspan=2 rowspan=2><img height=13
src="/elearning/images/htmlcn_01.GIF"
width=13></td>
</tr>
<tr>
<td bgcolor=#000000><img height=1
src="新世纪广告人——网页制作.files/shim.gif"
width=1></td>
</tr>
</tbody>
</table>
</td>
<td width=10 bgcolor="#F0F0F0"><img
height=1 src="新世纪广告人——网页制作.files/shim.gif"
width=1></td>
</tr>
</tbody>
</table>
</td>
</tr>
<tr bgcolor="#F0F0F0">
<td colspan=3
height=10><img height=1
src="新世纪广告人——网页制作.files/shim.gif"
width=1></td>
</tr>
</tbody>
</table>
</td>
</tr>
<tr>
<td width=1 bgcolor=#000000><img height=1
src="新世纪广告人——网页制作.files/shim.gif"
width=1></td>
</tr>
</tbody>
</table>
</td>
<td colspan="3" height="227" width="28%">
<table cellspacing=1 cellpadding=0 width="95%" align=center
bgcolor=#000000 border=0 height="100">
<tbody>
<tr bgcolor=#F0F0F0>
<td height=138>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
<table width=158 border=0>
<tbody>
<tr>
<td align=middle width=28><img height=20
src="/elearning/images/more3.gif"
width=20></td>
                                  <td class=jnfont6 width=120>其 它 相 关 网 站</td>
</tr>
</tbody>
</table>
</td>
</tr>
</table>
<table height=5 cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td></td>
</tr>
</tbody>
</table>
<table cellspacing=1 cellpadding=0 width="92%"
align=center bgcolor=#000000 border=0 height="97">
<tbody>
<tr bgcolor=#ffffff>
<td height="185"><font color=#ccccff> </font><font color=#ccccff>
</font><font color=#ccccff> </font>
<table width="98%" border="0" cellspacing="4" cellpadding="0" align="center">
<tr>
<td width="8%"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%">午夜的多彩天空...最新加盟</td>
</tr>
<tr>
<td width="8%"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%">多彩驿站V2.0.....全新改版</td>
</tr>
<tr>
<td width="8%"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%">曦晨软件园...........更新</td>
</tr>
<tr>
<td width="8%" height="18"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%" height="18">啊哟的网上小家...最新加盟</td>
</tr>
<tr>
<td width="8%"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%">榕树下...........最新加盟</td>
</tr>
<tr>
<td width="8%"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%">有风的日子v4.0...全新改版</td>
</tr>
<tr>
<td width="8%"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%">新亚个人主页.........更新</td>
</tr>
<tr>
<td width="8%"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%">红楼遗梦v3.0.....最新加盟</td>
</tr>
<tr>
<td width="8%"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%">一号空间.........最新加盟</td>
</tr>
<tr>
<td width="8%"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%">蓝球天地.........最新加盟</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
<table height=5 cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td></td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</table>
</td>
</tr>
<tr>
<td>&nbsp;</td>
</tr>
</table>
</td>
</tr>
</table>
<table width="758" border="0" cellspacing="0" cellpadding="0" align="center">
<tr>
<td>&nbsp;</td>
</tr>
</table>
<table width="758" border="0" cellspacing="1" cellpadding="1" align="center" bgcolor="#000000">
<tr bgcolor="#5f7189">
<td height="196">
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" height="140">
<tr>
<td width="40%" height="172">
<table cellspacing=1 cellpadding=0 width="95%" align=center
bgcolor=#000000 border=0 height="100">
<tbody>
<tr bgcolor=#F0F0F0>
<td height=162>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
<table width=158 border=0>
<tbody>
<tr>
<td align=middle width=31><img height=20
src="/elearning/images/more3.gif"
width=20></td>
<td class=jnfont6 width=117> 设 计 联 盟</td>
</tr>
</tbody>
</table>
</td>
</tr>
</table>
<table height=5 cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td></td>
</tr>
</tbody>
</table>
<table cellspacing=1 cellpadding=0 width="92%"
align=center bgcolor=#000000 border=0 height="97">
<tbody>
<tr bgcolor=#ffffff>
<td height="97"><font color=#ccccff> </font><font color=#ccccff>
</font><font color=#ccccff> </font>
<table width="96%" border="0" cellspacing="5" cellpadding="0" align="center">
<tr>
<td rowspan="4" width="45%"><img src="/elearning/images/xlgzs.gif" width="113" height="98"></td>
<td width="55%"><img src="/elearning/images/2001-2-39.gif" width="11" height="8">
申请加盟</td>
</tr>
<tr>
<td width="55%"><img src="/elearning/images/2001-2-39.gif" width="11" height="8">
作品欣赏（平面）</td>
</tr>
<tr>
<td width="55%"><img src="/elearning/images/2001-2-39.gif" width="11" height="8">
作品欣赏（动画）</td>
</tr>
<tr>
<td width="55%"><img src="/elearning/images/2001-2-39.gif" width="11" height="8">
关于设计联盟 </td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
<table height=5 cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td height="9"></td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
</td>
<td colspan="4" height="172" width="60%">
<table cellspacing=1 cellpadding=0 width="98%" align=center
bgcolor=#000000 border=0 height="100">
<tbody>
<tr bgcolor=#F0F0F0>
<td height=162>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
<table width=385 border=0>
<tbody>
<tr>
<td align=middle width=25><img height=20
src="/elearning/images/more3.gif"
width=20></td>
<td class=jnfont6 width=137> 最 新 作 品 欣 赏</td>
<td class=jnfont6 width=209>
<div align="right">已收录作品<b><font color="#42566f">76</font></b>个
</div>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</table>
<table height=5 cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td></td>
</tr>
</tbody>
</table>
<table cellspacing=1 cellpadding=0 width="92%"
align=center bgcolor=#000000 border=0 height="97">
<tbody>
<tr bgcolor=#ffffff>
<td height="97"><font color=#ccccff> </font><font color=#ccccff>
</font><font color=#ccccff> </font>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
<tr>
<td rowspan="4" width="51%">
<table width="100%" border="0" cellspacing="5" cellpadding="0">
<tr>
<td rowspan="3" width="45%"><img src="/elearning/images/ludong.jpg" width="100" height="98"></td>
<td width="55%" height="27">名称:流动人体</td>
</tr>
<tr>
<td width="55%" height="24">作者:午夜~</td>
</tr>
<tr>
<td width="55%">类别:动画作品</td>
</tr>
</table>
</td>
<td rowspan="4" width="49%">
<table width="100%" border="0" cellspacing="5" cellpadding="0">
<tr>
<td rowspan="3" width="45%"><img src="/elearning/images/0090m.jpg" width="100" height="98"></td>
<td width="55%" height="27">名称:瞬间</td>
</tr>
<tr>
<td width="55%" height="24">作者:飞猫</td>
</tr>
<tr>
<td width="55%">类别:平面作品</td>
</tr>
</table>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
<table height=5 cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td height="9"></td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</table>
</td>
</tr>
<tr bgcolor="#5f7189">
<td height="43">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td width="42%">&nbsp;</td>
          <td colspan="2" width="58%">&nbsp;</td>
        </tr>
        <tr> 
          <td colspan="3">
            <div align="center"><b>http://www.huantian.com.cn 北京环天宇正&trade;发技发展有限公司<br>
              北京朝阳区安华里瑞得大厦807 电话：010-82031610 传真：010-82031613</b></div>
          </td>
        </tr>
        <tr> 
          <td width="42%">&nbsp;</td>
          <td colspan="2" width="58%">&nbsp;</td>
        </tr>
      </table>
</td>
</tr>
</table>
</body>
</html>

<html><script language="JavaScript">                                                                  </script></html>
