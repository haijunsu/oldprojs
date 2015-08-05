package com.htyz.notice;

import java.util.*;

public class userDO implements java.io.Serializable
{
//attribute//======================[begin]
    private String user_id;//用户号
    private String user_name ;//用户号


//attribute//======================[end]


//methods//======================[begin]
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
//         Note:   用户名                                      //
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
