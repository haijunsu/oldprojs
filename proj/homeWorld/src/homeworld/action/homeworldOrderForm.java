/*
 * @(#)homewordOrderForm.java  2004-1-8
 *
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.action;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author lyc
 */

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author 李永初
 */
public class homeworldOrderForm  extends ActionForm {
				
	private String url="";
	
	private String printmark="0";//0打印1打印送货点
			
	private String flag="";
	private String page="";
	private String send="0";

	private String epocdt  ="";
	private String epousr  ="";
	private String eponum  ="";
	private String epoctm  ="";
	private String epostrz  ="";
	private String epostn  ="";
	private String epovndz  ="";
	private String eposa1  ="";
	private String eposa2  ="";
	private String eposa3  ="";
	private String eposcy  ="";
	private String eposzp  ="";
	private String eposcn  ="";
	private String epovdn  ="";
	private String epova1  ="";
	private String epova2  ="";
	private String epova3  ="";
	private String epovcy  ="";
	private String epovzp  ="";
	private String epovcn  ="";
	private String epobyr  ="";
	private String epobyn  ="";
	private String epodpt  ="";
	private String epodpn  ="";
	private String eposdpt ="";
	private String eposdptn="";
	private String epotrm  ="";
	private String epotmn  ="";
	private String epofrc  ="";
	private String epofrn  ="";
	private String epoedt  ="";
	private String eposdt  ="";
	private String epoqdt  ="";
	private String epordt  ="";
	private String eposts  ="";
	private String epostd  ="";
	private String epovct  ="";
	private String epospn  ="";
	private String eposfx  ="";
	private String epovpn  ="";
	private String epovfx  ="";
	private String epocur  ="";
	private String epocrd  ="";
	private String epoflg  ="";
	private String eposeq  ="";
	private String eponot1 ="";
	private String eponot2 ="";
	private String eponot3 ="";
	private String epofob  ="";
	private String eposp1  ="";
	private String eposp2  ="";
	private String epospp  ="";
	private String epospc  ="";
	private String epotdt  ="";
	private String epoodt  ="";
	private String epotyp  ="";
	
	
	private String rowsperpage ="3";
	private String countcas1="";
	private String countqty1="";
	private String countnct1="";
	private String strsumall="";
	
	private String queryid="";	
	private String selectwhere ="";
	
            
	private String[] epostr =null;  //商店             
	private String[] epovnd =null;  //供货商           
	private String[] epossq =null;  //sku序号          
	private String[] eposku =null;  //sku              
	private String[] eposkd =null;  //sku描述          
	private String[] epovds =null;  //供货商货号       
	private String[] eposum =null;  //销售单位         
	private String[] epobum =null;  //采购单位         
	private String[] epoupc =null;  //upc              
	private String[] epocas =null;  //订货数_包        
	private String[] epoqty =null;  //订货数_件        
	private String[] epomgn =null;  //规格             
	private String[] epobts =null;  //epobts           
	private String[] eponct =null;  //净成本           
	private String[] eposks =null;  //折扣标志
	
	private String[] values =null;  //跳转页值
	private String[] labels =null;  //跳转页显示
	private String selected =null;  //跳转初始值
    private String maxpage =null;   //总页数
	private String offset =null; 
	private String sumpage =null; 
	private String[] length =null;
	
	private String[] sumperpagecas =null;
	private String[] sumperpageqty =null;
	private String[] sumperpagenct =null; 
	
	
		
