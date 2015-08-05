/*
 * 
 */
package org.cuny.sensors;


/**
 * 
 * @author Haijun Su Created date: 2013-12-11
 */
public class SensorReport {
	
	private String name;
	/**
	 * Trend frequence. Unit is minute.
	 */
	private int trendEvery;

	private String dateRange;

	private String reportTimings;

	private int recordCount;
	
	private String reportFileName = null;
	
	private long startTime = 0;
	
	private long endTime = 0;
	
	

	public long getStartTime() {
		return startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public String getReportFileName() {
		return reportFileName;
	}

	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}

	public void increaseCount() {
		recordCount++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTrendEvery() {
		return trendEvery;
	}

	public void setTrendEvery(int trendEvery) {
		this.trendEvery = trendEvery;
	}

	public String getDateRange() {
		return dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
		// convert time to long type
		String[] timeslots = dateRange.split(" - ");
		startTime = Utils.getTime(timeslots[0]);
		endTime = Utils.getTime(timeslots[1]);		
	}

	public String getReportTimings() {
		return reportTimings;
	}

	public void setReportTimings(String reportTimings) {
		this.reportTimings = reportTimings;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateRange == null) ? 0 : dateRange.hashCode());
		result = prime * result + (int) (endTime ^ (endTime >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + recordCount;
		result = prime * result
				+ ((reportFileName == null) ? 0 : reportFileName.hashCode());
		result = prime * result
				+ ((reportTimings == null) ? 0 : reportTimings.hashCode());
		result = prime * result + (int) (startTime ^ (startTime >>> 32));
		result = prime * result + trendEvery;
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
		SensorReport other = (SensorReport) obj;
		if (dateRange == null) {
			if (other.dateRange != null)
				return false;
		} else if (!dateRange.equals(other.dateRange))
			return false;
		if (endTime != other.endTime)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (recordCount != other.recordCount)
			return false;
		if (reportFileName == null) {
			if (other.reportFileName != null)
				return false;
		} else if (!reportFileName.equals(other.reportFileName))
			return false;
		if (reportTimings == null) {
			if (other.reportTimings != null)
				return false;
		} else if (!reportTimings.equals(other.reportTimings))
			return false;
		if (startTime != other.startTime)
			return false;
		if (trendEvery != other.trendEvery)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SensorReport [name=" + name + ", trendEvery=" + trendEvery
				+ ", dateRange=" + dateRange + ", reportTimings="
				+ reportTimings + ", recordCount=" + recordCount
				+ ", reportFileName=" + reportFileName + "]";
	}

}
