package com.htyz.notice;

import java.util.*;

public class userDO implements java.io.Serializable
{
//attribute//======================[begin]
    private String user_id;//�û���
    private String user_name ;//�û���


//attribute//======================[end]


//methods//======================[begin]
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
//         Note:   �û���                                      //
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
