<%@page contentType="text/html;charset=GB2312"%>
<%@page language="java" import="com.htyz.*"%>
<html>
<head>
<title>�γ̹���</title>

<link rel="stylesheet" href="../xbz.css" type="text/css">
<jsp:useBean id="sqlbean" scope="request" class="com.htyz.beanGetdata"/>

<script language="Javascript">
<!--
function changecat(){
var catname=new Array();
var catid=new Array();
var catd=new Array();
var desc=new Array();
var id=new Array();
var biaozhun=new Array();
var classtime=new Array();
var i;
<%sqlbean.executeSelect("select * from tclass");%>
count=0
<%for (int i=0;i<sqlbean.getRowCount();i++)
{
%>
id[count]="<%=sqlbean.getFieldValue("class_id",i)%>";
catname[count]="<%=sqlbean.getFieldValue("class_name",i)%>";
catid[count]="<%=sqlbean.getFieldValue("class_type",i)%>";
catd[count]="<%=sqlbean.getFieldValue("keywords",i)%>";
desc[count]="<%=sqlbean.getFieldValue("detil",i)%>";
biaozhun[count]="<%=sqlbean.getFieldValue("pass_stander",i)%>";
classtime[count]="<%=sqlbean.getFieldValue("class_time",i)%>";
count=count+1
<%}%>
for(var i=0;i<id.length;i++)
  {
   if(id[i]==document.form2.id.value){
     document.form2.id.value=id[i];
	  document.form2.name.value=catname[i];
	  document.form2.di.value=catid[i];
	  document.form2.key.value=catd[i];
	  document.form2.desc.value=desc[i];
	  document.form2.biaozhun.value=biaozhun[i];
	  document.form2.classtime.value=classtime[i];
	 }
}
}
function dinit(){
    changecat(document.form2.id.value);
    document.form1.name.focus();
}


function getthis(){
var ss1=new Array();
var ss2=new Array();
var ss3=new Array();
var i;
<%sqlbean.executeSelect("select * from tcode where code_id in(select code_value from tcode where code_id='0001')");%>

<%for (int i=0;i<sqlbean.getRowCount();i++)
{
%>
ss1[<%=i%>]="<%=sqlbean.getFieldValue("code_id",i)%>";
ss2[<%=i%>]="<%=sqlbean.getFieldValue("code_value",i)%>";
ss3[<%=i%>]="<%=sqlbean.getFieldValue("code_namec",i).trim()%>";
<%}%>
for(i=form1.select2.options.length;i>=0;i--)
         { form1.select2.remove(i)}

for (i=0;i<ss1.length;i++){
 if (ss1[i]==document.form1.select1.value){
  var objEntry = document.createElement("OPTION")
               objEntry.text = ss3[i];
               objEntry.value=ss2[i];
               form1.select2.add(objEntry);
               //set objEntry=nothing
  }         
}

}
-->
</script>

</head>

<body bgcolor="#FFFFFF" text="#000000" onload=dinit()>

<%@ include file="/MsgNotify.jsp"%>

<form name="form1" method="post" action="saveclass.jsp?act=add">
  <table width="100%" border="0" cellspacing="0" cellpadding="1">
    <tr> 
      <td bgcolor="#ffffff"> 
        <table width="100%" border="0" cellspacing="1" cellpadding="0" class="xbz">
          <tr bgcolor="#cccccc"> 
            <td colspan="2" height="20"> �γ̵���ӣ�</td>
          </tr>
          <tr> 
            <td bgcolor="#eeeeee" height="20">�γ����ƣ�</td>
            <td bgcolor="#eeeeee" height="20"> 
              <input type="text" name="name" maxlength="50" size="15">
            </td>
          </tr>
          <tr> 
            <td bgcolor="#eeeeee" height="20">�γ̷��ࣺ</td>
            <td bgcolor="#eeeeee" height="20"> 
              <select name="select1" onClick="getthis()">
                <%sqlbean.executeSelect("select * from tcode where code_id='0001'");%>
                <option value="0">ѡ������</option>
                <%for (int i=0;i<sqlbean.getRowCount();i++){%>
                <option value="<%=sqlbean.getFieldValue("code_value",i)%>"><%=sqlbean.getFieldValue("code_namec",i)%></option>
                <%}%>
              </select>
              <select name="select2">
           
     
              </select>
            </td>
          </tr>
          <tr>
            <td bgcolor="#eeeeee" height="20">�γ̵����ͣ�</td>
            <td bgcolor="#eeeeee" height="20">
			<%sqlbean.executeSelect("select * from tcode where code_id='0007'");
			for (int i=0;i<sqlbean.getRowCount();i++){
			
			%>
              <input type="radio" name="di" value="<%=sqlbean.getFieldValue("code_value",i)%>"><%=sqlbean.getFieldValue("code_namec",i)%>
           <%}%>
			</td>
          </tr>
          <tr> 
            <td bgcolor="#eeeeee" height="20">�ؼ��֣�</td>
            <td bgcolor="#eeeeee" height="20"> 
              <textarea name="key" cols="30" rows="5"></textarea>
            </td>
          </tr>
          <tr> 
            <td bgcolor="#eeeeee" height="20">������</td>
            <td bgcolor="#eeeeee" height="20"> 
              <textarea name="desc" cols="30" rows="5"></textarea>
            </td>
          </tr>
          <tr> 
            <td bgcolor="#eeeeee" height="20">��ʱ��</td>
            <td bgcolor="#eeeeee" height="20">
              <input type="text" name="classtime" size="15">
            </td>
          </tr>
          <tr> 
            <td bgcolor="#eeeeee" height="20">ͨ����׼��</td>
            <td bgcolor="#eeeeee" height="20"> 
              <input type="text" name="biaozhun">
            </td>
          </tr>
          <tr bgcolor="#eeeeee"> 
            <td colspan="2" height="20"> 
              <input type="submit" name="Submit" value="���">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
