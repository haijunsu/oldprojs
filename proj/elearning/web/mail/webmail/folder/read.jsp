<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="beanGetdata" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="eds" scope="request" class="com.htyz.util.BeanDateFormat"/>
<HTML>
<HEAD>
<TITLE></TITLE>
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
.form1 {
	BACKGROUND-COLOR: #f6f6f6; BORDER-BOTTOM: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid;FONT-FAMILY: "宋体"; FONT-SIZE: 9pt;
}
.form2 {
	BACKGROUND-COLOR: #E3E3E3; BORDER-BOTTOM: #ffffff 2px solid; BORDER-LEFT: #ffffff 2px solid; BORDER-RIGHT: #ffffff 2px solid; BORDER-TOP: #ffffff 2px solid;FONT-FAMILY: "宋体"; FONT-SIZE: 9pt;
		}
.form3 {
	BACKGROUND-COLOR: #E3E3E3; BORDER-BOTTOM: #007777 2px solid; BORDER-LEFT: #007777 2px solid; BORDER-RIGHT: #007777 2px solid; BORDER-TOP: #007777 2px solid; FONT-FAMILY: "宋体"; FONT-SIZE: 9pt;height:16px
}
.form4 {
	BACKGROUND-COLOR: #ffffff; BORDER-BOTTOM: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid;FONT-FAMILY: "宋体"; FONT-SIZE: 9pt;
}
.border {  border-color: black black #000000; border-bottom-width: 1px}
</STYLE>
<META content=zh-cn http-equiv=Content-Language>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<META content="MSHTML 5.00.2614.3500" name=GENERATOR>
<META content=FrontPage.Editor.Document name=ProgId>
<script language="javascript">
<!--
function re()
	{
		var receiver=form1.text1.value;
		//匹配*@*.com(^代表字符串开头；$代表字符串结尾；\w*代表包括数字和大小写字母的组合；)
		myreg=/^\w*@\.\w*$/;
		//不能空,邮件地址yy@.的格式
		if (receiver=="")
			{
				alert("收件人不能为空!");
				return(false);
			}
		else
			{
				if (myreg.test(receiver))
					{
						return(false);
					}
				else
					{
						alert("请输入正确的地址!");
					}
			}
	}
//-->
</script>
</HEAD>
<BODY leftMargin=0 topmarging="0"
style="BORDER-BOTTOM: 0px outset; BORDER-LEFT: 0px outset; BORDER-RIGHT: 0px outset; BORDER-TOP: 0px outset" 
topMargin=0 marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td width="50">&nbsp;</td>
    <td>
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <form name="form1" method="post" action="">
          <tr> 
            <td  align="right" colspan="2" height="1" bgcolor="#b6b6b6"> 
          </tr>
          <tr> 
            <td  align="right" colspan="2" height="16" bgcolor="#d6d6d6"> 
          </tr>
          <tr> 
            <td style="padding-left:8px" align="right" height="1" colspan="2" bgcolor="#b6b6b6"> 
          </tr>
          <tr> 
            <td style="padding-left:8px;border-bottom:#d6d6d6 1px double;" align="right" bgcolor="#e6e6e6" width="120" height="22">发 
              件 人：</td>
            <td style="border-left:#c6c6c6 1px double;" bgcolor="#FFFFFF"> 
              <table width="200" border="0" cellspacing="0" cellpadding="0" height="100%">
                <tr> 
                  <td style="border-bottom:#d6d6d6 1px double;"><%=beanGetdata.getFieldValue("frommail",0)%>&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
          <tr> 
            <td style="padding-left:8px;border-bottom:#d6d6d6 1px double" align="right" bgcolor="#e6e6e6" width="120" height="22">收 
              件 人：</td>
            <td style="border-left:#c6c6c6 1px double;" bgcolor="#FFFFFF"> 
              <table width="200" border="0" cellspacing="0" cellpadding="0" height="100%">
                <tr> 
                  <td style="border-bottom:#d6d6d6 1px double;"><%=beanGetdata.getFieldValue("tomail",0)%>&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
          <tr> 
            <td style="padding-left:8px;border-bottom:#d6d6d6 1px double" align="right" height="22" bgcolor="#e6e6e6" width="120">发送时间：</td>
            <td height="14" bgcolor="#FFFFFF" style="border-left:#c6c6c6 1px double;"> 
              <table width="200" border="0" cellspacing="0" cellpadding="0" height="100%" bgcolor="#f6f6f6">
                <tr> 
                  <td style="border-bottom:#d6d6d6 1px double;"><%=eds.format(beanGetdata.getFieldValue("mailtime",0),8)%>&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
          <tr> 
            <td style="padding-left:8px;border-bottom:#d6d6d6 1px double" align="right" height="22" bgcolor="#e6e6e6" width="120">抄　　送：</td>
            <td height="14" style="border-left:#c6c6c6 1px double;" bgcolor="#FFFFFF"> 
              <table width="200" border="0" cellspacing="0" cellpadding="0" height="100%">
                <tr> 
                  <td style="border-bottom:#d6d6d6 1px double;"><%=beanGetdata.getFieldValue("ccmail",0)%>&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
          <tr> 
            <td style="padding-left:8px;" align="right" bgcolor="#e6e6e6" width="120" height="22"> 
              密　　送：</td>
            <td style="border-left:#c6c6c6 1px double;" bgcolor="#FFFFFF"><%=beanGetdata.getFieldValue("bccmail",0)%>&nbsp;</td>
          </tr>
          <tr bgcolor="#d6d6d6"> 
            <td style="padding-left:8px" align="right" colspan="2" height="1"> 
          </tr>
          <tr> 
            <td align="right" bgcolor="#e6e6e6" width="120" height="22" >主　　题：</td>
            <td style="border-left:#c6c6c6 1px double;" bgcolor="#f6f6f6"><%=beanGetdata.getFieldValue("subject",0)%>&nbsp;</td>
          </tr>
          <tr bgcolor="#c6c6c6"> 
            <td  align="right" valign="top" bgcolor="#d6d6d6" height="1">
            <td  align="right" valign="top" height="1">
          </tr>
          <tr bgcolor="#e6e6e6"> 
            <td  align="right" valign="top" colspan="2" height="10">
          </tr>
          <tr bgcolor="#c6c6c6"> 
            <td  align="right" valign="top" colspan="2" height="1"> 
          </tr>
          <tr> 
            <td style="padding-left:8px;padding-top:3px;" align="right" valign="top" bgcolor="#e6e6e6" width="120" height="22">正　　文：</td>
            <td style="padding-left:10px;padding-bottom:4px;padding-right:4px;border-left:#c6c6c6 1px double;border-right:#c6c6c6 1px double;padding-top:4px" bgcolor="#FFFFFF" valign="top"><%=beanGetdata.getFieldValue("content",0)%>&nbsp;</td>
          </tr>
          <tr> 
            <td  align="right" valign="top" colspan="2" height="1" bgcolor="#d6d6d6"> 
          </tr>
          <tr> 
            <td style="padding-left:8px" align="right" bgcolor="#e6e6e6" width="120" height="22">附　　件：</td>
            <td style="border-left:#c6c6c6 1px double;" bgcolor="#FFFFFF">&nbsp;</td>
          </tr>
          <tr> 
            <td  align="right" valign="top" colspan="2" height="1" bgcolor="#d6d6d6"> 
          </tr>
          <tr bgcolor="#e6e6e6"> 
            <td style="padding-left:8px" align="right" valign="top" colspan="2" height="30">&nbsp;</td>
          </tr>
          <tr> 
            <td  align="right" valign="top" colspan="2" height="1" bgcolor="#b6b6b6"> 
          </tr>
        </form>
      </table>
    </td>
    <td width="50">&nbsp;</td>
  </tr>
</table>
</BODY></HTML>
