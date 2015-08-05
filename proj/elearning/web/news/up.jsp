<%@page contentType="text/html;charset=GB2312"%>
<%@ page language="java" import="com.htyz.fileUtil.*"%>
<jsp:useBean id="mySmartUpload" scope="request" class="com.htyz.fileUtil.UploadFiles" />
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
		
		//out.println(mySmartUpload.getFiles().getFile(0).getFileName());
		
		//filename="http://"+"/upload/mysmartUpload.getFieldName()"
		// Save the files with their original names in the virtual path "/upload"
		// count = mySmartUpload.save("/upload", mySmartUpload.SAVE_VIRTUAL);

		// Display the number of files uploaded 
		out.println(count + " file(s) uploaded.");

	} catch (Exception e) { 
	out.println(e.toString());
	}
%>
