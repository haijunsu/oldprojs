/*
 * @(#)FunTax.java  2003-06-20
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package  system.fun;
/**
 * <P>������˰����</P>
 * 
 * @version 1.0
 * @author LYC
 */

import com.idn.sql.*;
public class FunTax  {
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(FunTax.class);
	private double d_taxrate[][]=null;        //���ʼ�˰����
	private double d_taxbase = 0.00;          //���ʼ�˰�۳�
	
	private double d_taxrateLW[][]=null;      //����Ѽ�˰����
	private double d_taxbaseLW[][]=null;      //����ѻ�������
	private double d_taxbasic;                //����ѽ��

	public FunTax() {
		super();
	}
	/**
	 * ��������Ѽ�˰�Ĳ���˰���ֵĽ��ֶε㡢��˰�ٷֱȡ����
	 * 
	 * @return int �Ƿ�����ݿ���в�����;0��δ�����ݽ��в���1�������ݿ���в����� -1�������쳣
	 * @exception Exception
	 */
	public int setTaxLWRateBase(){

		String s_date,s_temp,s_cs1,s_cs2,s_sql;
		java.util.Calendar ca;
		int i_return=0;
		DataBean db=new DataBean();
		DataBean dd=new DataBean();
		
		s_cs1="703";
		s_cs2="704";

		if (d_taxrate!=null) {
			if (d_taxrateLW.length>0) {
				i_return = 0;
				return i_return;
			}
		}
		log.info("������˰����������Ѽ�˰�趨��˰����");
		try{
			//��ǰ����
			ca=java.util.Calendar.getInstance();
			//��
			s_date=String.valueOf(ca.get(1));
			//��
			s_temp=String.valueOf(ca.get(2)+1);
			if (s_temp.length()==1)
			  s_temp="0"+s_temp;
			s_date=s_date+s_temp;
			//��
			s_temp=String.valueOf(ca.get(5));
			if (s_temp.length()==1)
			  s_temp="0"+s_temp;
			s_date=s_date+s_temp;
			
			d_taxbasic=0;
			
	//		ȡֵ������
			s_sql= "select saladiv,SALAVAL1 from salarypa where  salapar='"+s_cs1+"' and saladate=(select max(saladate) from salarypa where salapar='"+s_cs1+"' and saladate <='"+s_date+"') order by double(saladiv)";
			db.executeSelect(s_sql);
			
			d_taxbaseLW=new double[db.getRowCount()+1][db.getColumnCount()];
			for(int i=0;i<db.getRowCount();i++)
			{
				for(int j=0;j<db.getColumnCount();j++)
				{
					d_taxbaseLW[i+1][j]=Double.parseDouble(db.getElementValue(i,j));	
				}
			} 
			db.destory();	
			
	//		������ֵ	
			s_sql="select saladiv,salaval1,salaval2 from salarypa where  salapar='"+s_cs2+"' and saladate=(select max(saladate) from salarypa where salapar='"+s_cs2+"' and saladate <='"+s_date+"') order by double(saladiv) desc"; 
			dd.executeSelect(s_sql);
			
			d_taxrateLW=new double[dd.getRowCount()+1][dd.getColumnCount()];
			for(int i=0;i<dd.getRowCount();i++)
			{
				for(int j=0;j<dd.getColumnCount();j++)
				{
					d_taxrateLW[i+1][j]=Double.parseDouble(dd.getElementValue(i,j));
				}
			} 
			dd.destory();		
			i_return = 1;
		}catch(Exception e){
			log.error("������˰����������Ѽ�˰�趨��˰���������쳣����");
			e.printStackTrace();
			i_return = -1;			
		}
		return i_return;
	}

	/**
	 * �Ը���Ľ��(�����)������������˰
	 * @param double ˰ǰ���
	 * @return double ��������˰
	 * @exception Exception
	 */
	public double computeTaxLWOne(double d_salary)
	{
		double d_old,d_tax;
		int i_mark=0;
		d_old=0;
		d_tax=0;
		log.info("������˰����������Ѽ�˰");
		setTaxLWRateBase();
		try{
			for (int i=1 ; i<d_taxbaseLW.length;i++){
				if(d_salary<=d_taxbaseLW[i][0]){
					if (d_taxbaseLW[i][1]<1)
						d_taxbasic=d_salary*d_taxbaseLW[i][1];
					else
						d_taxbasic=d_taxbaseLW[i][1];
					break;
				}
			}
			d_tax=d_salary - d_taxbasic;
			if (d_tax<=0)
			{
				d_tax=0;
				return d_tax;
			}
			for(i_mark=1;i_mark<d_taxrateLW.length;i_mark++)
			{
				if( d_tax > d_taxrateLW[i_mark][0])
				break;
			}
			i_mark--;
			d_tax = d_tax * d_taxrateLW[i_mark][1] - d_taxrateLW[i_mark][2];
			d_tax =java.lang.Math.rint(d_tax *100);
			d_tax =d_tax /100;
			return d_tax;
		}catch (Exception e){
			log.error("������˰����������Ѽ�˰�쳣ʧ��");
			e.printStackTrace();
			return 0.00;
		}
	}

