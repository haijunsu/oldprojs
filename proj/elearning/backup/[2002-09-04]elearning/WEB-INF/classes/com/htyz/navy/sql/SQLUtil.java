/* JDBC Connection Pool
 * Copyright (C) 1998 James Cooper
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 *
 * For more information about this software, visit:
 * http://www.bitmechanic.com/projects/
 */

package com.htyz.navy.sql;

import java.util.*;
import java.text.*;

/**
 * A set of utility SQL methods.  Not really related to the JDBC Pool, but
 * included because you might find it useful
 *
 * @author James Cooper <pixel@bitmechanic.com>
 * @version $Id: SQLUtil.java,v 1.6 1999/08/25 06:20:07 pixel Exp $
 */
public class SQLUtil {

  private static String emptyStr = "";

  private static SimpleDateFormat mysqlFormat = new SimpleDateFormat("yyyy-MM-dd");
  private static SimpleDateFormat mysqlFormatHiRes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  /**
   * Calls escapeString(str, int) with a length of 999999
   */
  public static String escapeString(String str) {
    return escapeString(str, 999999);
  }

  /**
   * Escapes ' with ''  and truncates the string to the length specified.
   *
   * @param str String to escape
   * @param length Maximum allowable length of string
   */
  public static String escapeString(String str, int length) {
    // Truncate string
    if(str == null) return emptyStr;
    if(str.length() > length) str = str.substring(0, length);

    // Replace occurances of ' with ''
    StringTokenizer st = new StringTokenizer(str, "'");
    StringBuffer buffer = null;
    while(st.hasMoreTokens()) {
      if(buffer == null) buffer = new StringBuffer(str.length() + 20);
      else buffer.append("''");
      buffer.append(st.nextToken());
    }
    if(buffer == null) return str;
    else return buffer.toString();
  }

  /**
   * Checks if str is null, and if it is returns an empty string.  Otherwise
   * returns str itself.  Useful for wrapping around calls to rs.getString()
   * since that will return null if the field is undefined.
   */
  public static String notNull(String str) {
    if(str == null) return emptyStr;
    else return str;
  }

  /**
   * Formats a date using the SimpleDateFormat "yyyy-MM-dd"
   */
  public static String formatMysqlDate(Date date) {
    if(date == null) return "";
    else return mysqlFormat.format(date);
  }

  public static Date parseMysqlDate(String date_str) {
    if(date_str == null || date_str.equals("0000-00-00")) return null;
    try {
      return mysqlFormat.parse(date_str);
    }
    catch(Exception e) {
      return null;
    }
  }

  /**
   * Formats a date using the SimpleDateFormat "yyyy-MM-dd HH:mm:ss"
   */
  public static String formatHiResMysqlDate(Date date) {
    if(date == null) return "";
    else return mysqlFormatHiRes.format(date);
  }

  /**
   * returns "y" if b is true.  otherwise returns "n"
   */
  public static String booleanToString(boolean b) {
    if(b) return "y";
    else return "n";
  }

  /**
   * returns true if str is not null and equals "y"
   * otherwise returns true
   */
  public static boolean stringToBoolean(String str) {
    if(str == null || !str.equals("y")) return false;
    else return true;
  }
}
