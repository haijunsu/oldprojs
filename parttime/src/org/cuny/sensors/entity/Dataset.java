/*
 *
 */
package org.cuny.sensors.entity;

import java.util.Date;

/**
 *
 * @author Haijun Su Created date: Jan 4, 2014
 */
public class Dataset {
	private long id;
	private long sensorId;
	private String rawfile;
	private Date startTime;
	private Date endTime;
	private int interval;
	private int count = 0;
	private String timings;
	private Date importTime;
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

	public String getRawfile() {
		return rawfile;
	}

	public void setRawfile(String rawfile) {
		this.rawfile = rawfile;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getTimings() {
		return timings;
	}

	public void setTimings(String times) {
		this.timings = times;
	}

	public Date getImportTime() {
		return importTime;
	}

	public void setImportTime(Date importTime) {
		this.importTime = importTime;
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
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + interval;
		result = prime * result + ((rawfile == null) ? 0 : rawfile.hashCode());
		result = prime * result + (int) (sensorId ^ (sensorId >>> 32));
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
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
		Dataset other = (Dataset) obj;
		if (id != other.id)
			return false;
		if (interval != other.interval)
			return false;
		if (rawfile == null) {
			if (other.rawfile != null)
				return false;
		} else if (!rawfile.equals(other.rawfile))
			return false;
		if (sensorId != other.sensorId)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Dataset [id=" + id + ", sensorId=" + sensorId + ", rawfile="
				+ rawfile + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", interval=" + interval + ", count=" + count + ", timings="
				+ timings + ", importTime=" + importTime + ", notes=" + notes
				+ "]";
	}

}
