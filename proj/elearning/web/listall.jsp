<%@page contentType="text/html;charset=GB2312"%>
<%@page language="java" import="com.htyz.*"%>
<html>
<head>
<title>课程列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="css/site.css">
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
plus[0] = new Image(); plus[0].src = "images/tree/plus1.gif";
plus[1] = new Image(); plus[1].src = "images/tree/plus2.gif";
plus[2] = new Image(); plus[2].src = "images/tree/plus3.gif";
plus[3] = new Image(); plus[3].src = "images/tree/plus4.gif";
plus[4] = new Image(); plus[4].src = "images/tree/plus5.gif";
minus = new Array ();
minus[0] = new Image(); minus[0].src = "images/tree/minus1.gif";
minus[1] = new Image(); minus[1].src = "images/tree/minus2.gif";
minus[2] = new Image(); minus[2].src = "images/tree/minus3.gif";
minus[3] = new Image(); minus[3].src = "images/tree/minus4.gif";
minus[4] = new Image(); minus[4].src = "images/tree/minus5.gif";
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

</head>
<jsp:useBean id="sqlbean1" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="sqlbean2" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="sqlbean3" scope="request" class="com.htyz.beanGetdata"/>

<body background="images/2.gif" text="#000000">
<%//添加

sqlbean1.executeSelect("select * from t_code where code_id='0013'");
int i=0;
int j=0;
int k=0;
//  第一层开始
for(i=0;i<sqlbean1.getRowCount();i++){
	sqlbean2.executeSelect("select * from t_code where code_id='"+sqlbean1.getFieldValue("code_value",i)+"'");
	%>
	<div id='XXX<%=i%>Parent' class='parent'>
	<%if(sqlbean2.getRowCount()>0){%>
		<img id='XXX<%=i%>Child_1' src='images/tree/plus1.gif' border=0 align="absmiddle" hspace="4" onClick="expandIt('XXX<%=i%>','Child',1,0); return false;"><a href="class03.jsp?class_type=<%=sqlbean1.getFieldValue("code_value",i)%>" target="rightFrame">
		<%out.println(sqlbean1.getFieldValue("code_namec",i));%></a><%
	}else{%>
		<img src='images/tree/minus1.gif' border=0 align="absmiddle" hspace="4" ><a href="class03.jsp?class_type=<%=sqlbean1.getFieldValue("code_value",i)%>" target="rightFrame">
		<%out.println(sqlbean1.getFieldValue("code_namec",i));%></a><%}
	%><br></div>
	<%if(sqlbean2.getRowCount()>0){%>
		<div id='XXX<%=i%>Child' class='child'><%
		}
//    第二层开始
	for(j=0;j<sqlbean2.getRowCount();j++){
		sqlbean3.executeSelect("select * from t_class where class_status='1' and left(class_id,8)='"+sqlbean1.getFieldValue("code_value",i)+sqlbean2.getFieldValue("code_value",j)+"'");
		if(sqlbean3.getRowCount()>0){%>
			<img id='XXX<%=i%><%=j%>regular_1' src='images/tree/plus2.gif' border=0 align="absmiddle" hspace="4" onClick="expandIt('XXX<%=i%><%=j%>','regular',1,1); return false;"><a href="class03.jsp?class_type=<%=sqlbean1.getFieldValue("code_value",i)%>&class_type2=<%=sqlbean2.getFieldValue("code_value",j)%>" target="rightFrame" >
		<%}else{%>
			<img src='images/tree/minus2.gif' border=0 align="absmiddle" hspace="4" ><a href="class03.jsp?class_type=<%=sqlbean1.getFieldValue("code_value",i)%>&class_type2=<%=sqlbean2.getFieldValue("code_value",j)%>" target="rightFrame" >
			<%}
		out.println(sqlbean2.getFieldValue("code_namec",j));
		%></a><br>
		<%if(sqlbean3.getRowCount()>0){%>
			<div id='XXX<%=i%><%=j%>regular' class='regular'><%}
//    第三层开始
			for(k=0;k<sqlbean3.getRowCount();k++){
				%>
				<img src='images/tree/document3.gif'  border=0 hspace="4" align="absmiddle" ><a href="selectclass.jsp?class_id=<%=sqlbean3.getFieldValue("class_id",k)%>" target="rightFrame" >
				<%out.println(sqlbean3.getFieldValue("class_name",k));
				%></a><br><%
			}
//    第三层结束
				if(sqlbean3.getRowCount()>0){%></div><%}
//  第二层结束
	}
	if(sqlbean2.getRowCount()>0){%></div><%}
//  第一层结束
}
%>


</body>
</html>
