<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="eds" scope="request" class="com.htyz.util.BeanDateFormat"/>
<jsp:useBean id="beanGetdata" scope="request" class="com.htyz.beanGetdata"/>
<%
String navigation = (String)request.getAttribute("navigation");
String s_msg = (String)request.getAttribute("message");
String folder = (String)request.getAttribute("folder");
String action = (String)request.getAttribute("action");
System.out.println(action);
if(action==null)
	action="new";
String s_Subject="";
String s_Content="";
String s_Tomail="";

if(!action.equalsIgnoreCase("new"))
{
	s_Subject="FW:"+beanGetdata.getFieldValue("subject",0);
	s_Content="\r\n\r\n------------------------------------------\r\nԭ��:\r\n"+beanGetdata.getFieldValue("content",0);
}
if(action.equalsIgnoreCase("reply"))
{
	s_Tomail=beanGetdata.getFieldValue("frommail",0);
}
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
.form1 {
	BACKGROUND-COLOR: #ffffff; BORDER-BOTTOM: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid;FONT-FAMILY: "����"; FONT-SIZE: 9pt;
}
.form2 {
	BACKGROUND-COLOR: #ffffff; BORDER-BOTTOM:#000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid;FONT-FAMILY: "����"; FONT-SIZE: 9pt;
		}
.form3 {
	BACKGROUND-COLOR: #E3E3E3; BORDER-BOTTOM: #007777 2px solid; BORDER-LEFT: #007777 2px solid; BORDER-RIGHT: #007777 2px solid; BORDER-TOP: #007777 2px solid; FONT-FAMILY: "����"; FONT-SIZE: 9pt;height:16px
}
.form4 {
	BACKGROUND-COLOR: #ffffff; BORDER-BOTTOM: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid;FONT-FAMILY: "����"; FONT-SIZE: 9pt;
}
</STYLE>


<META content=zh-cn http-equiv=Content-Language>
<script language="javascript">
<!--
function re()
	{
		var receiver=form1.text1.value;
		//ƥ��*@*.com(^�����ַ�����ͷ��$�����ַ�����β��\w*����������ֺʹ�Сд��ĸ����ϣ�)
		myreg=/^\w*@\.\w*$/;
		//���ܿ�,�ʼ���ַyy@.�ĸ�ʽ
		if (receiver=="")
			{
				alert("�ռ��˲���Ϊ��!");
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
						alert("��������ȷ�ĵ�ַ!");
					}
			}
	}
//-->
</script>
</HEAD>
<BODY bgColor=#F6F6F6 leftMargin=0 topmarging="0"
style="BORDER-BOTTOM: 0px outset; BORDER-LEFT: 0px outset; BORDER-RIGHT: 0px outset; BORDER-TOP: 0px outset" 
topMargin=0>
<%@ include file="wrtmail_title.jsp"%>
<form name="form1" method="post" action="">
  <table width="100%" border="0" cellspacing="2" cellpadding="0">
    <tr> 
      <td style="padding-left:8px">�� �� �ˣ�</td>
      <td> 
        <input type="text" name="text1" class="form1" size="50" onchange="javascript:re()" value="<%=s_Tomail%>">
      </td>
    </tr>
    <tr> 
      <td style="padding-left:8px">�������ͣ�</td>
      <td> 
        <input type="text" name="textfield2" class="form1" size="50">
      </td>
    </tr>
    <tr> 
      <td style="padding-left:8px"> �ܡ����ͣ�</td>
      <td> 
        <input type="text" name="textfield3" class="form1" size="50">
      </td>
    </tr>
    <tr> 
      <td style="padding-left:8px">�������⣺</td>
      <td> 
        <input type="text" name="textfield4" class="form1" size="50" value="<%=s_Subject%>">
      </td>
    </tr>
    <tr> 
      <td style="padding-left:8px">����������</td>
      <td> 
        <input type="text" name="textfield42" class="form1" size="50">
      </td>
    </tr>
    <tr> 
      <td style="padding-left:8px">�������ģ�</td>
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td colspan="2" align="left" style="padding-left:50px"> 
        <textarea name="textfield5" cols="80" class="form2" rows="10"><%=s_Content%></textarea>
      </td>
    </tr>
    <tr> 
      <td align="right" height="5">&nbsp;</td>
      <td height="5"> 
        <p> ������������ ������������������������������ </p>
      </td>
    </tr>
  </table>
</form>
<p>&nbsp;</p>
</BODY></HTML>
