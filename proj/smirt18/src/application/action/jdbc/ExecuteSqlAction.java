/*
 * @(#)ExecuteSqlAction.java  2005-3-22
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.jdbc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import application.action.BaseDispachAction;
import application.dao.MyQuerysDao;
import application.entity.MyQuerys;
import application.helper.BeansLocator;
import framework.exception.CannotFoundRequestParameterException;
import framework.util.StringUtil;
import framework.util.http.RequestUtil;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * </p>
 * 
 * $Revision$
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class ExecuteSqlAction extends BaseDispachAction {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(ExecuteSqlAction.class);

    public ActionForward runSql(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward _forward = mapping.findForward("executePage");
        ActionMessages _messages = new ActionMessages();
        try {
            String _strSqlParam = RequestUtil.getStringParameter(request,
                    "sqls", "");
            if (StringUtil.isBlank(_strSqlParam)) {
                return _forward;
            }
            String[] _strSqls = StringUtil.split(_strSqlParam, ";");
            ExecuteSql _sqlHelper = (ExecuteSql) BeansLocator
                    .getBeanResource("sqlService");
            Object[][] _objects = null;
            int _nloop = _strSqls.length;
            if (_nloop > 1 && StringUtil.isBlank(_strSqls[_nloop - 1])) {
                _nloop--;
            }
            _objects = new Object[_nloop][1];
            request.setAttribute("isSelect", "false");
            if (_nloop > 1) {
                String[] _strings = _sqlHelper.execute(_strSqls);
                for (int i = 0; i < _nloop; i++) {
                    _objects[i][0] = _strings[i];
                }
            } else {
                if (_strSqls[0].toLowerCase().trim().startsWith("select")) {
                    request.setAttribute("isSelect", "true");
                    _objects = populateResult(_sqlHelper.query(_strSqls[0].trim()));
                } else {
                    if (StringUtil.isNotBlank(_strSqls[0])) {
                        _objects = new Object[1][1];
                        _objects[0][0] = _sqlHelper.execute(_strSqls[0].trim());
                    }
                }
            }

            request.setAttribute("results", _objects);
        } catch (Exception e) {
            logger
                    .error(
                            "runSql(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - Uncatched Exception!",
                            e);
            _messages.add("jdbcError", new ActionMessage("errors.code.150", e.getMessage()));
            saveErrors(request, _messages);
        }
        return _forward;
    }

    public ActionForward query(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward _forward = mapping.findForward("resultsPage");
        ActionMessages _messages = new ActionMessages();
        try {
            String _strQueryid = RequestUtil.getRequiredStringParameter(
                    request, "queryid");
            MyQuerysDao _querysDao = (MyQuerysDao) BeansLocator
                    .getBeanResource("querysDao");
            MyQuerys _querys = _querysDao.load(new Long(_strQueryid));
            ExecuteSql _sqlHelper = (ExecuteSql) BeansLocator
                    .getBeanResource("sqlService");
            Object[][] _objects = new Object[1][1];
            request.setAttribute("isSelect", "true");
            _objects = populateResult(_sqlHelper.query(_querys.getQuerySql().trim()));

            request.setAttribute("results", _objects);
            request.setAttribute("queryTitle", _querys.getQueryName());
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "query(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("jdbcError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "query(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - Uncatched Exception!",
                            e);

        }
        return _forward;
    }

    public static Object[][] populateResult(List list) {
        if (list == null || list.isEmpty()) {
            String[][] _strings = new String[1][1];
            _strings[0][0] = "result is empty.";
            return _strings;
         }
        Object[][] _results = new Object[list.size() + 1][];
        Object[] _titles = null;
        int _nRow = 0;
        Iterator _iterator = list.iterator();
        while (_iterator.hasNext()) {
	        HashMap _map = (HashMap)_iterator.next();
	        Iterator _iterator2 = _map.keySet().iterator();
	        if (_titles == null) {
	            _titles = _map.keySet().toArray(new Object[0]);
	            _results[_nRow] = _titles;
	            _nRow ++;
	        }
	        int _nCol = 0;
	        Object[] _objects = new Object[_titles.length];
	        while (_iterator2.hasNext()) {
	            _objects[_nCol] = _map.get(_iterator2.next());
	            if (_objects[_nCol] == null) {
	                _objects[_nCol] = "null";
	            }
	            if (_objects[_nCol] instanceof String) {
	                _objects[_nCol] = StringUtil.abbreviate(_objects[_nCol].toString(), 150);
	            }
	            _nCol ++;
	        }
            _results[_nRow] = _objects;
            _nRow ++;
        }
        return _results;
    }

}