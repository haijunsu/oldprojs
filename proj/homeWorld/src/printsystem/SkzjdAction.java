/*
 * @(#)SkzjdAction.java  2003-8-21
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package printsystem;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <P>打印税控装置装机单</P>
 * 
 * @version 0.1
 * @author navy
 */
public class SkzjdAction extends Action {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(SkzjdAction.class);

	/**
	 * 实例化
	 */
	public SkzjdAction() {
		super();
	}

	/* （非 Javadoc）
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
			
		ActionErrors errors = new ActionErrors();
		
		SkzjdForm skzjdForm = (SkzjdForm) form;
		
		if (skzjdForm.getInstId() == null) {
			ActionError error = new ActionError("skzjd.query.no.instid");
			errors.add("SkzjdError", error);
		} else {
			Skzjd skzjd = new Skzjd();
			skzjd.setForm(skzjdForm);
			try {
				skzjd.query();
			} catch (SQLException sqle) {
				log.error(sqle.getMessage());
				ActionError error = new ActionError("skzjd.query.error");
				errors.add("SkzjdError", error);
			} catch (SkzjdException skzjde) {
				log.error(skzjde.getMessage());
			
				ActionError error = new ActionError("skzjd.query.no.result");
				errors.add("SkzjdError", error);
			}
		}
		
		saveErrors(request, errors);
		return mapping.findForward("success");
	}

}