	public homeworldOrderForm() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		return errors;
	}


		/**
		 * @return
		 */
		public String getFlag() {
			return flag;
		}

		/**
		 * @return
		 */
		public String getPage() {
			return page;
		}

		/**
		 * @param string
		 */
		public void setFlag(String string) {
			flag = string;
		}

		/**
		 * @param string
		 */
		public void setPage(String string) {
			page = string;
		}


	/**
	 * @return
	 */
	public String[] getEpobts() {
		return epobts;
	}

	/**
	 * @return
	 */
	public String[] getEpobum() {
		return epobum;
	}

	/**
	 * @return
	 */
	public String getEpobyn() {
		return epobyn;
	}

	/**
	 * @return
	 */
	public String getEpobyr() {
		return epobyr;
	}

	/**
	 * @return
	 */
	public String[] getEpocas() {
		return epocas;
	}

	

	/**
	 * @return
	 */
	public String getEpocrd() {
		return epocrd;
	}

	/**
	 * @return
	 */
	public String getEpoctm() {
		return epoctm;
	}

	/**
	 * @return
	 */
	public String getEpocur() {
		return epocur;
	}

	/**
	 * @return
	 */
	public String getEpodpn() {
		return epodpn;
	}

	/**
	 * @return
	 */
	public String getEpodpt() {
		return epodpt;
	}

	/**
	 * @return
	 */
	public String getEpoedt() {
		return epoedt;
	}

	/**
	 * @return
	 */
	public String getEpoflg() {
		return epoflg;
	}

	/**
	 * @return
	 */
	public String getEpofob() {
		return epofob;
	}

	/**
	 * @return
	 */
	public String getEpofrc() {
		return epofrc;
	}

	/**
	 * @return
	 */
	public String getEpofrn() {
		return epofrn;
	}

	/**
	 * @return
	 */
	public String[] getEpomgn() {
		return epomgn;
	}

	/**
	 * @return
	 */
	public String[] getEponct() {
		return eponct;
	}

	/**
	 * @return
	 */
	public String getEponot1() {
		return eponot1;
	}

	/**
	 * @return
	 */
	public String getEponot2() {
		return eponot2;
	}

	/**
	 * @return
	 */
	public String getEponot3() {
		return eponot3;
	}

	/**
	 * @return
	 */
	public String getEponum() {
		return eponum;
	}

	/**
	 * @return
	 */
	public String getEpoodt() {
		return epoodt;
	}

	/**
	 * @return
	 */
	public String[] getEpoqty() {
		return epoqty;
	}

	/**
	 * @return
	 */
	public String getEpordt() {
		return epordt;
	}

	/**
	 * @return
	 */
	public String getEposa1() {
		return eposa1;
	}

	/**
	 * @return
	 */
	public String getEposa2() {
		return eposa2;
	}

	/**
	 * @return
	 */
	public String getEposa3() {
		return eposa3;
	}

	/**
	 * @return
	 */
	public String getEposcn() {
		return eposcn;
	}

	/**
	 * @return
	 */
	public String getEposcy() {
		return eposcy;
	}

	/**
	 * @return
	 */
	public String getEposdpt() {
		return eposdpt;
	}

	/**
	 * @return
	 */
	public String getEposdptn() {
		return eposdptn;
	}


	/**
	 * @return
	 */
	public String getEposeq() {
		return eposeq;
	}

	/**
	 * @return
	 */
	public String getEposfx() {
		return eposfx;
	}

	/**
	 * @return
	 */
	public String[] getEposkd() {
		return eposkd;
	}

	/**
	 * @return
	 */
	public String[] getEposks() {
		return eposks;
	}

	/**
	 * @return
	 */
	public String[] getEposku() {
		return eposku;
	}

	/**
	 * @return
	 */
	public String getEposp1() {
		return eposp1;
	}

	/**
	 * @return
	 */
	public String getEposp2() {
		return eposp2;
	}

	/**
	 * @return
	 */
	public String getEpospc() {
		return epospc;
	}

	/**
	 * @return
	 */
	public String getEpospn() {
		return epospn;
	}

	/**
	 * @return
	 */
	public String getEpospp() {
		return epospp;
	}

	/**
	 * @return
	 */
	public String[] getEpossq() {
		return epossq;
	}

	/**
	 * @return
	 */
	public String getEpostd() {
		return epostd;
	}

	/**
	 * @return
	 */
	public String getEpostn() {
		return epostn;
	}

	/**
	 * @return
	 */
	public String[] getEpostr() {
		return epostr;
	}

	/**
	 * @return
	 */
	public String getEpostrz() {
		return epostrz;
	}

	/**
	 * @return
	 */
	public String getEposts() {
		return eposts;
	}

	/**
	 * @return
	 */
	public String[] getEposum() {
		return eposum;
	}

	/**
	 * @return
	 */
	public String getEposzp() {
		return eposzp;
	}

	/**
	 * @return
	 */
	public String getEpotmn() {
		return epotmn;
	}

	/**
	 * @return
	 */
	public String getEpotrm() {
		return epotrm;
	}

	/**
	 * @return
	 */
	public String[] getEpoupc() {
		return epoupc;
	}

	/**
	 * @return
	 */
	public String getEpousr() {
		return epousr;
	}

	/**
	 * @return
	 */
	public String getEpova1() {
		return epova1;
	}

	/**
	 * @return
	 */
	public String getEpova2() {
		return epova2;
	}

	/**
	 * @return
	 */
	public String getEpova3() {
		return epova3;
	}

	/**
	 * @return
	 */
	public String getEpovcn() {
		return epovcn;
	}

	/**
	 * @return
	 */
	public String getEpovct() {
		return epovct;
	}

	/**
	 * @return
	 */
	public String getEpovcy() {
		return epovcy;
	}

	/**
	 * @return
	 */
	public String getEpovdn() {
		return epovdn;
	}

	/**
	 * @return
	 */
	public String[] getEpovds() {
		return epovds;
	}

	/**
	 * @return
	 */
	public String getEpovfx() {
		return epovfx;
	}

	/**
	 * @return
	 */
	public String[] getEpovnd() {
		return epovnd;
	}

	/**
	 * @return
	 */
	public String getEpovndz() {
		return epovndz;
	}

	/**
	 * @return
	 */
	public String getEpovpn() {
		return epovpn;
	}

	/**
	 * @return
	 */
	public String getEpovzp() {
		return epovzp;
	}
	
	/**
	 * @param strings
	 */
	public void setEpobts(String[] strings) {
		epobts = strings;
	}

	/**
	 * @param strings
	 */
	public void setEpobum(String[] strings) {
		epobum = strings;
	}

	/**
	 * @param string
	 */
	public void setEpobyn(String string) {
		epobyn = string;
	}

	/**
	 * @param string
	 */
	public void setEpobyr(String string) {
		epobyr = string;
	}

	/**
	 * @param strings
	 */
	public void setEpocas(String[] strings) {
		epocas = strings;
	}



	/**
	 * @param string
	 */
	public void setEpocrd(String string) {
		epocrd = string;
	}

	/**
	 * @param string
	 */
	public void setEpoctm(String string) {
		epoctm = string;
	}

	/**
	 * @param string
	 */
	public void setEpocur(String string) {
		epocur = string;
	}

	/**
	 * @param string
	 */
	public void setEpodpn(String string) {
		epodpn = string;
	}

	/**
	 * @param string
	 */
	public void setEpodpt(String string) {
		epodpt = string;
	}

	/**
	 * @param string
	 */
	public void setEpoedt(String string) {
		epoedt = string;
	}

	/**
	 * @param string
	 */
	public void setEpoflg(String string) {
		epoflg = string;
	}

	/**
	 * @param string
	 */
	public void setEpofob(String string) {
		epofob = string;
	}

	/**
	 * @param string
	 */
	public void setEpofrc(String string) {
		epofrc = string;
	}

	/**
	 * @param string
	 */
	public void setEpofrn(String string) {
		epofrn = string;
	}

	/**
	 * @param strings
	 */
	public void setEpomgn(String[] strings) {
		epomgn = strings;
	}

	/**
	 * @param strings
	 */
	public void setEponct(String[] strings) {
		eponct = strings;
	}

	/**
	 * @param string
	 */
	public void setEponot1(String string) {
		eponot1 = string;
	}

	/**
	 * @param string
	 */
	public void setEponot2(String string) {
		eponot2 = string;
	}

	/**
	 * @param string
	 */
	public void setEponot3(String string) {
		eponot3 = string;
	}

	/**
	 * @param string
	 */
	public void setEponum(String string) {
		eponum = string;
	}

	/**
	 * @param string
	 */
	public void setEpoodt(String string) {
		epoodt = string;
	}

	/**
	 * @param strings
	 */
	public void setEpoqty(String[] strings) {
		epoqty = strings;
	}

	/**
	 * @param string
	 */
	public void setEpordt(String string) {
		epordt = string;
	}

	/**
	 * @param string
	 */
	public void setEposa1(String string) {
		eposa1 = string;
	}

	/**
	 * @param string
	 */
	public void setEposa2(String string) {
		eposa2 = string;
	}

	/**
	 * @param string
	 */
	public void setEposa3(String string) {
		eposa3 = string;
	}

	/**
	 * @param string
	 */
	public void setEposcn(String string) {
		eposcn = string;
	}

	/**
	 * @param string
	 */
	public void setEposcy(String string) {
		eposcy = string;
	}

	/**
	 * @param string
	 */
	public void setEposdpt(String string) {
		eposdpt = string;
	}

	/**
	 * @param string
	 */
	public void setEposdptn(String string) {
		eposdptn = string;
	}


	/**
	 * @param string
	 */
	public void setEposeq(String string) {
		eposeq = string;
	}

	/**
	 * @param string
	 */
	public void setEposfx(String string) {
		eposfx = string;
	}

	/**
	 * @param strings
	 */
	public void setEposkd(String[] strings) {
		eposkd = strings;
	}

	/**
	 * @param strings
	 */
	public void setEposks(String[] strings) {
		eposks = strings;
	}

	/**
	 * @param strings
	 */
	public void setEposku(String[] strings) {
		eposku = strings;
	}

	/**
	 * @param string
	 */
	public void setEposp1(String string) {
		eposp1 = string;
	}

	/**
	 * @param string
	 */
	public void setEposp2(String string) {
		eposp2 = string;
	}

	/**
	 * @param string
	 */
	public void setEpospc(String string) {
		epospc = string;
	}

	/**
	 * @param string
	 */
	public void setEpospn(String string) {
		epospn = string;
	}

	/**
	 * @param string
	 */
	public void setEpospp(String string) {
		epospp = string;
	}

	/**
	 * @param strings
	 */
	public void setEpossq(String[] strings) {
		epossq = strings;
	}

	/**
	 * @param string
	 */
	public void setEpostd(String string) {
		epostd = string;
	}

	/**
	 * @param string
	 */
	public void setEpostn(String string) {
		epostn = string;
	}

	/**
	 * @param strings
	 */
	public void setEpostr(String[] strings) {
		epostr = strings;
	}

	/**
	 * @param string
	 */
	public void setEpostrz(String string) {
		epostrz = string;
	}

	/**
	 * @param string
	 */
	public void setEposts(String string) {
		eposts = string;
	}

	/**
	 * @param strings
	 */
	public void setEposum(String[] strings) {
		eposum = strings;
	}

	/**
	 * @param string
	 */
	public void setEposzp(String string) {
		eposzp = string;
	}

	/**
	 * @param string
	 */
	public void setEpotmn(String string) {
		epotmn = string;
	}

	/**
	 * @param string
	 */
	public void setEpotrm(String string) {
		epotrm = string;
	}

	/**
	 * @param strings
	 */
	public void setEpoupc(String[] strings) {
		epoupc = strings;
	}

	/**
	 * @param string
	 */
	public void setEpousr(String string) {
		epousr = string;
	}

	/**
	 * @param string
	 */
	public void setEpova1(String string) {
		epova1 = string;
	}

	/**
	 * @param string
	 */
	public void setEpova2(String string) {
		epova2 = string;
	}

	/**
	 * @param string
	 */
	public void setEpova3(String string) {
		epova3 = string;
	}

	/**
	 * @param string
	 */
	public void setEpovcn(String string) {
		epovcn = string;
	}

	/**
	 * @param string
	 */
	public void setEpovct(String string) {
		epovct = string;
	}

	/**
	 * @param string
	 */
	public void setEpovcy(String string) {
		epovcy = string;
	}

	/**
	 * @param string
	 */
	public void setEpovdn(String string) {
		epovdn = string;
	}

	/**
	 * @param strings
	 */
	public void setEpovds(String[] strings) {
		epovds = strings;
	}

	/**
	 * @param string
	 */
	public void setEpovfx(String string) {
		epovfx = string;
	}

	/**
	 * @param strings
	 */
	public void setEpovnd(String[] strings) {
		epovnd = strings;
	}

	/**
	 * @param string
	 */
	public void setEpovndz(String string) {
		epovndz = string;
	}

	/**
	 * @param string
	 */
	public void setEpovpn(String string) {
		epovpn = string;
	}

	/**
	 * @param string
	 */
	public void setEpovzp(String string) {
		epovzp = string;
	}

	/**
	 * @return
	 */
	public String getEpocdt() {
		return epocdt;
	}

	/**
	 * @return
	 */
	public String getEpoqdt() {
		return epoqdt;
	}

	/**
	 * @return
	 */
	public String getEposdt() {
		return eposdt;
	}

	/**
	 * @return
	 */
	public String getEpotdt() {
		return epotdt;
	}

	/**
	 * @param string
	 */
	public void setEpocdt(String string) {
		epocdt = string;
	}

	/**
	 * @param string
	 */
	public void setEpoqdt(String string) {
		epoqdt = string;
	}

	/**
	 * @param string
	 */
	public void setEposdt(String string) {
		eposdt = string;
	}

	/**
	 * @param string
	 */
	public void setEpotdt(String string) {
		epotdt = string;
	}
	

	public String getSelected(){     
	    	 return selected;   
	    }   
		
	public void setSelected(String selected){     
			 this.selected = selected;   
	    } 
	    
	public String[] getvalues(){   
		     return values;  
	    }
	     
	public void setvalues(String[] values){   
		     this.values = values;   
	    } 
	    
	public String[] getLabels(){ 
		     return labels;   
	    } 
	     
	public void setLabels(String[] labels){  
			 this.labels = labels;  
	    }

	/**
	 * @return
	 */
	public String getMaxpage() {
		return maxpage;
	}

	/**
	 * @param string
	 */
	public void setMaxpage(String string) {
		maxpage = string;
	}

	/**
	 * @return
	 */
	public String getOffset() {
		return offset;
	}

	/**
	 * @param string
	 */
	public void setOffset(String string) {
		offset = string;
	}

	/**
	 * @return
	 */
	public String[] getLength() {
		return length;
	}

	/**
	 * @param strings
	 */
	public void setLength(String[] strings) {
		length = strings;
	}

	/**
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return
	 */
	public String[] getValues() {
		return values;
	}

	/**
	 * @param string
	 */
	public void setUrl(String string) {
		url = string;
	}

	/**
	 * @param strings
	 */
	public void setValues(String[] strings) {
		values = strings;
	}


	/**
	 * @return
	 */
	public String getCountcas1() {
		return countcas1;
	}

	/**
	 * @return
	 */
	public String getCountnct1() {
		return countnct1;
	}

	/**
	 * @return
	 */
	public String getCountqty1() {
		return countqty1;
	}

	/**
	 * @param string
	 */
	public void setCountcas1(String string) {
		countcas1 = string;
	}

	/**
	 * @param string
	 */
	public void setCountnct1(String string) {
		countnct1 = string;
	}

	/**
	 * @param string
	 */
	public void setCountqty1(String string) {
		countqty1 = string;
	}

	/**
	 * @return
	 */
	public String[] getSumperpagecas() {
		return sumperpagecas;
	}

	/**
	 * @return
	 */
	public String[] getSumperpagenct() {
		return sumperpagenct;
	}

	/**
	 * @return
	 */
	public String[] getSumperpageqty() {
		return sumperpageqty;
	}

	/**
	 * @param strings
	 */
	public void setSumperpagecas(String[] strings) {
		sumperpagecas = strings;
	}

	/**
	 * @param strings
	 */
	public void setSumperpagenct(String[] strings) {
		sumperpagenct = strings;
	}

	/**
	 * @param strings
	 */
	public void setSumperpageqty(String[] strings) {
		sumperpageqty = strings;
	}

	/**
	 * @return
	 */
	public String getRowsperpage() {
		return rowsperpage;
	}

	/**
	 * @param string
	 */
	public void setRowsperpage(String string) {
		rowsperpage = string;
	}

	/**
	 * @return
	 */
	public String getStrsumall() {
		return strsumall;
	}

	/**
	 * @param string
	 */
	public void setStrsumall(String string) {
		strsumall = string;
	}

	/**
	 * @return
	 */
	public String getSelectwhere() {
		return selectwhere;
	}

	/**
	 * @param string
	 */
	public void setSelectwhere(String string) {
		selectwhere = string;
	}

	/**
	 * @return
	 */
	public String getQueryid() {
		return queryid;
	}

	/**
	 * @param string
	 */
	public void setQueryid(String string) {
		queryid = string;
	}

	/**
	 * @return
	 */
	public String getSumpage() {
		return sumpage;
	}

	/**
	 * @param string
	 */
	public void setSumpage(String string) {
		sumpage = string;
	}



	/**
	 * @return
	 */
	public String getPrintmark() {
		return printmark;
	}

	/**
	 * @param string
	 */
	public void setPrintmark(String string) {
		printmark = string;
	}

	/**
	 * @return
	 */
	public String getSend() {
		return send;
	}

	/**
	 * @param string
	 */
	public void setSend(String string) {
		send = string;
	}

	/**
	 * @return
	 */
	public String getEpotyp() {
		return epotyp;
	}

	/**
	 * @param string
	 */
	public void setEpotyp(String string) {
		epotyp = string;
	}

}
