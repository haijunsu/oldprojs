package com.htyz.notice;

import java.util.*;
import java.util.Calendar.*;
public class noticeDO implements java.io.Serializable
{
//attribute//======================[begin]
    private String notice_id;//通知号
    private String user_id ;//用户号
    private String notice_name;//通知名称
    private String notice_content;//通知内容
    private String notice_time;//通知时间
    private String valid_time;//生效时间
    private String to_time;//截止时间
    private String notice_class;//通知类别
    private String user_name ;//用户名

//attribute//======================[end]


//methods//======================[begin]
//==========================================================//
//         Note:   通知号                                      //
//==========================================================//
     public String getNotice_id ()
     {
         return notice_id;
     }
     public void setNotice_id (String in )
     {
         notice_id = in ;
     }
//==========================================================//
//         Note:   用户号                                      //
//==========================================================//
     public String getUser_id ()
     {
         return user_id;
     }
     public void setUser_id (String in )
     {
         user_id = in ;
     }
//==========================================================//
//         Note:   通知名称                                      //
//==========================================================//
     public String getNotice_name ()
     {
         return notice_name;
     }
     public void setNotice_name (String in )
     {
         notice_name = in ;
     }
//==========================================================//
//         Note:   通知内容                                      //
//==========================================================//
     public String getNotice_content ()
     {
         return notice_content;
     }
     public void setNotice_content (String in )
     {
         notice_content = in ;
     }
//==========================================================//
//         Note:   通知时间                                  //
//==========================================================//
     public String getNotice_time ()
     {
         return notice_time;
     }
     public void setNotice_time (String in )
     {
         notice_time = in ;
     }
//==========================================================//
//         Note:   生效时间                                      //
//==========================================================//
     public String getValid_time ()
     {
         return valid_time;
     }
     public void setValid_time (String in )
     {
         valid_time = in ;
     }
//==========================================================//
//         Note:   截止时间                                      //
//==========================================================//
     public String getTo_time ()
     {
         return to_time;
     }
     public void setTo_time (String in )
     {
         to_time = in ;
     }
//==========================================================//
//         Note:   通知类别                                    //
//==========================================================//
     public String getNotice_class ()
     {
         return notice_class;
     }
     public void setNotice_class (String in )
     {
         notice_class = in ;
     }
  //==========================================================//
//         Note:   用户名                                    //
//==========================================================//
     public String getUser_name ()
     {
         return user_name;
     }
     public void setUser_name (String in )
     {
         user_name = in ;
     }

//methods//======================[end]

}
