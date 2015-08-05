/**
 * @(#)PropertyManager.java  2003-1-14
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import com.idn.util.CommonTools;
/** 
 * <P>为程序提供环境参数，包括数据库配置及其它需要的参数</P>
 * @version 0.1
 * @author 苏海军
 */

public final class PropertyManager {
	public final static boolean CHINESE = true;

	public final static String FILE_SEPARATOR = SystemProperties.FILE_SEPARATOR;

	public final static String PATH_SEPARATOR = SystemProperties.PATH_SEPARATOR;

	public final static String LINE_SEPARATOR = SystemProperties.LINE_SEPARATOR;

	public final static String USER_DIR = SystemProperties.USER_DIR;

	public static String InitlogFilename = getInitLogFile();

	private static Properties systemProperties = getProperties();

	private static String SystemFilename = getConfigFile();

	/**
	 * 获取属性列表中的boolean值
	 *
	 * @param String key 名称
	 * @return boolean
	 */
	public static boolean getBooleanProperty(String key) {
		return Boolean.valueOf(getProperty(key)).booleanValue();
	}

	/**
	 * 获取指定的属性列表中的boolean值
	 *
	 * @param String key 名称
	 * @return boolean
	 */
	public static boolean getBooleanProperty(
		Properties properties,
		String key) {
		return Boolean.valueOf(getProperty(properties, key)).booleanValue();
	}

	/**
	 * 获取属性列表中的int值
	 *
	 * @param String key 名称
	 * @return int 整型
	 * @exception NumberFormatException
	 */
	public static int getIntProperty(String key) throws NumberFormatException {
		return Integer.parseInt(getProperty(key));
	}

	/**
	 * 获取指定属性列表中的int值
	 *
	 * @param String key 名称
	 * @return int 整型
	 * @exception NumberFormatException
	 */
	public static int getIntProperty(Properties properties, String key)
		throws NumberFormatException {
		return Integer.parseInt(getProperty(properties, key));
	}
	/**
	 * 获取属性列表中的long值
	 *
	 * @param String key 名称
	 * @return long 
	 * @exception NumberFormatException
	 */
	public static long getLongProperty(String key)
		throws NumberFormatException {
		return Long.parseLong(getProperty(key));
	}

	/**
	 * 获取指定属性列表中的long值
	 *
	 * @param String key 名称
	 * @return long 
	 * @exception NumberFormatException
	 */
	public static long getLongProperty(Properties properties, String key)
		throws NumberFormatException {
		return Long.parseLong(getProperty(properties, key));
	}
	/**
	 * 获取属性列表，根据home.properties文件中的设置来加载属性文件
	 * @return Properties
	 */
	public static Properties getProperties() {
		if (systemProperties == null) {
			systemProperties = loadProperties();
		}
		return systemProperties;
	}

	/**
	 * 获取指定属性列表中的指定值
	 *
	 * @param Properties properties如果为null,则为当前的属性
	 * @param String key 名称
	 * @param String isCN 是否进行中文处理的标志，默认为false
	 *
	 * @return String key的值
	 */
	public static String getProperty(
		Properties properties,
		String key,
		boolean isCN) {
		String value = properties.getProperty(key);
		if (value != null) {
			value = value.trim();
			if (isCN)
				return CommonTools.getGBKString(value);
		}
		return value;
	}

	public static String getProperty(String key, boolean isCN) {
		return getProperty(getProperties(), key, isCN);
	}

	public static String getProperty(Properties properties, String key) {
		return getProperty(properties, key, false);
	}

	public static String getProperty(String key) {
		return getProperty(key, false);
	}
	/**
	 * 获取属性列表中某个KEY的值，并将其转换为数组，中间以
	 * 			空格分隔
	 * @param String key
	 * @param String isCN 是否进行中文处理的标志，默认为false
	 *
	 * @return String 数组，所有元素都已经trim()
	 *
	 * @author name: 苏海军
	 * 创建日期：2002年 8月 3日
	 */
	public static String[] getPropertyArray(
		Properties properties,
		String key,
		boolean isCN) {
		return getPropertyArray(properties, key, null, isCN);
	}

	public static String[] getPropertyArray(String key, boolean isCN) {
		return getPropertyArray(getProperties(), key, isCN);
	}

	public static String[] getPropertyArray(
		Properties properties,
		String key) {
		return getPropertyArray(properties, key, null, false);
	}

	public static String[] getPropertyArray(String key) {
		return getPropertyArray(key, null, false);
	}

