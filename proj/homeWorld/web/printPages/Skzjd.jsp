<%@ page language="java"
 contentType="text/html; charset=GBK"
 pageEncoding="GBK"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>˰��װ��װ����</title>
 <script language="javascript">
    OLECMDID_PRINT = 6
    OLECMDID_PRINTPREVIEW = 7
    OLECMDID_PAGESETUP = 8
    OLECMDEXECOPT_DONTPROMPTUSER = 2
    OLECMDEXECOPT_PROMPTUSER = 1
    
	function javaPrint(oleCmdId, oleCmdExecopt) {
		document.all.divPrint.style.visibility = "hidden";
		document.body.focus();
		IEWB.ExecWB(oleCmdId, oleCmdExecopt);
		document.all.divPrint.style.visibility = "visible";
	}
	
	function setVariables() {
		object = "divPrint";
		if (navigator.appname=="Netscape") {
			v=".top=";
			dS="document.";
			sD="";
			y="window.pageYOffset";
		} else {
			v=".pixelTop=";
			dS="";
			sD=".style";
			y="document.body.scrollTop";
		}
	}
	
	function checkLocation() {
		yy=eval(y);
		eval(dS + object + sD + v +yy);
		setTimeout("checkLocation()", 10);
		
	}
	
</script>

<Script Language='JavaScript' For='window' event='onbeforeprint'>
	biasLine.style.pixelTop = biasLine.style.pixelTop - 15 * 1
	biasLine.style.pixelLeft = biasLine.style.pixelLeft - 10 * 1

   document.all.divPrint.style.visibility = "hidden";
   return true;
</Script>
<Script Language='JavaScript' For='window' event='onafterprint'>
 	biasLine.style.pixelTop = biasLine.style.pixelTop + 15 * 1
	biasLine.style.pixelLeft = biasLine.style.pixelLeft + 10 * 1
   document.all.divPrint.style.visibility = "visible";
   return true;
</Script>

</head>

<body onload="setVariables();checkLocation(); this.focus()">

