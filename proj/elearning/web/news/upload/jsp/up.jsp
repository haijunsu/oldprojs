<%@page contentType="text/html;charset=GB2312"%>
<%@ page language="java" import="com.jspsmart.upload.*"%>
<jsp:useBean id="mySmartUpload" scope="page" class="com.jspsmart.upload.SmartUpload" />

<HTML>
<BODY BGCOLOR="white">
<%

	// Variables
	int count=0;        

	// Initialization
	mySmartUpload.initialize(pageContext);



	// Upload	
	mySmartUpload.upload();
	

	try {

		// Save the files with their original names in the virtual path "/upload"
		// if it doesn't exist try to save in the physical path "/upload"
	  	count = mySmartUpload.save(request.getContextPath() + "/news/upload");
		
		out.println(mySmartUpload.getFiles().getFile(0).getFileName());
		
		//filename="http://"+"/upload/mysmartUpload.getFieldName()"
		// Save the files with their original names in the virtual path "/upload"
		// count = mySmartUpload.save("/upload", mySmartUpload.SAVE_VIRTUAL);

		// Display the number of files uploaded 
		out.println(count + " file(s) uploaded.");

	} catch (Exception e) { 
	out.println(e.toString());
	}
	
%>
<script>
    alert("<%=mySmartUpload.getFiles().getFile(0).getFileName()%>文件被上传！");
	/*  还要把上传的文件名称加入到上传界面的下拉列表中  */
	window.opener.form1.file1.length=0;  /*  初始化列表框  */
    window.opener.form1.file1.length+=1;
    window.opener.form1.file1.options[window.opener.form1.file1.length-1].text="/elearning/news/upload/<%=mySmartUpload.getFiles().getFile(0).getFileName()%>";
	window.opener.form1.file1.options[window.opener.form1.file1.length-1].selected=true;
    window.close();
</script>
</BODY>
</HTML>
