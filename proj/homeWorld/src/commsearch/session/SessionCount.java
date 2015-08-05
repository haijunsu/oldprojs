/* 
*��ǰ�����û����� 
*@XIAOAI
*2004-11-03 
*/ 
package commsearch.session; 

import javax.servlet.http.*; 

public class SessionCount implements HttpSessionListener 
{ 
	//���÷�װBean-com.idn.log.LogWrapper��������־��¼
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(SessionCount.class);

	private static int count=0; 
	/**
	* Constructor
	*/
	public SessionCount() {
		log.debug("Session����Ѿ�����!");
	}	
	public void sessionCreated(HttpSessionEvent se) 
	{ 
		count++; 
		log.debug("session������"+new java.util.Date()); 
	} 
	
	public void sessionDestroyed(HttpSessionEvent se) 
	{ 
		count--; 
		log.debug("session����:"+new java.util.Date()); 
	} 
	
	public static int getCount() 
	{ 
		return(count); 
	} 
}