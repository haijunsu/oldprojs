package com.htyz.notice;

import java.util.*;

public class groupDO implements java.io.Serializable
{
//attribute//======================[begin]
    private String group_id;//组号
    private String group_name ;//组名


//attribute//======================[end]


//methods//======================[begin]
//==========================================================//
//         Note:   组号                                      //
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
//         Note:  组名                                      //
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