	/**
	 * 获取属性列表中某个KEY的值，并将其转换为数组，中间以
	 * 			指定的字符delimiter分隔，如果此key不存在，则会检查
	 *			(key).count并返回以key.item开头的参数值组成的数组
	 * @param String key
	 * @param String isCN 是否进行中文处理的标志，默认为false
	 *
	 * @return String 数组，所有元素都已经trim()
	 *
	 * @author name: 苏海军
	 * 创建日期：2002年 8月 3日
	 */
	public static String[] getPropertyArray(
		Properties properties,
		String key,
		String delimiter,
		boolean isCN) {
		String[] array = null;

		String singleEntry = getProperty(properties, key, isCN);

		if (singleEntry != null) {
			array = CommonTools.stringToArray(singleEntry, delimiter);

		} else {
			array = getPropertyArrayFromMultipleEntries(properties, key, isCN);
		}

		return array;

	}

	public static String[] getPropertyArray(
		Properties properties,
		String key,
		String delimiter) {
		return getPropertyArray(properties, key, delimiter, false);
	}

	public static String[] getPropertyArray(
		String key,
		String delimiter,
		boolean isCN) {
		return getPropertyArray(getProperties(), key, delimiter, isCN);
	}

	public static String[] getPropertyArray(String key, String delimiter) {
		return getPropertyArray(key, delimiter, false);
	}

	/**
	 * 获取属性列表中多个以参数KEY的连续的属性值，并将其转
	 * 换为数组，其中(key).count定义了个数，如
	 *			<pre>
	 *				keywords.count = 3
	 *				keywords.item0 = item 1
	 *				keywords.item1 = item 2
	 *				keywords.item2 = item 3
	 *			</pre>
	 *			数组获取的结果为{"item 1", "item 2", "item 3"}
	 *
	 * @param  key
	 * @param  isCN 是否进行中文处理的标志，默认为false
	 *
	 * @return 数组，所有元素都已经trim()
	 */
	private static String[] getPropertyArrayFromMultipleEntries(
		Properties properties,
		String key,
		boolean isCN) {
		String[] array = null;
		int elementCount = 0;

		String arraySize = getProperty(properties, key + ".count");

		if (arraySize != null) {
			try {
				elementCount = Integer.parseInt(arraySize);
			} catch (NumberFormatException e) {
				elementCount = 0;
			}

			if (elementCount > 0) {
				array = new String[elementCount];

				for (int i = 0; i < elementCount; i++) {
					String entry =
						getProperty(
							properties,
							key + ".item" + Integer.toString(i),
							isCN);
					if (entry != null) {
						array[i] = entry;
					} else {
						array[i] = "";
					}
				}
			}
		}

		return array;
	}

	private static String[] getPropertyArrayFromMultipleEntries(
		String key,
		boolean isCN) {
		return getPropertyArrayFromMultipleEntries(getProperties(), key, isCN);
	}
	private static String[] getPropertyArrayFromMultipleEntries(String key) {
		return getPropertyArrayFromMultipleEntries(key, false);
	}

	/**
	 * 加载属性文件供系统使用
	 *
	 * @return Preperties
	 */
	public static Properties loadProperties() {
		try {
			return loadProperties(getConfigFile());
		} catch (Exception ex) {
			System.err.println("加载属性文件时出错:");
			System.err.println(ex.getMessage());
			return null;
		}
	}

	public static Properties loadProperties(String filename) {
		try {
			filename = filename.trim();
			Properties properties = new Properties();
			if ((filename.indexOf(SystemProperties.FILE_SEPARATOR) == -1)
				&& filename.endsWith("properties")) {
					String tempName = filename.substring(0, filename.indexOf("."));
					ResourceBundle configFile = ResourceBundle.getBundle(tempName);
					Enumeration enum = configFile.getKeys();
					String key, value;
					while (enum.hasMoreElements()) {
						key = (String) enum.nextElement();
						value = configFile.getString(key);
						properties.put(key, value);
					}
					
			} else {
				File file = new File(filename);
				if (!file.exists())
					throw new IOException("没有找到" + filename + "文件!");
				properties = new Properties();
				if (filename.toUpperCase().endsWith("XML")) {
					XMLConfigure xmlCon = new XMLConfigure();
					properties = xmlCon.XMLToProperties(filename);
				} else {
					FileInputStream propertiesFile = new FileInputStream(file);
					properties.load(propertiesFile);
					propertiesFile.close();
				}
			}
			return properties;
		} catch (Exception e) {
			System.out.println("错误:" + e.getMessage());
			return null;
		}

	}
	/**
	 * 获取system.properies属性文件所在的目录
	 *
	 * @return 目录
	 *
	 */

	private static String getHome() {
		String home = null;
		try {
			ResourceBundle configFile = ResourceBundle.getBundle("home");
			home =
				configFile.getString("config.home") == null
					? ""
					: configFile.getString("config.home");
		} catch (MissingResourceException ex) {
			home = "";
			System.out.println("获取home.properties文件时出错!");
			ex.printStackTrace();
		}
		if (!home.equals("") && !home.endsWith(FILE_SEPARATOR))
			home = home + FILE_SEPARATOR;
		return home;
	}

