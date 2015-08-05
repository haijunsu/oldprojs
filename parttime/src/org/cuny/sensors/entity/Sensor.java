/*
 *
 */
package org.cuny.sensors.entity;

import java.util.Date;

/**
 *
 * @author Haijun Su Created date: 2013-12-11
 */
public class Sensor {

	private long id;

	private String name;

	private String type;

	private String floor;

	private String devicdId;

	private String building;

	private String campus;

	private String intervals;

	private String datasetSummary;

	private long count;

	private Date startTime;

	private Date endTime;

	private Date updateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getDevicdId() {
		return devicdId;
	}

	public void setDevicdId(String devicdId) {
		this.devicdId = devicdId;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getIntervals() {
		return intervals;
	}

	public void setIntervals(String intervals) {
		this.intervals = intervals;
	}

	public String getDatasetSummary() {
		return datasetSummary;
	}

	public void setDatasetSummary(String datasetSummary) {
		this.datasetSummary = datasetSummary;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Sensor other = (Sensor) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sensor [id=" + id + ", name=" + name + ", type=" + type
				+ ", floor=" + floor + ", devicdId=" + devicdId + ", building="
				+ building + ", campus=" + campus + ", intervals=" + intervals
				+ ", datasetSummary=" + datasetSummary + ", count=" + count
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", updateTime=" + updateTime + "]";
	}



}
