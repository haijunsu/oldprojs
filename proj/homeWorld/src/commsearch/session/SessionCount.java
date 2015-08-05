/* 
*当前在线用户程序 
*@XIAOAI
*2004-11-03 
*/ 
package commsearch.session; 

import javax.servlet.http.*; 

public class SessionCount implements HttpSessionListener 
{ 
	//利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(SessionCount.class);

	private static int count=0; 
	/**
	* Constructor
	*/
	public SessionCount() {
		log.debug("Session监测已经启动!");
	}	
	public void sessionCreated(HttpSessionEvent se) 
	{ 
		count++; 
		log.debug("session创建："+new java.util.Date()); 
	} 
	
	public void sessionDestroyed(HttpSessionEvent se) 
	{ 
		count--; 
		log.debug("session销毁:"+new java.util.Date()); 
	} 
	
	public static int getCount() 
	{ 
		return(count); 
	} 
}