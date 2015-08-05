
/** 
 * @(#)SystemProperties.java 
 *
 * ����������Ϊ�����ṩ�����������������ݿ����ü�������Ҫ�Ĳ���
 *
 * @version ver 1.0
 * @author name: �պ���
 * @copyright 2002, ��Ȩ����
 * �������ڣ� 2002�� 8�� 2��
 */
package com.htyz.system;

import java.io.FileInputStream ; 
import java.util.Properties ; 
import java.util.ResourceBundle ; 
import java.util.StringTokenizer ; 
import java.util.Enumeration;
import java.io.File;

public final class SystemProperties
{
	private final static String CONFIGURATION_FILENAME = "system.properties" ; 

	public final static boolean CHINESE = true ; 

	private static Properties systemProperties = loadProperties(); 

	/** ************************************************************
     * ������������ȡ�����б��е�booleanֵ
     * 
     * @param String key ����
     * 
     * @retruns boolean
     * 
     * @exception 
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 3��
     */
	public static boolean getBooleanProperty( String key ) 
	{
		return Boolean.valueOf( getProperty( key ) ).booleanValue() ; 
	}

	/** ************************************************************
     * ������������ȡ�����б��е�intֵ
     * 
     * @param String key ����ֵ
     * 
     * @retruns int
     * 
     * @exception NumberFormatException
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 3��
     */
	public static int getIntProperty( String key ) throws NumberFormatException 
	{
		return Integer.parseInt(getProperty( key )) ; 
	}

	/** ************************************************************
     * ������������ȡ�����б��е�longֵ
     * 
     * @param String key ����ֵ
     * 
     * @retruns long
     * 
     * @exception NumberFormatException
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 3��
     */
	public static long getLongProperty( String key ) throws NumberFormatException 
	{
		return Long.parseLong(getProperty( key )) ; 
	}

	/** ************************************************************
     * ������������ȡ�����б�
     * 
     * @param 
     * 
     * @retruns Properties
     * 
     * @exception 
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 3��
     */
	public static Properties getProperties()
	{
		return systemProperties ; 
	}

	/** ************************************************************
     * ������������ȡ�����б��е�ָ��ֵ
     * 
     * @param String key
     * @param String isCN �Ƿ�������Ĵ���ı�־��Ĭ��Ϊfalse
     * 
     * @retruns String key��ֵ
     * 
     * @exception 
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 3��
     */
	public static String getProperty(String key, boolean isCN) 
	{
		if (systemProperties == null)
			systemProperties = loadProperties();
			
		String value = systemProperties.getProperty(key); 
		if (value != null)
		{
			value = value.trim(); 
			if (isCN) return getGBKString(value);
		}
		return value; 
	}

	public static String getProperty(String key) 
	{
		return getProperty(key, false); 
	}
	/** ************************************************************
     * ������������ȡ�����б���ĳ��KEY��ֵ��������ת��Ϊ���飬�м���
     * 			�ո�ָ�
     * @param String key
     * @param String isCN �Ƿ�������Ĵ���ı�־��Ĭ��Ϊfalse
     * 
     * @retruns String ���飬����Ԫ�ض��Ѿ�trim()
     * 
     * @exception 
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 3��
     */
	public static String[] getPropertyArray( String key , boolean isCN)
	{
		return getPropertyArray( key, null , isCN) ; 
	}

	public static String[] getPropertyArray( String key )
	{
		return getPropertyArray( key, null , false) ; 
	}

	/** ************************************************************
     * ������������ȡ�����б���ĳ��KEY��ֵ��������ת��Ϊ���飬�м���
     * 			ָ�����ַ�delimiter�ָ��������key�����ڣ������
     *			(key).count��������key.item��ͷ�Ĳ���ֵ��ɵ�����	
     * @param String key
     * @param String isCN �Ƿ�������Ĵ���ı�־��Ĭ��Ϊfalse
     * 
     * @retruns String ���飬����Ԫ�ض��Ѿ�trim()
     * 
     * @exception 
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 3��
     */
	public static String[] getPropertyArray( String key, String delimiter, boolean isCN )
	{
		String[] array = null ; 

		String singleEntry = getProperty( key, isCN ) ; 

		if ( singleEntry != null )
		{
			array = stringToArray( singleEntry, delimiter ) ; 

		}
		else
		{
			array = getPropertyArrayFromMultipleEntries( key, isCN ) ; 
		}

		return array ; 

	}

