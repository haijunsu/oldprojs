<%@page contentType="text/html;charset=GB2312"%>
<%@ page import="java.io.File.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.io.FileFilter"%>
<jsp:useBean id="beanTools" scope="session" class="com.htyz.beanElearnTools" />

<HTML>
<HEAD>
<TITLE>��Զ����E-learning</TITLE>
<script>
function winopen(myurl)
{
window.open(myurl,'searchwin','top=0,left=100,width=600,height=435,scrollbars=yes,resizable=yes,center:yes');
}
function wqp()
{
    window.open(top.location,'RUNMIT_LOG','resizable=yes,scrollbars=auto');
    window.focus();
    //document.closes.Click();
}
//if(window.name != "RUNMIT_LOG")	wqp();
</script>

<META http-equiv=Content-Type content="text/html; charset=gb2312">

<SCRIPT language=JavaScript>
function marquee1() {document.write("<marquee behavior=scroll direction=up width=230 height=120 scrollamount=1 scrolldelay=60 onmouseover='this.stop()' onmouseout='this.start()'>")}                                  
function marquee2() {document.write("</marquee>")}</SCRIPT>
</HEAD>
<BODY bgColor=#e5e5e5 topMargin=0><LINK href="images/style2.css" type=text/css rel=stylesheet>
<CENTER>
<TABLE id=AutoNumber11 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=762 border=0>
  <TBODY>
  <TR>
    <TD width="100%" bgcolor="#FFFFFF"><IMG src="logo1.gif"  border=0 width="70" height="55">
