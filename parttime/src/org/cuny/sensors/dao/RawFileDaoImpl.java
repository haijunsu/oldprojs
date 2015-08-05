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

import org.cuny.sensors.entity.RawFile;
import org.cuny.sensors.entity.RawFile.RawFileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

/**
 *
 * @author Haijun Su Created date: Jan 5, 2014
 */
@Component("RawFileDao")
public class RawFileDaoImpl implements RawFileDao {

	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcInsert insertActor;

	private static final class RawFileMapper implements RowMapper<RawFile> {

		/*
		 * (non-Javadoc)
		 *
		 * @see
		 * org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
		 * int)
		 */
		@Override
		public RawFile mapRow(ResultSet rs, int rowNum) throws SQLException {
			RawFile rawfile = new RawFile();
			rawfile.setId(rs.getLong(ID));
			rawfile.setFilename(rs.getString(FILE_NAME));
			rawfile.setFileSize(rs.getLong(FILE_SIZE));
			rawfile.setDatasetCount(rs.getInt(DATASET_COUNT));
			rawfile.setSensorCount(rs.getInt(SENSOR_COUNT));
			rawfile.setRecordCount(rs.getLong(RECORD_COUNT));
			rawfile.setBuilding(rs.getString(BUILDING));
			rawfile.setCampus(rs.getString(CAMPUS));
			rawfile.setStatus(RawFileStatus.valueOf(rs.getString(STATUS)));
			rawfile.setNotes(rs.getString(NOTES));
			rawfile.setImportedTime(rs.getDate(IMPORT_TIME));
			rawfile.setBplRawfileId(rs.getLong(BPL_RAW_FILE_ID));
			return rawfile;
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
				.usingColumns(FILE_NAME, FILE_SIZE, STATUS, BUILDING, CAMPUS)
				.usingGeneratedKeyColumns(ID);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.IDao#save(java.lang.Object)
	 */
	@Override
	public RawFile save(RawFile obj) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>(5);
		parameters.put(FILE_NAME, obj.getFilename());
		parameters.put(FILE_SIZE, obj.getFileSize());
		parameters.put(STATUS, obj.getStatus().toString());
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
	public RawFile update(RawFile obj) throws Exception {
		RawFile old = get(obj.getId());
		this.jdbcTemplate.update("update " + TABLE_NAME + " set " + FILE_NAME
				+ " = ?, " + FILE_SIZE + " = ?, " + DATASET_COUNT + " = ?, "
				+ SENSOR_COUNT + " = ?, " + RECORD_COUNT + " = ?, " + BUILDING
				+ " = ?," + CAMPUS + " = ?," + STATUS + " = ?," + NOTES
				+ "  = ? where " + ID + " = ?", obj.getFilename(), obj
				.getFileSize(), obj.getDatasetCount(), obj.getSensorCount(),
				obj.getRecordCount(), obj.getBuilding(), obj.getCampus(), obj
						.getStatus().toString(), obj.getNotes(), obj.getId());
		return old;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.IDao#remove(java.lang.Object)
	 */
	@Override
	public RawFile remove(RawFile obj) throws Exception {
		RawFile old = get(obj.getId());
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
	public RawFile get(long id) throws Exception {
		try {
			return this.jdbcTemplate.queryForObject("select * from "
					+ TABLE_NAME + " where " + ID + " = ?",
					new Object[] { id }, new RawFileMapper());
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
	public List<RawFile> findAll() throws Exception {
		return this.jdbcTemplate.query("select * from " + TABLE_NAME,
				new RawFileMapper());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.dao.RawFileDao#get(java.lang.String)
	 */
	@Override
	public RawFile get(String fileName) throws Exception {
		try {
			return this.jdbcTemplate.queryForObject("select * from "
					+ TABLE_NAME + " where " + FILE_NAME + " = ?",
					new Object[] { fileName }, new RawFileMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
