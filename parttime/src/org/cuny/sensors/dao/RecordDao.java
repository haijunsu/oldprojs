/*
 *
 */
package org.cuny.sensors.dao;

import java.util.Date;
import java.util.List;

import org.cuny.sensors.entity.Record;

/**
 *
 * @author Haijun Su Created date: Jan 4, 2014
 */
public interface RecordDao extends IDao<Record> {

	public static final String TABLE_NAME = "tbl_record";

	public static final String SENSOR_ID = "sensor_id";
	public static final String DATASET_ID = "dataset_id";
	public static final String RECORD_TIME = "record_time";
	public static final String RECORD_INTERVAL = "record_interval";
	public static final String VALUE = "value";
	public static final String NOTES = "notes";

	public List<Record> findByDataset(long datasetId) throws Exception;

	public List<Record> findBySensor(long sensorId) throws Exception;

	public List<Record> findBySensor(long sensorId, Date startTime, Date endTime)
			throws Exception;

}
