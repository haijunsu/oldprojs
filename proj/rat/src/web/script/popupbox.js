/******************************************
* Popup Box- By Jim Silver @ jimsilver47@yahoo.com
* Visit http://www.dynamicdrive.com/ for full source code
* This notice must stay intact for use
* Modified by Haijun Su
******************************************/

var ns4=document.layers
var ie4=document.all
var ns6=document.getElementById&&!document.all

//drag drop function for NS 4////
/////////////////////////////////

var dragswitch=0
var nsx
var nsy
var nstemp
var isPopupBoxShow = false;

window.onresize=function() {
	if (isPopupBoxShow) {
		hidebox();
		showbox();
	}
}

function drag_dropns(name){
	if (!ns4)
		return
	temp=eval(name)
	temp.captureEvents(Event.MOUSEDOWN | Event.MOUSEUP)
	temp.onmousedown=gons
	temp.onmousemove=dragns
	temp.onmouseup=stopns
}

function gons(e){
	temp.captureEvents(Event.MOUSEMOVE)
	nsx=e.x
	nsy=e.y
}
function dragns(e){
	if (dragswitch==1){
	temp.moveBy(e.x-nsx,e.y-nsy)
	return false
}
}

function stopns(){
	temp.releaseEvents(Event.MOUSEMOVE)
}

//drag drop function for ie4+ and NS6////
/////////////////////////////////


function drag_drop(e){
	if (ie4&&dragapproved){
		crossobj.style.left=tempx+event.clientX-offsetx
		crossobj.style.top=tempy+event.clientY-offsety
		hideShowCovered();
		return false
	}
	else if (ns6&&dragapproved){
		crossobj.style.left=tempx+e.clientX-offsetx+"px"
		crossobj.style.top=tempy+e.clientY-offsety+"px"
		hideShowCovered();
		return false
	}
}

function initializedrag(e){
	crossobj=ns6? document.getElementById("showimage") : document.all.showimage
	var firedobj=ns6? e.target : event.srcElement
	var topelement=ns6? "html" : document.compatMode && document.compatMode!="BackCompat"? "documentElement" : "body"
	while (firedobj.tagName!=topelement.toUpperCase() && firedobj.id!="dragbar"){
		firedobj=ns6? firedobj.parentNode : firedobj.parentElement
	}

	if (firedobj.id=="dragbar"){
		offsetx=ie4? event.clientX : e.clientX
		offsety=ie4? event.clientY : e.clientY

		tempx=parseInt(crossobj.style.left)
		tempy=parseInt(crossobj.style.top)

		dragapproved=true
		document.onmousemove=drag_drop
	}
}
document.onmouseup=new Function("dragapproved=false")

////drag drop functions end here//////

function hidebox(){
	crossobj=ns6? document.getElementById("showimage") : document.all.showimage
	if (ie4||ns6) {
		crossobj.style.visibility="hidden"
	} else if (ns4) {
		document.showimage.visibility="hide"
	}
	isPopupBoxShow = false;
	hideShowCovered();
}

function showbox(){
	crossobj=ns6? document.getElementById("showimage") : document.all.showimage
	crossobjTable=ns6? document.getElementById("popupboxTable") : document.all.popupboxTable
	if (!isPopupBoxShow) {
		//popupbox position
		iframeCwcIframe = ns6? document.getElementById("cwcIframe") : document.all.cwcIframe
		if (iframeCwcIframe != "undifined") {
			var p = getAbsolutePos(iframeCwcIframe);
			crossobj.style.left = p.x
			crossobj.style.top = p.y + iframeCwcIframe.offsetHeight + 1
			crossobj.style.width = iframeCwcIframe.offsetWidth
			crossobjTable.style.width = iframeCwcIframe.offsetWidth
		}
		if (ie4||ns6)
			crossobj.style.visibility="visible"
		else if (ns4)
			document.showimage.visibility="show"
		isPopupBoxShow = true;
	}
	hideShowCovered();
}