<!--           <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="158" height="50">
            <param name="movie" value="logo.swf">
            <param name="quality" value="high">
            <embed src="images/banner.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="468" height="60"></embed></object>
 -->			<IMG src="logo0.gif"  border=0 height="55" width="687"></TD>
  </TR>
  <TR>
    <TD width="100%">
      <TABLE id=AutoNumber12 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width="100%" border=1>
        <TBODY>
        <TR>
          <TD width="100%" bgColor=#007dc6>
            <TABLE id=AutoNumber13 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
              <TR>
                <TD width="100%">��</TD></TR>
              <TR>
                <TD width="100%">
                  <DIV align=right>
                  <TABLE id=AutoNumber14 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width="97%" border=1>
                    <TBODY>
                    <TR>
                      <TD width="100%" bgColor=#ffffff>
                        <TABLE id=AutoNumber15 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width="100%" border=0>
                          <TBODY>
                          <TR>
                            <TD width="98%" bgColor=#e5e5e5>
                              <TABLE id=AutoNumber6 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 width="100%" border=0>
                                <TBODY>
                                <TR>
                                <TD width="99%">
                                <P align=right>&nbsp;<SPAN lang=zh-cn><A href="<%=request.getContextPath()%>/servlet/system/userProfiles" target=_register>ע��</A> 
                                                      | <A href="<%=request.getContextPath()%>/login.jsp">�û���½</A> 
                                                      | �ҵ��ղ� 
                                                      | �������� 
                                                      | �û��б� 
                                                      | ����Ϣ 
                                                      | <A style="COLOR: #000000" href="javascript:winopen('<%=request.getContextPath()%>/search.jsp');">���� </A>
                                                      | �˳� 
                                                    </SPAN></P></TD>
                                <TD width="1%">��</TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
                        <TABLE id=AutoNumber16 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width="100%" border=0>
                          <TBODY>
                          <TR>
                            <TD width="100%" colSpan=3><IMG height=2 src="images/bl.gif" width=32 border=0></TD></TR>
                          <TR>
                            <TD vAlign=top width="78%">
                              <TABLE id=AutoNumber18 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width="100%" border=0>
                                              <TBODY>
                                                <TR> 
                                                  <TD vAlign=top width="3%" rowSpan=7>��</TD>
                                                  <TD vAlign=top width="54%"> 
                                                    <TABLE width="93%" border=0 align=center 
                                cellPadding=4 cellSpacing=0 id=fancyborder>
                                                      <TBODY>
                                                        <TR> 
                                                          <TD width="100%"> <P align=left><A href="<%=request.getContextPath()%>/index.jsp"><IMG height=54 src="images/reg.jpg" width=296 border=0></A><BR>
                                                              <marquee behavior=scroll direction=up width=290 height=61 scrollamount=1 scrolldelay=200 onmouseover='this.stop()' onmouseout='this.start()'>
                                                              <jsp:include page="/note.html"/>

                                                              </marquee></A></FONT>
                                                            </P></TD>
                                                        </TR>
                                                      </TBODY>
                                                    </TABLE></TD>
                                                  <TD width="3%">��</TD>
                                                  <TD vAlign=top width="40%"> 
                                                    <TABLE id=fancyborder cellSpacing=0 
                                cellPadding=4 align=center border=0>
                                                      <TBODY>
                                                        <TR> 
                                                          <form method="POST" action="<%=request.getContextPath()%>/servlet/beanUserLogin">
                                                            <TD width="100%"><IMG height=59  src="images/100402am_email.gif"  width=247 border=0><BR> 
                                                              <SPAN lang=zh-cn>�û���:</SPAN><SPAN lang=zh-cn> 
                                                              <INPUT  size=10 name=userid #000000? solid 1 border-bottom:>
                                                              </SPAN><SPAN lang=en-us> 
                                                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class=img  type=image  src="enter.gif" align=middle  border=0 name=I1>
                                                              </span> <SPAN lang=zh-cn><br>
                                                             &nbsp; ����:&nbsp; 
                                                              <INPUT   type=password size=10 name=pass #000000?  solid 1 border-bottom:>
                                                              <input    type=checkbox checked value=ON name=pass>
                                                              �����½״̬</SPAN></TD>
                                                          </FORM>
                                                        </TR>
                                                      </TBODY>
                                                    </TABLE></TD>
                                                </TR>

                                                <TR> 
                                                  <TD vAlign=top colSpan=3><IMG 
                                height=11 src="images/dp.gif" width=105 
                                border=0><FONT 
                                color=#c0c0c0>����������������������������������������������������������������������������������</FONT></TD>
                                                </TR>
                                                <TR> 
                                                  <TD vAlign=top colSpan=3> <TABLE id=AutoNumber20 
                                style="BORDER-COLLAPSE: collapse" 
                                borderColor=#111111 cellSpacing=0 cellPadding=0 
                                width="102%" border=0>
                                                      <TBODY>
                                                        <TR> 
                                                          <TD vAlign=top width="15%" rowSpan=7> 
                                                            <TABLE id=fancyborder cellSpacing=0 
                                cellPadding=4 align=center border=0>
                                                              <TBODY>
                                                                <TR> 
                                                                  <TD width="100%"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9010&name=��˾�ƶ�" target="_blank"><img src="images/opensource.jpg" width="85" height="110" border=0 style="filter:ALPHA(opacity=30)" onmouseover="javascript:this.style.filter='ALPHA(opacity=100)';" onmouseout="javascript:this.style.filter='ALPHA(opacity=30)';"></a></TD>
                                                                </TR>
                                                              </TBODY>
                                                            </TABLE></TD>
                                                          <TD width="1%" rowSpan=7>��</TD>
                                                          <TD align=middle width="18%" 
                                bgColor=#666666><B><FONT color=#ffffff><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9010&name=��˾�ƶ�" target="_blank"><FONT 
                                color=#ffffff>��˾�ƶ�</FONT></A></FONT></B></TD>
                                                          <TD width="3%" rowSpan=7>��</TD>
                                                          <TD vAlign=top width="15%" rowSpan=7> 
                                                            <TABLE id=fancyborder cellSpacing=0 
                                cellPadding=4 align=center border=0>
                                                              <TBODY>
                                                                <TR> 
                                                                  <TD width="100%"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9020&name=��Ա����ѵ" target="_blank"><IMG height=110 
                                src="images/100302_patent.jpg" width=85 
                                border=0 style="filter:ALPHA(opacity=30)" onmouseover="javascript:this.style.filter='ALPHA(opacity=100)';" onmouseout="javascript:this.style.filter='ALPHA(opacity=30)';"></a></TD>
                                                                </TR>
                                                              </TBODY>
                                                            </TABLE></TD>
                                                          <TD width="1%" rowSpan=7>��</TD>
                                                          <TD vAlign=top width="18%" bgColor=#666666> 
                                                            <P align=center><B><FONT color=#ffffff><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9020&name=��Ա����ѵ" target="_blank"><FONT 
                                color=#ffffff>��Ա����ѵ</FONT></A></FONT></B></P></TD>
                                                          <TD vAlign=top width="2%" rowSpan=7>&nbsp;</TD>
                                                          <TD vAlign=top width="45%" rowSpan=7><A 
                                href="<%=request.getContextPath()%>/display.php?forumid=23"><IMG 
                                src="images/adv2.jpg" 
                                border=0></A></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9010&codevalue=0005&name=��˾�ƶ�->��˾�³�" target="_blank">��˾�³�</A></div></TD>
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9020&codevalue=0005&name=��Ա����ѵ->��˾����" target="_blank">��˾����</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9010&codevalue=0045&name=��˾�ƶ�->����������" target="_blank">����������</A></div></TD>
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9020&codevalue=0010&name=��Ա����ѵ->��˾��ӪĿ��" target="_blank">��˾��ӪĿ��</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9010&codevalue=0010&name=��˾�ƶ�->���½�����" target="_blank">���½�����</A></div></TD>
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9020&codevalue=0015&name=��Ա����ѵ->��˾��չ" target="_blank">��˾��չ</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9010&codevalue=0015&name=��˾�ƶ�->���������" target="_blank">���������</A></div></TD>
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9020&codevalue=0020&name=��Ա����ѵ->����Ա����Ҫ��" target="_blank">����Ա����Ҫ��</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9010&codevalue=0025&name=��˾�ƶ�->��ͬ��������" target="_blank">��ͬ��������</A></div></TD>
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                               href="<%=request.getContextPath()%>/class/show.jsp?codeid=9020&codevalue=0025&name=��Ա����ѵ->�ճ��칫����" target="_blank">�ճ��칫����</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD width="18%" height="49" vAlign=top  bgColor=#f7f7f7> 
                                                            <div align="left"><A  href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9010&name=��˾�ƶ�" target="_blank">&gt;&gt;</SPAN>������ѵ����</A></div></TD>
                                                          <TD vAlign=top align=middle width="18%"   bgColor=#f7f7f7> 
                                                            <div align="left"><A  href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9020&name=��Ա����ѵ" target="_blank"><SPAN lang=en-us>&gt;&gt;</SPAN> 
                                                              ������ѵ����</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD width="100%" colspan="9"></TD>
                                                        </TR>
                                                    </TABLE></TD>
                                                </TR>
                                                <TR> 
                                                  <TD vAlign=top colSpan=3> <TABLE id=AutoNumber22 
                                style="BORDER-COLLAPSE: collapse" 
                                borderColor=#111111 cellSpacing=0 cellPadding=0 
                                width="100%" border=0>
                                                      <TBODY>
                                                        <TR> 
                                                          <TD width="17%" rowSpan=7> 
                                                            <TABLE id=fancyborder cellSpacing=0 
                                cellPadding=4 align=center border=0>
                                                              <TBODY>
                                                                <TR> 
                                                                  <TD width="100%"><A 
                               href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9030&name=ҵ��֪ʶ��ѵ" target="_blank"><IMG height=110 
                                src="images/webferret_promo.jpg" 
                                width=85 
