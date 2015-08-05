/*
 *
 */
package org.cuny.sensors.service;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Date;

import org.cuny.sensors.dao.RawFileDao;
import org.cuny.sensors.entity.RawFile;
import org.cuny.sensors.entity.RawFile.RawFileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author Haijun Su Created date: Jan 10, 2014
 */
@Service("cronJob")
public class CronJobServiceImpl implements CronJobService {

	@Value("${data.path}")
	private String[] dataPath;

	@Value("${data.campus}")
	private String[] campus;

	@Value("${data.building}")
	private String[] building;

	private DataService service;

	private RawFileDao rawFileDao;

	@Autowired
	public void setDataService(DataService dataService) {
		this.service = dataService;
	}

	@Autowired
	public void setRawFileDao(RawFileDao rawFileDao) {
		this.rawFileDao = rawFileDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cuny.sensors.service.CronJobService#importData()
	 */
	@Override
	@Scheduled(cron = "${cron.expression}")
	public void importData() {
		System.out.println("Path=" + Arrays.toString(dataPath) + ", campus="
				+ Arrays.toString(campus) + ", building="
				+ Arrays.toString(building));
		if (dataPath.length != campus.length
				|| dataPath.length != building.length) {
			System.out.println("Input parameters error.");
		}
		long startTime = 0l;
		for (int i = 0; i < dataPath.length; i++) {
			try {
				startTime = System.currentTimeMillis();
				System.out.println("Importing " + dataPath[i] + " at "
						+ new Date());
				importData(new File(dataPath[i]), building[i], campus[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out
					.println("Total time to import "
							+ dataPath[i]
							+ ": "
							+ (((System.currentTimeMillis() - startTime)) / 1000)
							+ "s");
		}
	}

	private void importData(File path, String building, String campus)
			throws Exception {
		if (path.isDirectory()) {
			// handle csv files
			File[] files = path.listFiles(new FileFilter() {
				@Override
				public boolean accept(File file) {
					if (file.getName().endsWith(".csv"))
						return true;
					return false;
				}
			});
			for (int i = 0; i < files.length; i++) {
				importData(files[i], building, campus);
			}
			// handle folders
			File[] folders = path.listFiles(new FileFilter() {
				@Override
				public boolean accept(File file) {
					if (file.isDirectory())
						return true;
					return false;
				}
			});

			for (int i = 0; i < folders.length; i++) {
				importData(folders[i], building, campus);
			}
			return;
		}
		RawFile rawFile = this.rawFileDao.get(path.getCanonicalPath());
		if (rawFile == null) {
			rawFile = new RawFile();
			rawFile.setFilename(path.getCanonicalPath());
			rawFile.setFileSize(path.length());
			rawFile.setStatus(RawFileStatus.Ready);
			rawFile.setBuilding(building);
			rawFile.setCampus(campus);
			rawFile = this.rawFileDao.save(rawFile);
		}
		try {
			this.service.importData(rawFile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rawFile.getStatus() != RawFileStatus.Imported) {
					this.rawFileDao.update(rawFile);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
