package com.htyz.notice;

import java.util.*;
import java.util.Calendar.*;
public class noticeDO implements java.io.Serializable
{
//attribute//======================[begin]
    private String notice_id;//֪ͨ��
    private String user_id ;//�û���
    private String notice_name;//֪ͨ����
    private String notice_content;//֪ͨ����
    private String notice_time;//֪ͨʱ��
    private String valid_time;//��Чʱ��
    private String to_time;//��ֹʱ��
    private String notice_class;//֪ͨ���
    private String user_name ;//�û���

//attribute//======================[end]


//methods//======================[begin]
//==========================================================//
//         Note:   ֪ͨ��                                      //
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
//         Note:   �û���                                      //
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
//         Note:   ֪ͨ����                                      //
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
//         Note:   ֪ͨ����                                      //
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
//         Note:   ֪ͨʱ��                                  //
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
//         Note:   ��Чʱ��                                      //
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
//         Note:   ��ֹʱ��                                      //
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
//         Note:   ֪ͨ���                                    //
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
//         Note:   �û���                                    //
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
