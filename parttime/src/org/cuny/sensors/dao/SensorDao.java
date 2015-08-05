/*
 *
 */
package org.cuny.sensors.dao;

import java.util.List;

import org.cuny.sensors.entity.Sensor;

/**
 *
 * @author Haijun Su
 * Created date: Jan 4, 2014
 */
public interface SensorDao extends IDao<Sensor>{
	public Sensor get(String name) throws Exception;

	public long getId(String name) throws Exception;

	public List<Sensor> findByFloor(String floor) throws Exception;

	public final static String SENSOR_NAME = "sensor_name";
	public final static String SENSOR_TYPE = "sensor_type";
	public final static String FLOOR = "floor";
	public final static String DEVICE_ID = "device_id";
	public final static String BUILDING = "building";
	public final static String CAMPUS = "campus";
	public final static String DATASET_SUMMARY = "dataset_summary";
	public final static String INTERVALS = "intervals";
	public final static String RECORD_COUNT = "record_count";
	public final static String START_TIME = "start_time";
	public final static String END_TIME = "end_time";
	public final static String UPDATE_TIME = "last_update";

	public final static String TABLE_NAME = "tbl_sensor";

}
