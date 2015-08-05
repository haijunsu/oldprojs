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
 * 用于压缩和解压缩ZIP文件，目前尚不能支持带有口令检验的ZIP文件
 *
 * @version ver 0.1
 * @author name: 苏海军
 */

public class ZipUtil {
	static final int BUFFER = 2048;
	static final String FILE_SEPARATOR = SystemProperties.FILE_SEPARATOR;
    private static LogWrapper log = new LogWrapper(ZipUtil.class);
   
    /**
     * <P>解压缩指定的ZIP文件到当前的目录<BR>
     * @param zipFileName 待解压的文件名<BR>
     * <BR>
     * @return  1 成功 <BR>
     *         -1 失败 <BR>
     *         -2 文件不存在 <BR>
     *         -3 指定的名称不是文件 <BR>
     */
	
	public static int unzip(String zipFileName) {
		return unzip(zipFileName, null, null);
	}

	/**
     * <P>解压缩指定的ZIP文件到指定的目录<BR>
     * @param zipFileName 待解压的文件名<BR>
     * @param destPath 待解压文件的存放路径<BR>
     * <BR>
     * @return 1 成功 <BR>
     *         -1 失败 <BR>
     *         -2 文件不存在 <BR>
     *         -3 指定的名称不是文件 <BR>
     */
    
	public static int unzip(String zipFileName, String destPath) {
		return unzip(zipFileName, null, destPath);
	}
	
	/**
     * <P>解压缩指定的ZIP文件中的指定条目到指定的目录<BR>
     * @param zipFileName 待解压的文件名<BR>
     * @param entryName 待解压文件的指定条目<BR>
     * @param destPath 待解压文件的存放路径<BR>
     * <BR>
     * @return  1 成功 <BR>
     *         -1 失败 <BR>
     *         -2 文件不存在 <BR>
     *         -3 指定的名称不是文件 <BR>
     *         -4 找不到指定条目 <BR>
     */
	public static int unzip(String zipFileName, String entryName, String destPath) {
		try {
			boolean isFound = false;
			ZipEntry zipEntry;
			File f = new File(zipFileName);
			if (!f.exists()) {
				return -2; //文件不存在
			}
			if (f.isDirectory()) {
				return -3; //输入的不是文件
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
     * <P>解压缩指定的条目<BR>
     * @param zipfile 已经打开的zip文件<BR>
     * @param zipEntry zip文件中的当前条目<BR>
     * @param zipEntryName 待解压的条目名称<BR>
     *
     * @exception IOException 当发生I/O错误时，抛出此信息<BR>
     * @exception ZipException 当发Zip文件读取错误时，抛出此信息
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
     * <P>查询ZIP文件中的条目
     * @param zipFileName ZIP文件名称
     *
     * @return Zip文件中条目列表
     * @exception IOException 当发生I/O错误时，抛出此信息<BR>
     * @exception ZipException 当发Zip文件读取错误时，抛出此信息
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
     * <P>压缩文件或文件夹
     * @param strName 待压缩的文件或文件夹的名称
     * @param zipFileName ZIP文件的名称
     * 
     * @return true 压缩成功 <BR>
     *         false 压缩失败
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
     * <P>压缩文件或文件夹
     * @param strName 待压缩的文件或文件夹的名称
     * @param zipFileName ZIP文件的名称
     * @param strRoot 压缩文件的根目录
     * 
     * @return true 压缩成功<BR>
     *         false 压缩失败
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
     * <P>对于目录进行压缩
     * @param fName 待压缩的文件夹的名称
     * @param zipout ZIP文件流
     * @param strRoot 压缩文件的根目录
     * 
     * @exception IOException 当发生I/O错误时，抛出此信息<BR>
     * @exception ZipException 当发Zip文件读取错误时，抛出此信息
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
     * <P>对指定的文件或文件夹进行压缩
     * @param fName 待压缩的文件或文件夹
     * @param zipout ZIP文件流
     * @param strRoot 压缩文件的根目录 
     * 
     * @exception IOException 当发生I/O错误时，抛出此信息<BR>
     * @exception ZipException 当发Zip文件读取错误时，抛出此信息
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
