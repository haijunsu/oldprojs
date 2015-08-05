/*
 *
 */
package org.cuny.sensors.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.cuny.sensors.Utils;
import org.cuny.sensors.dao.DatasetDao;
import org.cuny.sensors.dao.RawFileDao;
import org.cuny.sensors.dao.RecordDao;
import org.cuny.sensors.dao.SensorDao;
import org.cuny.sensors.entity.Dataset;
import org.cuny.sensors.entity.RawFile;
import org.cuny.sensors.entity.RawFile.RawFileStatus;
import org.cuny.sensors.entity.Record;
import org.cuny.sensors.entity.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Haijun Su Created date: Jan 4, 2014
 */
@Transactional(readOnly = true)
@Service("dataService")
public class DataServiceImpl implements DataService {

	private SensorDao sensorDao;

	private DatasetDao datasetDao;

	private RecordDao recordDao;

	private RawFileDao rawFileDao;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setSensorDao(SensorDao dao) {
		this.sensorDao = dao;
	}

	@Autowired
	public void setDatasetDao(DatasetDao dao) {
		this.datasetDao = dao;
	}

	@Autowired
	public void setRecordDao(RecordDao dao) {
		this.recordDao = dao;
	}

	@Autowired
	public void setRawFileDao(RawFileDao dao) {
		this.rawFileDao = dao;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.service.DataService#importData(java.lang.String)
	 */
	@SuppressWarnings("resource")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void importData(RawFile rawfile) throws Exception {
		BufferedReader br = null;
		String errors = null;
		try {
			if (rawfile.getStatus() == RawFileStatus.Imported) {
				// already imported
				System.out.println("File has been imported and skip it. "
						+ rawfile.getFilename());
				return;
			}
			if (rawfile.getStatus() == RawFileStatus.Retry) {
				// clean record and dataset. example:
				// delete from tbl_record where dataset_id in (select id from
				// tbl_dataset
				// where raw_file = '/storage/johnjay/ACS 03_North.csv');
				// delete from tbl_dataset where
				// raw_file = '/storage/johnjay/ACS 03_North.csv';

				String cleanRecordSql = "delete from " + RecordDao.TABLE_NAME
						+ " where " + RecordDao.DATASET_ID
						+ " in (select id from " + DatasetDao.TABLE_NAME
						+ " where " + DatasetDao.RAW_FILE + " = ?)";
				System.out.println(cleanRecordSql);
				String cleanDatasetSql = "delete from " + DatasetDao.TABLE_NAME
						+ " where " + DatasetDao.RAW_FILE + " = ?";
				System.out.println(cleanDatasetSql);
				this.jdbcTemplate.update(cleanRecordSql, rawfile.getFilename());
				this.jdbcTemplate
						.update(cleanDatasetSql, rawfile.getFilename());
				rawfile.setStatus(RawFileStatus.Ready);
				this.rawFileDao.update(rawfile);
			}
			if (rawfile.getStatus() != RawFileStatus.Ready) {
				// wrong file status
				System.out.println("File status is wrong and ignore it. "
						+ rawfile.getFilename());
				return;
			}
			rawfile.setStatus(RawFileStatus.Processing);
			this.rawFileDao.update(rawfile);
			System.out.println("Processing file " + rawfile.getFilename()
					+ " . . . ");
			br = new BufferedReader(new FileReader(new File(
					rawfile.getFilename())));
			List<Sensor> sensors = new ArrayList<Sensor>();
			String line = null;
			Dataset dataset = null;
			int datasetNum = 0;
			long recordNum = 0l;
			while ((line = br.readLine()) != null) {
				try {
					// process the line.
					line = line.trim();
					if (line.startsWith("\"Point")) {
						// create sensor
						String name = getValue(line);
						// query data from db
						Sensor sensor = this.sensorDao.get(name);
						if (sensor == null) {
							// sensor is not exist
							sensor = new Sensor();
							sensor.setName(name);
							String[] ssProps = name.split("\\.");
							if (ssProps.length >= 4) {
								sensor.setType(ssProps[1]);
								sensor.setFloor(ssProps[2]);
								sensor.setDevicdId(ssProps[3]);
							}
							sensor.setBuilding(rawfile.getBuilding());
							sensor.setCampus(rawfile.getCampus());
							sensor = this.sensorDao.save(sensor);
						}
						if (!sensors.contains(sensor)) {
							sensors.add(sensor);
						}
						System.out.println("Importing " + sensor.getName()
								+ " ...");
						if (dataset != null) {
							// update count in db
							this.datasetDao.update(dataset);
							datasetNum++;
							recordNum += dataset.getCount();
							dataset = null;
						}
						// reset dataset
						dataset = new Dataset();
						dataset.setSensorId(sensor.getId());
						dataset.setRawfile(rawfile.getFilename());
					} else if (line.startsWith("\"Trend")) {
						if (dataset == null) {
							// error data
							continue;
						}
						String trendEvery = getValue(line);
						// query minute
						String minuteValue = trendEvery.substring(0,
								trendEvery.indexOf(" "));
						dataset.setInterval(Integer.valueOf(minuteValue));

					} else if (line.startsWith("\"Date")) {
						if (dataset == null) {
							// error data
							continue;
						}
						String value = getValue(line);
						String[] timeslots = value.split(" - ");
						dataset.setStartTime(Utils.getDate(timeslots[0]));
						dataset.setEndTime(Utils.getDate(timeslots[1]));
					} else if (line.startsWith("\"Report")) {
						if (dataset == null) {
							// error data
							continue;
						}
						dataset.setTimings(getValue(line));
						// create dataset
						dataset = this.datasetDao.save(dataset);
					} else if (line.split(",").length == 4) {
						if (dataset == null) {
							// error data
							continue;
						}
						// remove quotation marks
						line = line.replaceAll("\"", "");
						String[] cols = line.split(",");
						Record record = new Record();
						record.setDatasetId(dataset.getId());
						record.setInterval(dataset.getInterval());
						record.setSensorId(dataset.getSensorId());
						record.setTime(Utils.getDate(cols[0] + " " + cols[1]));
						// convert ON=0, OFF=1, OCC=0
						if (cols[2].equals("ON") || cols[2].equals("OCC")) {
							cols[2] = "0";
							if (dataset.getNotes() == null) {
								dataset.setNotes("Changed value from " + "'"
										+ cols[2] + "' to '0'. Original line: "
										+ line);
							}
						} else if (cols[2].equals("OFF")) {
							cols[2] = "1";
							if (dataset.getNotes() == null) {
								dataset.setNotes("Changed value from " + "'"
										+ cols[2] + "' to '1'. Original line: "
										+ line);
							}
						}
						record.setValue(Float.valueOf(cols[2]));
						record.setNotes(cols[3]);
						this.recordDao.save(record);
						// update count
						dataset.setCount(dataset.getCount() + 1);
					}
				} catch (Exception e) {
					System.out
							.println("ERROR: " + line + ". " + e.getMessage());
					throw new Exception("ERROR: " + line + ". "
							+ e.getMessage());
				}
			}
			if (dataset != null) {
				// update count in db
				this.datasetDao.update(dataset);
				datasetNum++;
				recordNum += dataset.getCount();
				dataset = null;
			}
			line = null;
			br.close();
			rawfile.setSensorCount(sensors.size());
			rawfile.setDatasetCount(datasetNum);
			rawfile.setRecordCount(recordNum);
			rawfile.setStatus(RawFileStatus.Imported);
			this.rawFileDao.update(rawfile);
			summarySensors(sensors);
			sensors.clear();
			sensors = null;
			// System.out.println(sensor);;
		} catch (FileNotFoundException e) {
			errors = e.getMessage();
			e.printStackTrace();
		} catch (IOException e) {
			errors = e.getMessage();
			e.printStackTrace();
		} catch (Exception e) {
			errors = e.getMessage();
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (br != null)
					br.close();
				if (errors != null) {
					// need check the file status outside
					rawfile.setNotes(errors);
					rawfile.setStatus(RawFileStatus.Failed);
				}
			} catch (Exception e) {

			}
		}

	}

