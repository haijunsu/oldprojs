/*
 * 
 */
package org.cuny.sensors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author Haijun Su Created date: 2013-12-11
 */
public class SensorData {

	private String name;

	private List<SensorReport> reports = new ArrayList<SensorReport>();

	private List<String> missedReports = new ArrayList<String>();

	private List<String> duplicatedReports = new ArrayList<String>();

	private String location;

	private boolean isSorted = false;

	private long startTime = 0;

	private long endTime = 0;

	
	
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	private void sortReports() {
		if (isSorted) {
			return;
		}
		// sort report
		Collections.sort(reports, new Comparator<SensorReport>() {

			@Override
			public int compare(SensorReport o1, SensorReport o2) {
				// TODO Auto-generated method stub
				return (int) (o1.getStartTime() - o2.getStartTime());
			}
		});
		isSorted = true;
	}

	public void addMissedReport(long startTime, long endTime) {
		missedReports.add(Utils.getTimeString(startTime) + " - "
				+ Utils.getTimeString(endTime));
	}

	public void addDuplicatedReports(SensorReport report1,
			SensorReport report2, long startTime, long endTime) {
		duplicatedReports
				.add(Utils.getTimeString(startTime) + " - "
						+ Utils.getTimeString(endTime) + ", Files: "
						+ Utils.LINE_SEPARATOR + "\t\t\t"
						+ report1.getReportFileName() + Utils.LINE_SEPARATOR
						+ "\t\t\t" + report2.getReportFileName());
	}

	public String getMissedReportDescription() {
		if (missedReports.isEmpty()) {
			return "";
		}
		String desc = "Missed reports: ";
		for (String msg : missedReports) {
			desc += Utils.LINE_SEPARATOR + "\t\t" + msg;
		}
		return desc;
	}

	public String getDuplicatedReportDescription() {
		if (duplicatedReports.isEmpty()) {
			return "";
		}
		String desc = "Duplicated reports: ";
		for (String msg : duplicatedReports) {
			desc += Utils.LINE_SEPARATOR + "\t\t" + msg;
		}
		return desc;
	}

	public boolean isMissedOrDuplicated() {
		return !missedReports.isEmpty() || !duplicatedReports.isEmpty();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addReport(SensorReport report) {
		reports.add(report);
		if (startTime == 0 || startTime > report.getStartTime()) {
			startTime = report.getStartTime();
		}
		if (endTime == 0 || endTime < report.getEndTime()) {
			endTime = report.getEndTime();
		}
		isSorted = false;
	}

	public List<SensorReport> getReports() {
		sortReports();
		return reports;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Sensor [name=" + name + ", reports=" + reports + ", location="
				+ location + "]";
	}

}
