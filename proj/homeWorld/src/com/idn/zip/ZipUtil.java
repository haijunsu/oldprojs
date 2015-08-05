/*
 * @(#)ZipUtil.java  2003-1-10
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */

package com.idn.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import com.idn.log.LogWrapper;
import com.idn.property.SystemProperties;

/** 
 * 
 * ����ѹ���ͽ�ѹ��ZIP�ļ���Ŀǰ�в���֧�ִ��п�������ZIP�ļ�
 *
 * @version ver 0.1
 * @author name: �պ���
 */

public class ZipUtil {
	static final int BUFFER = 2048;
	static final String FILE_SEPARATOR = SystemProperties.FILE_SEPARATOR;
    private static LogWrapper log = new LogWrapper(ZipUtil.class);
   
    /**
     * <P>��ѹ��ָ����ZIP�ļ�����ǰ��Ŀ¼<BR>
     * @param zipFileName ����ѹ���ļ���<BR>
     * <BR>
     * @return  1 �ɹ� <BR>
     *         -1 ʧ�� <BR>
     *         -2 �ļ������� <BR>
     *         -3 ָ�������Ʋ����ļ� <BR>
     */
	
	public static int unzip(String zipFileName) {
		return unzip(zipFileName, null, null);
	}

	/**
     * <P>��ѹ��ָ����ZIP�ļ���ָ����Ŀ¼<BR>
     * @param zipFileName ����ѹ���ļ���<BR>
     * @param destPath ����ѹ�ļ��Ĵ��·��<BR>
     * <BR>
     * @return 1 �ɹ� <BR>
     *         -1 ʧ�� <BR>
     *         -2 �ļ������� <BR>
     *         -3 ָ�������Ʋ����ļ� <BR>
     */
    
	public static int unzip(String zipFileName, String destPath) {
		return unzip(zipFileName, null, destPath);
	}
	
	/**
     * <P>��ѹ��ָ����ZIP�ļ��е�ָ����Ŀ��ָ����Ŀ¼<BR>
     * @param zipFileName ����ѹ���ļ���<BR>
     * @param entryName ����ѹ�ļ���ָ����Ŀ<BR>
     * @param destPath ����ѹ�ļ��Ĵ��·��<BR>
     * <BR>
     * @return  1 �ɹ� <BR>
     *         -1 ʧ�� <BR>
     *         -2 �ļ������� <BR>
     *         -3 ָ�������Ʋ����ļ� <BR>
     *         -4 �Ҳ���ָ����Ŀ <BR>
     */
	public static int unzip(String zipFileName, String entryName, String destPath) {
		try {
			boolean isFound = false;
			ZipEntry zipEntry;
			File f = new File(zipFileName);
			if (!f.exists()) {
				return -2; //�ļ�������
			}
			if (f.isDirectory()) {
				return -3; //����Ĳ����ļ�
			}
			if (destPath == null && f.getParent()!=null) {
				destPath = f.getParent();
			} 
				
			if (destPath == null || destPath.equals("")) {
				destPath = "";
			} else {
			if (!destPath.endsWith(FILE_SEPARATOR))
				destPath = destPath + FILE_SEPARATOR;
			}
			ZipFile zipfile = new ZipFile(zipFileName);
			Enumeration enumEnties = zipfile.entries();
			try {
				while(enumEnties.hasMoreElements()) {
					 zipEntry = (ZipEntry) enumEnties.nextElement();
					 String zipEntryName = zipEntry.getName();
					 if (entryName == null) {
							 zipEntryName = destPath + zipEntryName;
							 unZipEntry(zipfile, zipEntry, zipEntryName);
					 } else {
					 	if (zipEntryName.equalsIgnoreCase(entryName)) {
								zipEntryName = destPath + zipEntryName;
								unZipEntry(zipfile, zipEntry, zipEntryName);
								isFound = true;
					 		break;
					 	}
					 }
				}
			} catch (Exception ex) {
				zipfile.close();
				ex.printStackTrace();
				return -1;
			}
			if (entryName != null && !isFound) {
				System.out.println ("-4");
				return -4;
			}
			zipfile.close();
		} catch(Exception e) {
			 e.printStackTrace();
			 return -1;
		}
		return 1;
	}
	/**
     * <P>��ѹ��ָ������Ŀ<BR>
     * @param zipfile �Ѿ��򿪵�zip�ļ�<BR>
     * @param zipEntry zip�ļ��еĵ�ǰ��Ŀ<BR>
     * @param zipEntryName ����ѹ����Ŀ����<BR>
     *
     * @exception IOException ������I/O����ʱ���׳�����Ϣ<BR>
     * @exception ZipException ����Zip�ļ���ȡ����ʱ���׳�����Ϣ
     */
	private static void unZipEntry (ZipFile zipfile, 
									ZipEntry zipEntry,
									String zipEntryName) 
							throws ZipException, IOException {
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		System.out.println("Extracting: " + zipEntry);
			
		int count;
		File fzipEntry = new File(zipEntryName);
		if (zipEntry.isDirectory()) {
			 if (!fzipEntry.exists())
			 	fzipEntry.mkdirs();
			 return;
		}
		File fzipEntryDir = new File(fzipEntry.getParent());
		if (!fzipEntryDir.exists() || fzipEntryDir.isFile())
			fzipEntryDir.mkdirs();
		byte data[] = new byte[BUFFER];
		bis = new BufferedInputStream
			(zipfile.getInputStream(zipEntry));
		FileOutputStream fos = new 
		  FileOutputStream(zipEntryName);
		bos = new 
		  BufferedOutputStream(fos, BUFFER);
		while ((count = bis.read(data, 0, BUFFER))!= -1) {
		   bos.write(data, 0, count);
		}
		bos.flush();
		bos.close();
		bis.close();
	}
	
