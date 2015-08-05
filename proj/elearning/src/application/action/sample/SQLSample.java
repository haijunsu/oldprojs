/*
 * Created on 2005-5-30
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.action.sample;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import framework.jdbc.SQLHelper;

import application.action.BaseAction;
import application.helper.BeansLocator;

/**
 * @author xiongll
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SQLSample extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(SQLSample.class);

	/* (non-Javadoc)
	 * @see application.action.BaseAction#performBusiness(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected ActionForward performBusiness(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("performBusiness() - a");
		}

		SQLHelper _helper = (SQLHelper)BeansLocator.getBeanResource("sqlHelper");
		List _list = _helper.query("select * from t_user");
		StringBuffer _sb = new StringBuffer();
		Iterator _iterator = _list.iterator();
		while (_iterator.hasNext()) {
			HashMap _row = (HashMap) _iterator.next();
			Set _set = _row.keySet();
			Iterator _iterator1 = _set.iterator();
			while (_iterator1.hasNext()) {
				Object _key = (Object) _iterator1.next();
				_sb.append(_key).append("=").append(_row.get(_key)).append(";");
			}
			_sb.append("<br>");
		}
		request.setAttribute("message", _sb.toString());
		return mapping.findForward("showmsg");
	}

}
