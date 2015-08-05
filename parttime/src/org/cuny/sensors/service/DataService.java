/*
 *
 */
package org.cuny.sensors.service;

import java.util.Date;
import java.util.List;

import org.cuny.sensors.entity.Dataset;
import org.cuny.sensors.entity.RawFile;
import org.cuny.sensors.entity.Record;
import org.cuny.sensors.entity.Sensor;

/**
 *
 * @author Haijun Su Created date: Jan 4, 2014
 */

public interface DataService {

	public void importData(RawFile rawfile) throws Exception;

	public List<Sensor> findSensorByFloor(String floor) throws Exception;

	public List<Sensor> findAllSensors() throws Exception;

	public Sensor findSensorByName(String sensorName) throws Exception;

	public Sensor findSensorById(long id) throws Exception;

	public Dataset findDatasetById(long id) throws Exception;

	public List<Dataset> findDatasetsBySensor(long sensorId) throws Exception;

	public List<Dataset> findDatasetsBySensorName(String sensorName)
			throws Exception;

	public List<Record> findRecordByDataset(long datasetId) throws Exception;

	public List<Record> findRecordBySensor(long sensorId) throws Exception;

	public List<Record> findRecordBySensorName(String sensorName)
			throws Exception;
	public List<Record> findRecordBySensor(long sensorId, Date startTime, Date endTime)
			throws Exception;
	public List<Record> findRecordBySensor(String sensorName, Date startTime, Date endTime)
			throws Exception;

}
