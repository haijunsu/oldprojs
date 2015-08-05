/*
 * @(#)JdbcServiceImpl.java  2005-1-20
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package framework.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.core.CollectionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterDisposer;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.StatementCreatorUtils;

/**
 * <p><b>Description</b></p>
 * <p>provide jdbc datasource</p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class JdbcServiceImpl extends BaseServiceImpl {
    
    private JdbcTemplate m_jdbcTemplate;


    protected JdbcTemplate getJdbcTemplate() {
         return this.m_jdbcTemplate;
    }
    public void setDataSource(DataSource dataSource) {
        this.m_jdbcTemplate = new MyJdbcTempleate(dataSource);
    }
    
    private static class MyJdbcTempleate extends JdbcTemplate {
        MyJdbcTempleate(DataSource dataSource) {
            super(dataSource);
        }
        
    	public List queryForList(String sql) throws DataAccessException {
    		return (List) query(sql, new MyListResultSetExtractor());
    	}
    	public List queryForList(String sql, final Object[] args) throws DataAccessException {
    		return (List) query(sql,
    				new MyArgPreparedStatementSetter(args),
    				new MyListResultSetExtractor());
    	}

    }

	/**
	 * ResultSetExtractor implementation that returns an ArrayList of HashMaps.
	 */
	private static class MyListResultSetExtractor implements ResultSetExtractor {

		public Object extractData(ResultSet rs) throws SQLException {
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			List listOfRows = new ArrayList();
			while (rs.next()) {
				Map mapOfColValues = CollectionFactory.createLinkedMapIfPossible(numberOfColumns);
				for (int i = 1; i <= numberOfColumns; i++) {
					Object o = getJdbcObject(rs, i);
					mapOfColValues.put(rsmd.getColumnName(i), o);
				}
				listOfRows.add(mapOfColValues);
			}
			return listOfRows;
		}
	}
	
	/**
	 * Retrieve a standard JDBC object from a ResultSet using the getObject method.
	 * This method includes a "hack" to get around Oracle returning a non standard
	 * object for their TIMESTAMP datatype.
	 * @param rs is the ResultSet holding the data
	 * @param index is the column index
	 * @return the Object returned
	 */
	private static Object getJdbcObject(ResultSet rs, int index) throws SQLException {
	    try {
		Object obj = rs.getObject(index);
		if (obj != null && obj.getClass().getName().startsWith("oracle.sql.TIMESTAMP")) {
			obj = rs.getTimestamp(index);
		}
		return obj;
	    }catch (Exception e) {
           return null;
        }
	}
	
	/**
	 * Simple adapter for PreparedStatementSetter that applies
	 * a given array of arguments.
	 */
	private static class MyArgPreparedStatementSetter implements PreparedStatementSetter, ParameterDisposer {

		private final Object[] args;

		public MyArgPreparedStatementSetter(Object[] args) {
			this.args = args;
		}

		public void setValues(PreparedStatement ps) throws SQLException {
			if (this.args != null) {
				for (int i = 0; i < this.args.length; i++) {
					StatementCreatorUtils.setParameterValue(ps, i + 1, SqlTypeValue.TYPE_UNKNOWN, null, this.args[i]);
				}
			}
		}

		public void cleanupParameters() {
			StatementCreatorUtils.cleanupParameters(this.args);
		}
	}



}


