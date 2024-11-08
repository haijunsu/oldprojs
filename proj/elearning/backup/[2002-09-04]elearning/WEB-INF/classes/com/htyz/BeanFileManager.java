package com.htyz;

/*****************************************************************************/
/* 文件上传Bean
/*  
/*****************************************************************************/
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;


public class BeanFileManager 
{
	public static final String M_IR_MULTI_DATA_FORM_PARSER = "MultiDataFormParser: ";
	
	public static final String READLINE_IOEXCEPTION = "从浏览器读取数据失败！";
	public static final String CORRUPTED_INPUT_STREAM = "数据流中断！";
	public static final String FILE_OUTPUT_STREAM_IOEXCEPTION = "创建文件失败！";
	public static final String CLOSE_IOEXCEPTION = "关闭文件失败！";
	public static final String WRITE_IOEXCEPTION = "写文件失败！";
	public static final String WORKING_DIR_IS_FILE = "目录名是一个文件";
	public static final String WORKING_DIR_CREATE_ERROR = "不能够创建目录";
	
	public static final int BUF_SIZE = 409600;
	public static final String NAME_STRING = "Content-Disposition: form-data; name=\"";
	public static final String FILE_STRING = "file";
	public static final String FILENAME_STRING = "\"; filename=\"";
	public static final String CONTENT_TYPE_STRING = "Content-Type:";
	
	protected Vector parameterNames        = new Vector(1);
	protected Vector parameterValues       = new Vector(1);
	protected Vector parameterContentTypes = new Vector(1);
	protected Vector parameterContents     = new Vector(1);
	protected Vector parameterContentSizes = new Vector(1);
	
	protected String upLoadedFileName = null;

	public BeanFileManager()
	{
		
	}

	// 上传文件函数
	// is         - HTTP 输入流
	// streamSize - 输入流大小
	// workingDir - 上载文件存放目录
	// 如果form中有targetFileName，则后上传后将上传的文件名改为targetFileName(此功能只限于传单一文件
	// 如果form中有OverWriteOption，则会自动覆盖已经存在的文件

