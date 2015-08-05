/**
 * @(#)NavyLoggerFactory.java  2003-1-10
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.log;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

/**
 * ��־��¼������ʵ����LoggerFactory��ͨ��NavyLogger
 * �������µĶ���
 * @see NavyLogger
 * @version 0.1
 * @author �պ���
 */
 
public class NavyLoggerFactory implements LoggerFactory {

  /**
   * ʵ������û��ִ���κβ���
   */
  public NavyLoggerFactory() {
  }

  /**
   * �����µ�Loggerʵ��
   */
  public Logger makeNewLoggerInstance(String name) {
    return new NavyLogger(name);
  }
}
