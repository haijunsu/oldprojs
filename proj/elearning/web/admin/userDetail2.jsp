<%@page contentType="text/html;charset=GB2312"%>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>

<html>
<head> 
<title>����E-learning������ѵƽ̨</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javaScript" type="text/javascript" SRC="board.js"></SCRIPT>
<!--end Java-->
<Script language="javascript">
function valid(){
	if (document.creator.userid.value.length==0){
		alert("�û�������Ϊ�գ���");
		document.creator.userid.focus();
		return false;
	}
	if (document.creator.pass.value.length==0){
		alert("�û����벻��Ϊ�գ���");
		document.creator.pass.focus();
		return false;
	}
	if (document.creator.pass.value!=document.creator.repass.value){
		alert("��������������벻һ�£���");
		document.creator.pass.focus();
		return false;
	}

	if (document.creator.email.value.length==0){
		alert("��û�������ʼ���ַ����");
		document.creator.email.focus();
		return false;
	}
	return true;
}
</Script>

<!--css info(editable)-->
<style>
A:visited{TEXT-DECORATION: none}
A:active{TEXT-DECORATION: none}
A:hover{text-decoration: underline;}
A:link{text-decoration: none;}
.t{LINE-HEIGHT: 1.7}
BODY{font-family:Verdana, Tahoma, Arial; FONT-SIZE: 9pt; 
SCROLLBAR-HIGHLIGHT-COLOR: buttonface;
SCROLLBAR-SHADOW-COLOR: buttonface;
SCROLLBAR-3DLIGHT-COLOR: buttonhighlight;
SCROLLBAR-TRACK-COLOR: #eeeeee;
SCROLLBAR-DARKSHADOW-COLOR: buttonshadow}
TD,DIV,form ,OPTION,P,TD,BR{font-family:Verdana, Tahoma, Arial; FONT-SIZE: 9pt  } 
INPUT{BORDER-TOP-WIDTH: 1px; PADDING-RIGHT: 1px; PADDING-LEFT: 1px; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 9pt; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 1px; BORDER-BOTTOM-COLOR: #cccccc; PADDING-BOTTOM: 1px; BORDER-TOP-COLOR: #cccccc; PADDING-TOP: 1px; HEIGHT: 18px; BORDER-RIGHT-WIDTH: 1px; BORDER-RIGHT-COLOR: #cccccc}
textarea, select {border-width: 1; border-color: #000000; background-color: #efefef; font-family:Verdana, Tahoma, Arial; font-size: 9pt;   font-style: bold;}
#category {
	 font-family: Verdana, Tahoma, Arial;
	 font-size: 9pt;
	 line-height: 150%;
}
</style>
<script>
function Check(){
var Name=document.creator.userid.value;
window.open("<%=request.getContextPath()%>/checkname.jsp?name="+Name,"Check","width=220,height=60,status=0,scrollbars=0,resizable=1,menubar=0,toolbar=0,location=0");
}
</script>
<!--end css info-->
</head>

<jsp:useBean id="beanUserInfo" scope="request" class="system.beanUserInfo"/>

<body bgcolor=#d7d7d7 background=<%=request.getContextPath()%>/images/bg.gif alink=#333333 vlink=#333333 link=#333333 topmargin=0 leftmargin=0>
<img src=<%=request.getContextPath()%>/images/none.gif width=10 height=5 border=0><br>
<table cellpadding=0 cellspacing=0 border=0 width=770 align=center>
  <tr>
    <td valign=bottom><font color=#000077><img src=<%=request.getContextPath()%>/images/closedfold.gif>
      <a href="leoboard.cgi">����E-learning������ѵƽ̨</a> 
      <img src=<%=request.getContextPath()%>/images/openfold.gif width="12" height="12">&nbsp�޸�ע����Ϣ</td>
    <td valign=bottom align=right>
    ��<a href="javascript:openScript('help.cgi?helpon=�û�ע��',500,400)">
      <img src=<%=request.getContextPath()%>/images/help_b.gif width="28" height="29" border=0></a></td>
  </tr>
</table>
<br>
<table cellpadding=0 cellspacing=0 border=0 width=770 align=center>
   <tr>
      <td>
         <table cellpadding=0 cellspacing=0 border=0 width=100%>
<TR>
             <Form name="creator" METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/system/userProfiles">
                
          <tr>
          <td width=17><img src=<%=request.getContextPath()%>/images/ktopleft.gif width=17 height="35"></td>
          <td background=<%=request.getContextPath()%>/images/ktop.gif colspan=2><img src=<%=request.getContextPath()%>/images/openfold.gif width=12 height="12" align=absmiddle> 
            <b><font color=#000077>����д����ע����Ϣ��</b></td>
          <td width=17><img src=<%=request.getContextPath()%>/images/ktopright.gif width=17></td>
        </tr><tr>
          <td width=17 background=<%=request.getContextPath()%>/images/kleft.gif bgcolor=#d7d7d7></td>
<td bgcolor=#d7d7d7 width=40% height=50><font color=#000077><b>�û�����</b><br>
            ע���û������ܳ���20���ַ���10�����֣�</td>
	<td bgcolor=#d7d7d7 width=60%>
	<input type=text maxlength="20" name="userid" value="<%=beanUserInfo.getUserid()%>" disabled>&nbsp;<font color=red>* ����ܸ���</td>

<td width=17 background=<%=request.getContextPath()%>/images/kright.gif bgcolor=#d7d7d7></td>
	<tr>
          <td width=17><img src=<%=request.getContextPath()%>/images/ksplitleft.gif border=0 width=17></td>
          <td background=<%=request.getContextPath()%>/images/ksplitmiddle.gif height=2 width=100% colspan=2><img src=<%=request.getContextPath()%>/images/none.gif height=2 border=0></td>
          <td><img src=<%=request.getContextPath()%>/images/ksplitright.gif border=0 width=17></td>
        </tr>
         <tr>
          <td width=17 background=<%=request.getContextPath()%>/images/kpartleft.gif bgcolor=#e7e7e7></td>
        <td bgcolor=#e7e7e7 width=40% height=60 rowspan=2><font color=#000077><b>���룺 (���20λ)</b><br>���������룬���ִ�Сд<br>ֻ��ʹ�ô�Сд��ĸ�����ֵ����</td>
        <td bgcolor=#e7e7e7 width=60%><input type=password name="pass" maxlength=20 value="">&nbsp;* ���������д</td><td width=17 background=<%=request.getContextPath()%>/images/kpartright.gif bgcolor=#e7e7e7></td>
        </tr>
        	<tr><td width=17 background=<%=request.getContextPath()%>/images/kpartleft.gif bgcolor=#e7e7e7></td>
        <td bgcolor=#e7e7e7><input type=password name="repass" maxlength=20 value="">&nbsp;* ����һ�飬�Ա�ȷ����</td><td width=17 background=<%=request.getContextPath()%>/images/kpartright.gif bgcolor=#e7e7e7></td>
        </tr>
		<tr>
          <td width=17><img src=<%=request.getContextPath()%>/images/ksplitleft.gif width=17 height="2" border=0></td>
          <td background=<%=request.getContextPath()%>/images/ksplitmiddle.gif height=2 width=100% colspan=2><img src=<%=request.getContextPath()%>/images/none.gif height=2 border=0></td>
          <td><img src=<%=request.getContextPath()%>/images/ksplitright.gif border=0 width=17></td>
        </tr>
		<tr>
<td width=17 background=<%=request.getContextPath()%>/images/kleft.gif bgcolor=#d7d7d7></td>
<td bgcolor=#d7d7d7 height=60><font color=#000077><b>�ʼ���ַ��</b><br>��������Ч���ʼ���ַ���⽫ʹ�����õ���̳�е����й���</td>
<td bgcolor=#d7d7d7><input type=text name="email" value="">&nbsp;* ���������д</td><td width=17 background=<%=request.getContextPath()%>/images/kright.gif bgcolor=#d7d7d7></td></tr>
		<tr>
          <td width=17><img src=<%=request.getContextPath()%>/images/ksplitleft.gif width=17 border=0></td>
          <td background=<%=request.getContextPath()%>/images/ksplitmiddle.gif height=2 width=100% colspan=2><img src=<%=request.getContextPath()%>/images/none.gif border=0></td>
          <td><img src=<%=request.getContextPath()%>/images/ksplitright.gif border=0 width=17></td>
        </tr>
        <tr><td width=17 background=<%=request.getContextPath()%>/images/kpartleft.gif bgcolor=#e7e7e7></td>
<td bgcolor=#e7e7e7 height=40><font color=#000077><b>��˾���ƣ�</b>��������д<br>��� 50 ���ֽڣ�25�����֣�</td> 
<td bgcolor=#e7e7e7><input type=text name="company" size=50 maxlength=50 value=""></td>
<td width=17 background=<%=request.getContextPath()%>/images/kpartright.gif bgcolor=#e7e7e7></td></tr>
<tr><td width=17 background=<%=request.getContextPath()%>/images/kpartleft.gif bgcolor=#e7e7e7></td>
<td bgcolor=#e7e7e7 height=40><font color=#000077><b>���ڲ��ţ�</b>�������ʹ��<br>��� 20 ���ֽڣ�10�����֣�</td> 
<td bgcolor=#e7e7e7><input type=text name="department" value="" size=20 maxlength=20 value=""></td><td width=17 background=<%=request.getContextPath()%>/images/kpartright.gif bgcolor=#e7e7e7></td></tr>
		<tr>
          <td width=17><img src=<%=request.getContextPath()%>/images/ksplitleft.gif width=17 height="2" border=0></td>
          <td background=<%=request.getContextPath()%>/images/ksplitmiddle.gif height=2 width=100% colspan=2><img src=<%=request.getContextPath()%>/images/none.gif height=2 border=0></td>
          <td><img src=<%=request.getContextPath()%>/images/ksplitright.gif border=0 width=17></td>
        </tr>
        <tr><td width=17 background=<%=request.getContextPath()%>/images/kleft.gif bgcolor=#d7d7d7></td><td bgcolor=#d7d7d7 valign=middle colspan=2 align=center height=24><font color=#880052><b>������Ϣ������¼ϵͳ����Ҫ��Ϣ����������룡</b></font></td><td width=17 background=<%=request.getContextPath()%>/images/kright.gif bgcolor=#d7d7d7></td></tr>
			<tr>
          <td width=17><img src=<%=request.getContextPath()%>/images/ksplitleft.gif width=17 height="2" border=0></td>
          <td background=<%=request.getContextPath()%>/images/ksplitmiddle.gif height=2 width=100% colspan=2><img src=<%=request.getContextPath()%>/images/none.gif height=2 border=0></td>
          <td><img src=<%=request.getContextPath()%>/images/ksplitright.gif border=0 width=17></td>
        </tr></table></td></tr></table>
<table cellpadding=0 cellspacing=0 border=0 width=770 bgcolor=#ffffff align=center id=adv style="DISPLAY: none"><tr><td>
<table cellpadding=4 cellspacing=1 border=0 width=100%>
<tr>
<td bgcolor=#d7d7d7><font color=#000077><b>��ʵ������</b><br>�����������ʵ����,�Ա����ԱΪ������Ȩ�ޡ�<br>�����ѡ</td>
<td bgcolor=#d7d7d7><input type=text name="username" value="<%=beanUserInfo.getUserName()%>"></td>
</tr>
<tr>
<td bgcolor=#d7d7d7 width=40%><font color=#000077><b>��ʾ�ʼ���ַ</b><br>���Ƿ�ϣ��������������֮����ʾ�����ʼ���</td>
<td bgcolor=#d7d7d7 width=60%><font color=#000077>
	<input name="contract" type="radio" value="yes" <%=beanUserInfo.getContract().equals("yes")?"checked":""%>> �ǡ���
	<input name="contract" type="radio" value="msn" <%=beanUserInfo.getContract().equals("msn")?"checked":""%>> MSN����
	<input name="contract" type="radio" value="no" <%=beanUserInfo.getContract().equals("no")?"checked":""%>> ��</font>
</td>
</tr><tr>
<td bgcolor=#d7d7d7><font color=#000077><b>�Ա�</b></td><td bgcolor=#d7d7d7>
<select name="sex" size="1">
<option value="n" <%=beanUserInfo.getSex().equals("n")?"selected":""%>>���� </option>
<option value="m" <%=beanUserInfo.getSex().equals("m")?"selected":""%>>˧�� </option>
<option value="f" <%=beanUserInfo.getSex().equals("f")?"selected":""%>>��Ů </option>
</select>
</td></tr>
<tr><td bgcolor=#d7d7d7><font color=#000077><b>���գ�</b>�粻����д����ȫ�����ա������ѡ</td>
<td bgcolor=#d7d7d7><input type="text" name="year" size=4 maxlength=4 value="<%=beanUserInfo.getYear()%>">�� 
  <select name="month">
      <option value="" selected></option>
      <option value="01" <%=beanUserInfo.getMonth().equals("01")?"selected":""%>>01</option>
      <option value="02" <%=beanUserInfo.getMonth().equals("02")?"selected":""%>>02</option>
      <option value="03" <%=beanUserInfo.getMonth().equals("03")?"selected":""%>>03</option>
      <option value="04" <%=beanUserInfo.getMonth().equals("04")?"selected":""%>>04</option>
      <option value="05" <%=beanUserInfo.getMonth().equals("05")?"selected":""%>>05</option>
      <option value="06" <%=beanUserInfo.getMonth().equals("06")?"selected":""%>>06</option>
      <option value="07" <%=beanUserInfo.getMonth().equals("07")?"selected":""%>>07</option>
      <option value="08" <%=beanUserInfo.getMonth().equals("08")?"selected":""%>>08</option>
      <option value="09" <%=beanUserInfo.getMonth().equals("09")?"selected":""%>>09</option>
      <option value="10" <%=beanUserInfo.getMonth().equals("10")?"selected":""%>>10</option>
      <option value="11" <%=beanUserInfo.getMonth().equals("11")?"selected":""%>>11</option>
      <option value="12" <%=beanUserInfo.getMonth().equals("12")?"selected":""%>>12</option>
  </select>��
   <select name="day">
      <option value="" selected></option>
      <option value="01" <%=beanUserInfo.getDay().equals("01")?"selected":""%>>01</option>
      <option value="02" <%=beanUserInfo.getDay().equals("02")?"selected":""%>>02</option>
      <option value="03" <%=beanUserInfo.getDay().equals("03")?"selected":""%>>03</option>
      <option value="04" <%=beanUserInfo.getDay().equals("04")?"selected":""%>>04</option>
      <option value="05" <%=beanUserInfo.getDay().equals("05")?"selected":""%>>05</option>
      <option value="06" <%=beanUserInfo.getDay().equals("06")?"selected":""%>>06</option>
      <option value="07" <%=beanUserInfo.getDay().equals("07")?"selected":""%>>07</option>
      <option value="08" <%=beanUserInfo.getDay().equals("08")?"selected":""%>>08</option>
      <option value="09" <%=beanUserInfo.getDay().equals("09")?"selected":""%>>09</option>
      <option value="10" <%=beanUserInfo.getDay().equals("10")?"selected":""%>>10</option>
      <option value="11" <%=beanUserInfo.getDay().equals("11")?"selected":""%>>11</option>
      <option value="12" <%=beanUserInfo.getDay().equals("12")?"selected":""%>>12</option>
      <option value="13" <%=beanUserInfo.getDay().equals("13")?"selected":""%>>13</option>
      <option value="14" <%=beanUserInfo.getDay().equals("14")?"selected":""%>>14</option>
      <option value="15" <%=beanUserInfo.getDay().equals("15")?"selected":""%>>15</option>
      <option value="16" <%=beanUserInfo.getDay().equals("16")?"selected":""%>>16</option>
      <option value="17" <%=beanUserInfo.getDay().equals("17")?"selected":""%>>17</option>
      <option value="18" <%=beanUserInfo.getDay().equals("18")?"selected":""%>>18</option>
      <option value="19" <%=beanUserInfo.getDay().equals("19")?"selected":""%>>19</option>
      <option value="20" <%=beanUserInfo.getDay().equals("20")?"selected":""%>>20</option>
      <option value="21" <%=beanUserInfo.getDay().equals("21")?"selected":""%>>21</option>
      <option value="22" <%=beanUserInfo.getDay().equals("22")?"selected":""%>>22</option>
      <option value="23" <%=beanUserInfo.getDay().equals("23")?"selected":""%>>23</option>
      <option value="24" <%=beanUserInfo.getDay().equals("24")?"selected":""%>>24</option>
      <option value="25" <%=beanUserInfo.getDay().equals("25")?"selected":""%>>25</option>
      <option value="26" <%=beanUserInfo.getDay().equals("26")?"selected":""%>>26</option>
      <option value="27" <%=beanUserInfo.getDay().equals("27")?"selected":""%>>27</option>
      <option value="28" <%=beanUserInfo.getDay().equals("28")?"selected":""%>>28</option>
      <option value="29" <%=beanUserInfo.getDay().equals("29")?"selected":""%>>29</option>
      <option value="30" <%=beanUserInfo.getDay().equals("30")?"selected":""%>>30</option>
      <option value="31" <%=beanUserInfo.getDay().equals("31")?"selected":""%>>31</option>
  </select>��
</td>
</tr>
<tr><SCRIPT language=javascript>
function showsx(){document.images.shengxiaos.src="<%=request.getContextPath()%>/images/sx/"+document.creator.shengxiao.options[document.creator.shengxiao.selectedIndex].value+".gif";}
</SCRIPT>
<td bgcolor=#d7d7d7 vAlign=top><font color=#000077><b>������Ф��</b><br>��ѡ������������Ф��<br>�������ȷ���������յĻ�����ô������Ч��</td>
<td bgcolor=#d7d7d7>
<SELECT name="shengxiao" onchange=showsx() size="1">
 <OPTION value="">����</OPTION>
<OPTION value="sx1" <%=beanUserInfo.getShengxiao().trim().equals("sx1" )?"selected":""%>>����</OPTION>
<OPTION value="sx2" <%=beanUserInfo.getShengxiao().equals("sx2" )?"selected":""%>>��ţ</OPTION>
<OPTION value="sx3" <%=beanUserInfo.getShengxiao().equals("sx3" )?"selected":""%>>����</OPTION>
<OPTION value="sx4" <%=beanUserInfo.getShengxiao().equals("sx4" )?"selected":""%>>î��</OPTION>
<OPTION value="sx5" <%=beanUserInfo.getShengxiao().equals("sx5" )?"selected":""%>>����</OPTION>
<OPTION value="sx6" <%=beanUserInfo.getShengxiao().equals("sx6" )?"selected":""%>>����</OPTION>
<OPTION value="sx7" <%=beanUserInfo.getShengxiao().equals("sx7" )?"selected":""%>>����</OPTION>
<OPTION value="sx8" <%=beanUserInfo.getShengxiao().equals("sx8" )?"selected":""%>>δ��</OPTION>
<OPTION value="sx9" <%=beanUserInfo.getShengxiao().equals("sx9" )?"selected":""%>>���</OPTION>
<OPTION value="sx10" <%=beanUserInfo.getShengxiao().equals("sx10")?"selected":""%>>�ϼ�</OPTION>
<OPTION value="sx11" <%=beanUserInfo.getShengxiao().equals("sx11")?"selected":""%>>�繷</OPTION>
<OPTION value="sx12" <%=beanUserInfo.getShengxiao().equals("sx12")?"selected":""%>>����</OPTION>
</SELECT>
 <IMG border=0 name=shengxiaos src="<%=request.getContextPath()%>/images/sx/<%=beanUserInfo.getShengxiao().trim().equals("")?"blank":beanUserInfo.getShengxiao().trim()%>.gif" align="absmiddle">
</TD></tr><tr>
<SCRIPT language=javascript>
function showxz(){document.images.userxzs.src="http:<%=request.getContextPath()%>/images/star/"+document.creator.userxz.options[document.creator.userxz.selectedIndex].value+".gif";}
</SCRIPT>
<td bgcolor=#d7d7d7 vAlign=top><font color=#000077><b>����������</b><br>��ѡ����������������<br>�������ȷ���������յĻ�����ô������Ч��</td>
<td bgcolor=#d7d7d7><SELECT name="userxz" onchange=showxz() size="1">
 <OPTION value="">����</OPTION>
<OPTION value="z1" <%=beanUserInfo.getStars().equals("z1")?"selected":""%>>������(3��21--4��19��)</OPTION> 
<OPTION value="z2" <%=beanUserInfo.getStars().equals("z2")?"selected":""%>>��ţ��(4��20--5��20��)</OPTION> 
<OPTION value="z3" <%=beanUserInfo.getStars().equals("z3")?"selected":""%>>˫����(5��21--6��21��)</OPTION> 
<OPTION value="z4" <%=beanUserInfo.getStars().equals("z4")?"selected":""%>>��з��(6��22--7��22��)</OPTION> 
<OPTION value="z5" <%=beanUserInfo.getStars().equals("z5")?"selected":""%>>ʨ����(7��23--8��22��)</OPTION> 
<OPTION value="z6" <%=beanUserInfo.getStars().equals("z6")?"selected":""%>>��Ů��(8��23--9��22��)</OPTION> 
<OPTION value="z7" <%=beanUserInfo.getStars().equals("z7")?"selected":""%>>�����(9��23--10��23��)</OPTION> 
<OPTION value="z8" <%=beanUserInfo.getStars().equals("z8")?"selected":""%>>��Ы��(10��24--11��21��)</OPTION> 
<OPTION value="z9" <%=beanUserInfo.getStars().equals("z9")?"selected":""%>>������(11��22--12��21��)</OPTION> 
<OPTION value="z10" <%=beanUserInfo.getStars().equals("z10")?"selected":""%>>ħ����(12��22--1��19��)</OPTION>
<OPTION value="z11" <%=beanUserInfo.getStars().equals("z11")?"selected":""%>>ˮƿ��(1��20--2��18��)</OPTION> 
<OPTION value="z12" <%=beanUserInfo.getStars().equals("z12")?"selected":""%>>˫����(2��19--3��20��)</OPTION>
</SELECT>
 <IMG border=0 name=userxzs src="<%=request.getContextPath()%>/images/star/<%=beanUserInfo.getStars().trim().equals("")?"blank":beanUserInfo.getStars().trim()%>.gif" width=15 height=15 align="absmiddle">
</TD>
</TR><tr>
<td bgcolor=#d7d7d7><font color=#000077><b>��ҳ��ַ��</b><br>���������ҳ����������ҳ��ַ�������ѡ</td>
<td bgcolor=#d7d7d7><input type=text name="webaddr" value="<%=beanUserInfo.getWebaddr()%>"></td>
</tr><tr>
<td bgcolor=#d7d7d7><font color=#000077><b>OICQ �ţ�</b><br>������� OICQ����������롣�����ѡ</td>
<td bgcolor=#d7d7d7><input type=text name="oicq" value="<%=beanUserInfo.getOicq()%>"></td>
</tr><tr>
<td bgcolor=#d7d7d7><font color=#000077><b>MSN �ţ�</b><br>������� ICQ����������롣�����ѡ</td>
<td bgcolor=#d7d7d7><input type=text name="msn" value="<%=beanUserInfo.getMsn()%>"></td>
</tr><script language="javascript">
function showflag(){document.images.userflags.src="<%=request.getContextPath()%>/images/flags/"+document.creator.userflag.options[document.creator.userflag.selectedIndex].value+".gif";}
</script>
<tr><td bgcolor=#d7d7d7 valign=top><font face=���� color=#000077><b>���ڹ���:</b><br>��ѡ�������ڵĹ��ҡ�</td>
<td bgcolor=#d7d7d7>
<select name="userflag" size=1 onChange="showflag()">
<option value="blank" selected>����</option>
<option <%=beanUserInfo.getCountry().equals("China")?"selected":""%> value="China">�й�</option>
<option <%=beanUserInfo.getCountry().equals("Angola")?"selected":""%> value="Angola">������</option>
<option <%=beanUserInfo.getCountry().equals("Antigua")?"selected":""%> value="Antigua">�����</option>
<option <%=beanUserInfo.getCountry().equals("Argentina")?"selected":""%> value="Argentina">����͢</option>
<option <%=beanUserInfo.getCountry().equals("Armenia")?"selected":""%> value="Antigua">��������</option>
<option <%=beanUserInfo.getCountry().equals("Australia")?"selected":""%> value="Australia">�Ĵ�����</option>
<option <%=beanUserInfo.getCountry().equals("Austria")?"selected":""%> value="Austria">�µ���</option>
<option <%=beanUserInfo.getCountry().equals("Bahamas")?"selected":""%> value="Bahamas">�͹���</option>
<option <%=beanUserInfo.getCountry().equals("Bahrain")?"selected":""%> value="Bahrain">����</option>
<option <%=beanUserInfo.getCountry().equals("Bangladesh")?"selected":""%> value="Bangladesh">�ϼ���</option>
<option <%=beanUserInfo.getCountry().equals("Barbados")?"selected":""%> value="Barbados">�ͰͶ�˹</option>
<option <%=beanUserInfo.getCountry().equals("Belgium")?"selected":""%> value="Belgium">����ʱ</option>
<option <%=beanUserInfo.getCountry().equals("Bermuda")?"selected":""%> value="Bermuda">��Ľ��</option>
<option <%=beanUserInfo.getCountry().equals("Bolivia")?"selected":""%> value="Bolivia">����ά��</option>
<option <%=beanUserInfo.getCountry().equals("Brazil")?"selected":""%> value="Brazil">����</option>
<option <%=beanUserInfo.getCountry().equals("Brunei")?"selected":""%> value="Brunei">����</option>
<option <%=beanUserInfo.getCountry().equals("Canada")?"selected":""%> value="Canada">���ô�</option>
<option <%=beanUserInfo.getCountry().equals("Chile")?"selected":""%> value="Chile">����</option>
<option <%=beanUserInfo.getCountry().equals("Colombia")?"selected":""%> value="Colombia">���ױ���</option>
<option <%=beanUserInfo.getCountry().equals("Croatia")?"selected":""%> value="Croatia">���޵���</option>
<option <%=beanUserInfo.getCountry().equals("Cuba")?"selected":""%> value="Cuba">�Ű�</option>
<option <%=beanUserInfo.getCountry().equals("Cyprus")?"selected":""%> value="Cyprus">����·˹</option>
<option <%=beanUserInfo.getCountry().equals("Czech_Republic")?"selected":""%> value="Czech_Republic">�ݿ�</option>
<option <%=beanUserInfo.getCountry().equals("Denmark")?"selected":""%> value="Denmark">����</option>
<option <%=beanUserInfo.getCountry().equals("Dominican_Republic")?"selected":""%> value="Dominican_Republic">�������</option>
<option <%=beanUserInfo.getCountry().equals("Ecuador")?"selected":""%> value="Ecuador">��϶��</option>
<option <%=beanUserInfo.getCountry().equals("Egypt")?"selected":""%> value="Egypt">����</option>
<option <%=beanUserInfo.getCountry().equals("Estonia")?"selected":""%> value="Estonia">��ɳ����</option>
<option <%=beanUserInfo.getCountry().equals("Finland")?"selected":""%> value="Finland">����</option>
<option <%=beanUserInfo.getCountry().equals("France")?"selected":""%> value="France">����</option>
<option <%=beanUserInfo.getCountry().equals("Germany")?"selected":""%> value="Germany">�¹�</option>
<option <%=beanUserInfo.getCountry().equals("Great_Britain")?"selected":""%> value="Great_Britain">Ӣ��</option>
<option <%=beanUserInfo.getCountry().equals("Greece")?"selected":""%> value="Greece">ϣ��</option>
<option <%=beanUserInfo.getCountry().equals("Guatemala")?"selected":""%> value="Guatemala">Σ������</option>
<option <%=beanUserInfo.getCountry().equals("Honduras")?"selected":""%> value="Honduras">�鶼��˹</option>
<option <%=beanUserInfo.getCountry().equals("Hungary")?"selected":""%> value="Hungary">������</option>
<option <%=beanUserInfo.getCountry().equals("Iceland")?"selected":""%> value="Iceland">����</option>
<option <%=beanUserInfo.getCountry().equals("India")?"selected":""%> value="India">ӡ��</option>
<option <%=beanUserInfo.getCountry().equals("Indonesia")?"selected":""%> value="Indonesia">ӡ��������</option>
<option <%=beanUserInfo.getCountry().equals("Iran")?"selected":""%> value="Iran">����</option>
<option <%=beanUserInfo.getCountry().equals("Iraq")?"selected":""%> value="Iraq">������</option>
<option <%=beanUserInfo.getCountry().equals("Ireland")?"selected":""%> value="Ireland">������</option>
<option <%=beanUserInfo.getCountry().equals("Israel")?"selected":""%> value="Israel">��ɫ��</option>
<option <%=beanUserInfo.getCountry().equals("Italy")?"selected":""%> value="Italy">�����</option>
<option <%=beanUserInfo.getCountry().equals("Jamaica")?"selected":""%> value="Jamaica">�����</option>
<option <%=beanUserInfo.getCountry().equals("Japan")?"selected":""%> value="Japan">�ձ�</option>
<option <%=beanUserInfo.getCountry().equals("Jordan")?"selected":""%> value="Jordan">Լ��</option>
<option <%=beanUserInfo.getCountry().equals("Kazakstan")?"selected":""%> value="Kazakstan">������</option>
<option <%=beanUserInfo.getCountry().equals("Kenya")?"selected":""%> value="Kenya">������</option>
<option <%=beanUserInfo.getCountry().equals("Kuwait")?"selected":""%> value="Kuwait">������</option>
<option <%=beanUserInfo.getCountry().equals("Latvia")?"selected":""%> value="Latvia">����ά��</option>
<option <%=beanUserInfo.getCountry().equals("Lebanon")?"selected":""%> value="Lebanon">�����</option>
<option <%=beanUserInfo.getCountry().equals("Lithuania")?"selected":""%> value="Lithuania">������</option>
<option <%=beanUserInfo.getCountry().equals("Malaysia")?"selected":""%> value="Malaysia">��������</option>
<option <%=beanUserInfo.getCountry().equals("Malawi")?"selected":""%> value="Malawi">����ά</option>
<option <%=beanUserInfo.getCountry().equals("Malta")?"selected":""%> value="Malta">�����</option>
<option <%=beanUserInfo.getCountry().equals("Mauritius")?"selected":""%> value="Mauritius">ë����˹</option>
<option <%=beanUserInfo.getCountry().equals("Morocco")?"selected":""%> value="Morocco">Ħ���</option>
<option <%=beanUserInfo.getCountry().equals("Mozambique")?"selected":""%> value="Mozambique">Īɣ�ȿ�</option>
<option <%=beanUserInfo.getCountry().equals("Netherlands")?"selected":""%> value="Netherlands">����</option>
<option <%=beanUserInfo.getCountry().equals("New_Zealand")?"selected":""%> value="New_Zealand">������</option>
<option <%=beanUserInfo.getCountry().equals("Nicaragua")?"selected":""%> value="Nicaragua">�������</option>
<option <%=beanUserInfo.getCountry().equals("Nigeria")?"selected":""%> value="Nigeria">��������</option>
<option <%=beanUserInfo.getCountry().equals("Norway")?"selected":""%> value="Norway">Ų��</option>
<option <%=beanUserInfo.getCountry().equals("Pakistan")?"selected":""%> value="Pakistan">�ͻ�˹̹</option>
<option <%=beanUserInfo.getCountry().equals("Panama")?"selected":""%> value="Panama">������</option>
<option <%=beanUserInfo.getCountry().equals("Paraguay")?"selected":""%> value="Paraguay">������</option>
<option <%=beanUserInfo.getCountry().equals("Peru")?"selected":""%> value="Peru">��³</option>
<option <%=beanUserInfo.getCountry().equals("Poland")?"selected":""%> value="Poland">����</option>
<option <%=beanUserInfo.getCountry().equals("Portugal")?"selected":""%> value="Portugal">������</option>
<option <%=beanUserInfo.getCountry().equals("Romania")?"selected":""%> value="Romania">��������</option>
<option <%=beanUserInfo.getCountry().equals("Russia")?"selected":""%> value="Russia">����˹</option>
<option <%=beanUserInfo.getCountry().equals("Saudi_Arabia")?"selected":""%> value="Saudi_Arabia">ɳ�ذ�����</option>
<option <%=beanUserInfo.getCountry().equals("Singapore")?"selected":""%> value="Singapore">�¼���</option>
<option <%=beanUserInfo.getCountry().equals("Slovakia")?"selected":""%> value="Slovakia">˹�工��</option>
<option <%=beanUserInfo.getCountry().equals("Slovenia")?"selected":""%> value="Slovenia">˹��������</option>
<option <%=beanUserInfo.getCountry().equals("Solomon_Islands")?"selected":""%> value="Solomon_Islands">������</option>
<option <%=beanUserInfo.getCountry().equals("Somalia")?"selected":""%> value="Somalia">������</option>
<option <%=beanUserInfo.getCountry().equals("South_Africa")?"selected":""%> value="South_Africa">�Ϸ�</option>
<option <%=beanUserInfo.getCountry().equals("South_Korea")?"selected":""%> value="South_Korea">����</option>
<option <%=beanUserInfo.getCountry().equals("Spain")?"selected":""%> value="Spain">������</option>
<option <%=beanUserInfo.getCountry().equals("Sri_Lanka")?"selected":""%> value="Sri_Lanka">ӡ��</option>
<option <%=beanUserInfo.getCountry().equals("Surinam")?"selected":""%> value="Surinam">������</option>
<option <%=beanUserInfo.getCountry().equals("Sweden")?"selected":""%> value="Sweden">���</option>
<option <%=beanUserInfo.getCountry().equals("Switzerland")?"selected":""%> value="Switzerland">��ʿ</option>
<option <%=beanUserInfo.getCountry().equals("Thailand")?"selected":""%> value="Thailand">̩��</option>
<option <%=beanUserInfo.getCountry().equals("Trinidad_Tobago")?"selected":""%> value="Trinidad_Tobago">��͸�</option>
<option <%=beanUserInfo.getCountry().equals("Turkey")?"selected":""%> value="Turkey">������</option>
<option <%=beanUserInfo.getCountry().equals("Ukraine")?"selected":""%> value="Ukraine">�ڿ���</option>
<option <%=beanUserInfo.getCountry().equals("United_Arab_Emirates")?"selected":""%> value="United_Arab_Emirates">����������������</option>
<option <%=beanUserInfo.getCountry().equals("United_States")?"selected":""%> value="United_States">����</option>
<option <%=beanUserInfo.getCountry().equals("Uruguay")?"selected":""%> value="Uruguay">������</option>
<option <%=beanUserInfo.getCountry().equals("Venezuela")?"selected":""%> value="Venezuela">ί������</option>
<option <%=beanUserInfo.getCountry().equals("Yugoslavia")?"selected":""%> value="Yugoslavia">��˹����</option>
<option <%=beanUserInfo.getCountry().equals("Zambia")?"selected":""%> value="Zambia">�ޱ���</option>
<option <%=beanUserInfo.getCountry().equals("Zimbabwe")?"selected":""%> value="Zimbabwe">��Ͳ�Τ</option>
</select>
<img src="<%=request.getContextPath()%>/images/flags/<%=beanUserInfo.getCountry().trim().equals("")?"blank":beanUserInfo.getCountry().trim()%>.gif" name="userflags" border=0 height=14 width=21>
</td></tr><tr>
<td bgcolor=#d7d7d7><font color=#000077><b>���ԣ�</b><br>�����������ڹ��ҵľ���ط��������ѡ</td>
<td bgcolor=#d7d7d7>
ʡ��/���� <input type=text name="pravency" maxlength=12 size=10 style="font-weight: bold" value="<%=beanUserInfo.getPravency()%>">�����ܳ���10���ַ���5�����֣�
</td>
</tr>
<tr>
<td bgcolor=#d7d7d7><font color=#000077><b>���Ҽ�飺 </b><BR>���ܳ��� <B>5</B> �У�Ҳ���ܳ��� <B>100</B> ���ַ�<br><br>�������ڴ��������ĸ��˼�顣�����ѡ</td>
<td bgcolor=#d7d7d7><textarea name="resume" cols="60" rows="5"><%=beanUserInfo.getResume()%></textarea></td>
</tr><tr>
<td bgcolor=#d7d7d7><font color=#000077><b>ǩ����</b><br>���ܳ��� <B>5</B> �У�Ҳ���ܳ��� <B>300</B> ���ַ�
<br><br>����ʹ�� HTML ��ǩ<br>����ʹ�� <a href="javascript:openScript('misc.cgi?action=lbcode',300,350)">LB5000 ��ǩ</a><BR>
<li>��ͼ��ǩ��: <b>����</b><li>Flash ��ǩ: <b>��ֹ</b><li>���ֱ�ǩ��: <b>��ֹ</b><li>���ִ�С��: <b>��ֹ</b>
</td>
<td bgcolor=#d7d7d7><textarea name="signature" cols="60" rows="8"><%=beanUserInfo.getSignature()%></textarea></td>
</tr>
<script language="javascript">
function showimage(){document.images.useravatars.src="<%=request.getContextPath()%>/images/face/"+document.creator.useravatar.options[document.creator.useravatar.selectedIndex].value+".gif";}
</script>
<tr><td bgcolor=#d7d7d7 valign=top><font color=#000077><b>����ͼƬ��</b><br>������ѡ��һ������ͼƬ�����㷢��ʱ����ʾ�����������·���<BR>�������д��������Զ���ͷ�񲿷֣���ô���ͷ�����Զ����Ϊ׼���������������Զ���ͷ���������Ŀ��<BR>
<br><br><b>�����Զ���ͷ��</b>��<br>��Ҳ����������������Զ���ͷ��� URL ��ַ��ͷ��ĸ߶ȺͿ��(����)�� �������Ҫ�Զ���ͷ���뽫��Ӧ��Ŀ��Ŀȫ�����գ�<BR><BR>
<br><b>����㲻��Ҫ�κε�ͷ����ô�������ڲ˵���ѡ����Ҫͷ�񡱣�Ȼ�����������Զ���ͷ��Ĳ��֣�</b><BR><br>
<td bgcolor=#d7d7d7 valign=top>��ͷ������� 373 ����
<select name="useravatar" size=1 onChange="showimage()" value="<%=beanUserInfo.getUserimg()%>">
<option value="">��Ҫͷ��</option>
<%
String tmp,tmp1;
for(int i = 1; i < 374; i++) {
	tmp="000"+Integer.toString(i);
	tmp=tmp.substring(tmp.length()-3,tmp.length());
	tmp1=beanUserInfo.getUserimg().equals(tmp)?"selected":"";
	out.println("<option "+tmp1+" value=\""+tmp+"\" >"+tmp+"</option>");
}%>
</select>
<img src=<%=request.getContextPath()%>/images/face/<%=beanUserInfo.getUserimg().trim().equals("")?"blank":beanUserInfo.getUserimg().trim()%>.gif name="useravatars" width=32 height=32 hspace=15><br><br><br>
<br>ͼ��λ�ã� <input type=text name="personalavatar" size=50 maxlength=100 value="<%=beanUserInfo.getSelfimg()%>"><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���������� URL ·����<br>
<br>ͼ���ȣ� <input type=text name="personalwidth" size=2 maxlength=3 value=<%=beanUserInfo.getSelfwidth()%>>�������� 20 -- 100 ֮���һ��������<br>
<br>ͼ��߶ȣ� <input type=text name="personalheight" size=2 maxlength=3 value=<%=beanUserInfo.getSelfheight()%>>�������� 20 -- 100 ֮���һ��������<br></td>
</td></tr>
</table></td></tr>
<script>
function showadv(){
if (document.creator.advshow.checked == true) {
adv.style.display = "";
advance.innerText="�رո߼��û�����ѡ��"
}else{
adv.style.display = "none";
advance.innerText="��ʾ�߼��û�����ѡ��"
}
}
</script>
</tr></table><img src="" width=0 height=4><BR>
<table cellpadding=0 cellspacing=0 border=0 width=770 align=center>
<tr><td width=17 background=<%=request.getContextPath()%>/images/kleft.gif bgcolor=#d7d7d7 height=30></td>

<td width=50%><INPUT id=advcheck name=advshow type=checkbox value=1 onclick=showadv()><span id="advance">��ʾ�߼�ע��ѡ��</a></span> 
</td>

<td width=50%>
<input type=submit value="�� ��" onclick="return valid()"></td>
<td width=17 background=<%=request.getContextPath()%>/images/kright.gif bgcolor=#d7d7d7></td></form></tr></table>
	<TABLE cellSpacing=0 cellPadding=0 width=770 height=17 align=center border=0><tr><td width=17><img src=<%=request.getContextPath()%>/images/kbotleft.gif width=17 border=0></td><td background=<%=request.getContextPath()%>/images/kbot.gif>&nbsp;</td><td width=17><img src=<%=request.getContextPath()%>/images/kbotright.gif width=17 border=0></td></tr></table>


<center><hr width=380 size=1></center>


</body>
</html>