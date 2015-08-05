package com.abebooks.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/**
 * JDBC specific implementation of the TermDAO. Transforms database results into
 * POJOs using Spring-JDBC
 * <p/>
 * AbeBooks Sample File
 */
public class TermDAOJdbc implements TermDAO {
	private SimpleJdbcTemplate jdbcTemplate;
	private static String SELECT_SQL = "SELECT key from term";

	@SuppressWarnings("unchecked")
	public Collection<String> getTerms() {
		List query = jdbcTemplate.query(SELECT_SQL, new RowMapper<String>() {

			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString(1);
			}

		});
		return query;
		//        return Collections.emptyList(); // FIXME implement me

	}

	/**
	 * Dependency injection of a required JDBC DataSource
	 *
	 * @param datasource
	 *            the JDBC datasource to use in loading POJOs
	 */
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate = new SimpleJdbcTemplate(datasource);
		       // FIXME implement me

	}
}
