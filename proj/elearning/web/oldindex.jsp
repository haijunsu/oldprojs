<%@ page contentType="text/html;charset=GB2312" %>
<%@page session="true"%>
<%@page import="com.htyz.*, elearn.bean_Menus"%>
<html>
<head>
<title>�������� �ţ��̣�������</title>
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
                <td width="35%"><img src="/elearning/images/niux-f.gif" width="40" height="33" alt="�����ղؼ�"></td>
                <td width="27%"><a href="mailto:39team@niux.com"></a><img src="/elearning/images/niux-mail.gif" width="40" height="33" alt="��ϵ����"></td>
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
                                  <td class=jnfont6 width=117> <b>E-learning ��¼</b></td>
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
<div align="center"><br>��<font
color=#d1d5ed class="jnfont6">��:</font>
                                    <input class=input1 size=11 id="userid" name="userid" >
<br>
<font color=#d1d5ed class="jnfont6">����:</font>
                                    <input class=input1
type=password size=11  id="pass" name="pass">
<br><br>
                         
                                  <font color=#ccccff class="jnfont6">
                                  <input type="image" src="/elearning/images/btn_1.gif" alt="��½">
                                  ��<a href="#"><img src="/elearning/images/btn_2.gif" width="40" height="20" onClick=window.open("servlet/elearn.bean_UserProfiles?action=apply","_blank","top=0,left=0,width=400,height=450,status=no") alt="ע��" border="0"></a> 
                                  </font> </div>
