/*
 *
 */
package org.cuny.sensors.dao;

import org.cuny.sensors.entity.RawFile;

/**
 *
 * @author Haijun Su Created date: Jan 5, 2014
 */
public interface BplRawFileDao extends IDao<RawFile> {

	public static final String TABLE_NAME = "tbl_rawfile";

	public static final String FILE_NAME = "file_name";
	public static final String FILE_SIZE = "file_size";
	public static final String DATASET_COUNT = "dataset_count";
	public static final String SENSOR_COUNT = "sensor_count";
	public static final String RECORD_COUNT = "record_count";
	public static final String BUILDING = "building";
	public static final String CAMPUS = "campus";
	public static final String STATUS = "status";
	public static final String NOTES = "notes";
	public static final String IMPORT_TIME = "imported_time";

	public RawFile get(String fileName) throws Exception;

}
