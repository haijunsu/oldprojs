<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">

<script language="JavaScript1.2">
NS4 = (document.layers) ? 1 : 0;
IE4 = (document.all) ? 1 : 0;
ver4 = (NS4 || IE4) ? 1 : 0;
if (ver4) 
{    
	with (document) 
	{        
		write("<STYLE TYPE='text/css'>");
		if (NS4) 
		{            
			write(".parent {position:absolute; visibility:visible}");            
			write(".child {position:absolute; visibility:visible}");            
			write(".regular {position:absolute; visibility:visible}") 
			write(".four{position:absolute; visibility:visible}") 
			write(".five{position:absolute; visibility:visible}")             
		}        
		else 
		{            
			write(".child {display:none}") 
			write(".regular {display:none}") 
			write(".four{display:none}") 
			write(".five{display:none}")           
		}        
		write("</STYLE>");    
	}
}

function swapimage(img_url)
{
	if (document.images) 
	{
		imgs=new image();
		imgs.src=img_url
	}
}



function initIt()
{    
	if (!ver4) return;    
	if (NS4) 
	{        
		for (i=0; i<document.layers.length; i++) 
		{            
			whichEl = document.layers[i];            
			if (whichEl.id.indexOf("Child") != -1||whichEl.id.indexOf("regular") != -1||whichEl.id.indexOf("four") != -1||whichEl.id.indexOf("five") != -1) whichEl.visibility = "hide";       
		}        
		arrange();    
	}    
	else 
	{        
		divColl = document.all.tags("DIV");        
		for (i=0; i<divColl.length; i++) 
		{            
			whichEl = divColl(i);            
			if (whichEl.className == "child"||whichEl.className == "regular"||whichEl.className == "four"||whichEl.className == "five") 
				whichEl.style.display = "none";        
		}    
	}
}
plus = new Array ();
plus[0] = new Image(); plus[0].src = "plus1.gif";
plus[1] = new Image(); plus[1].src = "plus2.gif";
plus[2] = new Image(); plus[2].src = "plus3.gif";
plus[3] = new Image(); plus[3].src = "plus4.gif";
plus[4] = new Image(); plus[4].src = "plus5.gif";
minus = new Array ();
minus[0] = new Image(); minus[0].src = "minus1.gif";
minus[1] = new Image(); minus[1].src = "minus2.gif";
minus[2] = new Image(); minus[2].src = "minus3.gif";
minus[3] = new Image(); minus[3].src = "minus4.gif";
minus[4] = new Image(); minus[4].src = "minus5.gif";
function expandIt(name,order,serial,num) 
{	
	if (!ver4) return;    
	if (IE4) 
	{        

		whichEl = eval([name] + [order]);		
		if (whichEl.style.display == "none") 
		{            
			whichEl.style.display = "block"; 			
		}        
		else 
		{            
			whichEl.style.display = "none";        
		}    
		whichEl = eval([name] + [order]+ "_" +[serial]);					
		if (whichEl.src.indexOf("plus") >= 0) {
			
			whichEl.src = minus[num].src
			}
		else {
			whichEl.src = plus[num].src
		}
	}    
	else 
	{        
		whichEl = eval("document." + [name] + [order]);		
		     
		if (whichEl.visibility == "hide") 
		{            
			whichEl.visibility = "show";        
		}        
		else 
		{            
			whichEl.visibility = "hide";        
		}        
		
	}
}
onload = initIt;
</script>


</HEAD>

<BODY>
<div id='XXXParent' class='parent'>
	<a href="qdgl/qdgl_fqgl.html" target="main" > <img id='XXXChild_1' src='plus1.gif' border=0 align="absmiddle" hspace="4" onClick="expandIt('XXX','Child',1,0); return false;">渠道管理</a></div>



<div id='XXXChild' class='child'>
	<a href="qdgl/wlgl2.html" target="main" ><img id='XXXregular_1' src='plus2.gif' border=0 align="absmiddle" hspace="4" onClick="expandIt('XXX','regular',1,1); return false;">网络管理</a>

	<div id='XXXregular' class='regular'>
		<a href="qdgl/qdgl_fgsgl.html" target="main"><img id='XXXfour_1'src='plus3.gif' border=0 hspace="4" align="absmiddle" onClick="expandIt('XXX','four',1,2); return false;">华北区</a>

		<div id='XXXfour' class='four'>
			<a href="qdgl/qdgl_zmdgl.html" target="main"><img id='XXXfive_1' src='plus4.gif' border=0 hspace="4" align="absmiddle" onClick="expandIt('XXX','five',1,3); return false;"  >北京市</a>

			<div id='XXXfive' class='five'>
				<a href="qdgl/chaoyang.html" target="main"><img src='document4.gif' border=0 hspace="4" align="absmiddle" >朝阳区</a><br><a href="qdgl/haidian.html" target="main"><img src='document4.gif' border=0 hspace="4" align="absmiddle" >海淀区</a>
</div></div></div></div>

</BODY>
</HTML>
