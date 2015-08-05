/*
 *
 */
package org.cuny.sensors.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.cuny.sensors.entity.Record;
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
@Component
public class RecordDaoImpl implements RecordDao {
	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcInsert insertActor;

	private static final class RecordMapper implements RowMapper<Record> {
		/*
		 * (non-Javadoc)
		 *
		 * @see
		 * org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
		 * int)
		 */
		@Override
		public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
			Record record = new Record();
			record.setId(rs.getLong(ID));
			record.setSensorId(rs.getLong(SENSOR_ID));
			record.setDatasetId(rs.getLong(DATASET_ID));
			record.setInterval(rs.getInt(RECORD_INTERVAL));
			record.setTime(rs.getDate(RECORD_TIME));
			record.setValue(rs.getFloat(VALUE));
			record.setNotes(rs.getString(NOTES));
			return record;
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
				.usingColumns(SENSOR_ID, DATASET_ID, RECORD_INTERVAL,
						RECORD_TIME, VALUE, NOTES).usingGeneratedKeyColumns(ID);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.IDao#save(java.lang.Object)
	 */
	@Override
	public Record save(Record obj) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>(5);
		parameters.put(SENSOR_ID, obj.getSensorId());
		parameters.put(DATASET_ID, obj.getDatasetId());
		parameters.put(RECORD_INTERVAL, obj.getInterval());
		parameters.put(RECORD_TIME, obj.getTime());
		parameters.put(VALUE, obj.getValue());
		parameters.put(NOTES, obj.getNotes());
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
	public Record update(Record obj) throws Exception {
		Record old = get(obj.getId());
		this.jdbcTemplate.update("update " + TABLE_NAME + " set " + SENSOR_ID
				+ " = ?, " + DATASET_ID + " = ?, " + RECORD_INTERVAL + " = ?, "
				+ RECORD_TIME + " = ?, " + VALUE + " = ?, " + NOTES
				+ " = ? where " + ID + " = ?", obj.getSensorId(),
				obj.getDatasetId(), obj.getInterval(), obj.getTime(),
				obj.getValue(), obj.getNotes(), obj.getId());
		return old;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.IDao#remove(java.lang.Object)
	 */
	@Override
	public Record remove(Record obj) throws Exception {
		Record old = get(obj.getId());
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
	public Record get(long id) throws Exception {
		try {
			return this.jdbcTemplate.queryForObject("select * from "
					+ TABLE_NAME + " where " + ID + " = ?",
					new Object[] { id }, new RecordMapper());
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
	public List<Record> findAll() throws Exception {
		return this.jdbcTemplate.query("select * from " + TABLE_NAME,
				new RecordMapper());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.RecordDao#findByDataset(long)
	 */
	@Override
	public List<Record> findByDataset(long datasetId) throws Exception {
		return this.jdbcTemplate.query("select * from " + TABLE_NAME
				+ " where " + DATASET_ID + " = ?", new Object[] { datasetId },
				new RecordMapper());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.RecordDao#findBySensor(long)
	 */
	@Override
	public List<Record> findBySensor(long sensorId) throws Exception {
		return this.jdbcTemplate.query("select * from " + TABLE_NAME
				+ " where " + SENSOR_ID + " = ?", new Object[] { sensorId },
				new RecordMapper());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.RecordDao#findBySensor(long, java.util.Date,
	 * java.util.Date)
	 */
	@Override
	public List<Record> findBySensor(long sensorId, Date startTime, Date endTime)
			throws Exception {
		return this.jdbcTemplate.query("select * from " + TABLE_NAME
				+ " where " + SENSOR_ID + " = ? and " + RECORD_TIME
				+ " >= ? and " + RECORD_TIME + " <= ?", new Object[] {
				sensorId, startTime, endTime }, new RecordMapper());
	}

}
