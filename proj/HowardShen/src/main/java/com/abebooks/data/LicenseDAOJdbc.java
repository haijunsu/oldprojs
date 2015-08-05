package com.abebooks.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.abebooks.data.License.Type;

/**
 * JDBC specific implementation of the LicenseDAO. Transforms database results
 * into POJOs using Spring-JDBC
 * <p/>
 * AbeBooks Sample File
 */
public class LicenseDAOJdbc implements LicenseDAO {
	private SimpleJdbcTemplate jdbcTemplate;

	private static String SELECT_SQL = "SELECT name, type, text from license";

	@SuppressWarnings("unchecked")
	@Override
	public Collection<License> getLicenses() {
		List query = jdbcTemplate.query(SELECT_SQL, new RowMapper<License>() {

			public License mapRow(ResultSet rs, int arg1) throws SQLException {
				Type typeLicense = null;
				String typeStr = rs.getString(2);
				if (Type.FILE.toString().equals(typeStr)) {
					typeLicense = Type.FILE;
				} else if (Type.URL.toString().equals(typeStr)) {
					typeLicense = Type.URL;
				} else {
					typeLicense = Type.INLINE;
				}
				return new License(rs.getString(1), typeLicense, rs
						.getString(3));
			}

		});
		return query;
		// return Collections.emptyList(); // FIXME- implement me
	}

	/**
	 * Dependency injection of a required JDBC DataSource
	 *
	 * @param datasource
	 *            the JDBC datasource to use in loading POJOs
	 */
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate = new SimpleJdbcTemplate(datasource);
	}
}