	public static String[] getPropertyArray( String key, String delimiter )
	{
		return getPropertyArray( key, delimiter, false);
	}

	/** ************************************************************
     * ������������ȡ�����б��ж���Բ���KEY������������ֵ��������ת
     *			��Ϊ���飬����(key).count�����˸�������
     *			<pre>
     *				keywords.count = 3
     *				keywords.item0 = item 1
     *				keywords.item1 = item 2
     *				keywords.item2 = item 3
     *			</pre>
     *			�����ȡ�Ľ��Ϊ{"item 1", "item 2", "item 3"}
     *
     * @param String key
     * @param String isCN �Ƿ�������Ĵ���ı�־��Ĭ��Ϊfalse
     * 
     * @retruns String ���飬����Ԫ�ض��Ѿ�trim()
     * 
     * @exception 
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 3��
     */
	private static String[] getPropertyArrayFromMultipleEntries( String key, boolean isCN )
	{
		String[] array = null ;
		int elementCount = 0 ; 

		String arraySize = getProperty( key + ".count" ) ; 
		
		if ( arraySize != null ) 
		{
			try
			{
				elementCount = Integer.parseInt( arraySize ) ; 
			}
			catch( NumberFormatException e )
			{
				elementCount = 0 ; 
			}

			if ( elementCount > 0 )
			{
				array = new String[ elementCount ] ; 

				for ( int i = 0 ; i < elementCount ; i++ )
				{
					String entry = getProperty( key + ".item" + Integer.toString(i), isCN ) ; 
					if ( entry != null ) 
					{
						array[i] = entry ; 
					}
					else
					{
						array[i] = "" ; 
					}
				}
			}	
		}	

		return array ;
	}

	private static String[] getPropertyArrayFromMultipleEntries( String key )
	{
		return getPropertyArrayFromMultipleEntries( key, false);
	}

	/** ************************************************************
     * ������������ָ�����ַ���ת��Ϊ���飬�м���ָ�����ַ�delimiter�ָ�
     * 			���δָ����������delimiterΪ�ո�
     *
     * @param String str ��
     * @param String delimiter �ָ��� 
     *
     * @retruns String ���飬����Ԫ�ض��Ѿ�trim()
     *
     * @exception 
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 3��
     */
	public static String[] stringToArray( String str, String delimiter )
	{
		String[] array = null ; 
		int elementCount = 0 ; 

		String separator = " " ; 
		if ( delimiter != null )
		{
			separator = delimiter ; 
		}

		StringTokenizer tokens = new StringTokenizer( str.trim(), separator ) ; 
		elementCount = tokens.countTokens() ; 

		if ( elementCount != 0 )
		{
			array = new String[ elementCount ] ; 
			for ( int i = 0 ; i < elementCount ; i++ )
			{
				array[i] = tokens.nextToken().trim() ;
			}
		}

		return array ; 
	}

	public static String[] stringToArray( String str )
	{
		return stringToArray( str, null);
	}
	
	/** ************************************************************
     * �������������������ļ���ϵͳʹ��
     * 
     * @param 
     * 
     * @retruns Preperties
     * 
     * @exception 
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 3��
     */
	public static Properties loadProperties()
	{
		try
		{
			String filename = getHome() + getConfigFile();
			File file = new File(filename);
//			if (!file.exists())
//				throw new Exception ("û���ҵ�" + filename + "�ļ�!");
			FileInputStream propertiesFile = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(propertiesFile);
			propertiesFile.close();
			return properties; 
		}
		catch (Exception e)
		{
			System.out.println("����:" + e.getMessage()) ;
			return null;
		}
	}
	
