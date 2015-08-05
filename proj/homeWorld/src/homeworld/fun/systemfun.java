/**
 * @(#)BBSTopicForm.java  2003-11-7
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package homeworld.fun;

/**
 * <P>显示分页的超文本链接</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */
import java.util.Vector;

//import org.apache.struts.action.ActionError;

import com.idn.sql.DataBean;

public class systemfun {
	
	public systemfun() {
		super();
	}

	public static String getNamec(String name) throws Exception{
		String sql="select vndnam from vndinfo where vndnum=upper('"+name+"')";
		DataBean dbBean = new DataBean();	
		try{
		dbBean.executeSelect(sql);
		} catch (Exception e) {
		return "-1";
		}
		String s_re=dbBean.getElementValue(0,0).trim();
		
		if (s_re.equals("")){
			s_re=name;
		}
		return  s_re;
	}
	
	public static  String coding(String[] strTemp){
		String Purview ="";
		if (strTemp != null){
			for (int i = 1; i < strTemp.length + 1;i++){
				Purview = "1"+string("0",Integer.parseInt(strTemp[i-1])-Purview.length()) + Purview;
			}
		}
		Purview="00000000000000000000000000000000"+Purview;
		Purview=Purview.substring(Purview.length()-32);
		return Purview;
	}	
	
	public static String[] decode(String Purview) {

		Vector vecTemp=new Vector();
		String[] strCshowc =null;
		String temp="";
		int i=0;
		
		for (int intRow = Purview.length()-1; intRow >=0 ;intRow--){
			temp=Purview.substring(intRow,intRow+1);
			if(temp.equals("1")){
				vecTemp.addElement(Integer.toString(i));
			}
			i++;
			
		}
		strCshowc  = (String[]) vecTemp.toArray(new String[0]);
		return strCshowc;
		
	}
	
	public static String string(String ss,int count) {
		
		String s_return="";
		for (int intRow = 0; intRow < count;intRow++){
			s_return=s_return+ss;
		}
		return s_return;
	}
	

}