border=0 style="filter:ALPHA(opacity=30)" onmouseover="javascript:this.style.filter='ALPHA(opacity=100)';" onmouseout="javascript:this.style.filter='ALPHA(opacity=30)';"></a></TD>
                                                                </TR>
                                                              </TBODY>
                                                            </TABLE></TD>
                                                          <TD vAlign=top width="1%" rowSpan=7>��</TD>
                                                          <TD vAlign=top width="17%" bgColor=#666666> 
                                                            <P align=center><B><FONT color=#ffffff><A 
                               href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9030&name=ҵ��֪ʶ��ѵ" target="_blank"><FONT 
                                color=#ffffff>ҵ��֪ʶ��ѵ</FONT></A></FONT></B></P></TD>
                                                          <TD vAlign=top width="3%" rowSpan=7>��</TD>
                                                          <TD vAlign=top width="17%" rowSpan=7> 
                                                            <TABLE id=fancyborder cellSpacing=0 
                                cellPadding=4 align=center border=0>
                                                              <TBODY>
                                                                <TR> 
                                                                  <TD width="100%"><A 
                               href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9040&name=רҵ������ѵ" target="_blank"><IMG height=110 
                                src="images/100202wirless.jpg" width=85 
                                border=0 style="filter:ALPHA(opacity=30)" onmouseover="javascript:this.style.filter='ALPHA(opacity=100)';" onmouseout="javascript:this.style.filter='ALPHA(opacity=30)';"></a></TD>
                                                                </TR>
                                                              </TBODY>
                                                            </TABLE></TD>
                                                          <TD vAlign=top width="1%" rowSpan=7>��</TD>
                                                          <TD vAlign=top width="17%" bgColor=#666666> 
                                                            <P align=center><FONT color=#ffffff><B><A 
                               href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9040&name=רҵ������ѵ" target="_blank"><FONT 
                                color=#ffffff>רҵ������ѵ</FONT></A></B></FONT></P></TD>
                                                          <TD vAlign=top width="2%" rowSpan=7>��</TD>
                                                          <TD vAlign=top width="25%" rowSpan=7><A 
                                href="<%=request.getContextPath()%>/display.php?forumid=42"><IMG 
                                src="images/adv1.jpg" 
                                border=0></A></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9030&codevalue=0005&name=ҵ��֪ʶ��ѵ->���ڵ��ݼ�����" target="_blank">���ڵ��ݼ�����</A></div></TD>
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                               href="<%=request.getContextPath()%>/class/show.jsp?codeid=9040&codevalue=0030&name=רҵ������ѵ->���ۼ���" target="_blank">���ۼ���</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9030&codevalue=0010&name=ҵ��֪ʶ��ѵ->���ۺ�ͬ������" target="_blank">���ۺ�ͬ������</A></div></TD>
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9040&codevalue=0010&name=רҵ������ѵ->��ͨ����" target="_blank">��ͨ����</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9030&codevalue=0015&name=ҵ��֪ʶ��ѵ->ҵ�����涨" target="_blank">ҵ�����涨</A></div></TD>
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9040&codevalue=0015&name=רҵ������ѵ-≯�м���" target="_blank"≯�м���</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9030&codevalue=0020&name=ҵ��֪ʶ��ѵ->�ؿ��Ʊ" target="_blank">�ؿ��Ʊ</A></div></TD>
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9040&codevalue=0020&name=רҵ������ѵ->������˰��" target="_blank">������˰��</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9030&codevalue=0025&name=ҵ��֪ʶ��ѵ->���㼰���󱨱�" target="_blank">���㼰���󱨱�</A></div></TD>
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9040&codevalue=0025&name=רҵ������ѵ->����ó��" target="_blank">����ó��</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9030&name=ҵ��֪ʶ��ѵ" target="_blank"><SPAN 
                                lang=en-us>&gt;&gt;</SPAN> ������ѵ����</A></div></TD>
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9040&name=רҵ������ѵ" target="_blank"><SPAN 
                                lang=en-us>&gt;&gt;</SPAN> ������ѵ����</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD colspan="9">&nbsp;</TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD colspan="9"><TABLE id=AutoNumber23 
                                style="BORDER-COLLAPSE: collapse" 
                                borderColor=#111111 cellSpacing=0 cellPadding=0 
                                width="100%" border=0>
                                                              <TBODY>
                                                                <TR> 
                                                                  <TD width="99%"> 
                                                                    <TABLE id=AutoNumber24 
                                style="BORDER-COLLAPSE: collapse" 
                                borderColor=#111111 cellSpacing=0 cellPadding=0 
                                width="100%" border=0>
                                                                      <TBODY>
                                                                        <TR> 
                                                                          <TD width="17%" rowSpan=7> 
                                                                            <TABLE id=fancyborder cellSpacing=0 
                                cellPadding=4 align=center border=0>
                                                                              <TBODY>
                                                                                <TR> 
                                                                                  <TD width="100%"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9050&name=ְ�ƿ�����ѵ" target="_blank"><IMG height=110 
                                src="images/100102_mousegesture.jpg" 
                                width=85 
