/*
 * @(#)CommSQL.java  2003-7-25
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch;
/**
 * <P>SQL���ͨ���� </P>
 * 
 * @author xiaoai
 * @version 1.0
 */
import java.sql.BatchUpdateException;
import java.sql.SQLException;
import com.idn.sql.*;
public class CommSQL  {

	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommSQL.class);

	private String[][] rsdata=null;
	private String[] rscolname = null;
	private String[] rscoltype = null;
	
	
	private String model = "";
	private String as = "";
	
	private int rowcount;
	private int colcount;
	
	private int maxcount;
	
	public CommSQL() {
		super();
		//setModel("SOFT_IDN.");
		setModel("");
		setAs(" AS ");
	}	
	
	
	/**
	 * <P>ȡָ���У��е�����<P>
	 * @param int ��
	 * @param int ��
	 * @return String  ���
	 */	
	public String getFieldsValue(int i_row,int i_col){
		String s_return;
		if (getRowcount() == 0) {
			//�������������
			s_return="";
			return s_return;
		}
		if (i_row < 0 || i_row>=getRowcount()){
			//�޴���
			s_return="";
			return s_return;
		}
		if (i_col < 0 || i_col>=getColcount()){
			//�޴���
			s_return="";
			return s_return;
		}
		s_return = "";
		s_return = this.getRsdata()[i_row][i_col].trim();
		return s_return;
	}
	/**
	 * ȡָ���У��е�����
	 * @param int ָ������
	 * @param String ָ������
	 * @return String ���
	 */	
	public String getFieldsValue(int i_row,String s_col){
		int i_col;
		String s_return;
		s_return = "";
		if (getRowcount() == 0) {
			//�������������
			s_return="";
			return s_return;
		}
		if (i_row < 0 || i_row>=getRowcount()){
			//�޴���
			s_return="";
			return s_return;
		}
		s_col = s_col.trim();

		try {
			for (i_col=0;i_col<getColcount();i_col++){
				if (this.getRscolname()[i_col].trim().equals(s_col)){
					break;
				}
			}
			if (i_col < getColcount()){
				s_return =getFieldsValue(i_row,i_col);
			} else{
				s_return = "";
			}
			
		} catch (Exception e){
			log.error("CommSQL.SQL TongYongLie : Get Column is wrong!");
			e.printStackTrace();
		}
		return s_return.trim();
	}	


	/**
	 * <P>Ԥ����SQL���<P>
	 * @param String SQL���
	 * @return int 1���ɹ���-1�����ɹ�
	 */	
	public int executeSQL(String sqlBt,String[] parm){
		DynaSqlBean dsb = null;
		try{
			dsb = new DynaSqlBean();
			sqlBt = addModelSQL(sqlBt.trim());
			//TODO  ����EXECUTE SQL   
			dsb.setSql(sqlBt);
			dsb.setParam(parm);
			dsb.execute();
			dsb = null;
			
		} catch (Exception e){
			e.printStackTrace();
			log.error("CommSQL.executeSQL: prepare SQL is wrong");
			return -1;
		}
		return 1;
	}
	/**
	 * <P>Ԥ����SQL���<P>
	 * @param String SQL���
	 * @return int 1���ɹ���-1�����ɹ�
	 */	
	public int executeSQL(String sqlBt,String parm){
		DynaSqlBean dsb = null;
		try{
			dsb = new DynaSqlBean();
			sqlBt = addModelSQL(sqlBt.trim());
			//TODO  ����EXECUTE SQL
			dsb.setSql(sqlBt);
			dsb.setParam(parm);
			dsb.execute();
			dsb = null;
			
		} catch (Exception e){
			e.printStackTrace();
			log.error("CommSearch.executeSQL: prepare SQL is wrong");
			return -1;
		}
		return 1;
	}
	/**
	 * <P>������SQL���<P>
	 * @param String SQL���
	 * @return int 1���ɹ���-1�����ɹ�
	 */	
	public int executeBatchSQL(String sqlBt){
		String[] s_temp = new String[1];
		s_temp[0] = sqlBt;
		return executeBatchSQL(s_temp);
	}
	/**
	 * <P>��ִ�е�SQL������ģʽ��<P>
	 * @param String SQL���
	 * @return String ����ģʽ����SQL���
	 */	
	public String addModelSQL(String s_sql){
		String s_return="";
		String s_temp = "";
		String[] s_all = null;
		String[] s_one = null;
		commsearch.util.CommString cstr = new commsearch.util.CommString();
		if (getModel().equals("")) return s_sql;
		
		s_all = cstr.DivString(s_sql," ");
		for (int i_fori=0;i_fori<s_all.length;i_fori++) {
			if (s_all[i_fori].trim().toUpperCase().equals("FROM") || s_all[i_fori].trim().toUpperCase().equals("UPDATE") || s_all[i_fori].trim().toUpperCase().equals("INTO")){
				if (!cstr.Left(s_all[i_fori + 1],1).equals("(")){
					//FROM���治��ǰ���ŵĲ�Ҫ����
					s_temp = "";
					s_one = cstr.DivString(s_all[i_fori + 1],",");
					for (int i_forj=0;i_forj<s_one.length;i_forj++){
						s_temp = s_temp + getModel() + s_one[i_forj] + ",";
					}
					s_temp = cstr.Left(s_temp,s_temp.length() - 1);
					s_all[i_fori + 1] = s_temp;
				}
				 
			}
			s_return = s_return + " " + s_all[i_fori];
		}
		return s_return;
	}
	
	/**
	 * <P>��SELECT������ģʽ��<P>
	 * @param String SELECT���
	 * @return String ����ģʽ����SELECT���
	 */			
	public String addModel(String s_sql){
		String s_return="";
		String s_temp = "";
		String[] s_all = null;
		String[] s_one = null;
		commsearch.util.CommString cstr = new commsearch.util.CommString();
		if (getModel().equals("")) return s_sql;
		
		s_all = cstr.DivString(s_sql," ");
		for (int i_fori=0;i_fori<s_all.length;i_fori++) {
			if (s_all[i_fori].trim().toUpperCase().equals("FROM") ){
				if (!cstr.Left(s_all[i_fori + 1],1).equals("(")){
					//FROM���治��ǰ���ŵĲ�Ҫ����
					s_temp = "";
					s_one = cstr.DivString(s_all[i_fori + 1],",");
					for (int i_forj=0;i_forj<s_one.length;i_forj++){
						s_temp = s_temp + getModel() + s_one[i_forj] + getAs() + s_one[i_forj] + ",";
					}
					s_temp = cstr.Left(s_temp,s_temp.length() - 1);
					s_all[i_fori + 1] = s_temp;
				}
				 
			}
			s_return = s_return + " " + s_all[i_fori];
		}
		return s_return;
	}
	/**
	 * <P>������SQL���<P>
	 * @param String[] SQL���
	 * @return int 1���ɹ���-1�����ɹ�
	 */	
	public int executeBatchSQL(String[] sqlBt){
		int i_count,i_fori;
		int retuN=-1;
		String[] s_temp=null;
		DynaSqlBean dsb = null;
		dsb = new DynaSqlBean();
		try
		{
			//��������
			i_count = 0;
			for (i_fori=0;i_fori<sqlBt.length;i_fori++){
				if (!sqlBt[i_fori].trim().equals("")) {
					i_count ++;
					sqlBt[i_fori] = addModelSQL(sqlBt[i_fori].trim());
				}
			}
			s_temp = new String[i_count];
			i_count = 0;
			if (i_count!=sqlBt.length){
				for (i_fori=0;i_fori<sqlBt.length;i_fori++){
					if (!sqlBt[i_fori].trim().equals("")) {
						s_temp[i_count] = sqlBt[i_fori];
						i_count ++;
					}
				}	
			} else {
				s_temp = sqlBt;
			}
			dsb.setSql(s_temp);
			log.debug("CommSQL.executeBatchSQL:exceute all SQL count: "+s_temp.length);
			//TODO ����EXECUTE BATCH
			dsb.executeBatch();
			//if (dsb.isExecuteArrayBatchSuccess()){
			//if (dsb.executeBatch()){
				retuN=1;
				log.debug("CommSQL.executeBatchSQL:succeed");
			//}
		    
		}catch (BatchUpdateException e){
			log.error("CommSQL.executeBatchSQL: BatchUpdateException lost"+e.getMessage());
		}catch (SQLException e){
			log.error("CommSQL.executeBatchSQL: SQLException lost"+e.getMessage());
		}
		catch (Exception e){
			log.error("CommSQL.executeBatchSQL: Exception lost"+e.getMessage());
		}
		dsb = null;
		return retuN;
		}
		
	/**
	 * <P>��SELECT���ȡ�����������<P>
	 * @param String SELECT���
	 * @return String[][] double�͵��ַ���
	 */	
	public  String[][] executeSelect(String s_sqlstr){
		String[] s_temp=null;
		//����ģʽ
		//s_sqlstr = addModel(s_sqlstr);
		return executeSelect(s_sqlstr,s_temp);
	}
	/**
	 * <P>��SELECT���ȡ�����������<P>
	 * @param String SELECT���
	 * @param String SELECT���Ĳ���
	 * @return String[][] double�͵��ַ���
	 */	
	public  String[][] executeSelect(String s_sqlstr,String s_param){
		String[] s_temp=new String[1];
		s_temp[0]=s_param;
		//����ģʽ
		//s_sqlstr = addModel(s_sqlstr);
		return executeSelect(s_sqlstr,s_temp);
	}
	/**
	 * <P>��SELECT���ȡ�����������<P>
	 * @param String SELECT���
	 * @param String[] SELECT���Ĳ���
	 * @return String[][] double�͵��ַ���
	 */	
	public  String[][] executeSelect(String s_sqlstr,String[] s_param){
	 	
		int i_rowcount,i_colcount,i_fori,i_forj;
		String s_temp;
		String[][] s_data = null;
		String[] s_rscolname = null;
		String[] s_rscoltype = null;
		

		long l_begin,l_end;
		l_begin = System.currentTimeMillis();		
		
		
		try{
			DataBean db = null;
			DynaSqlBean dsb = null;
			db = new DataBean();
			dsb = new DynaSqlBean();
			s_sqlstr = addModel(s_sqlstr);
			//���������
			dsb.setSql(s_sqlstr);
			if (!(s_param==null))	dsb.setParam(s_param);
			db.setCrs(dsb.executeQuery());
			i_rowcount = db.getRowCount();
			if (i_rowcount>0){

				i_colcount = db.getColumnCount(); 
				setRowcount(i_rowcount);
				setColcount(i_colcount);
				//�����
				s_data = new String[i_rowcount][i_colcount];
				for (i_fori=0;i_fori<i_rowcount;i_fori++){
					for (i_forj=0;i_forj<i_colcount;i_forj++){
						//s_temp = db.getFieldValue(i_forj,i_fori);						
						s_temp = db.getElementValue(i_fori,i_forj);
						if (s_temp==null) {
							s_temp = "";
						} else {
							s_temp = s_temp.trim();
						}
						s_data[i_fori][i_forj]= s_temp;
					}
				}
				//������������
				s_rscolname = new String[i_colcount];
				s_rscoltype = new String[i_colcount];
				for (i_forj=0;i_forj<i_colcount;i_forj++){
					s_rscolname[i_forj] = db.getColumnName(i_forj);
					s_rscoltype[i_forj] = db.getColumnTypeName(i_forj);
				}
				setRsdata(s_data);
				setRscolname(s_rscolname);
				setRscoltype(s_rscoltype);
				//db.destory();
				db = null;
				dsb = null;

				l_end = System.currentTimeMillis();
				log.info("XIAOAI :SELECT count:"+i_rowcount + " records��times(millisecond):" + Long.toString((l_end - l_begin)));
			} else {
				s_data=null;
				if (db!=null) {
					//db.destory();
					db = null;} 
				if (db!=null) dsb = null;
				setRowcount(0);
				setColcount(0);
				l_end = System.currentTimeMillis();
				log.info("XIAOAI :SELECT count:0;times(millisecond):" + Long.toString((l_end - l_begin)));
			}
		} 
		catch (SQLException e1){
			s_data = null;
			setRowcount(0);
			setColcount(0);
			//exceute sql
			if (e1.getErrorCode()== -9001){ 
				log.error("XIAOAI : SelectSQLException TIMED OUT :" + s_sqlstr);
				log.error("XIAOAI : SelectSQLException TIMED OUT :" + e1.getMessage());
			} else {
				log.error("XIAOAI : SelectSQLException :" + s_sqlstr);
				log.error("XIAOAI : SelectSQLException :" + e1.getMessage());
			}
		}
		catch (Exception e1){
			s_data = null;
			setRowcount(0);
			setColcount(0);
			//exceute sql
			log.error("XIAOAI : SelectException :" + s_sqlstr);
			log.error("XIAOAI : SelectException :" + e1.toString());
		}

		return s_data;
	}	  
	/**
	 * @return
	 */
	public int getColcount() {
		return this.colcount;
	}

	/**
	 * @return
	 */
	public int getRowcount() {
		return this.rowcount;
	}

	/**
	 * @param i
	 */
	public void setColcount(int i) {
		this.colcount = i;
	}

	/**
	 * @param i
	 */
	public void setRowcount(int i) {
		this.rowcount = i;
	}


	/**
	 * @return
	 */
	public String[] getRscolname() {
		return this.rscolname;
	}

	/**
	 * @return
	 */
	public String[] getRscoltype() {
		return this.rscoltype;
	}

	/**
	 * @param strings
	 */
	public void setRscolname(String[] strings) {
		this.rscolname = strings;
	}

	/**
	 * @param strings
	 */
	public void setRscoltype(String[] strings) {
		this.rscoltype = strings;
	}

	/**
	 * @return
	 */
	public String[][] getRsdata() {
		return this.rsdata;
	}

	/**
	 * @param strings
	 */
	public void setRsdata(String[][] strings) {
		this.rsdata = strings;
	}

	/**
	 * @return
	 */
	public String getModel() {
		return this.model;
	}

	/**
	 * @param string
	 */
	public void setModel(String string) {
		this.model = string;
	}

	/**
	 * @return
	 */
	public String getAs() {
		return this.as;
	}

	/**
	 * @param string
	 */
	public void setAs(String string) {
		this.as = string;
	}

	/**
	 * @return ���� maxcount��
	 */
	public int getMaxcount() {
		return this.maxcount;
	}
	/**
	 * @param maxcount Ҫ���õ� maxcount��
	 */
	public void setMaxcount(int i_maxcount) {
		this.maxcount = i_maxcount;
	}
}
