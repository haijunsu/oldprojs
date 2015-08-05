<%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<table cellspacing="0" cellpadding="0" width="771" border="0">
   <tr>
      <td class="x" valign="top" width="376">
      <img src="<%=request.getContextPath()%>/img/home1.GIF" width="375" height="67"></td>
      <td align="right" width="396">
      <img height="72" src="<%=request.getContextPath()%>/img/rw2.gif" width="312" border="0"><a href="http://thehomeworld.com/english/default.htm"><img height="20" src="<%=request.getContextPath()%>/img/english.gif" width="57" border="0"></a></td>
   </tr>
   <tr>
      <td class="f20" width="376" bgcolor="#ee0313">　 
      <script language="JavaScript">          
          today=new Date(); 
          function initArray(){ 
            this.length=initArray.arguments.length 
            for(var i=0;i<this.length;i++) 
            this[i+1]=initArray.arguments[i]  } 
            var d=new initArray( 
              "星期日", 
              "星期一", 
              "星期二", 
              "星期三", 
              "星期四", 
              "星期五", 
              "星期六"); 
         document.write( 
              "<font color=#ffffff style='font-size:9pt;'> ", 
              today.getYear(),"年", 
              today.getMonth()+1,"月", 
              today.getDate(),"日   ", 
              d[today.getDay()+1], 
              "</font>" );  
          </script>
      </td>
      <td valign="top" align="middle" width="396" bgcolor="#ee0313">
      <img src="<%=request.getContextPath()%>/img/homeword.gif" width="374" height="34"></td>
   </tr>
</table>