	private void summarySensors(List<Sensor> sensors) throws Exception {
		for (Sensor sensor : sensors) {
			// summary datasets and count
			List<Map<String, Object>> summarys = this.jdbcTemplate
					.queryForList("select " + DatasetDao.RECORD_INTERVAL
							+ ", count(1) as count, sum("
							+ DatasetDao.RECORD_COUNT + ") as sum from "
							+ DatasetDao.TABLE_NAME + " where "
							+ DatasetDao.RECORD_COUNT + "<> 0 and "
							+ DatasetDao.SENSOR_ID + " = ?", sensor.getId());
			long count = 0;
			Long datasetSum = 0L;
			String summary = "";
			String sensorIntervals = "";
			Set<Integer> intervals = new HashSet<Integer>();
			if (summarys != null && summarys.size() > 0) {
				for (Map<String, Object> map : summarys) {
					Integer interval = (Integer) map
							.get(DatasetDao.RECORD_INTERVAL);
					Long datasetCount = (Long) map.get("count");
					Long recordCount = ((BigDecimal) map.get("sum"))
							.longValue();
					intervals.add(interval);
					count += recordCount;
					datasetSum += datasetCount;
					summary += "Inteval " + interval + " - " + datasetCount
							+ " datasets - " + recordCount + " records"
							+ Utils.LINE_SEPARATOR;
				}
			}
			List<Map<String, Object>> emptyDatasetSummarys = this.jdbcTemplate
					.queryForList("select " + DatasetDao.RECORD_INTERVAL
							+ ", count(1) as count from "
							+ DatasetDao.TABLE_NAME + " where "
							+ DatasetDao.RECORD_COUNT + "= 0 and "
							+ DatasetDao.SENSOR_ID + " = ?", sensor.getId());
			if (emptyDatasetSummarys != null && emptyDatasetSummarys.size() > 0) {
				for (Map<String, Object> map : emptyDatasetSummarys) {
					Integer interval = (Integer) map
							.get(DatasetDao.RECORD_INTERVAL);
					if (interval != null) {
						Long datasetCount = (Long) map.get("count");
						intervals.add(interval);
						datasetSum += datasetCount;
						summary += "Inteval " + interval + " - " + datasetCount
								+ " datasets are empty." + Utils.LINE_SEPARATOR;
					}
				}
			}
			summary += "Total " + datasetSum + " datasets.";

			for (Integer interval : intervals) {
				if (!sensorIntervals.equals("")) {
					sensorIntervals += ", ";
				}
				sensorIntervals += interval;
			}
			sensor.setIntervals(sensorIntervals);
			sensor.setDatasetSummary(summary);
			sensor.setCount(count);
			List<Map<String, Object>> recordTimes = this.jdbcTemplate
					.queryForList("select min(" + DatasetDao.START_TIME
							+ ") as startTime, max(" + DatasetDao.END_TIME
							+ ") as endTime from " + DatasetDao.TABLE_NAME
							+ " where " + DatasetDao.SENSOR_ID + " = ?",
							sensor.getId());
			if (recordTimes != null && recordTimes.size() > 0) {
				for (Map<String, Object> map : recordTimes) {
					sensor.setStartTime((Date) map.get("startTime"));
					sensor.setEndTime((Date) map.get("endTime"));
					break;
				}
			}
			System.out.println(sensor);
			this.sensorDao.update(sensor);
		}
	}