	/**
     * <P>��ѯZIP�ļ��е���Ŀ
     * @param zipFileName ZIP�ļ�����
     *
     * @return Zip�ļ�����Ŀ�б�
     * @exception IOException ������I/O����ʱ���׳�����Ϣ<BR>
     * @exception ZipException ����Zip�ļ���ȡ����ʱ���׳�����Ϣ
     */
	public static String[] getZipList (String zipFileName)
							throws ZipException, IOException {
		 ZipEntry zipEntry;
		 String[] zipEntryNames;
		 ZipFile zipfile = new ZipFile(zipFileName);
		 Enumeration enumEnties = zipfile.entries();
		 Vector vEnties = new Vector();
		 while (enumEnties.hasMoreElements()) {
		 	zipEntry = (ZipEntry) enumEnties.nextElement();
		 	vEnties.add(zipEntry.getName());
		 }
		 zipEntryNames = new String[vEnties.size()];
		 for (int i=0; i<vEnties.size(); i++)
		 	zipEntryNames[i] = (String)vEnties.elementAt(i);
		 return zipEntryNames;
	}
	
	/**
     * <P>ѹ���ļ����ļ���
     * @param strName ��ѹ�����ļ����ļ��е�����
     * @param zipFileName ZIP�ļ�������
     * 
     * @return true ѹ���ɹ� <BR>
     *         false ѹ��ʧ��
     * 
     */
    public static boolean zip(String strName, 
    						  String zipFileName) {
         File f = new File(strName);
         if (f.isDirectory())
         	return zip(strName, zipFileName, f.getAbsolutePath());
         return zip(strName, zipFileName, f.getAbsoluteFile().getParent());
    }
    
	/**
     * <P>ѹ���ļ����ļ���
     * @param strName ��ѹ�����ļ����ļ��е�����
     * @param zipFileName ZIP�ļ�������
     * @param strRoot ѹ���ļ��ĸ�Ŀ¼
     * 
     * @return true ѹ���ɹ�<BR>
     *         false ѹ��ʧ��
     */
    private static boolean zip(String strName, 
    						  String zipFileName, 
    						  String strRoot) {
      FileOutputStream fos = null;
      ZipOutputStream zipout = null;
      try {
         File zipFile = new File(zipFileName);
		 File zipFileParent = new File(zipFile.getParent());
	     if (!zipFileParent.exists())
     	 	zipFileParent.mkdirs();
         fos = new FileOutputStream(zipFile);
         zipout = new ZipOutputStream(new BufferedOutputStream(fos));
         File f = new File(strName);
         if (f.isDirectory()) {
         	seekDir(f, zipout, strRoot);
	      } else {
	      	compress(f, zipout, strRoot);
	      }
      } catch(Exception e) {
         e.printStackTrace();
         return false;
      } finally {
      	try {
      		zipout.close();
      	} catch (IOException ioe) {
      		zipout = null;
      		ioe.printStackTrace();
      		return false;
      	}
      }
      return true;
   }
   
	/**
     * <P>����Ŀ¼����ѹ��
     * @param fName ��ѹ�����ļ��е�����
     * @param zipout ZIP�ļ���
     * @param strRoot ѹ���ļ��ĸ�Ŀ¼
     * 
     * @exception IOException ������I/O����ʱ���׳�����Ϣ<BR>
     * @exception ZipException ����Zip�ļ���ȡ����ʱ���׳�����Ϣ
     * 
     */
   private static void seekDir (File fName,
   								ZipOutputStream zipout,
   								String strRoot)
   						throws ZipException, IOException {
     File files[] = fName.listFiles();
  	 compress(fName, zipout, strRoot);
     int i;
     for (i=0; i < files.length; i++) {
     	if (files[i].isDirectory()) {
     		seekDir(files[i], zipout, strRoot);
     		continue;
     	}
     	compress(files[i], zipout, strRoot);
     }
   }

	/**
     * <P>��ָ�����ļ����ļ��н���ѹ��
     * @param fName ��ѹ�����ļ����ļ���
     * @param zipout ZIP�ļ���
     * @param strRoot ѹ���ļ��ĸ�Ŀ¼ 
     * 
     * @exception IOException ������I/O����ʱ���׳�����Ϣ<BR>
     * @exception ZipException ����Zip�ļ���ȡ����ʱ���׳�����Ϣ
     * 
     */
    private static void compress(File fName, 
      							ZipOutputStream zipout,
      							String strRoot) 
      					throws ZipException,IOException {
		BufferedInputStream bis = null;
        byte data[] = new byte[BUFFER];
        String strEntryName = fName.getAbsoluteFile()
        					  .getAbsolutePath();
        if (strEntryName.equals(strRoot))
        	return;
        strEntryName = strEntryName.substring(strRoot.length() + 1);
        strEntryName = strEntryName.replace('\\', '/');
		if (fName.isDirectory()&&!strEntryName.endsWith("/"))
			strEntryName = strEntryName + "/";
		System.out.println("Adding: " + strEntryName);
		ZipEntry entry = new ZipEntry(strEntryName);
		zipout.putNextEntry(entry);
		if (fName.isDirectory())
			return;
		FileInputStream fis = new FileInputStream(fName);
		bis = new BufferedInputStream(fis, BUFFER);
		int count;
		while((count = bis.read(data, 0, BUFFER)) != -1) {
			zipout.write(data, 0, count);
		}
		bis.close();
		fis.close();
   }
}