border=0 style="filter:ALPHA(opacity=30)" onmouseover="javascript:this.style.filter='ALPHA(opacity=100)';" onmouseout="javascript:this.style.filter='ALPHA(opacity=30)';"></a></TD>
                                                                                </TR>
                                                                              </TBODY>
                                                                            </TABLE></TD>
                                                                          <TD vAlign=top width="1%" rowSpan=7>��</TD>
                                                                          <TD vAlign=top width="17%" bgColor=#666666> 
                                                                            <P align=center><FONT color=#ffffff><B><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9050&name=ְ�ƿ�����ѵ" target="_blank"><FONT 
                                color=#ffffff>ְ�ƿ�����ѵ</FONT></A></B></FONT></P></TD>
                                                                          <TD vAlign=top width="3%" rowSpan=7>��</TD>
                                                                          <TD vAlign=top width="17%" rowSpan=7> 
                                                                            <TABLE id=fancyborder cellSpacing=0 
                                cellPadding=4 align=center border=0>
                                                                              <TBODY>
                                                                                <TR> 
                                                                                  <TD width="100%"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9060&name=Ա��������ѵ" target="_blank"><IMG height=110 
                                src="images/fds_0622hackers.jpg" 
                                width=85 
border=0 style="filter:ALPHA(opacity=30)" onmouseover="javascript:this.style.filter='ALPHA(opacity=100)';" onmouseout="javascript:this.style.filter='ALPHA(opacity=30)';"></a></TD>
                                                                                </TR>
                                                                              </TBODY>
                                                                            </TABLE></TD>
                                                                          <TD vAlign=top width="1%" rowSpan=7>��</TD>
                                                                          <TD vAlign=top width="17%" bgColor=#666666> 
                                                                            <P align=center><FONT color=#ffffff><B><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9060&name=Ա��������ѵ" target="_blank"><FONT 
                                color=#ffffff>Ա��������ѵ</FONT></A></B></FONT></P></TD>
                                                                          <TD vAlign=top width="2%" rowSpan=7>��</TD>
                                                                          <TD vAlign=top width="25%" rowSpan=7><A 
                                href="<%=request.getContextPath()%>/display.php?forumid=27"><IMG 
                                src="images/adv3.jpg" 
                                border=0></A></TD>
                                                                        </TR>
                                                                        <TR> 
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9050&codevalue=0005&name=ְ�ƿ�����ѵ->����Ա����" target="_blank">����Ա����</A></div></TD>
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9060&codevalue=0005&name=Ա��������ѵ->���ڲ���֪ʶ" target="_blank">���ڲ���֪ʶ</A></div></TD>
                                                                        </TR>
                                                                        <TR> 
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9050&codevalue=0010&name=ְ�ƿ�����ѵ->����Ա����" target="_blank">����Ա����</A></div></TD>
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9060&codevalue=0010&name=Ա��������ѵ->WTOר��" target="_blank">WTOר��</A></div></TD>
                                                                        </TR>
                                                                        <TR> 
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9050&codevalue=0015&name=ְ�ƿ�����ѵ->����֪ʶ��ѵ" target="_blank">����֪ʶ��ѵ</A></div></TD>
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9060&codevalue=0015&name=Ա��������ѵ->����֪ʶ" target="_blank">����֪ʶ</A></div></TD>
                                                                        </TR>
                                                                        <TR> 
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9050&codevalue=0020&name=ְ�ƿ�����ѵ->MBAר��" target="_blank">MBAר��</A></div></TD>
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9060&codevalue=0020&name=Ա��������ѵ->Ӣ����" target="_blank">Ӣ����</A></div></TD>
                                                                        </TR>
                                                                        <TR> 
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9050&codevalue=0025&name=ְ�ƿ�����ѵ->MPAר��" target="_blank">MPAר��</A></div></TD>
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9060&codevalue=0025&name=Ա��������ѵ->������ѵ" target="_blank">������ѵ</A></div></TD>
                                                                        </TR>
                                                                        <TR> 
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9050&name=ְ�ƿ�����ѵ" target="_blank"><SPAN 
                                lang=en-us>&gt;&gt;</SPAN> ������ѵ����</A></div></TD>
                                                                          <TD vAlign=top width="17%" 
                                bgColor=#f7f7f7><div align="left"><a 
                               href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9060&name=Ա��������ѵ" target="_blank"><span 
                                lang=en-us>&gt;&gt;</span> ������ѵ����</a></div></TD>
                                                                        </TR>
                                                                      </TBODY>
                                                                    </TABLE></TD>
                                                                </TR>
                                                              </TBODY>
                                                            </TABLE></TD>
                                                        </TR>
                                                      </TBODY>
                                                    </TABLE></TD>
                                                </TR>
                                                <TR> 
                                                  <TD colSpan=4>&nbsp; </TD>
                                                </TR>
                                              </TBODY>
                                            </TABLE></TD>
                            <TD vAlign=top width="1%">��</TD>
                            <TD vAlign=top width="21%">
                              <TABLE id=AutoNumber17 
                              style="BORDER-COLLAPSE: collapse" 
                              borderColor=#111111 height=54 cellSpacing=0 
                              cellPadding=0 width="100%" border=0>
                                <TBODY>
                                <TR>
                                <TD width="100%" height=14><IMG height=541 
                                src="images/banner.gif" width=148 
                                border=0></TD></TR>
                                <TR>
                                <TD width="100%" height=4></TD></TR>
                                <TR>
                                <TD width="100%" height=4>
                                <P></P>
                                <P></P></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
                        <TABLE id=AutoNumber28 style="BORDER-COLLAPSE: collapse" 
                        borderColor=#111111 cellSpacing=0 cellPadding=0 
                        width="100%" border=0>
                          <TBODY>
                          <TR>
                            <TD width="1%" rowSpan=2>��</TD>
                            <TD width="99%">
                              <TABLE cellSpacing=0 cellPadding=0 width="98%" 
                              align=center border=0>
                                            </TABLE></TD></TR>
                          <TR>
                            <TD width="99%">
                              <TABLE id=AutoNumber29 
                              style="BORDER-COLLAPSE: collapse" 
                              borderColor=#111111 cellSpacing=0 cellPadding=0 
                              width="100%" border=0>
                                <TBODY>
                                <TR>
                                <TD width="100%">
                                <TABLE cellSpacing=0 cellPadding=0 width="98%" 
                                align=center border=0>
                                <TBODY>
                                <TR>
                                <TD bgColor=#edeced>
                                <TABLE cellSpacing=1 cellPadding=4 width="100%" 
                                border=0>
                                <TBODY>
                                <TR bgColor=#007dc6>
                                                                  <TD colSpan=2><IMG 
                                src="images/absolute.gif" 
                                align=absMiddle>&nbsp;&nbsp;<FONT 
                                color=#ffffff><B>��˾�������</B></FONT></TD>
                                                                </TR>
                                <TR bgColor=#f7f7f7>
                                <TD noWrap align=middle width="4%" 
                                bgColor=#ffffff>��</TD>
                                                                  <TD bgColor=#ffffff><A 
                                href="http://www.huantian.com.cn/" 
                                target=_blank>��˾��<SPAN lang=zh-cn>վ</SPAN></A> | <A 
                                href="http://www.huantian.com.cn" 
                                target=_blank><SPAN lang=zh-cn>��˾OAƽ̨</SPAN></A> 
                                                                    | <SPAN lang=zh-cn><A 
                                href="http://www.huantian.com.cn" 
                                target=_blank>��˾��Ϣ��</A></SPAN></TD>
                                                                </TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR>
                                <TR>
                                <TD 
                          width="100%">��</TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></DIV></TD></TR>
              <TR>
                <TD 
  width="100%">��</TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
  <P align=center><BR>
    &copy; 2001-2002 Huantian Co.Ltd. All Rights Reserved. Powerd by Huantian&#8482;. </CENTER>
</BODY></HTML>
