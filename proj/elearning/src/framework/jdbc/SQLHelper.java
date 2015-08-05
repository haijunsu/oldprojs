/*
 * Created on 2005-5-29
 */
package framework.jdbc;

import java.util.List;

import framework.exception.DaoException;

/**
 * @author xiongll
 *
 */
public interface SQLHelper {

	public int execute(String strSql) throws DaoException;
    public int[] execute(String[] strSql) throws DaoException;
    public List query(String strSql) throws DaoException;
    public String queryForString(String strSql) throws DaoException;

}
