/*
 *
 */
package org.cuny.sensors.entity;

import java.util.Date;

/**
 *
 * @author Haijun Su Created date: Jan 5, 2014
 */
public class BplRawFile {

	public enum BplRawFileStatus {
		Ready, Downloading, Downloaded, Imported, DownloadFailed, NeedRetry, ImportFailed
	}

	private long id;

	private long bplId;

	private String directory;

	private String filename;

	private long fileSize;

	private int datasetCount;

	private String building;

	private String campus;

	private BplRawFileStatus status;

	private String notes;

	private Date importedTime;

	private Date downloadedTime;

	private Date enteredTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBplId() {
		return bplId;
	}

	public void setBplId(long bplId) {
		this.bplId = bplId;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
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

	public BplRawFileStatus getStatus() {
		return status;
	}

	public void setStatus(BplRawFileStatus status) {
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

	public Date getDownloadedTime() {
		return downloadedTime;
	}

	public void setDownloadedTime(Date downloadedTime) {
		this.downloadedTime = downloadedTime;
	}

	public Date getEnteredTime() {
		return enteredTime;
	}

	public void setEnteredTime(Date enteredTime) {
		this.enteredTime = enteredTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bplId ^ (bplId >>> 32));
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
		BplRawFile other = (BplRawFile) obj;
		if (bplId != other.bplId)
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BplRawFile [id=" + id + ", bplId=" + bplId + ", directory="
				+ directory + ", filename=" + filename + ", fileSize="
				+ fileSize + ", datasetCount=" + datasetCount + ", building="
				+ building + ", campus=" + campus + ", status=" + status
				+ ", notes=" + notes + ", importedTime=" + importedTime
				+ ", downloadedTime=" + downloadedTime + ", enteredTime="
				+ enteredTime + "]";
	}

}
