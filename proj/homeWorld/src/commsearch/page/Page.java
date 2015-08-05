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
 * <P>BBS�ķ�ҳ����</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class Page  {
	
	//���÷�װBean-com.idn.log.LogWrapper��������־��¼
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(Page.class);
	private PageForm pf = null;     //��ҳ�õ�����
	private long count = 0;               //�ܼ�¼��

	public Page(String s_select,int i_nowPage,int i_nowoncePage,int i_onePage,int i_oncePage, int l_count) {
		super();
		//��ҳ�õ�FormBean
		//		�趨PageForm
		PageForm pf_pf =new PageForm();
		if (i_onePage>0) {
			//�ı�ÿһҳ����ʾ����
			pf_pf.setOnePage(i_onePage);
		}
		if (i_oncePage>0) {
			//�ı�һ����ʾ��ҳ��
			pf_pf.setOncePage(i_oncePage);
		}
		//�趨��ǰҳ
		pf_pf.setNowPage(i_nowPage);
		//�趨��ǰһ����ʾ��ҳ
		pf_pf.setNowOncePage(i_nowoncePage);
		//�趨��ʾ���ݵ�SQL���
		pf_pf.setSelect(s_select );
		setPf(pf_pf);
		//�ܼ�¼��
		setCount(l_count);
	}
	public void init(){
		int i_beginpage,i_endpage;
		long l_temp;
		PageForm pf_pf = getPf();
		//��ǰ��ʼ��ҳ��
		i_beginpage = (pf_pf.getNowOncePage() - 1) * pf_pf.getOncePage() + 1;
		pf_pf.setBeginPage(i_beginpage);
		l_temp =  pf_pf.getNowOncePage() * pf_pf.getOncePage() * pf_pf.getOnePage();
		if (l_temp <= getCount()){
			//��ǰ�ļ�¼���Ѿ�����
			i_endpage = pf_pf.getNowOncePage() * pf_pf.getOncePage();
		} else {
			l_temp = getCount();
			i_endpage = (int)(l_temp / pf_pf.getOnePage());
			if (l_temp!=(i_endpage * pf_pf.getOnePage())){
				i_endpage = i_endpage + 1;
			}
		}
		//��ǰ������ҳ��
		pf_pf.setEndPage(i_endpage);
		//��ҳ��ʾ
		if (pf_pf.getNowOncePage()!=1) {
			pf_pf.setPrewOncePage(Integer.toString(pf_pf.getNowOncePage() - 1));
		}
		//��ҳ��ʾ
		if (getCount()>i_endpage * pf_pf.getOnePage()) {
			pf_pf.setNextOncePage(Integer.toString(pf_pf.getNowOncePage() + 1));
		}		
		//��¼���ķ�Χ
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
