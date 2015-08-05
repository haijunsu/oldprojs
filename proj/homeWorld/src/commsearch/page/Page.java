/**
 * @(#)Page.java  2003-11-12
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch.page;
//import java.io.IOException;


//import javax.servlet.ServletException;
//import javax.servlet.http.*;

//import org.apache.struts.action.*;
//import org.apache.struts.util.MessageResources;
//import org.apache.commons.beanutils.PropertyUtils;


/**
 * <P>BBS的分页处理</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class Page  {
	
	//利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(Page.class);
	private PageForm pf = null;     //分页用的数据
	private long count = 0;               //总记录数

	public Page(String s_select,int i_nowPage,int i_nowoncePage,int i_onePage,int i_oncePage, int l_count) {
		super();
		//分页用的FormBean
		//		设定PageForm
		PageForm pf_pf =new PageForm();
		if (i_onePage>0) {
			//改变每一页的显示行数
			pf_pf.setOnePage(i_onePage);
		}
		if (i_oncePage>0) {
			//改变一次显示的页数
			pf_pf.setOncePage(i_oncePage);
		}
		//设定当前页
		pf_pf.setNowPage(i_nowPage);
		//设定当前一次显示的页
		pf_pf.setNowOncePage(i_nowoncePage);
		//设定显示数据的SQL语句
		pf_pf.setSelect(s_select );
		setPf(pf_pf);
		//总记录数
		setCount(l_count);
	}
	public void init(){
		int i_beginpage,i_endpage;
		long l_temp;
		PageForm pf_pf = getPf();
		//当前开始的页数
		i_beginpage = (pf_pf.getNowOncePage() - 1) * pf_pf.getOncePage() + 1;
		pf_pf.setBeginPage(i_beginpage);
		l_temp =  pf_pf.getNowOncePage() * pf_pf.getOncePage() * pf_pf.getOnePage();
		if (l_temp <= getCount()){
			//当前的记录数已经够了
			i_endpage = pf_pf.getNowOncePage() * pf_pf.getOncePage();
		} else {
			l_temp = getCount();
			i_endpage = (int)(l_temp / pf_pf.getOnePage());
			if (l_temp!=(i_endpage * pf_pf.getOnePage())){
				i_endpage = i_endpage + 1;
			}
		}
		//当前结束的页数
		pf_pf.setEndPage(i_endpage);
		//上页显示
		if (pf_pf.getNowOncePage()!=1) {
			pf_pf.setPrewOncePage(Integer.toString(pf_pf.getNowOncePage() - 1));
		}
		//下页显示
		if (getCount()>i_endpage * pf_pf.getOnePage()) {
			pf_pf.setNextOncePage(Integer.toString(pf_pf.getNowOncePage() + 1));
		}		
		//记录数的范围
		pf_pf.setNowPageBegin(((pf_pf.getNowPage() - 1) * pf_pf.getOnePage() + 1));
		if (pf_pf.getNowPage() * pf_pf.getOnePage()>getCount()){
			pf_pf.setNowPageEnd((int) getCount());
		} else {
			pf_pf.setNowPageEnd(pf_pf.getNowPage() * pf_pf.getOnePage());
		}
		//pf_pf.setFirstSQL("BETWEEN " + ((pf_pf.getNowPage() - 1) * pf_pf.getOnePage() + 1) + " AND " + (pf_pf.getNowPage() * pf_pf.getOnePage()));
		pf_pf.setFirstSQL("BETWEEN " + pf_pf.getNowPageBegin() + " AND " + pf_pf.getNowPageEnd());
		setPf(pf_pf);
	}


	/**
	 * @return
	 */
	public PageForm getPf() {
		return this.pf;
	}

	/**
	 * @param form
	 */
	public void setPf(PageForm form) {
		this.pf = form;
	}

	/**
	 * @return
	 */
	public long getCount() {
		return this.count;
	}

	/**
	 * @param i
	 */
	public void setCount(long i) {
		this.count = i;
	}

}
