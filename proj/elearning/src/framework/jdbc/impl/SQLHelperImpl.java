/*
 * Created on 2005-5-29
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package framework.jdbc.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

import framework.exception.DaoException;
import framework.jdbc.SQLHelper;
import framework.service.impl.JdbcServiceImpl;

/**
 * @author xiongll
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SQLHelperImpl extends JdbcServiceImpl implements SQLHelper {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(SQLHelperImpl.class);

	/* (non-Javadoc)
	 * @see framework.jdbc.ISQLHelper#execute(java.lang.String)
	 */
	public int execute(String strSql) throws DaoException {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("execute(String) - SQL: " + strSql);
            }
            return getJdbcTemplate().update(strSql);
        } catch (Exception e) {
            logger.error("execute(String) - exception", e);
            throw new DaoException(e.getMessage());
         }
	}

	/* (non-Javadoc)
	 * @see framework.jdbc.ISQLHelper#execute(java.lang.String[])
	 */
	public int[] execute(String[] strSql) throws DaoException {
        int[] _returns = new int[strSql.length];
        for (int i = 0; i < _returns.length; i++) {
            try {
                if (logger.isDebugEnabled()) {
                    logger.debug("execute(String[]) - SQL: " + strSql[i]);
                }
                _returns[i] = getJdbcTemplate().update(strSql[i].trim());
            } catch (Exception e) {
                logger.error("execute(String[]) - exception", e);
                throw new DaoException(e.getMessage());
             }
        }
        return _returns;
	}

	/* (non-Javadoc)
	 * @see framework.jdbc.ISQLHelper#query(java.lang.String)
	 */
	public List query(String strSql) throws DaoException {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("query(String) - SQL: " + strSql);
            }
            List _list = getJdbcTemplate().queryForList(strSql);
            return _list;
        } catch (Exception e) {
            logger.error("query(String) - exception", e);
            throw new DaoException(e.getMessage());
         }
	}

    /* (non-Javadoc)
     * @see framework.jdbc.SQLHelper#queryForString(java.lang.String)
     */
    public String queryForString(String strSql) throws DaoException {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("query(String) - SQL: " + strSql);
            }
            String _str = (String) getJdbcTemplate().queryForObject(strSql, String.class);
            return _str;
        } catch (Exception e) {
            logger.error("query(String) - exception", e);
            throw new DaoException(e.getMessage());
         }
    }

}