	/** ************************************************************
     * ������������ȡsystem.properies�����ļ����ڵ�Ŀ¼
     * 
     * @param 
     * 
     * @retruns String Ŀ¼
     * 
     * @exception IOException
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 3��
     */
	private static String getHome () throws java.io.IOException {
//		File file = new File("home.properties");
//		if (!file.exists())
//			throw new java.io.IOException ("û���ҵ�home.properties�ļ�!");
		ResourceBundle configFile = ResourceBundle.getBundle("home");
		String home = configFile.getString("config.home") == null?"": configFile.getString("config.home");
		String fileSeparator = System.getProperties().getProperty("file.separator");
		if (!home.endsWith(fileSeparator)&&!home.equals(""))
			home = home + fileSeparator;
		return home;
	}
	
	/** ************************************************************
     * ������������ȡsystem.properies�����ļ����ڵ�Ŀ¼
     * 
     * @param 
     * 
     * @retruns String �����ļ�����
     * 
     * @exception IOException
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 3��
     */
	private static String getConfigFile () throws java.io.IOException {
//		File file = new File("home.properties");
//		if (!file.exists())
//			throw new java.io.IOException ("û���ҵ�home.properties�ļ�!");
		ResourceBundle configFile = ResourceBundle.getBundle("home");
		String filename = configFile.getString("config.filename") == null?CONFIGURATION_FILENAME : configFile.getString("config.filename");
		return filename;
	}

	/** ************************************************************
     * ��������������ָ���������ļ�,�������ĵĴ���������,���̺����UNICODE
     * 
     * @param Properties Pro �����б�
     * @param String header �����ļ����ļ�ͷ 
     * @param String filename �����ļ����ļ���
     * @param String filename �����ļ���·���������ָ��������home.propertiesָ�� 
     * @param boolean create ���·��������ʱ���Ƿ񴴽�
     * 
     * @retruns 
     * 
     * @exception java.io.Excepiton
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 3��
     */
	public static void updateProperies(Properties pro, String header, String filename, String home, boolean create)
		throws java.io.IOException {
		
		String fileSeparator = System.getProperties().getProperty("file.separator");
		if (filename == null)
			filename = getConfigFile();
		if (home == null)
			home = getHome();
		if (filename.startsWith(fileSeparator))
			filename = filename.substring(1, filename.length());
		if (!home.endsWith(fileSeparator)&&!home.equals(""))
			home = home + fileSeparator;
		java.io.File file = new java.io.File(home + filename);
		java.io.File dir = new java.io.File(file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(fileSeparator)));
		if (dir != null)
		{
			if (!dir.exists())
			{
				if (create)
					dir.mkdirs();
				else
					throw new java.io.IOException(dir.toString() + "Ŀ¼������");
			}
		}
		
		java.io.FileOutputStream propertiesFile = new java.io.FileOutputStream(file);
		pro.store(propertiesFile, header);
		propertiesFile.close();
	}
	
	public static void updateProperies (Properties pro, String header, String filename, String home)
		throws java.io.IOException {
		updateProperies(pro, header, filename, home, false);
	}
	
	public static void updateProperies (Properties pro, String header, String filename)
		throws java.io.IOException {
		updateProperies(pro, header, filename, null);
	}
	
	public static void updateProperies (Properties pro, String header)
		throws java.io.IOException {
		updateProperies(pro, header, null);
	}

	public static void updateProperies (Properties pro)
		throws java.io.IOException {
		updateProperies(pro, "System Properties", null);
	}
	
	/** ************************************************************
     * �������������Ĵ���
     * 
     * @param String �����������
     * 
     * @retruns String ����������
     * 
     * @exception 
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 3��
     */
	public static String getGBKString(String str)
	{
		try
		{
			String str_temp = str;
			byte[] temp_t = str_temp.getBytes("ISO8859-1");
			str_temp = new String(temp_t);
			return str_temp;
		}
		catch(Exception e)
		{
			return str;
		}
	}

	public static String[] getGBKString(String[] str)
	{
		try
		{
			String[] str_temp = new String[str.length];
			
			for (int i=0; i<str_temp.length; i++) {
				str_temp[i] = str[i];
				str_temp[i] = getGBKString(str_temp[i]);
			}
			
			for (int i=0; i<str_temp.length; i++) {
				str[i] = str_temp[i];
			}
			return str;
		}
		catch(Exception e)
		{
			return str;
		}
	}

}	
