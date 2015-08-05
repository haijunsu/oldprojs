/**
 * @(#)BBSTopicForm.java  2003-11-7
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package homeworld.bbs;

/**
 * <P>处理通告的历史</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */
import commsearch.CommSQL;
import commsearch.util.CommDate;

public class BbsFun {

	//利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(BbsFun.class);
	
	
	CommSQL cs = new CommSQL();	
	public BbsFun() {
		super();
	}
	/**
	 * CLEAR通告历史
	 * 
	 * @param String 通告ID
	 * @return String 1、成功 "" 失败
	 */			
	
	
	public String clearTopicLog(String topid) {
		String s_return="";
		StringBuffer sb_sql = null;
		
		
		try {
			//通告修改后对通告历史作CLEAR
			sb_sql = new StringBuffer("");
			sb_sql.append("UPDATE TOPICLOG ");
			sb_sql.append("SET TOPFLAG='3' ");
			sb_sql.append(" WHERE ");
			sb_sql.append("TOPID ='");
			sb_sql.append(topid);
			sb_sql.append("'");
			log.debug("对通告历史作CLEAR：" + sb_sql.toString());
			cs.executeBatchSQL(sb_sql.toString());			
			s_return = "1";
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		return s_return;
	}
	/**
	 * 记录通告历史
	 * 
	 * @param String 通告ID
	 * @param String IP地址 
	 * @param String 浏览人
	 * @return String 1、成功 "" 失败
	 */			

	public String addTopicLog(String topid,String act_ip,String userid) {
		
		String s_return="",s_now;
		String[][] temp = null;
		StringBuffer sb_select = null,sb_sql = null;
		int i_seq;
		
		
		try{		
			
			//当前时间
			CommDate cd = new CommDate();
			s_now = cd.getNow(cd.FORMAT_DATETIME_SIGN);
			//查询一下通告日志表中是否有这一TOPID与ACT_IP并且TOPFLAG为1的记录
			sb_select =  new StringBuffer("");
			sb_select.append("SELECT SEQ FROM TOPICLOG WHERE ");
			sb_select.append(" TOPID='");
			sb_select.append(topid);
			sb_select.append("' AND ACT_IP='");
			sb_select.append(act_ip);
			sb_select.append("' AND TOPFLAG='1'");
			log.debug("查询通告历史：" + sb_select.toString());
			temp = cs.executeSelect(sb_select.toString());
			if (temp==null){
				//无此记录（未看过）
				sb_select =  new StringBuffer("");
				sb_select.append("SELECT SEQ FROM TOPICLOG WHERE ");
				sb_select.append(" TOPID='");
				sb_select.append(topid);
				sb_select.append("' AND ACT_IP='");
				sb_select.append(act_ip);
				sb_select.append("' ORDER BY SEQ DESC");
				log.debug("查询通告历史的SEQ：" + sb_select.toString());
				//查询一下通告日志表中是否有这一TOPID与ACT_IP，按SEQ DESC排序
				temp = cs.executeSelect(sb_select.toString());
				if (temp == null){
					//无记录
					i_seq = 1;
				} else {
					//有记录
					i_seq = Integer.parseInt(temp[0][0]) + 1;
				}
				//将新的一笔历史INSERT 进入数据库
				sb_sql = new StringBuffer("");
				sb_sql.append("INSERT INTO TOPICLOG ");
				sb_sql.append("(TOPID,ACT_IP,SEQ,TOPFLAG,USERID,ACT_DATETIME,ACT_MEMO)");
				sb_sql.append(" VALUES ");
				sb_sql.append("('");
				sb_sql.append(topid);
				sb_sql.append("','");
				sb_sql.append(act_ip);
				sb_sql.append("',");
				sb_sql.append(Integer.toString(i_seq));
				sb_sql.append(",");
				sb_sql.append("'1'");
				sb_sql.append(",'");
				sb_sql.append(userid);
				sb_sql.append("','");
				sb_sql.append(s_now);
				sb_sql.append("',");
				sb_sql.append("''");
				sb_sql.append(")");
				log.debug("对通告历史作INSERT：" + sb_sql.toString());
				cs.executeBatchSQL(sb_sql.toString());
			} else {
				//有此条件的记录（已经看过），改变USERID（用户名）与ACT_DATETIME（时间）
				sb_sql = new StringBuffer("");
				sb_sql.append("UPDATE TOPICLOG SET ");
				sb_sql.append(" USERID = '");
				sb_sql.append(userid);
				sb_sql.append("',ACT_DATETIME='");
				sb_sql.append(s_now);
				sb_sql.append("' WHERE ");
				sb_sql.append(" TOPID='");
				sb_sql.append(topid);
				sb_sql.append("' AND ACT_IP='");
				sb_sql.append(act_ip);
				sb_sql.append("' AND seq=");
				sb_sql.append(temp[0][0]);
				log.debug("对通告历史作最后一次更新：" + sb_sql.toString());
				cs.executeBatchSQL(sb_sql.toString());
			}
			s_return = "1";
		} catch (Exception e){
			e.printStackTrace();
		}
		return s_return;
	}	
}