<html:errors property="SkzjdError"/>
<object id="IEWB" width=0 height=0 classid="clsid:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
<div id="divPrint" style="position:absolute; visibility:visible; right:0px; top:0px; z-index:5">
<form>
<table cellSpacing="0" cellPadding="0" border="1" bgcolor="#3366FF">
<tr>
<td>
<input type="button" onClick="javaPrint(OLECMDID_PAGESETUP, OLECMDEXECOPT_DONTPROMPTUSER)" value="ҳ������">
<input type="button" onClick="javaPrint(OLECMDID_PRINTPREVIEW, OLECMDEXECOPT_PROMPTUSER)" value="��ӡԤ��">
<input type="button" onClick="javaPrint(OLECMDID_PRINT, OLECMDEXECOPT_DONTPROMPTUSER)" value="��ӡ">
</td>
</tr>
</table>
</form>
</div>
<logic:match name="skzjdForm" property="haveData" value="true">
<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="972" id="AutoNumber1">
  <tr>
    <td align="center" colspan="2">
    <font size="5">˰��װ��װ����</font> </td>
  </tr>
  <tr>
    <td align="left" colspan="2">
    <font size="2">��ţ�<bean:write name="skzjdForm" property="instId"/> </font> </td>
  </tr>
  <tr>
    <td width="972" valign="top" colspan="2">
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="972" id="AutoNumber2">
      <tr>
        <td width="162" align="center" height="25">��˰������</td>
        <td width="810" colspan="5" nowrap height="25"><font face="����_GB2312">&nbsp;<bean:write name="skzjdForm" property="taxpayerName"/></font></td>
      </tr>
      <tr>
        <td width="162" align="center" height="26">˰��ǼǺ�</td>
        <td width="162" nowrap height="26"><font face="����_GB2312">&nbsp;<bean:write name="skzjdForm" property="taxRegisterNumber"/></font></td>
        <td width="162" align="center" height="26">���������</td>
        <td width="162" nowrap height="26"><font face="����_GB2312">&nbsp;<bean:write name="skzjdForm" property="computerId"/></font></td>
        <td width="162" align="center" height="26">ԤԼ��װʱ��</td>
        <td width="162" nowrap height="26"><font face="����_GB2312">&nbsp;<bean:write name="skzjdForm" property="preInstallDate"/></font></td>
      </tr>
      <tr>
        <td width="162" align="center" height="25">��λ��ַ</td>
        <td width="810" colspan="5" nowrap height="25"><font face="����_GB2312">&nbsp;<bean:write name="skzjdForm" property="address"/></font></td>
      </tr>
      <tr>
        <td width="162" align="center" height="26">��ϵ��</td>
        <td width="162" nowrap height="26"><font face="����_GB2312">&nbsp;<bean:write name="skzjdForm" property="linkman"/></font></td>
        <td width="162" align="center" height="26">�绰</td>
        <td width="162" nowrap height="26"><font face="����_GB2312">&nbsp;<bean:write name="skzjdForm" property="phone"/></font></td>
        <td width="162" align="center" height="26">ʵ�ʰ�װʱ��</td>
        <td width="162" nowrap height="26"><font face="����_GB2312">&nbsp;<bean:write name="skzjdForm" property="instDate"/></font></td>
      </tr>
      
      <tr>
        <td colspan="6" width="972">
        <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="972" id="AutoNumber3">
          <tr>
            <td width="72" style="border-right-style: solid; border-right-width: 1; border-bottom-style: solid; border-bottom-width: 1" align="center" height="26">
            ���</td>
            <td width="180" style="border-right-style: solid; border-right-width: 1; border-bottom-style: solid; border-bottom-width: 1" align="center" height="26">
            �豸�ͺ�</td>
            <td width="180" style="border-right-style: solid; border-right-width: 1; border-bottom-style: solid; border-bottom-width: 1" align="center" height="26">
            �豸���</td>
            <td width="180" style="border-right-style: solid; border-right-width: 1; border-bottom-style: solid; border-bottom-width: 1" align="center" height="26">
            ��������ʼ������</td>
            <td width="180" style="border-right-style: solid; border-right-width: 1; border-bottom-style: solid; border-bottom-width: 1" align="center" height="26">
            ���������</td>
            <td width="180" style="border-bottom-style: solid; border-bottom-width: 1" align="center" height="26">
            IC����</td>
          </tr>
			<%
			String strEquipmentType = "";
			String strEquipmentNumber = "";
			String strPasswordInitOrderNumber = "";
			String strPasswordNumber = "";
			String strIcCardNumber = "";
			int biasLeft = 10;
			int biasTop = 188;
			%>
		<logic:iterate id="sequence_number" name="skzjdForm" property="sequenceNumber" indexId="index">
			<%
			strEquipmentType = "equipmentType[" + index + "]";
			strEquipmentNumber = "equipmentNumber[" + index + "]";
			strPasswordInitOrderNumber = "passwordInitOrderNumber[" + index + "]";
			strPasswordNumber = "passwordNumber[" + index + "]";
			strIcCardNumber = "icCardNumber[" + index + "]";
			biasTop += 22;
			%>
          
          <tr>
            <td width="72" style="border-right-style: solid; border-right-width: 1; border-bottom-style: solid; border-bottom-width: 1" align="center" nowrap height="22">
            <font face="����_GB2312">&nbsp;<bean:write name="sequence_number" filter="false"/></font></td>
            <td width="180" style="border-right-style: solid; border-right-width: 1; border-bottom-style: solid; border-bottom-width: 1" nowrap height="22">
            <font face="����_GB2312">&nbsp;<bean:write name="skzjdForm" property='<%=strEquipmentType%>' filter="false"/></font>
            </td>
            <td width="180" style="border-right-style: solid; border-right-width: 1; border-bottom-style: solid; border-bottom-width: 1" nowrap height="22">
            <font face="����_GB2312">&nbsp;<bean:write name="skzjdForm" property='<%=strEquipmentNumber%>' filter="false"/></font>
            </td>
            <td width="180" style="border-right-style: solid; border-right-width: 1; border-bottom-style: solid; border-bottom-width: 1" nowrap height="22">
            <font face="����_GB2312">&nbsp;<bean:write name="skzjdForm" property='<%=strPasswordInitOrderNumber%>' filter="false"/></font>
            </td>
            <td width="180" style="border-right-style: solid; border-right-width: 1; border-bottom-style: solid; border-bottom-width: 1" nowrap height="22">
            <font face="����_GB2312">&nbsp;<bean:write name="skzjdForm" property='<%=strPasswordNumber%>' filter="false"/></font>
            </td>
            <td width="180" style="border-bottom-style: solid; border-bottom-width: 1" nowrap height="22">
            <font face="����_GB2312">&nbsp;<bean:write name="skzjdForm" property='<%=strIcCardNumber%>' filter="false"/></font>
            </td>
          </tr>
  		</logic:iterate>
          
        </table>
        </td>
      </tr>
      <tr>
        <td width="162" style="border-bottom-style: none;" align="right" height="19">
        �û����</td>
        <td width="162" rowspan="2" align="center" height="42">�ܺ�</td>
        <td width="162" rowspan="2" align="center" height="42">��</td>
        <td width="162" rowspan="2" align="center" height="42">һ��</td>
        <td width="162" rowspan="2" align="center" height="42">����</td>
        <td width="162" rowspan="2" align="center" height="42">��</td>
      </tr>
      <tr>
        <td width="162" style="border-top-style: none;" height="21" valign="bottom">
        ������Ŀ</td>
      </tr>
      <tr>
        <td width="162" height="22" align="center">��װ����</td>
        <td width="162" height="22">��</td>
        <td width="162" height="22">��</td>
        <td width="162" height="22">��</td>
        <td width="162" height="22">��</td>
        <td width="162" height="22">��</td>
      </tr>
      <tr>
        <td width="162" height="22" align="center">��ѵ</td>
        <td width="162" height="22">��</td>
        <td width="162" height="22">��</td>
        <td width="162" height="22">��</td>
        <td width="162" height="22">��</td>
        <td width="162" height="22">��</td>
      </tr>
      <tr>
        <td width="162" height="22" align="center">����</td>
        <td width="162" height="22">��</td>
        <td width="162" height="22">��</td>
        <td width="162" height="22">��</td>
        <td width="162" height="22">��</td>
        <td width="162" height="22">��</td>
      </tr>
      <tr>
        <td width="162" height="22" align="center">����</td>
        <td width="162" height="22">��</td>
        <td width="162" height="22">��</td>
        <td width="162" height="22">��</td>
        <td width="162" height="22">��</td>
        <td width="162" height="22">��</td>
      </tr>
      <tr>
        <td width="990" height="57" align="center" colspan="6">
        �û�ǩ�֣��������������������������ڣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </tr>
      <tr>
        <td width="162" height="22" align="center">��װ����ʦ</td>
        <td width="330" height="22" colspan="2">��</td>
        <td width="162" height="22" align="center">���</td>
        <td width="330" height="22" colspan="2">��</td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="609" align="left">
    <font size="2">�Ʊ��ˣ�<font face="����_GB2312"><bean:write name="skzjdForm" property="orderOwner" filter="false"/></font></font></td>
    <td width="370" align="right">
    <font size="2">�Ʊ����ڣ�<font face="����_GB2312"><bean:write name="skzjdForm" property="createDate"  filter="false"/></font></font> </td>
  </tr>
  </table>
