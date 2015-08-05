/*
 * @(#)CreatetableForm.java  2003-8-4
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package system.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author haizhou
 */
public class CreatetableForm extends ActionForm {
	

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CreatetableForm.class);
	private String currrowshow="";
	private String currrowshowold="";
	private String key="";
	private String count = "";
	private String flag = "";
	private String currrow = "";
	private String currcolumn = "";
	private String page = "1";
	private String id = "";
	private String[] queryid = null;
	private String[] seq = null;
	private String[] fieldid = null;
	private String[] ftypeeshow = null;
	private String[] flength = null;
	private String[] fdigits = null;
	private String[] fheaderc = null;
	private String[] cclass = null;
	private String[] qnamec = null;
	private String[] qnamee = null;
	private String[] fheadere = null;
	private String[] qdefault = null;
	private String[] rowstate = null;
	private String[] qwhere = null;
	private String[] qkattri = null;
	private String[] seqhid = null;
	private String[] ftypeshow = null;
	private String[] ftypeeid= null;
	private String[] ftypeid = null;
	

	/**
	 * 
	 */
	public CreatetableForm() {
		super();
		key="";
		flag = "";
		currrow = "-1";
		currcolumn = "";
		page = "1";
		currrowshow="-1";
		currrowshowold="-1";
	}
	
	/**
		 * @return
		 */
		public String[] getCclass() {
			return cclass;
		}

		/**
		 * @return
		 */
		public String[] getFdigits() {
			return fdigits;
		}

		/**
		 * @return
		 */
		public String[] getFheaderc() {
			return fheaderc;
		}

		/**
		 * @return
		 */
		public String[] getFieldid() {
			return fieldid;
		}

		/**
		 * @return
		 */
		public String[] getFlength() {
			return flength;
		}

		
		/**
		 * @return
		 */
		public String[] getQnamec() {
			return qnamec;
		}

		/**
		 * @return
		 */
		public String[] getQueryid() {
			return queryid;
		}

		/**
		 * @return
		 */
		public String[] getSeq() {
			return seq;
		}

		/**
		 * @param strings
		 */
		public void setCclass(String[] strings) {
			cclass = strings;
		}

		/**
		 * @param strings
		 */
		public void setFdigits(String[] strings) {
			fdigits = strings;
		}

		/**
		 * @param strings
		 */
		public void setFheaderc(String[] strings) {
			fheaderc = strings;
		}

		/**
		 * @param strings
		 */
		public void setFieldid(String[] strings) {
			fieldid = strings;
		}

		/**
		 * @param strings
		 */
		public void setFlength(String[] strings) {
			flength = strings;
		}

		

		/**
		 * @param strings
		 */
		public void setQnamec(String[] strings) {
			qnamec = strings;
		}

		/**
		 * @param strings
		 */
		public void setQueryid(String[] strings) {
			queryid = strings;
		}

		/**
		 * @param strings
		 */
		public void setSeq(String[] strings) {
			seq = strings;
		}
	
		/* (non-Javadoc)
		 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
		 */
		public void reset(ActionMapping mapping, HttpServletRequest request) {
			flag = "";
			currrow = "0";
			currcolumn = "";
			page = "1";
			currrowshow="-1";
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
		 * @param string
		 */
		public void setFlag(String string) {
			flag = string;
		}

		/**
		 * @return
		 */
		public String getCount() {
			return count;
		}

		/**
		 * @return
		 */
		public String getCurrcolumn() {
			return currcolumn;
		}

		/**
		 * @return
		 */
		public String getCurrrow() {
			return currrow;
		}

		/**
		 * @return
		 */
		public String getCurrrowshow() {
			return currrowshow;
		}

		/**
		 * @return
		 */
		public String getCurrrowshowold() {
			return currrowshowold;
		}

		/**
		 * @return
		 */
		public String getId() {
			return id;
		}

		/**
		 * @return
		 */
		public String getKey() {
			return key;
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
		public void setCount(String string) {
			count = string;
		}

		/**
		 * @param string
		 */
		public void setCurrcolumn(String string) {
			currcolumn = string;
		}

		/**
		 * @param string
		 */
		public void setCurrrow(String string) {
			currrow = string;
		}

		/**
		 * @param string
		 */
		public void setCurrrowshow(String string) {
			currrowshow = string;
		}

		/**
		 * @param string
		 */
		public void setCurrrowshowold(String string) {
			currrowshowold = string;
		}

		/**
		 * @param string
		 */
		public void setId(String string) {
			id = string;
		}

		/**
		 * @param string
		 */
		public void setKey(String string) {
			key = string;
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
	public static com.idn.log.LogWrapper getLog() {
		return log;
	}

	/**
	 * @param wrapper
	 */
	public static void setLog(com.idn.log.LogWrapper wrapper) {
		log = wrapper;
	}

	/**
	 * @return
	 */
	public String[] getFheadere() {
		return fheadere;
	}

	/**
	 * @return
	 */
	public String[] getQdefault() {
		return qdefault;
	}

	/**
	 * @param strings
	 */
	public void setFheadere(String[] strings) {
		fheadere = strings;
	}

	/**
	 * @param strings
	 */
	public void setQdefault(String[] strings) {
		qdefault = strings;
	}

	/**
	 * @return
	 */
	public String[] getRowstate() {
		return rowstate;
	}

	/**
	 * @param strings
	 */
	public void setRowstate(String[] strings) {
		rowstate = strings;
	}

	/**
	 * @return
	 */
	public String[] getQnamee() {
		return qnamee;
	}

	/**
	 * @param strings
	 */
	public void setQnamee(String[] strings) {
		qnamee = strings;
	}

	/**
	 * @return
	 */
	public String[] getQwhere() {
		return qwhere;
	}

	/**
	 * @param strings
	 */
	public void setQwhere(String[] strings) {
		qwhere = strings;
	}

	/**
	 * @return
	 */
	public String[] getQkattri() {
		return qkattri;
	}

	/**
	 * @param strings
	 */
	public void setQkattri(String[] strings) {
		qkattri = strings;
	}

	/**
	 * @return
	 */
	public String[] getSeqhid() {
		return seqhid;
	}

	/**
	 * @param strings
	 */
	public void setSeqhid(String[] strings) {
		seqhid = strings;
	}

	
	

	/**
	 * @return
	 */
	public String[] getFtypeid() {
		return ftypeid;
	}

	/**
	 * @return
	 */
	public String[] getFtypeshow() {
		return ftypeshow;
	}

	/**
	 * @param strings
	 */
	public void setFtypeid(String[] strings) {
		ftypeid = strings;
	}

	/**
	 * @param strings
	 */
	public void setFtypeshow(String[] strings) {
		ftypeshow = strings;
	}

	/**
	 * @return
	 */
	public String[] getFtypeeid() {
		return ftypeeid;
	}

	/**
	 * @return
	 */
	public String[] getFtypeeshow() {
		return ftypeeshow;
	}

	/**
	 * @param strings
	 */
	public void setFtypeeid(String[] strings) {
		ftypeeid = strings;
	}

	/**
	 * @param strings
	 */
	public void setFtypeeshow(String[] strings) {
		ftypeeshow = strings;
	}

}
