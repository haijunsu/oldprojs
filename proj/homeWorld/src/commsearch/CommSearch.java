/*
 * @(#)CommSearch.java  2003-07-10
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;
import org.apache.commons.beanutils.PropertyUtils;

//import com.idn.util.*;
//import com.idn.property.*;
//import com.idn.property.PropertyManager;
//import com.idn.sql.*;

/**
 * <P>���ò�ѯ��������</P>
 * 
 * @version 1.0
 * @author XIAOAI
 * 	//�����ű������
 *	cq = new CommQuery(s_queryid);
 *	//��ʼ�����ű�
 *	//�趨����
 *	cq.init();
 *	CommQueryData cqd = new CommQueryData(cq);
 *	cqd.setActiondo(s_actiondo)
 *	cqd.init(s_where);
 *	setData(cqd.getDatashow());
 */
public class CommSearch extends Action {

	//���÷�װBean-com.idn.log.LogWrapper��������־��¼
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommSearch.class);
	
	/**
	 * Constructor for QueryAction.
	 */		
	public CommSearch() {
		super();
	}
	/**
	 * ���ò�ѯ����EXECUTE����
	 * 
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @exception IOException, ServletException 
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException 
		{
		Locale locale = null;             //ȡ�����ļ���
		MessageResources messages = null; //ȡ�����ļ���
		String username = null;            //��¼�û���
		CommTools ct = new CommTools();    //���ò�ѯ������
		CommQuery cq = null;               //���ò�ѯ������

		int i_fori,i_forj,i_return;
		int i_count,i_rowcount,i_colcount;
		String s_statu = "",s_flag,s_page,s_queryid,s_queryiddis,s_actiondo,s_searchid;
		String s_two = "",s_nowpage,s_nowoncepage;
		String fieldslist,fieldschlist,fieldstype,fieldskey,tablename,actionname;
		String s_where,s_selectwhere,s_begintitle;
		String[] s_fields=null,s_fieldsch=null,s_fieldskey=null,s_fieldstype=null,s_title=null;
		
		ActionForward forward = new ActionForward();

		log.info("���ò�ѯ����:��ʼִ�й��ò�ѯ�������Ƴ���Execute");
		log.info("��¼��IP��ַ��:"+request.getRemoteAddr());
		HttpSession session = request.getSession();
		username = (String)session.getAttribute("userid");
		if (username !=null) log.info("��¼������:"+username);
		locale = getLocale(request);
		messages = getResources(request);
		try {
			//ȡ�������
			//1:���ò�ѯ����,2:���ò�ѯ
			s_searchid = ct.getParameter(request,"searchid");
			if (s_searchid ==null ) s_searchid = "1";
			if (s_searchid.equals("")) s_searchid = "1";
			PropertyUtils.setSimpleProperty(form, "searchid",s_searchid);
			//queryid=XGDJZB&page=2&searchid=2
			//1:��ѯ����;2:��ѯ���б�ҳ��
			s_page = ct.getParameter(request,"page");
			if (s_page ==null ) s_page = "1";
			if (s_page.equals("")) s_page = "1";
			s_flag = ct.getParameter(request,"flag");
			s_actiondo = ct.getParameter(request,"actiondo");
			if (s_flag.equals("search")) {
				s_page = "2";
			}
			s_two = ct.getParameter(request,"two");
			if (s_two.equals("")) s_two = "1";
			
			s_selectwhere=ct.getParameter(request,"selectwhere");
			if (s_searchid ==null ) s_selectwhere = "";
			
			log.debug("searchid:"+s_searchid+";page:"+s_page);
			
			//���ű��queryid			
			s_queryid = ct.getParameter(request,"queryid");
			s_queryiddis = ct.getParameter(request,"queryiddis");
			if (s_page.equals("2")){
				//��ʾ
				if (!s_queryiddis.equals("")) {
					
					s_queryid = s_queryiddis;
					log.debug("��ʾ����:" + s_queryid);
				}
			}
			//��ѯ����
			if (s_queryid.equals("")){
				//����
				fieldslist = ct.getParameter(request,"fieldslist");
				fieldstype = ct.getParameter(request,"fieldstype");
				fieldschlist = ct.getParameter(request,"fieldschlist");
				fieldskey = ct.getParameter(request,"fieldskey");
				tablename = ct.getParameter(request,"tablename");
				actionname = ct.getParameter(request,"actionname");
				//�ֶε�ID�������Ǵ����
				s_fields = ct.addArray(ct.DivString(fieldslist),"��ѯ���б�");
				s_fieldstype = ct.addArray(ct.DivString(fieldstype),"");
				s_fieldsch = ct.addArray(ct.DivString(fieldschlist),"");
				s_fieldskey = ct.DivString(fieldskey);				
			} else {
				//�����ű������
				cq = new CommQuery(s_queryid);
				//��ʼ�����ű�
				//�趨����
				cq.init();

				//ȡ����
				s_fields = ct.addArray(cq.getFields(),"��ѯ���б�");
				s_fieldstype = ct.addArray(cq.getFieldstype(),"");
				s_fieldsch = ct.addArray(cq.getFieldsch(),"");
				s_fieldskey = cq.getFieldskey();
				s_title = cq.getFieldscol("QNAMEC");
//				String a = null;
//				a = cq.getFieldscodelist();		
//				log.debug(a);		
			}
			s_nowpage = ct.getParameter(request,"nowpage");
			if (s_nowpage.equals("")) s_nowpage = "0";
			s_nowoncepage = ct.getParameter(request,"nowoncepage");
			if (s_nowoncepage.equals("")) s_nowoncepage = "1";
			
			//дFORMBEAN����ֵ
			if (s_page.equals("1")){
				setFormBeanSearch(form,s_fields,s_fieldstype,s_fieldsch,s_fieldskey,s_page,s_queryid,s_queryiddis,s_actiondo,s_title[0],s_two,s_nowpage,s_nowoncepage);
			} else {
				s_begintitle="";
				CommNewTitle cnt = new CommNewTitle();
				if (s_selectwhere.equals("")){
				
					s_begintitle = cnt.makeBeginTitle(s_queryid,request);
					s_where = makeWhere(request);
				} else {
					s_where = s_selectwhere;
				}
				
				log.debug("�������:"+s_where);
				//��ѯ���б�ҳ��
				//setFormBeanWhere(form,request);
				//setFormBeanSearch(form,s_fields,s_fieldstype,s_fieldsch,s_fieldskey,s_page,s_queryid,s_queryiddis,s_actiondo,s_title[0],s_two,s_nowpage,s_nowoncepage);
				setFormBeanList(form,s_page,s_where,cq,s_queryid,s_actiondo,s_begintitle,"",Integer.parseInt(s_nowpage),Integer.parseInt(s_nowoncepage),s_queryiddis,s_searchid,s_two);
			}

//�����������õ�
//			String[][] aaa=null;
//			String[] bbb= null;
//			CommActQuery caq = new CommActQuery("SALARYIN","SALASERI='2003076100'");
//			aaa = caq.getData();
//			bbb = caq.getFields("FIELDID");

			//�ɹ�ת��ҳ��
			forward = mapping.findForward("success");
			log.info("���ò�ѯ����:���ò�ѯ�������Ƴ���Execute����ɹ�����");
		} catch (Exception e) {
			//�쳣����
			log.error("���ò�ѯ����:δ֪�쳣����");
			e.printStackTrace();
			system.action.ErrorValue ev = new system.action.ErrorValue(); 
			ev.setError("CommSearch","execute","���ò�ѯ�����쳣����:" + e.getMessage());
			request.setAttribute("siev",ev);
			forward = mapping.findForward("failure");
			log.info("���ò�ѯ����:���ò�ѯ�������Ƴ���Execute����ʧ�ܻ���");
			return forward;
		}
		return forward;
	}
		/**
		 * ���������WHERE
		 * 
		 * @param HttpServletRequest request
		 * @return stirng
		 */	
		public String makeWhere(HttpServletRequest request){
			
			CommTools ct = new CommTools();    //���ò�ѯ������
			String s_one="",s_two="",s_three="";
			int i_seq=0;
			String s_fields[] = null;        //��ѯ�ֶ�
			String s_fieldstype[] = null;    //��ѯ�ֶε�����
			
			String s_fields1id = null;     //��ѯ�ֶ�1
			String s_condition1id = null;  //��ѯ����1
			String s_thing1 = null;      //��ѯ������1
	
			String s_fields2id = null;     //��ѯ�ֶ�2  
			String s_condition2id = null;  //��ѯ����2
			String s_thing2 = null;      //��ѯ������2
	
			String s_fields3id = null;     //��ѯ�ֶ�3  
			String s_condition3id = null;  //��ѯ����3  
			String s_thing3 = null;      //��ѯ������3
	
			String s_relation1id = null;    //��ѯ��ϵ1
			String s_relation2id = null;    //��ѯ��ϵ2
			//�ֶ���Ϣ
			s_fields = ct.getParameterValues(request,"fieldsid");
			s_fieldstype = ct.getParameterValues(request,"fieldstype");

			//���������
			s_fields1id = ct.getParameter(request,"fields1id");
			s_condition1id = ct.getParameter(request,"condition1id");
			s_thing1 = ct.getParameter(request,"thing1");
			s_fields2id = ct.getParameter(request,"fields2id");
			s_condition2id = ct.getParameter(request,"condition2id");
			s_thing2 = ct.getParameter(request,"thing2");
			s_fields3id = ct.getParameter(request,"fields3id");
			s_condition3id = ct.getParameter(request,"condition3id");
			s_thing3 = ct.getParameter(request,"thing3");
			
			s_relation1id = ct.getParameter(request,"relation1id");
			s_relation2id = ct.getParameter(request,"relation2id");
			
			s_one = makeOne(s_fields,s_fieldstype,s_fields1id,s_condition1id,s_thing1);
			s_two = makeOne(s_fields,s_fieldstype,s_fields2id,s_condition2id,s_thing2);
			s_three = makeOne(s_fields,s_fieldstype,s_fields3id,s_condition3id,s_thing3);

			if (s_one.equals("") && s_two.equals("") && s_three.equals("")){
				//������
				return ""; 
			}
			if ((!s_two.equals("")) && (!s_three.equals(""))){
				//������������
				s_two = " ( " + s_two + " " + s_relation2id + " " + s_three + " ) ";
			} else{ 
				if (s_two.equals("")){
					s_two = s_three;
				} 
			}
			if ((!s_one.equals("")) && (!s_two.equals(""))){
				s_one = " ( " + s_one + " " + s_relation1id + " " + s_two +" )";
			} else {
				if (s_one.equals("")){
					s_one = s_two;
				}
			}
			return s_one;
		}	

	/**
	 * ���������һ��С��WHERE
	 * 
	 * @param String[] ȫ�����ֶ�
	 * @param String[] ȫ�����ֶε�����
	 * @param String �ֶ�
	 * @param String ����
	 * @param String ����
	 * @return stirng WHERE���
	 */	
	public String makeOne(String[] s_fields,String[] s_fieldstype,String s_fieldsid,
							String s_conditionid,String s_thing){
		
		CommTools ct = new CommTools();    //���ò�ѯ������
		int i_seq = -1;
		String s_one;
		if (s_thing.trim().equals("")) return "";
		i_seq = ct.searchInArray(s_fields,s_fieldsid);
		if (i_seq>-1){
			//�ҵ�������
			if (s_fieldstype[i_seq].trim().toUpperCase().equals("CHAR") ||
			s_fieldstype[i_seq].trim().toUpperCase().equals("VARCHAR") ||
			s_fieldstype[i_seq].trim().toUpperCase().equals("VARCHAR2") ){
				//�ַ��ʹ���
				if (s_conditionid.equals("LIKE")){
					s_thing = "'%" + s_thing + "%'";
				} else {
					s_thing = "'" + s_thing + "'";
				}
			}
			if (s_fieldstype[i_seq].trim().toUpperCase().equals("INTEGER") ||
			s_fieldstype[i_seq].trim().toUpperCase().equals("DECIMAL") ||
			s_fieldstype[i_seq].trim().toUpperCase().equals("SMALLINT") ||
			s_fieldstype[i_seq].trim().toUpperCase().equals("DOUBLE") ){
				//�����͵Ĵ���
				if (ct.isNumber(s_thing)==-1){
					//����������,�ֶ�ȴ��!Ҫ������Ĵ���
					log.debug("���������͵�����:"+s_thing);
					return "";    
				}
			}
			if (s_fieldstype[i_seq].trim().toUpperCase().equals("DATE")){
				//�����͵Ĵ���
					s_thing = "'" + s_thing + "'";
			}
		}
		s_one = " (" + s_fieldsid + " " + s_conditionid + " " + s_thing +") ";
		return s_one;
	}


	/**
	 * ���ò�ѯ���б���ֵ�ĳ���
	 * 
	 * @param ActionForm form 
	 * @param String ��ʾҳ��
	 * @param String WHERE��� 
	 * @param CommQuery ���ű�Ľ��
	 * @return stirng �������1:�ɹ�;-1ʧ��
	 */			
	public String setFormBeanWhere(ActionForm form,HttpServletRequest request) {

		
		CommTools ct = new CommTools();    //���ò�ѯ������
		String s_fields[] = null;        //��ѯ�ֶ�
		String s_fieldstype[] = null;    //��ѯ�ֶε�����
			
		String s_fields1id = null;     //��ѯ�ֶ�1
		String s_condition1id = null;  //��ѯ����1
		String s_thing1 = null;      //��ѯ������1
	
		String s_fields2id = null;     //��ѯ�ֶ�2  
		String s_condition2id = null;  //��ѯ����2
		String s_thing2 = null;      //��ѯ������2
	
		String s_fields3id = null;     //��ѯ�ֶ�3  
		String s_condition3id = null;  //��ѯ����3  
		String s_thing3 = null;      //��ѯ������3
	
		String s_relation1id = null;    //��ѯ��ϵ1
		String s_relation2id = null;    //��ѯ��ϵ2
		//�ֶ���Ϣ
		s_fields = ct.getParameterValues(request,"fieldsid");
		s_fieldstype = ct.getParameterValues(request,"fieldstype");

		//���������
		s_fields1id = ct.getParameter(request,"fields1id");
		s_condition1id = ct.getParameter(request,"condition1id");
		s_thing1 = ct.getParameter(request,"thing1");
		s_fields2id = ct.getParameter(request,"fields2id");
		s_condition2id = ct.getParameter(request,"condition2id");
		s_thing2 = ct.getParameter(request,"thing2");
		s_fields3id = ct.getParameter(request,"fields3id");
		s_condition3id = ct.getParameter(request,"condition3id");
		s_thing3 = ct.getParameter(request,"thing3");
			
		s_relation1id = ct.getParameter(request,"relation1id");
		s_relation2id = ct.getParameter(request,"relation2id");


		try {
			PropertyUtils.setSimpleProperty(form, "fieldsid",s_fields);
			PropertyUtils.setSimpleProperty(form, "fieldstype",s_fieldstype);
			
			PropertyUtils.setSimpleProperty(form, "fields1id",s_fields1id);
			PropertyUtils.setSimpleProperty(form, "condition1id",s_condition1id);
			PropertyUtils.setSimpleProperty(form, "thing1",s_thing1);
			PropertyUtils.setSimpleProperty(form, "fields2id",s_fields2id);
			PropertyUtils.setSimpleProperty(form, "condition2id",s_condition2id);
			PropertyUtils.setSimpleProperty(form, "thing2",s_thing2);
			PropertyUtils.setSimpleProperty(form, "fields3id",s_fields3id);
			PropertyUtils.setSimpleProperty(form, "condition3id",s_condition3id);
			PropertyUtils.setSimpleProperty(form, "thing3",s_thing3);
			
			PropertyUtils.setSimpleProperty(form, "relation1id",s_relation1id);
			PropertyUtils.setSimpleProperty(form, "relation2id",s_relation2id);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * ���ò�ѯ���б���ֵ�ĳ���
	 * 
	 * @param ActionForm form 
	 * @param String ��ʾҳ��
	 * @param String WHERE��� 
	 * @param CommQuery ���ű�Ľ��
	 * @return stirng �������1:�ɹ�;-1ʧ��
	 */			
	public String setFormBeanList(
	ActionForm form,String s_page,
	String s_where,CommQuery cq,
	String s_queryid,String s_actiondo,
	String s_begintitle,String s_endtitle,
	int i_nowpage,int i_nowoncepage,
	String s_queryiddis,String s_searchid,String s_two){
		int i_count = 0;                //�ܵļ�¼��
		String s_return = "-1";
		String[][] s_data=null;
		String[][] s_datashow=null;
		String[] s_datakey=null,s_datatitle=null;
		String s_prewOncePage=null;     //����ʾҳ
		String s_nextOncePage=null;      //����ʾҳ

		CommQueryData cqd = new CommQueryData(cq);
		CommNewTitle cnt = new CommNewTitle();
		commsearch.page.PageList[] pagelist = null;
		cqd.setActiondo(s_actiondo);
		//�Ƿ�����ҳ����
		//��REPLACE
		commsearch.util.CommSearchWhere csw = new commsearch.util.CommSearchWhere(); 
		s_where = csw.whereReplace(s_where);
		if (i_nowpage==0 ){
			//����ҳ
			
			cqd.init(s_where,"","");
		} else {
			//��ҳ
			i_count = cqd.makeSelectCount(s_where);
			log.debug("��¼��Ϊ��" + i_count);
			commsearch.page.PageForm pf = null;
			commsearch.page.Page p = new commsearch.page.Page("",i_nowpage,i_nowoncepage,0,0,i_count);
			p.init();
			pf = p.getPf();
			log.debug("��ǰҳ��" + pf.getNowPage());
			log.debug("��ǰ��ʾҳ��" + pf.getNowOncePage());
			log.debug("��ǰ��ʼҳ��" + pf.getBeginPage());
			log.debug("��ǰ����ҳ��" + pf.getEndPage());
			log.debug("һҳ����ʾ��¼��" + pf.getOnePage());
			log.debug("��ǰҳ����ʾҳ����" + pf.getOncePage());
			log.debug("��Χ��" + pf.getFirstSQL());
			//���ı����ӵĴ���

			pagelist = new commsearch.page.PageList[pf.getEndPage() - pf.getBeginPage() + 1];
			log.debug(pagelist.length);
			for (int i_fori=0;i_fori<pagelist.length;i_fori++){
				pagelist[i_fori] = new commsearch.page.PageList();
				//pagelist[i_fori].list = (String)("../" + "commsearch.do?queryid="+s_queryid+"&page="+s_page+"&searchid="+s_searchid+"&two="+s_two+"&nowpage="+Integer.toString(pf.getBeginPage() + i_fori)+"&nowoncepage="+Integer.toString(i_nowoncepage));
				pagelist[i_fori].list = Integer.toString(pf.getBeginPage() + i_fori);
				pagelist[i_fori].number = Integer.toString(pf.getBeginPage() + i_fori); 
				log.debug("��ҳ��"+pagelist[i_fori]);
			}
			if (pf.getPrewOncePage()!=null){
				//s_prewOncePage = "../" + "commsearch.do?queryid="+s_queryid+"&page="+s_page+"&searchid="+s_searchid+"&two="+s_two+"&nowpage="+((Integer.parseInt(pf.getPrewOncePage()) - 1 ) * pf.getOncePage() + 1 )+"&nowoncepage="+pf.getPrewOncePage();
				s_prewOncePage = Integer.toString((Integer.parseInt(pf.getPrewOncePage()) - 1 ) * pf.getOncePage() + 1 );
			}
			if (pf.getNextOncePage()!=null){			
				//s_nextOncePage = "../" + "commsearch.do?queryid="+s_queryid+"&page="+s_page+"&searchid="+s_searchid+"&two="+s_two+"&nowpage="+((Integer.parseInt(pf.getNextOncePage()) - 1 ) * pf.getOncePage() + 1 )+"&nowoncepage="+pf.getNextOncePage();
				s_nextOncePage = Integer.toString((Integer.parseInt(pf.getNextOncePage()) - 1 ) * pf.getOncePage() + 1) ;
			}
			//����ʾ����
			cqd.init(s_where,"",pf.getFirstSQL());	
			
		}
		
		
		
		s_data = cqd.getData();
		s_datashow = cqd.getDatashow();
		s_datatitle = cqd.getDatatitle();
		s_datakey = cqd.getDatakey();
		
		s_endtitle = cnt.makeEndTitle(s_queryid,s_data);
		try{
			PropertyUtils.setSimpleProperty(form, "prewOncePage",s_prewOncePage);
			PropertyUtils.setSimpleProperty(form, "nextOncePage",s_nextOncePage);
			PropertyUtils.setSimpleProperty(form, "pagelist",pagelist);
			PropertyUtils.setSimpleProperty(form, "page",s_page);
			PropertyUtils.setSimpleProperty(form, "data",s_datashow);
			PropertyUtils.setSimpleProperty(form, "datatitle",s_datatitle);
			PropertyUtils.setSimpleProperty(form, "datakey",s_datakey);
			if (s_datashow != null){
				log.debug("��¼����"+Integer.toString(s_datashow.length)+"��");
				if (s_begintitle.trim().equals("")){
					s_begintitle = "��ǰ����" + Integer.toString(s_datashow.length) + "����¼" ;
				} else {
					
				}
				PropertyUtils.setSimpleProperty(form, "rowcount",s_begintitle);
				
			}
			PropertyUtils.setSimpleProperty(form, "title",cq.getQuerysValue(0,"QNAMEC"));
			PropertyUtils.setSimpleProperty(form, "endtitle",s_endtitle);
			s_return = "1";		
		} catch (Exception e){
			e.printStackTrace();
			log.debug("���ò�ѯ����:setFormBeanList�����쳣����");
		}

		return s_return;
	}
	/**
	 * ���ò�ѯ������ֵ�ĳ���
	 * 
	 * @param ActionForm form 
	 * @param String[] �ֶ�ID
	 * @param String[] �ֶ�ID������
	 * @param String[] �������KEY
	 * @param String   ��ǰ��ʾҳ��
	 * @param String   ��һ������
	 * @return stirng �������1:�ɹ�;-1ʧ��
	 */		
	public String setFormBeanSearch(ActionForm form,
	String[] s_fields,String[] s_fieldstype,
	String[] s_fieldsch,String[] s_fieldskey,
	String s_page,String s_queryid,
	String s_queryiddis,String s_actiondo,
	String s_title ,String s_two,
	String s_nowpage,String s_nowoncepage){
		CommTools ct = new CommTools();    //���ò�ѯ������
		String s_return;
		s_return = "";
		String[] s_condition = null;        //������ID
		String[] s_conditionch = null;      //����������
		String[] s_relation = null;         //��ϵ��ID
		String[] s_relationch = null;       //��ϵ������
		try{
			PropertyUtils.setSimpleProperty(form, "page",s_page);
			PropertyUtils.setSimpleProperty(form, "queryid",s_queryid);
			PropertyUtils.setSimpleProperty(form, "queryiddis",s_queryiddis);
			PropertyUtils.setSimpleProperty(form, "actiondo",s_actiondo);
			PropertyUtils.setSimpleProperty(form, "fieldsid",s_fields);
			PropertyUtils.setSimpleProperty(form, "fieldstype",s_fieldstype);
			PropertyUtils.setSimpleProperty(form, "fieldsshow",s_fieldsch);
			PropertyUtils.setSimpleProperty(form, "fieldskey",s_fieldskey);
			
			PropertyUtils.setSimpleProperty(form, "nowpage",s_nowpage);
			PropertyUtils.setSimpleProperty(form, "nowoncepage",s_nowoncepage);
			s_condition = ct.getCondition();
			s_conditionch = ct.getConditionch();
			s_relation = ct.getRelation();
			s_relationch = ct.getRelationch();
			PropertyUtils.setSimpleProperty(form, "title",s_title);
			
			PropertyUtils.setSimpleProperty(form, "conditionid",s_condition);
			PropertyUtils.setSimpleProperty(form, "conditionshow",s_conditionch);
			PropertyUtils.setSimpleProperty(form, "relationid",s_relation);
			PropertyUtils.setSimpleProperty(form, "relationshow",s_relationch);
			//���ֶε����ݸ�ֵ
	
			PropertyUtils.setSimpleProperty(form, "fields1id",s_fields[1]);
			PropertyUtils.setSimpleProperty(form, "fields1show",s_fieldsch[1]);
			PropertyUtils.setSimpleProperty(form, "condition1id",s_condition[1]);
			PropertyUtils.setSimpleProperty(form, "condition1show",s_conditionch[1]);
			PropertyUtils.setSimpleProperty(form, "thing1","");
			
			if (s_two.equals("1")){
				PropertyUtils.setSimpleProperty(form, "fields2id",s_fields[1]);
				PropertyUtils.setSimpleProperty(form, "fields2show",s_fieldsch[1]);
			} 
			if (s_two.equals("2")){
				PropertyUtils.setSimpleProperty(form, "fields2id",s_fields[2]);
				PropertyUtils.setSimpleProperty(form, "fields2show",s_fieldsch[2]);				
			}
			
			PropertyUtils.setSimpleProperty(form, "condition2id",s_condition[5]);
			PropertyUtils.setSimpleProperty(form, "condition2show",s_conditionch[5]);
			PropertyUtils.setSimpleProperty(form, "thing2","");
			if (s_two.equals("1")){
				PropertyUtils.setSimpleProperty(form, "fields3id",s_fields[1]);
				PropertyUtils.setSimpleProperty(form, "fields3show",s_fieldsch[1]);
			}
			if (s_two.equals("2")){
				PropertyUtils.setSimpleProperty(form, "fields3id",s_fields[2]);
				PropertyUtils.setSimpleProperty(form, "fields3show",s_fieldsch[2]);
			}
			PropertyUtils.setSimpleProperty(form, "condition3id",s_condition[6]);
			PropertyUtils.setSimpleProperty(form, "condition3show",s_conditionch[6]);
			PropertyUtils.setSimpleProperty(form, "thing3","");
			
			PropertyUtils.setSimpleProperty(form, "relation1id",s_relation[1]);
			PropertyUtils.setSimpleProperty(form, "relation1show",s_relationch[1]);
			PropertyUtils.setSimpleProperty(form, "relation2id",s_relation[1]);
			PropertyUtils.setSimpleProperty(form, "relation2show",s_relationch[1]);
			PropertyUtils.setSimpleProperty(form, "data",null);
			s_return = "1";
		} catch (Exception e){
			e.printStackTrace();
			log.debug("���ò�ѯ����:setFormBeanSearch�����쳣����");
			s_return = "-1";
		}
		return s_return;
	}
}