	/**
	 * 获取system.properies属性文件所在的目录
	 *
	 * @return String 配置文件名称
	 *
	 */
	private static String getConfigFile() {
		if (SystemFilename == null) {
			try {
				ResourceBundle configFile = ResourceBundle.getBundle("home");
				SystemFilename = configFile.getString("property.filename");
				if (SystemFilename == null || SystemFilename.equals(""))
					SystemFilename = "system.properties";
			} catch (MissingResourceException ioex) {
				System.out.println("获取系统属性文件时出错，" + "默认为'system.properties'");
				ioex.printStackTrace();
				SystemFilename = "system.properties";
			}
		}
		return findPropertiesFile(SystemFilename);
	}

	/**
	 * 获取initlog属性文件所在的目录
	 *
	 * @return 日志配置文件名称
	 *
	 * @exception IOException
	 */
	private static String getInitLogFile() {
		if (InitlogFilename == null) {
			try {
				ResourceBundle configFile = ResourceBundle.getBundle("home");
				InitlogFilename = configFile.getString("initlog.filename");
				if (InitlogFilename == null || InitlogFilename.equals(""))
					InitlogFilename = "initlog.properties";
			} catch (MissingResourceException ioex) {
				System.out.println("获取日志属性文件时出错，" + "默认为'initlog.properties'");
				ioex.printStackTrace();
				InitlogFilename = "initlog.properties";
			}
		}
		return findPropertiesFile(InitlogFilename);
	}

