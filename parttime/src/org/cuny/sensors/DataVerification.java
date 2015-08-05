/*
 *
 */
package org.cuny.sensors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Haijun Su Created date: 2013-12-11
 */
public class DataVerification {

	private Map<String, SensorData> dataMap = new HashMap<String, SensorData>();

	private long startTime = 0;

	private long endTime = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		// printDir("/storage/johnjay", 0, 10);
		DataVerification dv = new DataVerification();
		File froot = new File("/storage/johnjay");
		dv.validate(null, froot);
		System.out.println("Report Time: " + Utils.getTimeString(dv.startTime)
				+ " - " + Utils.getTimeString(dv.endTime));
		dv.verifyReports();
		// dv.printReports();
	}

	private void verifyReports() {
		Set<String> keys = dataMap.keySet();
		List<String> sortedKeys = new ArrayList<String>();
		for (String key : keys) {
			// System.out.println(dataMap.get(key));
			sortedKeys.add(key);
		}
		Collections.sort(sortedKeys);
		for (String key : sortedKeys) {
			// System.out.println(dataMap.get(key));
			SensorData sensor = dataMap.get(key);
			// check report time slots
			SensorReport preReport = null;
			List<SensorReport> reports = sensor.getReports();
			for (SensorReport sensorReport : reports) {
				if (preReport != null) {
					if (sensorReport.getStartTime() - preReport.getEndTime() != 1) {
						if (sensorReport.getStartTime() < preReport
								.getEndTime()) {
							// duplicated report
							sensor.addDuplicatedReports(preReport,
									sensorReport, sensorReport.getStartTime(),
									preReport.getEndTime());
						} else {
							// missed some reports
							sensor.addMissedReport(preReport.getEndTime() + 1,
									sensorReport.getStartTime() - 1);
						}
					}
				}
				preReport = sensorReport;
			}
			preReport = null;
			if (sensor.isMissedOrDuplicated()) {
				System.out.println();
				System.out.println("============");
				System.out.println(sensor.getName() + ",  Location: "
						+ sensor.getLocation());
				System.out.println("\t" + sensor.getMissedReportDescription());
				System.out.println("\t" + sensor.getDuplicatedReportDescription());
			}
		}

	}

	private void validate(String parent, File file) {
		if (file.isDirectory()) {
			if (parent == null) {
				parent = "";
			} else {
				parent += "/" + file.getName();
			}
			File[] files = file.listFiles();
			for (File subfile : files) {
				validate(parent, subfile);
			}
		} else {
			checkFileContent(parent, file);
		}
	}

	private void checkFileContent(String parent, File textfile) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(textfile));
			String line = null;
			SensorReport report = null;
			SensorData sensor = null;
			while ((line = br.readLine()) != null) {
				// process the line.
				line = line.trim();
				if (line.startsWith("\"Point")) {
					String name = getValue(line);
					sensor = dataMap.get(parent + name);
					if (sensor == null) {
						sensor = new SensorData();
						sensor.setName(name);
						sensor.setLocation(parent);
						dataMap.put(parent + name, sensor);
					}
					report = new SensorReport();
					sensor.addReport(report);
					report.setName(name);
					report.setReportFileName(textfile.getCanonicalPath());
				} else if (line.startsWith("\"Trend")) {
					String trendEvery = getValue(line);
					// query minute
					String minuteValue = trendEvery.substring(0,
							trendEvery.indexOf(" "));
					report.setTrendEvery(Integer.valueOf(minuteValue));

				} else if (line.startsWith("\"Date")) {
					report.setDateRange(getValue(line));
					// update report time range
					if (startTime == 0 || startTime > report.getStartTime()) {
						startTime = report.getStartTime();
					}
					if (endTime == 0 || endTime < report.getEndTime()) {
						endTime = report.getEndTime();
					}
					if (sensor.getStartTime() == 0
							|| sensor.getStartTime() > report.getStartTime()) {
						sensor.setStartTime(report.getStartTime());
					}
					if (sensor.getEndTime() == 0
							|| sensor.getEndTime() < report.getEndTime()) {
						sensor.setEndTime(report.getEndTime());
					}
				} else if (line.startsWith("\"Report")) {
					report.setReportTimings(getValue(line));
				} else if (line.split(",").length == 4) {
					report.increaseCount();
				}
			}
			line = null;
			br.close();
			// System.out.println(sensor);;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {

			}
		}
	}

	private String getValue(String line) {
		return line.substring(line.indexOf("\",\"") + 3, line.length() - 1);
	}

	public static void printDir(String path, int deepth, int maxDeepth) {
		printDir(path, deepth, maxDeepth, null, null);
	}

	public static void printDir(String path, int deepth, int maxDeepth,
			String[] ignoredPreffix, String[] ignoredSuffix) {
		if (deepth > maxDeepth) {
			return;
		}
		//
		File f = new File(path);

		if (ignoredPreffix != null && ignoredPreffix.length > 0) {
			String fName = f.getName().trim();
			for (int i = 0; i < ignoredPreffix.length; i++) {
				if (fName.startsWith(ignoredPreffix[i])) {
					return;
				}
			}
		}

		if (ignoredSuffix != null && ignoredSuffix.length > 0) {
			String fName = f.getName().trim();
			for (int i = 0; i < ignoredSuffix.length; i++) {
				if (fName.endsWith(ignoredSuffix[i])) {
					return;
				}
			}
		}

		//
		if (f.isFile()) {
			String fileName = f.getName();
			String formattedPath = getSpace(deepth) + fileName;
			// 1. normal
			System.out.println(formattedPath);

			return;
		} else {
			String folderName = getSpace(deepth) + "[-]" + f.getName();

			// 1.normal display
			System.out.println(folderName);

			File[] ff = f.listFiles();
			ArrayList<File> lst = new ArrayList<File>();
			for (int i = 0; i < ff.length; i++) {
				lst.add(ff[i]);
			}
			//
			Collections.sort(lst, new FileNameComparator());

			int newDepth = deepth + 1;
			for (int i = 0; i < lst.size(); i++) {
				printDir(lst.get(i).getAbsolutePath(), newDepth, maxDeepth,
						ignoredPreffix, ignoredSuffix);
			}
		}
	}

	public static String getSpace(int deepth) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < deepth; i++) {
			sb.append("    ");
		}
		return sb.toString();
	}
}
