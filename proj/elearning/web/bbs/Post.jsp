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
font		{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:����, Arial, Helvetica}
font_size	{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:����, Arial, Helvetica}
SELECT		{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:����, Arial, Helvetica; BACKGROUND-COLOR: #efefef}
table		{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:����, Arial, Helvetica}
td		{ FONT-size: 9pt; line-height: 13pt; FONT-FAMILY:����, Arial, Helvetica}
textarea	{ BACKGROUND-COLOR: #efefef; BORDER-BOTTOM: 1px double; BORDER-LEFT: 1px double; BORDER-RIGHT: 1px double; BORDER-TOP: 1px double; COLOR: #000000; font-size: 9pt ;FONT-FAMILY:����, Arial, Helvetica}
.Coolinput	{ BACKGROUND-COLOR: #EFF3F9; BORDER-BOTTOM: 1px double; BORDER-LEFT: 1px double; BORDER-RIGHT: 1px double; BORDER-TOP: 1px double; COLOR: #000000; font-size: 9pt; FONT-FAMILY:����, Arial, Helvetica}
INPUT		{ BACKGROUND-COLOR: #EFF3F9; CURSOR: HAND; BORDER-TOP-WIDTH: 1px; PADDING-RIGHT: 1px; PADDING-LEFT: 1px; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 9pt; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 1px; BORDER-BOTTOM-COLOR: #cccccc; PADDING-BOTTOM: 1px; BORDER-TOP-COLOR: #cccccc; PADDING-TOP: 1px; HEIGHT: 18px; BORDER-RIGHT-WIDTH: 1px; BORDER-RIGHT-COLOR: #cccccc}
input2		{ font-family: "Arial", "Helvetica", "sans-serif", "����"; font-size: 12px; border: 1px #000000 dashed}
.input3		{ width: 72px; height: 19px; font-size: 10.5pt; vertical-align: bottom}
BODY		{ CURSOR: default; FONT-FAMILY: Verdana,Arial,Helvetica,sans-serif,����; FONT-SIZE: 9pt;
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


<font face="����, Arial, Helvetica">

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
		alert("Email��ǩ\n��һ��Email��ַת����Email���ӡ�\n�÷�һ : [url]webmaster\@wormcn.net[/url] \n�÷��� : [url=\"huantian.com.cn\"]Email������Ϣ[/url]");
		}
	else if (basic) {
		AddTxt="[url][/url]";
		AddText(AddTxt);
		}
	else {
		txt2=prompt("������Email��������Ҫ��ʾ����Ϣ���磺��ʦ���ʼ���ַ\n����ϣ��ֱ����ʾEmail��ַ���Ϳ��Ų�Ҫ��д��Ȼ���ȷ����ť��","");
		if (txt2!=null) {
			txt=prompt("������Email��ַ.","mailto:");
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
		alert("�����С��ǩ\n���������С��\n��ѡֵΪ1�ŵ�6�š�\n 1������С���壬6����������塣.\n\n��: [size="+size+"]����Ϊ "+size+" ��������ı�[/size="+size+"]");
	} else if (basic) {
		AddTxt="[size="+size+"][/size="+size+"]";
		AddText(AddTxt);
	} else {
		txt=prompt("���������СΪ "+size,"������Ҫ���������С���ı�");
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
		alert("�����ǩ.\n���ı�����Ӵ���ʾ..\n\n�÷�: [b]��Ҫ�Ӵ���ʾ���ı�[/b]");
	} else if (basic) {
		AddTxt="[b][/b]";
		AddText(AddTxt);
	} else {
		txt=prompt("�Ӵ���ʾ.","������Ҫ�Ӵ���ʾ���ı�");
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
		alert("б���ǩ\n���ı���������Ϊб��.\n\n�÷�: [i]��Ҫ����Ϊб����ı�[/i]");
	} else if (basic) {
		AddTxt="[i][/i]";
		AddText(AddTxt);
	} else {
		txt=prompt("����Ϊб��","������Ҫ����Ϊб����ı�");
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
		alert("���ñ�ǩ\n����ĳ�˷����һ������.\n\n�÷�: [quote]�����õ�����[/quote]");
	} else if (basic) {
		AddTxt="[quote][/quote]";
		AddText(AddTxt);
	} else {
		txt=prompt("����","������Ҫ���õ�����");
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
		alert("������ɫ��ǩ\n����������ɫ.\n\n�÷�: ["+color+"]��Ҫ���ó� "+color+" ���ı�[/"+color+"]");
	} else if (basic) {
		AddTxt="["+color+"][/"+color+"]";
		AddText(AddTxt);
	} else {
     	txt=prompt("������ɫ���ó� "+color,"����ָ����ɫ���ı�");
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
		alert("���б�ǩ\n��ĳ���ı�������ʾ.\n\n�÷�: [center]�������ʾ���ı�[/center]");
	} else if (basic) {
		AddTxt="[center][/center]";
		AddText(AddTxt);
	} else {
		txt=prompt("�ı�������ʾ","�����������ʾ���ı�");
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
		alert("�������ӱ�ǩ\n����ַת��Ϊ�������ӡ�.\n\n�÷�һ: [url]http://www.wormcn.net[/url]\n\n�÷���: [url=\"http://www.wormcn.net\"]����������Ϣ[/url]");
	} else if (basic) {
		AddTxt="[url][/url]";
		AddText(AddTxt);
	} else {
		txt2=prompt("����ʾ�ĳ���������Ϣ.\n����ϣ��ֱ����ʾ��ַ���Ϳ��Ų�Ҫ��д��Ȼ��ȷ����ť.","");
		if (txt2!=null) {
			txt=prompt("�����볬�����ӵ���ַ.","http://");
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
		alert("ͼƬ��ǩ\n�������в���һ��ͼƬ��.\n\n�÷�: [img]http://www.wormcn.net/logo.gif[/img]");
	} else if (basic) {
		AddTxt="[img][/img]";
		AddText(AddTxt);
	} else {
		txt=prompt("������ͼƬ���ӵ���ַ","http://");
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
		alert("�����ǩ\n���������Ϣͷβ����ˮƽ�ߣ�ʹ��������Ϣ����ԭ���ĸ�ʽ��\n��Ҫ��������ȷ�ĸ�ʽ��ʾһЩ���룬��Perl��javascripts���롣.\n\n�÷�: [code]��Ҫ��ԭʼ��ʽ��ʾ�Ĵ�������[/code]");
	} else if (basic) {
		AddTxt="[code][/code]";
		AddText(AddTxt);
	} else {
		txt=prompt("������Ҫ��ԭʼ��ʽ��ʾ�Ĵ�������","");
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
		alert("��ű�ǩ\n����һ����԰�㡢���ֻ�����ĸΪ��ŵ��б�.\n\n�÷�: \n[list]\n[*]��Ŀһ[/*]\n[*]��Ŀ��[/*]\n[*]��Ŀ��[/*]\n[/list]");
	} else if (basic) {
		AddTxt="\r[list]\r[*]1[/*]\r[*]2[/*]\r[*]3[/*]\r[/list]\r";
		AddText(AddTxt);
	} else {
		type=prompt("�������\n����\'A\' ��ʾΪ��ĸ���, \'1\' ��ʾΪ���ֱ�ţ��հ���ʾΪ԰����.","");
		while ((type!="") && (type!="A") && (type!="a") && (type!="1") && (type!=null)) {
			type=prompt("����\n�������ֻ���ǿհף��������� 'A' �� '1'.","");
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
				txt=prompt("�հױ�ʾ�����б�","");
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
		alert("�»��߱�ǩ\n��ָ�����ּ����»���\n\n�÷�: [u]������»��ߵ��ı�[/u]");
	} else if (basic) {
		AddTxt="[u][/u]";
		AddText(AddTxt);
	} else {
		txt=prompt("����������»��ߵ��ı�.","�ı�");
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
		alert("�����ǩ\n�����ı�����\n�����ܷ���ȷ��ʾȡ��������ߵ������Ƿ�װָ������\n��ˣ����������Ƽ�����ͺ��������ֳ�������.\n\n�÷�: [font="+font+"]ָ���ı�������Ϊ "+font+"[/font]");
	} else if (basic) {
		AddTxt="[font="+font+"][/font="+font+"]";
		AddText(AddTxt);
	} else {
		txt=prompt("�ı�������Ϊ "+font,"����������������ı�");
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
		alert("Flash ����\n���� Flash ����.\n�÷�: [flash]Flash �ļ��ĵ�ַ[/flash]");
	} else if (basic) {
		AddTxt="[flash][/flash]";
		AddText(AddTxt);
	} else {
		txt=prompt("Flash �ļ��ĵ�ַ","http://");
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
		alert("�������\n������������.\n�÷�: [sound]�����ļ��ĵ�ַ[/sound]");
	} else if (basic) {
		AddTxt="[sound][/sound]";
		AddText(AddTxt);
	} else {
		txt=prompt("������������.","http://");
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
		alert("������\nʹ���ַ���.\n�÷�: [fly]����Ϊ��������[/fly]");
	} else if (basic) {
		AddTxt="[fly][/fly]";
		AddText(AddTxt);
	} else {
		txt=prompt("��������","����");
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
		alert("�ƶ����\nʹ���ֲ����ƶ�Ч��.\n�÷�: [move]Ҫ�����ƶ�Ч��������[/move]");
	} else if (basic) {
		AddTxt="[move][/move]";
		AddText(AddTxt);
	} else {
		txt=prompt("Ҫ�����ƶ�Ч��������","����");
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
		alert("�˹��� Netscape �û�����ʹ�ã�")
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
		alert('�����Ѿ�������......\n\n' + '��ȴ�Ƭ��......\n\n' + '��Ҫ�ظ����ύ����лл��');
		return false;
	}
    if(document.PostTopic.Bi_title.value.length==0){
		alert('��û����д���ӵ�����!');
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
        <td bgColor="#A9B6CD" colspan="2" align="center">��������</td>
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

	function checklength(theform){alert("��������Ŀǰ�� "+theform.Message.value.length+" �ֽڡ�");}
</script>
    <tr>
        <td bgColor="#f0F3Fa" noWrap vAlign="top" align="right"><font color="#333333"><B>�������⣺</B></FONT>
<%if(request.getParameter("action").equalsIgnoreCase("Reply")){%>
</td>
<td bgColor="#f0F3Fa"><font color="#333333"><%=ForumData.getFieldValue("Bi_title",0)%> A<%=ForumData.getRowCount()%>A</FONT>
</td>
<%}else {%>
			<SELECT name=font onchange=DoTitle(this.options[this.selectedIndex].value)>
			<OPTION selected value="">ѡ����</OPTION> <OPTION value=[ԭ��]>[ԭ��]</OPTION>
			<OPTION value=[ת��]>[ת��]</OPTION> <OPTION value=[��ˮ]>[��ˮ]</OPTION>
			<OPTION value=[����]>[����]</OPTION> <OPTION value=[����]>[����]</OPTION>
			<OPTION value=[�Ƽ�]>[�Ƽ�]</OPTION> <OPTION value=[����]>[����]</OPTION>
			<OPTION value=[ע��]>[ע��]</OPTION> <OPTION value=[��ͼ]>[��ͼ]</OPTION>
			<OPTION value=[����]>[����]</OPTION> <OPTION value=[����]>[����]</OPTION>
			<OPTION value=[����]>[����]</OPTION></SELECT>
		</td>
        <td bgColor="#f0F3Fa">&nbsp;<input maxLength="80" name="Bi_title" value="" size="60"> &nbsp;<font color="#333333">���ó��� 40 ������</FONT></td>
<%}%>
    </tr>

<!---
    <input name="UserName" type="hidden" Value="">
    <input name="Password" type="hidden" value="">
//-->

      <tr>
        <td bgColor="#f0F3Fa" noWrap vAlign="top" align="right"><font face="����, Arial, Helvetica" size="2"><B>�����������û�����</B></font></td>
        <td bgColor="#f0F3Fa">&nbsp;<input name="user_id" size="23" maxLength="25" type="text" value="<%=session.getAttribute("userid")==null?"":session.getAttribute("userid")%>"></td>
      </tr>
      <tr>
        <td bgColor="#f0F3Fa" noWrap vAlign="top" align="right"><font face="����, Arial, Helvetica" size="2"><B>�������������룺</B></font></td>
        <td bgColor="#f0F3Fa" valign="top">&nbsp;<input name="Password" size="25" maxLength="13" type="password" value="<%=session.getAttribute("password")==null?"":session.getAttribute("password")%>"></td>
      </tr>
<tr>
<td bgColor="#EFF3F9" align=right valign=top>
<font face="����, Arial, Helvetica" size="2"><B>��ǰ���飺</B><BR><li>���������ӵ�ǰ��</font>&nbsp;&nbsp;</td>
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
		<font face="����, Arial, Helvetica" size="2"><B>��ʽ��</B></font></td>
	<td bgColor="#EFF3F9" align=left>&nbsp;
<style>
.gray {CURSOR:hand;filter:gray}
</style>


<IMG onclick=bold() height=22 alt=������ src=<%=request.getContextPath()%>/bbs/images/btg/bold.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=italicize() height=22 alt=б���� src=<%=request.getContextPath()%>/bbs/images/btg/italicize.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=underline() height=22 alt=�»��� src=<%=request.getContextPath()%>/bbs/images/btg/underline.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=center() height=22 alt=���� src=<%=request.getContextPath()%>/bbs/images/btg/center.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=hyperlink() height=22 alt=���볬������ src=<%=request.getContextPath()%>/bbs/images/btg/url.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=email() height=22 alt=�����ʼ���ַ src=<%=request.getContextPath()%>/bbs/images/btg/email.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=image() height=22 alt=����ͼƬ src=<%=request.getContextPath()%>/bbs/images/btg/image.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=setflash() height=22 alt="���� Flash ����" src=<%=request.getContextPath()%>/bbs/images/btg/swf.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=setsound() height=22 alt=�������� src=<%=request.getContextPath()%>/bbs/images/btg/sound.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=showcode() height=22 alt=������� src=<%=request.getContextPath()%>/bbs/images/btg/code.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=quote() height=22 alt=�������� src=<%=request.getContextPath()%>/bbs/images/btg/quote.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=list() height=22 alt=�����б� src=<%=request.getContextPath()%>/bbs/images/btg/list.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=setfly() height=22 alt=������ src=<%=request.getContextPath()%>/bbs/images/btg/fly.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
<IMG onclick=move() height=22 alt=�ƶ��� src=<%=request.getContextPath()%>/bbs/images/btg/move.gif width=23 class="gray" onmouseover="enable(this)" onmouseout="disable(this)">
</td>
</tr>
<tr>
<td bgColor="#EFF3F9" align=left>&nbsp;
<font face="����, Arial, Helvetica" size="2">���壺
    <select name="font" onChange="showfont(this.options[this.selectedIndex].value)">
	<option value="����" selected>����</option>
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
</select>&nbsp;&nbsp;�ֺţ�
<select name="size" onChange="showsize(this.options[this.selectedIndex].value)">
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3" selected>3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>	
</select>&nbsp;&nbsp;��ɫ��
<select name="color" onChange="showcolor(this.options[this.selectedIndex].value)">
	<option style=background-color:#ffffff;color:black value="black">&nbsp;Ĭ��ֵ&nbsp;</option>
	<option style=background-color:black;color:black value="black">[ ��ɫ ]</option>
	<option style=background-color:white;color:white value="white">[ ��ɫ ]</option>
	<option style=background-color:silver;color:silver value="silver">[ ���� ]</option>
	<option style=background-color:gray;color:gray value="gray">[ ��ɫ ]</option>
	<option style=background-color:red;color:red value="red">[ ��ɫ ]</option>
	<option style=background-color:yellow;color:yellow value="yellow">[ ��ɫ ]</option>
	<option style=background-color:gold;color:gold value="gold">[ ��ɫ ]</option>
	<option style=background-color:pink;color:pink value="pink">[ �ۺ� ]</option>
	<option style=background-color:green;color:green value="green">[ ��ɫ ]</option>
	<option style=background-color:orange;color:orange value="orange">[ ��ɫ ]</option>
	<option style=background-color:purple;color:purple value="purple">[ ��ɫ ]</option>
	<option style=background-color:blue;color:blue value="blue">[ ��ɫ ]</option>
	<option style=background-color:beige;color:beige value="beige">[ �׻� ]</option>
	<option style=background-color:brown;color:brown value="brown">[ ��ɫ ]</option>
	<option style=background-color:teal;color:teal value="teal">[ ���� ]</option>
	<option style=background-color:navy;color:navy value="navy">[ ���� ]</option>
	<option style=background-color:maroon;color:maroon value="maroon">[ ���� ]</option>
	<option style=background-color:limeGreen;color:limeGreen value="limeGreen">[ ���� ]</option>
	<option style=background-color:violet;color:violet value="violet">[ ���� ]</option>
</select></td>
</tr>
      <tr>
        <td bgColor="#f0F3Fa" noWrap vAlign="top" align="right"><font face="����, Arial, Helvetica" size="2"><B>���ݣ�</B><br>
        </FONT>
        </td>
        <td bgColor="#f0F3Fa">
	&nbsp;<textarea cols="80" rows="12" name="Message" wrap="VIRTUAL" onkeydown="ctlent(this.form)"></textarea>
	<br><a href=javascript:HighlightAll('PostTopic.Message')>���Ƶ�������</a> | <a href=javascript:checklength(document.PostTopic);>�鿴���³���</a>&gt;&gt;
	</td>
      </tr>
      <tr>
        <td bgColor="#A9B6CD" colspan="2" align="center"><input name="Submit" type="submit" value="<%=request.getParameter("action").equalsIgnoreCase("Reply")?"�ظ�":"����������"%>" onClick="return clckcntr();">

        &nbsp;<input name="Reset" type="reset" value="ȫ�����"></td>
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
<TD bgcolor="#f0F3Fa" nowrap align="right"><a href="#top"><img src="<%=request.getContextPath()%>/bbs/images/icon_go_up.gif" border="0" align="right" alt="����"></A></FONT></TD>
</TR>
</TABLE>

<TABLE border=0 width="95%" align="center" cellpadding="0" cellspacing="0">
<TR>
<TD align="center"><HR width="390" noshade size="1"></TD>
</TR>
<TR>
<TD align="center"><font color="#333333">&copy; ���İ�Ȩ���У���������������Ϣ�Ƽ����޹�˾  Ver.1.1.0.1</font>
<BR>����̳���۴��������߸����������<font color="#990000"><b> ��������������Ϣ�Ƽ����޹�˾ </b></font>�����޹�<BR>
<IMG src="" width=0 height=5></td>
</TR>
</TABLE>

</BODY>
</HTML>
