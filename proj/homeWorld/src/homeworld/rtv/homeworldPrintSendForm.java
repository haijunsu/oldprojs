/*
 * @(#)homeworldPrintSendForm.java  2004-5-14
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
		public class homeworldPrintSendForm  extends ActionForm {
			
			private String senddatetime  ="";
						   
			
			private String senddate  ="";
			private String sendid  ="";
			
			private String selectwhere ="";
			private String endnew ="";  //尾是否为新页
			private String pagerow ="";
			private String addrow =""; 

		
			private String one1 ="";  //通告类型
			private String one2 ="";  //通告类型
			private String one3 ="";  //通告类型
			
//////////////////////////////////////////////////////		
		
			private String printmark  ="";
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
			
			private String[] epoddd =null;  //质

			private String sumcas  ="";  
			private String sumqty ="";  
			private String sumnct ="";  
			
			/////////////////////////////////////////
			
			private String edcpon="";   //定单号	        
			private String edcvdr="";   //供货商号	
			private String edcvdn="";   //供货商名称	
			private String edcstr="";   //商店	        
			private String edcnum="";   //预约号	        
			private String edcstn="";   //商店名	        
			private String edcdat="";   //预约送货（日期）
			private String edctim="";   //预约送货（时间）
			private String edcrnm="";   //预约收货口
			private String edcshp="";   //预约收货口	

			private String[] edcssq =null; //序号	        
			private String[] edcsku =null; //sku编号	
			private String[] edcskd =null; //说明	        
			private String[] edcsum =null; //销售单位	
			private String[] edcupc =null; //upc	        
			private String[] edccas =null; //订货箱数	
			private String[] edcqty =null; //订货数_件	
			private String[] edcmgn =null; //规格	        
			private String[] edcdpn =null; //Part. No 	
			private String[] edcbum =null; //采购单位	
			private String[] edcwgh =null; //箱包重量	
			private String[] edccub =null; //箱包体积	
			private String[] edcadt =null; //DC允收期	
			private String[] edcplr =null; //码放规则
			private String[] edcddd =null; //码放规则
			

			private String sumwgh  ="";  //尾是否为新页
			private String sumcub ="";  //尾是否为新页
			
			public homeworldPrintSendForm () {
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



			/**
			 * @return
			 */
			public String getEndnew() {
				return endnew;
			}

			/**
			 * @param string
			 */
			public void setEndnew(String string) {
				endnew = string;
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
			public String getPagerow() {
				return pagerow;
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
			public String getSumcas() {
				return sumcas;
			}

			/**
			 * @return
			 */
			public String getSumnct() {
				return sumnct;
			}

			/**
			 * @return
			 */
			public String getSumqty() {
				return sumqty;
			}

			/**
			 * @param string
			 */
			public void setSumcas(String string) {
				sumcas = string;
			}

			/**
			 * @param string
			 */
			public void setSumnct(String string) {
				sumnct = string;
			}

			/**
			 * @param string
			 */
			public void setSumqty(String string) {
				sumqty = string;
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
			public String getOne1() {
				return one1;
			}

			/**
			 * @return
			 */
			public String getOne2() {
				return one2;
			}

			/**
			 * @return
			 */
			public String getOne3() {
				return one3;
			}

			/**
			 * @return
			 */
			public String getSenddate() {
				return senddate;
			}

			/**
			 * @return
			 */
			public String getSendid() {
				return sendid;
			}

			/**
			 * @param string
			 */
			public void setOne1(String string) {
				one1 = string;
			}

			/**
			 * @param string
			 */
			public void setOne2(String string) {
				one2 = string;
			}

			/**
			 * @param string
			 */
			public void setOne3(String string) {
				one3 = string;
			}

			/**
			 * @param string
			 */
			public void setSenddate(String string) {
				senddate = string;
			}

			/**
			 * @param string
			 */
			public void setSendid(String string) {
				sendid = string;
			}

			/**
			 * @return
			 */
			public String[] getEdcadt() {
				return edcadt;
			}

			/**
			 * @return
			 */
			public String[] getEdcbum() {
				return edcbum;
			}

			/**
			 * @return
			 */
			public String[] getEdccas() {
				return edccas;
			}

			/**
			 * @return
			 */
			public String[] getEdccub() {
				return edccub;
			}

			/**
			 * @return
			 */
			public String getEdcdat() {
				return edcdat;
			}

			/**
			 * @return
			 */
			public String[] getEdcdpn() {
				return edcdpn;
			}

			/**
			 * @return
			 */
			public String[] getEdcmgn() {
				return edcmgn;
			}

			/**
			 * @return
			 */
			public String getEdcnum() {
				return edcnum;
			}

			/**
			 * @return
			 */
			public String[] getEdcplr() {
				return edcplr;
			}

			/**
			 * @return
			 */
			public String getEdcpon() {
				return edcpon;
			}

			/**
			 * @return
			 */
			public String[] getEdcqty() {
				return edcqty;
			}

			/**
			 * @return
			 */
			public String getEdcrnm() {
				return edcrnm;
			}

			/**
			 * @return
			 */
			public String[] getEdcskd() {
				return edcskd;
			}

			/**
			 * @return
			 */
			public String[] getEdcsku() {
				return edcsku;
			}

			/**
			 * @return
			 */
			public String[] getEdcssq() {
				return edcssq;
			}

			/**
			 * @return
			 */
			public String getEdcstn() {
				return edcstn;
			}

			/**
			 * @return
			 */
			public String getEdcstr() {
				return edcstr;
			}

			/**
			 * @return
			 */
			public String[] getEdcsum() {
				return edcsum;
			}

			/**
			 * @return
			 */
			public String getEdctim() {
				return edctim;
			}

			/**
			 * @return
			 */
			public String[] getEdcupc() {
				return edcupc;
			}

			/**
			 * @return
			 */
			public String getEdcvdn() {
				return edcvdn;
			}

			/**
			 * @return
			 */
			public String getEdcvdr() {
				return edcvdr;
			}


			/**
			 * @return
			 */
			public String getSumcub() {
				return sumcub;
			}

			/**
			 * @return
			 */
			public String getSumwgh() {
				return sumwgh;
			}

			/**
			 * @param strings
			 */
			public void setEdcadt(String[] strings) {
				edcadt = strings;
			}

			/**
			 * @param strings
			 */
			public void setEdcbum(String[] strings) {
				edcbum = strings;
			}

			/**
			 * @param strings
			 */
			public void setEdccas(String[] strings) {
				edccas = strings;
			}

			/**
			 * @param strings
			 */
			public void setEdccub(String[] strings) {
				edccub = strings;
			}

			/**
			 * @param string
			 */
			public void setEdcdat(String string) {
				edcdat = string;
			}

			/**
			 * @param strings
			 */
			public void setEdcdpn(String[] strings) {
				edcdpn = strings;
			}

			/**
			 * @param strings
			 */
			public void setEdcmgn(String[] strings) {
				edcmgn = strings;
			}

			/**
			 * @param string
			 */
			public void setEdcnum(String string) {
				edcnum = string;
			}

			/**
			 * @param strings
			 */
			public void setEdcplr(String[] strings) {
				edcplr = strings;
			}

			/**
			 * @param string
			 */
			public void setEdcpon(String string) {
				edcpon = string;
			}

			/**
			 * @param strings
			 */
			public void setEdcqty(String[] strings) {
				edcqty = strings;
			}

			/**
			 * @param string
			 */
			public void setEdcrnm(String string) {
				edcrnm = string;
			}

			/**
			 * @param strings
			 */
			public void setEdcskd(String[] strings) {
				edcskd = strings;
			}

			/**
			 * @param strings
			 */
			public void setEdcsku(String[] strings) {
				edcsku = strings;
			}

			/**
			 * @param strings
			 */
			public void setEdcssq(String[] strings) {
				edcssq = strings;
			}

			/**
			 * @param string
			 */
			public void setEdcstn(String string) {
				edcstn = string;
			}

			/**
			 * @param string
			 */
			public void setEdcstr(String string) {
				edcstr = string;
			}

			/**
			 * @param strings
			 */
			public void setEdcsum(String[] strings) {
				edcsum = strings;
			}

			/**
			 * @param string
			 */
			public void setEdctim(String string) {
				edctim = string;
			}

			/**
			 * @param strings
			 */
			public void setEdcupc(String[] strings) {
				edcupc = strings;
			}

			/**
			 * @param string
			 */
			public void setEdcvdn(String string) {
				edcvdn = string;
			}

			/**
			 * @param string
			 */
			public void setEdcvdr(String string) {
				edcvdr = string;
			}


			/**
			 * @param string
			 */
			public void setSumcub(String string) {
				sumcub = string;
			}

			/**
			 * @param string
			 */
			public void setSumwgh(String string) {
				sumwgh = string;
			}

			/**
			 * @return
			 */
			public String[] getEdcwgh() {
				return edcwgh;
			}

			/**
			 * @param strings
			 */
			public void setEdcwgh(String[] strings) {
				edcwgh = strings;
			}

			/**
			 * @return
			 */
			public String getSenddatetime() {
				return senddatetime;
			}

			/**
			 * @param string
			 */
			public void setSenddatetime(String string) {
				senddatetime = string;
			}

			/**
			 * @return
			 */
			public String getAddrow() {
				return addrow;
			}

			/**
			 * @param string
			 */
			public void setAddrow(String string) {
				addrow = string;
			}

			/**
			 * @return
			 */
			public String[] getEpoddd() {
				return epoddd;
			}

			/**
			 * @param strings
			 */
			public void setEpoddd(String[] strings) {
				epoddd = strings;
			}

			/**
			 * @return
			 */
			public String getEdcshp() {
				return edcshp;
			}

			/**
			 * @param string
			 */
			public void setEdcshp(String string) {
				edcshp = string;
			}

			/**
			 * @return
			 */
			public String[] getEdcddd() {
				return edcddd;
			}

			/**
			 * @param strings
			 */
			public void setEdcddd(String[] strings) {
				edcddd = strings;
			}

		}
