// /////////////////////////////////
//常用的工具函数
//
//  苏海军
//
//     2002.1
//
//
///////////////////////////////////

package com.htyz;

import java.util.*;
import java.sql.SQLException;
import java.text.*;
import java.io.*;

import com.htyz.util.BeanDateFormat;

public class beanElearnTools {
    public beanElearnTools() {
        //加入初始化信息
    }

    //校验是否显示该MENU，userPriv是用户的权限，menuPriv是菜单的权限
    public boolean isMenuShow(String userPriv, String menuPriv) {
        int i_userPriv, i_menuPriv;
        try {
            i_userPriv = Integer.valueOf(userPriv).intValue();
            i_menuPriv = Integer.valueOf(menuPriv).intValue();
            if ((i_userPriv & i_menuPriv) == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            System.err.println("bean_ElearnTools.isMenuShow error");
            e.printStackTrace();
            return false;
        }
    }

    //计算用户和菜单权限
    public String parseRight(String[] s_right) {
        int i_Right, i_RightNum;
        String s_rtnRight;
        i_Right = 0;
        for (int i = 0; i < s_right.length; i++) {
            i_RightNum = Integer.parseInt(s_right[i]);
            if (i_RightNum != 0)
                i_Right = i_Right + (int) java.lang.Math.pow(2, i_RightNum - 1);
        }
        s_rtnRight = Integer.toString(i_Right);
        return s_rtnRight;
    }

    //计算用户组权限
    public String parseRight(String s_right) {
        int i_Right, i_RightNum;
        String s_rtnRight;
        i_Right = 0;
        i_RightNum = Integer.parseInt(s_right);
        if (i_RightNum != 0)
            i_Right = i_Right + (int) java.lang.Math.pow(2, i_RightNum - 1);
        s_rtnRight = Integer.toString(i_Right);
        return s_rtnRight;
    }

    //	校验SQL语句中的日期是否合法，在拼写SQL语句前，对字符串进行判断
    //	日期格式：yyyy-mm-dd
    //	错误编号：
    //	1: ok, 日期合法
    //-1：日期格式错误
    //-2：年代表示错误
    //-3：月份表示错误
    //-4：日期表示错误
    //-5：年代超出范围
    //-6：月份超出范围
    //-7：日期超出范围
    //-8：日期应为30天
    //-9：闰年2月份的天数是29天
    //-10：2月份的天数是28天
    //	
    public int check_date(String date_str) {
        String num = "0123456789";
        String year, month, day;
        String temp_str;
        int i, i1, j, int_year, int_month, int_day;
        year = "";
        month = "";
        day = "";
        //校验日期格式并分离出年月日
        i = date_str.indexOf("-");
        if (i == -1) {
            return -1;
        } else {
            //取出年代
            year = date_str.substring(0, i);
            i1 = date_str.indexOf("-", i + 1);
            if (i1 == -1 || (i1 - i) == 1) {
                return -1;
            } else {
                //取出月份和日期
                month = date_str.substring(i + 1, i1);
                day = date_str.substring(i1 + 1, date_str.length());
            }
        }
        //判断年代的长度并校验是否为数字
        if (year.length() != 4) {
            return -2;
        } else {
            for (i = 0; i < 4; i++) {
                temp_str = year.substring(i, i + 1);
                i1 = num.indexOf(temp_str);
                if (i1 == -1) {
                    return -2;
                }
            }
        }
        //判断月份的长度并校验是否为数字
        if (month.length() > 2) {
            return -3;
        } else {
            for (i = 0; i < month.length(); i++) {
                temp_str = month.substring(i, i + 1);
                i1 = num.indexOf(temp_str);
                if (i1 == -1) {
                    return -3;
                }
            }
        }
        //判断日期的长度并校验是否为数字
        if (day.length() > 2) {
            return -4;
        } else {
            for (i = 0; i < day.length(); i++) {
                temp_str = day.substring(i, i + 1);
                i1 = num.indexOf(temp_str);
                if (i1 == -1) {
                    return -4;
                }
            }
        }
        //将年月日转换为数字型以方便计算
        int_year = Integer.valueOf(year).intValue();
        int_month = Integer.valueOf(month).intValue();
        int_day = Integer.valueOf(day).intValue();
        //判断年代是否超出范围，第一位为能为0，有效年代为1753~9999
        if (int_year < 1753) {
            return -5;
        }
        //判断月份是否超出范围
        if (int_month < 1 || int_month > 12) {
            return -6;
        }
        //判断日期是否超出范围
        if (int_day < 1 || int_day > 31) {
            return -7;
        }
        //进一步对4、6、9、11月份进行日期超界判断
        if (int_day == 31
                && (int_month == 4 || int_month == 6 || int_month == 9 || int_month == 11)) {
            return -8;
        }
        //判断是否为闰年来确定2月份的最大天数
        if (int_month == 2) {
            if (int_year % 4 == 0
                    || (int_year % 100 == 0 && int_year % 400 == 0)) {
                //闰年
                if (int_day > 29) {
                    return -9;
                }
            } else {
                if (int_day > 28) {
                    return -10;
                }
            }
        }
        //日期合法
        return 1;
    }

    //替换SQL语句中的单引号，在拼写SQL语句前，对字符串进行处理
    public String check_quote(String Ostr) {
        String Estr;
        int i;
        Estr = "";
        i = 0;
        while (i != -1) {
            i = Ostr.indexOf("'");
            if (i != -1) {
                Estr = Estr + Ostr.substring(0, i + 1) + "'";
                Ostr = Ostr.substring(i + 1, Ostr.length());
            } else {
                Estr = Estr + Ostr;
            }
        }
        return Estr;
    }

    //将从SQL数据库中选择出来的日期性字段格式化为本地日期描述
    public String FormatDate(String DateStr) {
        if (DateStr.equals(null) || DateStr.equals("")) {
            return "    -  -  ";
        } else {
            try {
                DateFormat df = DateFormat.getDateInstance();
                Date d = df.parse(DateStr);
                df = DateFormat.getDateInstance(DateFormat.FULL);
                return df.format(d);
            } catch (ParseException e) {
                System.err.println("bean_ElearnTools.FormatDate error: "
                        + e.getMessage());
                return DateStr;
            }
        }
    }

    //==========================================================================
    // METHOD: getSysDate
    // DESC : 获取当前系统日期
    // CREATE: 1.0, liu-ag, 2002-7-12
    // MODIFY:
    //==========================================================================
    public String getSysDate() throws Exception {
        Calendar rightNow = Calendar.getInstance();

        Integer year = new Integer(rightNow.get(Calendar.YEAR));
        Integer month = new Integer(rightNow.get(Calendar.MONTH) + 1);
        Integer day = new Integer(rightNow.get(Calendar.DATE));

        String sYear = null;
        String sMonth = null;
        String sDay = null;

        sYear = year.toString();
        if (month.intValue() < 10)
            sMonth = "0" + month.toString();
        else
            sMonth = month.toString();
        if (day.intValue() < 10)
            sDay = "0" + day.toString();
        else
            sDay = day.toString();

        //return sYear +"-" +sMonth +"-" +sDay;
        BeanDateFormat beanDate = new BeanDateFormat();
        return beanDate.parseDBDate(sYear, sMonth, sDay);
    }

    //自动发号程序，将送来的字符串按数字方式加1
    public String AutoNum(String strNum) {
        String strZero = "";
        String rtnNum = "";
        int numLen = strNum.length();
        //将来准备自动在前面加"0";
        if (numLen == 0) {
            return "0";
        } else {
            for (int i = 0; i < numLen; i++) {
                strZero = strZero + "0";
            }
            int numIntValue = Integer.valueOf(strNum).intValue();
            numIntValue = numIntValue + 1;
            rtnNum = strZero + Integer.toString(numIntValue);
            rtnNum = rtnNum
                    .substring(rtnNum.length() - numLen, rtnNum.length());
            return rtnNum;
        }
    }

    //自动发号程序，将送来的字符串按数字方式加1
    public String AutoNum4(String strNum) {
        String strZero = "";
        String rtnNum = "";
        int numLen = strNum.length();
        //将来准备自动在前面加"0";
        if (numLen == 0) {
            return "0";
        } else {
            for (int i = 0; i < numLen; i++) {
                strZero = strZero + "0";
            }
            int numIntValue = Integer.valueOf(strNum).intValue();
            numIntValue = numIntValue + 1;
            rtnNum = strZero + Integer.toString(numIntValue);
            numLen = Integer.toString(numIntValue).length();
            rtnNum = rtnNum
                    .substring(rtnNum.length() - numLen, rtnNum.length());
            return rtnNum;
        }
    }

    //处理中文参数
    public String getGBKString(String str) {
        boolean processURLChinese = com.htyz.system.SystemProperties
                .getBooleanProperty("processURLChinese");
        if (processURLChinese) {
            try {
                String str_temp = str;
                byte[] temp_t = str_temp.getBytes("ISO8859-1");
                str_temp = new String(temp_t);
                return str_temp;
            } catch (Exception e) {
                return null;
            }
        } else {
            return str;
        }
    }

    //建立文件夹
    public boolean MakeDir(String s_dir) {
        //转换为UNIX下的标准目录
        StringTokenizer tokens = new StringTokenizer(s_dir, "\\");
        s_dir = "";
        while (tokens.hasMoreTokens()) {
            s_dir = s_dir + tokens.nextToken().trim() + "/";
        }
        s_dir = s_dir.substring(0, s_dir.length() - 1);
        tokens = new StringTokenizer(s_dir, "/");
        s_dir = "";
        while (tokens.hasMoreTokens()) {
            s_dir = s_dir + tokens.nextToken().trim() + "/";
            System.out.println(s_dir);
            File fileFolder = new File(s_dir);
            if (!fileFolder.exists()) {
                if (!fileFolder.mkdir()) {
                    System.err.println("create " + s_dir + " Fail!");
                    return false;
                }
            }
        }
        return true;
    }

    //判断是不是管理员
    public boolean isAdmin(String userid) {
        if (userid.equals("")) {
            return false;
        }
        beanGetdata bgd = new beanGetdata();
        try {
            bgd.executeSelect("select user_right from t_user where user_id = '"
                    + userid + "'");
            if (bgd.getRowCount() == 0)
                return false;
            return isMenuShow(parseRight("6"), bgd.getFieldValue("user_right",
                    0));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //获取用户权限
    public String getUserRight(String userid) {
        try{
        beanGetdata bgd = new beanGetdata();
        bgd.executeSelect("select user_right from t_user where user_id = '"
                + userid + "'");
        if (bgd.getRowCount() == 0)
            return "0";
        return bgd.getFieldValue("user_right", 0);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
   }

    //判断用户角色，roleCode是代码的角色值
    public boolean isRole(String userid, String roleCode) {
        return isMenuShow(getUserRight(userid), parseRight(roleCode));
    }

    //课程管理导航
    public String getClassNavigation(String contextPath) {
        String s_navigation = "<A href=\"" + contextPath +"/\" target=\"_top\">首页</A>"
                + "&gt;<A href=\"lessonManager\" target=\"_top\">课程管理</A>";
        return s_navigation;
    }

    public String getClassNavigation(String classid, String contextPath) {
        String s_navigation, temp;
        beanQueryCodes bqc = new beanQueryCodes();

        if (classid.length() == 4)
            temp = classid;
        else
            temp = classid.substring(0, 4);
        bqc.QueryCode("0013", temp);
        s_navigation = getClassNavigation(contextPath)
                + "&gt;<A href=\"lessonList?action=showDetail&codeid=0013&codevalue="
                + temp + "\">" + bqc.getCodeValue("code_namec", 0) + "</A>";

        if (classid.length() >= 8) {
            bqc.QueryCode(temp, classid.substring(4, 8));
            s_navigation = s_navigation
                    + "&gt;<A href=\"lessonList?action=showDetail&codeid="
                    + temp + "&codevalue=" + classid.substring(4, 8) + "\">"
                    + bqc.getCodeValue("code_namec", 0) + "</A>";
        }
        return s_navigation;
    }

    public String getClassNavigation(String classid, String lessonid, String contextPath) {
        String s_navigation;
        beanGetdata bgd = new beanGetdata();
        s_navigation = getClassNavigation(classid, contextPath);
        try {
        bgd.executeSelect("SELECT class_name FROM t_class WHERE class_id = '"
                + classid + "'");
        s_navigation = s_navigation
                + "&gt;<A href=\"chapterList?reload=true&classid=" + classid
                + "\" target=left>" + bgd.getFieldValue("class_name", 0)
                + "</A>";

        if (!lessonid.endsWith("000")) {
            lessonid = lessonid.substring(0, 3) + "000";
            bgd
                    .executeSelect("SELECT lesson_name FROM t_lesson WHERE class_id = '"
                            + classid + "' AND lesson_id = '" + lessonid + "'");
            s_navigation = s_navigation
                    + "&gt;<A href=\"chapterList?action=showDetail&classid="
                    + classid + "&lessonid=" + lessonid + "\">"
                    + bgd.getFieldValue("lesson_name", 0) + "</A>";
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return s_navigation;
    }

    //将字符串转换为HTML
    public String stringToHtml(String str) {
        //		str = str.replaceAll("<", "&lt;");
        //		str = str.replaceAll(">", "&gt;");
        //		str = str.replaceAll(" ", "&nbsp;");
        //		str = str.replaceAll("\"", "&quot;");
        str = strReplace(str, "<", "&lt;");
        str = strReplace(str, ">", "&gt;");
        str = strReplace(str, " ", "&nbsp;");
        str = strReplace(str, "\"", "&quot;");
        str = stringReplace(str, '\n', "<BR>");
        return str;
    }

    //扩展字符串替换功能，将字符串中的char替换为新的字符串
    public String stringReplace(String str, char c, String s) {
        //		String temp = "";
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                //				temp = temp + str.substring(0, i) + s;
                temp.append(str.substring(0, i)).append(s);
                str = str.substring(i + 1, str.length());
                i = 0;
            }
        }
        //		str = temp + str;
        //		return str;
        temp.append(str);
        return temp.toString();
    }

    //replace函数，将fString中的olds串换成news
    public String strReplace(String fString, String olds, String news) {
        int Startwith = fString.indexOf(olds);
        while (Startwith != -1) {
            fString = fString.substring(0, Startwith) + news
                    + fString.substring(Startwith + olds.length());
            Startwith = fString.indexOf(olds);
        }
        return fString;

    }

    public String FormatFlash(String fString) {
        String strFlashBASE = "<OBJECT codeBase=http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=4,0,2,0 width=500 height=400 classid=clsid:D27CDB6E-AE6D-11cf-96B8-444553540000><PARAM NAME=movie VALUE=\"$2\"><PARAM NAME=quality VALUE=high><embed src=\"$2\" quality=high pluginspage=\"http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash\" type=\"application/x-shockwave-flash\" width=500 height=400>$2</embed></OBJECT>";
        int Startwith, Stopwith;
        String tmpFlash, tmpInsert;
        fString = strReplace(fString, "[FLASH]", "[flash]");
        fString = strReplace(fString, "[/FLASH]", "[/flash]");
        Startwith = fString.indexOf("[flash]");
        while (Startwith != -1) {
            Stopwith = fString.indexOf("[/flash]");
            tmpFlash = fString.substring(Startwith + 7, Stopwith);
            tmpInsert = strReplace(strFlashBASE, "$2", tmpFlash);
            fString = fString.substring(0, Startwith) + tmpInsert
                    + fString.substring(Stopwith + 8);
            Startwith = fString.indexOf("[flash]");

        }
        return fString;
    }

    public String FormatImage(String fString) {
        String strFlashBASE = "<a herf='$1' target=_blank title=开新窗口浏览><img src='$1' border=0 onload='javascript:if(this.width>screen.width-333)this.width=screen.width-333'></a>";
        int Startwith, Stopwith;
        String tmpFlash, tmpInsert;
        fString = strReplace(fString, "[IMG]", "[img]");
        fString = strReplace(fString, "[/IMG]", "[/img]");

        Startwith = fString.indexOf("[img]");
        while (Startwith != -1) {
            Stopwith = fString.indexOf("[/img]");
            tmpFlash = fString.substring(Startwith + 5, Stopwith);
            tmpInsert = strReplace(strFlashBASE, "$1", tmpFlash);
            fString = fString.substring(0, Startwith) + tmpInsert
                    + fString.substring(Stopwith + 6);
            Startwith = fString.indexOf("[img]");

        }
        return fString;
    }

    //将网页中的UBB字串换成标准HTML格式

    public String strFormat(String fString) {
        fString = fString.replace('\n', '\r');
        fString = strReplace(fString, "\r\r", "</P><P>");
        fString = strReplace(fString, "\r", "<BR>");
        fString = strReplace(fString, "[b]", "<b>");
        fString = strReplace(fString, "[/b]", "</b>");
        fString = strReplace(fString, "[s]", "<s>");
        fString = strReplace(fString, "[/s]", "</s>");
        fString = strReplace(fString, "[u]", "<u>");
        fString = strReplace(fString, "[/u]", "</u>");
        fString = strReplace(fString, "[i]", "<i>");
        fString = strReplace(fString, "[/i]", "</i>");
        fString = strReplace(fString, "[font=宋体]", "<font face='宋体'>");
        fString = strReplace(fString, "[/font=宋体]", "</font id='宋体'>");
        fString = strReplace(fString, "[font=楷体]", "<font face='楷体_GB2312'>");
        fString = strReplace(fString, "[/font=楷体]", "</font id='楷体_GB2312'>");
        fString = strReplace(fString, "[font=新宋体]", "<font face='新宋体'>");
        fString = strReplace(fString, "[/font=新宋体]", "</font id='新宋体'>");
        fString = strReplace(fString, "[font=黑体]", "<font face='黑体'>");
        fString = strReplace(fString, "[/font=黑体]", "</font id='黑体'>");
        fString = strReplace(fString, "[font=隶书]", "<font face='隶书'>");
        fString = strReplace(fString, "[/font=隶书]", "</font id='隶书'>");
        fString = strReplace(fString, "[font=Andale Mono]",
                "<font face='Andale Mono'>");
        fString = strReplace(fString, "[/font=Andale Mono]",
                "</font id='Andale Mono'>");
        fString = strReplace(fString, "[font=Arial]", "<font face='Arial'>");
        fString = strReplace(fString, "[/font=Arial]", "</font id='Arial'>");
        fString = strReplace(fString, "[font=Arial Black]",
                "<font face='Arial Black'>");
        fString = strReplace(fString, "[/font=Arial Black]",
                "</font id='Arial Black'>");
        fString = strReplace(fString, "[font=Book Antiqua]",
                "<font face='Book Antiqua'>");
        fString = strReplace(fString, "[/font=Book Antiqua]",
                "</font id='Book Antiqua'>");
        fString = strReplace(fString, "[font=Century Gothic]",
                "<font face='Century Gothic'>");
        fString = strReplace(fString, "[/font=Century Gothic]",
                "</font id='Century Gothic'>");
        fString = strReplace(fString, "[font=Comic Sans MS]",
                "<font face='Comic Sans MS'>");
        fString = strReplace(fString, "[/font=Comic Sans MS]",
                "</font id='Comic Sans MS'>");
        fString = strReplace(fString, "[font=Courier New]",
                "<font face='Courier New'>");
        fString = strReplace(fString, "[/font=Courier New]",
                "</font id='Courier New'>");
        fString = strReplace(fString, "[font=Georgia]", "<font face='Georgia'>");
        fString = strReplace(fString, "[/font=Georgia]", "</font id='Georgia'>");
        fString = strReplace(fString, "[font=Impact]", "<font face='Impact'>");
        fString = strReplace(fString, "[/font=Impact]", "</font id='Impact'>");
        fString = strReplace(fString, "[font=Tahoma]", "<font face='Tahoma'>");
        fString = strReplace(fString, "[/font=Tahoma]", "</font id='Tahoma'>");
        fString = strReplace(fString, "[font=Times New Roman]",
                "<font face='Times New Roman'>");
        fString = strReplace(fString, "[/font=Times New Roman]",
                "</font id='Times New Roman'>");
        fString = strReplace(fString, "[font=Trebuchet MS]",
                "<font face='Trebuchet MS'>");
        fString = strReplace(fString, "[/font=Trebuchet MS]",
                "</font id='Trebuchet MS'>");
        fString = strReplace(fString, "[font=Script MT Bold]",
                "<font face='Script MT Bold'>");
        fString = strReplace(fString, "[/font=Script MT Bold]",
                "</font id='Script MT Bold'>");
        fString = strReplace(fString, "[font=Stencil]", "<font face='Stencil'>");
        fString = strReplace(fString, "[/font=Stencil]", "</font id='Stencil'>");
        fString = strReplace(fString, "[font=Lucida Console]",
                "<font face='Lucida Console'>");
        fString = strReplace(fString, "[/font=Lucida Console]",
                "</font id='Lucida Console'>");

        fString = strReplace(fString, "[red]", "<font color=red>");
        fString = strReplace(fString, "[/red]", "</font id=red>");
        fString = strReplace(fString, "[green]", "<font color=green>");
        fString = strReplace(fString, "[/green]", "</font id=green>");
        fString = strReplace(fString, "[blue]", "<font color=blue>");
        fString = strReplace(fString, "[/blue]", "</font id=blue>");
        fString = strReplace(fString, "[white]", "<font color=white>");
        fString = strReplace(fString, "[/white]", "</font id=white>");
        fString = strReplace(fString, "[purple]", "<font color=purple>");
        fString = strReplace(fString, "[/purple]", "</font id=purple>");
        fString = strReplace(fString, "[yellow]", "<font color=yellow>");
        fString = strReplace(fString, "[/yellow]", "</font id=yellow>");
        fString = strReplace(fString, "[violet]", "<font color=violet>");
        fString = strReplace(fString, "[/violet]", "</font id=violet>");
        fString = strReplace(fString, "[brown]", "<font color=brown>");
        fString = strReplace(fString, "[/brown]", "</font id=brown>");
        fString = strReplace(fString, "[black]", "<font color=black>");
        fString = strReplace(fString, "[/black]", "</font id=black>");
        fString = strReplace(fString, "[pink]", "<font color=pink>");
        fString = strReplace(fString, "[/pink]", "</font id=pink>");
        fString = strReplace(fString, "[orange]", "<font color=orange>");
        fString = strReplace(fString, "[/orange]", "</font id=orange>");
        fString = strReplace(fString, "[gold]", "<font color=gold>");
        fString = strReplace(fString, "[/gold]", "</font id=gold>");
        fString = strReplace(fString, "[gray]", "<font color=gray>");
        fString = strReplace(fString, "[/gray]", "</font id=gray>");
        fString = strReplace(fString, "[silver]", "<font color=silver>");
        fString = strReplace(fString, "[/silver]", "</font id=silver>");
        fString = strReplace(fString, "[beige]", "<font color=beige>");
        fString = strReplace(fString, "[/beige]", "</font id=beige>");
        fString = strReplace(fString, "[teal]", "<font color=teal>");
        fString = strReplace(fString, "[/teal]", "</font id=teal>");
        fString = strReplace(fString, "[navy]", "<font color=navy>");
        fString = strReplace(fString, "[/navy]", "</font id=navy>");
        fString = strReplace(fString, "[maroon]", "<font color=maroon>");
        fString = strReplace(fString, "[/maroon]", "</font id=maroon>");
        fString = strReplace(fString, "[limegreen]", "<font color=limegreen>");
        fString = strReplace(fString, "[/limegreen]", "</font id=limegreen>");
        fString = strReplace(fString, "[h1]", "<h1>");
        fString = strReplace(fString, "[/h1]", "</h1>");
        fString = strReplace(fString, "[h2]", "<h2>");
        fString = strReplace(fString, "[/h2]", "</h2>");
        fString = strReplace(fString, "[h3]", "<h3>");
        fString = strReplace(fString, "[/h3]", "</h3>");
        fString = strReplace(fString, "[h4]", "<h4>");
        fString = strReplace(fString, "[/h4]", "</h4>");
        fString = strReplace(fString, "[h5]", "<h5>");
        fString = strReplace(fString, "[/h5]", "</h5>");
        fString = strReplace(fString, "[h6]", "<h6>");
        fString = strReplace(fString, "[/h6]", "</h6>");
        fString = strReplace(fString, "[size=1]", "<font size='1'>");
        fString = strReplace(fString, "[/size=1]", "</font id=1>");
        fString = strReplace(fString, "[size=2]", "<font size='2'>");
        fString = strReplace(fString, "[/size=2]", "</font id=2>");
        fString = strReplace(fString, "[size=3]", "<font size='3'>");
        fString = strReplace(fString, "[/size=3]", "</font id=3>");
        fString = strReplace(fString, "[size=4]", "<font size='4'>");
        fString = strReplace(fString, "[/size=4]", "</font id=4>");
        fString = strReplace(fString, "[size=5]", "<font size='5'>");
        fString = strReplace(fString, "[/size=5]", "</font id=5>");
        fString = strReplace(fString, "[size=6]", "<font size='6'>");
        fString = strReplace(fString, "[/size=6]", "</font id=6>");
        fString = strReplace(fString, "[br]", "<br>");
        fString = strReplace(fString, "[/url]", "[/URL]");
        fString = strReplace(fString, "[url", "[URL");
        fString = strReplace(fString, "[left]", "<div align=left>");
        fString = strReplace(fString, "[/left]", "</div id=left>");
        fString = strReplace(fString, "[center]", "<center>");
        fString = strReplace(fString, "[/center]", "</center>");
        fString = strReplace(fString, "[right]", "<div align=right>");
        fString = strReplace(fString, "[/right]", "</div id=right>");
        fString = strReplace(fString, "[list]", "<ul>");
        fString = strReplace(fString, "[/list]", "</ul>");
        fString = strReplace(fString, "[list=1]", "<ol type=1>");
        fString = strReplace(fString, "[/list=1]", "</ol id=1>");
        fString = strReplace(fString, "[list=a]", "<ol type=a>");
        fString = strReplace(fString, "[/list=a]", "</ol id=a>");
        fString = strReplace(fString, "[list=A]", "<ol type=a>");
        fString = strReplace(fString, "[/list=A]", "</ol id=a>");
        fString = strReplace(fString, "[*]", "<li>");
        fString = strReplace(fString, "[/*]", "</li>");
        fString = strReplace(fString, "[quote]",
                "<BLOCKQUOTE id=quote><font id=quote><hr height=1 noshade id=quote>");
        fString = strReplace(fString, "[/quote]",
                "<hr height=1 noshade id=quote></font id=quote></BLOCKQUOTE id=quote>");
        fString = strReplace(fString, "[fly]",
                "<marquee width=90% behavior=alternate scrollamount=3>");
        fString = strReplace(fString, "[/fly]", "</marquee>");
        fString = strReplace(fString, "[move]", "<MARQUEE scrollamount=3>");
        fString = strReplace(fString, "[/move]", "</MARQUEE>");

        fString = strReplace(
                fString,
                "[quote]",
                "<BR><table cellpadding=0 cellspacing=0 border=0 WIDTH=94% bgcolor=#000000 align=center><tr><td><table width=100% cellpadding=5 cellspacing=1 border=0><TR><TD BGCOLOR=#F8F8F8><font id=quote>");
        fString = strReplace(fString, "[/quote]",
                "</td></tr></table></td></tr></table>");
        fString = strReplace(
                fString,
                "[quote]",
                "<table cellpadding=0 cellspacing=0 border=0 WIDTH=94% bgcolor=#000000 align=center><tr><td><table width=100% cellpadding=5 cellspacing=1 border=0><TR><TD BGCOLOR=#F8F8F8><font id=quote>");
        fString = FormatFlash(fString);
        fString = FormatImage(fString);
        return fString;
    }

    public String getSignature(String userid) {
        try {
        beanGetdata bgd = new beanGetdata();
        bgd.executeSelect("select signature from t_user where user_id = '"
                + userid + "'");
        if (bgd.getRowCount() < 1) {
            return "";
        } else {
            return "<BR><BR>------------------------------------------------------------------------------------<BR>"
                    + strFormat(bgd.getFieldValue(1, 0));
        }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public String getDBDate() {
        try {
        beanGetdata getData = new beanGetdata();
        getData
                .executeSelect("SELECT getdate() AS getdate FROM t_code WHERE code_id = '0000' AND code_value = '0001'");
        return getData.getFieldValue("getdate", 0);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}