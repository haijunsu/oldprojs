var mmenus    = new Array();
var misShow   = new Boolean(); 
misShow=false;
var misdown   = new Boolean();
misdown=false;
var musestatus=false;
var mpopTimer = 0;
mmenucolor='#8080C0';mfontcolor='#000000';mmenuoutcolor='#DDDDFF';mmenuincolor='#DDDDFF';mmenuoutbordercolor='#000000';mmenuinbordercolor='#000000';mmidoutcolor='#555580';mmidincolor='#555580';mmenuovercolor='#000000';mitemedge='1';msubedge='0';mmenuunitwidth=1;mmenuitemwidth=125;mmenuheight=22;mmenuwidth='100%';mmenuadjust=0;mmenuadjustV=0;mfonts='font-family: Verdana; font-size: 9pt; color: #000000; ';mcursor='default';
function stoperror(){
return true;
}
window.onerror=stoperror;
function mpopOut() {
mpopTimer = setTimeout('mallhide()', 500);
}
function mMenuRegister(menu) 
{
  mmenus[mmenus.length] = menu
  return (mmenus.length - 1)
}
function mMenuItem(caption,command,target,isline,statustxt,img,sizex,sizey,pos){
	this.caption=caption;
	this.command=command;
	this.target=target;
	this.isline=isline;
	this.statustxt=statustxt;
	this.img=img;
	this.sizex=sizex;
	this.sizey=sizey;
	this.pos=pos;
}
function mMenu(caption,command,target,img,sizex,sizey,pos){
	this.items = new Array();
	this.caption=caption;
	this.command=command;
	this.target=target;
	this.img=img;
	this.sizex=sizex;
	this.sizey=sizey;
	this.pos=pos;
	this.id=mMenuRegister(this);
}
function mMenuAddItem(item)
{
  this.items[this.items.length] = item
  item.parent = this.id;
  this.children=true;
}

