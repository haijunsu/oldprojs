
<%@ page contentType="text/html; charset=GB2312" %>
<%@page session="true"%>
<%@ page import="java.io.File.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.io.FileFilter"%>
<jsp:useBean id="beanDate" scope="session" class="com.htyz.util.BeanDateFormat" />


<html><head><title>note</title>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<link rel="stylesheet" href="product.css">
</head>
<%
//String lessonname= request.getParameter("bz");
String lessonname=request.getParameter("bz");
int i,ind=0;
String content="",tempstr="",openname="";

if(lessonname!=null)
{
    lessonname=new String(lessonname.getBytes("ISO8859_1"));
   
    byte[] b = new byte[1000];

    FileInputStream file = new FileInputStream(lessonname);
   // FileInputStream file = new FileInputStream("D:\\liu\\lesson\\lesson0005.html");
    
    DataInputStream indata = new DataInputStream(file);
    //outdata.write(c);
    try
        {
            do
            {

              content=indata.readLine();
              if(content!=null)
                 tempstr=tempstr+content;

            }
            while(content!= null);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    indata.close();
    file.close();
    tempstr=new String(tempstr.getBytes("ISO8859_1"));
	ind=lessonname.lastIndexOf("\\");
	int len=lessonname.length() ;
		//System.out.println("the str ind====="+ind);
	openname =lessonname.substring(ind+1,len-5);
    //System.out.println("the tempstr====="+tempstr);
}

%>

<body bgcolor="#FFFFFF" leftmargin="0" marginwidth="0" topmargin="4" marginheight="4" >

<!--<table width="100%" border="0" cellpadding="1" cellspacing="1" align="center">
  <tr>
    <td bgcolor="#0A51DC" width="66%" valign="bottom" class="title1" height="15">&nbsp;课程列表</td>
    <td width="34%" bgcolor="#3378FF" height="15">&nbsp;</td>
  </tr>
</table>
-->
<!-- AdSpace SpaceDesc=top -->
<form  name="Send" action="lessonsave.jsp" method="post">
  <table width="100%" border="0" cellpadding="2" cellspacing="0" align="center">
    <tr>
      <td bgcolor="e1e1e1" valign="bottom" width="5%">
	  <input type="image" src="images/save.gif" name="save" value="保存我的笔记" onClick="transfer()"></td>
     <td bgcolor="e1e1e1" width="5%"><a href='lessonmade.jsp' ><img name="Imagenew" border="0" src="images/new.gif" ></a>新建</td>
      <td bgcolor="e1e1e1" width="5%"><a href='openLesson.jsp' ><img name="Imageopen" border="0" src="images/open.gif" ></a>打开</td>
    </tr>

  </table>
<table border=1 cellpadding=0 cellspacing=0 width=100%><center>
<tr>
<input type=hidden name=lessonname>
<input type=hidden name=MText>
<td valign="top" align="left" width="100%">
<!--<object id="msg" width="100%" height=390 data="deditor.js" type="text/x-scriptlet"></object>-->

<!-- Styles -->
<link REL="stylesheet" TYPE="text/css" HREF="notestyles.css">

<SCRIPT>
<!--
var bLoad=false
var pureText=true
var bodyTag="<BODY MONOSPACE STYLE=\"font:10pt arial,sans-serif\">"
var bTextMode=false

public_description=new Editor

function Editor() {
  this.put_html=SetHtml;
  this.get_html=GetHtml;
}

function GetHtml() {
  if (bTextMode)
    return Composition.document.body.innerText
  else {
    cleanHtml();
    cleanHtml();
    return Composition.document.body.innerHTML;
  }
}

function public_SetMyHtml(sVal) {
  if (bTextMode)
    Composition.document.body.innerText=sVal
  else
    Composition.document.body.innerHTML=sVal
}

function SetHtml(sVal) {
  if (bTextMode)
    Composition.document.body.innerText=sVal
  else
    Composition.document.body.innerHTML=sVal
}

// -->
</script>

<div class="yToolbar" ID="ParaToolbar">
  <div class="TBHandle"></div>
  <select ID="FontName" class="TBGen" TITLE="字体名" LANGUAGE="javascript" onchange="format('fontname',this[this.selectedIndex].value);">
    <option class="heading">字体 </option>
    <option value="宋体">宋体</option>
    <option value="黑体">黑体</option>
    <option value="楷体">楷体 </option>
    <option value="隶书">隶书</option>
    <option value="幼圆">幼圆 </option>
    <option value="新宋体">新宋体</option>
    <option value="细明体">细明体</option>
    <option value="Arial">Arial </option>
    <option value="Arial Black">Arial Black </option>
    <option value="Arial Narrow">Arial Narrow </option>
    <option value="Bradley Hand ITC">Bradley Hand ITC </option>
    <option value="Brush Script MT">Brush Script MT </option>
    <option value="Century Gothic">Century Gothic </option>
    <option value="Comic Sans MS">Comic Sans MS </option>
    <option value="Courier">Courier </option>
    <option value="Courier New">Courier New </option>
    <option value="MS Sans Serif">MS Sans Serif </option>
    <option value="Script">Script </option>
    <option value="System">System </option>
    <option value="Times New Roman">Times New Roman </option>
    <option value="Viner Hand ITC">Viner Hand ITC </option>
    <option value="Verdana">Verdana </option>
    <option value="Wide Latin">Wide Latin </option>
    <option value="Wingdings">Wingdings </option>
  </select>
  <select ID="FontSize" class="TBGen" TITLE="字号大小" LANGUAGE="javascript" onchange="format('fontsize',this[this.selectedIndex].value);">
    <option class="heading">字号
    <option value="1">一号
    <option value="2">二号
    <option value="3">三号
    <option value="4">四号
    <option value="5">五号
    <option value="6">六号
    <option value="7">七号
  </select>
  <select ID="ForeColor" class="TBGen" TITLE="字体色彩" LANGUAGE="javascript" onchange="format('forecolor',this[this.selectedIndex].value);">
    <option class="heading">字色
    <option value="red">红色
    <option value="blue">蓝色
    <option value="green">绿色
    <option value="yellow">黄色
    <option value="pink">粉红
    <option value="cyan">天青
    <option value="white">白色
    <option value="darkred">深红
    <option value="darkblue">深蓝
    <option value="darkgreen">深绿
    <option value="darkyellow">土黄
    <option value="darkcyan">蓝绿
    <option value="gray">浅灰
    <option value="black">黑色
  </select>
  <select ID="BackColor" class="TBGen" TITLE="字体背景色彩" LANGUAGE="javascript" onchange="format('backcolor',this[this.selectedIndex].value);">
    <option class="heading">背景色
    <option value="red">红色
    <option value="blue">蓝色
    <option value="green">绿色
    <option value="yellow">黄色
    <option value="pink">粉红
    <option value="cyan">天青
    <option value="white">白色
    <option value="darkred">深红
    <option value="darkblue">深蓝
    <option value="darkgreen">深绿
    <option value="darkyellow">土黄
    <option value="darkcyan">蓝绿
    <option value="gray">浅灰
    <option value="black">黑色
  </select>
 <div class="TBSep"></div>
  <div class="Btn" TITLE="加粗" LANGUAGE="javascript" onclick="format('bold');">
    <img class="Ico" src="images/bold.gif">
  </div>
  <div class="Btn" TITLE="斜体" LANGUAGE="javascript" onclick="format('italic')">
    <img class="Ico" src="images/italic.gif">
  </div>
  <div class="Btn" TITLE="下划线" LANGUAGE="javascript" onclick="format('underline')">
    <img class="Ico" src="images/under.gif">
  </div>

</div>

<div class="yToolbar" ID="FormatToolbar">
  <div class="TBHandle"></div>
<div class="Btn" TITLE="剪切" LANGUAGE="javascript" onclick="format('cut')">
    <img class="Ico" src="images/cut.gif">
  </div>
  <div class="Btn" TITLE="复制" LANGUAGE="javascript" onclick="format('copy')">
    <img class="Ico" src="images/copy.gif">
  </div>
  <div class="Btn" TITLE="粘贴" LANGUAGE="javascript" onclick="format('paste')">
    <img class="Ico" src="images/paste.gif">
  </div>

  <div class="TBSep"></div>

  <div class="Btn" TITLE="左对齐" NAME="Justify" LANGUAGE="javascript" onclick="format('justifyleft')">
    <img class="Ico" src="images/aleft.gif">
  </div>
  <div class="Btn" TITLE="居中" NAME="Justify" LANGUAGE="javascript" onclick="format('justifycenter')">
    <img class="Ico" src="images/center.gif">
  </div>
  <div class="Btn" TITLE="右对齐" NAME="Justify" LANGUAGE="javascript" onclick="format('justifyright')">
    <img class="Ico" src="images/aright.gif">
  </div>
  <div class="TBSep"></div>
  <div class="Btn" TITLE="编号" LANGUAGE="javascript" onclick="format('insertorderedlist')">
    <img class="Ico" src="images/nlist.gif">
  </div>
  <div class="Btn" TITLE="项目符号" LANGUAGE="javascript" onclick="format('insertunorderedlist')">
    <img class="Ico" src="images/blist.gif">
  </div>
  <div class="TBSep"></div>
  <div class="Btn" TITLE="减少缩进量" LANGUAGE="javascript" onclick="format('outdent')">
    <img class="Ico" src="images/ileft.gif">
  </div>
  <div class="Btn" TITLE="增加缩进量" LANGUAGE="javascript" onclick="format('indent')">
    <img class="Ico" src="images/iright.gif" >
  </div>
            <div class="Btn" TITLE="插入图像" LANGUAGE="javascript" onclick="createImage()">
              <img class="Ico" src="images/images.jpg" width="22" height="22" >
            </div>
            <div class="Btn" TITLE="插入链接" LANGUAGE="javascript" onclick=" createLink()">
              <img class="Ico" src="images/link.jpg" width="22" height="22" >
            </div>

 <div ID="EditMode" class="TBGen" style="width:150" TITLE="Editing Mode">
    <input type=checkbox name="switchMode" LANGUAGE="javascript" onclick="setMode(switchMode.checked)">
查看HTML源文件
  </div>
</div>

<IFRAME class="Composition" width="100%" ID="Composition" height="320">
</IFRAME>

<SCRIPT>
<!--
Composition.document.open();
Composition.document.write(bodyTag);
Composition.document.close();
Composition.document.designMode="On";
Composition.focus();
setTimeout("Composition.focus()",0)
// -->
</SCRIPT>

<script LANGUAGE="Javascript"><!--

//Constants.
SEP_PADDING = 5
HANDLE_PADDING = 7

var yToolbars = new Array();  // Array of all toolbars.

// Initialize everything when the document is ready
var YInitialized = false;
function document.onreadystatechange() {
  if (YInitialized) return;
  YInitialized = true;

  var i, s, curr;

  // Find all the toolbars and initialize them.
  for (i=0; i<document.body.all.length; i++) {
    curr=document.body.all[i];
    if (curr.className == "yToolbar") {
      if (! InitTB(curr)) {
        alert("Toolbar: " + curr.id + " failed to initialize. Status: false");
      }
      yToolbars[yToolbars.length] = curr;
    }
  }

  //Lay out the page, set handler.
  DoLayout();
  window.onresize = DoLayout;


  Composition.document.open()
  Composition.document.write("<BODY MONOSPACE STYLE=\"font:10pt arial,sans-serif\"></body>");
  Composition.document.close()
  Composition.document.designMode="On"
  setTimeout("Composition.focus()",0)
  //begin add by liu-ag 2002-8-14
  Composition.document.body.innerHTML='<%=tempstr%>';
 //end add by liu-ag 2002-8-14
}

// Initialize a toolbar button
function InitBtn(btn) {
  btn.onmouseover = BtnMouseOver;
  btn.onmouseout = BtnMouseOut;
  btn.onmousedown = BtnMouseDown;
  btn.onmouseup = BtnMouseUp;
  btn.ondragstart = YCancelEvent;
  btn.onselectstart = YCancelEvent;
  btn.onselect = YCancelEvent;
  btn.YUSERONCLICK = btn.onclick;
  btn.onclick = YCancelEvent;
  btn.YINITIALIZED = true;
  return true;
}

//Initialize a toolbar.
function InitTB(y) {
  // Set initial size of toolbar to that of the handle
  y.TBWidth = 0;

  // Populate the toolbar with its contents
  if (! PopulateTB(y)) return false;

  // Set the toolbar width and put in the handle
  y.style.posWidth = y.TBWidth;

  return true;
}


// Hander that simply cancels an event
function YCancelEvent() {
  event.returnValue=false;
  event.cancelBubble=true;
  return false;
}

// Toolbar button onmouseover handler
function BtnMouseOver() {
  if (event.srcElement.tagName != "IMG") return false;
  var image = event.srcElement;
  var element = image.parentElement;

  // Change button look based on current state of image.
  if (image.className == "Ico") element.className = "BtnMouseOverUp";
  else if (image.className == "IcoDown") element.className = "BtnMouseOverDown";

  event.cancelBubble = true;
}

// Toolbar button onmouseout handler
function BtnMouseOut() {
  if (event.srcElement.tagName != "IMG") {
    event.cancelBubble = true;
    return false;
  }

  var image = event.srcElement;
  var element = image.parentElement;
  yRaisedElement = null;

  element.className = "Btn";
  image.className = "Ico";

  event.cancelBubble = true;
}

// Toolbar button onmousedown handler
function BtnMouseDown() {
  if (event.srcElement.tagName != "IMG") {
    event.cancelBubble = true;
    event.returnValue=false;
    return false;
  }

  var image = event.srcElement;
  var element = image.parentElement;

  element.className = "BtnMouseOverDown";
  image.className = "IcoDown";

  event.cancelBubble = true;
  event.returnValue=false;
  return false;
}

// Toolbar button onmouseup handler
function BtnMouseUp() {
  if (event.srcElement.tagName != "IMG") {
    event.cancelBubble = true;
    return false;
  }

  var image = event.srcElement;
  var element = image.parentElement;

  if (element.YUSERONCLICK) eval(element.YUSERONCLICK + "anonymous()");

  element.className = "BtnMouseOverUp";
  image.className = "Ico";

  event.cancelBubble = true;
  return false;
}

// Populate a toolbar with the elements within it
function PopulateTB(y) {
  var i, elements, element;

  // Iterate through all the top-level elements in the toolbar
  elements = y.children;
  for (i=0; i<elements.length; i++) {
    element = elements[i];
    if (element.tagName == "SCRIPT" || element.tagName == "!") continue;

    switch (element.className) {
    case "Btn":
      if (element.YINITIALIZED == null) {
	if (! InitBtn(element)) {
	  alert("Problem initializing:" + element.id);
	  return false;
	}
      }

      element.style.posLeft = y.TBWidth;
      y.TBWidth += element.offsetWidth + 1;
      break;

    case "TBGen":
      element.style.posLeft = y.TBWidth;
      y.TBWidth += element.offsetWidth + 1;
      break;

    case "TBSep":
      element.style.posLeft = y.TBWidth + 2;
      y.TBWidth += SEP_PADDING;
      break;

    case "TBHandle":
      element.style.posLeft = 2;
      y.TBWidth += element.offsetWidth + HANDLE_PADDING;
      break;

    default:
      alert("Invalid class: " + element.className + " on Element: " + element.id + " <" + element.tagName + ">");
      return false;
    }
  }

  y.TBWidth += 1;
  return true;
}

function DebugObject(obj) {
  var msg = "";
  for (var i in TB) {
    ans=prompt(i+"="+TB[i]+"\n");
    if (! ans) break;
  }
}

// Lay out the docked toolbars
function LayoutTBs() {
  NumTBs = yToolbars.length;

  // If no toolbars we're outta here
  if (NumTBs == 0) return;

  //Get the total size of a TBline.
  var i;
  var ScrWid = (document.body.offsetWidth) - 6;
  var TotalLen = ScrWid;
  for (i = 0 ; i < NumTBs ; i++) {
    TB = yToolbars[i];
    if (TB.TBWidth > TotalLen) TotalLen = TB.TBWidth;
  }

  var PrevTB;
  var LastStart = 0;
  var RelTop = 0;
  var LastWid, CurrWid;

  //Set up the first toolbar.
  var TB = yToolbars[0];
  TB.style.posTop = 0;
  TB.style.posLeft = 0;

  //Lay out the other toolbars.
  var Start = TB.TBWidth;
  for (i = 1 ; i < yToolbars.length ; i++) {
    PrevTB = TB;
    TB = yToolbars[i];
    CurrWid = TB.TBWidth;

    if ((Start + CurrWid) > ScrWid) {
      //TB needs to go on next line.
      Start = 0;
      LastWid = TotalLen - LastStart;
    }
    else {
      //Ok on this line.
      LastWid = PrevTB.TBWidth;
      //RelTop -= TB.style.posHeight;
      RelTop -= TB.offsetHeight;
    }

    //Set TB position and LastTB width.
    TB.style.posTop = RelTop;
    TB.style.posLeft = Start;
    PrevTB.style.width = LastWid;

    //Increment counters.
    LastStart = Start;
    Start += CurrWid;
  }

  //Set width of last toolbar.
  TB.style.width = TotalLen - LastStart;

  //Move everything after the toolbars up the appropriate amount.
  i--;
  TB = yToolbars[i];
  var TBInd = TB.sourceIndex;
  var A = TB.document.all;
  var item;
  for (i in A) {
    item = A.item(i);
    if (! item) continue;
    if (! item.style) continue;
    if (item.sourceIndex <= TBInd) continue;
    if (item.style.position == "absolute") continue;
    item.style.posTop = RelTop;
  }
}

//Lays out the page.
function DoLayout() {
  LayoutTBs();
}

// Check if toolbar is being used when in text mode
function validateMode() {
  if (! bTextMode) return true;
  alert("Please uncheck the \"View HTML source\" checkbox to use the toolbars");
  Composition.focus();
  return false;
}

//Formats text in composition.
function format(what,opt) {
  if (!validateMode()) return;

  if (opt=="removeFormat") {
    what=opt;
    opt=null;
  }

  if (opt==null) Composition.document.execCommand(what);
  else Composition.document.execCommand(what,"",opt);

  pureText = false;
  Composition.focus();
}

//Switches between text and html mode.
function setMode(newMode) {
  bTextMode = newMode;
  var cont;

  if (bTextMode) {
    cleanHtml();
    cleanHtml();

    cont=Composition.document.body.innerHTML;
    Composition.document.body.innerText=cont;
  } else {
    cont=Composition.document.body.innerText;
    Composition.document.body.innerHTML=cont;
    //Composition.document.body.innerHTML="aaaaaaaa";
    //Composition.document.writeln("bbbbbb");
  }

  Composition.focus();
}

//Finds and returns an element.
function getEl(sTag,start) {
  while ((start!=null) && (start.tagName!=sTag)) start = start.parentElement;
  return start;
}

function createLink() {
  if (!validateMode()) return;

  var isA = getEl("A",Composition.document.selection.createRange().parentElement());
  var str=prompt("写出连接URL:", isA ? isA.href : "http:\/\/");

  if ((str!=null) && (str!="http://")) {
    if (Composition.document.selection.type=="None") {
      var sel=Composition.document.selection.createRange();
      sel.pasteHTML("<A HREF=\""+str+"\">"+str+"</A> ");
      sel.select();
    }
    else format("CreateLink",str);
  }
  else Composition.focus();
}

function createImage() {
  if (!validateMode()) return;

  var isA = getEl("A",Composition.document.selection.createRange().parentElement());
  var str=prompt(" (写出图像URL地址:)", isA ? isA.href : "http:\/\/");
  if ((str!=null) && (str!="http://")) {
    if (Composition.document.selection.type=="None") {
      var sel=Composition.document.selection.createRange();
      sel.pasteHTML("<img src=\""+str+"\">");
      sel.select();
    }
    else format("CreateImage",str);
  }
  else Composition.focus();
}

//Sets the text color.
function foreColor() {
  if (! validateMode()) return;
  var arr = showModalDialog("/ym/ColorSelect?3", "", "font-family:Verdana; font-size:12; dialogWidth:30em; dialogHeight:35em");
  if (arr != null) format('forecolor', arr);
  else Composition.focus();
}

//Sets the background color.
function backColor() {
  if (!validateMode()) return;
  var arr = showModalDialog("/ym/ColorSelect?3", "", "font-family:Verdana; font-size:12; dialogWidth:30em; dialogHeight:35em");
  if (arr != null) format('backcolor', arr);
  else Composition.focus()
}

function cleanHtml() {
  var fonts = Composition.document.body.all.tags("FONT");
  var curr;
  for (var i = fonts.length - 1; i >= 0; i--) {
    curr = fonts[i];
    if (curr.style.backgroundColor == "#ffffff") curr.outerHTML = curr.innerHTML;
  }
}

function getPureHtml() {
  var str = "";

  var paras = Composition.document.body.all.tags("P");
  if (paras.length > 0) {
    for (var i=paras.length-1; i >= 0; i--) str = paras[i].innerHTML + "\n" + str;
  } else {
    str = Composition.document.body.innerHTML;
  }
  return str;
}

function transfer()
{
	var value1 = "" ;
	var strname="";
	var value = Composition.document.body.innerHTML;
	strname='<%=openname%>';
	for (var i = 0; i< value.length; i ++)
	{
		while (value.charAt(i) == "\\")
			i ++ ;
		if (value.charAt(i) == "'")
			value1 = value1 + '\"' ;
		else
			value1 = value1 + value.charAt(i) ;
	}
        var re=/\n/g;
        var bb=/\r\r/g ;
        var cc=/\r/g ;
        var name;
        //alert(value1.replace(re,"jcm"));
       value2=value1.replace(re,"\r");
       value2 =value2.replace(bb,"</p>");
       value2 =value2.replace(cc,"<br>");
       self.document.Send.MText.value = value2 ;
	   //alert("the name===="+strname)
	   if(strname.length==0)
         name=prompt("请输入保存文件的名字","lesson0001");
	   else
		 name=strname ;
       self.document.Send.lessonname.value=name;

}


// Local Variables:
// c-basic-offset: 2
// End:

-->

</script>

</td></tr></center>


</table>
<input type="hidden" name="title" value=note>
<input type="hidden" name="part" value=/course/win2k/>
<table width="100%" border="0" cellpadding="2" cellspacing="0" align="center">
    <tr>
      <td bgcolor="e1e1e1" valign="bottom">
	  <input type="image" src="images/save.gif" name="save" value="保存我的笔记" onClick="transfer()"></td>
	</tr>
  </table>
</form>

</body>

</html>


