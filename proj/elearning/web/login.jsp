<%@page contentType="text/html;charset=GB2312"%>
<%@ page import="java.io.File.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.io.FileFilter"%>
<jsp:useBean id="beanTools" scope="session" class="com.htyz.beanElearnTools" />

<HTML>
<HEAD>
<TITLE>中远物流E-learning</TITLE>
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
                <TD width="100%">　</TD></TR>
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
                                <P align=right>&nbsp;<SPAN lang=zh-cn><A href="<%=request.getContextPath()%>/servlet/system/userProfiles" target=_register>注册</A> 
                                                      | <A href="<%=request.getContextPath()%>/login.jsp">用户登陆</A> 
                                                      | 我的收藏 
                                                      | 个人设置 
                                                      | 用户列表 
                                                      | 短消息 
                                                      | <A style="COLOR: #000000" href="javascript:winopen('<%=request.getContextPath()%>/search.jsp');">搜索 </A>
                                                      | 退出 
                                                    </SPAN></P></TD>
                                <TD width="1%">　</TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
                        <TABLE id=AutoNumber16 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width="100%" border=0>
                          <TBODY>
                          <TR>
                            <TD width="100%" colSpan=3><IMG height=2 src="images/bl.gif" width=32 border=0></TD></TR>
                          <TR>
                            <TD vAlign=top width="78%">
                              <TABLE id=AutoNumber18 style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width="100%" border=0>
                                              <TBODY>
                                                <TR> 
                                                  <TD vAlign=top width="3%" rowSpan=7>　</TD>
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
                                                  <TD width="3%">　</TD>
                                                  <TD vAlign=top width="40%"> 
                                                    <TABLE id=fancyborder cellSpacing=0 
                                cellPadding=4 align=center border=0>
                                                      <TBODY>
                                                        <TR> 
                                                          <form method="POST" action="<%=request.getContextPath()%>/servlet/beanUserLogin">
                                                            <TD width="100%"><IMG height=59  src="images/100402am_email.gif"  width=247 border=0><BR> 
                                                              <SPAN lang=zh-cn>用户名:</SPAN><SPAN lang=zh-cn> 
                                                              <INPUT  size=10 name=userid #000000? solid 1 border-bottom:>
                                                              </SPAN><SPAN lang=en-us> 
                                                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class=img  type=image  src="enter.gif" align=middle  border=0 name=I1>
                                                              </span> <SPAN lang=zh-cn><br>
                                                             &nbsp; 密码:&nbsp; 
                                                              <INPUT   type=password size=10 name=pass #000000?  solid 1 border-bottom:>
                                                              <input    type=checkbox checked value=ON name=pass>
                                                              保存登陆状态</SPAN></TD>
                                                          </FORM>
                                                        </TR>
                                                      </TBODY>
                                                    </TABLE></TD>
                                                </TR>

                                                <TR> 
                                                  <TD vAlign=top colSpan=3><IMG 
                                height=11 src="images/dp.gif" width=105 
                                border=0><FONT 
                                color=#c0c0c0>—————————————————————————————————————————</FONT></TD>
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
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9010&name=公司制度" target="_blank"><img src="images/opensource.jpg" width="85" height="110" border=0 style="filter:ALPHA(opacity=30)" onmouseover="javascript:this.style.filter='ALPHA(opacity=100)';" onmouseout="javascript:this.style.filter='ALPHA(opacity=30)';"></a></TD>
                                                                </TR>
                                                              </TBODY>
                                                            </TABLE></TD>
                                                          <TD width="1%" rowSpan=7>　</TD>
                                                          <TD align=middle width="18%" 
                                bgColor=#666666><B><FONT color=#ffffff><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9010&name=公司制度" target="_blank"><FONT 
                                color=#ffffff>公司制度</FONT></A></FONT></B></TD>
                                                          <TD width="3%" rowSpan=7>　</TD>
                                                          <TD vAlign=top width="15%" rowSpan=7> 
                                                            <TABLE id=fancyborder cellSpacing=0 
                                cellPadding=4 align=center border=0>
                                                              <TBODY>
                                                                <TR> 
                                                                  <TD width="100%"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9020&name=新员工培训" target="_blank"><IMG height=110 
                                src="images/100302_patent.jpg" width=85 
                                border=0 style="filter:ALPHA(opacity=30)" onmouseover="javascript:this.style.filter='ALPHA(opacity=100)';" onmouseout="javascript:this.style.filter='ALPHA(opacity=30)';"></a></TD>
                                                                </TR>
                                                              </TBODY>
                                                            </TABLE></TD>
                                                          <TD width="1%" rowSpan=7>　</TD>
                                                          <TD vAlign=top width="18%" bgColor=#666666> 
                                                            <P align=center><B><FONT color=#ffffff><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9020&name=新员工培训" target="_blank"><FONT 
                                color=#ffffff>新员工培训</FONT></A></FONT></B></P></TD>
                                                          <TD vAlign=top width="2%" rowSpan=7>&nbsp;</TD>
                                                          <TD vAlign=top width="45%" rowSpan=7><A 
                                href="<%=request.getContextPath()%>/display.php?forumid=23"><IMG 
                                src="images/adv2.jpg" 
                                border=0></A></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9010&codevalue=0005&name=公司制度->公司章程" target="_blank">公司章程</A></div></TD>
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9020&codevalue=0005&name=新员工培训->公司背景" target="_blank">公司背景</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9010&codevalue=0045&name=公司制度->行政总务类" target="_blank">行政总务类</A></div></TD>
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9020&codevalue=0010&name=新员工培训->公司经营目标" target="_blank">公司经营目标</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9010&codevalue=0010&name=公司制度->人事教育类" target="_blank">人事教育类</A></div></TD>
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9020&codevalue=0015&name=新员工培训->公司发展" target="_blank">公司发展</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9010&codevalue=0015&name=公司制度->财务审计类" target="_blank">财务审计类</A></div></TD>
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9020&codevalue=0020&name=新员工培训->对新员工的要求" target="_blank">对新员工的要求</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9010&codevalue=0025&name=公司制度->合同、储运类" target="_blank">合同、储运类</A></div></TD>
                                                          <TD vAlign=top align=middle width="18%" 
                                bgColor=#f7f7f7><div align="left"><A 
                               href="<%=request.getContextPath()%>/class/show.jsp?codeid=9020&codevalue=0025&name=新员工培训->日常办公流程" target="_blank">日常办公流程</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD width="18%" height="49" vAlign=top  bgColor=#f7f7f7> 
                                                            <div align="left"><A  href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9010&name=公司制度" target="_blank">&gt;&gt;</SPAN>更多培训内容</A></div></TD>
                                                          <TD vAlign=top align=middle width="18%"   bgColor=#f7f7f7> 
                                                            <div align="left"><A  href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9020&name=新员工培训" target="_blank"><SPAN lang=en-us>&gt;&gt;</SPAN> 
                                                              更多培训内容</A></div></TD>
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
                               href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9030&name=业务知识培训" target="_blank"><IMG height=110 
                                src="images/webferret_promo.jpg" 
                                width=85 
