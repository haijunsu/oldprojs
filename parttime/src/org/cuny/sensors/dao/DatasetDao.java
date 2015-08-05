/*
 *
 */
package org.cuny.sensors.dao;

import java.util.List;

import org.cuny.sensors.entity.Dataset;

/**
 *
 * @author Haijun Su Created date: Jan 4, 2014
 */
public interface DatasetDao extends IDao<Dataset> {

	public static final String TABLE_NAME = "tbl_dataset";

	public static final String SENSOR_ID = "sensor_id";
	public static final String RAW_FILE = "raw_file";
	public static final String START_TIME = "start_time";
	public static final String END_TIME = "end_time";
	public static final String RECORD_INTERVAL = "record_interval";
	public static final String RECORD_COUNT = "record_count";
	public static final String REPORT_TIMINGS = "report_timings";
	public static final String IMPORT_TIME = "import_time";
	public static final String NOTES = "notes";

	public List<Dataset> findBySensor(long sensorId) throws Exception;

	public List<Dataset> findEmptyDatesetsBySensor(long sensorId)
			throws Exception;

}
