package com.htyz;

/*****************************************************************************/
/* �ļ��ϴ�Bean
/*  
/*****************************************************************************/
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;


public class BeanFileManager 
{
	public static final String M_IR_MULTI_DATA_FORM_PARSER = "MultiDataFormParser: ";
	
	public static final String READLINE_IOEXCEPTION = "���������ȡ����ʧ�ܣ�";
	public static final String CORRUPTED_INPUT_STREAM = "�������жϣ�";
	public static final String FILE_OUTPUT_STREAM_IOEXCEPTION = "�����ļ�ʧ�ܣ�";
	public static final String CLOSE_IOEXCEPTION = "�ر��ļ�ʧ�ܣ�";
	public static final String WRITE_IOEXCEPTION = "д�ļ�ʧ�ܣ�";
	public static final String WORKING_DIR_IS_FILE = "Ŀ¼����һ���ļ�";
	public static final String WORKING_DIR_CREATE_ERROR = "���ܹ�����Ŀ¼";
	
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

	// �ϴ��ļ�����
	// is         - HTTP ������
	// streamSize - ��������С
	// workingDir - �����ļ����Ŀ¼
	// ���form����targetFileName������ϴ����ϴ����ļ�����ΪtargetFileName(�˹���ֻ���ڴ���һ�ļ�
	// ���form����OverWriteOption������Զ������Ѿ����ڵ��ļ�

	public void uploadFile(ServletInputStream is, int streamSize, String workingDir) throws Exception 
	{
		String parameterName = "";
		String parameterValue = "";
		String parameterContentType = "";
		String parameterContent = "";
		boolean contentNotParse = true, noContentType;
		Integer parameterContentSize;
	
		// ��һ��ͨ�������ݷָ���
		byte[] bBuf = new byte[BUF_SIZE];
		int byteRead = is.readLine(bBuf, 0, BUF_SIZE);
		int totalBytes = byteRead;
	
		// �������ݷָ�������
		String dataSeparator = new String(bBuf, 0, byteRead);
		System.out.println(dataSeparator);
		
		while (streamSize > totalBytes) 
		{
			// �����ļ�����
			byteRead = is.readLine(bBuf, 0, BUF_SIZE);
			totalBytes += byteRead;
			String aLine = new String(bBuf, 0, byteRead);
			System.out.println(aLine);
			
			if (byteRead > NAME_STRING.length()) 
			{
				if ((aLine.substring(0, NAME_STRING.length())).equals(NAME_STRING)) 
				{
					// �����ļ�����      
					parameterName = aLine.substring(NAME_STRING.length(), aLine.indexOf("\"", NAME_STRING.length() + 1));
					parameterNames.addElement(parameterName);
	
					// ��������а����ļ�������ȡ���ļ���
					if (parameterName.equals(FILE_STRING)) 
					{
						parameterValue = aLine.substring(aLine.indexOf(FILENAME_STRING) + FILENAME_STRING.length(), aLine.indexOf("\"", aLine.indexOf(FILENAME_STRING) + FILENAME_STRING.length()));
						parameterValues.addElement(parameterValue);
						upLoadedFileName = parameterValue;
					}
				} 
				else // ������������ļ�����û���ܽ������ļ�����������������ж�
					throw new Exception(M_IR_MULTI_DATA_FORM_PARSER + CORRUPTED_INPUT_STREAM + aLine);
			}
			byteRead = is.readLine(bBuf, 0, BUF_SIZE);
			totalBytes += byteRead;
			System.out.println(new String(bBuf, 0, byteRead));
	
			// ���Ϊ�ļ�������������ļ�����Ϣ������Ϊ����
			if (parameterName.equals(FILE_STRING)) 
			{
				noContentType = true;
	
				// ������ǿ���
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
	
						// ��ȡ��һ������
						byteRead = is.readLine(bBuf, 0, BUF_SIZE);
						totalBytes += byteRead;
						System.out.println(new String(bBuf, 0, byteRead));
					}
				}
	
				// ���Ϊ����������Ԫ������Ϊ��
				if (noContentType) 
					parameterContentTypes.addElement(null);
	
				// ����ļ�����
				if (parameterValue.length() > 0) 
				{
					String targetName = getParameter("targetFileName");
					workingDir = workingDir.replace('\\', '/');
	
					//�������Ŀ¼�����ڣ��򴴽�Ŀ¼
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
						throw new Exception(M_IR_MULTI_DATA_FORM_PARSER + "�ļ���" + targetName.substring(targetName.lastIndexOf("/") + 1, targetName.length()) + "���Ѿ�����!�ϴ�ʧ�ܣ�");

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
				else // �ļ�������
				{
					// ������Ԫ�غʹ�С����Ϊnull��0
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
			else // ��������ļ�
			{
				// ���Կ��У
				parameterContentTypes.addElement(null);
	
				parameterContents.addElement(null);
				parameterContentSize = null;
				parameterContentSize = new Integer(0);
				parameterContentSizes.addElement(parameterContentSize);
	
				// ��ȡ�������Ͳ���ֵ
				byteRead = is.readLine(bBuf, 0, BUF_SIZE);
				totalBytes += byteRead;
				aLine = null;
				aLine = new String(bBuf, 0, byteRead);
				parameterValue = aLine.substring(0, aLine.length() - 2);
				parameterValues.addElement(parameterValue);
	
				// ���ݷָ���
				byteRead = is.readLine(bBuf, 0, BUF_SIZE);
				totalBytes += byteRead;
			}
		}
	}
	//
	//��ȡָ��������ֵ��������������ڣ��򷵻�null
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