border=0 style="filter:ALPHA(opacity=30)" onmouseover="javascript:this.style.filter='ALPHA(opacity=100)';" onmouseout="javascript:this.style.filter='ALPHA(opacity=30)';"></a></TD>
                                                                </TR>
                                                              </TBODY>
                                                            </TABLE></TD>
                                                          <TD vAlign=top width="1%" rowSpan=7>　</TD>
                                                          <TD vAlign=top width="17%" bgColor=#666666> 
                                                            <P align=center><B><FONT color=#ffffff><A 
                               href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9030&name=业务知识培训" target="_blank"><FONT 
                                color=#ffffff>业务知识培训</FONT></A></FONT></B></P></TD>
                                                          <TD vAlign=top width="3%" rowSpan=7>　</TD>
                                                          <TD vAlign=top width="17%" rowSpan=7> 
                                                            <TABLE id=fancyborder cellSpacing=0 
                                cellPadding=4 align=center border=0>
                                                              <TBODY>
                                                                <TR> 
                                                                  <TD width="100%"><A 
                               href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9040&name=专业技能培训" target="_blank"><IMG height=110 
                                src="images/100202wirless.jpg" width=85 
                                border=0 style="filter:ALPHA(opacity=30)" onmouseover="javascript:this.style.filter='ALPHA(opacity=100)';" onmouseout="javascript:this.style.filter='ALPHA(opacity=30)';"></a></TD>
                                                                </TR>
                                                              </TBODY>
                                                            </TABLE></TD>
                                                          <TD vAlign=top width="1%" rowSpan=7>　</TD>
                                                          <TD vAlign=top width="17%" bgColor=#666666> 
                                                            <P align=center><FONT color=#ffffff><B><A 
                               href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9040&name=专业技能培训" target="_blank"><FONT 
                                color=#ffffff>专业技能培训</FONT></A></B></FONT></P></TD>
                                                          <TD vAlign=top width="2%" rowSpan=7>　</TD>
                                                          <TD vAlign=top width="25%" rowSpan=7><A 
                                href="<%=request.getContextPath()%>/display.php?forumid=42"><IMG 
                                src="images/adv1.jpg" 
                                border=0></A></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9030&codevalue=0005&name=业务知识培训->进口单据及报关" target="_blank">进口单据及报关</A></div></TD>
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                               href="<%=request.getContextPath()%>/class/show.jsp?codeid=9040&codevalue=0030&name=专业技能培训->销售技巧" target="_blank">销售技巧</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9030&codevalue=0010&name=业务知识培训->销售合同及流程" target="_blank">销售合同及流程</A></div></TD>
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9040&codevalue=0010&name=专业技能培训->沟通技巧" target="_blank">沟通技巧</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9030&codevalue=0015&name=业务知识培训->业务请款规定" target="_blank">业务请款规定</A></div></TD>
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9040&codevalue=0015&name=专业技能培训->谈判技巧" target="_blank">谈判技巧</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9030&codevalue=0020&name=业务知识培训->回款及发票" target="_blank">回款及发票</A></div></TD>
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9040&codevalue=0020&name=专业技能培训->财务与税收" target="_blank">财务与税收</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9030&codevalue=0025&name=业务知识培训->结算及利润报表" target="_blank">结算及利润报表</A></div></TD>
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9040&codevalue=0025&name=专业技能培训->国际贸易" target="_blank">国际贸易</A></div></TD>
                                                        </TR>
                                                        <TR> 
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9030&name=业务知识培训" target="_blank"><SPAN 
                                lang=en-us>&gt;&gt;</SPAN> 更多培训内容</A></div></TD>
                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9040&name=专业技能培训" target="_blank"><SPAN 
                                lang=en-us>&gt;&gt;</SPAN> 更多培训内容</A></div></TD>
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
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9050&name=职称考试培训" target="_blank"><IMG height=110 
                                src="images/100102_mousegesture.jpg" 
                                width=85 
