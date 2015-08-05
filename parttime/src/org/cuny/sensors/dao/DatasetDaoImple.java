/*
 *
 */
package org.cuny.sensors.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.cuny.sensors.entity.Dataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

/**
 *
 * @author Haijun Su Created date: Jan 4, 2014
 */
@Component("DatasetDao")
public class DatasetDaoImple implements DatasetDao {
	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcInsert insertActor;

	private static final class DatasetMapper implements RowMapper<Dataset> {
		/*
		 * (non-Javadoc)
		 *
		 * @see
		 * org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
		 * int)
		 */
		@Override
		public Dataset mapRow(ResultSet rs, int rowNum) throws SQLException {
			Dataset dataset = new Dataset();
			dataset.setId(rs.getLong(ID));
			dataset.setSensorId(rs.getLong(SENSOR_ID));
			dataset.setRawfile(rs.getString(RAW_FILE));
			dataset.setStartTime(rs.getDate(START_TIME));
			dataset.setEndTime(rs.getDate(END_TIME));
			dataset.setInterval(rs.getInt(RECORD_INTERVAL));
			dataset.setCount(rs.getInt(RECORD_COUNT));
			dataset.setTimings(rs.getString(REPORT_TIMINGS));
			dataset.setImportTime(rs.getDate(IMPORT_TIME));
			dataset.setNotes(rs.getString(NOTES));
			return dataset;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.IDao#setDataSource(javax.sql.DataSource)
	 */
	@Override
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.insertActor = new SimpleJdbcInsert(dataSource)
				.withTableName(TABLE_NAME)
				.usingColumns(SENSOR_ID, RAW_FILE, START_TIME, END_TIME,
						RECORD_INTERVAL, RECORD_COUNT, REPORT_TIMINGS)
				.usingGeneratedKeyColumns(ID);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.IDao#save(java.lang.Object)
	 */
	@Override
	public Dataset save(Dataset obj) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>(7);
		parameters.put(SENSOR_ID, obj.getSensorId());
		parameters.put(RAW_FILE, obj.getRawfile());
		parameters.put(START_TIME, obj.getStartTime());
		parameters.put(END_TIME, obj.getEndTime());
		parameters.put(RECORD_INTERVAL, obj.getInterval());
		parameters.put(RECORD_COUNT, obj.getCount());
		parameters.put(REPORT_TIMINGS, obj.getTimings());
		Number newId = insertActor.executeAndReturnKey(parameters);
		obj.setId(newId.longValue());
		return obj;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.IDao#update(java.lang.Object)
	 */
	@Override
	public Dataset update(Dataset obj) throws Exception {
		Dataset old = get(obj.getId());
		this.jdbcTemplate.update("update " + TABLE_NAME + " set " + SENSOR_ID
				+ " = ?, " + RAW_FILE + " = ?, " + START_TIME + " = ?, "
				+ END_TIME + " = ?, " + RECORD_INTERVAL + " = ?, "
				+ RECORD_COUNT + " = ?," + REPORT_TIMINGS + " = ?, " + NOTES + " = ? where " + ID
				+ " = ?", obj.getSensorId(), obj.getRawfile(),
				obj.getStartTime(), obj.getEndTime(), obj.getInterval(),
				obj.getCount(), obj.getTimings(), obj.getNotes(), obj.getId());
		return old;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.IDao#remove(java.lang.Object)
	 */
	@Override
	public Dataset remove(Dataset obj) throws Exception {
		Dataset old = get(obj.getId());
		this.jdbcTemplate.update("delete from " + TABLE_NAME + " where " + ID
				+ " = ?", obj.getId());
		return old;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.IDao#remove(long)
	 */
	@Override
	public void remove(long id) throws Exception {
		this.jdbcTemplate.update("delete from " + TABLE_NAME + " where " + ID
				+ " = ?", id);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.IDao#get(long)
	 */
	@Override
	public Dataset get(long id) throws Exception {
		try {
			return this.jdbcTemplate.queryForObject("select * from "
					+ TABLE_NAME + " where " + ID + " = ?",
					new Object[] { id }, new DatasetMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.IDao#findAll()
	 */
	@Override
	public List<Dataset> findAll() throws Exception {
		return this.jdbcTemplate.query("select * from " + TABLE_NAME,
				new DatasetMapper());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.DatasetDao#findBySensor(long)
	 */
	@Override
	public List<Dataset> findBySensor(long sensorId) throws Exception {
		return this.jdbcTemplate.query("select * from " + TABLE_NAME
				+ " where " + SENSOR_ID + " = ?", new Object[] { sensorId },
				new DatasetMapper());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.DatasetDao#findEmptyDatesetBySensor(long)
	 */
	@Override
	public List<Dataset> findEmptyDatesetsBySensor(long sensorId)
			throws Exception {
		return this.jdbcTemplate.query("select * from " + TABLE_NAME
				+ " where " + RECORD_COUNT + " = 0 and " + SENSOR_ID + " = ?",
				new Object[] { sensorId }, new DatasetMapper());
	}

}
