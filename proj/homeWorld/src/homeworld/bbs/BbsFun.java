/**
 * @(#)BBSTopicForm.java  2003-11-7
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package homeworld.bbs;

/**
 * <P>����ͨ�����ʷ</P>
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

	//���÷�װBean-com.idn.log.LogWrapper��������־��¼
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(BbsFun.class);
	
	
	CommSQL cs = new CommSQL();	
	public BbsFun() {
		super();
	}
	/**
	 * CLEARͨ����ʷ
	 * 
	 * @param String ͨ��ID
	 * @return String 1���ɹ� "" ʧ��
	 */			
	
	
	public String clearTopicLog(String topid) {
		String s_return="";
		StringBuffer sb_sql = null;
		
		
		try {
			//ͨ���޸ĺ��ͨ����ʷ��CLEAR
			sb_sql = new StringBuffer("");
			sb_sql.append("UPDATE TOPICLOG ");
			sb_sql.append("SET TOPFLAG='3' ");
			sb_sql.append(" WHERE ");
			sb_sql.append("TOPID ='");
			sb_sql.append(topid);
			sb_sql.append("'");
			log.debug("��ͨ����ʷ��CLEAR��" + sb_sql.toString());
			cs.executeBatchSQL(sb_sql.toString());			
			s_return = "1";
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		return s_return;
	}
	/**
	 * ��¼ͨ����ʷ
	 * 
	 * @param String ͨ��ID
	 * @param String IP��ַ 
	 * @param String �����
	 * @return String 1���ɹ� "" ʧ��
	 */			

	public String addTopicLog(String topid,String act_ip,String userid) {
		
		String s_return="",s_now;
		String[][] temp = null;
		StringBuffer sb_select = null,sb_sql = null;
		int i_seq;
		
		
		try{		
			
			//��ǰʱ��
			CommDate cd = new CommDate();
			s_now = cd.getNow(cd.FORMAT_DATETIME_SIGN);
			//��ѯһ��ͨ����־�����Ƿ�����һTOPID��ACT_IP����TOPFLAGΪ1�ļ�¼
			sb_select =  new StringBuffer("");
			sb_select.append("SELECT SEQ FROM TOPICLOG WHERE ");
			sb_select.append(" TOPID='");
			sb_select.append(topid);
			sb_select.append("' AND ACT_IP='");
			sb_select.append(act_ip);
			sb_select.append("' AND TOPFLAG='1'");
			log.debug("��ѯͨ����ʷ��" + sb_select.toString());
			temp = cs.executeSelect(sb_select.toString());
			if (temp==null){
				//�޴˼�¼��δ������
				sb_select =  new StringBuffer("");
				sb_select.append("SELECT SEQ FROM TOPICLOG WHERE ");
				sb_select.append(" TOPID='");
				sb_select.append(topid);
				sb_select.append("' AND ACT_IP='");
				sb_select.append(act_ip);
				sb_select.append("' ORDER BY SEQ DESC");
				log.debug("��ѯͨ����ʷ��SEQ��" + sb_select.toString());
				//��ѯһ��ͨ����־�����Ƿ�����һTOPID��ACT_IP����SEQ DESC����
				temp = cs.executeSelect(sb_select.toString());
				if (temp == null){
					//�޼�¼
					i_seq = 1;
				} else {
					//�м�¼
					i_seq = Integer.parseInt(temp[0][0]) + 1;
				}
				//���µ�һ����ʷINSERT �������ݿ�
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
				log.debug("��ͨ����ʷ��INSERT��" + sb_sql.toString());
				cs.executeBatchSQL(sb_sql.toString());
			} else {
				//�д������ļ�¼���Ѿ����������ı�USERID���û�������ACT_DATETIME��ʱ�䣩
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
				log.debug("��ͨ����ʷ�����һ�θ��£�" + sb_sql.toString());
				cs.executeBatchSQL(sb_sql.toString());
			}
			s_return = "1";
		} catch (Exception e){
			e.printStackTrace();
		}
		return s_return;
	}	
}