	public void uploadFile(ServletInputStream is, int streamSize, String workingDir) throws Exception 
	{
		String parameterName = "";
		String parameterValue = "";
		String parameterContentType = "";
		String parameterContent = "";
		boolean contentNotParse = true, noContentType;
		Integer parameterContentSize;
	
		// 第一行通常是数据分割行
		byte[] bBuf = new byte[BUF_SIZE];
		int byteRead = is.readLine(bBuf, 0, BUF_SIZE);
		int totalBytes = byteRead;
	
		// 保留数据分割行内容
		String dataSeparator = new String(bBuf, 0, byteRead);
		System.out.println(dataSeparator);
		
		while (streamSize > totalBytes) 
		{
			// 解析文件参数
			byteRead = is.readLine(bBuf, 0, BUF_SIZE);
			totalBytes += byteRead;
			String aLine = new String(bBuf, 0, byteRead);
			System.out.println(aLine);
			
			if (byteRead > NAME_STRING.length()) 
			{
				if ((aLine.substring(0, NAME_STRING.length())).equals(NAME_STRING)) 
				{
					// 存贮文件参数      
					parameterName = aLine.substring(NAME_STRING.length(), aLine.indexOf("\"", NAME_STRING.length() + 1));
					parameterNames.addElement(parameterName);
	
					// 如果参数中包含文件名，则取得文件名
					if (parameterName.equals(FILE_STRING)) 
					{
						parameterValue = aLine.substring(aLine.indexOf(FILENAME_STRING) + FILENAME_STRING.length(), aLine.indexOf("\"", aLine.indexOf(FILENAME_STRING) + FILENAME_STRING.length()));
						parameterValues.addElement(parameterValue);
						upLoadedFileName = parameterValue;
					}
				} 
				else // 如果包括上载文件，但没有能解析到文件名，则造成数据流中断
					throw new Exception(M_IR_MULTI_DATA_FORM_PARSER + CORRUPTED_INPUT_STREAM + aLine);
			}
			byteRead = is.readLine(bBuf, 0, BUF_SIZE);
			totalBytes += byteRead;
			System.out.println(new String(bBuf, 0, byteRead));
	
			// 如果为文件，则这里包含文件的信息，否则为空行
			if (parameterName.equals(FILE_STRING)) 
			{
				noContentType = true;
	
				// 如果不是空行
				while (byteRead != 2) {
					if (byteRead > CONTENT_TYPE_STRING.length()) 
					{
						aLine = null;
						aLine = new String(bBuf, 0, byteRead);
						System.out.println(aLine);
						if ((aLine.substring(0, CONTENT_TYPE_STRING.length())).equals(CONTENT_TYPE_STRING)) 
						{
							parameterContentType = aLine.substring(CONTENT_TYPE_STRING.length(), aLine.length());
							parameterContentType = parameterContentType.trim();
							parameterContentTypes.addElement(parameterContentType);
							noContentType = false;
						}
	
						// 读取下一个空行
						byteRead = is.readLine(bBuf, 0, BUF_SIZE);
						totalBytes += byteRead;
						System.out.println(new String(bBuf, 0, byteRead));
					}
				}
	
				// 如果为空行则将内容元素设置为空
				if (noContentType) 
					parameterContentTypes.addElement(null);
	
				// 如果文件存在
				if (parameterValue.length() > 0) 
				{
					String targetName = getParameter("targetFileName");
					workingDir = workingDir.replace('\\', '/');
	
					//如果上载目录不存在，则创建目录
					File workDir = new File(workingDir);
					if (!workDir.isDirectory())
						workDir.mkdirs();
	
					if (!workingDir.endsWith("/"))
						workingDir = workingDir + "/";
	
					upLoadedFileName = upLoadedFileName.replace('\\', '/');
					if (targetName == null || targetName.trim().equals("")) 
						targetName = upLoadedFileName.substring(upLoadedFileName.lastIndexOf("/") + 1, upLoadedFileName.length());
					
					targetName = workingDir + targetName;
					
					File targetFile = new File(targetName);
					if (getParameter("OverWriteOption") == null && targetFile.exists()) 
						throw new Exception(M_IR_MULTI_DATA_FORM_PARSER + "文件（" + targetName.substring(targetName.lastIndexOf("/") + 1, targetName.length()) + "）已经存在!上传失败！");

					FileOutputStream fileOut = new FileOutputStream(targetFile);
					int totalFileBytes = 0;
					do {
						byteRead = is.readLine(bBuf, 0, BUF_SIZE);
						totalBytes += byteRead;
						totalFileBytes += byteRead;
						aLine = new String(bBuf, 0, byteRead);
	
						if (aLine.length() == dataSeparator.length()) 
						{
							if (aLine.equals(dataSeparator)) 
							{
								fileOut.close();
								parameterContents.addElement(parameterContent);
								parameterContentSize = null;
								totalFileBytes -= byteRead;
								parameterContentSize = new Integer(totalFileBytes);
								parameterContentSizes.addElement(parameterContentSize);
								contentNotParse = false;
							}
						} 
						else if (aLine.length() > dataSeparator.length()) 
						{
							if (aLine.substring(0, dataSeparator.length() - 2).equals(dataSeparator.substring(0, dataSeparator.length() - 2))) 
							{
								fileOut.close();
								parameterContents.addElement(parameterContent);
								parameterContentSize = null;
								totalFileBytes -= byteRead;
								parameterContentSize = new Integer(totalFileBytes);
								parameterContentSizes.addElement(parameterContentSize);
								contentNotParse = false;
							}
						}
	
						if (contentNotParse == true) 
							fileOut.write(bBuf, 0, byteRead);
					} 
					while (contentNotParse);
					
					contentNotParse = true;
				} 
				else // 文件不存在
				{
					// 将内容元素和大小设置为null或0
					parameterContents.addElement(null);
					parameterContentSize = null;
					parameterContentSize = new Integer(0);
					parameterContentSizes.addElement(parameterContentSize);
	
					do {
						byteRead = is.readLine(bBuf, 0, BUF_SIZE);
						totalBytes += byteRead;
						aLine = new String(bBuf, 0, byteRead);
						if (aLine.length() == dataSeparator.length()) 
						{
							if (aLine.equals(dataSeparator)) 
								contentNotParse = false;
						} 
						else if (aLine.length() > dataSeparator.length()) 
						{
							if (aLine.substring(0, dataSeparator.length() - 2).equals(dataSeparator.substring(0, dataSeparator.length() - 2)))
								contentNotParse = false;
						}
					} while (contentNotParse); 
	
					contentNotParse = true;
				}
			} 
			else // 如果不是文件
			{
				// 忽略空行�
				parameterContentTypes.addElement(null);
	
				parameterContents.addElement(null);
				parameterContentSize = null;
				parameterContentSize = new Integer(0);
				parameterContentSizes.addElement(parameterContentSize);
	
				// 获取参数名和参数值
				byteRead = is.readLine(bBuf, 0, BUF_SIZE);
				totalBytes += byteRead;
				aLine = null;
				aLine = new String(bBuf, 0, byteRead);
				parameterValue = aLine.substring(0, aLine.length() - 2);
				parameterValues.addElement(parameterValue);
	
				// 数据分割行
				byteRead = is.readLine(bBuf, 0, BUF_SIZE);
				totalBytes += byteRead;
			}
		}
	}
	//
	//获取指定参数的值，如果参数不存在，则返回null
	public String getParameter(String parameterName)
	{
		int parameterIndex = parameterNames.indexOf(parameterName);
		return (parameterIndex < 0) ? null : (String) parameterValues.elementAt(parameterIndex);
	}
	
	public Vector displayFiles(String folder) throws Exception
	{
		folder = folder.replace('\\', '/');
		if (!folder.endsWith("/")) 
			folder = folder + "/";
		
		File userFile = new File(folder.substring(0, folder.length() - 1));
		Vector filesVector = new Vector();
		
	
	  	if (userFile.isDirectory())
	  	{
			String list[] = userFile.list();
			int count = list.length;
			
			for (int i = 0; i < count; i++)
		 	{
		   		filesVector.addElement(new File(folder +  list[i]));
		   	}   
		}
		else 
		{
			userFile.mkdirs();
		}      
		
		return filesVector;
	}
}