	/**
	 * �����˰�Ĳ���˰���ֵĽ��ֶε㡢��˰�ٷֱȡ����
	 * 
	 * @return int �Ƿ�����ݿ���в�����;0��δ�����ݽ��в���1�������ݿ���в����� -1�������쳣
	 * @exception Exception
	 */
	public int setTaxRateBase(){
		int i_return = 0;
		String s_date,s_temp,s_cs1,s_cs2,s_sql;
		java.util.Calendar ca;
		try {
			if (d_taxrate!=null) {
				if (d_taxrate.length>0) {
					i_return = 0;
					return i_return;
				}
			}
			DataBean db=new DataBean();
			DataBean dd=new DataBean();
			//701�����ʼ�˰�۳����Ĳ�����702�����ʼ�˰��˰�����Ĳ�����
			s_cs1="701";
			s_cs2="702";
			
			ca=java.util.Calendar.getInstance();  //��ǰ����
			s_date=String.valueOf(ca.get(1));  //��
			s_temp=String.valueOf(ca.get(2)+1);  //��
			if (s_temp.length()==1){
				s_temp="0"+s_temp;
			}
			s_date=s_date+s_temp;
			s_temp=String.valueOf(ca.get(5));			//��
			if (s_temp.length()==1){
				s_temp="0"+s_temp;
			}
			s_date=s_date+s_temp;
			//ȡֵ������
			s_sql="select SALAVAL1 from salarypa where saladiv='1' and salapar='"+s_cs1+"' and saladate=(select max(saladate) from salarypa where saladiv='1' and salapar='"+s_cs1+"' and saladate <='"+s_date+"')";
			db.executeSelect(s_sql);
			d_taxbase=Double.parseDouble(db.getElementValue(0,0));
			//������ֵ
			s_temp="";
			s_sql="select max(saladate) from salarypa where salapar='"+s_cs2+"' and saladate <='"+s_date+"'";
			db.executeSelect(s_sql);
			s_temp=db.getElementValue(0,0).trim();
			
			db.destory();
			s_sql="select saladiv,SALAVAL1,SALAVAL2 from salarypa where salapar='"+s_cs2+"' and saladate='"+s_temp+"'order by double(saladiv)desc";
			dd.executeSelect(s_sql);
			d_taxrate=new double[dd.getRowCount()+1][dd.getColumnCount()];
			for(int i=0;i<dd.getRowCount();i++)
			{
				for(int j=0;j<dd.getColumnCount();j++)
				{
					d_taxrate[i+1][j]=Double.parseDouble(dd.getElementValue(i,j));
//					System.out.print(d_taxrate[i+1][j]+" , ");
				}
//				System.out.println();
			} 
			dd.destory();
			i_return = 1;
		} catch (Exception e){
			i_return = -1;
			e.printStackTrace();
		}
		return i_return;
	}