<form name="form2" method="post" action="saveclass.jsp?act=mod">
  <table width="100%" border="0" cellspacing="0" cellpadding="1">
    <tr> 
      <td bgcolor="#ffffff"> 
        <table width="100%" border="0" cellspacing="1" cellpadding="0" class="xbz">
          <tr bgcolor="#cccccc"> 
            <td colspan="2" height="20">�γ̵��޸ģ�</td>
          </tr>
          <tr> 
            <td bgcolor="#eeeeee" height="20">Ҫ�޸ĵĿγ̣�</td>
            <td bgcolor="#eeeeee" height="20"> 
              <select name="id" class="xbz" onchange=changecat()>
                <%sqlbean.executeSelect("select * from tclass where class_status='1'");
			  for (int i=0; i<sqlbean.getRowCount(); i++)
			  {
			   %>
                <option value="<%=sqlbean.getFieldValue("class_id",i)%>"><%=sqlbean.getFieldValue("class_name",i)%></option>
                <%}%>
              </select>
            </td>
          </tr>
          <tr> 
            <td bgcolor="#eeeeee" height="26">�γ����ƣ�</td>
            <td bgcolor="#eeeeee" height="26"> 
              <input type="text" name="name" maxlength="50" size="15">
            </td>
          </tr>
          <tr> 
            <td bgcolor="#eeeeee" height="20">�γ����ͣ�</td>
            <td bgcolor="#eeeeee" height="20"> 
              <%sqlbean.executeSelect("select * from tcode where code_id='0007'");
			for (int i=0;i<sqlbean.getRowCount();i++){
			
			%>
              <input type="radio" name="di" value="<%=sqlbean.getFieldValue("code_value",i)%>">
              <%=sqlbean.getFieldValue("code_namec",i)%> 
              <%}%>
            </td>
          </tr>
          <tr> 
            <td bgcolor="#eeeeee" height="20">�ؼ��֣�</td>
            <td bgcolor="#eeeeee" height="20"> 
              <textarea name="key" cols="30" rows="5"></textarea>
            </td>
          </tr>
          <tr> 
            <td bgcolor="#eeeeee" height="20">������</td>
            <td bgcolor="#eeeeee" height="20"> 
              <textarea name="desc" cols="30" rows="5"></textarea>
            </td>
          </tr>
          <tr>
            <td bgcolor="#eeeeee" height="20">��ʱ��</td>
            <td bgcolor="#eeeeee" height="20">
              <input type="text" name="classtime" size="15">
            </td>
          </tr>
          <tr> 
            <td bgcolor="#eeeeee" height="20">ͨ����׼��</td>
            <td bgcolor="#eeeeee" height="20"> 
              <input type="text" name="biaozhun">
            </td>
          </tr>
          <tr> 
            <td bgcolor="#eeeeee" height="20" colspan="2"> 
              <input type="submit" name="Submit2" value="�޸�">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
<form name="form3" method="post" action="saveclass.jsp?act=del">
  <table width="100%" border="0" cellspacing="0" cellpadding="1">
    <tr> 
      <td bgcolor="#ffffff"> 
        <table width="100%" border="0" cellspacing="1" cellpadding="0" class="xbz">
          <tr bgcolor="#cccccc"> 
            <td colspan="2" height="20">�γ̵�ɾ����</td>
          </tr>
          <tr> 
            <td bgcolor="#eeeeee" height="20">�γ����ƣ�</td>
            <td bgcolor="#eeeeee" height="20"> 
              <select name="id" class="xbz">
                <%sqlbean.executeSelect("select * from tclass where class_status='1'");
			  for (int i=0; i<sqlbean.getRowCount(); i++)
			  {
			   %>
                <option value="<%=sqlbean.getFieldValue("class_id",i)%>"><%=sqlbean.getFieldValue("class_name",i)%></option>
              <%}%>
			    </select>
            </td>
          </tr>
          <tr> 
            <td bgcolor="#eeeeee" height="20" colspan="2"> 
              <input type="submit" name="Submit22" value="ɾ��">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
