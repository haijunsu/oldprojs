<%@page contentType="text/html;charset=GB2312"%>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>

<html>
<head> 
<title>环天E-learning网络培训平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javaScript" type="text/javascript" SRC="board.js"></SCRIPT>
<!--end Java-->
<Script language="javascript">
function valid(){
	if (document.creator.userid.value.length==0){
		alert("用户名不能为空！！");
		document.creator.userid.focus();
		return false;
	}
	if (document.creator.pass.value.length==0){
		alert("用户密码不能为空！！");
		document.creator.pass.focus();
		return false;
	}
	if (document.creator.pass.value!=document.creator.repass.value){
		alert("您输入的两次密码不一致！！");
		document.creator.pass.focus();
		return false;
	}

	if (document.creator.email.value.length==0){
		alert("您没有输入邮件地址！！");
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
      <a href="leoboard.cgi">环天E-learning网络培训平台</a> 
      <img src=<%=request.getContextPath()%>/images/openfold.gif width="12" height="12">&nbsp修改注册信息</td>
    <td valign=bottom align=right>
    　<a href="javascript:openScript('help.cgi?helpon=用户注册',500,400)">
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
            <b><font color=#000077>请填写您的注册信息：</b></td>
          <td width=17><img src=<%=request.getContextPath()%>/images/ktopright.gif width=17></td>
        </tr><tr>
          <td width=17 background=<%=request.getContextPath()%>/images/kleft.gif bgcolor=#d7d7d7></td>
<td bgcolor=#d7d7d7 width=40% height=50><font color=#000077><b>用户名：</b><br>
            注册用户名不能超过20个字符（10个汉字）</td>
	<td bgcolor=#d7d7d7 width=60%>
	<input type=text maxlength="20" name="userid" value="<%=beanUserInfo.getUserid()%>" disabled>&nbsp;<font color=red>* 此项不能更改</td>

<td width=17 background=<%=request.getContextPath()%>/images/kright.gif bgcolor=#d7d7d7></td>
	<tr>
          <td width=17><img src=<%=request.getContextPath()%>/images/ksplitleft.gif border=0 width=17></td>
          <td background=<%=request.getContextPath()%>/images/ksplitmiddle.gif height=2 width=100% colspan=2><img src=<%=request.getContextPath()%>/images/none.gif height=2 border=0></td>
          <td><img src=<%=request.getContextPath()%>/images/ksplitright.gif border=0 width=17></td>
        </tr>
         <tr>
          <td width=17 background=<%=request.getContextPath()%>/images/kpartleft.gif bgcolor=#e7e7e7></td>
        <td bgcolor=#e7e7e7 width=40% height=60 rowspan=2><font color=#000077><b>密码： (最多20位)</b><br>请输入密码，区分大小写<br>只能使用大小写字母和数字的组合</td>
        <td bgcolor=#e7e7e7 width=60%><input type=password name="pass" maxlength=20 value="">&nbsp;* 此项必须填写</td><td width=17 background=<%=request.getContextPath()%>/images/kpartright.gif bgcolor=#e7e7e7></td>
        </tr>
        	<tr><td width=17 background=<%=request.getContextPath()%>/images/kpartleft.gif bgcolor=#e7e7e7></td>
        <td bgcolor=#e7e7e7><input type=password name="repass" maxlength=20 value="">&nbsp;* 再输一遍，以便确定！</td><td width=17 background=<%=request.getContextPath()%>/images/kpartright.gif bgcolor=#e7e7e7></td>
        </tr>
		<tr>
          <td width=17><img src=<%=request.getContextPath()%>/images/ksplitleft.gif width=17 height="2" border=0></td>
          <td background=<%=request.getContextPath()%>/images/ksplitmiddle.gif height=2 width=100% colspan=2><img src=<%=request.getContextPath()%>/images/none.gif height=2 border=0></td>
          <td><img src=<%=request.getContextPath()%>/images/ksplitright.gif border=0 width=17></td>
        </tr>
		<tr>
<td width=17 background=<%=request.getContextPath()%>/images/kleft.gif bgcolor=#d7d7d7></td>
<td bgcolor=#d7d7d7 height=60><font color=#000077><b>邮件地址：</b><br>请输入有效的邮件地址，这将使您能用到论坛中的所有功能</td>
<td bgcolor=#d7d7d7><input type=text name="email" value="">&nbsp;* 此项必须填写</td><td width=17 background=<%=request.getContextPath()%>/images/kright.gif bgcolor=#d7d7d7></td></tr>
		<tr>
          <td width=17><img src=<%=request.getContextPath()%>/images/ksplitleft.gif width=17 border=0></td>
          <td background=<%=request.getContextPath()%>/images/ksplitmiddle.gif height=2 width=100% colspan=2><img src=<%=request.getContextPath()%>/images/none.gif border=0></td>
          <td><img src=<%=request.getContextPath()%>/images/ksplitright.gif border=0 width=17></td>
        </tr>
        <tr><td width=17 background=<%=request.getContextPath()%>/images/kpartleft.gif bgcolor=#e7e7e7></td>
<td bgcolor=#e7e7e7 height=40><font color=#000077><b>公司名称：</b>建议您填写<br>最大 50 个字节（25个汉字）</td> 
<td bgcolor=#e7e7e7><input type=text name="company" size=50 maxlength=50 value=""></td>
<td width=17 background=<%=request.getContextPath()%>/images/kpartright.gif bgcolor=#e7e7e7></td></tr>
<tr><td width=17 background=<%=request.getContextPath()%>/images/kpartleft.gif bgcolor=#e7e7e7></td>
<td bgcolor=#e7e7e7 height=40><font color=#000077><b>所在部门：</b>配合上栏使用<br>最大 20 个字节（10个汉字）</td> 
<td bgcolor=#e7e7e7><input type=text name="department" value="" size=20 maxlength=20 value=""></td><td width=17 background=<%=request.getContextPath()%>/images/kpartright.gif bgcolor=#e7e7e7></td></tr>
		<tr>
          <td width=17><img src=<%=request.getContextPath()%>/images/ksplitleft.gif width=17 height="2" border=0></td>
          <td background=<%=request.getContextPath()%>/images/ksplitmiddle.gif height=2 width=100% colspan=2><img src=<%=request.getContextPath()%>/images/none.gif height=2 border=0></td>
          <td><img src=<%=request.getContextPath()%>/images/ksplitright.gif border=0 width=17></td>
        </tr>
        <tr><td width=17 background=<%=request.getContextPath()%>/images/kleft.gif bgcolor=#d7d7d7></td><td bgcolor=#d7d7d7 valign=middle colspan=2 align=center height=24><font color=#880052><b>以上信息是您登录系统的重要信息，请谨慎输入！</b></font></td><td width=17 background=<%=request.getContextPath()%>/images/kright.gif bgcolor=#d7d7d7></td></tr>
			<tr>
          <td width=17><img src=<%=request.getContextPath()%>/images/ksplitleft.gif width=17 height="2" border=0></td>
          <td background=<%=request.getContextPath()%>/images/ksplitmiddle.gif height=2 width=100% colspan=2><img src=<%=request.getContextPath()%>/images/none.gif height=2 border=0></td>
          <td><img src=<%=request.getContextPath()%>/images/ksplitright.gif border=0 width=17></td>
        </tr></table></td></tr></table>
<table cellpadding=0 cellspacing=0 border=0 width=770 bgcolor=#ffffff align=center id=adv style="DISPLAY: none"><tr><td>
<table cellpadding=4 cellspacing=1 border=0 width=100%>
<tr>
<td bgcolor=#d7d7d7><font color=#000077><b>真实姓名：</b><br>请输入你的真实姓名,以便管理员为您分配权限。<br>此项可选</td>
<td bgcolor=#d7d7d7><input type=text name="username" value="<%=beanUserInfo.getUserName()%>"></td>
</tr>
<tr>
<td bgcolor=#d7d7d7 width=40%><font color=#000077><b>显示邮件地址</b><br>您是否希望在您发表文章之后显示您的邮件？</td>
<td bgcolor=#d7d7d7 width=60%><font color=#000077>
	<input name="contract" type="radio" value="yes" <%=beanUserInfo.getContract().equals("yes")?"checked":""%>> 是　　
	<input name="contract" type="radio" value="msn" <%=beanUserInfo.getContract().equals("msn")?"checked":""%>> MSN　　
	<input name="contract" type="radio" value="no" <%=beanUserInfo.getContract().equals("no")?"checked":""%>> 否</font>
</td>
</tr><tr>
<td bgcolor=#d7d7d7><font color=#000077><b>性别</b></td><td bgcolor=#d7d7d7>
<select name="sex" size="1">
<option value="n" <%=beanUserInfo.getSex().equals("n")?"selected":""%>>保密 </option>
<option value="m" <%=beanUserInfo.getSex().equals("m")?"selected":""%>>帅哥 </option>
<option value="f" <%=beanUserInfo.getSex().equals("f")?"selected":""%>>美女 </option>
</select>
</td></tr>
<tr><td bgcolor=#d7d7d7><font color=#000077><b>生日：</b>如不想填写，请全部留空。此项可选</td>
<td bgcolor=#d7d7d7><input type="text" name="year" size=4 maxlength=4 value="<%=beanUserInfo.getYear()%>">年 
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
  </select>月
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
  </select>日
</td>
</tr>
<tr><SCRIPT language=javascript>
function showsx(){document.images.shengxiaos.src="<%=request.getContextPath()%>/images/sx/"+document.creator.shengxiao.options[document.creator.shengxiao.selectedIndex].value+".gif";}
</SCRIPT>
<td bgcolor=#d7d7d7 vAlign=top><font color=#000077><b>所属生肖：</b><br>请选择你所属的生肖。<br>如果你正确输入了生日的话，那么此项无效！</td>
<td bgcolor=#d7d7d7>
<SELECT name="shengxiao" onchange=showsx() size="1">
 <OPTION value="">保密</OPTION>
<OPTION value="sx1" <%=beanUserInfo.getShengxiao().trim().equals("sx1" )?"selected":""%>>子鼠</OPTION>
<OPTION value="sx2" <%=beanUserInfo.getShengxiao().equals("sx2" )?"selected":""%>>丑牛</OPTION>
<OPTION value="sx3" <%=beanUserInfo.getShengxiao().equals("sx3" )?"selected":""%>>寅虎</OPTION>
<OPTION value="sx4" <%=beanUserInfo.getShengxiao().equals("sx4" )?"selected":""%>>卯兔</OPTION>
<OPTION value="sx5" <%=beanUserInfo.getShengxiao().equals("sx5" )?"selected":""%>>辰龙</OPTION>
<OPTION value="sx6" <%=beanUserInfo.getShengxiao().equals("sx6" )?"selected":""%>>巳蛇</OPTION>
<OPTION value="sx7" <%=beanUserInfo.getShengxiao().equals("sx7" )?"selected":""%>>午马</OPTION>
<OPTION value="sx8" <%=beanUserInfo.getShengxiao().equals("sx8" )?"selected":""%>>未羊</OPTION>
<OPTION value="sx9" <%=beanUserInfo.getShengxiao().equals("sx9" )?"selected":""%>>申猴</OPTION>
<OPTION value="sx10" <%=beanUserInfo.getShengxiao().equals("sx10")?"selected":""%>>酉鸡</OPTION>
<OPTION value="sx11" <%=beanUserInfo.getShengxiao().equals("sx11")?"selected":""%>>戌狗</OPTION>
<OPTION value="sx12" <%=beanUserInfo.getShengxiao().equals("sx12")?"selected":""%>>亥猪</OPTION>
</SELECT>
 <IMG border=0 name=shengxiaos src="<%=request.getContextPath()%>/images/sx/<%=beanUserInfo.getShengxiao().trim().equals("")?"blank":beanUserInfo.getShengxiao().trim()%>.gif" align="absmiddle">
</TD></tr><tr>
<SCRIPT language=javascript>
function showxz(){document.images.userxzs.src="http:<%=request.getContextPath()%>/images/star/"+document.creator.userxz.options[document.creator.userxz.selectedIndex].value+".gif";}
</SCRIPT>
<td bgcolor=#d7d7d7 vAlign=top><font color=#000077><b>所属星座：</b><br>请选择你所属的星座。<br>如果你正确输入了生日的话，那么此项无效！</td>
<td bgcolor=#d7d7d7><SELECT name="userxz" onchange=showxz() size="1">
 <OPTION value="">保密</OPTION>
<OPTION value="z1" <%=beanUserInfo.getStars().equals("z1")?"selected":""%>>白羊座(3月21--4月19日)</OPTION> 
<OPTION value="z2" <%=beanUserInfo.getStars().equals("z2")?"selected":""%>>金牛座(4月20--5月20日)</OPTION> 
<OPTION value="z3" <%=beanUserInfo.getStars().equals("z3")?"selected":""%>>双子座(5月21--6月21日)</OPTION> 
<OPTION value="z4" <%=beanUserInfo.getStars().equals("z4")?"selected":""%>>巨蟹座(6月22--7月22日)</OPTION> 
<OPTION value="z5" <%=beanUserInfo.getStars().equals("z5")?"selected":""%>>狮子座(7月23--8月22日)</OPTION> 
<OPTION value="z6" <%=beanUserInfo.getStars().equals("z6")?"selected":""%>>处女座(8月23--9月22日)</OPTION> 
<OPTION value="z7" <%=beanUserInfo.getStars().equals("z7")?"selected":""%>>天秤座(9月23--10月23日)</OPTION> 
<OPTION value="z8" <%=beanUserInfo.getStars().equals("z8")?"selected":""%>>天蝎座(10月24--11月21日)</OPTION> 
<OPTION value="z9" <%=beanUserInfo.getStars().equals("z9")?"selected":""%>>射手座(11月22--12月21日)</OPTION> 
<OPTION value="z10" <%=beanUserInfo.getStars().equals("z10")?"selected":""%>>魔羯座(12月22--1月19日)</OPTION>
<OPTION value="z11" <%=beanUserInfo.getStars().equals("z11")?"selected":""%>>水瓶座(1月20--2月18日)</OPTION> 
<OPTION value="z12" <%=beanUserInfo.getStars().equals("z12")?"selected":""%>>双鱼座(2月19--3月20日)</OPTION>
</SELECT>
 <IMG border=0 name=userxzs src="<%=request.getContextPath()%>/images/star/<%=beanUserInfo.getStars().trim().equals("")?"blank":beanUserInfo.getStars().trim()%>.gif" width=15 height=15 align="absmiddle">
</TD>
</TR><tr>
<td bgcolor=#d7d7d7><font color=#000077><b>主页地址：</b><br>如果您有主页，请输入主页地址。此项可选</td>
<td bgcolor=#d7d7d7><input type=text name="webaddr" value="<%=beanUserInfo.getWebaddr()%>"></td>
</tr><tr>
<td bgcolor=#d7d7d7><font color=#000077><b>OICQ 号：</b><br>如果您有 OICQ，请输入号码。此项可选</td>
<td bgcolor=#d7d7d7><input type=text name="oicq" value="<%=beanUserInfo.getOicq()%>"></td>
</tr><tr>
<td bgcolor=#d7d7d7><font color=#000077><b>MSN 号：</b><br>如果您有 ICQ，请输入号码。此项可选</td>
<td bgcolor=#d7d7d7><input type=text name="msn" value="<%=beanUserInfo.getMsn()%>"></td>
</tr><script language="javascript">
function showflag(){document.images.userflags.src="<%=request.getContextPath()%>/images/flags/"+document.creator.userflag.options[document.creator.userflag.selectedIndex].value+".gif";}
</script>
<tr><td bgcolor=#d7d7d7 valign=top><font face=宋体 color=#000077><b>所在国家:</b><br>请选择你所在的国家。</td>
<td bgcolor=#d7d7d7>
<select name="userflag" size=1 onChange="showflag()">
<option value="blank" selected>保密</option>
<option <%=beanUserInfo.getCountry().equals("China")?"selected":""%> value="China">中国</option>
<option <%=beanUserInfo.getCountry().equals("Angola")?"selected":""%> value="Angola">安哥拉</option>
<option <%=beanUserInfo.getCountry().equals("Antigua")?"selected":""%> value="Antigua">安提瓜</option>
<option <%=beanUserInfo.getCountry().equals("Argentina")?"selected":""%> value="Argentina">阿根廷</option>
<option <%=beanUserInfo.getCountry().equals("Armenia")?"selected":""%> value="Antigua">亚美尼亚</option>
<option <%=beanUserInfo.getCountry().equals("Australia")?"selected":""%> value="Australia">澳大利亚</option>
<option <%=beanUserInfo.getCountry().equals("Austria")?"selected":""%> value="Austria">奥地利</option>
<option <%=beanUserInfo.getCountry().equals("Bahamas")?"selected":""%> value="Bahamas">巴哈马</option>
<option <%=beanUserInfo.getCountry().equals("Bahrain")?"selected":""%> value="Bahrain">巴林</option>
<option <%=beanUserInfo.getCountry().equals("Bangladesh")?"selected":""%> value="Bangladesh">孟加拉</option>
<option <%=beanUserInfo.getCountry().equals("Barbados")?"selected":""%> value="Barbados">巴巴多斯</option>
<option <%=beanUserInfo.getCountry().equals("Belgium")?"selected":""%> value="Belgium">比利时</option>
<option <%=beanUserInfo.getCountry().equals("Bermuda")?"selected":""%> value="Bermuda">百慕大</option>
<option <%=beanUserInfo.getCountry().equals("Bolivia")?"selected":""%> value="Bolivia">玻利维亚</option>
<option <%=beanUserInfo.getCountry().equals("Brazil")?"selected":""%> value="Brazil">巴西</option>
<option <%=beanUserInfo.getCountry().equals("Brunei")?"selected":""%> value="Brunei">文莱</option>
<option <%=beanUserInfo.getCountry().equals("Canada")?"selected":""%> value="Canada">加拿大</option>
<option <%=beanUserInfo.getCountry().equals("Chile")?"selected":""%> value="Chile">智利</option>
<option <%=beanUserInfo.getCountry().equals("Colombia")?"selected":""%> value="Colombia">哥伦比亚</option>
<option <%=beanUserInfo.getCountry().equals("Croatia")?"selected":""%> value="Croatia">克罗地亚</option>
<option <%=beanUserInfo.getCountry().equals("Cuba")?"selected":""%> value="Cuba">古巴</option>
<option <%=beanUserInfo.getCountry().equals("Cyprus")?"selected":""%> value="Cyprus">塞浦路斯</option>
<option <%=beanUserInfo.getCountry().equals("Czech_Republic")?"selected":""%> value="Czech_Republic">捷克</option>
<option <%=beanUserInfo.getCountry().equals("Denmark")?"selected":""%> value="Denmark">丹麦</option>
<option <%=beanUserInfo.getCountry().equals("Dominican_Republic")?"selected":""%> value="Dominican_Republic">多米尼加</option>
<option <%=beanUserInfo.getCountry().equals("Ecuador")?"selected":""%> value="Ecuador">厄瓜多尔</option>
<option <%=beanUserInfo.getCountry().equals("Egypt")?"selected":""%> value="Egypt">埃及</option>
<option <%=beanUserInfo.getCountry().equals("Estonia")?"selected":""%> value="Estonia">爱沙尼亚</option>
<option <%=beanUserInfo.getCountry().equals("Finland")?"selected":""%> value="Finland">芬兰</option>
<option <%=beanUserInfo.getCountry().equals("France")?"selected":""%> value="France">法国</option>
<option <%=beanUserInfo.getCountry().equals("Germany")?"selected":""%> value="Germany">德国</option>
<option <%=beanUserInfo.getCountry().equals("Great_Britain")?"selected":""%> value="Great_Britain">英国</option>
<option <%=beanUserInfo.getCountry().equals("Greece")?"selected":""%> value="Greece">希腊</option>
<option <%=beanUserInfo.getCountry().equals("Guatemala")?"selected":""%> value="Guatemala">危地马拉</option>
<option <%=beanUserInfo.getCountry().equals("Honduras")?"selected":""%> value="Honduras">洪都拉斯</option>
<option <%=beanUserInfo.getCountry().equals("Hungary")?"selected":""%> value="Hungary">匈牙利</option>
<option <%=beanUserInfo.getCountry().equals("Iceland")?"selected":""%> value="Iceland">冰岛</option>
<option <%=beanUserInfo.getCountry().equals("India")?"selected":""%> value="India">印度</option>
<option <%=beanUserInfo.getCountry().equals("Indonesia")?"selected":""%> value="Indonesia">印度尼西亚</option>
<option <%=beanUserInfo.getCountry().equals("Iran")?"selected":""%> value="Iran">伊朗</option>
<option <%=beanUserInfo.getCountry().equals("Iraq")?"selected":""%> value="Iraq">伊拉克</option>
<option <%=beanUserInfo.getCountry().equals("Ireland")?"selected":""%> value="Ireland">爱尔兰</option>
<option <%=beanUserInfo.getCountry().equals("Israel")?"selected":""%> value="Israel">以色列</option>
<option <%=beanUserInfo.getCountry().equals("Italy")?"selected":""%> value="Italy">意大利</option>
<option <%=beanUserInfo.getCountry().equals("Jamaica")?"selected":""%> value="Jamaica">牙买加</option>
<option <%=beanUserInfo.getCountry().equals("Japan")?"selected":""%> value="Japan">日本</option>
<option <%=beanUserInfo.getCountry().equals("Jordan")?"selected":""%> value="Jordan">约旦</option>
<option <%=beanUserInfo.getCountry().equals("Kazakstan")?"selected":""%> value="Kazakstan">哈萨克</option>
<option <%=beanUserInfo.getCountry().equals("Kenya")?"selected":""%> value="Kenya">肯尼亚</option>
<option <%=beanUserInfo.getCountry().equals("Kuwait")?"selected":""%> value="Kuwait">科威特</option>
<option <%=beanUserInfo.getCountry().equals("Latvia")?"selected":""%> value="Latvia">拉脱维亚</option>
<option <%=beanUserInfo.getCountry().equals("Lebanon")?"selected":""%> value="Lebanon">黎巴嫩</option>
<option <%=beanUserInfo.getCountry().equals("Lithuania")?"selected":""%> value="Lithuania">立陶宛</option>
<option <%=beanUserInfo.getCountry().equals("Malaysia")?"selected":""%> value="Malaysia">马来西亚</option>
<option <%=beanUserInfo.getCountry().equals("Malawi")?"selected":""%> value="Malawi">马拉维</option>
<option <%=beanUserInfo.getCountry().equals("Malta")?"selected":""%> value="Malta">马耳他</option>
<option <%=beanUserInfo.getCountry().equals("Mauritius")?"selected":""%> value="Mauritius">毛里求斯</option>
<option <%=beanUserInfo.getCountry().equals("Morocco")?"selected":""%> value="Morocco">摩洛哥</option>
<option <%=beanUserInfo.getCountry().equals("Mozambique")?"selected":""%> value="Mozambique">莫桑比克</option>
<option <%=beanUserInfo.getCountry().equals("Netherlands")?"selected":""%> value="Netherlands">荷兰</option>
<option <%=beanUserInfo.getCountry().equals("New_Zealand")?"selected":""%> value="New_Zealand">新西兰</option>
<option <%=beanUserInfo.getCountry().equals("Nicaragua")?"selected":""%> value="Nicaragua">尼加拉瓜</option>
<option <%=beanUserInfo.getCountry().equals("Nigeria")?"selected":""%> value="Nigeria">尼日利亚</option>
<option <%=beanUserInfo.getCountry().equals("Norway")?"selected":""%> value="Norway">挪威</option>
<option <%=beanUserInfo.getCountry().equals("Pakistan")?"selected":""%> value="Pakistan">巴基斯坦</option>
<option <%=beanUserInfo.getCountry().equals("Panama")?"selected":""%> value="Panama">巴拿马</option>
<option <%=beanUserInfo.getCountry().equals("Paraguay")?"selected":""%> value="Paraguay">巴拉圭</option>
<option <%=beanUserInfo.getCountry().equals("Peru")?"selected":""%> value="Peru">秘鲁</option>
<option <%=beanUserInfo.getCountry().equals("Poland")?"selected":""%> value="Poland">波兰</option>
<option <%=beanUserInfo.getCountry().equals("Portugal")?"selected":""%> value="Portugal">葡萄牙</option>
<option <%=beanUserInfo.getCountry().equals("Romania")?"selected":""%> value="Romania">罗马尼亚</option>
<option <%=beanUserInfo.getCountry().equals("Russia")?"selected":""%> value="Russia">俄罗斯</option>
<option <%=beanUserInfo.getCountry().equals("Saudi_Arabia")?"selected":""%> value="Saudi_Arabia">沙特阿拉伯</option>
<option <%=beanUserInfo.getCountry().equals("Singapore")?"selected":""%> value="Singapore">新加坡</option>
<option <%=beanUserInfo.getCountry().equals("Slovakia")?"selected":""%> value="Slovakia">斯洛伐克</option>
<option <%=beanUserInfo.getCountry().equals("Slovenia")?"selected":""%> value="Slovenia">斯洛文尼亚</option>
<option <%=beanUserInfo.getCountry().equals("Solomon_Islands")?"selected":""%> value="Solomon_Islands">所罗门</option>
<option <%=beanUserInfo.getCountry().equals("Somalia")?"selected":""%> value="Somalia">索马里</option>
<option <%=beanUserInfo.getCountry().equals("South_Africa")?"selected":""%> value="South_Africa">南非</option>
<option <%=beanUserInfo.getCountry().equals("South_Korea")?"selected":""%> value="South_Korea">韩国</option>
<option <%=beanUserInfo.getCountry().equals("Spain")?"selected":""%> value="Spain">西班牙</option>
<option <%=beanUserInfo.getCountry().equals("Sri_Lanka")?"selected":""%> value="Sri_Lanka">印度</option>
<option <%=beanUserInfo.getCountry().equals("Surinam")?"selected":""%> value="Surinam">苏里南</option>
<option <%=beanUserInfo.getCountry().equals("Sweden")?"selected":""%> value="Sweden">瑞典</option>
<option <%=beanUserInfo.getCountry().equals("Switzerland")?"selected":""%> value="Switzerland">瑞士</option>
<option <%=beanUserInfo.getCountry().equals("Thailand")?"selected":""%> value="Thailand">泰国</option>
<option <%=beanUserInfo.getCountry().equals("Trinidad_Tobago")?"selected":""%> value="Trinidad_Tobago">多巴哥</option>
<option <%=beanUserInfo.getCountry().equals("Turkey")?"selected":""%> value="Turkey">土耳其</option>
<option <%=beanUserInfo.getCountry().equals("Ukraine")?"selected":""%> value="Ukraine">乌克兰</option>
<option <%=beanUserInfo.getCountry().equals("United_Arab_Emirates")?"selected":""%> value="United_Arab_Emirates">阿拉伯联合酋长国</option>
<option <%=beanUserInfo.getCountry().equals("United_States")?"selected":""%> value="United_States">美国</option>
<option <%=beanUserInfo.getCountry().equals("Uruguay")?"selected":""%> value="Uruguay">乌拉圭</option>
<option <%=beanUserInfo.getCountry().equals("Venezuela")?"selected":""%> value="Venezuela">委内瑞拉</option>
<option <%=beanUserInfo.getCountry().equals("Yugoslavia")?"selected":""%> value="Yugoslavia">南斯拉夫</option>
<option <%=beanUserInfo.getCountry().equals("Zambia")?"selected":""%> value="Zambia">赞比亚</option>
<option <%=beanUserInfo.getCountry().equals("Zimbabwe")?"selected":""%> value="Zimbabwe">津巴布韦</option>
</select>
<img src="<%=request.getContextPath()%>/images/flags/<%=beanUserInfo.getCountry().trim().equals("")?"blank":beanUserInfo.getCountry().trim()%>.gif" name="userflags" border=0 height=14 width=21>
</td></tr><tr>
<td bgcolor=#d7d7d7><font color=#000077><b>来自：</b><br>请输入您所在国家的具体地方。此项可选</td>
<td bgcolor=#d7d7d7>
省份/城市 <input type=text name="pravency" maxlength=12 size=10 style="font-weight: bold" value="<%=beanUserInfo.getPravency()%>">　不能超过10个字符（5个汉字）
</td>
</tr>
<tr>
<td bgcolor=#d7d7d7><font color=#000077><b>自我简介： </b><BR>不能超过 <B>5</B> 行，也不能超过 <B>100</B> 个字符<br><br>您可以在此输入您的个人简介。此项可选</td>
<td bgcolor=#d7d7d7><textarea name="resume" cols="60" rows="5"><%=beanUserInfo.getResume()%></textarea></td>
</tr><tr>
<td bgcolor=#d7d7d7><font color=#000077><b>签名：</b><br>不能超过 <B>5</B> 行，也不能超过 <B>300</B> 个字符
<br><br>不能使用 HTML 标签<br>可以使用 <a href="javascript:openScript('misc.cgi?action=lbcode',300,350)">LB5000 标签</a><BR>
<li>贴图标签　: <b>允许</b><li>Flash 标签: <b>禁止</b><li>音乐标签　: <b>禁止</b><li>文字大小　: <b>禁止</b>
</td>
<td bgcolor=#d7d7d7><textarea name="signature" cols="60" rows="8"><%=beanUserInfo.getSignature()%></textarea></td>
</tr>
<script language="javascript">
function showimage(){document.images.useravatars.src="<%=request.getContextPath()%>/images/face/"+document.creator.useravatar.options[document.creator.useravatar.selectedIndex].value+".gif";}
</script>
<tr><td bgcolor=#d7d7d7 valign=top><font color=#000077><b>个性图片：</b><br>您可以选择一个个性图片，当你发表时将显示在您的名字下方。<BR>如果你填写了下面的自定义头像部分，那么你的头像以自定义的为准。否则，请你留空自定义头像的所有栏目！<BR>
<br><br><b>关于自定义头像</b>：<br>你也可以在这里给出你自定义头像的 URL 地址，头像的高度和宽度(像素)。 如果不想要自定义头像，请将相应栏目栏目全部留空！<BR><BR>
<br><b>如果你不想要任何的头像，那么请首先在菜单上选“不要头像”，然后留空所有自定义头像的部分！</b><BR><br>
<td bgcolor=#d7d7d7 valign=top>总头像个数： 373 个。
<select name="useravatar" size=1 onChange="showimage()" value="<%=beanUserInfo.getUserimg()%>">
<option value="">不要头像</option>
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
<br>图像位置： <input type=text name="personalavatar" size=50 maxlength=100 value="<%=beanUserInfo.getSelfimg()%>"><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;输入完整的 URL 路径。<br>
<br>图像宽度： <input type=text name="personalwidth" size=2 maxlength=3 value=<%=beanUserInfo.getSelfwidth()%>>　必须是 20 -- 100 之间的一个整数。<br>
<br>图像高度： <input type=text name="personalheight" size=2 maxlength=3 value=<%=beanUserInfo.getSelfheight()%>>　必须是 20 -- 100 之间的一个整数。<br></td>
</td></tr>
</table></td></tr>
<script>
function showadv(){
if (document.creator.advshow.checked == true) {
adv.style.display = "";
advance.innerText="关闭高级用户设置选项"
}else{
adv.style.display = "none";
advance.innerText="显示高级用户设置选项"
}
}
</script>
</tr></table><img src="" width=0 height=4><BR>
<table cellpadding=0 cellspacing=0 border=0 width=770 align=center>
<tr><td width=17 background=<%=request.getContextPath()%>/images/kleft.gif bgcolor=#d7d7d7 height=30></td>

<td width=50%><INPUT id=advcheck name=advshow type=checkbox value=1 onclick=showadv()><span id="advance">显示高级注册选项</a></span> 
</td>

<td width=50%>
<input type=submit value="修 改" onclick="return valid()"></td>
<td width=17 background=<%=request.getContextPath()%>/images/kright.gif bgcolor=#d7d7d7></td></form></tr></table>
	<TABLE cellSpacing=0 cellPadding=0 width=770 height=17 align=center border=0><tr><td width=17><img src=<%=request.getContextPath()%>/images/kbotleft.gif width=17 border=0></td><td background=<%=request.getContextPath()%>/images/kbot.gif>&nbsp;</td><td width=17><img src=<%=request.getContextPath()%>/images/kbotright.gif width=17 border=0></td></tr></table>


<center><hr width=380 size=1></center>


</body>
</html>