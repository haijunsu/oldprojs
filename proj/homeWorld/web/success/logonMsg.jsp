 <%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);

%>       <html>
		<html:base />
        <%
        
			String strId = (String)session.getAttribute("userid");
 			String strUserid = (String)session.getAttribute("userid");
 			String strUsername = (String)session.getAttribute("username");
 			String strLifeCycle = (String) request.getAttribute("lifecycle");
 			if (strLifeCycle == null) strLifeCycle = "";
 			String strMsg = "";
 			String redirectUrl = request.getContextPath();
			if (strUsername == null) {
				strMsg = "<br>״̬�����Ѿ��ɹ��˳�<br><ul><li><a href=" + request.getContextPath() + ">����</a>��վ��ҳ</li></ul>";
			} else {
				strMsg = "<br>״̬�����Ѿ���¼�ɹ�<br>" + strLifeCycle + "<br><ul><li><a href=javascript:welcome()>����ϵͳ</a></li></ul>";
				redirectUrl += "/crisWelcome.jsp";
			}
        %>
		
        <head>
        <style type="text/css">
        <!--
        a {color:#333333;font-size: 9pt;text-decoration:none; }
        a:hover {color:#000066;text-decoration:underline overline;}
        td{FONT-SIZE: 9pt; color:#333333; font-family:����}
        -->
        </style>
        <title>��Ϣ</title>
        <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
        <!--meta HTTP-EQUIV=REFRESH CONTENT='4; URL=<%=redirectUrl%>'-->
        <script language="Javascript">
			<!-- 
			if (top.location !== self.location) {
				top.location=self.location;
			}
			
			//-->
		</script>
		<script language="Javascript">
                function Show(divid) {
                divid.filters.revealTrans.apply(); 
                divid.style.visibility = "visible"; 
                divid.filters.revealTrans.play(); 
        }
        function Hide(divid) {
                divid.filters.revealTrans.apply();
                divid.style.visibility = "hidden";
                divid.filters.revealTrans.play();
<%
				if (strId == null || strUserid == null ||!strId.equals(strUserid)) {
%> 
				window.location='<%=request.getContextPath()%>';              
<%
				} else {
%>                
               welcome();
<%
				}
%>                
        }
        function welcome(){
        	document.welcome.submit();
        }
        </script>
        
        </head>

        <body bgcolor=FEFEFF Onload="Show(msgboard)">
        <form id='welcome' name='welcome' method='post' action='<%=request.getContextPath()%>/Welcomepage'>
        	<input type='hidden' id='userid' name='userid' value='<%=strUserid%>'></input>
        </form>
        <div id="msgboard" style="FILTER: revealTrans(transition=23,duration=0.5) blendTrans(duration=0.5); position:absolute; left:210px; top:150px; width:350px; height:100px; z-index:1; visibility: hidden">
            <table width="100%" border=0 cellspacing=0 cellpadding=6 bgcolor=E0E0E3>
             <tr>
              <td><%=strMsg%></td>
            </tr>
          </table>
        </div>
        <script>
        setTimeout('Hide(msgboard)',3000);
        </script>
        </body>
        </html>