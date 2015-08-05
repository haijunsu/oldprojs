/*
 * @(#)homewordErthdrForm.java  2004-2-17
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
public class homeworldPrintErthdrForm extends ActionForm {
				
	private String ertstr="";//商店
	private String ertvnd="";//供货商
	private String ertnum="";//返厂单号
	private String ertstn="";//店名
	private String ertnam="";//供货商名
	private String ertvra="";//验货单号
	private String ertsa1="";//商店地址1
	private String ertad1="";//供货商地址１
	private String ertaut="";//授权人
	private String ertsa2="";//商店地址2
	private String ertad2="";//供货商地址２
	private String ertvwd="";//
	private String ertsa3="";//商店地址3
	private String ertad3="";//供货商地址３
	private String ertsdt="";//发货日期
	private String ertszp="";//邮编
	private String ertcnt="";//国家
	private String ertscn="";//国家
	private String ertsmg="";//商店经理
	private String ertacn="";//供货商联系人
	private String ertsph="";//商店电话
	private String ertphn="";//供货商电话
	private String ertins="";//特殊指令
	private String ertrsn="";//返厂原因
	private String ertsfx="";//门市传真
	private String ertfax="";//厂商传真
	private String ertshb="";//发货者
	private String ertcar="";// 运输者
	private String ertcom="";// 备注
	private String ertreq="";//  REQUESTED BY
	private String ertcdt="";//生成日期
	private String ertctm="";//生成时间
	private String ertseq="";//序号
	private String ertsts="";//状态
	
	private String ertsct="";//商店城市
	private String ertvct="";//供货商城市
	private String ertvzp="";//供货商邮编

	private String selectwhere="";
	private String queryid="";
	
	
	private String[] ertstsmx =null;	//状态
	private String[] ertsku =null;	
	private String[] ertskd =null;	
	private String[] ertupc =null;	
	private String[] ertmfg =null;	
	private String[] ertbum =null;	
	private String[] ertsum =null;	
	private String[] ertcst =null;	
	private String[] ertshq =null;
	private String[] ertvdn =null;
	
	
	private String[] oertstsmx =null;	
	private String[] oertsku =null;	
	private String[] oertskd =null;	
	private String[] oertupc =null;	
	private String[] oertmfg =null;	
	private String[] oertbum =null;	
	private String[] oertsum =null;	
	private String[] oertcst =null;	
	private String[] oertshq =null;
	private String[] oertvdn =null;
	
	private String endnew ="";  //尾是否为新页

	private String pagerow ="";  //普通的行数
	private String onerow ="";  //首页的行数
	
	private String onlyone ="";  //是否只有一页
	
		
	private String sumcst  ="";  //尾是否为新页
	private String sumshq ="";  //尾是否为新页
	
	
	public homeworldPrintErthdrForm() {
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
	public String getErtacn() {
		return ertacn;
	}

	/**
	 * @return
	 */
	public String getErtad1() {
		return ertad1;
	}

	/**
	 * @return
	 */
	public String getErtad2() {
		return ertad2;
	}

	/**
	 * @return
	 */
	public String getErtad3() {
		return ertad3;
	}

	/**
	 * @return
	 */
	public String getErtaut() {
		return ertaut;
	}

	/**
	 * @return
	 */
	public String[] getErtbum() {
		return ertbum;
	}

	/**
	 * @return
	 */
	public String getErtcar() {
		return ertcar;
	}

	/**
	 * @return
	 */
	public String getErtcdt() {
		return ertcdt;
	}

	/**
	 * @return
	 */
	public String getErtcnt() {
		return ertcnt;
	}

	/**
	 * @return
	 */
	public String getErtcom() {
		return ertcom;
	}

	/**
	 * @return
	 */
	public String[] getErtcst() {
		return ertcst;
	}

	/**
	 * @return
	 */
	public String getErtctm() {
		return ertctm;
	}

	/**
	 * @return
	 */
	public String getErtins() {
		return ertins;
	}

	/**
	 * @return
	 */
	public String[] getErtmfg() {
		return ertmfg;
	}

	/**
	 * @return
	 */
	public String getErtnam() {
		return ertnam;
	}

	/**
	 * @return
	 */
	public String getErtnum() {
		return ertnum;
	}

	/**
	 * @return
	 */
	public String getErtphn() {
		return ertphn;
	}

	/**
	 * @return
	 */
	public String getErtreq() {
		return ertreq;
	}

	/**
	 * @return
	 */
	public String getErtrsn() {
		return ertrsn;
	}

	/**
	 * @return
	 */
	public String getErtsa1() {
		return ertsa1;
	}

	/**
	 * @return
	 */
	public String getErtsa2() {
		return ertsa2;
	}

	/**
	 * @return
	 */
	public String getErtsa3() {
		return ertsa3;
	}

	/**
	 * @return
	 */
	public String getErtscn() {
		return ertscn;
	}

	/**
	 * @return
	 */
	public String getErtsdt() {
		return ertsdt;
	}

	/**
	 * @return
	 */
	public String getErtseq() {
		return ertseq;
	}

	/**
	 * @return
	 */
	public String getErtshb() {
		return ertshb;
	}

	/**
	 * @return
	 */
	public String[] getErtshq() {
		return ertshq;
	}

	/**
	 * @return
	 */
	public String[] getErtskd() {
		return ertskd;
	}

	/**
	 * @return
	 */
	public String[] getErtsku() {
		return ertsku;
	}

	/**
	 * @return
	 */
	public String getErtsmg() {
		return ertsmg;
	}

	/**
	 * @return
	 */
	public String getErtsph() {
		return ertsph;
	}

	/**
	 * @return
	 */
	public String getErtstn() {
		return ertstn;
	}

	/**
	 * @return
	 */
	public String getErtstr() {
		return ertstr;
	}

	/**
	 * @return
	 */
	public String getErtsts() {
		return ertsts;
	}

	/**
	 * @return
	 */
	public String[] getErtstsmx() {
		return ertstsmx;
	}

	/**
	 * @return
	 */
	public String[] getErtsum() {
		return ertsum;
	}

	/**
	 * @return
	 */
	public String getErtszp() {
		return ertszp;
	}

	/**
	 * @return
	 */
	public String[] getErtupc() {
		return ertupc;
	}

	/**
	 * @return
	 */
	public String getErtvnd() {
		return ertvnd;
	}

	/**
	 * @return
	 */
	public String getErtvra() {
		return ertvra;
	}

	/**
	 * @return
	 */
	public String getErtvwd() {
		return ertvwd;
	}

	/**
	 * @param string
	 */
	public void setErtacn(String string) {
		ertacn = string;
	}

	/**
	 * @param string
	 */
	public void setErtad1(String string) {
		ertad1 = string;
	}

	/**
	 * @param string
	 */
	public void setErtad2(String string) {
		ertad2 = string;
	}

	/**
	 * @param string
	 */
	public void setErtad3(String string) {
		ertad3 = string;
	}

	/**
	 * @param string
	 */
	public void setErtaut(String string) {
		ertaut = string;
	}

	/**
	 * @param strings
	 */
	public void setErtbum(String[] strings) {
		ertbum = strings;
	}

	/**
	 * @param string
	 */
	public void setErtcar(String string) {
		ertcar = string;
	}

	/**
	 * @param string
	 */
	public void setErtcdt(String string) {
		ertcdt = string;
	}

	/**
	 * @param string
	 */
	public void setErtcnt(String string) {
		ertcnt = string;
	}

	/**
	 * @param string
	 */
	public void setErtcom(String string) {
		ertcom = string;
	}

	/**
	 * @param strings
	 */
	public void setErtcst(String[] strings) {
		ertcst = strings;
	}

	/**
	 * @param string
	 */
	public void setErtctm(String string) {
		ertctm = string;
	}

	/**
	 * @param string
	 */
	public void setErtins(String string) {
		ertins = string;
	}

	/**
	 * @param strings
	 */
	public void setErtmfg(String[] strings) {
		ertmfg = strings;
	}

	/**
	 * @param string
	 */
	public void setErtnam(String string) {
		ertnam = string;
	}

	/**
	 * @param string
	 */
	public void setErtnum(String string) {
		ertnum = string;
	}

	/**
	 * @param string
	 */
	public void setErtphn(String string) {
		ertphn = string;
	}

	/**
	 * @param string
	 */
	public void setErtreq(String string) {
		ertreq = string;
	}

	/**
	 * @param string
	 */
	public void setErtrsn(String string) {
		ertrsn = string;
	}

	/**
	 * @param string
	 */
	public void setErtsa1(String string) {
		ertsa1 = string;
	}

	/**
	 * @param string
	 */
	public void setErtsa2(String string) {
		ertsa2 = string;
	}

	/**
	 * @param string
	 */
	public void setErtsa3(String string) {
		ertsa3 = string;
	}

	/**
	 * @param string
	 */
	public void setErtscn(String string) {
		ertscn = string;
	}

	/**
	 * @param string
	 */
	public void setErtsdt(String string) {
		ertsdt = string;
	}

	/**
	 * @param string
	 */
	public void setErtseq(String string) {
		ertseq = string;
	}

	/**
	 * @param string
	 */
	public void setErtshb(String string) {
		ertshb = string;
	}

	/**
	 * @param strings
	 */
	public void setErtshq(String[] strings) {
		ertshq = strings;
	}

	/**
	 * @param strings
	 */
	public void setErtskd(String[] strings) {
		ertskd = strings;
	}

	/**
	 * @param strings
	 */
	public void setErtsku(String[] strings) {
		ertsku = strings;
	}

	/**
	 * @param string
	 */
	public void setErtsmg(String string) {
		ertsmg = string;
	}

	/**
	 * @param string
	 */
	public void setErtsph(String string) {
		ertsph = string;
	}

	/**
	 * @param string
	 */
	public void setErtstn(String string) {
		ertstn = string;
	}

	/**
	 * @param string
	 */
	public void setErtstr(String string) {
		ertstr = string;
	}

	/**
	 * @param string
	 */
	public void setErtsts(String string) {
		ertsts = string;
	}

	/**
	 * @param strings
	 */
	public void setErtstsmx(String[] strings) {
		ertstsmx = strings;
	}

	/**
	 * @param strings
	 */
	public void setErtsum(String[] strings) {
		ertsum = strings;
	}

	/**
	 * @param string
	 */
	public void setErtszp(String string) {
		ertszp = string;
	}

	/**
	 * @param strings
	 */
	public void setErtupc(String[] strings) {
		ertupc = strings;
	}

	/**
	 * @param string
	 */
	public void setErtvnd(String string) {
		ertvnd = string;
	}

	/**
	 * @param string
	 */
	public void setErtvra(String string) {
		ertvra = string;
	}

	/**
	 * @param string
	 */
	public void setErtvwd(String string) {
		ertvwd = string;
	}

	/**
	 * @return
	 */
	public String getErtfax() {
		return ertfax;
	}

	/**
	 * @return
	 */
	public String getErtsfx() {
		return ertsfx;
	}

	/**
	 * @param string
	 */
	public void setErtfax(String string) {
		ertfax = string;
	}

	/**
	 * @param string
	 */
	public void setErtsfx(String string) {
		ertsfx = string;
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
	public String getErtsct() {
		return ertsct;
	}

	/**
	 * @return
	 */
	public String getErtvct() {
		return ertvct;
	}

	/**
	 * @return
	 */
	public String getErtvzp() {
		return ertvzp;
	}

	/**
	 * @param string
	 */
	public void setErtsct(String string) {
		ertsct = string;
	}

	/**
	 * @param string
	 */
	public void setErtvct(String string) {
		ertvct = string;
	}

	/**
	 * @param string
	 */
	public void setErtvzp(String string) {
		ertvzp = string;
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
	 * @return
	 */
	public String getSumcst() {
		return sumcst;
	}

	/**
	 * @return
	 */
	public String getSumshq() {
		return sumshq;
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
	 * @param string
	 */
	public void setSumcst(String string) {
		sumcst = string;
	}

	/**
	 * @param string
	 */
	public void setSumshq(String string) {
		sumshq = string;
	}

	/**
	 * @return
	 */
	public String[] getErtvdn() {
		return ertvdn;
	}

	/**
	 * @param strings
	 */
	public void setErtvdn(String[] strings) {
		ertvdn = strings;
	}

	/**
	 * @return
	 */
	public String[] getOertbum() {
		return oertbum;
	}

	/**
	 * @return
	 */
	public String[] getOertcst() {
		return oertcst;
	}

	/**
	 * @return
	 */
	public String[] getOertmfg() {
		return oertmfg;
	}

	/**
	 * @return
	 */
	public String[] getOertshq() {
		return oertshq;
	}

	/**
	 * @return
	 */
	public String[] getOertskd() {
		return oertskd;
	}

	/**
	 * @return
	 */
	public String[] getOertsku() {
		return oertsku;
	}

	/**
	 * @return
	 */
	public String[] getOertstsmx() {
		return oertstsmx;
	}

	/**
	 * @return
	 */
	public String[] getOertsum() {
		return oertsum;
	}

	/**
	 * @return
	 */
	public String[] getOertupc() {
		return oertupc;
	}

	/**
	 * @return
	 */
	public String[] getOertvdn() {
		return oertvdn;
	}

	/**
	 * @param strings
	 */
	public void setOertbum(String[] strings) {
		oertbum = strings;
	}

	/**
	 * @param strings
	 */
	public void setOertcst(String[] strings) {
		oertcst = strings;
	}

	/**
	 * @param strings
	 */
	public void setOertmfg(String[] strings) {
		oertmfg = strings;
	}

	/**
	 * @param strings
	 */
	public void setOertshq(String[] strings) {
		oertshq = strings;
	}

	/**
	 * @param strings
	 */
	public void setOertskd(String[] strings) {
		oertskd = strings;
	}

	/**
	 * @param strings
	 */
	public void setOertsku(String[] strings) {
		oertsku = strings;
	}

	/**
	 * @param strings
	 */
	public void setOertstsmx(String[] strings) {
		oertstsmx = strings;
	}

	/**
	 * @param strings
	 */
	public void setOertsum(String[] strings) {
		oertsum = strings;
	}

	/**
	 * @param strings
	 */
	public void setOertupc(String[] strings) {
		oertupc = strings;
	}

	/**
	 * @param strings
	 */
	public void setOertvdn(String[] strings) {
		oertvdn = strings;
	}

	/**
	 * @return
	 */
	public String getOnerow() {
		return onerow;
	}

	/**
	 * @param string
	 */
	public void setOnerow(String string) {
		onerow = string;
	}

	/**
	 * @return
	 */
	public String getOnlyone() {
		return onlyone;
	}

	/**
	 * @param string
	 */
	public void setOnlyone(String string) {
		onlyone = string;
	}

}
