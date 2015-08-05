<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<% 
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
%>
<META HTTP-EQUIV="Cache-Control" CONTENT="No-cache">
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css">
<TITLE><bean:message key="menus.title"/></TITLE>
<STYLE type=text/css>#menubar {
	BACKGROUND: rgb(212,208,200); FONT: menu; CURSOR: default; POSITION: relative
}
#menubar .root {
	BORDER-RIGHT: rgb(212,208,200) 1px solid; PADDING-RIGHT: 2pt; BORDER-TOP: rgb(212,208,200) 1px solid; PADDING-LEFT: 2pt; BORDER-LEFT: rgb(212,208,200) 1px solid; BORDER-BOTTOM: rgb(212,208,200) 1px solid
}
#menubar TABLE {
	BORDER-RIGHT: gray 2px solid; PADDING-RIGHT: 0pt; BORDER-TOP: #eeeeee 2px solid; DISPLAY: none; PADDING-LEFT: 0pt; BACKGROUND: rgb(212,208,200); PADDING-BOTTOM: 0pt; MARGIN: 0pt; FONT: menu; BORDER-LEFT: #eeeeee 2px solid; PADDING-TOP: 0pt; BORDER-BOTTOM: gray 2px solid; POSITION: absolute
}
#menubar TABLE TD {
	PADDING-RIGHT: 15pt; PADDING-LEFT: 12pt; PADDING-BOTTOM: 2pt; MARGIN: 0pt; PADDING-TOP: 2pt
}
#menubar .highlight {
	BACKGROUND: #08246B; COLOR: white;
}
#menubar .disabledhighlight {
	BACKGROUND: rgb(212,208,200); COLOR: gray
}
#menubar #break {
	COLOR: gray
}
#menubar .disabled {
	COLOR: gray
}
#menubar #break {
	PADDING-RIGHT: 0pt; PADDING-LEFT: 0pt; PADDING-BOTTOM: 0pt; MARGIN: 0pt; PADDING-TOP: 0pt
}
#menubar SPAN.more {
	PADDING-RIGHT: 0pt; PADDING-LEFT: 0pt; LEFT: 14pt; FLOAT: right; PADDING-BOTTOM: 0pt; MARGIN: 0pt; FONT: 9pt webdings; WIDTH: 0.8em; PADDING-TOP: 0pt; POSITION: relative; TOP: -2pt; HEIGHT: 9pt
}
#menubar .TBHandle {
    BACKGROUND-COLOR: menu;
    BORDER-LEFT: buttonhighlight 1px solid;
    BORDER-RIGHT: buttonshadow 1px solid;
    BORDER-TOP:	buttonhighlight	1px solid;
    FONT-SIZE: 1px;
    HEIGHT: 22px;
    POSITION: absolute;
    TOP: 1px;
    WIDTH: 3px
}
#menubar .yToolbar {
    BACKGROUND-COLOR: menu;
    BORDER-BOTTOM: buttonshadow	1px solid;
    BORDER-LEFT: buttonhighlight 1px solid;
    BORDER-RIGHT: buttonshadow 1px solid;
    BORDER-TOP:	buttonhighlight	1px solid;
    HEIGHT: 27px;
    LEFT: 0px;
    POSITION: relative;
    TOP: 0px;    
}
.handbtn {
	BACKGROUND: buttonface; BORDER-BOTTOM: buttonshadow 1px solid; BORDER-LEFT: buttonhighlight 1px solid; BORDER-RIGHT: buttonshadow 1px solid; BORDER-TOP: buttonhighlight 1px solid; HEIGHT: 21px; WIDTH: 3px
}
.sepbtn1 {
	BORDER-LEFT: buttonshadow 1px ridge; BORDER-RIGHT: buttonhighlight 1px solid; WIDTH: 2px
}

</STYLE>
<SCRIPT LANGUAGE=JAVASCRIPT>
<!-- 
if (top.location !== self.location) {
top.location=self.location;
}

//-->
</SCRIPT>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" scroll="no">
<table name="table1" height="100%" id="table1" width='100%' cellpadding='0' cellspacing='0' border='0''>
<tr>
<td>