	/**
	 * �Ը���Ľ�������������˰
	 * 
	 * @param double ˰ǰ���
	 * @return double ��������˰
	 * @exception Exception
	 */
	public double computeTaxOne(double d_salary){
		return computeTaxOne(d_salary,"0");
	}
	/**
	 * �Ը���Ľ�������������˰
	 * 
	 * @param double ˰ǰ���
	 * @param String ������-1000�Ĳ���
	 *        "0"     ������
	 *        "1"     ����-1000
	 * @return double ��������˰
	 * @exception Exception
	 */
	public double computeTaxOne(double d_salary,String s_flag)
	{
		double d_old,d_tax;
		int i_mark=0;
		d_old=0;
		d_tax=0;
		try{
			if (setTaxRateBase()==-1) return 0.00;
			if (s_flag.equals("0")){
				d_tax=d_salary - d_taxbase;
			} else {
				d_tax=d_salary;
			}
			if (d_tax<=0){
				d_tax=0;
				return d_tax;
			}
			for(i_mark=1;i_mark<11;i_mark++){
				if( d_tax >= d_taxrate[i_mark][0])
				break;
			}
			i_mark--;
			d_tax = d_tax * d_taxrate[i_mark][1] - d_taxrate[i_mark][2];
			d_tax =java.lang.Math.rint(d_tax *100);
			d_tax =d_tax /100;
		} catch (Exception e){
			e.printStackTrace();
			d_tax = 0.00;	
		}
		return d_tax;
	}
	/**
	 * �Ը���Ľ���˰����,����������˰ǰ���
	 * 
	 * @param double ˰����
	 * @return double ˰ǰ���
	 * @exception Exception
	 */
	public double inComputeTaxOne(double d_salary)
	{
		double d_return=0;
		double d_salanow=0;
		double dnow=0;
		int i_mark=1;
		try {
			if (setTaxRateBase()==-1) return 0.00;
			d_salanow=(d_salary - d_taxbase);
			if ( d_salanow <= 0 ) {
				return d_salary;
			}
			//�ҵ�
			for (;i_mark<11;i_mark++) {
				if (d_salanow >= d_taxrate[i_mark][0])
				break;
			}
			i_mark++;
			//ѭ�����㷵��ֵ
			do {
				i_mark--;
				d_return=(d_salanow - d_taxrate[i_mark][2])/(1 - d_taxrate[i_mark][1]);
			}  while (d_return>=d_taxrate[i_mark][0]);
			d_return = d_return+d_taxbase;
			d_return =java.lang.Math.rint(d_return*100);
			d_return=d_return/100;
			
		} catch (Exception e){
			e.printStackTrace();
			d_return = 0.00;
		}
		return d_return;
	}



	/**
	 * �Ը���Ľ�������������˰
	 * 
	 * @param double ˰ǰ���
	 * @param boolean ������-�����Ĳ���
	 *        "true"     ������
	 *        "false"     ����
	 * @param double  ��˰��
	 * @return double ��������˰
	 * @exception Exception
	 */
	public double computeTaxOne(double d_salary,boolean flag,double rate){
		long l_tax;
		double d_old,d_tax;
		int i_mark=0;
		d_old=0;
		d_tax=0; 
		if (setTaxRateBase()==-1) return 0.00;
		d_tax=d_salary;
		if(flag){d_tax=d_salary - d_taxbase;}
		if (d_tax<=0){return 0.00;}
		for(i_mark=1;i_mark<11;i_mark++){
			if( d_tax >= d_taxrate[i_mark][0]){break;}
		}
		i_mark--;
		d_tax = d_tax * d_taxrate[i_mark][1];
		d_tax =d_tax - d_taxrate[i_mark][2];
		d_tax=d_tax * rate;
		d_tax = rint(d_tax) ;
		
		return d_tax;
	}
	
	/**
	 * �Ը���Ľ���˰����,����������˰ǰ���
	 * 
	 * @param double ˰����
	 * @param boolean ������-�������Ĳ���
	 *        "true"     ������
	 *        "false"     ����
	 * @param double  ��˰��
	 * @return double ˰ǰ���
	 * @exception Exception
	 */
	public double inComputeTaxOne(double d_salary,boolean flag,double rate){
		double d_return=0;
		double d_salanow=0;
		double dnow=0;
		int i_mark=1;
	  
		setTaxRateBase();
		
		d_salanow=d_salary;
		if(flag){d_salanow=d_salary - d_taxbase;}
		if ( d_salanow <= 0 ){return d_salary;}
		
		//�ҵ�
		for (;i_mark<11;i_mark++){
			if (d_salanow >= d_taxrate[i_mark][0]){break;}
		}
		
		i_mark++;
		//ѭ�����㷵��ֵ
		do{
			i_mark--;
			d_return=(d_salanow - rate * d_taxrate[i_mark][2])/(1 - rate * d_taxrate[i_mark][1]);
			System.out.println();
		}while (d_return>=d_taxrate[i_mark][0]);
		d_return=rint(d_return);
		if(flag){d_return = d_return+d_taxbase;}
		return d_return;
	}
	
	public double rint(double d_tax){
		long l_tax;
			
		d_tax = d_tax *100;
		d_tax = d_tax + 0.51;
		l_tax = (long) d_tax;
		d_tax =(double)l_tax /100;
		return d_tax;
	}

}

