<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<%@page language="java" import="com.htyz.*"%>
<jsp:useBean id="ets" scope="request" class="com.htyz.beanElearnTools"/>
<jsp:useBean id="sqlbean0" scope="request" class="com.htyz.beanGetdata"/>
<%
String userright= (String)session.getAttribute("right");
if(userright==null||(String)session.getAttribute("userid")==null){%>
	<script language="JavaScript">
		alert("����û�е�¼���������ѳ�ʱ�������µ�¼��");
		document.location="<%=request.getContextPath()%>/login.jsp";
	</script>
	<jsp:forward page="login.jsp" />
<%}
%>
<script language="JavaScript" src="Page_Tab_core.js"></script>
<script language="JavaScript">
//  �����÷�����Ҫ�����ҳ�ļ����Ƿ�Ҫ������no/auto/yes��ҳ����ı�ǩ����ҳ˵����
<%

int i,j=0;
String menunamec[]=new String[20];
String menuurl[]=new String[20];
String menualt[]=new String[20];
String menuright[]=new String[20];

sqlbean0.executeSelect("select * from t_menu order by menu_id");
if(sqlbean0.getRowCount()<0){
}else{
	for(i=0;i<sqlbean0.getRowCount();i++){
		menunamec[i]=sqlbean0.getFieldValue("menu_namec",i);
		menuurl[i]=sqlbean0.getFieldValue("menu_url",i);
		menualt[i]=sqlbean0.getFieldValue("menu_namee",i);
		menuright[i]=sqlbean0.getFieldValue("menu_right",i);
	}
}
for(i=0;i<sqlbean0.getRowCount();i++){
	if(ets.isMenuShow(userright,menuright[i])){
%>
		Pages[<%=j%>]=new Array("<%=menuurl[i]%>","auto","<%=menunamec[i]%>","<%=menualt[i]%>");
<%
		j=j+1;
	}
}

%>
Controler_Height=28	
DrawFrames()
</script>