<%
	String strAppsys = (String)session.getAttribute("appsys");
	String strPurview = (String)session.getAttribute("purview");
	String strReload = (String)session.getAttribute("menuReload");
	String strLocaleCode = (String)session.getAttribute("localeCode");
	String strContextPath = request.getContextPath();
	if (strAppsys == null) {
%>
<FONT color="red"><bean:message key="error.logonRequired"/></FONT>
<%
	} else {
	if (strAppsys == null) strAppsys = "98";
	if (strPurview == null) strPurview = "255";
	if (strReload == null) strReload = "true";
	boolean isReload = strReload.equals("true");
	session.setAttribute("menuReload", "false");
	if (strLocaleCode == null) strLocaleCode = "1";
%>
<jsp:useBean id="menuBean" scope="session" class="com.idn.menu.MenusBean"/>
<jsp:setProperty name="menuBean" property="reload" value="<%=isReload%>"/>
<jsp:setProperty name="menuBean" property="appsys" value="<%=strAppsys%>"/>
<jsp:setProperty name="menuBean" property="purview" value="<%=strPurview%>"/>
<jsp:setProperty name="menuBean" property="contextPath" value="<%=strContextPath%>"/>
<jsp:setProperty name="menuBean" property="localeCode" value="<%=strLocaleCode%>"/>
<script language=javascript>

//数组名+次序号码

//数组内容第一个值为标题

//"样式名称","字体颜色","名称","连接","目标窗口"
	<bean:write name="menuBean" property="menuItems" filter="false"/>

	var childActive = null 

    var menuActive = null

    var lastHighlight = null

    var active = false

   

    function getReal(el) {

      // Find a table cell element in the parent chain */

      temp = el

      while ((temp!=null) && (temp.tagName!="TABLE") && (temp.className!="root") && (temp.id!="menuBar")) {

        if (temp.tagName=="TD")

          el = temp

        temp = temp.parentElement

      }

      return el

    }



    function raiseMenu(el) {

      el.style.borderLeft = "1px #08246B solid"

      el.style.borderTop = "1px #08246B solid"

      el.style.borderRight = "1px #08246B solid"

      el.style.borderBottom = "1px #08246B solid"

      el.style.background = "#B5BED6"

    }



    function clearHighlight(el) {

      if (el==null) return

      el.style.borderRight = "1px lightgrey solid"

      el.style.borderBottom = "1px lightgrey solid"

      el.style.borderTop = "1px lightgrey solid"

      el.style.borderLeft = "1px lightgrey solid" 

      el.style.background = "rgb(212,208,200)"

    }



    function sinkMenu(el) {

      el.style.borderRight = "1px #EEEEEE solid"

      el.style.borderBottom = "1px #EEEEEE solid"

      el.style.borderTop = "1px gray solid"

      el.style.borderLeft = "1px gray solid"

      el.style.background = "rgb(212,208,200)"

    }



    function menuHandler(menuItem) {

      // Write generic menu handlers here!

      // Returning true collapses the menu. Returning false does not collapse the menu

      return true

    }



    function getOffsetPos(which,el,tagName) {

      var pos = 0 // el["offset" + which]

      while (el.tagName!=tagName) {

        pos+=el["offset" + which]

        el = el.offsetParent

      }

      return pos	

    }



    function getRootTable(el) {

      el = el.offsetParent

      if (el.tagName=="TR") 

        el = el.offsetParent

      return el

    }



    function getElement(el,tagName) {

      while ((el!=null) && (el.tagName!=tagName) )

        el = el.parentElement

      return el

    }



    function processClick() {
      var el = getReal(event.srcElement);
      if ((getRootTable(el).id=="menuBar") && (active)) {        

        cleanupMenu(menuActive)

        clearHighlight(menuActive)

        active=false

        lastHighlight=null

        doHighlight(el)

      }

      else {

        if ((el.className=="root") || (!menuHandler(el)))

          doMenuDown(el)

        else {

          if (el._childItem==null) 

            el._childItem = getChildren(el)

          if (el._childItem!=null)  return;

          if ((el.id!="break") && (el.className!="disabled") && (el.className!="disabledhighlight") && (el.className!="clear"))  {

            if (menuHandler(el)) {

              cleanupMenu(menuActive)

              clearHighlight(menuActive)

              active=false

              lastHighlight=null

            }

          }

        }

      }
      	

    }



    function getChildren(el) {

      var tList = el.children.tags("TABLE")

      var i = 0

      while ((i<tList.length) && (tList[i].tagName!="TABLE"))

        i++

      if (i==tList.length)

        return null

      else

        return tList[i]

    }



    function doMenuDown(el) {
//	  parent.myframe.rows="60%,*";

      if (el._childItem==null) 

        el._childItem = getChildren(el)

      if ((el._childItem!=null) && (el.className!="disabled") && (el.className!="disabledhighlight")) {

        // Performance Optimization - Cache child element

        ch = el._childItem

        if (ch.style.display=="block") {

          removeHighlight(ch.active)

          return

        }

        ch.style.display = "block"

        if (el.className=="root") {

          ch.style.pixelTop = el.offsetHeight + el.offsetTop + 2

          ch.style.pixelLeft = el.offsetLeft + 1

	  if (ch.style.pixelWidth==0)

            ch.style.pixelWidth = ch.rows[0].offsetWidth+50

          sinkMenu(el)

          active = true

          menuActive = el

        } else {

          childActive = el

          ch.style.pixelTop = getOffsetPos("Top",el,"TABLE") -3 // el.offsetTop + el.offsetParent.offsetTop - 3

          ch.style.pixelLeft = el.offsetLeft + el.offsetWidth

	  if (ch.style.pixelWidth==0)

            ch.style.pixelWidth = ch.offsetWidth+50

        }     

      }

    }

 

    function doHighlight(el) {

      el = getReal(el)

      if ("root"==el.className) {

        if ((menuActive!=null) && (menuActive!=el)) {

          clearHighlight(menuActive)

        }

        if (!active) {

          raiseMenu(el)

        }          

        else 

          sinkMenu(el)

        if ((active) && (menuActive!=el)) {

          cleanupMenu(menuActive)          

          doMenuDown(el)

        }

        menuActive = el  

        lastHighlight=null

      }

      else {

        if (childActive!=null) 

          if (!childActive.contains(el)) 

            closeMenu(childActive, el)    



        if (("TD"==el.tagName) && ("clear"!=el.className)) {

          var ch = getRootTable(el)         

          if (ch.active!=null) {

            if (ch.active!=el) {

              if (ch.active.className=="disabledhighlight")  

                ch.active.className="disabled"

              else

                ch.active.className=""

              }

            }

          ch.active = el

          lastHighlight = el

          if ((el.className=="disabled") || (el.className=="disabledhighlight") || (el.id=="break")) 

            el.className = "disabledhighlight"

          else {

            if (el.id!="break") {

              el.className = "highlight"

              if (el._childItem==null) 

                el._childItem = getChildren(el)

              if (el._childItem!=null) {

                doMenuDown(el)

              }

            }  

          }

        }

      }

    }



    function removeHighlight(el) {

      if (el!=null)

        if ((el.className=="disabled") || (el.className=="disabledhighlight"))  

          el.className="disabled"

        else

          el.className=""

    }



    function cleanupMenu(el) {

      if (el==null) return

//	  parent.myframe.rows="25,*";

      for (var i = 0; i < el.all.length; i++) {

        var item = el.all[i]

        if (item.tagName=="TABLE")

         item.style.display = ""

        removeHighlight(item.active)

        item.active=null

      }

    }





    function closeMenu(ch, el) {

      var start = ch

      while (ch.className!="root") {

          ch = ch.parentElement

          if (((!ch.contains(el)) && (ch.className!="root"))) {

            start=ch

          }

      }

      cleanupMenu(start)
      

    }

 

    function checkMenu() {      

      if (document.all.menuBar==null) return

      if ((!document.all.menuBar.contains(event.srcElement)) && (menuActive!=null)) {

        clearHighlight(menuActive)

        closeMenu(menuActive)

        active = false

        menuActive=null

        choiceActive = null

      }

    }



    function doCheckOut() {

      var el = event.toElement      

      if ((!active) && (menuActive!=null) && (!menuActive.contains(el))) {

        clearHighlight(menuActive)

        menuActive=null

      }

    }





    function processKey() {

      if (active) {

        switch (event.keyCode) {
        

         case 13: lastHighlight.click(); break;

         case 39:  // right

           if ((lastHighlight==null) || (lastHighlight._childItem==null)) {

             var idx = menuActive.cellIndex

//             if (idx==menuActive.offsetParent.cells.length-2)

             if (idx==getElement(menuActive,"TR").cells.length-1)

               idx = 0

             else

               idx++

             newItem = getElement(menuActive,"TR").cells[idx]

           } else

           {

             newItem = lastHighlight._childItem.rows[0].cells[0]

           }

           doHighlight(newItem)

           break; 

         case 37: //left

           if ((lastHighlight==null) || (getElement(getRootTable(lastHighlight),"TR").id=="menuBar")) {

             var idx = menuActive.cellIndex

             if (idx==0)

               idx = getElement(menuActive,"TR").cells.length-1

             else

               idx--

             newItem = getElement(menuActive,"TR").cells[idx]

           } else

           {

             newItem = getElement(lastHighlight,"TR")

             while (newItem.tagName!="TD")

               newItem = newItem.parentElement

           }

           doHighlight(newItem)

           break; 

         case 40: // down

            if (lastHighlight==null) {

              itemCell = menuActive._childItem

              curCell=0

              curRow = 0

            }

            else {

              itemCell = getRootTable(lastHighlight)

              if (lastHighlight.cellIndex==getElement(lastHighlight,"TR").cells.length-1) {

                curCell = 0

                curRow = getElement(lastHighlight,"TR").rowIndex+1

                if (getElement(lastHighlight,"TR").rowIndex==itemCell.rows.length-1)

                  curRow = 0

              } else {

                curCell = lastHighlight.cellIndex+1

                curRow = getElement(lastHighlight,"TR").rowIndex

              }

            }

            doHighlight(itemCell.rows[curRow].cells[curCell])

            break;

        case 27: //ESC
        	processClick();
        	break;
         case 38: // up

            if (lastHighlight==null) {

              itemCell = menuActive._childItem

              curRow = itemCell.rows.length-1

              curCell= itemCell.rows[curRow].cells.length-1



            }

            else {

              itemCell = getRootTable(lastHighlight)

              if (lastHighlight.cellIndex==0) {

                curRow = getElement(lastHighlight,"TR").rowIndex-1

                if (curRow==-1)

                  curRow = itemCell.rows.length-1

                curCell= itemCell.rows[curRow].cells.length-1



              } else {

                curCell = lastHighlight.cellIndex - 1

                curRow = getElement(lastHighlight,"TR").rowIndex

              }

            }

            doHighlight(itemCell.rows[curRow].cells[curCell])

            break;

           if (lastHighlight==null) {

              curCell = menuActive._childItem

              curRow = curCell.rows.length-1

            }

            else {

              curCell = getRootTable(lastHighlight)

              if (getElement(lastHighlight,"TR").rowIndex==0)

                curRow = curCell.rows.length-1

              else

                curRow = getElement(lastHighlight,"TR").rowIndex-1

            }

            doHighlight(curCell.rows[curRow].cells[0])

            break;

        }
        

      }
 
    }





function make_menu() {

	j=1;

	while(eval("window.OutBarFolder"+j))

		j++;
		
	if (j == 1) {
		document.write("<center><font color=red> 无菜单可供显示，你需要重新登录！<br>");
		document.write("如果问题依然存在，请与管理员联系</font></center>");
//		go(1,"about:blank",main);
		return;
	}

	i=1;
//	document.write("<div id=menuBlock style='position:absolute; visibility:show; left:0px; top:0px; z-index:5'>");
	document.write("<table width='100%' cellpadding='0' cellspacing='0' border='0' style='BACKGROUND: buttonface; BORDER-BOTTOM: buttonshadow 1px solid; BORDER-LEFT: buttonhighlight 1px solid; BORDER-RIGHT: buttonshadow 1px solid; BORDER-TOP: buttonhighlight 1px solid;'>");

	document.write("<tr><TD><SPAN class=handbtn></SPAN></TD><td>");

	document.write("<TABLE ID=menuBar ONSELECTSTART='return false' ONCLICK='processClick();' ONMOUSEOVER='doHighlight(event.toElement)' ONMOUSEOUT='doCheckOut()' ONKEYDOWN='processKey()'><TR>");
	while(i<j)

	{

		Folder=eval("OutBarFolder"+i)

        document.write("<TD NOWRAP CLASS=root>"+Folder[0]+"<TABLE CELLSPACING=0 CELLPADDING=0>");

		MakeItems(Folder);

        document.write("</TABLE>");

		i++;

	}

document.write("</TD></TR></TABLE>");

document.write("</td><td width=100% onClick='processClick()'></td></tr></table>");
//document.write("</DIV>");



}



function MakeItems(Folder)

{

	var items=0;

	while(Folder[items+1])

		items+=5;

	items/=5;

	for(var i=1;i<items*5;i+=5)

	{
		document.write("<TR><TD NOWRAP"+((Folder[i+0]=="none")?"":" ID='"+Folder[i+0]+"'")+((Folder[i+3]=="none")?"":" onclick=\"go(1,'"+Folder[i+3]+"','"+Folder[i+4]+"')\"")+">"+((Folder[i+1]=="default")?"":"<font color="+Folder[i+1]+">")+Folder[i+2]+((Folder[i+1]=="default")?"":"</font>")+"</TD></TR>");
	}	

}



function go(i,iurl,itarget) {

	switch (i) 

	{

		case 1 : window.open(iurl, itarget);break;
			//parent.location=iurl;break; //返回首页

		case 2 : top.main.location='login.htm';break;  //登录

		case 3 : top.main.location='shenqing.htm';break;  //注册

		case 4 : top.main.location='addnew.asp';break;   //增加新贴

		case 5 : top.main.location='index1.asp';break;   //第一页

		case 6 : //上一页

			var obj=top.main

			var str=obj.location.href;	

			if(str.indexOf("index1")>0)

				obj.location="index1.asp@page="+obj.document.all("ppage").value;

			else

				obj.location="index1.asp";

			break;   

		case 7 : //下一页

			var obj=top.main

			var str=obj.location.href;	

			if(str.indexOf("index1")>0)

				obj.location="index1.asp@page="+obj.document.all("npage").value;

			else

				obj.location="index1.asp";

			break;   

		case 8 :  //最后一页

			var obj=top.main

			var str=obj.location.href;	

			if(str.indexOf("index1")>0)

				obj.location="index1.asp@page="+obj.document.all("epage").value;

			else

				obj.location="index1.asp";

			break;   

		case 9: top.main.location='editinfo.asp';break;

		case 10: top.main.location='quit.asp';break;

		case 11: top.main.location='userinfo.asp';break;			

		case 12: 

			var newwin=top.open("");

			newwin.focus();

			break;

	}

}

function setVariables() {
	if (navigator.appname == "Netscape") {
		v=".top=";
		dS="document.";
		sD = "";
		y="window.pageYOffset";
	} else {
		v=".pixelTop=";
		dS="";
		sD = ".style";
		y="document.body.scrollTop";
	}
}

function checkLocation() {
	object = "menuBlock";
	yy=eval(y);
	eval(dS + object + sD + v + yy);
	setTimeout("checkLocation()", 200);
}

make_menu();
setVariables();
//checkLocation();

</script>
</td>
</tr>
<tr>
<td height=100%>
<IFRAME SRC="<%=request.getContextPath()%>/main.jsp" ID="main" NAME="main" BORDER="0" SCROLLING="AUTO" WIDTH="100%" HEIGHT="100%"></IFRAME>
</td>

<%    } %>
</tr>
</table>

</BODY>
</HTML>
