/*
 * @(#)FunTax.java  2003-06-20
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package  system.fun;
/**
 * <P>单条计税函数</P>
 * 
 * @version 1.0
 * @author LYC
 */

import com.idn.sql.*;
public class FunTax  {
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(FunTax.class);
	private double d_taxrate[][]=null;        //工资计税比例
	private double d_taxbase = 0.00;          //工资计税扣除
	
	private double d_taxrateLW[][]=null;      //劳务费计税比例
	private double d_taxbaseLW[][]=null;      //劳务费基础比例
	private double d_taxbasic;                //劳务费结果

	public FunTax() {
		super();
	}
	/**
	 * 处理劳务费计税的不计税部分的金额、分段点、上税百分比、差额
	 * 
	 * @return int 是否对数据库进行操作了;0、未对数据进行操作1、对数据库进行操作了 -1、出现异常
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
		log.info("单条计税函数：劳务费计税设定计税比例");
		try{
			//当前日期
			ca=java.util.Calendar.getInstance();
			//年
			s_date=String.valueOf(ca.get(1));
			//月
			s_temp=String.valueOf(ca.get(2)+1);
			if (s_temp.length()==1)
			  s_temp="0"+s_temp;
			s_date=s_date+s_temp;
			//日
			s_temp=String.valueOf(ca.get(5));
			if (s_temp.length()==1)
			  s_temp="0"+s_temp;
			s_date=s_date+s_temp;
			
			d_taxbasic=0;
			
	//		取值起征点
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
			
	//		基础赋值	
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
			log.error("单条计税函数：劳务费计税设定计税比例出现异常错误");
			e.printStackTrace();
			i_return = -1;			
		}
		return i_return;
	}

	/**
	 * 对给入的金额(劳务费)计算它的所得税
	 * @param double 税前金额
	 * @return double 个人所得税
	 * @exception Exception
	 */
	public double computeTaxLWOne(double d_salary)
	{
		double d_old,d_tax;
		int i_mark=0;
		d_old=0;
		d_tax=0;
		log.info("单条计税函数：劳务费计税");
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
			log.error("单条计税函数：劳务费计税异常失败");
			e.printStackTrace();
			return 0.00;
		}
	}

	/**
	 * 处理计税的不计税部分的金额、分段点、上税百分比、差额
	 * 
	 * @return int 是否对数据库进行操作了;0、未对数据进行操作1、对数据库进行操作了 -1、出现异常
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
			//701、工资计税扣除金额的参数；702：工资计税纳税比例的参数；
			s_cs1="701";
			s_cs2="702";
			
			ca=java.util.Calendar.getInstance();  //当前日期
			s_date=String.valueOf(ca.get(1));  //年
			s_temp=String.valueOf(ca.get(2)+1);  //月
			if (s_temp.length()==1){
				s_temp="0"+s_temp;
			}
			s_date=s_date+s_temp;
			s_temp=String.valueOf(ca.get(5));			//日
			if (s_temp.length()==1){
				s_temp="0"+s_temp;
			}
			s_date=s_date+s_temp;
			//取值起征点
			s_sql="select SALAVAL1 from salarypa where saladiv='1' and salapar='"+s_cs1+"' and saladate=(select max(saladate) from salarypa where saladiv='1' and salapar='"+s_cs1+"' and saladate <='"+s_date+"')";
			db.executeSelect(s_sql);
			d_taxbase=Double.parseDouble(db.getElementValue(0,0));
			//基础赋值
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
	 * 对给入的金额计算它的所得税
	 * 
	 * @param double 税前金额
	 * @return double 个人所得税
	 * @exception Exception
	 */
	public double computeTaxOne(double d_salary){
		return computeTaxOne(d_salary,"0");
	}
	/**
	 * 对给入的金额计算它的所得税
	 * 
	 * @param double 税前金额
	 * @param String 作不作-1000的操作
	 *        "0"     正常作
	 *        "1"     不作-1000
	 * @return double 个人所得税
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
	 * 对给入的金额当作税后金额,来反查它的税前金额
	 * 
	 * @param double 税后金额
	 * @return double 税前金额
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
			//找点
			for (;i_mark<11;i_mark++) {
				if (d_salanow >= d_taxrate[i_mark][0])
				break;
			}
			i_mark++;
			//循环计算返回值
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
	 * 对给入的金额计算它的所得税
	 * 
	 * @param double 税前金额
	 * @param boolean 作不作-基数的操作
	 *        "true"     正常作
	 *        "false"     不作
	 * @param double  上税率
	 * @return double 个人所得税
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
	 * 对给入的金额当作税后金额,来反查它的税前金额
	 * 
	 * @param double 税后金额
	 * @param boolean 作不作-减基数的操作
	 *        "true"     正常作
	 *        "false"     不作
	 * @param double  上税率
	 * @return double 税前金额
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
		
		//找点
		for (;i_mark<11;i_mark++){
			if (d_salanow >= d_taxrate[i_mark][0]){break;}
		}
		
		i_mark++;
		//循环计算返回值
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

