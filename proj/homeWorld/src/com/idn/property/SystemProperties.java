/**
 * @(#)SystemProperties.java  2003-1-10
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */

package com.idn.property;


/**
 * 获取与系统相关的各个属性及其他常用属性
 * @version 0.1
 * @author 苏海军
 */
public final class SystemProperties
{
    /**
     * 显示器分辨率宽度值
     */
//    public static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    /**
     * 显示器分辨率高度值
     */
//    public static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    /**
     * 当前用户工作目录
     */
    public static final String USER_DIR = System.getProperty("user.dir");
    /**
     * 用户HOME目录
     */
    public static final String USER_HOME = System.getProperty("user.home");
    /**
     * 当前登录用户名
     */
    public static final String USER_NAME = System.getProperty("user.name");
    /**
     * 文件分割符，Windows下是"\"，UNIX下是"/"，操作系统相关
     */
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    /**
     * 行回车换行符，Windows下是"\r\n"，UNIX下是"\n"，操作系统相关
     */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    /**
     * 路径分割符，Windows下是分号";"，UNIX下是冒号":"，操作系统相关
     */
    public static final String PATH_SEPARATOR = System.getProperty("path.separator");
    /**
     * JDK安装目录
     */
    public static final String JAVA_HOME = System.getProperty("java.home");
    /**
     * JDK 提供商
     */
    public static final String JAVA_VENDOR = System.getProperty("java.vendor");
    /**
     * JDK 提供商URL
     */
    public static final String JAVA_VENDOR_URL = System.getProperty("java.vendor.url");
    /**
     * JDK 版本号
     */
    public static final String JAVA_VERSION = System.getProperty("java.version");
    /**
     * 类路径
     */
    public static final String JAVA_CLASS_PATH = System.getProperty("java.class.path");
    /**
     * 类版本号
     */
    public static final String JAVA_CLASS_VERSION = System.getProperty("java.class.version");
    /**
     * 操作系统名称
     */
    public static final String OS_NAME = System.getProperty("os.name");
    /**
     * 操作系统处理器型号"x86"、"alpha"
     */
    public static final String OS_ARCH = System.getProperty("os.arch");
    /**
     * 操作系统版本号
     */
    public static final String OS_VERSION = System.getProperty("os.version");
    /**
     * 操作系统所有字体的名称
     */
//    public static final String FONT_NAME_LIST[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    /**
     * 操作系统所有的字体
     */
//    public static final Font FONT_LIST[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
	/**
     * 文件的编码"GBK"、"En"等
     */
	public final static String FILE_ENCODING = System.getProperties().getProperty("file.encoding");
	/**
     * 用户使用的系统语言"zh"、"En"等
     */
	public final static String USER_LANGUAGE = System.getProperties().getProperty("user.language");
	/**
     * 操作系统使用的临时目录
     */
	public final static String TEMP_DIR = System.getProperties().getProperty("java.io.tmpdir");
	/**
     * 操作系统的补丁级别
     */
	public final static String OS_PATCH_LEVEL = System.getProperties().getProperty("sun.os.patch.level");
	/**
     * 用户所在的国家"CN"、"JP"等
     */
    public final static String USER_COUNTRY = System.getProperties().getProperty("user.country");
    /**
     * 用户所在的国家时区
     */
    public final static String USER_TIMEZONE = System.getProperties().getProperty("user.timezone");
   
}