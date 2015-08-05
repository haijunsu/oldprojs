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
 * <P>Ϊ�����ṩ�����������������ݿ����ü�������Ҫ�Ĳ���</P>
 * @version 0.1
 * @author �պ���
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
	 * ��ȡ�����б��е�booleanֵ
	 *
	 * @param String key ����
	 * @return boolean
	 */
	public static boolean getBooleanProperty(String key) {
		return Boolean.valueOf(getProperty(key)).booleanValue();
	}

	/**
	 * ��ȡָ���������б��е�booleanֵ
	 *
	 * @param String key ����
	 * @return boolean
	 */
	public static boolean getBooleanProperty(
		Properties properties,
		String key) {
		return Boolean.valueOf(getProperty(properties, key)).booleanValue();
	}

	/**
	 * ��ȡ�����б��е�intֵ
	 *
	 * @param String key ����
	 * @return int ����
	 * @exception NumberFormatException
	 */
	public static int getIntProperty(String key) throws NumberFormatException {
		return Integer.parseInt(getProperty(key));
	}

	/**
	 * ��ȡָ�������б��е�intֵ
	 *
	 * @param String key ����
	 * @return int ����
	 * @exception NumberFormatException
	 */
	public static int getIntProperty(Properties properties, String key)
		throws NumberFormatException {
		return Integer.parseInt(getProperty(properties, key));
	}
	/**
	 * ��ȡ�����б��е�longֵ
	 *
	 * @param String key ����
	 * @return long 
	 * @exception NumberFormatException
	 */
	public static long getLongProperty(String key)
		throws NumberFormatException {
		return Long.parseLong(getProperty(key));
	}

	/**
	 * ��ȡָ�������б��е�longֵ
	 *
	 * @param String key ����
	 * @return long 
	 * @exception NumberFormatException
	 */
	public static long getLongProperty(Properties properties, String key)
		throws NumberFormatException {
		return Long.parseLong(getProperty(properties, key));
	}
	/**
	 * ��ȡ�����б�����home.properties�ļ��е����������������ļ�
	 * @return Properties
	 */
	public static Properties getProperties() {
		if (systemProperties == null) {
			systemProperties = loadProperties();
		}
		return systemProperties;
	}

	/**
	 * ��ȡָ�������б��е�ָ��ֵ
	 *
	 * @param Properties properties���Ϊnull,��Ϊ��ǰ������
	 * @param String key ����
	 * @param String isCN �Ƿ�������Ĵ���ı�־��Ĭ��Ϊfalse
	 *
	 * @return String key��ֵ
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
	 * ��ȡ�����б���ĳ��KEY��ֵ��������ת��Ϊ���飬�м���
	 * 			�ո�ָ�
	 * @param String key
	 * @param String isCN �Ƿ�������Ĵ���ı�־��Ĭ��Ϊfalse
	 *
	 * @return String ���飬����Ԫ�ض��Ѿ�trim()
	 *
	 * @author name: �պ���
	 * �������ڣ�2002�� 8�� 3��
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
	 * ��ȡ�����б���ĳ��KEY��ֵ��������ת��Ϊ���飬�м���
	 * 			ָ�����ַ�delimiter�ָ��������key�����ڣ������
	 *			(key).count��������key.item��ͷ�Ĳ���ֵ��ɵ�����
	 * @param String key
	 * @param String isCN �Ƿ�������Ĵ���ı�־��Ĭ��Ϊfalse
	 *
	 * @return String ���飬����Ԫ�ض��Ѿ�trim()
	 *
	 * @author name: �պ���
	 * �������ڣ�2002�� 8�� 3��
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
	 * ��ȡ�����б��ж���Բ���KEY������������ֵ��������ת
	 * ��Ϊ���飬����(key).count�����˸�������
	 *			<pre>
	 *				keywords.count = 3
	 *				keywords.item0 = item 1
	 *				keywords.item1 = item 2
	 *				keywords.item2 = item 3
	 *			</pre>
	 *			�����ȡ�Ľ��Ϊ{"item 1", "item 2", "item 3"}
	 *
	 * @param  key
	 * @param  isCN �Ƿ�������Ĵ���ı�־��Ĭ��Ϊfalse
	 *
	 * @return ���飬����Ԫ�ض��Ѿ�trim()
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
	 * ���������ļ���ϵͳʹ��
	 *
	 * @return Preperties
	 */
	public static Properties loadProperties() {
		try {
			return loadProperties(getConfigFile());
		} catch (Exception ex) {
			System.err.println("���������ļ�ʱ����:");
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
					throw new IOException("û���ҵ�" + filename + "�ļ�!");
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
			System.out.println("����:" + e.getMessage());
			return null;
		}

	}
	/**
	 * ��ȡsystem.properies�����ļ����ڵ�Ŀ¼
	 *
	 * @return Ŀ¼
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
			System.out.println("��ȡhome.properties�ļ�ʱ����!");
			ex.printStackTrace();
		}
		if (!home.equals("") && !home.endsWith(FILE_SEPARATOR))
			home = home + FILE_SEPARATOR;
		return home;
	}

	/**
	 * ��ȡsystem.properies�����ļ����ڵ�Ŀ¼
	 *
	 * @return String �����ļ�����
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
				System.out.println("��ȡϵͳ�����ļ�ʱ����" + "Ĭ��Ϊ'system.properties'");
				ioex.printStackTrace();
				SystemFilename = "system.properties";
			}
		}
		return findPropertiesFile(SystemFilename);
	}

	/**
	 * ��ȡinitlog�����ļ����ڵ�Ŀ¼
	 *
	 * @return ��־�����ļ�����
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
				System.out.println("��ȡ��־�����ļ�ʱ����" + "Ĭ��Ϊ'initlog.properties'");
				ioex.printStackTrace();
				InitlogFilename = "initlog.properties";
			}
		}
		return findPropertiesFile(InitlogFilename);
	}

	/**
	 * ����ָ���������ļ��������home.properties�ļ���û�ж��壬��
	 * ��userdir����·������
	 * @param filename
	 * @return �ҵ����ļ���
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
			System.out.println("���棺�Ҳ���" + filename + "�����ļ���������·����Ѱ�ҡ�");
			System.out.println("��ʱ�ҵ����ļ����ܻ������⣬���飡");
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
					System.out.println("�ҵ����ļ�Ϊ��");
					System.out.println(filename);
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				System.out.println("�����Ҳ��������ļ���");
				return oldName;
			}
		} else {
			filename = strPath + oldName;
		}
		return filename;
	}

	/**
	 * ����ָ���������ļ�,�������ĵĴ���������,
	 * ���̺����UNICODE
	 *
	 * @param Pro �����б�
	 * @param header �����ļ����ļ�ͷ
	 * @param filename �����ļ����ļ���
	 * @param home �����ļ���·���������ָ��������home.propertiesָ��
	 * @param create ���·��������ʱ���Ƿ񴴽�
	 *
	 * @return true ����ɹ�
	 * @return false ����ʧ��
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
				System.out.println(dir.toString() + "Ŀ¼������");
				return false;
			}
		}
		if (filename.toUpperCase().endsWith("XML")) {
			//			try{
			//				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			//				DocumentBuilder builder=factory.newDocumentBuilder();
			//				Document doc=builder.parse(filename);
			//				doc.normalize();
			//                String strDescription = "�����������˵����Ϣ";
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
			//                System.out.println("����XMLʱ����");
			//                dome.printStackTrace();
			//                return false;
			//            }catch(SAXException saxe){
			//                System.out.println("����XMLʱ����");
			//                saxe.printStackTrace();
			//                return false;
			//            }catch(ParserConfigurationException pce){
			//                System.out.println("����XMLʱ����");
			//                pce.printStackTrace();
			//                return false;
			//            } catch (IOException ioe) {
			//                System.out.println("����XML�ļ�ʱ����");
			//                ioe.printStackTrace();
			//                return false;
			//            } catch (TransformerConfigurationException tce) {
			//                System.out.println("����XML�ļ�ʱ����");
			//                tce.printStackTrace();
			//            } catch (TransformerException te) {
			//                System.out.println("����XML�ļ�ʱ����");
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
				System.out.println("���������ļ�ʱ����");
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
	 * ���XML�ĵ�
	 * @param doc Ҫ�����XML Document����
	 * @param filename Ҫ������ļ���
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