	/**
	 * 查找指定的属性文件名，如果home.properties文件中没有定义，则
	 * 从userdir和类路径查找
	 * @param filename
	 * @return 找到的文件名
	 */
	private static String findPropertiesFile(String filename) {
		String strPath = getHome();
		String oldName = filename;
		if (getHome().equals("") && filename.endsWith(".properties")) {
			return filename;
		}
		if (strPath.equals("")) {
			strPath =
				SystemProperties.USER_DIR + SystemProperties.FILE_SEPARATOR;
		}
		File f = new File(getHome() + filename);
		if (!f.exists()) {
			strPath = InitServletProperty.getRealPath();
			if (strPath != null) {
				if (!strPath.endsWith(SystemProperties.FILE_SEPARATOR))
					strPath += SystemProperties.FILE_SEPARATOR;
				strPath += "WEB-INF"
					+ SystemProperties.FILE_SEPARATOR
					+ "classes"
					+ SystemProperties.FILE_SEPARATOR;
				f = new File(strPath + filename);
				if (f.exists()) {
					return strPath + filename;
				}
			}
			System.out.println("警告：找不到" + filename + "属性文件，将从类路径中寻找。");
			System.out.println("此时找到的文件可能会有问题，请检查！");
			boolean isFound = false;
			String[] strHome =
				CommonTools.stringToArray(
					SystemProperties.JAVA_CLASS_PATH,
					SystemProperties.PATH_SEPARATOR);
			for (int i = 0; i < strHome.length; i++) {
				File file = new File(strHome[i]);
				if (file.isFile())
					continue;
				if (!strHome[i].endsWith(SystemProperties.FILE_SEPARATOR))
					strHome[i] = strHome[i] + SystemProperties.FILE_SEPARATOR;
				file = new File(strHome[i] + filename);
				if (file.exists()) {
					filename = strHome[i] + filename;
					System.out.println("找到的文件为：");
					System.out.println(filename);
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				System.out.println("错误：找不到属性文件！");
				return oldName;
			}
		} else {
			filename = strPath + oldName;
		}
		return filename;
	}

	/**
	 * 更新指定的属性文件,对于中文的处理有问题,
	 * 存盘后会变成UNICODE
	 *
	 * @param Pro 属性列表
	 * @param header 属性文件的文件头
	 * @param filename 属性文件的文件名
	 * @param home 属性文件的路径，如果不指定，则由home.properties指定
	 * @param create 如果路径不存在时，是否创建
	 *
	 * @return true 保存成功
	 * @return false 保存失败
	 */
	public static boolean updateProperies(
		Properties pro,
		String header,
		String filename,
		String home,
		boolean create) {

		if (filename == null)
			filename = getConfigFile();
		if (home == null)
			home = getHome();
		filename = filename.trim();
		java.io.File dir = new java.io.File(filename).getParentFile();
		if (dir == null) {
			dir = new File(home);
			if (home.endsWith(FILE_SEPARATOR))
				filename = home + filename;
			else
				filename = home + FILE_SEPARATOR + filename;
		}
		if (!dir.exists()) {
			if (create)
				dir.mkdirs();
			else {
				System.out.println(dir.toString() + "目录不存在");
				return false;
			}
		}
		if (filename.toUpperCase().endsWith("XML")) {
			//			try{
			//				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			//				DocumentBuilder builder=factory.newDocumentBuilder();
			//				Document doc=builder.parse(filename);
			//				doc.normalize();
			//                String strDescription = "请在这里加入说明信息";
			//				NodeList links =doc.getElementsByTagName("prop");
			//				Enumeration enum = pro.propertyNames();
			//				while (enum.hasMoreElements()) {
			//				    String strKey = (String) enum.nextElement();
			//				    String strKeyValue = (String) pro.getProperty(strKey);
			//				    boolean isFind = false;
			//                    strKey = strKey.trim();
			//                    for (int i=0;i<links.getLength();i++){
			//    					Element link=(Element) links.item(i);
			//    					String propName = link.getElementsByTagName("name")
			//    										.item(0).getFirstChild()
			//    										.getNodeValue();
			//    					if (propName.trim().equals(strKey)) {
			//    					   link.getElementsByTagName("value")
			//    					       .item(0).getFirstChild()
			//    					       .setNodeValue(strKeyValue);
			//    					   isFind = true;
			//    					   break;
			//    				    }
			//    				}
			//    				if (!isFind) {
			//                        Text textMsg;
			//                        Element link = doc.createElement("prop");
			//                        Element linkName = doc.createElement("name");
			//                        textMsg = doc.createTextNode(strKey);
			//                        linkName.appendChild(textMsg);
			//                        link.appendChild(linkName);
			//                        Element linkValue = doc.createElement("value");
			//                        textMsg = doc.createTextNode(strKeyValue);
			//                        linkValue.appendChild(textMsg);
			//                        link.appendChild(linkValue);
			//                        Element linkDesc = doc.createElement("description");
			//                        textMsg = doc.createTextNode(strDescription);
			//                        linkDesc.appendChild(textMsg);
			//                        link.appendChild(linkDesc);
			//                        doc.getDocumentElement().appendChild(link);
			//    				}
			//			    }
			//                outputDoc(doc, filename);
			//            }catch(DOMException dome){
			//                System.out.println("解析XML时出错！");
			//                dome.printStackTrace();
			//                return false;
			//            }catch(SAXException saxe){
			//                System.out.println("解析XML时出错！");
			//                saxe.printStackTrace();
			//                return false;
			//            }catch(ParserConfigurationException pce){
			//                System.out.println("解析XML时出错！");
			//                pce.printStackTrace();
			//                return false;
			//            } catch (IOException ioe) {
			//                System.out.println("保存XML文件时出错！");
			//                ioe.printStackTrace();
			//                return false;
			//            } catch (TransformerConfigurationException tce) {
			//                System.out.println("保存XML文件时出错！");
			//                tce.printStackTrace();
			//            } catch (TransformerException te) {
			//                System.out.println("保存XML文件时出错！");
			//                te.printStackTrace();
			//            }
		} else {
			try {
				java.io.File file = new java.io.File(filename);
				java.io.FileOutputStream propertiesFile =
					new java.io.FileOutputStream(file);
				pro.store(propertiesFile, header);
				propertiesFile.close();
			} catch (java.io.IOException ioe) {
				System.out.println("保存属性文件时出错！");
				ioe.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public static boolean updateProperies(
		Properties pro,
		String header,
		String filename,
		String home) {
		return updateProperies(pro, header, filename, home, false);
	}

	public static boolean updateProperies(
		Properties pro,
		String header,
		String filename) {
		return updateProperies(pro, header, filename, null);
	}

	public static boolean updateProperies(Properties pro, String header) {
		return updateProperies(pro, header, null);
	}

	public static boolean updateProperies(Properties pro) {
		return updateProperies(pro, "System Properties", null);
	}

	/**
	 * 输出XML文档
	 * @param doc 要保存的XML Document对象
	 * @param filename 要保存的文件名
	 *
	 * @throws TransformerConfigurationException 
	 * @throws TransformerException 
	 */

	//	public static void outputDoc (Document doc, String filename) 
	//	  throws TransformerConfigurationException,
	//	         TransformerException{
	//		DOMSource doms = new DOMSource (doc);	
	//		File f = new File (filename.trim());
	//		StreamResult sr = new StreamResult (f);
	//		TransformerFactory tf=TransformerFactory.newInstance();	
	//		Transformer t=tf.newTransformer ();	
	//		Properties properties = t.getOutputProperties();   
	//		properties.setProperty(OutputKeys.ENCODING,"GB2312"); 
	//		properties.setProperty(OutputKeys.METHOD,"xml");
	//		properties.setProperty(OutputKeys.VERSION,"1.0");
	//        t.setOutputProperties(properties);   
	//		t.transform(doms,sr);	
	//	}
}