function writeDiv(divCtrl, text) {
    if (document.layers) {
        divCtrl.document.write(text);
        divCtrl.document.close();
    } else if (document.all) {
        divCtrl.innerHTML = text;
    } else {
        divCtrl.firstChild.nodeValue = text;
    }
}

function writeBox(title, content) {
	boxTitle=ns6? document.getElementById("popupboxTitle") : document.all.popupboxTitle
	boxContent=ns6? document.getElementById("popupboxContent") : document.all.popupboxContent
	writeDiv(boxTitle, title);
	writeDiv(boxContent, content);
}

function writeBox(title, content, closeImage) {
//	writeBox(title, content);
	boxTitle=ns6? document.getElementById("popupboxTitle") : document.all.popupboxTitle
	boxContent=ns6? document.getElementById("popupboxContent") : document.all.popupboxContent
	writeDiv(boxTitle, title);
	writeDiv(boxContent, content);
	changeCloseImage(closeImage);
	showbox();
}

function changeCloseImage(imgPath) {
	document.images.closeImg.src = imgPath;
}

function hideShowCovered() {
	var tags = new Array("applet", "select");
	var el=ns6? document.getElementById("showimage") : document.all.showimage

	var p = getAbsolutePos(el);
	var EX1 = p.x;
	var EX2 = el.offsetWidth + EX1;
	var EY1 = p.y;
	var EY2 = el.offsetHeight + EY1;

	for (var k = tags.length; k > 0; ) {
		var ar = document.getElementsByTagName(tags[--k]);
		var cc = null;

		for (var i = ar.length; i > 0;) {
			cc = ar[--i];

			p = getAbsolutePos(cc);
			var CX1 = p.x;
			var CX2 = cc.offsetWidth + CX1;
			var CY1 = p.y;
			var CY2 = cc.offsetHeight + CY1;

			if (!isPopupBoxShow || (CX1 > EX2) || (CX2 < EX1) || (CY1 > EY2) || (CY2 < EY1)) {
				cc.style.visibility = "visible";
			} else {
				cc.style.visibility = "hidden";
			}
		}
	}
}

function createBox() {
	if (ie4||ns6) {
		document.writeln("<div id=\"showimage\" style=\"position:absolute;width:717px;left:236px;top:290px;visibility: hidden\">");
	} else {
		document.writeln("<div id=\"showimage\" style=\"position:absolute;width:717px;left:236px;top:290px;visibility: hide\">");
	}
	document.writeln("<table id=\"popupboxTable\" name=\"popupboxTable\" border=\"0\" width=\"717\" bgcolor=\"#000080\" cellspacing=\"0\" cellpadding=\"2\">");
	document.writeln("  <tr>");
	document.writeln("    <td width=\"100%\"><table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"");
	document.writeln("    height=\"36px\">");
	document.writeln("     <tr>");
	document.writeln("        <td id=\"dragbar\" width=\"100%\"><ilayer width=\"100%\" onSelectStart=\"return false\"><layer width=\"100%\" onMouseover=\"dragswitch=1;if (ns4) drag_dropns(showimage)\" onMouseout=\"dragswitch=0\"><font face=\"Verdana\"");
	document.writeln("        color=\"#FFFFFF\"><strong>");
	document.writeln("        <div id=\"popupboxTitle\"></div>");
	document.writeln("		</strong></font></layer></ilayer></td>");
	document.writeln("        <td style=\"cursor:hand\"><a href=\"#\" onClick=\"hidebox();return false\"><img id=\"closeImg\" name=\"closeImg\" src=\"close.gif\" width=\"16px\"");
	document.writeln("        height=\"14px\" border=0></a></td>");
	document.writeln("      </tr>");
	document.writeln("      <tr>");
	document.writeln("        <td width=\"100%\" bgcolor=\"#FFFFFF\" style=\"padding:4px\" colspan=\"2\">");
	document.writeln("           <div id=\"popupboxContent\"></div>");
	document.writeln("        </td>");
	document.writeln("      </tr>");
	document.writeln("    </table>");
	document.writeln("    </td>");
	document.writeln("  </tr>");
	document.writeln("</table>");
	document.writeln("</div>");
}