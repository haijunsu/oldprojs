package com.htyz.notice;

import java.util.*;

public class groupDO implements java.io.Serializable
{
//attribute//======================[begin]
    private String group_id;//���
    private String group_name ;//����


//attribute//======================[end]


//methods//======================[begin]
//==========================================================//
//         Note:   ���                                      //
//==========================================================//
     public String getGroup_id ()
     {
         return group_id;
     }
     public void setGroup_id (String in )
     {
         group_id = in ;
     }

//==========================================================//
//         Note:  ����                                      //
//==========================================================//
     public String getGroup_name ()
     {
         return group_name;
     }
     public void setGroup_name (String in )
     {
        group_name = in ;
     }


//methods//======================[end]

}
