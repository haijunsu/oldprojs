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
 * <P>通过工厂模式来构造Logger实例</P>
 * @version 0.1
 * @author 苏海军
 *
 */

public class NavyLogger extends Logger {

  /**
   * 当前CLASS名称加"."后缀
   */
  private static String FQCN = NavyLogger.class.getName() + ".";
  /**
   * 实例工厂
   */
  private static NavyLoggerFactory navyFactory = new NavyLoggerFactory();

  /**
   * 仅用来执行父级实例化
   */
  public NavyLogger(String name) {
    super(name);
  }

  /**
   * 覆盖父级方法来支持工厂模式
   */
  public static Category getInstance(String name) {
    return Logger.getLogger(name, navyFactory);
  }
  
  /**
   * 覆盖父级方法来支持工厂模式
   */
  public static Logger getLogger(String name) {
    return Logger.getLogger(name, navyFactory); 
  }

  /**
   * 覆盖父级方法来支持工厂模式
   */
  public static Category getInstance(Class name) {
    return Logger.getLogger(name.getName(), navyFactory); 
  }
  
  /**
   * 覆盖父级方法来支持工厂模式
   */
  public static Logger getLogger(Class name) {
    return Logger.getLogger(name.getName(), navyFactory); 
  }
}


