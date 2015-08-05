/**
 * @(#)SystemProperties.java  2003-1-10
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */

package com.idn.property;


/**
 * ��ȡ��ϵͳ��صĸ������Լ�������������
 * @version 0.1
 * @author �պ���
 */
public final class SystemProperties
{
    /**
     * ��ʾ���ֱ��ʿ��ֵ
     */
//    public static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    /**
     * ��ʾ���ֱ��ʸ߶�ֵ
     */
//    public static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    /**
     * ��ǰ�û�����Ŀ¼
     */
    public static final String USER_DIR = System.getProperty("user.dir");
    /**
     * �û�HOMEĿ¼
     */
    public static final String USER_HOME = System.getProperty("user.home");
    /**
     * ��ǰ��¼�û���
     */
    public static final String USER_NAME = System.getProperty("user.name");
    /**
     * �ļ��ָ����Windows����"\"��UNIX����"/"������ϵͳ���
     */
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    /**
     * �лس����з���Windows����"\r\n"��UNIX����"\n"������ϵͳ���
     */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    /**
     * ·���ָ����Windows���Ƿֺ�";"��UNIX����ð��":"������ϵͳ���
     */
    public static final String PATH_SEPARATOR = System.getProperty("path.separator");
    /**
     * JDK��װĿ¼
     */
    public static final String JAVA_HOME = System.getProperty("java.home");
    /**
     * JDK �ṩ��
     */
    public static final String JAVA_VENDOR = System.getProperty("java.vendor");
    /**
     * JDK �ṩ��URL
     */
    public static final String JAVA_VENDOR_URL = System.getProperty("java.vendor.url");
    /**
     * JDK �汾��
     */
    public static final String JAVA_VERSION = System.getProperty("java.version");
    /**
     * ��·��
     */
    public static final String JAVA_CLASS_PATH = System.getProperty("java.class.path");
    /**
     * ��汾��
     */
    public static final String JAVA_CLASS_VERSION = System.getProperty("java.class.version");
    /**
     * ����ϵͳ����
     */
    public static final String OS_NAME = System.getProperty("os.name");
    /**
     * ����ϵͳ�������ͺ�"x86"��"alpha"
     */
    public static final String OS_ARCH = System.getProperty("os.arch");
    /**
     * ����ϵͳ�汾��
     */
    public static final String OS_VERSION = System.getProperty("os.version");
    /**
     * ����ϵͳ�������������
     */
//    public static final String FONT_NAME_LIST[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    /**
     * ����ϵͳ���е�����
     */
//    public static final Font FONT_LIST[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
	/**
     * �ļ��ı���"GBK"��"En"��
     */
	public final static String FILE_ENCODING = System.getProperties().getProperty("file.encoding");
	/**
     * �û�ʹ�õ�ϵͳ����"zh"��"En"��
     */
	public final static String USER_LANGUAGE = System.getProperties().getProperty("user.language");
	/**
     * ����ϵͳʹ�õ���ʱĿ¼
     */
	public final static String TEMP_DIR = System.getProperties().getProperty("java.io.tmpdir");
	/**
     * ����ϵͳ�Ĳ�������
     */
	public final static String OS_PATCH_LEVEL = System.getProperties().getProperty("sun.os.patch.level");
	/**
     * �û����ڵĹ���"CN"��"JP"��
     */
    public final static String USER_COUNTRY = System.getProperties().getProperty("user.country");
    /**
     * �û����ڵĹ���ʱ��
     */
    public final static String USER_TIMEZONE = System.getProperties().getProperty("user.timezone");
   
}