</form>
<%}
else{%>
<%=session.getAttribute("userid")%>����ӭ����½�����
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
src="�����͹���ˡ�����ҳ����.files/shim.gif" width=1></td>
<td bgcolor=#000000><img height=1
src="�����͹���ˡ�����ҳ����.files/shim.gif" width=1></td>
<td width=1 bgcolor=#000000 rowspan=3><img height=1
src="�����͹���ˡ�����ҳ����.files/shim.gif" width=1></td>
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
<td class="jnfont6"><span class=p14>վ �� �� ��</span></td>
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
height=1 src="�����͹���ˡ�����ҳ����.files/shim.gif"
width=1></td>
<td>
<table cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td bgcolor=#000000 height=1 rowspan=4><img
height=1 src="�����͹���ˡ�����ҳ����.files/shim.gif"
width=1></td>
<td bgcolor=#000000 colspan=2><img height=1
src="�����͹���ˡ�����ҳ����.files/shim.gif" width=1></td>
<td bgcolor=#000000 height=1 rowspan=2><img
height=1 src="�����͹���ˡ�����ҳ����.files/shim.gif"
width=1></td>
</tr>
<tr>
<td bgcolor=#ffffff height=11>
<table cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td width=12 rowspan=2><img height=1
src="�����͹���ˡ�����ҳ����.files/shim.gif" width=1></td>
<td height=12><img height=1
src="�����͹���ˡ�����ҳ����.files/shim.gif"
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
href="index3.htm">2001/09/13 �����շ���IP�Ѵﵽ1000�Ρ�
</a></td>
</tr>
<tr>
<td width="9%"><img height=7
src="/elearning/images/ra.gif" width=7></td>
<td width="91%"><a
href="index3.htm">2001/09/09 ��һ�����ASP�ռ�����ͨ���� </a></td>
</tr>
<tr>
<td width="9%"><img height=7
src="/elearning/images/ra.gif" width=7></td>
<td width="91%"><a
href="index3.htm">2001/09/05 ������̳Ͷ��ʹ�á�</a></td>
</tr>
<tr>
<td width="9%"><img height=7
src="/elearning/images/ra.gif" width=7></td>
<td width="91%"><a
href="index3.htm">2001/09/03 �������Կ�ͨ����ӭ���á�</a></td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
</td>
<td width=12 bgcolor=#ffffff height=11><img
height=1 src="�����͹���ˡ�����ҳ����.files/shim.gif"
width=1></td>
</tr>
<tr>
<td bgcolor=#ffffff height=12><img height=1
src="�����͹���ˡ�����ҳ����.files/shim.gif" width=1></td>
<td colspan=2 rowspan=2><img height=13
src="/elearning/images/htmlcn_01.GIF"
width=13></td>
</tr>
<tr>
<td bgcolor=#000000><img height=1
src="�����͹���ˡ�����ҳ����.files/shim.gif"
width=1></td>
</tr>
</tbody>
</table>
</td>
<td width=10 bgcolor="#F0F0F0"><img
height=1 src="�����͹���ˡ�����ҳ����.files/shim.gif"
width=1></td>
</tr>
</tbody>
</table>
</td>
</tr>
<tr bgcolor="#F0F0F0">
<td colspan=3
height=10><img height=1
src="�����͹���ˡ�����ҳ����.files/shim.gif"
width=1></td>
</tr>
</tbody>
</table>
</td>
</tr>
<tr>
<td width=1 bgcolor=#000000><img height=1
src="�����͹���ˡ�����ҳ����.files/shim.gif"
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
                                  <td class=jnfont6 width=120>�� վ �� �� ��</td>
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
var marqueecontents='<font color="#000000"> &nbsp Ϊ���ṩרҵ�ĵ���<br>&nbsp;&nbsp;������������&nbsp;&nbsp;<br>&nbsp;&nbsp;................<br><br>&nbsp;&nbsp;Ϊ������ҵ�ṩȫ<br>&nbsp;&nbsp;�����ѵ���������<br>&nbsp;&nbsp;................<br><br>&nbsp;&nbsp;Ϊ������ҵ������<br>&nbsp;&nbsp;ѧ����ѵ��ϵ��<br>&nbsp;&nbsp;................<br><br>&nbsp;&nbsp;���������������<br>&nbsp;&nbsp;��������ռ䣡<br>&nbsp;&nbsp;................<br>&nbsp;&nbsp;�������Ĺ�˾������<br>&nbsp;&nbsp;ʱ����������ս����<br>&nbsp;&nbsp;ҵ���������룡<br>&nbsp;&nbsp;���ǵĲ�Ʒ�����<br>&nbsp;&nbsp;��ʱ����Ϊ���ṩ<br>&nbsp;&nbsp;��ǿ��Ľ������<br>&nbsp;&nbsp;&nbsp;&nbsp;<b>E-learning</b><br>&nbsp;&nbsp;................<br><br>&nbsp;&nbsp;         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--��������</font>'
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
                                  <td class=jnfont6 width=117> ÿ �� �� �� �� ��</td>
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
                                  <td>�γ�����: --Word����ͨ--</td>
</tr>
<tr>
                                  <td>�γ�����: ̷����</td>
</tr>
<tr>
                                  <td>
                                    <div align="right">�γ̽���: ������������ȫ<br>
                                      ����  Word  2002<br>
                                      ����ʹ�÷�����</div>
                                  </td>
</tr>
<tr>
<td>�Ƽ�ָ��:<font color="#CC6600">������</font></td>
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
src="�����͹���ˡ�����ҳ����.files/shim.gif" width=1></td>
<td bgcolor=#000000><img height=1
src="�����͹���ˡ�����ҳ����.files/shim.gif" width=1></td>
<td width=1 bgcolor=#000000 rowspan=3><img height=1
src="�����͹���ˡ�����ҳ����.files/shim.gif" width=1></td>
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
                                  <td class="jnfont6" width="54%">����ѡ�Ŀγ�</td>
                                  <td class="jnfont6" width="46%">��<a href="#">����</a>ѡ�¿�</td>
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
height=1 src="�����͹���ˡ�����ҳ����.files/shim.gif"
width=1></td>
<td>
<table cellspacing=0 cellpadding=0 width="100%"
border=0>
<tbody>
<tr>
<td bgcolor=#000000 height=1 rowspan=4><img
height=1 src="�����͹���ˡ�����ҳ����.files/shim.gif"
width=1></td>
<td bgcolor=#000000 colspan=2><img height=1
src="�����͹���ˡ�����ҳ����.files/shim.gif" width=1></td>
<td bgcolor=#000000 height=1 rowspan=2><img
height=1 src="�����͹���ˡ�����ҳ����.files/shim.gif"
width=1></td>
</tr>
<tr>
                                        <td bgcolor=#ffffff height=174>afasfasfasfdasfasfafasfasfasfasfasfasasfd 
                                        </td>
<td width=12 bgcolor=#ffffff height=174><img
height=1 src="�����͹���ˡ�����ҳ����.files/shim.gif"
width=1></td>
</tr>
<tr>
<td bgcolor=#ffffff height=12><img height=1
src="�����͹���ˡ�����ҳ����.files/shim.gif" width=1></td>
<td colspan=2 rowspan=2><img height=13
src="/elearning/images/htmlcn_01.GIF"
width=13></td>
</tr>
<tr>
<td bgcolor=#000000><img height=1
src="�����͹���ˡ�����ҳ����.files/shim.gif"
width=1></td>
</tr>
</tbody>
</table>
</td>
<td width=10 bgcolor="#F0F0F0"><img
height=1 src="�����͹���ˡ�����ҳ����.files/shim.gif"
width=1></td>
</tr>
</tbody>
</table>
</td>
</tr>
<tr bgcolor="#F0F0F0">
<td colspan=3
height=10><img height=1
src="�����͹���ˡ�����ҳ����.files/shim.gif"
width=1></td>
</tr>
</tbody>
</table>
</td>
</tr>
<tr>
<td width=1 bgcolor=#000000><img height=1
src="�����͹���ˡ�����ҳ����.files/shim.gif"
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
                                  <td class=jnfont6 width=120>�� �� �� �� �� վ</td>
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
<td width="92%">��ҹ�Ķ�����...���¼���</td>
</tr>
<tr>
<td width="8%"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%">�����վV2.0.....ȫ�¸İ�</td>
</tr>
<tr>
<td width="8%"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%">�س����԰...........����</td>
</tr>
<tr>
<td width="8%" height="18"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%" height="18">��Ӵ������С��...���¼���</td>
</tr>
<tr>
<td width="8%"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%">������...........���¼���</td>
</tr>
<tr>
<td width="8%"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%">�з������v4.0...ȫ�¸İ�</td>
</tr>
<tr>
<td width="8%"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%">���Ǹ�����ҳ.........����</td>
</tr>
<tr>
<td width="8%"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%">��¥����v3.0.....���¼���</td>
</tr>
<tr>
<td width="8%"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%">һ�ſռ�.........���¼���</td>
</tr>
<tr>
<td width="8%"><img height=8
src="/elearning/images/ra.gif" width=8></td>
<td width="92%">�������.........���¼���</td>
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
<td class=jnfont6 width=117> �� �� �� ��</td>
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
�������</td>
</tr>
<tr>
<td width="55%"><img src="/elearning/images/2001-2-39.gif" width="11" height="8">
��Ʒ���ͣ�ƽ�棩</td>
</tr>
<tr>
<td width="55%"><img src="/elearning/images/2001-2-39.gif" width="11" height="8">
��Ʒ���ͣ�������</td>
</tr>
<tr>
<td width="55%"><img src="/elearning/images/2001-2-39.gif" width="11" height="8">
����������� </td>
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
<td class=jnfont6 width=137> �� �� �� Ʒ �� ��</td>
<td class=jnfont6 width=209>
<div align="right">����¼��Ʒ<b><font color="#42566f">76</font></b>��
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
<td width="55%" height="27">����:��������</td>
</tr>
<tr>
<td width="55%" height="24">����:��ҹ~</td>
</tr>
<tr>
<td width="55%">���:������Ʒ</td>
</tr>
</table>
</td>
<td rowspan="4" width="49%">
<table width="100%" border="0" cellspacing="5" cellpadding="0">
<tr>
<td rowspan="3" width="45%"><img src="/elearning/images/0090m.jpg" width="100" height="98"></td>
<td width="55%" height="27">����:˲��</td>
</tr>
<tr>
<td width="55%" height="24">����:��è</td>
</tr>
<tr>
<td width="55%">���:ƽ����Ʒ</td>
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
            <div align="center"><b>http://www.huantian.com.cn ������������&trade;������չ���޹�˾<br>
              ������������������ô���807 �绰��010-82031610 ���棺010-82031613</b></div>
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
