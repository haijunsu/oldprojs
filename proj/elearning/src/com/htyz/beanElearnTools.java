// /////////////////////////////////
//���õĹ��ߺ���
//
//  �պ���
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
        //�����ʼ����Ϣ
    }

    //У���Ƿ���ʾ��MENU��userPriv���û���Ȩ�ޣ�menuPriv�ǲ˵���Ȩ��
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

    //�����û��Ͳ˵�Ȩ��
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

    //�����û���Ȩ��
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

    //	У��SQL����е������Ƿ�Ϸ�����ƴдSQL���ǰ�����ַ��������ж�
    //	���ڸ�ʽ��yyyy-mm-dd
    //	�����ţ�
    //	1: ok, ���ںϷ�
    //-1�����ڸ�ʽ����
    //-2�������ʾ����
    //-3���·ݱ�ʾ����
    //-4�����ڱ�ʾ����
    //-5�����������Χ
    //-6���·ݳ�����Χ
    //-7�����ڳ�����Χ
    //-8������ӦΪ30��
    //-9������2�·ݵ�������29��
    //-10��2�·ݵ�������28��
    //	
    public int check_date(String date_str) {
        String num = "0123456789";
        String year, month, day;
        String temp_str;
        int i, i1, j, int_year, int_month, int_day;
        year = "";
        month = "";
        day = "";
        //У�����ڸ�ʽ�������������
        i = date_str.indexOf("-");
        if (i == -1) {
            return -1;
        } else {
            //ȡ�����
            year = date_str.substring(0, i);
            i1 = date_str.indexOf("-", i + 1);
            if (i1 == -1 || (i1 - i) == 1) {
                return -1;
            } else {
                //ȡ���·ݺ�����
                month = date_str.substring(i + 1, i1);
                day = date_str.substring(i1 + 1, date_str.length());
            }
        }
        //�ж�����ĳ��Ȳ�У���Ƿ�Ϊ����
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
        //�ж��·ݵĳ��Ȳ�У���Ƿ�Ϊ����
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
        //�ж����ڵĳ��Ȳ�У���Ƿ�Ϊ����
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
        //��������ת��Ϊ�������Է������
        int_year = Integer.valueOf(year).intValue();
        int_month = Integer.valueOf(month).intValue();
        int_day = Integer.valueOf(day).intValue();
        //�ж�����Ƿ񳬳���Χ����һλΪ��Ϊ0����Ч���Ϊ1753~9999
        if (int_year < 1753) {
            return -5;
        }
        //�ж��·��Ƿ񳬳���Χ
        if (int_month < 1 || int_month > 12) {
            return -6;
        }
        //�ж������Ƿ񳬳���Χ
        if (int_day < 1 || int_day > 31) {
            return -7;
        }
        //��һ����4��6��9��11�·ݽ������ڳ����ж�
        if (int_day == 31
                && (int_month == 4 || int_month == 6 || int_month == 9 || int_month == 11)) {
            return -8;
        }
        //�ж��Ƿ�Ϊ������ȷ��2�·ݵ��������
        if (int_month == 2) {
            if (int_year % 4 == 0
                    || (int_year % 100 == 0 && int_year % 400 == 0)) {
                //����
                if (int_day > 29) {
                    return -9;
                }
            } else {
                if (int_day > 28) {
                    return -10;
                }
            }
        }
        //���ںϷ�
        return 1;
    }

    //�滻SQL����еĵ����ţ���ƴдSQL���ǰ�����ַ������д���
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

    //����SQL���ݿ���ѡ��������������ֶθ�ʽ��Ϊ������������
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
    // DESC : ��ȡ��ǰϵͳ����
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

    //�Զ����ų��򣬽��������ַ��������ַ�ʽ��1
    public String AutoNum(String strNum) {
        String strZero = "";
        String rtnNum = "";
        int numLen = strNum.length();
        //����׼���Զ���ǰ���"0";
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

    //�Զ����ų��򣬽��������ַ��������ַ�ʽ��1
    public String AutoNum4(String strNum) {
        String strZero = "";
        String rtnNum = "";
        int numLen = strNum.length();
        //����׼���Զ���ǰ���"0";
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

    //�������Ĳ���
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

    //�����ļ���
    public boolean MakeDir(String s_dir) {
        //ת��ΪUNIX�µı�׼Ŀ¼
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

    //�ж��ǲ��ǹ���Ա
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

    //��ȡ�û�Ȩ��
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

    //�ж��û���ɫ��roleCode�Ǵ���Ľ�ɫֵ
    public boolean isRole(String userid, String roleCode) {
        return isMenuShow(getUserRight(userid), parseRight(roleCode));
    }

    //�γ̹�����
    public String getClassNavigation(String contextPath) {
        String s_navigation = "<A href=\"" + contextPath +"/\" target=\"_top\">��ҳ</A>"
                + "&gt;<A href=\"lessonManager\" target=\"_top\">�γ̹���</A>";
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

    //���ַ���ת��ΪHTML
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

    //��չ�ַ����滻���ܣ����ַ����е�char�滻Ϊ�µ��ַ���
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

    //replace��������fString�е�olds������news
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
        String strFlashBASE = "<a herf='$1' target=_blank title=���´������><img src='$1' border=0 onload='javascript:if(this.width>screen.width-333)this.width=screen.width-333'></a>";
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

    //����ҳ�е�UBB�ִ����ɱ�׼HTML��ʽ

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
        fString = strReplace(fString, "[font=����]", "<font face='����'>");
        fString = strReplace(fString, "[/font=����]", "</font id='����'>");
        fString = strReplace(fString, "[font=����]", "<font face='����_GB2312'>");
        fString = strReplace(fString, "[/font=����]", "</font id='����_GB2312'>");
        fString = strReplace(fString, "[font=������]", "<font face='������'>");
        fString = strReplace(fString, "[/font=������]", "</font id='������'>");
        fString = strReplace(fString, "[font=����]", "<font face='����'>");
        fString = strReplace(fString, "[/font=����]", "</font id='����'>");
        fString = strReplace(fString, "[font=����]", "<font face='����'>");
        fString = strReplace(fString, "[/font=����]", "</font id='����'>");
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