<div id="biasLine" style="position:absolute; visibility:visible; left:<%=biasLeft%>; top:<%=biasTop%>; z-index:-1">
<img border="0" src="<%=request.getContextPath()%>/img/Skzjd.JPG" width="160" height="43">
</div>

<!--
<form>
�������ƶ�����:<input type="text" id="basePixel" value="22" size="20"/><br>

<input type="button" onclick="moveUpBiasLine(this.form)" value="�����ƶ�б��"/>
<input type="button" onclick="moveDownBiasLine(this.form)" value="�����ƶ�б��"/>
<input type="button" onclick="moveLeftBiasLine(this.form)" value="�����ƶ�б��"/>
<input type="button" onclick="moveRightBiasLine(this.form)" value="�����ƶ�б��"/>
<input type="button" onclick="show()" value="��ѯб��λ��"/>
</form>
<script>
	function moveDownBiasLine(form) {
		biasLine.style.pixelTop = biasLine.style.pixelTop + form.basePixel.value * 1
	}
	function moveUpBiasLine(form) {
		biasLine.style.pixelTop = biasLine.style.pixelTop - form.basePixel.value
	}
	function moveLeftBiasLine(form) {
		biasLine.style.pixelLeft= biasLine.style.pixelLeft - form.basePixel.value
	}
	function moveRightBiasLine(form) {
		biasLine.style.pixelLeft= biasLine.style.pixelLeft + form.basePixel.value * 1
	}
	
	function show() {
		alert("б��λ��Ϊ: " + biasLine.style.pixelLeft+ "/" + biasLine.style.pixelTop)
	}
</script>

-->
  
</logic:match>


</body>

</html>