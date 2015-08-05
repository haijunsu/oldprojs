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

import org.cuny.sensors.entity.Sensor;
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
@Component("SensorDao")
public class SensorDaoImpl implements SensorDao {

	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcInsert insertActor;

	private static final class SensorMapper implements RowMapper<Sensor> {

		public Sensor mapRow(ResultSet rs, int rowNum) throws SQLException {
			Sensor sensor = new Sensor();
			sensor.setId(rs.getLong(ID));
			sensor.setName(rs.getString(SENSOR_NAME));
			sensor.setType(rs.getString(SENSOR_TYPE));
			sensor.setFloor(rs.getString(FLOOR));
			sensor.setDevicdId(rs.getString(DEVICE_ID));
			sensor.setBuilding(rs.getString(BUILDING));
			sensor.setCampus(rs.getString(CAMPUS));
			sensor.setCount(rs.getLong(RECORD_COUNT));
			sensor.setDatasetSummary(rs.getString(DATASET_SUMMARY));
			sensor.setIntervals(rs.getString(INTERVALS));
			sensor.setStartTime(rs.getDate(START_TIME));
			sensor.setEndTime(rs.getDate(END_TIME));
			sensor.setUpdateTime(rs.getDate(UPDATE_TIME));
			return sensor;
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
				.usingColumns(SENSOR_NAME, SENSOR_TYPE, FLOOR, DEVICE_ID,
						BUILDING, CAMPUS).usingGeneratedKeyColumns(ID);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.IDao#save(java.lang.Object)
	 */

	@Override
	public Sensor save(Sensor obj) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>(6);
		parameters.put(SENSOR_NAME, obj.getName());
		parameters.put(SENSOR_TYPE, obj.getType());
		parameters.put(FLOOR, obj.getFloor());
		parameters.put(DEVICE_ID, obj.getDevicdId());
		parameters.put(BUILDING, obj.getBuilding());
		parameters.put(CAMPUS, obj.getCampus());
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
	public Sensor update(Sensor obj) throws Exception {
		Sensor old = get(obj.getId());
		this.jdbcTemplate.update("update " + TABLE_NAME + " set " + SENSOR_NAME
				+ " = ?, " + SENSOR_TYPE + " = ?, " + FLOOR + " = ?, "
				+ DEVICE_ID + " = ?, " + BUILDING + " = ?, " + CAMPUS + " = ?,"
				+ DATASET_SUMMARY + " = ?, " + INTERVALS + " = ?, "
				+ RECORD_COUNT + " = ?, " + START_TIME + " = ?," + END_TIME
				+ "  = ? where " + ID + " = ?", obj.getName(), obj.getType(),
				obj.getFloor(), obj.getDevicdId(), obj.getBuilding(),
				obj.getCampus(), obj.getDatasetSummary(), obj.getIntervals(),
				obj.getCount(), obj.getStartTime(), obj.getEndTime(),
				obj.getId());
		return old;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.IDao#remove(java.lang.Object)
	 */
	@Override
	public Sensor remove(Sensor obj) throws Exception {
		Sensor old = get(obj.getId());
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
	public Sensor get(long id) throws Exception {
		try {
			return this.jdbcTemplate.queryForObject("select * from "
					+ TABLE_NAME + " where " + ID + " = ?",
					new Object[] { id }, new SensorMapper());
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
	public List<Sensor> findAll() throws Exception {
		return this.jdbcTemplate.query("select * from " + TABLE_NAME,
				new SensorMapper());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.SensorDao#get(java.lang.String)
	 */
	@Override
	public Sensor get(String name) throws Exception {
		try {
			return this.jdbcTemplate.queryForObject("select * from "
					+ TABLE_NAME + " where " + SENSOR_NAME + " = ?",
					new Object[] { name }, new SensorMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.SensorDao#getId(java.lang.String)
	 */
	@Override
	public long getId(String name) throws Exception {
		return this.jdbcTemplate.queryForObject("select " + ID + " from "
				+ TABLE_NAME + " where " + SENSOR_NAME + " = ?",
				new Object[] { name }, long.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.SensorDao#findByFloor(java.lang.String)
	 */
	@Override
	public List<Sensor> findByFloor(String floor) throws Exception {
		return this.jdbcTemplate.query("select * from " + TABLE_NAME
				+ " where " + FLOOR + " = ?", new Object[] { floor },
				new SensorMapper());
	}

}
