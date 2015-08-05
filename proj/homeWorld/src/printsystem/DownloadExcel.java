/*
 * @(#)SaveToFile.java  2003-9-3
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package printsystem;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.idn.print.PrintController;
import com.idn.print.PrintException;
import com.idn.property.SystemProperties;
import com.idn.upload.Upload;
import com.idn.util.MissingParameterException;

/**
 * <P>将查询内容保存到文件，同时供客户下载</P>
 * 
 * @version 0.1
 * @author navy
 */
public class DownloadExcel extends Action {
	
	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(DownloadExcel.class);
		
	/* （非 Javadoc）
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		org.apache.struts.action.ActionErrors errors =
			new org.apache.struts.action.ActionErrors();
		try {
			PrintForm pf = (PrintForm) form;
			String strQueryid = pf.getQueryid();
			String strSqlWhere = pf.getSqlwhere();
			
			if (strQueryid.trim().equals(""))
				throw new MissingParameterException("没有输入查询ID");
			PrintController pctrl = new PrintController(strQueryid, strSqlWhere);
			pctrl.setSavePath(SystemProperties.TEMP_DIR);
			pctrl.saveExcel();
			
			String strTempFile = pctrl.getSavePath() + pctrl.getFileName();
			Upload upload = new Upload();
			upload.initialize(getServlet().getServletConfig(), request, response);
			upload.downloadFile(strTempFile, strQueryid + ".XLS");
			File file = new File(strTempFile);
			file.delete();
			return null;
		} catch (MissingParameterException mpe){
			log.error(mpe.getMessage(), mpe);
			ActionError error = new ActionError("common.error.miss", "查询ID");
			errors.add(ActionErrors.GLOBAL_ERROR, error);
		} catch (PrintException pe) {
			log.error(pe.getMessage(), pe);
			ActionError error = new ActionError("print.excel.fail");
			errors.add(ActionErrors.GLOBAL_ERROR, error);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ActionError error = new ActionError("common.error.uncatch");
			errors.add(ActionErrors.GLOBAL_ERROR, error);
		}
		saveErrors(request, errors);
		return mapping.findForward("failure");
	}

}
