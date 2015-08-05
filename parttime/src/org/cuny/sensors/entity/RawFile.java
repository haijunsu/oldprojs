/*
 *
 */
package org.cuny.sensors.entity;

import java.util.Date;

/**
 *
 * @author Haijun Su Created date: Jan 5, 2014
 */
public class RawFile {

	public enum RawFileStatus {
		Ready, Processing, Imported, Failed, Retry
	}

	private long id;

	private long bplRawfileId;

	private String filename;

	private long fileSize;

	private int datasetCount;

	private int sensorCount;

	private long recordCount;

	private String building;

	private String campus;

	private RawFileStatus status;

	private String notes;

	private Date importedTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBplRawfileId() {
		return bplRawfileId;
	}

	public void setBplRawfileId(long bplRawfileId) {
		this.bplRawfileId = bplRawfileId;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public int getDatasetCount() {
		return datasetCount;
	}

	public void setDatasetCount(int datasetCount) {
		this.datasetCount = datasetCount;
	}

	public int getSensorCount() {
		return sensorCount;
	}

	public void setSensorCount(int sensorCount) {
		this.sensorCount = sensorCount;
	}

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
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

	public RawFileStatus getStatus() {
		return status;
	}

	public void setStatus(RawFileStatus status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getImportedTime() {
		return importedTime;
	}

	public void setImportedTime(Date importedTime) {
		this.importedTime = importedTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bplRawfileId ^ (bplRawfileId >>> 32));
		result = prime * result
				+ ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		RawFile other = (RawFile) obj;
		if (bplRawfileId != other.bplRawfileId)
			return false;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RawFile [id=" + id + ", bplRawfileId=" + bplRawfileId
				+ ", filename=" + filename + ", fileSize=" + fileSize
				+ ", datasetCount=" + datasetCount + ", sensorCount="
				+ sensorCount + ", recordCount=" + recordCount + ", building="
				+ building + ", campus=" + campus + ", status=" + status
				+ ", notes=" + notes + ", importedTime=" + importedTime + "]";
	}



}