border=0 style="filter:ALPHA(opacity=30)" onmouseover="javascript:this.style.filter='ALPHA(opacity=100)';" onmouseout="javascript:this.style.filter='ALPHA(opacity=30)';"></a></TD>
                                                                                </TR>
                                                                              </TBODY>
                                                                            </TABLE></TD>
                                                                          <TD vAlign=top width="1%" rowSpan=7>　</TD>
                                                                          <TD vAlign=top width="17%" bgColor=#666666> 
                                                                            <P align=center><FONT color=#ffffff><B><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9050&name=职称考试培训" target="_blank"><FONT 
                                color=#ffffff>职称考试培训</FONT></A></B></FONT></P></TD>
                                                                          <TD vAlign=top width="3%" rowSpan=7>　</TD>
                                                                          <TD vAlign=top width="17%" rowSpan=7> 
                                                                            <TABLE id=fancyborder cellSpacing=0 
                                cellPadding=4 align=center border=0>
                                                                              <TBODY>
                                                                                <TR> 
                                                                                  <TD width="100%"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9060&name=员工素质培训" target="_blank"><IMG height=110 
                                src="images/fds_0622hackers.jpg" 
                                width=85 
border=0 style="filter:ALPHA(opacity=30)" onmouseover="javascript:this.style.filter='ALPHA(opacity=100)';" onmouseout="javascript:this.style.filter='ALPHA(opacity=30)';"></a></TD>
                                                                                </TR>
                                                                              </TBODY>
                                                                            </TABLE></TD>
                                                                          <TD vAlign=top width="1%" rowSpan=7>　</TD>
                                                                          <TD vAlign=top width="17%" bgColor=#666666> 
                                                                            <P align=center><FONT color=#ffffff><B><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9060&name=员工素质培训" target="_blank"><FONT 
                                color=#ffffff>员工素质培训</FONT></A></B></FONT></P></TD>
                                                                          <TD vAlign=top width="2%" rowSpan=7>　</TD>
                                                                          <TD vAlign=top width="25%" rowSpan=7><A 
                                href="<%=request.getContextPath()%>/display.php?forumid=27"><IMG 
                                src="images/adv3.jpg" 
                                border=0></A></TD>
                                                                        </TR>
                                                                        <TR> 
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9050&codevalue=0005&name=职称考试培训->报关员考试" target="_blank">报关员考试</A></div></TD>
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9060&codevalue=0005&name=员工素质培训->金融财政知识" target="_blank">金融财政知识</A></div></TD>
                                                                        </TR>
                                                                        <TR> 
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9050&codevalue=0010&name=职称考试培训->外销员考试" target="_blank">外销员考试</A></div></TD>
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9060&codevalue=0010&name=员工素质培训->WTO专区" target="_blank">WTO专区</A></div></TD>
                                                                        </TR>
                                                                        <TR> 
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9050&codevalue=0015&name=职称考试培训->金融知识培训" target="_blank">金融知识培训</A></div></TD>
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9060&codevalue=0015&name=员工素质培训->法律知识" target="_blank">法律知识</A></div></TD>
                                                                        </TR>
                                                                        <TR> 
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9050&codevalue=0020&name=职称考试培训->MBA专区" target="_blank">MBA专区</A></div></TD>
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9060&codevalue=0020&name=员工素质培训->英语类" target="_blank">英语类</A></div></TD>
                                                                        </TR>
                                                                        <TR> 
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9050&codevalue=0025&name=职称考试培训->MPA专区" target="_blank">MPA专区</A></div></TD>
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=9060&codevalue=0025&name=员工素质培训->销售培训" target="_blank">销售培训</A></div></TD>
                                                                        </TR>
                                                                        <TR> 
                                                                          <TD vAlign=top align=middle width="17%" 
                                bgColor=#f7f7f7><div align="left"><A 
                                href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9050&name=职称考试培训" target="_blank"><SPAN 
                                lang=en-us>&gt;&gt;</SPAN> 更多培训内容</A></div></TD>
                                                                          <TD vAlign=top width="17%" 
                                bgColor=#f7f7f7><div align="left"><a 
                               href="<%=request.getContextPath()%>/class/show.jsp?codeid=0013&codevalue=9060&name=员工素质培训" target="_blank"><span 
                                lang=en-us>&gt;&gt;</span> 更多培训内容</a></div></TD>
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
                            <TD vAlign=top width="1%">　</TD>
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
                            <TD width="1%" rowSpan=2>　</TD>
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
                                color=#ffffff><B>公司相关链接</B></FONT></TD>
                                                                </TR>
                                <TR bgColor=#f7f7f7>
                                <TD noWrap align=middle width="4%" 
                                bgColor=#ffffff>　</TD>
                                                                  <TD bgColor=#ffffff><A 
                                href="http://www.huantian.com.cn/" 
                                target=_blank>公司网<SPAN lang=zh-cn>站</SPAN></A> | <A 
                                href="http://www.huantian.com.cn" 
                                target=_blank><SPAN lang=zh-cn>公司OA平台</SPAN></A> 
                                                                    | <SPAN lang=zh-cn><A 
                                href="http://www.huantian.com.cn" 
                                target=_blank>公司信息网</A></SPAN></TD>
                                                                </TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR>
                                <TR>
                                <TD 
                          width="100%">　</TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></DIV></TD></TR>
              <TR>
                <TD 
  width="100%">　</TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
  <P align=center><BR>
    &copy; 2001-2002 Huantian Co.Ltd. All Rights Reserved. Powerd by Huantian&#8482;. </CENTER>
</BODY></HTML>
