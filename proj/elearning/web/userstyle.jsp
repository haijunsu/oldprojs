<%@page contentType="text/html;charset=GB2312"%>
<%@page import="java.awt.Font.*"%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>
<form name="form1" method="post" action="setupstyle.jsp">
  <table width="300" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td width="35%" height="20">背景颜色： </td>
      <td width="33%" height="20"><select name="color"  onChange="text.style.color=value;text.focus();text.value=this.value">
          <option>请选择</option>
          <option style="background:#66CCCC;color: #66CCCC" value="#66CCCC"></option>
          <option style="background:#FFCC33;color: #FFCC33" value="#FFCC33"></option>
          <option style="background:#CCFFFF;color: #CCFFFF" value="#CCFFFF"></option>
          <option style="background:#009900;color: #009900" value="#009900"></option>
          <option style="background:#ff0000;color: #ff0000" value="#ff0000"></option>
          <option style="background:#DDDDDD;color: #DDDDDD" value="#DDDDDD"></option>
          <option style="background:#0099FF;color: #0099FF" value="#0099FF"></option>
          <option style="background:#000000;color: #000000" value="#000000"></option>
          <option value="#000088" style="background:#000088;color: #000088"></option>
          <option style="background:#0000ff;color: #0000ff" value="#0000ff"></option>
          <option style="background:#008800;color: #008800" value="#008800"></option>
          <option style="background:#008888;color: #008888" value="#008888"></option>
          <option style="background:#0088ff;color: #0088ff" value="#0088ff"></option>
          <option style="background:#00a010;color: #00a010" value="#00a010"></option>
          <option style="background:#1100ff;color: #1100ff" value="#1100ff"></option>
          <option style="background:#111111;color: #000000" value="#000000"></option>
          <option style="background:#333333;color: #0099FF" value="#0099FF"></option>
          <option style="background:#50b000;color: #50b000" value="#50b000"></option>
          <option style="background:#880000;color: #880000" value="#8800ff"></option>
          <option style="background:#8800ff;color: #8800ff" value="#8800ff"></option>
          <option style="background:#888800;color: ##FF0000" value="#FF0000"></option>
          <option style="background:#888888;color: #888888" value="#888888"></option>
          <option style="background:#8888ff;color: #8888ff" value="#8888ff"></option>
          <option style="background:#aa00cc;color: #aa00cc" value="#aa00cc"></option>
          <option style="background:#aaaa00;color: #aaaa00" value="#aaaa00"></option>
          <option style="background:#ccaa00;color: #ccaa00" value="#ccaa00"></option>
          <option style="background:#ff0088;color: #ff0088" value="#ff0088"></option>
          <option style="background:#ff00ff;color: #ff00ff" value="#ff00ff"></option>
          <option style="background:#ff8800;color: #ff8800" value="#ff8800"></option>
          <option style="background:#ff0005;color: #ff0005" value="#ff0005"></option>
          <option style="background:#ff88ff;color: #ff88ff" value="#ff88ff"></option>
          <option style="background:#ee0005;color: #ee0005" value="#ee0005"></option>
          <option style="background:#ee01ff;color: #ee01ff" value="#ee01ff"></option>
          <option style="background:#3388aa;color: #3388aa" value="#3388aa"></option>
        </select></td>
      <td width="32%" height="20"><input name="text" type="text" class="input1"  size="10"></td>
    </tr>
    <tr> 
      <td height="20">字体颜色：</td>
      <td height="20"><select name="select"  onChange="text1.style.color=value;text.focus();text1.value=this.value">
          <option>请选择</option>
          <option style="background:#66CCCC;color: #66CCCC" value="#66CCCC"></option>
          <option style="background:#FFCC33;color: #FFCC33" value="#FFCC33"></option>
          <option style="background:#CCFFFF;color: #CCFFFF" value="#CCFFFF"></option>
          <option style="background:#009900;color: #009900" value="#009900"></option>
          <option style="background:#ff0000;color: #ff0000" value="#ff0000"></option>
          <option style="background:#DDDDDD;color: #DDDDDD" value="#DDDDDD"></option>
          <option style="background:#0099FF;color: #0099FF" value="#0099FF"></option>
          <option style="background:#000000;color: #000000" value="#000000"></option>
          <option value="#000088" style="background:#000088;color: #000088"></option>
          <option style="background:#0000ff;color: #0000ff" value="#0000ff"></option>
          <option style="background:#008800;color: #008800" value="#008800"></option>
          <option style="background:#008888;color: #008888" value="#008888"></option>
          <option style="background:#0088ff;color: #0088ff" value="#0088ff"></option>
          <option style="background:#00a010;color: #00a010" value="#00a010"></option>
          <option style="background:#1100ff;color: #1100ff" value="#1100ff"></option>
          <option style="background:#111111;color: #000000" value="#000000"></option>
          <option style="background:#333333;color: #0099FF" value="#0099FF"></option>
          <option style="background:#50b000;color: #50b000" value="#50b000"></option>
          <option style="background:#880000;color: #880000" value="#8800ff"></option>
          <option style="background:#8800ff;color: #8800ff" value="#8800ff"></option>
          <option style="background:#888800;color: ##FF0000" value="#FF0000"></option>
          <option style="background:#888888;color: #888888" value="#888888"></option>
          <option style="background:#8888ff;color: #8888ff" value="#8888ff"></option>
          <option style="background:#aa00cc;color: #aa00cc" value="#aa00cc"></option>
          <option style="background:#aaaa00;color: #aaaa00" value="#aaaa00"></option>
          <option style="background:#ccaa00;color: #ccaa00" value="#ccaa00"></option>
          <option style="background:#ff0088;color: #ff0088" value="#ff0088"></option>
          <option style="background:#ff00ff;color: #ff00ff" value="#ff00ff"></option>
          <option style="background:#ff8800;color: #ff8800" value="#ff8800"></option>
          <option style="background:#ff0005;color: #ff0005" value="#ff0005"></option>
          <option style="background:#ff88ff;color: #ff88ff" value="#ff88ff"></option>
          <option style="background:#ee0005;color: #ee0005" value="#ee0005"></option>
          <option style="background:#ee01ff;color: #ee01ff" value="#ee01ff"></option>
          <option style="background:#3388aa;color: #3388aa" value="#3388aa"></option>
        </select></td>
      <td height="20"><input name="text1" type="text" class="input1"  size="10"></td>
    </tr>
    <tr> 
      <td height="20">鼠标移上去颜色：</td>
      <td height="20"><select name="select2"  onChange="text2.style.color=value;text2.focus();text2.value=this.value">
          <option>请选择</option>
          <option style="background:#66CCCC;color: #66CCCC" value="#66CCCC"></option>
          <option style="background:#FFCC33;color: #FFCC33" value="#FFCC33"></option>
          <option style="background:#CCFFFF;color: #CCFFFF" value="#CCFFFF"></option>
          <option style="background:#009900;color: #009900" value="#009900"></option>
          <option style="background:#ff0000;color: #ff0000" value="#ff0000"></option>
          <option style="background:#DDDDDD;color: #DDDDDD" value="#DDDDDD"></option>
          <option style="background:#0099FF;color: #0099FF" value="#0099FF"></option>
          <option style="background:#000000;color: #000000" value="#000000"></option>
          <option value="#000088" style="background:#000088;color: #000088"></option>
          <option style="background:#0000ff;color: #0000ff" value="#0000ff"></option>
          <option style="background:#008800;color: #008800" value="#008800"></option>
          <option style="background:#008888;color: #008888" value="#008888"></option>
          <option style="background:#0088ff;color: #0088ff" value="#0088ff"></option>
          <option style="background:#00a010;color: #00a010" value="#00a010"></option>
          <option style="background:#1100ff;color: #1100ff" value="#1100ff"></option>
          <option style="background:#111111;color: #000000" value="#000000"></option>
          <option style="background:#333333;color: #0099FF" value="#0099FF"></option>
          <option style="background:#50b000;color: #50b000" value="#50b000"></option>
          <option style="background:#880000;color: #880000" value="#8800ff"></option>
          <option style="background:#8800ff;color: #8800ff" value="#8800ff"></option>
          <option style="background:#888800;color: ##FF0000" value="#FF0000"></option>
          <option style="background:#888888;color: #888888" value="#888888"></option>
          <option style="background:#8888ff;color: #8888ff" value="#8888ff"></option>
          <option style="background:#aa00cc;color: #aa00cc" value="#aa00cc"></option>
          <option style="background:#aaaa00;color: #aaaa00" value="#aaaa00"></option>
          <option style="background:#ccaa00;color: #ccaa00" value="#ccaa00"></option>
          <option style="background:#ff0088;color: #ff0088" value="#ff0088"></option>
          <option style="background:#ff00ff;color: #ff00ff" value="#ff00ff"></option>
          <option style="background:#ff8800;color: #ff8800" value="#ff8800"></option>
          <option style="background:#ff0005;color: #ff0005" value="#ff0005"></option>
          <option style="background:#ff88ff;color: #ff88ff" value="#ff88ff"></option>
          <option style="background:#ee0005;color: #ee0005" value="#ee0005"></option>
          <option style="background:#ee01ff;color: #ee01ff" value="#ee01ff"></option>
          <option style="background:#3388aa;color: #3388aa" value="#3388aa"></option>
        </select></td>
      <td height="20"><input name="text2" type="text" class="input1" id="text2"  size="10"></td>
    </tr>
    <tr> 
      <td height="20">链接颜色：</td>
      <td height="20"><select name="select3"  onChange="text3.style.color=value;text3.focus();text3.value=this.value">
          <option>请选择</option>
          <option style="background:#66CCCC;color: #66CCCC" value="#66CCCC"></option>
          <option style="background:#FFCC33;color: #FFCC33" value="#FFCC33"></option>
          <option style="background:#CCFFFF;color: #CCFFFF" value="#CCFFFF"></option>
          <option style="background:#009900;color: #009900" value="#009900"></option>
          <option style="background:#ff0000;color: #ff0000" value="#ff0000"></option>
          <option style="background:#DDDDDD;color: #DDDDDD" value="#DDDDDD"></option>
          <option style="background:#0099FF;color: #0099FF" value="#0099FF"></option>
          <option style="background:#000000;color: #000000" value="#000000"></option>
          <option value="#000088" style="background:#000088;color: #000088"></option>
          <option style="background:#0000ff;color: #0000ff" value="#0000ff"></option>
          <option style="background:#008800;color: #008800" value="#008800"></option>
          <option style="background:#008888;color: #008888" value="#008888"></option>
          <option style="background:#0088ff;color: #0088ff" value="#0088ff"></option>
          <option style="background:#00a010;colo: #00a010" value="#00a010"></option>
          <option style="background:#1100ff;color: #1100ff" value="#1100ff"></option>
          <option style="background:#111111;color: #000000" value="#000000"></option>
          <option style="background:#333333;color: #0099FF" value="#0099FF"></option>
          <option style="background:#50b000;color: #50b000" value="#50b000"></option>
          <option style="background:#880000;color: #880000" value="#8800ff"></option>
          <option style="background:#8800ff;color: #8800ff" value="#8800ff"></option>
          <option style="background:#888800;color: ##FF0000" value="#FF0000"></option>
          <option style="background:#888888;color: #888888" value="#888888"></option>
          <option style="background:#8888ff;color: #8888ff" value="#8888ff"></option>
          <option style="background:#aa00cc;color: #aa00cc" value="#aa00cc"></option>
          <option style="background:#aaaa00;color: #aaaa00" value="#aaaa00"></option>
          <option style="background:#ccaa00;color: #ccaa00" value="#ccaa00"></option>
          <option style="background:#ff0088;color: #ff0088" value="#ff0088"></option>
          <option style="background:#ff00ff;color: #ff00ff" value="#ff00ff"></option>
          <option style="background:#ff8800;color: #ff8800" value="#ff8800"></option>
          <option style="background:#ff0005;color: #ff0005" value="#ff0005"></option>
          <option style="background:#ff88ff;color: #ff88ff" value="#ff88ff"></option>
          <option style="background:#ee0005;color: #ee0005" value="#ee0005"></option>
          <option style="background:#ee01ff;color: #ee01ff" value="#ee01ff"></option>
          <option style="background:#3388aa;color: #3388aa" value="#3388aa"></option>
        </select></td>
      <td height="20"><input name="text3" type="text" class="input1" id="text3"  size="10"></td>
    </tr>
    <tr> 
      <td height="20">链接过的颜色：</td>
      <td height="20"><select name="select4"  onChange="text4.style.color=value;text4.focus();text4.value=this.value">
          <option>请选择</option>
          <option style="background:#66CCCC;color: #66CCCC" value="#66CCCC"></option>
          <option style="background:#FFCC33;color: #FFCC33" value="#FFCC33"></option>
          <option style="background:#CCFFFF;color: #CCFFFF" value="#CCFFFF"></option>
          <option style="background:#009900;color: #009900" value="#009900"></option>
          <option style="background:#ff0000;color: #ff0000" value="#ff0000"></option>
          <option style="background:#DDDDDD;color: #DDDDDD" value="#DDDDDD"></option>
          <option style="background:#0099FF;color: #0099FF" value="#0099FF"></option>
          <option style="background:#000000;color: #000000" value="#000000"></option>
          <option value="#000088" style="background:#000088;color: #000088"></option>
          <option style="background:#0000ff;color: #0000ff" value="#0000ff"></option>
          <option style="background:#008800;color: #008800" value="#008800"></option>
          <option style="background:#008888;color: #008888" value="#008888"></option>
          <option style="background:#0088ff;color: #0088ff" value="#0088ff"></option>
          <option style="background:#00a010;color: #00a010" value="#00a010"></option>
          <option style="background:#1100ff;color: #1100ff" value="#1100ff"></option>
          <option style="background:#111111;color: #000000" value="#000000"></option>
          <option style="background:#333333;color: #0099FF" value="#0099FF"></option>
          <option style="background:#50b000;color: #50b000" value="#50b000"></option>
          <option style="background:#880000;color: #880000" value="#8800ff"></option>
          <option style="background:#8800ff;color: #8800ff" value="#8800ff"></option>
          <option style="background:#888800;color: ##FF0000" value="#FF0000"></option>
          <option style="background:#888888;color: #888888" value="#888888"></option>
          <option style="background:#8888ff;color: #8888ff" value="#8888ff"></option>
          <option style="background:#aa00cc;color: #aa00cc" value="#aa00cc"></option>
          <option style="background:#aaaa00;color: #aaaa00" value="#aaaa00"></option>
          <option style="background:#ccaa00;color: #ccaa00" value="#ccaa00"></option>
          <option style="background:#ff0088;color: #ff0088" value="#ff0088"></option>
          <option style="background:#ff00ff;color: #ff00ff" value="#ff00ff"></option>
          <option style="background:#ff8800;color: #ff8800" value="#ff8800"></option>
          <option style="background:#ff0005;color: #ff0005" value="#ff0005"></option>
          <option style="background:#ff88ff;color: #ff88ff" value="#ff88ff"></option>
          <option style="background:#ee0005;color: #ee0005" value="#ee0005"></option>
          <option style="background:#ee01ff;color: #ee01ff" value="#ee01ff"></option>
          <option style="background:#3388aa;color: #3388aa" value="#3388aa"></option>
        </select></td>
      <td height="20"><input name="text4" type="text" class="input1" id="text4"  size="10"></td>
    </tr>
    <tr> 
      <td height="20">标题颜色：</td>
      <td height="20"><select name="select5"  onChange="text5.style.color=value;text5.focus();text5.value=this.value">
          <option>请选择</option>
          <option style="background:#66CCCC;color: #66CCCC" value="#66CCCC"></option>
          <option style="background:#FFCC33;color: #FFCC33" value="#FFCC33"></option>
          <option style="background:#CCFFFF;color: #CCFFFF" value="#CCFFFF"></option>
          <option style="background:#009900;color: #009900" value="#009900"></option>
          <option style="background:#ff0000;color: #ff0000" value="#ff0000"></option>
          <option style="background:#DDDDDD;color: #DDDDDD" value="#DDDDDD"></option>
          <option style="background:#0099FF;color: #0099FF" value="#0099FF"></option>
          <option style="background:#000000;color: #000000" value="#000000"></option>
          <option value="#000088" style="background:#000088;color: #000088"></option>
          <option style="background:#0000ff;color: #0000ff" value="#0000ff"></option>
          <option style="background:#008800;color: #008800" value="#008800"></option>
          <option style="background:#008888;color: #008888" value="#008888"></option>
          <option style="background:#0088ff;color: #0088ff" value="#0088ff"></option>
          <option style="background:#00a010;color: #00a010" value="#00a010"></option>
          <option style="background:#1100ff;color: #1100ff" value="#1100ff"></option>
          <option style="background:#111111;color: #000000" value="#000000"></option>
          <option style="background:#333333;color: #0099FF" value="#0099FF"></option>
          <option style="background:#50b000;color: #50b000" value="#50b000"></option>
          <option style="background:#880000;color: #880000" value="#8800ff"></option>
          <option style="background:#8800ff;color: #8800ff" value="#8800ff"></option>
          <option style="background:#888800;color: ##FF0000" value="#FF0000"></option>
          <option style="background:#888888;color: #888888" value="#888888"></option>
          <option style="background:#8888ff;color: #8888ff" value="#8888ff"></option>
          <option style="background:#aa00cc;color: #aa00cc" value="#aa00cc"></option>
          <option style="background:#aaaa00;color: #aaaa00" value="#aaaa00"></option>
          <option style="background:#ccaa00;color: #ccaa00" value="#ccaa00"></option>
          <option style="background:#ff0088;color: #ff0088" value="#ff0088"></option>
          <option style="background:#ff00ff;color: #ff00ff" value="#ff00ff"></option>
          <option style="background:#ff8800;color: #ff8800" value="#ff8800"></option>
          <option style="background:#ff0005;color: #ff0005" value="#ff0005"></option>
          <option style="background:#ff88ff;color: #ff88ff" value="#ff88ff"></option>
          <option style="background:#ee0005;color: #ee0005" value="#ee0005"></option>
          <option style="background:#ee01ff;color: #ee01ff" value="#ee01ff"></option>
          <option style="background:#3388aa;color: #3388aa" value="#3388aa"></option>
        </select></td>
      <td height="20"><input name="text5" type="text" class="input1" id="text5"  size="10"></td>
    </tr>
    <tr> 
      <td height="20">表格颜色：</td>
      <td height="20"><select name="select6"  onChange="text6.style.color=value;text6.focus();text6.value=this.value">
          <option>请选择</option>
          <option style="background:#66CCCC;color: #66CCCC" value="#66CCCC"></option>
          <option style="background:#FFCC33;color: #FFCC33" value="#FFCC33"></option>
          <option style="background:#CCFFFF;color: #CCFFFF" value="#CCFFFF"></option>
          <option style="background:#009900;color: #009900" value="#009900"></option>
          <option style="background:#ff0000;color: #ff0000" value="#ff0000"></option>
          <option style="background:#DDDDDD;color: #DDDDDD" value="#DDDDDD"></option>
          <option style="background:#0099FF;color: #0099FF" value="#0099FF"></option>
          <option style="background:#000000;color: #000000" value="#000000"></option>
          <option value="#000088" style="background:#000088;color: #000088"></option>
          <option style="background:#0000ff;color: #0000ff" value="#0000ff"></option>
          <option style="background:#008800;color: #008800" value="#008800"></option>
          <option style="background:#008888;color: #008888" value="#008888"></option>
          <option style="background:#0088ff;color: #0088ff" value="#0088ff"></option>
          <option style="background:#00a010;color: #00a010" value="#00a010"></option>
          <option style="background:#1100ff;color: #1100ff" value="#1100ff"></option>
          <option style="background:#111111;color: #000000" value="#000000"></option>
          <option style="background:#333333;color: #0099FF" value="#0099FF"></option>
          <option style="background:#50b000;color: #50b000" value="#50b000"></option>
          <option style="background:#880000;color: #880000" value="#8800ff"></option>
          <option style="background:#8800ff;color: #8800ff" value="#8800ff"></option>
          <option style="background:#888800;color: ##FF0000" value="#FF0000"></option>
          <option style="background:#888888;color: #888888" value="#888888"></option>
          <option style="background:#8888ff;color: #8888ff" value="#8888ff"></option>
          <option style="background:#aa00cc;color: #aa00cc" value="#aa00cc"></option>
          <option style="background:#aaaa00;color: #aaaa00" value="#aaaa00"></option>
          <option style="background:#ccaa00;color: #ccaa00" value="#ccaa00"></option>
          <option style="background:#ff0088;color: #ff0088" value="#ff0088"></option>
          <option style="background:#ff00ff;color: #ff00ff" value="#ff00ff"></option>
          <option style="background:#ff8800;color: #ff8800" value="#ff8800"></option>
          <option style="background:#ff0005;color: #ff0005" value="#ff0005"></option>
          <option style="background:#ff88ff;color: #ff88ff" value="#ff88ff"></option>
          <option style="background:#ee0005;color: #ee0005" value="#ee0005"></option>
          <option style="background:#ee01ff;color: #ee01ff" value="#ee01ff"></option>
          <option style="background:#3388aa;color: #3388aa" value="#3388aa"></option>
        </select></td>
      <td height="20"><input name="text6" type="text" class="input1" id="text6"  size="10"></td>
    </tr>
    <tr> 
      <td height="20">第一行颜色：</td>
      <td height="20"><select name="select7"  onChange="text7.style.color=value;text7.focus();text7.value=this.value">
          <option>请选择</option>
          <option style="background:#66CCCC;color: #66CCCC" value="#66CCCC"></option>
          <option style="background:#FFCC33;color: #FFCC33" value="#FFCC33"></option>
          <option style="background:#CCFFFF;color: #CCFFFF" value="#CCFFFF"></option>
          <option style="background:#009900;color: #009900" value="#009900"></option>
          <option style="background:#ff0000;color: #ff0000" value="#ff0000"></option>
          <option style="background:#DDDDDD;color: #DDDDDD" value="#DDDDDD"></option>
          <option style="background:#0099FF;color: #0099FF" value="#0099FF"></option>
          <option style="background:#000000;color: #000000" value="#000000"></option>
          <option value="#000088" style="background:#000088;color: #000088"></option>
          <option style="background:#0000ff;color: #0000ff" value="#0000ff"></option>
          <option style="background:#008800;color: #008800" value="#008800"></option>
          <option style="background:#008888;color: #008888" value="#008888"></option>
          <option style="background:#0088ff;color: #0088ff" value="#0088ff"></option>
          <option style="background:#00a010;color: #00a010" value="#00a010"></option>
          <option style="background:#1100ff;color: #1100ff" value="#1100ff"></option>
          <option style="background:#111111;color: #000000" value="#000000"></option>
          <option style="background:#333333;color: #0099FF" value="#0099FF"></option>
          <option style="background:#50b000;color: #50b000" value="#50b000"></option>
          <option style="background:#880000;color: #880000" value="#8800ff"></option>
          <option style="background:#8800ff;color: #8800ff" value="#8800ff"></option>
          <option style="background:#888800;color: ##FF0000" value="#FF0000"></option>
          <option style="background:#888888;color: #888888" value="#888888"></option>
          <option style="background:#8888ff;color: #8888ff" value="#8888ff"></option>
          <option style="background:#aa00cc;color: #aa00cc" value="#aa00cc"></option>
          <option style="background:#aaaa00;color: #aaaa00" value="#aaaa00"></option>
          <option style="background:#ccaa00;color: #ccaa00" value="#ccaa00"></option>
          <option style="background:#ff0088;color: #ff0088" value="#ff0088"></option>
          <option style="background:#ff00ff;color: #ff00ff" value="#ff00ff"></option>
          <option style="background:#ff8800;color: #ff8800" value="#ff8800"></option>
          <option style="background:#ff0005;color: #ff0005" value="#ff0005"></option>
          <option style="background:#ff88ff;color: #ff88ff" value="#ff88ff"></option>
          <option style="background:#ee0005;color: #ee0005" value="#ee0005"></option>
          <option style="background:#ee01ff;color: #ee01ff" value="#ee01ff"></option>
          <option style="background:#3388aa;color: #3388aa" value="#3388aa"></option>
        </select></td>
      <td height="20"><input name="text7" type="text" class="input1" id="text7"  size="10"></td>
    </tr>
    <tr> 
      <td height="20">bbs颜色：</td>
      <td height="20"><select name="select8"  onChange="text8.style.color=value;text8.focus();text8.value=this.value">
          <option>请选择</option>
          <option style="background:#66CCCC;color: #66CCCC" value="#66CCCC"></option>
          <option style="background:#FFCC33;color: #FFCC33" value="#FFCC33"></option>
          <option style="background:#CCFFFF;color: #CCFFFF" value="#CCFFFF"></option>
          <option style="background:#009900;color: #009900" value="#009900"></option>
          <option style="background:#ff0000;color: #ff0000" value="#ff0000"></option>
          <option style="background:#DDDDDD;color: #DDDDDD" value="#DDDDDD"></option>
          <option style="background:#0099FF;color: #0099FF" value="#0099FF"></option>
          <option style="background:#000000;color: #000000" value="#000000"></option>
          <option value="#000088" style="background:#000088;color: #000088"></option>
          <option style="background:#0000ff;color: #0000ff" value="#0000ff"></option>
          <option style="background:#008800;color: #008800" value="#008800"></option>
          <option style="background:#008888;color: #008888" value="#008888"></option>
          <option style="background:#0088ff;color: #0088ff" value="#0088ff"></option>
          <option style="background:#00a010;color: #00a010" value="#00a010"></option>
          <option style="background:#1100ff;color: #1100ff" value="#1100ff"></option>
          <option style="background:#111111;color: #000000" value="#000000"></option>
          <option style="background:#333333;color: #0099FF" value="#0099FF"></option>
          <option style="background:#50b000;color: #50b000" value="#50b000"></option>
          <option style="background:#880000;color: #880000" value="#8800ff"></option>
          <option style="background:#8800ff;color: #8800ff" value="#8800ff"></option>
          <option style="background:#888800;color: ##FF0000" value="#FF0000"></option>
          <option style="background:#888888;color: #888888" value="#888888"></option>
          <option style="background:#8888ff;color: #8888ff" value="#8888ff"></option>
          <option style="background:#aa00cc;color: #aa00cc" value="#aa00cc"></option>
          <option style="background:#aaaa00;color: #aaaa00" value="#aaaa00"></option>
          <option style="background:#ccaa00;color: #ccaa00" value="#ccaa00"></option>
          <option style="background:#ff0088;color: #ff0088" value="#ff0088"></option>
          <option style="background:#ff00ff;color: #ff00ff" value="#ff00ff"></option>
          <option style="background:#ff8800;color: #ff8800" value="#ff8800"></option>
          <option style="background:#ff0005;color: #ff0005" value="#ff0005"></option>
          <option style="background:#ff88ff;color: #ff88ff" value="#ff88ff"></option>
          <option style="background:#ee0005;color: #ee0005" value="#ee0005"></option>
          <option style="background:#ee01ff;color: #ee01ff" value="#ee01ff"></option>
          <option style="background:#3388aa;color: #3388aa" value="#3388aa"></option>
        </select></td>
      <td height="20"><input name="text8" type="text" class="input1" id="text8"  size="10"></td>
    </tr>
    <tr>
      <td height="20">bbs链接颜色：</td>
      <td height="20"><select name="select11"  onChange="text11.style.color=value;text11.focus();text11.value=this.value">
          <option>请选择</option>
          <option style="background:#66CCCC;color: #66CCCC" value="#66CCCC"></option>
          <option style="background:#FFCC33;color: #FFCC33" value="#FFCC33"></option>
          <option style="background:#CCFFFF;color: #CCFFFF" value="#CCFFFF"></option>
          <option style="background:#009900;color: #009900" value="#009900"></option>
          <option style="background:#ff0000;color: #ff0000" value="#ff0000"></option>
          <option style="background:#DDDDDD;color: #DDDDDD" value="#DDDDDD"></option>
          <option style="background:#0099FF;color: #0099FF" value="#0099FF"></option>
          <option style="background:#000000;color: #000000" value="#000000"></option>
          <option value="#000088" style="background:#000088;color: #000088"></option>
          <option style="background:#0000ff;color: #0000ff" value="#0000ff"></option>
          <option style="background:#008800;color: #008800" value="#008800"></option>
          <option style="background:#008888;color: #008888" value="#008888"></option>
          <option style="background:#0088ff;color: #0088ff" value="#0088ff"></option>
          <option style="background:#00a010;color: #00a010" value="#00a010"></option>
          <option style="background:#1100ff;color: #1100ff" value="#1100ff"></option>
          <option style="background:#111111;color: #000000" value="#000000"></option>
          <option style="background:#333333;color: #0099FF" value="#0099FF"></option>
          <option style="background:#50b000;color: #50b000" value="#50b000"></option>
          <option style="background:#880000;color: #880000" value="#8800ff"></option>
          <option style="background:#8800ff;color: #8800ff" value="#8800ff"></option>
          <option style="background:#888800;color: ##FF0000" value="#FF0000"></option>
          <option style="background:#888888;color: #888888" value="#888888"></option>
          <option style="background:#8888ff;color: #8888ff" value="#8888ff"></option>
          <option style="background:#aa00cc;color: #aa00cc" value="#aa00cc"></option>
          <option style="background:#aaaa00;color: #aaaa00" value="#aaaa00"></option>
          <option style="background:#ccaa00;color: #ccaa00" value="#ccaa00"></option>
          <option style="background:#ff0088;color: #ff0088" value="#ff0088"></option>
          <option style="background:#ff00ff;color: #ff00ff" value="#ff00ff"></option>
          <option style="background:#ff8800;color: #ff8800" value="#ff8800"></option>
          <option style="background:#ff0005;color: #ff0005" value="#ff0005"></option>
          <option style="background:#ff88ff;color: #ff88ff" value="#ff88ff"></option>
          <option style="background:#ee0005;color: #ee0005" value="#ee0005"></option>
          <option style="background:#ee01ff;color: #ee01ff" value="#ee01ff"></option>
          <option style="background:#3388aa;color: #3388aa" value="#3388aa"></option>
        </select></td>
      <td height="20"><input name="text11" type="text" class="input1" id="text11"  size="10"></td>
    </tr>
    <tr> 
      <td height="20">表格边线颜色：</td>
      <td height="20"><select name="select9"  onChange="text12.style.color=value;text12.focus();text12.value=this.value">
          <option>请选择</option>
          <option style="background:#66CCCC;color: #66CCCC" value="#66CCCC"></option>
          <option style="background:#FFCC33;color: #FFCC33" value="#FFCC33"></option>
          <option style="background:#CCFFFF;color: #CCFFFF" value="#CCFFFF"></option>
          <option style="background:#009900;color: #009900" value="#009900"></option>
          <option style="background:#ff0000;color: #ff0000" value="#ff0000"></option>
          <option style="background:#DDDDDD;color: #DDDDDD" value="#DDDDDD"></option>
          <option style="background:#0099FF;color: #0099FF" value="#0099FF"></option>
          <option style="background:#000000;color: #000000" value="#000000"></option>
          <option value="#000088" style="background:#000088;color: #000088"></option>
          <option style="background:#0000ff;color: #0000ff" value="#0000ff"></option>
          <option style="background:#008800;color: #008800" value="#008800"></option>
          <option style="background:#008888;color: #008888" value="#008888"></option>
          <option style="background:#0088ff;color: #0088ff" value="#0088ff"></option>
          <option style="background:#00a010;color: #00a010" value="#00a010"></option>
          <option style="background:#1100ff;color: #1100ff" value="#1100ff"></option>
          <option style="background:#111111;color: #000000" value="#000000"></option>
          <option style="background:#333333;color: #0099FF" value="#0099FF"></option>
          <option style="background:#50b000;color: #50b000" value="#50b000"></option>
          <option style="background:#880000;color: #880000" value="#8800ff"></option>
          <option style="background:#8800ff;color: #8800ff" value="#8800ff"></option>
          <option style="background:#888800;color: ##FF0000" value="#FF0000"></option>
          <option style="background:#888888;color: #888888" value="#888888"></option>
          <option style="background:#8888ff;color: #8888ff" value="#8888ff"></option>
          <option style="background:#aa00cc;color: #aa00cc" value="#aa00cc"></option>
          <option style="background:#aaaa00;color: #aaaa00" value="#aaaa00"></option>
          <option style="background:#ccaa00;color: #ccaa00" value="#ccaa00"></option>
          <option style="background:#ff0088;color: #ff0088" value="#ff0088"></option>
          <option style="background:#ff00ff;color: #ff00ff" value="#ff00ff"></option>
          <option style="background:#ff8800;color: #ff8800" value="#ff8800"></option>
          <option style="background:#ff0005;color: #ff0005" value="#ff0005"></option>
          <option style="background:#ff88ff;color: #ff88ff" value="#ff88ff"></option>
          <option style="background:#ee0005;color: #ee0005" value="#ee0005"></option>
          <option style="background:#ee01ff;color: #ee01ff" value="#ee01ff"></option>
          <option style="background:#3388aa;color: #3388aa" value="#3388aa"></option>
        </select></td>
      <td height="20"><input name="text12" type="text" class="input1" id="text12"  size="10"></td>
    </tr>
    <tr> 
      <td height="20">表格的宽度：</td>
      <td height="20"><select name="select10">
          <option value="50%">50%</option>
          <option value="70%">70%</option>
          <option value="100%" selected>100%</option>
          <option value="400pix">400pix</option>
          <option value="500pix">500pix</option>
          <option value="600pix">600pix</option>
          <option value="700pix">700pix</option>
        </select></td>
      <td height="20">&nbsp;</td>
    </tr>
    <tr> 
      <td height="20"> <div align="left">默认字体：</div></td>
      <td height="20"><select name="dfont" id="font">
          <option selected value="9">9</option>
          <option value="10.5">10.5</option>
          <option value="11">11</option>
          <option value="12">12</option>
        </select></td>
      <td height="20">&nbsp;</td>
    </tr>
    <tr> 
      <td height="20"> 默认标题字体：</td>
      <td height="20"><select name="titlefont" id="select">
          <option selected value="9">9</option>
          <option value="10.5">10.5</option>
          <option value="11">11</option>
          <option value="12">12</option>
        </select></td>
      <td height="20">&nbsp;</td>
    </tr>
    <tr> 
      <td height="20">标头字体</td>
      <td height="20"><select name="headfont" id="select2">
          <option selected value="9">9</option>
          <option value="10.5">10.5</option>
          <option value="11">11</option>
          <option value="12">12</option>
        </select></td>
      <td height="20">&nbsp;</td>
    </tr>
    <tr> 
      <td height="20">字型：</td>
      <td height="20"><select name="font" id="select3">

		  <option value="宋体" selected>宋体</option>
          <option value="黑体">黑体</option>
          <option value="楷体">楷体</option>
        </select></td>
      <td height="20">&nbsp;</td>
    </tr>
    <tr> 
      <td height="19">加重字体：</td>
      <td height="19"><select name="stress" id="select5">
          <option selected value="9">9</option>
          <option value="10.5">10.5</option>
          <option value="11">11</option>
          <option value="12">12</option>
        </select></td>
      <td height="19">&nbsp;</td>
    </tr>
    <tr> 
      <td height="20">每页显示的行:</td>
      <td height="20"><input name="row" type="text" id="row" size="5"></td>
      <td height="20">&nbsp;</td>
    </tr>
    <tr> 
      <td height="20" colspan="3"> <input name="Submit" type="submit" class="input2" value="提交"> 
        <input name="Submit2" type="submit" class="input2" value="取消"> </td>
    </tr>
    <tr> 
      <td height="20" colspan="3">&nbsp;</td>
    </tr>
  </table>
</form>
</body>
</html>
