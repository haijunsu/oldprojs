/*
 * @(#)homeworldPrintErvhdrForm.java  2004-4-26
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.rtv;

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
		public class homeworldPrintErvhdrForm extends ActionForm {
		
			private String selectwhere="";
			private String queryid="";
	
			private String ervnum	="";  //收货单号      
			private String ervcdt	="";  //生成日期      
			private String ervctm	="";  //生成时间      
			private String ervpno	="";  //定单号        
			private String ervstr	="";  //商店          
			private String ervstn 	="";  //商店名        
			private String ervdpt	="";  //部门          
			private String ervdpn	="";  //部门名称      
			private String ervsdpt	="";  //子部          
			private String ervsdptn ="";  //子部名
			private String ervbyr	="";  //采购          
			private String ervbyn	="";  //采购名        
			private String ervvnd	="";  //供货商        
			private String ervvdn	="";  //供货商名      
			private String ervedt	="";  //输入日期      
			private String ervva1	="";  //供货商地址    
			private String ervva2	="";  //供货商地址    
			private String ervva3	="";  //供货商地址    
			private String ervvcy	="";  //供货商城市    
			private String ervvzp	="";  //供货商邮编    
			private String ervvcn	="";  //供货商国家    
			private String ervtrm	="";  //票期代码      
			private String ervtmn	="";  //票期名        
			private String ervfrc	="";  //Freight code  
			private String ervfrn	="";  //freight desc. 
			private String ervrdt	="";  //收货日期      
			private String ervvct	="";  //联络人        
			private String ervsts	="";  //状态          
			private String ervnot1	="";  //              
			private String ervnot2	="";  //              
			private String ervnot3	="";  //              
			private String ervfob	="";  //              
			private String ervsp1	="";  //              
			private String ervsp2	="";  //              
			private String ervspp	="";  //              
			private String ervspc	="";  //              
			private String ervsdt	="";  //              
			private String ervvdt	="";  //              
			private String ervseq	="";  //序号      

			private String[] ervseqmx	=null; //序号       
			//private String[] ervnum	=null; //收货单号  
			private String[] ervstrmx	=null; //商店      
			private String[] ervvndmx	=null; //供货商    
			private String[] ervssq	=null; //sku序号   
			private String[] ervsku	=null; //sku       
			private String[] ervskd	=null; //sku描述   
			private String[] ervvds	=null; //供货商货号
			private String[] ervsum	=null; //销售单位  
			private String[] ervbum	=null; //采购单位  
			private String[] ervupc	=null; //upc       
			private String[] ervcas =null; //订货数_包 
			private String[] ervqty	=null; //订货数_件 
			private String[] ervmgn	=null; //规格      
			private String[] ervsks	=null; //折扣标志  
			private String[] ervrqy	=null; //收货数    
			private String[] ervnqy	=null; //欠收数    
		
			private String countqty	="";  
			private String countcas	="";  
			private String countrqy	="";  
			private String countnqy	="";
			  
			private String pagerow	="";
			private String endnew	="";
			
			private String ervtrt	="";
			private String ervtct	="";
			
			
			
			public homeworldPrintErvhdrForm () {
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
			public String[] getErvbum() {
				return ervbum;
			}

			/**
			 * @return
			 */
			public String getErvbyn() {
				return ervbyn;
			}

			/**
			 * @return
			 */
			public String getErvbyr() {
				return ervbyr;
			}

			/**
			 * @return
			 */
			public String[] getErvcas() {
				return ervcas;
			}

			/**
			 * @return
			 */
			public String getErvcdt() {
				return ervcdt;
			}

			/**
			 * @return
			 */
			public String getErvctm() {
				return ervctm;
			}

			/**
			 * @return
			 */
			public String getErvdpn() {
				return ervdpn;
			}

			/**
			 * @return
			 */
			public String getErvdpt() {
				return ervdpt;
			}

			/**
			 * @return
			 */
			public String getErvedt() {
				return ervedt;
			}

			/**
			 * @return
			 */
			public String getErvfob() {
				return ervfob;
			}

			/**
			 * @return
			 */
			public String getErvfrc() {
				return ervfrc;
			}

			/**
			 * @return
			 */
			public String getErvfrn() {
				return ervfrn;
			}

			/**
			 * @return
			 */
			public String[] getErvmgn() {
				return ervmgn;
			}

			/**
			 * @return
			 */
			public String getErvnot1() {
				return ervnot1;
			}

			/**
			 * @return
			 */
			public String getErvnot2() {
				return ervnot2;
			}

			/**
			 * @return
			 */
			public String getErvnot3() {
				return ervnot3;
			}

			/**
			 * @return
			 */
			public String[] getErvnqy() {
				return ervnqy;
			}

			/**
			 * @return
			 */
			public String getErvnum() {
				return ervnum;
			}

			/**
			 * @return
			 */
			public String getErvpno() {
				return ervpno;
			}

			/**
			 * @return
			 */
			public String[] getErvqty() {
				return ervqty;
			}

			/**
			 * @return
			 */
			public String getErvrdt() {
				return ervrdt;
			}

			/**
			 * @return
			 */
			public String[] getErvrqy() {
				return ervrqy;
			}

			/**
			 * @return
			 */
			public String getErvsdpt() {
				return ervsdpt;
			}

			/**
			 * @return
			 */
			public String getErvsdptn() {
				return ervsdptn;
			}

			/**
			 * @return
			 */
			public String getErvsdt() {
				return ervsdt;
			}

			/**
			 * @return
			 */
			public String getErvseq() {
				return ervseq;
			}

			/**
			 * @return
			 */
			public String[] getErvseqmx() {
				return ervseqmx;
			}

			/**
			 * @return
			 */
			public String[] getErvskd() {
				return ervskd;
			}

			/**
			 * @return
			 */
			public String[] getErvsks() {
				return ervsks;
			}

			/**
			 * @return
			 */
			public String[] getErvsku() {
				return ervsku;
			}

			/**
			 * @return
			 */
			public String getErvsp1() {
				return ervsp1;
			}

			/**
			 * @return
			 */
			public String getErvsp2() {
				return ervsp2;
			}

			/**
			 * @return
			 */
			public String getErvspc() {
				return ervspc;
			}

			/**
			 * @return
			 */
			public String getErvspp() {
				return ervspp;
			}

			/**
			 * @return
			 */
			public String[] getErvssq() {
				return ervssq;
			}

			/**
			 * @return
			 */
			public String getErvstn() {
				return ervstn;
			}

			/**
			 * @return
			 */
			public String getErvstr() {
				return ervstr;
			}

			/**
			 * @return
			 */
			public String[] getErvstrmx() {
				return ervstrmx;
			}

			/**
			 * @return
			 */
			public String getErvsts() {
				return ervsts;
			}

			/**
			 * @return
			 */
			public String[] getErvsum() {
				return ervsum;
			}

			/**
			 * @return
			 */
			public String getErvtmn() {
				return ervtmn;
			}

			/**
			 * @return
			 */
			public String getErvtrm() {
				return ervtrm;
			}

			/**
			 * @return
			 */
			public String[] getErvupc() {
				return ervupc;
			}

			/**
			 * @return
			 */
			public String getErvva1() {
				return ervva1;
			}

			/**
			 * @return
			 */
			public String getErvva2() {
				return ervva2;
			}

			/**
			 * @return
			 */
			public String getErvva3() {
				return ervva3;
			}

			/**
			 * @return
			 */
			public String getErvvcn() {
				return ervvcn;
			}

			/**
			 * @return
			 */
			public String getErvvct() {
				return ervvct;
			}

			/**
			 * @return
			 */
			public String getErvvcy() {
				return ervvcy;
			}

			/**
			 * @return
			 */
			public String getErvvdn() {
				return ervvdn;
			}

			/**
			 * @return
			 */
			public String[] getErvvds() {
				return ervvds;
			}

			/**
			 * @return
			 */
			public String getErvvdt() {
				return ervvdt;
			}

			/**
			 * @return
			 */
			public String getErvvnd() {
				return ervvnd;
			}

			/**
			 * @return
			 */
			public String[] getErvvndmx() {
				return ervvndmx;
			}

			/**
			 * @return
			 */
			public String getErvvzp() {
				return ervvzp;
			}

			/**
			 * @return
			 */
			public String getQueryid() {
				return queryid;
			}

			/**
			 * @return
			 */
			public String getSelectwhere() {
				return selectwhere;
			}

			/**
			 * @param strings
			 */
			public void setErvbum(String[] strings) {
				ervbum = strings;
			}

			/**
			 * @param string
			 */
			public void setErvbyn(String string) {
				ervbyn = string;
			}

			/**
			 * @param string
			 */
			public void setErvbyr(String string) {
				ervbyr = string;
			}

			/**
			 * @param strings
			 */
			public void setErvcas(String[] strings) {
				ervcas = strings;
			}

			/**
			 * @param string
			 */
			public void setErvcdt(String string) {
				ervcdt = string;
			}

			/**
			 * @param string
			 */
			public void setErvctm(String string) {
				ervctm = string;
			}

			/**
			 * @param string
			 */
			public void setErvdpn(String string) {
				ervdpn = string;
			}

			/**
			 * @param string
			 */
			public void setErvdpt(String string) {
				ervdpt = string;
			}

			/**
			 * @param string
			 */
			public void setErvedt(String string) {
				ervedt = string;
			}

			/**
			 * @param string
			 */
			public void setErvfob(String string) {
				ervfob = string;
			}

			/**
			 * @param string
			 */
			public void setErvfrc(String string) {
				ervfrc = string;
			}

			/**
			 * @param string
			 */
			public void setErvfrn(String string) {
				ervfrn = string;
			}

			/**
			 * @param strings
			 */
			public void setErvmgn(String[] strings) {
				ervmgn = strings;
			}

			/**
			 * @param string
			 */
			public void setErvnot1(String string) {
				ervnot1 = string;
			}

			/**
			 * @param string
			 */
			public void setErvnot2(String string) {
				ervnot2 = string;
			}

			/**
			 * @param string
			 */
			public void setErvnot3(String string) {
				ervnot3 = string;
			}

			/**
			 * @param strings
			 */
			public void setErvnqy(String[] strings) {
				ervnqy = strings;
			}

			/**
			 * @param string
			 */
			public void setErvnum(String string) {
				ervnum = string;
			}

			/**
			 * @param string
			 */
			public void setErvpno(String string) {
				ervpno = string;
			}

			/**
			 * @param strings
			 */
			public void setErvqty(String[] strings) {
				ervqty = strings;
			}

			/**
			 * @param string
			 */
			public void setErvrdt(String string) {
				ervrdt = string;
			}

			/**
			 * @param strings
			 */
			public void setErvrqy(String[] strings) {
				ervrqy = strings;
			}

			/**
			 * @param string
			 */
			public void setErvsdpt(String string) {
				ervsdpt = string;
			}

			/**
			 * @param string
			 */
			public void setErvsdptn(String string) {
				ervsdptn = string;
			}

			/**
			 * @param string
			 */
			public void setErvsdt(String string) {
				ervsdt = string;
			}

			/**
			 * @param string
			 */
			public void setErvseq(String string) {
				ervseq = string;
			}

			/**
			 * @param strings
			 */
			public void setErvseqmx(String[] strings) {
				ervseqmx = strings;
			}

			/**
			 * @param strings
			 */
			public void setErvskd(String[] strings) {
				ervskd = strings;
			}

			/**
			 * @param strings
			 */
			public void setErvsks(String[] strings) {
				ervsks = strings;
			}

			/**
			 * @param strings
			 */
			public void setErvsku(String[] strings) {
				ervsku = strings;
			}

			/**
			 * @param string
			 */
			public void setErvsp1(String string) {
				ervsp1 = string;
			}

			/**
			 * @param string
			 */
			public void setErvsp2(String string) {
				ervsp2 = string;
			}

			/**
			 * @param string
			 */
			public void setErvspc(String string) {
				ervspc = string;
			}

			/**
			 * @param string
			 */
			public void setErvspp(String string) {
				ervspp = string;
			}

			/**
			 * @param strings
			 */
			public void setErvssq(String[] strings) {
				ervssq = strings;
			}

			/**
			 * @param string
			 */
			public void setErvstn(String string) {
				ervstn = string;
			}

			/**
			 * @param string
			 */
			public void setErvstr(String string) {
				ervstr = string;
			}

			/**
			 * @param strings
			 */
			public void setErvstrmx(String[] strings) {
				ervstrmx = strings;
			}

			/**
			 * @param string
			 */
			public void setErvsts(String string) {
				ervsts = string;
			}

			/**
			 * @param strings
			 */
			public void setErvsum(String[] strings) {
				ervsum = strings;
			}

			/**
			 * @param string
			 */
			public void setErvtmn(String string) {
				ervtmn = string;
			}

			/**
			 * @param string
			 */
			public void setErvtrm(String string) {
				ervtrm = string;
			}

			/**
			 * @param strings
			 */
			public void setErvupc(String[] strings) {
				ervupc = strings;
			}

			/**
			 * @param string
			 */
			public void setErvva1(String string) {
				ervva1 = string;
			}

			/**
			 * @param string
			 */
			public void setErvva2(String string) {
				ervva2 = string;
			}

			/**
			 * @param string
			 */
			public void setErvva3(String string) {
				ervva3 = string;
			}

			/**
			 * @param string
			 */
			public void setErvvcn(String string) {
				ervvcn = string;
			}

			/**
			 * @param string
			 */
			public void setErvvct(String string) {
				ervvct = string;
			}

			/**
			 * @param string
			 */
			public void setErvvcy(String string) {
				ervvcy = string;
			}

			/**
			 * @param string
			 */
			public void setErvvdn(String string) {
				ervvdn = string;
			}

			/**
			 * @param strings
			 */
			public void setErvvds(String[] strings) {
				ervvds = strings;
			}

			/**
			 * @param string
			 */
			public void setErvvdt(String string) {
				ervvdt = string;
			}

			/**
			 * @param string
			 */
			public void setErvvnd(String string) {
				ervvnd = string;
			}

			/**
			 * @param strings
			 */
			public void setErvvndmx(String[] strings) {
				ervvndmx = strings;
			}

			/**
			 * @param string
			 */
			public void setErvvzp(String string) {
				ervvzp = string;
			}

			/**
			 * @param string
			 */
			public void setQueryid(String string) {
				queryid = string;
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
			public String getCountcas() {
				return countcas;
			}

			/**
			 * @return
			 */
			public String getCountnqy() {
				return countnqy;
			}

			/**
			 * @return
			 */
			public String getCountqty() {
				return countqty;
			}

			/**
			 * @return
			 */
			public String getCountrqy() {
				return countrqy;
			}

			/**
			 * @param string
			 */
			public void setCountcas(String string) {
				countcas = string;
			}

			/**
			 * @param string
			 */
			public void setCountnqy(String string) {
				countnqy = string;
			}

			/**
			 * @param string
			 */
			public void setCountqty(String string) {
				countqty = string;
			}

			/**
			 * @param string
			 */
			public void setCountrqy(String string) {
				countrqy = string;
			}

			/**
			 * @return
			 */
			public String getEndnew() {
				return endnew;
			}

			/**
			 * @return
			 */
			public String getPagerow() {
				return pagerow;
			}

			/**
			 * @param string
			 */
			public void setEndnew(String string) {
				endnew = string;
			}

			/**
			 * @param string
			 */
			public void setPagerow(String string) {
				pagerow = string;
			}

			/**
			 * @return
			 */
			public String getErvtct() {
				return ervtct;
			}

			/**
			 * @return
			 */
			public String getErvtrt() {
				return ervtrt;
			}

			/**
			 * @param string
			 */
			public void setErvtct(String string) {
				ervtct = string;
			}

			/**
			 * @param string
			 */
			public void setErvtrt(String string) {
				ervtrt = string;
			}

		}
