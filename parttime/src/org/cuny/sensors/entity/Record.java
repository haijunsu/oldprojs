/*
 *
 */
package org.cuny.sensors.entity;

import java.util.Date;

/**
 *
 * @author Haijun Su
 * Created date: Jan 4, 2014
 */
public class Record {

	private long id;

	private long sensorId;

	private long datasetId;

	private int interval;

	private Date time;

	private float value;

	private String notes;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSensorId() {
		return sensorId;
	}

	public void setSensorId(long sensorId) {
		this.sensorId = sensorId;
	}

	public long getDatasetId() {
		return datasetId;
	}

	public void setDatasetId(long datasetId) {
		this.datasetId = datasetId;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (datasetId ^ (datasetId >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (sensorId ^ (sensorId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		if (datasetId != other.datasetId)
			return false;
		if (id != other.id)
			return false;
		if (sensorId != other.sensorId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", sensorId=" + sensorId + ", datasetId="
				+ datasetId + ", interval=" + interval + ", time=" + time
				+ ", value=" + value + ", notes=" + notes + "]";
	}

}
