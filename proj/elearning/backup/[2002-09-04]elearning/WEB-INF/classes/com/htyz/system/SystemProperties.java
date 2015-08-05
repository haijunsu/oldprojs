
/** 
 * @(#)SystemProperties.java 
 *
 * 功能描述：为程序提供环境参数，包括数据库配置及其它需要的参数
 *
 * @version ver 1.0
 * @author name: 苏海军
 * @copyright 2002, 版权所有
 * 创建日期： 2002年 8月 2日
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
     * 功能描述：获取属性列表中的boolean值
     * 
     * @param String key 名称
     * 
     * @retruns boolean
     * 
     * @exception 
     * 
     * @author name: 苏海军
     * 创建日期：2002年 8月 3日
     */
	public static boolean getBooleanProperty( String key ) 
	{
		return Boolean.valueOf( getProperty( key ) ).booleanValue() ; 
	}

	/** ************************************************************
     * 功能描述：获取属性列表中的int值
     * 
     * @param String key 整数值
     * 
     * @retruns int
     * 
     * @exception NumberFormatException
     * 
     * @author name: 苏海军
     * 创建日期：2002年 8月 3日
     */
	public static int getIntProperty( String key ) throws NumberFormatException 
	{
		return Integer.parseInt(getProperty( key )) ; 
	}

	/** ************************************************************
     * 功能描述：获取属性列表中的long值
     * 
     * @param String key 整数值
     * 
     * @retruns long
     * 
     * @exception NumberFormatException
     * 
     * @author name: 苏海军
     * 创建日期：2002年 8月 3日
     */
	public static long getLongProperty( String key ) throws NumberFormatException 
	{
		return Long.parseLong(getProperty( key )) ; 
	}

	/** ************************************************************
     * 功能描述：获取属性列表
     * 
     * @param 
     * 
     * @retruns Properties
     * 
     * @exception 
     * 
     * @author name: 苏海军
     * 创建日期：2002年 8月 3日
     */
	public static Properties getProperties()
	{
		return systemProperties ; 
	}

	/** ************************************************************
     * 功能描述：获取属性列表中的指定值
     * 
     * @param String key
     * @param String isCN 是否进行中文处理的标志，默认为false
     * 
     * @retruns String key的值
     * 
     * @exception 
     * 
     * @author name: 苏海军
     * 创建日期：2002年 8月 3日
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
     * 功能描述：获取属性列表中某个KEY的值，并将其转换为数组，中间以
     * 			空格分隔
     * @param String key
     * @param String isCN 是否进行中文处理的标志，默认为false
     * 
     * @retruns String 数组，所有元素都已经trim()
     * 
     * @exception 
     * 
     * @author name: 苏海军
     * 创建日期：2002年 8月 3日
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
     * 功能描述：获取属性列表中某个KEY的值，并将其转换为数组，中间以
     * 			指定的字符delimiter分隔，如果此key不存在，则会检查
     *			(key).count并返回以key.item开头的参数值组成的数组	
     * @param String key
     * @param String isCN 是否进行中文处理的标志，默认为false
     * 
     * @retruns String 数组，所有元素都已经trim()
     * 
     * @exception 
     * 
     * @author name: 苏海军
     * 创建日期：2002年 8月 3日
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
     * 功能描述：获取属性列表中多个以参数KEY的连续的属性值，并将其转
     *			换为数组，其中(key).count定义了个数，如
     *			<pre>
     *				keywords.count = 3
     *				keywords.item0 = item 1
     *				keywords.item1 = item 2
     *				keywords.item2 = item 3
     *			</pre>
     *			数组获取的结果为{"item 1", "item 2", "item 3"}
     *
     * @param String key
     * @param String isCN 是否进行中文处理的标志，默认为false
     * 
     * @retruns String 数组，所有元素都已经trim()
     * 
     * @exception 
     * 
     * @author name: 苏海军
     * 创建日期：2002年 8月 3日
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
     * 功能描述：将指定的字符串转换为数组，中间以指定的字符delimiter分隔
     * 			如果未指定参数，则delimiter为空格。
     *
     * @param String str 待
     * @param String delimiter 分隔符 
     *
     * @retruns String 数组，所有元素都已经trim()
     *
     * @exception 
     * 
     * @author name: 苏海军
     * 创建日期：2002年 8月 3日
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
     * 功能描述：加载属性文件供系统使用
     * 
     * @param 
     * 
     * @retruns Preperties
     * 
     * @exception 
     * 
     * @author name: 苏海军
     * 创建日期：2002年 8月 3日
     */
	public static Properties loadProperties()
	{
		try
		{
			String filename = getHome() + getConfigFile();
			File file = new File(filename);
//			if (!file.exists())
//				throw new Exception ("没有找到" + filename + "文件!");
			FileInputStream propertiesFile = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(propertiesFile);
			propertiesFile.close();
			return properties; 
		}
		catch (Exception e)
		{
			System.out.println("错误:" + e.getMessage()) ;
			return null;
		}
	}
	
	/** ************************************************************
     * 功能描述：获取system.properies属性文件所在的目录
     * 
     * @param 
     * 
     * @retruns String 目录
     * 
     * @exception IOException
     * 
     * @author name: 苏海军
     * 创建日期：2002年 8月 3日
     */
	private static String getHome () throws java.io.IOException {
//		File file = new File("home.properties");
//		if (!file.exists())
//			throw new java.io.IOException ("没有找到home.properties文件!");
		ResourceBundle configFile = ResourceBundle.getBundle("home");
		String home = configFile.getString("config.home") == null?"": configFile.getString("config.home");
		String fileSeparator = System.getProperties().getProperty("file.separator");
		if (!home.endsWith(fileSeparator)&&!home.equals(""))
			home = home + fileSeparator;
		return home;
	}
	
	/** ************************************************************
     * 功能描述：获取system.properies属性文件所在的目录
     * 
     * @param 
     * 
     * @retruns String 配置文件名称
     * 
     * @exception IOException
     * 
     * @author name: 苏海军
     * 创建日期：2002年 8月 3日
     */
	private static String getConfigFile () throws java.io.IOException {
//		File file = new File("home.properties");
//		if (!file.exists())
//			throw new java.io.IOException ("没有找到home.properties文件!");
		ResourceBundle configFile = ResourceBundle.getBundle("home");
		String filename = configFile.getString("config.filename") == null?CONFIGURATION_FILENAME : configFile.getString("config.filename");
		return filename;
	}

	/** ************************************************************
     * 功能描述：更新指定的属性文件,对于中文的处理有问题,存盘后会变成UNICODE
     * 
     * @param Properties Pro 属性列表
     * @param String header 属性文件的文件头 
     * @param String filename 属性文件的文件名
     * @param String filename 属性文件的路径，如果不指定，则由home.properties指定 
     * @param boolean create 如果路径不存在时，是否创建
     * 
     * @retruns 
     * 
     * @exception java.io.Excepiton
     * 
     * @author name: 苏海军
     * 创建日期：2002年 8月 3日
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
					throw new java.io.IOException(dir.toString() + "目录不存在");
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
     * 功能描述：中文处理
     * 
     * @param String 待处理的中文
     * 
     * @retruns String 处理后的中文
     * 
     * @exception 
     * 
     * @author name: 苏海军
     * 创建日期：2002年 8月 3日
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
