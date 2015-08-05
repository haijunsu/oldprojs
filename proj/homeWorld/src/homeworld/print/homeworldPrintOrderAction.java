/*
 * @(#)homewordOrderAction.java  2004-1-8
 *maxpages selected offset
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.print;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.idn.secure.SecureAction;
import com.idn.sql.DataBean;
import com.idn.util.DecimalTools;
import com.idn.util.CommonTools;
import commsearch.util.CommActionLog;


/**
 * <P>菜单管理</P>
 * 
 * @version 0.1
 * @author 李永初
 */
public class homeworldPrintOrderAction extends SecureAction {
	//private static LogWrapper log= new LogWrapper(SalaryCardCtrl.class);
	private int intTemp = 0;
	private String strSql;
	private String[] strTemp = null;
	ActionErrors errors = new ActionErrors();

	/**
	 * 构造函数
	 */
	public homeworldPrintOrderAction() {
		super();
	}

	/** (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeme(ActionMapping mapping,ActionForm form,
		HttpServletRequest request,	HttpServletResponse response)throws IOException, ServletException {

			errors.clear();
			errors.add("header",new ActionError("errors.header"));
			errors.add("footer",new ActionError("errors.footer"));	
		try{
			HttpSession hs = request.getSession();
			String uname=(String)hs.getAttribute("userid");
			
			if(uname==null){
				errors.add("errormessage",new ActionError("NoName"));
				saveErrors(request, errors);
				return(mapping.findForward("success"));
			}
			if(uname.trim().equals("")){
				errors.add("errormessage",new ActionError("NoName"));
				saveErrors(request, errors);
				return(mapping.findForward("success"));
			}
			
			
			
			Vector vecTemp=new Vector();
			//page--> 0：录入 1：更改
			String page=((String) PropertyUtils.getSimpleProperty(form, "page")).trim();
//            String temp="EPOSEQ='1'AND EPONUM='1586343-00'";
			String temp = (String)request.getParameter("selectwhere");
			temp=CommonTools.stringReplace(temp,"&#39;","'");
			if(temp!=null){
			       PropertyUtils.setSimpleProperty(form, "selectwhere" ,temp );
			}else{  
				   temp=((String)PropertyUtils.getSimpleProperty(form,"selectwhere"));
			}
            if (page.equals("")){
				if (temp==null){
					page = "0";            
				}else{
					if(temp.trim().length()==1){
						return(mapping.findForward("select"));
					}
					page = "1";
					temp=temp.trim();
					if(temp.equals("")){page="0";}
				}
				PropertyUtils.setSimpleProperty(form,"page",page);
			}
			
			String flag=((String) PropertyUtils.getSimpleProperty(form, "flag")).trim();


			//不保存
			if(flag.equals("") || flag.equals("lrexic") ){
				if (page.equals("0")){
					resetFormbeen(form,request);
					return(mapping.findForward("success"));
					}
				if(setFormbeen(form,request,temp,uname).equals("0")){
					saveErrors(request, errors);
					return(mapping.findForward("success"));
				}
				
//				hs.setAttribute(selectwhere,temp);
				return(mapping.findForward("success"));
			}
			//为修改时
			if(flag.equals("xgexic")){
				return(mapping.findForward("select"));
			}
			
		  //录入
		  if(flag.equals("lr")){
			  resetFormbeen(form,request);
			  return(mapping.findForward("success"));
		  }
		  //修改
		  if(flag.equals("xg")){
			  return(mapping.findForward("select"));
		  }		
			

		  //为修改时无条件返回
		  if (page.equals("1")){return(mapping.findForward("success"));}
		  
			
			
			
			
			resetFormbeen(form,request);
			return(mapping.findForward("success"));
		} catch (Exception e){
			errors.add("errormessage",new ActionError("OrderIn.formbean"));
			saveErrors(request, errors);
			return(mapping.findForward("success"));
		}
	}


	/**
	 * 将指定类代码内容传入显示FormBeen(小表格)
	 * 
	 * @param ActionForm 显示FormBeen
	 * @param String 工资卡号
	 * @return String 1、成功；0、不成功；
	 * @exception Exception
	 */
	public String setFormbeen(ActionForm form,HttpServletRequest request,String where,String uname) throws Exception{
				DataBean dbBean = new DataBean();
				
				String strsql="select * from epohdr where "+where+" ";   
				try{
					dbBean.executeSelect(strsql);
				} catch (Exception e) {
					errors.add("errormessage",new ActionError("OrderIn.readdb"));
					return "0";
				}
								
		String epocdt  =dbBean.getElementValue(0,"epocdt"  );
		String epousr  =dbBean.getElementValue(0,"epousr"  );
		String eponum  =dbBean.getElementValue(0,"eponum"  );
		String epoctm  =dbBean.getElementValue(0,"epoctm"  );
		String epostrz =dbBean.getElementValue(0,"epostr"  );
		String epostn  =dbBean.getElementValue(0,"epostn"  );
		String epovndz =dbBean.getElementValue(0,"epovnd"  );
		String eposa1  =dbBean.getElementValue(0,"eposa1"  );
		String eposa2  =dbBean.getElementValue(0,"eposa2"  );
		String eposa3  =dbBean.getElementValue(0,"eposa3"  );
		String eposcy  =dbBean.getElementValue(0,"eposcy"  );
		String eposzp  =dbBean.getElementValue(0,"eposzp"  );
		String eposcn  =dbBean.getElementValue(0,"eposcn"  );
		String epovdn  =dbBean.getElementValue(0,"epovdn"  );
		String epova1  =dbBean.getElementValue(0,"epova1"  );
		String epova2  =dbBean.getElementValue(0,"epova2"  );
		String epova3  =dbBean.getElementValue(0,"epova3"  );
		String epovcy  =dbBean.getElementValue(0,"epovcy"  );
		String epovzp  =dbBean.getElementValue(0,"epovzp"  );
		String epovcn  =dbBean.getElementValue(0,"epovcn"  );
		String epobyr  =dbBean.getElementValue(0,"epobyr"  );
		String epobyn  =dbBean.getElementValue(0,"epobyn"  );
		String epodpt  =dbBean.getElementValue(0,"epodpt"  );
		String epodpn  =dbBean.getElementValue(0,"epodpn"  );
		String eposdpt =dbBean.getElementValue(0,"eposdpt" );
		String eposdptn=dbBean.getElementValue(0,"eposdptn");
		String epotrm  =dbBean.getElementValue(0,"epotrm"  );
		String epotmn  =dbBean.getElementValue(0,"epotmn"  );
		String epofrc  =dbBean.getElementValue(0,"epofrc"  );
		String epofrn  =dbBean.getElementValue(0,"epofrn"  );
		String epoedt  =dbBean.getElementValue(0,"epoedt"  );
		String eposdt  =dbBean.getElementValue(0,"eposdt"  );
		String epoqdt  =dbBean.getElementValue(0,"epoqdt"  );
		String epordt  =dbBean.getElementValue(0,"epordt"  );
		String eposts  =dbBean.getElementValue(0,"eposts"  );
		String epostd  =dbBean.getElementValue(0,"epostd"  );
		String epovct  =dbBean.getElementValue(0,"epovct"  );
		String epospn  =dbBean.getElementValue(0,"epospn"  );
		String eposfx  =dbBean.getElementValue(0,"eposfx"  );
		String epovpn  =dbBean.getElementValue(0,"epovpn"  );
		String epovfx  =dbBean.getElementValue(0,"epovfx"  );
		String epocur  =dbBean.getElementValue(0,"epocur"  );
		String epocrd  =dbBean.getElementValue(0,"epocrd"  );
		String epoflg  =dbBean.getElementValue(0,"epoflg"  );
		String eposeq  =dbBean.getElementValue(0,"eposeq"  );
		String eponot1 =dbBean.getElementValue(0,"eponot1" );
		String eponot2 =dbBean.getElementValue(0,"eponot2" );
		String eponot3 =dbBean.getElementValue(0,"eponot3" );
		String epofob  =dbBean.getElementValue(0,"epofob"  );
		String eposp1  =dbBean.getElementValue(0,"eposp1"  );
		String eposp2  =dbBean.getElementValue(0,"eposp2"  );
		String epospp  =dbBean.getElementValue(0,"epospp"  );
		String epospc  =dbBean.getElementValue(0,"epospc"  );
		String epotdt  =dbBean.getElementValue(0,"epotdt"  );
		String epoodt  =dbBean.getElementValue(0,"epoodt"  );


   PropertyUtils.setSimpleProperty(form, "epocdt" ,epocdt );
   PropertyUtils.setSimpleProperty(form, "epousr"  ,epousr  );
   PropertyUtils.setSimpleProperty(form, "eponum"  ,eponum  );
   PropertyUtils.setSimpleProperty(form, "epoctm"  ,epoctm  );
   PropertyUtils.setSimpleProperty(form, "epostrz" ,epostrz );
   PropertyUtils.setSimpleProperty(form, "epostn"  ,epostn  );
   PropertyUtils.setSimpleProperty(form, "epovndz" ,epovndz );
   PropertyUtils.setSimpleProperty(form, "eposa1"  ,eposa1  );
   PropertyUtils.setSimpleProperty(form, "eposa2"  ,eposa2  );
   PropertyUtils.setSimpleProperty(form, "eposa3"  ,eposa3  );
   PropertyUtils.setSimpleProperty(form, "eposcy"  ,eposcy  );
   PropertyUtils.setSimpleProperty(form, "eposzp"  ,eposzp  );
   PropertyUtils.setSimpleProperty(form, "eposcn"  ,eposcn  );
   PropertyUtils.setSimpleProperty(form, "epovdn"  ,epovdn  );
   PropertyUtils.setSimpleProperty(form, "epova1"  ,epova1  );
   PropertyUtils.setSimpleProperty(form, "epova2"  ,epova2  );
   PropertyUtils.setSimpleProperty(form, "epova3"  ,epova3  );
   PropertyUtils.setSimpleProperty(form, "epovcy"  ,epovcy  );
   PropertyUtils.setSimpleProperty(form, "epovzp"  ,epovzp  );
   PropertyUtils.setSimpleProperty(form, "epovcn"  ,epovcn  );
   PropertyUtils.setSimpleProperty(form, "epobyr"  ,epobyr  );
   PropertyUtils.setSimpleProperty(form, "epobyn"  ,epobyn  );
   PropertyUtils.setSimpleProperty(form, "epodpt"  ,epodpt  );
   PropertyUtils.setSimpleProperty(form, "epodpn"  ,epodpn  );
   PropertyUtils.setSimpleProperty(form, "eposdpt" ,eposdpt );
   PropertyUtils.setSimpleProperty(form, "eposdptn",eposdptn);
   PropertyUtils.setSimpleProperty(form, "epotrm"  ,epotrm  );
   PropertyUtils.setSimpleProperty(form, "epotmn"  ,epotmn  );
   PropertyUtils.setSimpleProperty(form, "epofrc"  ,epofrc  );
   PropertyUtils.setSimpleProperty(form, "epofrn"  ,epofrn  );
   PropertyUtils.setSimpleProperty(form, "epoedt"  ,epoedt  );
   PropertyUtils.setSimpleProperty(form, "eposdt"  ,eposdt );
   PropertyUtils.setSimpleProperty(form, "epoqdt"  ,epoqdt );
   PropertyUtils.setSimpleProperty(form, "epordt"  ,epordt  );
   PropertyUtils.setSimpleProperty(form, "eposts"  ,eposts  );
   PropertyUtils.setSimpleProperty(form, "epostd"  ,epostd  );
   PropertyUtils.setSimpleProperty(form, "epovct"  ,epovct  );
   PropertyUtils.setSimpleProperty(form, "epospn"  ,epospn  );
   PropertyUtils.setSimpleProperty(form, "eposfx"  ,eposfx  );
   PropertyUtils.setSimpleProperty(form, "epovpn"  ,epovpn  );
   PropertyUtils.setSimpleProperty(form, "epovfx"  ,epovfx  );
   PropertyUtils.setSimpleProperty(form, "epocur"  ,epocur  );
   PropertyUtils.setSimpleProperty(form, "epocrd"  ,epocrd  );
   PropertyUtils.setSimpleProperty(form, "epoflg"  ,epoflg  );
   PropertyUtils.setSimpleProperty(form, "eposeq"  ,eposeq  );
   PropertyUtils.setSimpleProperty(form, "eponot1" ,eponot1 );
   PropertyUtils.setSimpleProperty(form, "eponot2" ,eponot2 );
   PropertyUtils.setSimpleProperty(form, "eponot3" ,eponot3 );
   PropertyUtils.setSimpleProperty(form, "epofob"  ,epofob  );
   PropertyUtils.setSimpleProperty(form, "eposp1"  ,eposp1  );
   PropertyUtils.setSimpleProperty(form, "eposp2"  ,eposp2  );
   PropertyUtils.setSimpleProperty(form, "epospp"  ,epospp  );
   PropertyUtils.setSimpleProperty(form, "epospc"  ,epospc  );
   PropertyUtils.setSimpleProperty(form, "epotdt"  ,epotdt );
   PropertyUtils.setSimpleProperty(form, "epoodt"  ,epoodt  );
   //写日志
   CommActionLog  cal= new CommActionLog();
			
   cal.setAct_user(uname);
   cal.setAct_from("homeworldOrder");
   cal.setAct_do("SEA");
   cal.setAct_table("EPOHDR");
   cal.setAct_ip(request.getRemoteAddr());
   cal.setAct_memo("查询定单编号为" + eponum);
   cal.setAct_me("");
   cal.setActionLog();
 
                                                  
		String[] epostr =null;  //商店             
		String[] epovnd =null;  //供货商           
		String[] epossq =null;  //sku序号          
		String[] eposku =null;  //sku              
		String[] eposkd =null;  //sku描述          
		String[] epovds =null;  //供货商货号       
		String[] eposum =null;  //销售单位         
		String[] epobum =null;  //采购单位         
		String[] epoupc =null;  //upc              
		String[] epocas =null;  //订货数_包        
		String[] epoqty =null;  //订货数_件        
		String[] epomgn =null;  //规格             
		String[] epobts =null;  //epobts           
		String[] eponct =null;  //净成本           
		String[] eposks =null;  //折扣标志 
		String[] values =null;  //跳转页值
		String[] labels =null;  //跳转页显示
		String[] length =null;
		String[] sumperpagecas=null; 
	    String[] sumperpageqty=null;
	    String[] sumperpagenct=null;
		String[] sum =null;  
		int sumpage=0;                  //为消除打印最后的空页		

		int count =0;
		strsql="select * from epodtl where "+where+" ";   
		try{
			dbBean.executeSelect(strsql);
		} catch (Exception e) {
			errors.add("errormessage",new ActionError("OrderIn.readdb"));
			return "0";
		}
		count=dbBean.getRowCount();
		int count1=count;

		//计算分页显示的最大页数
		String rowsperpage0=(String)PropertyUtils.getSimpleProperty(form,"rowsperpage");
		int rowsperpage=Integer.parseInt(rowsperpage0);
		int maxpage=1;
			if(count%rowsperpage==0){
				 maxpage=count/rowsperpage;
			}
			else{
				 maxpage=count/rowsperpage+1;
			}
		if(maxpage==0){
		 values=new String[1];
		 labels=new String[1];		
		}else{
		values=new String[maxpage];  
		labels=new String[maxpage];
		}
		if(maxpage!=0){		
		for(int i=1;i<=maxpage;i++){
			 int y=i;
			 String x=String.valueOf(y);
			 labels[i-1]=x;
			 values[i-1]=x;
		}
	}else{
		     labels[0]="1";
		     values[0]="1";
		}
		int selected=0;
		String select=((String)PropertyUtils.getSimpleProperty(form,"selected"));
		if(select!=null){			   
			   selected=Integer.parseInt(select);
			   if(selected!=maxpage){ 
			   count=rowsperpage;
			   }else{
			   count=count1-(selected-1)*rowsperpage;
			   }      
			   length=new String[1];
			   length[0]="1";
			   sumpage=1;		
		  }else{
		  	if(maxpage!=0){ 		  
			    length=new String[maxpage];
			}else{
			    length=new String[1];
			}
			sumpage=maxpage;
			if(maxpage!=0){
			   for(int i=0;i<maxpage;i++){
				length[i]="i+1";
			   }
			}else{
			   length[0]="1";
			}			  	 
		}
		if(selected==0){
			selected=1;
		}
		epostr=new String[count];
		epovnd=new String[count];
		epossq=new String[count];
		eposku=new String[count];
		eposkd=new String[count];
		epovds=new String[count];
		eposum=new String[count];
		epobum=new String[count];
		epoupc=new String[count];
		epocas=new String[count];
		epoqty=new String[count];
		epomgn=new String[count];
		epobts=new String[count];
		eponct=new String[count];
		eposks=new String[count];
		if(maxpage!=0){
		   sumperpagecas=new String[maxpage];
		   sumperpageqty=new String[maxpage];
		   sumperpagenct=new String[maxpage];
		}else{
		   sumperpagecas=new String[1];
		   sumperpageqty=new String[1];
		   sumperpagenct=new String[1];		 
		}
		double countcas=0;
		double countqty=0;
		double countnct=0;

		 
		for(int i=0;i<count;i++){
			int t=((selected)-1)*rowsperpage;   
			epostr[i]=dbBean.getElementValue(i+t,"epostr"  ); 
			epovnd[i]=dbBean.getElementValue(i+t,"epovnd"  ); 
			epossq[i]=dbBean.getElementValue(i+t,"epossq"  ); 
			eposku[i]=dbBean.getElementValue(i+t,"eposku"  ); 
			eposkd[i]=dbBean.getElementValue(i+t,"eposkd"  ); 
			epovds[i]=dbBean.getElementValue(i+t,"epovds"  ); 
			eposum[i]=dbBean.getElementValue(i+t,"eposum"  ); 
			epobum[i]=dbBean.getElementValue(i+t,"epobum"  ); 
			epoupc[i]=dbBean.getElementValue(i+t,"epoupc"  ); 
			epocas[i]=dbBean.getElementValue(i+t,"epocas"  ); 
			epoqty[i]=dbBean.getElementValue(i+t,"epoqty"  ); 
			epomgn[i]=dbBean.getElementValue(i+t,"epomgn"  ); 
			epobts[i]=dbBean.getElementValue(i+t,"epobts"  ); 
			eponct[i]=dbBean.getElementValue(i+t,"eponct"  ); 
			eposks[i]=dbBean.getElementValue(i+t,"eposks"  );

				
				if (epostr[i]==null){epostr[i]="";}
				if (epovnd[i]==null){epovnd[i]="";}
				if (epossq[i]==null){epossq[i]="";}
				if (eposku[i]==null){eposku[i]="";}
				if (eposkd[i]==null){eposkd[i]="";}
				if (epovds[i]==null){epovds[i]="";}
				if (eposum[i]==null){eposum[i]="";}
				if (epobum[i]==null){epobum[i]="";}
				if (epoupc[i]==null){epoupc[i]="";}
				if (epocas[i]==null){epocas[i]="";}
				if (epoqty[i]==null){epoqty[i]="";}
				if (epomgn[i]==null){epomgn[i]="";}
				if (epobts[i]==null){epobts[i]="";}
				if (eponct[i]==null){eponct[i]="";}
				if (eposks[i]==null){eposks[i]="";}


			epocas[i]=DecimalTools.format(epocas[i],"###,##0.00");
			epoqty[i]=DecimalTools.format(epoqty[i],"###,##0.00");
			eponct[i]=DecimalTools.format(eponct[i],"###,##0.0000");
 
			countcas=countcas+Double.parseDouble(epocas[i]);
			countqty=countqty+Double.parseDouble(epoqty[i]);
			countnct=countnct+Double.parseDouble(eponct[i]);
			if(((i+1)%rowsperpage)==0&&(i!=0)){
				sumperpagecas[(i+1)/rowsperpage-1]=DecimalTools.format(countcas,"###,##0.00");			
				sumperpageqty[(i+1)/rowsperpage-1]=DecimalTools.format(countqty,"###,##0.00");	
				sumperpagenct[(i+1)/rowsperpage-1]=DecimalTools.format(countnct,"###,##0.00");	
				countcas=0;
				countqty=0;
				countnct=0;
			}
			if((i==(count-1))&&((i+1)%rowsperpage)!=0){
				int maxpage1;
				if(count==count1){
					 maxpage1=maxpage-1;
				}else{
					 maxpage1=0;
				}
				sumperpagecas[maxpage1]=DecimalTools.format(countcas,"###,##0.00");			
				sumperpageqty[maxpage1]=DecimalTools.format(countqty,"###,##0.00");	
				sumperpagenct[maxpage1]=DecimalTools.format(countnct,"###,##0.00");
			}
			
		}

//		epocas[count-1]=DecimalTools.format(countcas,"###,##0.00");
//		epoqty[count-1]=DecimalTools.format(countqty,"###,##0.00");
//		eponct[count-1]=DecimalTools.format(countnct,"###,##0.0000");
//		epoupc[count-1]="小计--";
        if(maxpage==0){
			sumperpagecas[maxpage]=DecimalTools.format(countcas,"###,##0.00");			
			sumperpageqty[maxpage]=DecimalTools.format(countqty,"###,##0.00");	
			sumperpagenct[maxpage]=DecimalTools.format(countnct,"###,##0.00");        
        }
		sum =new String[count1];
		double sumall=0;
		if(count1!=0){		
            for(int i=0;i<count1;i++){
			  sum[i]=dbBean.getElementValue(i,"eponct"); 
			  sumall=sumall+Double.parseDouble(sum[i]);
            }
		}else{
		    sumall=0;
		}
	    String strsumall=DecimalTools.format(sumall,"###,##0.00"); 
        
        String maxpages=String.valueOf(maxpage);
		String sumpage1=String.valueOf(sumpage);
		PropertyUtils.setSimpleProperty(form, "labels" ,labels);
		PropertyUtils.setSimpleProperty(form, "values" ,values);       
		PropertyUtils.setSimpleProperty(form, "epostr" ,epostr);
		PropertyUtils.setSimpleProperty(form, "epovnd" ,epovnd);
		PropertyUtils.setSimpleProperty(form, "epossq" ,epossq);
		PropertyUtils.setSimpleProperty(form, "eposku" ,eposku);
		PropertyUtils.setSimpleProperty(form, "eposkd" ,eposkd);
		PropertyUtils.setSimpleProperty(form, "epovds" ,epovds);
		PropertyUtils.setSimpleProperty(form, "eposum" ,eposum);
		PropertyUtils.setSimpleProperty(form, "epobum" ,epobum);
		PropertyUtils.setSimpleProperty(form, "epoupc" ,epoupc);
		PropertyUtils.setSimpleProperty(form, "epocas" ,epocas);
		PropertyUtils.setSimpleProperty(form, "epoqty" ,epoqty);
		PropertyUtils.setSimpleProperty(form, "epomgn" ,epomgn);
		PropertyUtils.setSimpleProperty(form, "epobts" ,epobts);
		PropertyUtils.setSimpleProperty(form, "eponct" ,eponct);
		PropertyUtils.setSimpleProperty(form, "eposks" ,eposks);
		PropertyUtils.setSimpleProperty(form,"maxpage",maxpages);
		PropertyUtils.setSimpleProperty(form, "length" ,length);
		PropertyUtils.setSimpleProperty(form, "sumperpagecas" ,sumperpagecas);
		PropertyUtils.setSimpleProperty(form, "sumperpageqty" ,sumperpageqty);
		PropertyUtils.setSimpleProperty(form, "sumperpagenct" ,sumperpagenct);
		PropertyUtils.setSimpleProperty(form, "strsumall" ,strsumall);
		PropertyUtils.setSimpleProperty(form, "sumpage" ,sumpage1);
         
    	return "1";
	}

	public void resetFormbeen(ActionForm form,HttpServletRequest request) throws Exception{
 
	}

	/* （非 Javadoc）
	 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		return executeme(mapping,form,request,response);
	}
	
	
	
}
