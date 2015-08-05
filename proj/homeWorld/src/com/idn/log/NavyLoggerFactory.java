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
 * 日志记录工厂，实现了LoggerFactory，通过NavyLogger
 * 来创建新的对象
 * @see NavyLogger
 * @version 0.1
 * @author 苏海军
 */
 
public class NavyLoggerFactory implements LoggerFactory {

  /**
   * 实例化，没有执行任何操作
   */
  public NavyLoggerFactory() {
  }

  /**
   * 创建新的Logger实例
   */
  public Logger makeNewLoggerInstance(String name) {
    return new NavyLogger(name);
  }
}