	private String getValue(String line) {
		return line.substring(line.indexOf("\",\"") + 3, line.length() - 1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.cuny.sensors.service.DataService#findSensorByFloor(java.lang.String)
	 */
	@Override
	public List<Sensor> findSensorByFloor(String floor) throws Exception {
		return this.sensorDao.findByFloor(floor);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.service.DataService#findAllSensors()
	 */
	@Override
	public List<Sensor> findAllSensors() throws Exception {
		return this.sensorDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.cuny.sensors.service.DataService#findSensorByName(java.lang.String)
	 */
	@Override
	public Sensor findSensorByName(String sensorName) throws Exception {
		return this.sensorDao.get(sensorName);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.service.DataService#findSensorById(long)
	 */
	@Override
	public Sensor findSensorById(long id) throws Exception {
		return this.sensorDao.get(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.service.DataService#findDatasetById(long)
	 */
	@Override
	public Dataset findDatasetById(long id) throws Exception {
		return this.datasetDao.get(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.service.DataService#findDatasetsBySensor(long)
	 */
	@Override
	public List<Dataset> findDatasetsBySensor(long sensorId) throws Exception {
		return this.datasetDao.findBySensor(sensorId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.cuny.sensors.service.DataService#findDatasetsBySensorName(java.lang
	 * .String)
	 */
	@Override
	public List<Dataset> findDatasetsBySensorName(String sensorName)
			throws Exception {
		long sensorId = this.sensorDao.getId(sensorName);
		return this.datasetDao.findBySensor(sensorId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.service.DataService#findRecordByDataset(long)
	 */
	@Override
	public List<Record> findRecordByDataset(long datasetId) throws Exception {
		return this.recordDao.findByDataset(datasetId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.service.DataService#findRecordBySensor(long)
	 */
	@Override
	public List<Record> findRecordBySensor(long sensorId) throws Exception {
		return this.recordDao.findBySensor(sensorId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.cuny.sensors.service.DataService#findRecordBySensorName(java.lang
	 * .String)
	 */
	@Override
	public List<Record> findRecordBySensorName(String sensorName)
			throws Exception {
		long sensorId = this.sensorDao.getId(sensorName);
		return this.recordDao.findBySensor(sensorId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.service.DataService#findRecordBySensor(long,
	 * java.util.Date, java.util.Date)
	 */
	@Override
	public List<Record> findRecordBySensor(long sensorId, Date startTime,
			Date endTime) throws Exception {
		return this.recordDao.findBySensor(sensorId, startTime, endTime);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.cuny.sensors.service.DataService#findRecordBySensor(java.lang.String,
	 * java.util.Date, java.util.Date)
	 */
	@Override
	public List<Record> findRecordBySensor(String sensorName, Date startTime,
			Date endTime) throws Exception {
		long sensorId = this.sensorDao.getId(sensorName);
		return this.recordDao.findBySensor(sensorId, startTime, endTime);
	}
}
