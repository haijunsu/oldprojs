/**
 * @(#)NavyLogger.java  2003-1-10
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */

package com.idn.log;

import org.apache.log4j.Logger;
import org.apache.log4j.Category;

/**
 * <P>ͨ������ģʽ������Loggerʵ��</P>
 * @version 0.1
 * @author �պ���
 *
 */

public class NavyLogger extends Logger {

  /**
   * ��ǰCLASS���Ƽ�"."��׺
   */
  private static String FQCN = NavyLogger.class.getName() + ".";
  /**
   * ʵ������
   */
  private static NavyLoggerFactory navyFactory = new NavyLoggerFactory();

  /**
   * ������ִ�и���ʵ����
   */
  public NavyLogger(String name) {
    super(name);
  }

  /**
   * ���Ǹ���������֧�ֹ���ģʽ
   */
  public static Category getInstance(String name) {
    return Logger.getLogger(name, navyFactory);
  }
  
  /**
   * ���Ǹ���������֧�ֹ���ģʽ
   */
  public static Logger getLogger(String name) {
    return Logger.getLogger(name, navyFactory); 
  }

  /**
   * ���Ǹ���������֧�ֹ���ģʽ
   */
  public static Category getInstance(Class name) {
    return Logger.getLogger(name.getName(), navyFactory); 
  }
  
  /**
   * ���Ǹ���������֧�ֹ���ģʽ
   */
  public static Logger getLogger(Class name) {
    return Logger.getLogger(name.getName(), navyFactory); 
  }
}


