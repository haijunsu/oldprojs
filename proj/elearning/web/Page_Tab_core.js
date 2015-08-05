
var Pages=new Array
var Controler_Height=28
var Page_Cnt
function DrawFrames(){
with(document){
if(!location.search){
writeln("<frameset rows=\"*,"+Controler_Height+"\" border=\"0\" framespacing=\"0\">")
var Pager=""
for(i=1;i<Pages.length;i++){
Pager+=",*"
}
if(i-1){write("<frameset id=\"Page_Switch\" rows=\"100%"+Pager+"\" border=\"0\" framespacing=\"0\">")}
for(i=0;i<Pages.length;i++){
write("<frame name=\"Content_"+(i+1)+"\" src=\""+Pages[i][0]+"\" marginwidth=\"0\" marginheight=\"0\" scrolling=\""+Pages[i][1]+"\" frameborder=\"no\" noresize>")
}
if(i-1){write("</frameset>")}
write("<frame name=\"Controler\" src=\""+self.location.href+"?Controler\" marginwidth=\"0\" marginheight=\"0\" scrolling=\"no\" frameborder=\"no\" noresize>\
</frameset>\
")
}else{
switch(location.search){
case "?Controler":
write("\
<style>\
body,td {font-size:12px;margin:0px;over-flow:hidden;cursor:default}\
div     {font:normal 12px 宋体;letter-spacing:1px;height:22px}\
.Tab_on {padding-top:4px;color:#05c;background:url(Page_Tab_02.gif);cursor:default}\
.Tab_off{padding-top:5px;color:#888;background:url(Page_Tab_01.gif);cursor:hand}\
</style>\
<body bgcolor=\"#00b4de\" background=\"Page_Tab_00.gif\" onload=\"PreLoad()\">\
<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr>\
")
for(i=0;i<Pages.length;i++){
Tab_Show=(i==0)?"on":"off"
Tab_Flag=(i==0)?"04":(i==1)?"07":"05"
write("<td width=\"14\"><img src=\"Page_Tab_"+Tab_Flag+".gif\" name=\"flag_"+(i+1)+"\" width=\"14\" height=\"22\"></td>\
<td><div id=\"tab_"+(i+1)+"\" class=\"Tab_"+Tab_Show+"\" nowrap onclick=\"Switch_Page("+(i+1)+")\" title=\""+Pages[i][3]+"\">"+Pages[i][2]+"</div></td>")
}
End_Flag=(i==1)?"09":"08"
write("\
<td><img src=\"Page_Tab_"+End_Flag+".gif\" name=\"flag_"+(i+1)+"\" width=\"14\" height=\"22\"></td>\
<td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img src=\"images/niux-home.gif\" alt='关闭系统' border=0 onclick=javascript:parent.parent.location=\"login.jsp\"></td>\
</tr></table>\
</body>\
")
break
}
}
Page_Cnt=Pages.length
top.document.title=Pages[0][2]
}
}

function Switch_Page(p){
Reset_Tabs()
var flag_1=(p==1)?"04":(p>1)?"06":(p==Page_Cnt)?"09":"05"
var flag_2=(p==Page_Cnt)?"09":"07"
eval('document.flag_'+p).src="Page_Tab_"+flag_1+".gif"
eval('tab_'+p).className="Tab_on"
eval('document.flag_'+(p+1)).src="Page_Tab_"+flag_2+".gif"
top.document.title=Pages[(p-1)][2]
var Pager=""
for(i=0;i<Pages.length;i++){
Pager+=(i==(p-1))?",100%":",*"
}
parent.Page_Switch.rows=Pager.substring(1,Pager.length)
}

function Reset_Tabs(){
for(i=0;i<Pages.length;i++){
Tab_Flag=(i==0)?"03":"05"
eval('document.flag_'+(i+1)).src="Page_Tab_"+Tab_Flag+".gif"
eval('tab_'+(i+1)).className="Tab_off"
}
eval('document.flag_'+(i+1)).src="Page_Tab_08.gif"
}

function PreLoad(){
var image1=new Image;image1.src="Page_Tab_01.gif"
var image2=new Image;image2.src="Page_Tab_02.gif"
var image3=new Image;image3.src="Page_Tab_03.gif"
var image4=new Image;image4.src="Page_Tab_04.gif"
var image5=new Image;image5.src="Page_Tab_05.gif"
var image6=new Image;image6.src="Page_Tab_06.gif"
var image7=new Image;image7.src="Page_Tab_07.gif"
var image8=new Image;image8.src="Page_Tab_08.gif"
var image9=new Image;image9.src="Page_Tab_09.gif"
var image0=new Image;image0.src="Page_Tab_00.gif"
}

document.ondragstart  =new Function("return false")
document.onselectstart=new Function("return false")
document.oncontextmenu=new Function("return false")