mMenu.prototype.addItem = mMenuAddItem;
function mtoout(src){
src.style.borderLeftColor=mmenuoutbordercolor;
src.style.borderRightColor=mmenuinbordercolor;
src.style.borderTopColor=mmenuoutbordercolor;
src.style.borderBottomColor=mmenuinbordercolor;
src.style.backgroundColor=mmenuoutcolor;
src.style.color=mmenuovercolor;
}
function mtoin(src){
src.style.borderLeftColor=mmenuinbordercolor;
src.style.borderRightColor=mmenuoutbordercolor;
src.style.borderTopColor=mmenuinbordercolor;
src.style.borderBottomColor=mmenuoutbordercolor;
src.style.backgroundColor=mmenuincolor;
src.style.color=mmenuovercolor;
}
function mnochange(src){
src.style.borderLeftColor=mmenucolor;
src.style.borderRightColor=mmenucolor;
src.style.borderTopColor=mmenucolor;
src.style.borderBottomColor=mmenucolor;
src.style.backgroundColor='';
src.style.color=mfontcolor;
}
function mallhide(){
	for(var nummenu=0;nummenu<mmenus.length;nummenu++){
		var themenu=document.getElementById('mMenu'+nummenu);
		var themenudiv=document.getElementById('mmenudiv'+nummenu);
                mnochange(themenu);
                mmenuhide(themenudiv);
                }
}
function mmenuhide(menuid){
menuid.style.visibility='hidden';
misShow=false;
}
function mmenushow(x){
menuid=document.getElementById("mmenudiv"+x);
pid=document.getElementById("mMenu"+x);
menuid.style.left=pid.offsetLeft+mmenuadjust;menuid.style.top=document.getElementById("mposflag").offsetTop+document.getElementById("mmenutable").offsetHeight+mmenuadjustV;
cw=window.innerWidth;
if(mmenuitemwidth+parseInt(menuid.style.left)>cw)
menuid.style.left=cw-mmenuitemwidth;
menuid.style.visibility='visible';
misShow=true;
}
function mmenu_over(x){
if(x<0){
  misShow = false;
  mallhide();
  mtoout(document.getElementById("mMenu"+x));
}else{

  mallhide();
  mtoin(document.getElementById("mMenu"+x));
  mmenushow(x);

}
clearTimeout(mpopTimer);
}
function mmenu_out(x){
if (misShow){
mtoin(document.getElementById("mMenu"+x));
}else{
mnochange(document.getElementById("mMenu"+x));
}
mpopOut()
}
function mmenu_down(x){
  if(misShow){
  mmenuhide(document.getElementById("mmenudiv"+x));
  mtoout(document.getElementById("mMenu"+x));
  }
  else{
  mtoin(document.getElementById("mMenu"+x));
  mmenushow(x);
  misdown=true;
  }
}
function mmenu_up(){
  misdown=false;
}
function mmenuitem_over(x,i){
srcel = document.getElementById("m&"+x+"&"+i);
if(misdown){
	mtoin(srcel);
}
else{
mtoout(srcel);
}
mthestatus = mmenus[x].items[i].statustxt;
if(mthestatus!=""){
	musestatus=true;
	window.status=mthestatus;
}
clearTimeout(mpopTimer);
}
function mmenuitem_out(x,i){
srcel = document.getElementById("m&"+x+"&"+i);
mnochange(srcel);
if(musestatus)window.status="";
mpopOut()
}
function mmenuitem_down(x,i){
srcel = document.getElementById("m&"+x+"&"+i);
mtoin(srcel)
misdown=true;
}
function mmenuitem_up(x,i){
srcel = document.getElementById("m&"+x+"&"+i);
mtoout(srcel)
misdown=false;
}
function mexec2(x){
var cmd;
if(mmenus[x].target=="blank"){
  cmd = "window.open('"+mmenus[x].command+"')";
}else{
  cmd = mmenus[x].target+".location=\""+mmenus[x].command+"\"";
}
eval(cmd);
}
function mexec(x,i){
var cmd;
if(mmenus[x].items[i].target=="blank"){
  cmd = "window.open('"+mmenus[x].items[i].command+"')";
}else{
  cmd = mmenus[x].items[i].target+".location=\""+mmenus[x].items[i].command+"\"";
}
eval(cmd);
}
function mbody_click(e){

if (misShow){
        srcel = e.target;
        while((srcel.className!="coolButton")&&(srcel.tagName!="BODY")&&(srcel.tagName!="HTML")){
           srcel = srcel.parentNode;                
        }        
	for(var x=0;x<=mmenus.length;x++){
		if(srcel.id=="mMenu"+x)
		return;
	}
	mallhide();
}
}
document.onclick=mbody_click;
function mwritetodocument(){
      var mwb=1
                     var stringx='<div id="mposflag" style="position:absolute;"></div><table  id=mmenutable border=0 cellpadding=3 cellspacing=2 width='+mmenuwidth+' height='+mmenuheight+' bgcolor='+mmenucolor+
                     ' style="cursor:'+mcursor+';'+mfonts+
                     ' border-left: '+mwb+'px solid '+mmenuoutbordercolor+';'+
                     ' border-right: '+mwb+'px solid '+mmenuinbordercolor+'; '+
                     'border-top: '+mwb+'px solid '+mmenuoutbordercolor+'; '+
                     'border-bottom: '+mwb+'px solid '+mmenuinbordercolor+'; padding:0px"><tr>'
                     for(var x=0;x<mmenus.length;x++){
                     	var thismenu=mmenus[x];
                     	var imgsize="";
                     	if(thismenu.sizex!="0"||thismenu.sizey!="0")imgsize=" width="+thismenu.sizex+" height="+thismenu.sizey;
                     	var ifspace="";
                     	if(thismenu.caption!="")ifspace="&nbsp;";
                     	stringx += "<td nowrap class=coolButton id=mMenu"+x+" style='border: "+mitemedge+"px solid "+mmenucolor+
                     	"' width="+mmenuunitwidth+"px onmouseover=mmenu_over("+x+") onmouseout=mmenu_out("+x+
                     	") onmousedown=mmenu_down("+x+")";
                     	      if(thismenu.command!=""){
                     	          stringx += " onmouseup=mmenu_up();mexec2("+x+");";
                     	      }else{
                     	      	  stringx += " onmouseup=mmenu_up()";
                     	      }
                     	      if(thismenu.pos=="0"){
                     	          stringx += " align=center><img align=absmiddle src='"+thismenu.img+"'"+imgsize+">"+ifspace+thismenu.caption+"</td>";	
                     	      }else if(thismenu.pos=="1"){
                     	          stringx += " align=center>"+thismenu.caption+ifspace+"<img align=absmiddle src='"+thismenu.img+"'"+imgsize+"></td>";	
                     	      }else if(thismenu.pos=="2"){
                     	          stringx += " align=center background='"+thismenu.img+"'>&nbsp;"+thismenu.caption+"&nbsp;</td>";	
                     	      }else{
                     	          stringx += " align=center>&nbsp;"+thismenu.caption+"&nbsp;</td>";
                     	      }
                     	stringx += "";
                     }
                     stringx+="<td width=*>&nbsp;</td></tr></table>";
                     
                     
                     for(var x=0;x<mmenus.length;x++){
                     	thismenu=mmenus[x];
                        if(x<0){
                        stringx+='<div id=mmenudiv'+x+' style="visiable:none"></div>';
                        }else{
                        stringx+='<div id=mmenudiv'+x+
                        ' style="cursor:'+mcursor+';position:absolute;'+
                        'width:'+mmenuitemwidth+'px; z-index:'+(x+100);
                        if(mmenuinbordercolor!=mmenuoutbordercolor&&msubedge=="0"){
                        stringx+=';border-left: 1px solid '+mmidoutcolor+
                        ';border-top: 1px solid '+mmidoutcolor;}
                        stringx+=';border-right: 1px solid '+mmenuinbordercolor+
                        ';border-bottom: 1px solid '+mmenuinbordercolor+';visibility:hidden" onselectstart="event.returnValue=false">\n'+
                     	'<table  width="100%" border="0" height="100%" align="center" cellpadding="0" cellspacing="2" '+
                     	'style="'+mfonts+' border-left: 1px solid '+mmenuoutbordercolor;
                        if(mmenuinbordercolor!=mmenuoutbordercolor&&msubedge=="0"){
                     	stringx+=';border-right: 1px solid '+mmidincolor+
                     	';border-bottom: 1px solid '+mmidincolor;}
                     	stringx+=';border-top: 1px solid '+mmenuoutbordercolor+
                     	';" bgcolor='+mmenucolor+'>\n'
                     	for(var i=0;i<thismenu.items.length;i++){
                     		var thismenuitem=thismenu.items[i];
                     		var imgsize="";
                     	        if(thismenuitem.sizex!="0"||thismenuitem.sizey!="0")imgsize=" width="+thismenuitem.sizex+" height="+thismenuitem.sizey
                     	        var ifspace="";
                     	        if(thismenu.caption!="")ifspace="&nbsp;";
                     		if(!thismenuitem.isline){
                     		stringx += "<tr><td id=m&"+x+"&"+i+" class=coolButton style='border: "+mitemedge+"px solid "+mmenucolor+
                     		"' width=100% height=26px onmouseover=\"mmenuitem_over("+x+","+i+
                     		");\" onmouseout=mmenuitem_out("+x+","+i+
                     		") onmousedown=mmenuitem_down("+x+","+i+") onmouseup=";
 				stringx += "mmenuitem_up("+x+","+i+");mexec("+x+","+i+"); ";
 				if(thismenuitem.pos=="0"){
                     	            stringx += ">&nbsp;<img align=absmiddle src='"+thismenuitem.img+"'"+imgsize+">"+ifspace+thismenuitem.caption+"</td></tr>";	
                     	          }else if(thismenuitem.pos=="1"){
                     	            stringx += ">&nbsp;"+thismenuitem.caption+ifspace+"<img align=absmiddle src='"+thismenuitem.img+"'"+imgsize+"></td></tr>";	
                     	          }else if(thismenuitem.pos=="2"){
                     	            stringx += "background='"+thismenuitem.img+"'>&nbsp;"+thismenuitem.caption+"</td></tr>";	
                     	          }else{
                     	            stringx += ">&nbsp;"+thismenuitem.caption+"</td></tr>";
                     	          }
 				}else{
                     		stringx+='<tr><td height="6" background="hr.gif" onmousemove="clearTimeout(mpopTimer);"></td></tr>\n';
                     		}
                     	}stringx+='</table>\n</div>'
                     	}
                     	
                }
                
                     document.write("<div align='left'>"+stringx+"</div>");
}
