package com.htyz;

/*****************************************************************************/
/* ÎÄ¼şÉÏ´«Bean
/*  
/*****************************************************************************/
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;


public class BeanFileManager 
{
	public static final String M_IR_MULTI_DATA_FORM_PARSER = "MultiDataFormParser: ";
	
	public static final String READLINE_IOEXCEPTION = "´Óä¯ÀÀÆ÷¶ÁÈ¡Êı¾İÊ§°Ü£¡";
	public static final String CORRUPTED_INPUT_STREAM = "Êı¾İÁ÷ÖĞ¶Ï£¡";
	public static final String FILE_OUTPUT_STREAM_IOEXCEPTION = "´´½¨ÎÄ¼şÊ§°Ü£¡";
	public static final String CLOSE_IOEXCEPTION = "¹Ø±ÕÎÄ¼şÊ§°Ü£¡";
	public static final String WRITE_IOEXCEPTION = "Ğ´ÎÄ¼şÊ§°Ü£¡";
	public static final String WORKING_DIR_IS_FILE = "Ä¿Â¼ÃûÊÇÒ»¸öÎÄ¼ş";
	public static final String WORKING_DIR_CREATE_ERROR = "²»ÄÜ¹»´´½¨Ä¿Â¼";
	
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

	// ÉÏ´«ÎÄ¼şº¯Êı
	// is         - HTTP ÊäÈëÁ÷
	// streamSize - ÊäÈëÁ÷´óĞ¡
	// workingDir - ÉÏÔØÎÄ¼ş´æ·ÅÄ¿Â¼
	// Èç¹ûformÖĞÓĞtargetFileName£¬ÔòºóÉÏ´«ºó½«ÉÏ´«µÄÎÄ¼şÃû¸ÄÎªtargetFileName(´Ë¹¦ÄÜÖ»ÏŞÓÚ´«µ¥Ò»ÎÄ¼ş
	// Èç¹ûformÖĞÓĞOverWriteOption£¬Ôò»á×Ô¶¯¸²¸ÇÒÑ¾­´æÔÚµÄÎÄ¼ş

	public void uploadFile(ServletInputStream is, int streamSize, String workingDir) throws Exception 
	{
		String parameterName = "";
		String parameterValue = "";
		String parameterContentType = "";
		String parameterContent = "";
		boolean contentNotParse = true, noContentType;
		Integer parameterContentSize;
	
		// µÚÒ»ĞĞÍ¨³£ÊÇÊı¾İ·Ö¸îĞĞ
		byte[] bBuf = new byte[BUF_SIZE];
		int byteRead = is.readLine(bBuf, 0, BUF_SIZE);
		int totalBytes = byteRead;
	
		// ±£ÁôÊı¾İ·Ö¸îĞĞÄÚÈİ
		String dataSeparator = new String(bBuf, 0, byteRead);
		System.out.println(dataSeparator);
		
		while (streamSize > totalBytes) 
		{
			// ½âÎöÎÄ¼ş²ÎÊı
			byteRead = is.readLine(bBuf, 0, BUF_SIZE);
			totalBytes += byteRead;
			String aLine = new String(bBuf, 0, byteRead);
			System.out.println(aLine);
			
			if (byteRead > NAME_STRING.length()) 
			{
				if ((aLine.substring(0, NAME_STRING.length())).equals(NAME_STRING)) 
				{
					// ´æÖüÎÄ¼ş²ÎÊı      
					parameterName = aLine.substring(NAME_STRING.length(), aLine.indexOf("\"", NAME_STRING.length() + 1));
					parameterNames.addElement(parameterName);
	
					// Èç¹û²ÎÊıÖĞ°üº¬ÎÄ¼şÃû£¬ÔòÈ¡µÃÎÄ¼şÃû
					if (parameterName.equals(FILE_STRING)) 
					{
						parameterValue = aLine.substring(aLine.indexOf(FILENAME_STRING) + FILENAME_STRING.length(), aLine.indexOf("\"", aLine.indexOf(FILENAME_STRING) + FILENAME_STRING.length()));
						parameterValues.addElement(parameterValue);
						upLoadedFileName = parameterValue;
					}
				} 
				else // Èç¹û°üÀ¨ÉÏÔØÎÄ¼ş£¬µ«Ã»ÓĞÄÜ½âÎöµ½ÎÄ¼şÃû£¬ÔòÔì³ÉÊı¾İÁ÷ÖĞ¶Ï
					throw new Exception(M_IR_MULTI_DATA_FORM_PARSER + CORRUPTED_INPUT_STREAM + aLine);
			}
			byteRead = is.readLine(bBuf, 0, BUF_SIZE);
			totalBytes += byteRead;
			System.out.println(new String(bBuf, 0, byteRead));
	
			// Èç¹ûÎªÎÄ¼ş£¬ÔòÕâÀï°üº¬ÎÄ¼şµÄĞÅÏ¢£¬·ñÔòÎª¿ÕĞĞ
			if (parameterName.equals(FILE_STRING)) 
			{
				noContentType = true;
	
				// Èç¹û²»ÊÇ¿ÕĞĞ
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
	
						// ¶ÁÈ¡ÏÂÒ»¸ö¿ÕĞĞ
						byteRead = is.readLine(bBuf, 0, BUF_SIZE);
						totalBytes += byteRead;
						System.out.println(new String(bBuf, 0, byteRead));
					}
				}
	
				// Èç¹ûÎª¿ÕĞĞÔò½«ÄÚÈİÔªËØÉèÖÃÎª¿Õ
				if (noContentType) 
					parameterContentTypes.addElement(null);
	
				// Èç¹ûÎÄ¼ş´æÔÚ
				if (parameterValue.length() > 0) 
				{
					String targetName = getParameter("targetFileName");
					workingDir = workingDir.replace('\\', '/');
	
					//Èç¹ûÉÏÔØÄ¿Â¼²»´æÔÚ£¬Ôò´´½¨Ä¿Â¼
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
						throw new Exception(M_IR_MULTI_DATA_FORM_PARSER + "ÎÄ¼ş£¨" + targetName.substring(targetName.lastIndexOf("/") + 1, targetName.length()) + "£©ÒÑ¾­´æÔÚ!ÉÏ´«Ê§°Ü£¡");

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
				else // ÎÄ¼ş²»´æÔÚ
				{
					// ½«ÄÚÈİÔªËØºÍ´óĞ¡ÉèÖÃÎªnull»ò0
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
			else // Èç¹û²»ÊÇÎÄ¼ş
			{
				// ºöÂÔ¿ÕĞĞ£
				parameterContentTypes.addElement(null);
	
				parameterContents.addElement(null);
				parameterContentSize = null;
				parameterContentSize = new Integer(0);
				parameterContentSizes.addElement(parameterContentSize);
	
				// »ñÈ¡²ÎÊıÃûºÍ²ÎÊıÖµ
				byteRead = is.readLine(bBuf, 0, BUF_SIZE);
				totalBytes += byteRead;
				aLine = null;
				aLine = new String(bBuf, 0, byteRead);
				parameterValue = aLine.substring(0, aLine.length() - 2);
				parameterValues.addElement(parameterValue);
	
				// Êı¾İ·Ö¸îĞĞ
				byteRead = is.readLine(bBuf, 0, BUF_SIZE);
				totalBytes += byteRead;
			}
		}
	}
	//
	//»ñÈ¡Ö¸¶¨²ÎÊıµÄÖµ£¬Èç¹û²ÎÊı²»´æÔÚ£¬Ôò·µ»Ønull
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
