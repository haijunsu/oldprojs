<%@page contentType="text/html;charset=GB2312"%>
<jsp:useBean id="ForumData" scope="request" class="com.htyz.beanGetdata"/>
<html>

<head>
<title></title>
<script language="JavaScript">
<!-- hide from JavaScript-challenged browsers
function openWindow(url){popupWin=window.open(url,'new_page','width=400,height=400,directories=0,status=0,menubar=0,max=0,min=0');}
function openWindow2(url){popupWin=window.open(url,'new_page','width=400,height=450');}
function openWindow3(url){popupWin=window.open(url,'new_page','width=400,height=450,scrollbars=yes');}
function openAdminWindow(url){popupWin=window.open(url,'new_page','width=400,height=400,status=0,menubar=0,max=0,min=0');return false;}
function openpollWindow(url){popupWin=window.open(url,'new_page','width=300,height=400');}
function openAnnounceWindow(url){popupWin=window.open(url,'new_page','width='+(window.screen.availWidth/1.5)+',height=400,left='+(window.screen.availWidth-window.screen.availWidth/1.5-10)+',top=0,scrollbars=1');}
// done hiding -->
</script>
<style type=text/css>
<!--
A:link		{color:#333333;text-decoration:none;}
A:visited	{color:#333333;text-decoration:none;}
A:hover		{color:#333333;text-decoration:underline overline;}
A:active	{color:#333333;text-decoration:none;}
input.radio	{background: #EFF3F9; color:#000000}
font		{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:宋体, Arial, Helvetica}
font_size	{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:宋体, Arial, Helvetica}
SELECT		{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:宋体, Arial, Helvetica; BACKGROUND-COLOR: #efefef}
table		{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:宋体, Arial, Helvetica}
td		{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:宋体, Arial, Helvetica}
textarea	{ BACKGROUND-COLOR: #efefef; BORDER-BOTTOM: 1px double; BORDER-LEFT: 1px double; BORDER-RIGHT: 1px double; BORDER-TOP: 1px double; COLOR: #000000; font-size: 9pt ;FONT-FAMILY:宋体, Arial, Helvetica}
.Coolinput	{ BACKGROUND-COLOR: #EFF3F9; BORDER-BOTTOM: 1px double; BORDER-LEFT: 1px double; BORDER-RIGHT: 1px double; BORDER-TOP: 1px double; COLOR: #000000; font-size: 9pt; FONT-FAMILY:宋体, Arial, Helvetica}
INPUT		{ BACKGROUND-COLOR: #EFF3F9; CURSOR: HAND; BORDER-TOP-WIDTH: 1px; PADDING-RIGHT: 1px; PADDING-LEFT: 1px; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 9pt; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 1px; BORDER-BOTTOM-COLOR: #cccccc; PADDING-BOTTOM: 1px; BORDER-TOP-COLOR: #cccccc; PADDING-TOP: 1px; HEIGHT: 18px; BORDER-RIGHT-WIDTH: 1px; BORDER-RIGHT-COLOR: #cccccc}
input2		{ font-family: "Arial", "Helvetica", "sans-serif", "宋体"; font-size: 12px; border: 1px #000000 dashed}
.input3		{ width: 72px; height: 19px; font-size: 10.5pt; vertical-align: bottom}
BODY		{ CURSOR: default; FONT-FAMILY: Verdana,Arial,Helvetica,sans-serif,宋体; FONT-SIZE: 9pt;
		  SCROLLBAR-BASE-COLOR: #A9B6CD;
		/*SCROLLBAR-ARROW-COLOR: #f0F3Fa; SCROLLBAR-HIGHLIGHT-COLOR: buttonface;
		  SCROLLBAR-SHADOW-COLOR: buttonface; SCROLLBAR-3DLIGHT-COLOR: buttonhighlight;
		  SCROLLBAR-TRACK-COLOR: #A9B6CD; SCROLLBAR-DARKSHADOW-COLOR: #f0F3Fa
		*/ }
option#red{color:red}
option#blue{color:blue}
//-->
</style>

</HEAD>

<body bgColor="#FFFFFF" background="<%=request.getContextPath()%>/bbs/images/bg.gif" topmargin="0" leftmargin="0" text="#333333" link="#333333" aLink="#333333" vLink="#333333" >


<font face="宋体, Arial, Helvetica">

<SCRIPT LANGUAGE="JavaScript">
<!--
helpstat = false;
stprompt = true;
basic = false;

function thelp(swtch){
	if (swtch == 1){
		basic = false;
		stprompt = false;
		helpstat = true;
	} else if (swtch == 0) {
		helpstat = false;
		stprompt = false;
		basic = true;
	} else if (swtch == 2) {
		helpstat = false;
		basic = false;
		stprompt = true;
	}
}

function AddText(NewCode) {
	document.PostTopic.Message.value+=NewCode
}

function email() {
	if (helpstat) {
		alert("Email标签\n将一个Email地址转换成Email链接。\n用法一 : [url]webmaster\@wormcn.net[/url] \n用法二 : [url=\"huantian.com.cn\"]Email链接信息[/url]");
		}
	else if (basic) {
		AddTxt="[url][/url]";
		AddText(AddTxt);
		}
	else {
		txt2=prompt("输入在Email链接中想要显示的信息，如：老师的邮件地址\n您若希望直接显示Email地址，就空着不要填写，然后点确定按钮！","");
		if (txt2!=null) {
			txt=prompt("请输入Email地址.","mailto:");
			if (txt!=null) {
				if (txt2=="") {
					AddTxt="<a herf="+txt+">";
					AddText(AddTxt);
					AddTxt="</a>";
					AddText(AddTxt);
				} else {
					AddTxt="<a herf="+txt;
					AddText(AddTxt);
					AddTxt=">"+txt2;
					AddText(AddTxt);
					AddTxt="</a>";
					AddText(AddTxt);
				}
			}
		}
	}
}

function showsize(size) {
	if (helpstat) {
		alert("字体大小标签\n设置字体大小。\n可选值为1号到6号。\n 1号是最小字体，6号是最大字体。.\n\n法: [size="+size+"]设置为 "+size+" 号字体的文本[/size="+size+"]");
	} else if (basic) {
		AddTxt="[size="+size+"][/size="+size+"]";
		AddText(AddTxt);
	} else {
		txt=prompt("设置字体大小为 "+size,"输入需要设置字体大小的文本");
		if (txt!=null) {
			AddTxt="[size="+size+"]"+txt;
			AddText(AddTxt);
			AddTxt="[/size="+size+"]";
			AddText(AddTxt);
		}
	}
}

function bold() {
	if (helpstat) {
		alert("粗体标签.\n将文本字体加粗显示..\n\n用法: [b]需要加粗显示的文本[/b]");
	} else if (basic) {
		AddTxt="[b][/b]";
		AddText(AddTxt);
	} else {
		txt=prompt("加粗显示.","输入想要加粗显示的文本");
		if (txt!=null) {
			AddTxt="[b]"+txt;
			AddText(AddTxt);
			AddTxt="[/b]";
			AddText(AddTxt);
		}
	}
}

function italicize() {
	if (helpstat) {
		alert("斜体标签\n将文本字体设置为斜体.\n\n用法: [i]需要设置为斜体的文本[/i]");
	} else if (basic) {
		AddTxt="[i][/i]";
		AddText(AddTxt);
	} else {
		txt=prompt("设置为斜体","输入需要设置为斜体的文本");
		if (txt!=null) {
			AddTxt="[i]"+txt;
			AddText(AddTxt);
			AddTxt="[/i]";
			AddText(AddTxt);
		}
	}
}

function quote() {
	if (helpstat){
		alert("引用标签\n引用某人发表的一段文字.\n\n用法: [quote]需引用的内容[/quote]");
	} else if (basic) {
		AddTxt="[quote][/quote]";
		AddText(AddTxt);
	} else {
		txt=prompt("引用","输入需要引用的内容");
		if(txt!=null) {
			AddTxt="[quote]"+txt;
			AddText(AddTxt);
			AddTxt="[/quote]";
			AddText(AddTxt);
		}
	}
}

function showcolor(color) {
	if (helpstat) {
		alert("文字颜色标签\n设置文字颜色.\n\n用法: ["+color+"]需要设置成 "+color+" 的文本[/"+color+"]");
	} else if (basic) {
		AddTxt="["+color+"][/"+color+"]";
		AddText(AddTxt);
	} else {
     	txt=prompt("文字颜色设置成 "+color,"输入指定颜色的文本");
		if(txt!=null) {
			AddTxt="["+color+"]"+txt;
			AddText(AddTxt);
			AddTxt="[/"+color+"]";
			AddText(AddTxt);
		}
	}
}

function center() {
 	if (helpstat) {
		alert("居中标签\n将某段文本居中显示.\n\n用法: [center]需居中显示的文本[/center]");
	} else if (basic) {
		AddTxt="[center][/center]";
		AddText(AddTxt);
	} else {
		txt=prompt("文本居中显示","输入需居中显示的文本");
		if (txt!=null) {
			AddTxt="[center]"+txt;
			AddText(AddTxt);
			AddTxt="[/center]";
			AddText(AddTxt);
		}
	}
}

function hyperlink() {
	if (helpstat) {
		alert("超级链接标签\n将网址转换为超级链接。.\n\n用法一: [url]http://www.wormcn.net[/url]\n\n用法二: [url=\"http://www.wormcn.net\"]超级链接信息[/url]");
	} else if (basic) {
		AddTxt="[url][/url]";
		AddText(AddTxt);
	} else {
		txt2=prompt("需显示的超级链接信息.\n您若希望直接显示网址，就空着不要填写，然后按确定按钮.","");
		if (txt2!=null) {
			txt=prompt("请输入超级链接的网址.","http://");
			if (txt!=null) {
				if (txt2=="") {
					AddTxt="<a herf="+txt+">";
					AddText(AddTxt);
					AddTxt="</a>";
					AddText(AddTxt);
				} else {
					AddTxt="<a herf=\""+txt;
					AddText(AddTxt);
					AddTxt=">"+txt2;
					AddText(AddTxt);
					AddTxt="</a>";
					AddText(AddTxt);
				}
			}
		}
	}
}

function image() {
	if (helpstat){
		alert("图片标签\n在帖子中插入一个图片。.\n\n用法: [img]http://www.wormcn.net/logo.gif[/img]");
	} else if (basic) {
		AddTxt="[img][/img]";
		AddText(AddTxt);
	} else {
		txt=prompt("请输入图片链接的网址","http://");
		if(txt!=null) {
			AddTxt="[img]"+txt;
			AddText(AddTxt);
			AddTxt="[/img]";
			AddText(AddTxt);
		}
	}
}

function showcode() {
	if (helpstat) {
		alert("代码标签\n在输入的信息头尾加上水平线，使得输入信息保持原来的格式。\n主要用于以正确的格式显示一些代码，如Perl、javascripts代码。.\n\n用法: [code]需要以原始格式显示的代码内容[/code]");
	} else if (basic) {
		AddTxt="[code][/code]";
		AddText(AddTxt);
	} else {
		txt=prompt("输入需要以原始格式显示的代码内容","");
		if (txt!=null) {
			AddTxt="[code]"+txt;
			AddText(AddTxt);
			AddTxt="[/code]";
			AddText(AddTxt);
		}
	}
}

function list() {
	if (helpstat) {
		alert("编号标签\n创建一个以园点、数字或者字母为编号的列表.\n\n用法: \n[list]\n[*]项目一[/*]\n[*]项目二[/*]\n[*]项目三[/*]\n[/list]");
	} else if (basic) {
		AddTxt="\r[list]\r[*]1[/*]\r[*]2[/*]\r[*]3[/*]\r[/list]\r";
		AddText(AddTxt);
	} else {
		type=prompt("编号类型\n输入\'A\' 显示为字母编号, \'1\' 显示为数字编号，空白显示为园点编号.","");
		while ((type!="") && (type!="A") && (type!="a") && (type!="1") && (type!=null)) {
			type=prompt("错误！\n编号类型只能是空白，或者输入 'A' 和 '1'.","");
		}
		if (type!=null) {
			if (type=="") {
				AddTxt="\r[list]\r\n";
			} else {
				AddTxt="\r[list="+type+"]\r";
			}
			AddText(AddTxt);
			txt="1";
			while ((txt!="") && (txt!=null)) {
				txt=prompt("空白表示结束列表","");
				if (txt!="" && (txt!=null)) {
					AddTxt="[*]"+txt+"[/*]\r";
					AddText(AddTxt);
				}
			}
			if (type=="") {
				AddTxt="[/list]\r\n";
			} else {
				AddTxt="[/list="+type+"]\r\n";
			}
			AddText(AddTxt);
		}
	}
}

function underline() {
  	if (helpstat) {
		alert("下划线标签\n将指定文字加上下划线\n\n用法: [u]需加上下划线的文本[/u]");
	} else if (basic) {
		AddTxt="[u][/u]";
		AddText(AddTxt);
	} else {
		txt=prompt("输入需加上下划线的文本.","文本");
		if (txt!=null) {
			AddTxt="[u]"+txt;
			AddText(AddTxt);
			AddTxt="[/u]";
			AddText(AddTxt);
		}
	}
}

function showfont(font) {
 	if (helpstat){
		alert("字体标签\n设置文本字体\n字体能否正确显示取决于浏览者电脑中是否安装指定字体\n因此，中文字体推荐宋体和黑体这两种常用字体.\n\n用法: [font="+font+"]指定文本的字体为 "+font+"[/font]");
	} else if (basic) {
		AddTxt="[font="+font+"][/font="+font+"]";
		AddText(AddTxt);
	} else {
		txt=prompt("文本的字体为 "+font,"输入需设置字体的文本");
		if (txt!=null) {
			AddTxt="[font="+font+"]"+txt;
			AddText(AddTxt);
			AddTxt="[/font="+font+"]";
			AddText(AddTxt);
		}
	}
}

function setflash() {
	if (helpstat) {
		alert("Flash 动画\n插入 Flash 动画.\n用法: [flash]Flash 文件的地址[/flash]");
	} else if (basic) {
		AddTxt="[flash][/flash]";
		AddText(AddTxt);
	} else {
		txt=prompt("Flash 文件的地址","http://");
		if (txt!=null) {
			AddTxt="[flash]"+txt;
			AddText(AddTxt);
			AddTxt="[/flash]";
			AddText(AddTxt);
		}
	}
}

function setsound() {
	if (helpstat) {
		alert("声音标记\n产生背景音乐.\n用法: [sound]音乐文件的地址[/sound]");
	} else if (basic) {
		AddTxt="[sound][/sound]";
		AddText(AddTxt);
	} else {
		txt=prompt("产生背景音乐.","http://");
		if (txt!=null) {
			AddTxt="[sound]"+txt;
			AddText(AddTxt);
			AddTxt="[/sound]";
			AddText(AddTxt);
		}
	}
}

function setfly() {
	if (helpstat) {
		alert("飞翔标记\n使文字飞行.\n用法: [fly]文字为这样文字[/fly]");
	} else if (basic) {
		AddTxt="[fly][/fly]";
		AddText(AddTxt);
	} else {
		txt=prompt("飞翔文字","文字");
		if (txt!=null) {
			AddTxt="[fly]"+txt;
			AddText(AddTxt);
			AddTxt="[/fly]";
			AddText(AddTxt);
		}
	}
}

function move() {
	if (helpstat) {
		alert("移动标记\n使文字产生移动效果.\n用法: [move]要产生移动效果的文字[/move]");
	} else if (basic) {
		AddTxt="[move][/move]";
		AddText(AddTxt);
	} else {
		txt=prompt("要产生移动效果的文字","文字");
		if (txt!=null) {
			AddTxt="[move]"+txt;
			AddText(AddTxt);
			AddTxt="[/move]";
			AddText(AddTxt);
		}
	}
}

//-->
</SCRIPT>
<script src="Board.js"></script>

<script language="JavaScript">
<!--
function openscriphtml()
{
	if (navigator.appName!="Microsoft Internet Explorer")
		alert("此功能 Netscape 用户不能使用！")
	else
 	{
		newwin=window.open('htmledit/editor.html','','width=544,height=294');
		newwin.focus();
	}
}

function selectUsers()
{
	if (document.PostTopic.AuthUsers.length == 1)
	{
		document.PostTopic.AuthUsers.options[0].value = "";
		return;
	}
	if (document.PostTopic.AuthUsers.length == 2)
		document.PostTopic.AuthUsers.options[0].selected = true
	else
	for (x = 0;x < document.PostTopic.AuthUsers.length - 1 ;x++)
		document.PostTopic.AuthUsers.options[x].selected = true;
}

function MoveWholeList(strAction)
{
	if (strAction == "Add")
	{
		if (document.PostTopic.AuthUsersCombo.length > 1)
		{
	for (x = 0;x < document.PostTopic.AuthUsersCombo.length - 1 ;x++)
		document.PostTopic.AuthUsersCombo.options[x].selected = true;
		InsertSelection("Add");
	}
	}
	else
	{
		if (document.PostTopic.AuthUsers.length > 1)
	{
	for (x = 0;x < document.PostTopic.AuthUsers.length - 1 ;x++)
		document.PostTopic.AuthUsers.options[x].selected = true;
		InsertSelection("Del");
	}
}
}

function InsertSelection(strAction)
{
	var pos,user,mText;
	var count,finished;

	if (strAction == "Add")
	{
		pos = document.PostTopic.AuthUsers.length;
		finished = false;
		count = 0;	
		do //Add to destination
		{
		try{
			if (document.PostTopic.AuthUsersCombo.options[count].text == "")
			{
				finished = true;
				continue;
			}
		}
		catch(e)
		{
				finished = true;
				return;
		}
			if (document.PostTopic.AuthUsersCombo.options[count].selected)
			{
				document.PostTopic.AuthUsers.length +=1;
				document.PostTopic.AuthUsers.options[pos].value = document.PostTopic.AuthUsers.options[pos-1].value;	
				document.PostTopic.AuthUsers.options[pos].text = document.PostTopic.AuthUsers.options[pos-1].text;
				document.PostTopic.AuthUsers.options[pos-1].value = document.PostTopic.AuthUsersCombo.options[count].value;	
				document.PostTopic.AuthUsers.options[pos-1].text = document.PostTopic.AuthUsersCombo.options[count].text;
				document.PostTopic.AuthUsers.options[pos-1].selected = true;
			}
			pos = document.PostTopic.AuthUsers.length;
			count += 1;
		}while (!finished); //finished adding
		finished = false;
		count = document.PostTopic.AuthUsersCombo.length - 1;
		do //remove from source
		{	
			try {
			if (document.PostTopic.AuthUsersCombo.options[count].text == "")
			{
				--count;
				continue;
			}
			}
			catch(e)
			{
				return;
			}
			if (document.PostTopic.AuthUsersCombo.options[count].selected )
			{
				for ( z = count ; z < document.PostTopic.AuthUsersCombo.length-1;z++)
				{	
				if ((document.PostTopic.AuthUsers.length-count) == 2)
				{
						document.PostTopic.AuthUsers.options[z].value = "";						
						document.PostTopic.AuthUsers.options[z].text = "";						
				}											
				else					
				{
					document.PostTopic.AuthUsersCombo.options[z].value = document.PostTopic.AuthUsersCombo.options[z+1].value;	
					document.PostTopic.AuthUsersCombo.options[z].text = document.PostTopic.AuthUsersCombo.options[z+1].text;
				}
				}
				document.PostTopic.AuthUsersCombo.length -= 1;
			}
			--count;
			if (count < 0)
				finished = true;
		}while(!finished) //finished removing
	}	

	if (strAction == "Del")
	{
		pos = document.PostTopic.AuthUsersCombo.length;
		finished = false;
		count = 0;	
		do //Add to destination
		{
			try{
			if (document.PostTopic.AuthUsers.options[count].text == "")
			{
				finished = true;
				continue;
			}
			}
			catch(e)
			{
				finished = true;
				return;
			}
			if (document.PostTopic.AuthUsers.options[count].selected)
			{
				document.PostTopic.AuthUsersCombo.length +=1;
				document.PostTopic.AuthUsersCombo.options[pos].value = document.PostTopic.AuthUsersCombo.options[pos-1].value;	
				document.PostTopic.AuthUsersCombo.options[pos].text = document.PostTopic.AuthUsersCombo.options[pos-1].text;
				document.PostTopic.AuthUsersCombo.options[pos-1].value = document.PostTopic.AuthUsers.options[count].value;	
				document.PostTopic.AuthUsersCombo.options[pos-1].text = document.PostTopic.AuthUsers.options[count].text;
				document.PostTopic.AuthUsersCombo.options[pos-1].selected = true;
			}
			count += 1;
			pos = document.PostTopic.AuthUsersCombo.length;
		}while (!finished); //finished adding
		finished = false;
		count = document.PostTopic.AuthUsers.length - 1;
		do //remove from source
		{	
			try{
			if (document.PostTopic.AuthUsers.options[count].text == "")
			{
				--count;
				continue;
			}
			}
			catch(e)
			{
				return;
			}
			if (document.PostTopic.AuthUsers.options[count].selected )
			{
				for ( z = count ; z < document.PostTopic.AuthUsers.length-1;z++)
				{	
					if ((document.PostTopic.AuthUsers.length-count) == 2)
						{
						document.PostTopic.AuthUsers.options[z].value = "";
						document.PostTopic.AuthUsers.options[z].text = "";
						}
						
					else							
						{							
						document.PostTopic.AuthUsers.options[z].value = document.PostTopic.AuthUsers.options[z+1].value;	
						document.PostTopic.AuthUsers.options[z].text = document.PostTopic.AuthUsers.options[z+1].text;
						}
				}
				document.PostTopic.AuthUsers.length -= 1;
			}
			--count;
			if (count < 0)
				finished = true;
		}while(!finished) //finished removing
	}	
}

function autoReload(objform)
{
	var tmpCookieURL = '/angel/';
	if (objform.SelectSize.value == 1)
	{
			document.PostTopic.Message.cols = 60;
			document.PostTopic.Message.rows = 8;		
		}
		if (objform.SelectSize.value == 2)
		{			
			document.PostTopic.Message.cols = 80;			
			document.PostTopic.Message.rows = 12;		
		}		
		if (objform.SelectSize.value == 3)
		{			
			document.PostTopic.Message.cols = 90;			
			document.PostTopic.Message.rows = 12;		
		}		
		if (objform.SelectSize.value == 4)
		{			
			document.PostTopic.Message.cols = 130;			
			document.PostTopic.Message.rows = 15;		
		}	
		document.PostTopic.SelectSize.value = objform.SelectSize.value;		
		document.cookie =  tmpCookieURL + "strSelectSize=" + objform.SelectSize.value;

}

ie = (document.all)? true:false
if (ie){
function ctlent(eventobject){if(event.ctrlKey && window.event.keyCode==13){this.document.PostTopic.submit();}}
}

clckcnt = 0;
function clckcntr() {
	clckcnt++;
	if(clckcnt > 1) {
		if(clckcnt > 2) { return false; }
		alert('贴子已经发出了......\n\n' + '请等待片刻......\n\n' + '不要重复按提交键，谢谢！');
		return false;
	}
    if(document.PostTopic.Bi_title.value.length==0){
		alert('您没有填写帖子的主题!');
		clckcnt=0;
		return false;
	}
	return true;
}
//-->
</script>

<br>
<table border="0" cellspacing="0" cellpadding="0" align=center width="95%">
  <tr>
        <td bgColor="#A9B6CD" colspan="2" align="center">发表主题</td>
  </tr>
  <tr>
     <td bgcolor="#60718B">
    <table border="0" cellspacing="1" cellpadding="4" width="100%">

<form action="<%=request.getContextPath()%>/servlet/bbs/Forum" method="post" name="PostTopic">
	<input name="biid" type="hidden" value="<%=request.getParameter("biid")%>">
	<input name="catid" type="hidden" value="<%=request.getParameter("catid")%>">
	<input name="action" type="hidden" value="<%=request.getParameter("action").equalsIgnoreCase("Reply")?"ReplySave":"save"%>">


<script>
	function HighlightAll(theField) {
		var tempval=eval("document."+theField)
		tempval.focus()
		tempval.select()
		therange=tempval.createTextRange()
		therange.execCommand("Copy")}

	function DoTitle(addTitle) {
		var revisedTitle;
		var currentTitle = document.PostTopic.Bi_title.value;
		revisedTitle = currentTitle+addTitle;
		document.PostTopic.Bi_title.value=revisedTitle;
		document.PostTopic.Bi_title.focus();
		return; }

	function checklength(theform){alert("您的文章目前有 "+theform.Message.value.length+" 字节。");}
</script>
    <tr>
        <td bgColor="#f0F3Fa" noWrap vAlign="top" align="right"><font color="#333333"><B>贴子主题：</B></FONT>
<%if(request.getParameter("action").equalsIgnoreCase("Reply")){%>
</td>
<td bgColor="#f0F3Fa"><font color="#333333"><%=ForumData.getFieldValue("Bi_title",0)%> A<%=ForumData.getRowCount()%>A</FONT>
</td>
<%}else {%>
			<SELECT name=font onchange=DoTitle(this.options[this.selectedIndex].value)>
			<OPTION selected value="">选择话题</OPTION> <OPTION value=[原创]>[原创]</OPTION>
			<OPTION value=[转帖]>[转帖]</OPTION> <OPTION value=[灌水]>[灌水]</OPTION>
			<OPTION value=[讨论]>[讨论]</OPTION> <OPTION value=[求助]>[求助]</OPTION>
			<OPTION value=[推荐]>[推荐]</OPTION> <OPTION value=[公告]>[公告]</OPTION>
			<OPTION value=[注意]>[注意]</OPTION> <OPTION value=[贴图]>[贴图]</OPTION>
			<OPTION value=[建议]>[建议]</OPTION> <OPTION value=[下载]>[下载]</OPTION>
			<OPTION value=[分享]>[分享]</OPTION></SELECT>
		</td>
        <td bgColor="#f0F3Fa">&nbsp;<input maxLength="80" name="Bi_title" value="" size="60"> &nbsp;<font color="#333333">不得超过 40 个汉字</FONT></td>
<%}%>
    </tr>

<!---
    <input name="UserName" type="hidden" Value="">
    <input name="Password" type="hidden" value="">
//-->

      <tr>
        <td bgColor="#f0F3Fa" noWrap vAlign="top" align="right"><font face="宋体, Arial, Helvetica" size="2"><B>请输入您的用户名：</B></font></td>
        <td bgColor="#f0F3Fa">&nbsp;<input name="user_id" size="23" maxLength="25" type="text" value="<%=session.getAttribute("userid")==null?"":session.getAttribute("userid")%>"></td>
      </tr>
      <tr>
        <td bgColor="#f0F3Fa" noWrap vAlign="top" align="right"><font face="宋体, Arial, Helvetica" size="2"><B>请输入您的密码：</B></font></td>
        <td bgColor="#f0F3Fa" valign="top">&nbsp;<input name="Password" size="25" maxLength="13" type="password" value="<%=session.getAttribute("password")==null?"":session.getAttribute("password")%>"></td>
      </tr>
<tr>
<td bgColor="#EFF3F9" align=right valign=top>
<font face="宋体, Arial, Helvetica" size="2"><B>当前心情：</B><BR><li>将放在贴子的前面</font>&nbsp;&nbsp;</td>
<td bgColor="#EFF3F9" align=left>
&nbsp;<INPUT type=radio value="67" name="usericon" CHECKED><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/67.gif" width=13 border=0>&nbsp;<INPUT type=radio value="60" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/60.gif" width=13 border=0>&nbsp;<INPUT type=radio value="43" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/43.gif" width=13 border=0>&nbsp;<INPUT type=radio value="74" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/74.gif" width=13 border=0>&nbsp;<INPUT type=radio value="46" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/46.gif" width=13 border=0>&nbsp;<INPUT type=radio value="66" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/66.gif" width=13 border=0>&nbsp;<INPUT type=radio value="5" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/5.gif" width=13 border=0>&nbsp;<INPUT type=radio value="56" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/56.gif" width=13 border=0>&nbsp;<INPUT type=radio value="37" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/37.gif" width=13 border=0>&nbsp;<INPUT type=radio value="17" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/17.gif" width=13 border=0>&nbsp;<INPUT type=radio value="57" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/57.gif" width=13 border=0>&nbsp;<INPUT type=radio value="13" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/13.gif" width=13 border=0>&nbsp;<INPUT type=radio value="32" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/32.gif" width=13 border=0>&nbsp;<BR>&nbsp;<INPUT type=radio value="53" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/53.gif" width=13 border=0>&nbsp;<INPUT type=radio value="25" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/25.gif" width=13 border=0>&nbsp;<INPUT type=radio value="51" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/51.gif" width=13 border=0>&nbsp;<INPUT type=radio value="30" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/30.gif" width=13 border=0>&nbsp;<INPUT type=radio value="36" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/36.gif" width=13 border=0>&nbsp;<INPUT type=radio value="75" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/75.gif" width=13 border=0>&nbsp;<INPUT type=radio value="44" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/44.gif" width=13 border=0>&nbsp;<INPUT type=radio value="71" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/71.gif" width=13 border=0>&nbsp;<INPUT type=radio value="24" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/24.gif" width=13 border=0>&nbsp;<INPUT type=radio value="15" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/15.gif" width=13 border=0>&nbsp;<INPUT type=radio value="18" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/18.gif" width=13 border=0>&nbsp;<INPUT type=radio value="40" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/40.gif" width=13 border=0>&nbsp;<INPUT type=radio value="62" name="usericon"><IMG height=13 src="<%=request.getContextPath()%>/bbs/images/Face/62.gif" width=13 border=0>&nbsp;
</td>
</tr><script language="Javascript">
<!--
function insertsmilie(smilieface){
	PostTopic.Message.value+=smilieface;
}
function enable(btn){btn.filters.gray.enabled=0;}
function disable(btn){btn.filters.gray.enabled=1;}
// -->
</script>

<tr>
	<td bgColor="#EFF3F9" align=right rowspan=2 valign=top>
		<font face="宋体, Arial, Helvetica" size="2"><B>格式：</B></font></td>
	<td bgColor="#EFF3F9" align=left>&nbsp;
<style>
.gray {CURSOR:hand;filter:gray}
</style>


<IMG onclick=bold() height=22 alt=粗体字 src=<%=request.getContextPath()%>/bbs/images/btg/bold.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=italicize() height=22 alt=斜体字 src=<%=request.getContextPath()%>/bbs/images/btg/italicize.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=underline() height=22 alt=下划线 src=<%=request.getContextPath()%>/bbs/images/btg/underline.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=center() height=22 alt=居中 src=<%=request.getContextPath()%>/bbs/images/btg/center.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=hyperlink() height=22 alt=插入超级链接 src=<%=request.getContextPath()%>/bbs/images/btg/url.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=email() height=22 alt=插入邮件地址 src=<%=request.getContextPath()%>/bbs/images/btg/email.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=image() height=22 alt=插入图片 src=<%=request.getContextPath()%>/bbs/images/btg/image.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=setflash() height=22 alt="插入 Flash 动画" src=<%=request.getContextPath()%>/bbs/images/btg/swf.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=setsound() height=22 alt=插入声音 src=<%=request.getContextPath()%>/bbs/images/btg/sound.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=showcode() height=22 alt=插入代码 src=<%=request.getContextPath()%>/bbs/images/btg/code.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=quote() height=22 alt=插入引用 src=<%=request.getContextPath()%>/bbs/images/btg/quote.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=list() height=22 alt=插入列表 src=<%=request.getContextPath()%>/bbs/images/btg/list.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=setfly() height=22 alt=飞行字 src=<%=request.getContextPath()%>/bbs/images/btg/fly.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=move() height=22 alt=移动字 src=<%=request.getContextPath()%>/bbs/images/btg/move.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
</td>
</tr>
<tr>
<td bgColor="#EFF3F9" align=left>&nbsp;
<font face="宋体, Arial, Helvetica" size="2">字体：
    <select name="font" onChange="showfont(this.options[this.selectedIndex].value)">
	<option value="宋体" selected>宋体</option>
	<option style="font-family:'Arial'" value="Arial">Arial</option>
	<option style="font-family:'Book Antiqua'" value="Book Antiqua">Book Antiqua</option>
	<option value="Century Gothic">Century Gothic</option>
	<option value="Comic Sans MS">Comic Sans MS</option>
	<option value="Courier New">Courier New</option>
	<option value="Georgia">Georgia</option>
	<option value="Impact">Impact</option>
	<option value="Tahoma">Tahoma</option>
	<option value="Times New Roman">Times New Roman</option>
	<option value="Trebuchet MS">Trebuchet MS</option>
	<option value="Script MT Bold">Script MT Bold</option>
	<option value="Stencil">Stencil</option>
	<option value="Verdana">Verdana</option>
	<option value="Lucida Console">Lucida Console</option>
</select>&nbsp;&nbsp;字号：
<select name="size" onChange="showsize(this.options[this.selectedIndex].value)">
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3" selected>3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>	
</select>&nbsp;&nbsp;颜色：
<select name="color" onChange="showcolor(this.options[this.selectedIndex].value)">
	<option style=background-color:#ffffff;color:black value="black">&nbsp;默认值&nbsp;</option>
	<option style=background-color:black;color:black value="black">[ 黑色 ]</option>
	<option style=background-color:white;color:white value="white">[ 白色 ]</option>
	<option style=background-color:silver;color:silver value="silver">[ 银白 ]</option>
	<option style=background-color:gray;color:gray value="gray">[ 灰色 ]</option>
	<option style=background-color:red;color:red value="red">[ 红色 ]</option>
	<option style=background-color:yellow;color:yellow value="yellow">[ 黄色 ]</option>
	<option style=background-color:gold;color:gold value="gold">[ 金色 ]</option>
	<option style=background-color:pink;color:pink value="pink">[ 粉红 ]</option>
	<option style=background-color:green;color:green value="green">[ 绿色 ]</option>
	<option style=background-color:orange;color:orange value="orange">[ 橘色 ]</option>
	<option style=background-color:purple;color:purple value="purple">[ 紫色 ]</option>
	<option style=background-color:blue;color:blue value="blue">[ 蓝色 ]</option>
	<option style=background-color:beige;color:beige value="beige">[ 米黄 ]</option>
	<option style=background-color:brown;color:brown value="brown">[ 棕色 ]</option>
	<option style=background-color:teal;color:teal value="teal">[ 蓝绿 ]</option>
	<option style=background-color:navy;color:navy value="navy">[ 深蓝 ]</option>
	<option style=background-color:maroon;color:maroon value="maroon">[ 褐紫 ]</option>
	<option style=background-color:limeGreen;color:limeGreen value="limeGreen">[ 黄绿 ]</option>
	<option style=background-color:violet;color:violet value="violet">[ 淡紫 ]</option>
</select></td>
</tr>
      <tr>
        <td bgColor="#f0F3Fa" noWrap vAlign="top" align="right"><font face="宋体, Arial, Helvetica" size="2"><B>内容：</B><br>
        </FONT>
        </td>
        <td bgColor="#f0F3Fa">
	&nbsp;<textarea cols="80" rows="12" name="Message" wrap="VIRTUAL" onkeydown="ctlent(this.form)"></textarea>
	<br><a href=javascript:HighlightAll('PostTopic.Message')>复制到剪贴板</a> | <a href=javascript:checklength(document.PostTopic);>查看文章长度</a>&gt;&gt;
	</td>
      </tr>
      <tr>
        <td bgColor="#A9B6CD" colspan="2" align="center"><input name="Submit" type="submit" value="<%=request.getParameter("action").equalsIgnoreCase("Reply")?"回复":"发表新主题"%>" onClick="return clckcntr();">

        &nbsp;<input name="Reset" type="reset" value="全部清除"></td>
      </tr>

    </table>
    </td>
  </tr>
</table>
</form>
</TABLE>
</TD>
</TR>
</TABLE>
</TD>
</TR>
</TABLE>

</div>

<TABLE width="95%" border=0 cellpadding="0" cellspacing="1" align="center">

<TR>
<TD bgcolor="#f0F3Fa" nowrap align="right"><a href="#top"><img src="<%=request.getContextPath()%>/bbs/images/icon_go_up.gif" border="0" align="right" alt="顶部"></A></FONT></TD>
</TR>
</TABLE>

<TABLE border=0 width="95%" align="center" cellpadding="0" cellspacing="0">
<TR>
<TD align="center"><HR width="390" noshade size="1"></TD>
</TR>
<TR>
<TD align="center"><font color="#333333">&copy; 中文版权所有，北京环天宇正信息科技有限公司  Ver.1.1.0.1</font>
<BR>本论坛言论纯属发表者个人意见，与<font color="#990000"><b> 北京环天宇正信息科技有限公司 </b></font>立场无关<BR>
<IMG src="" width=0 height=5></td>
</TR>
</TABLE>

</BODY>
</HTML>
