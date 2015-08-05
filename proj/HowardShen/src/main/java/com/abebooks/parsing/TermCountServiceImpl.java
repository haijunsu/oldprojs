package com.abebooks.parsing;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.abebooks.data.License;
import com.abebooks.data.License.Type;

/**
 * Default implementation of the TermCountService
 * <p/>
 * AbeBooks Sample File
 */
public class TermCountServiceImpl implements TermCountService {
	ConcurrentHashMap<String, AtomicInteger> termCounts = new ConcurrentHashMap<String, AtomicInteger>();

	/**
	 * FIXME -- implement me
	 * Create threads per license to deal the compute
	 */
	@Override
	public Map<String, Integer> getTopTerms(Collection<License> licenses,
			Collection<String> terms, int count) {
		ExecutorService service = Executors.newFixedThreadPool(licenses.size());
		CountDownLatch countDown = new CountDownLatch(licenses.size());
		// initialize termCounts
		for (String term : terms) {
			termCounts.put(term, new AtomicInteger(0));
		}
		for (License lic : licenses) {
			Type licType = lic.getType();
			StringBuffer licTxtBuf = new StringBuffer();
			switch (licType) {
			case URL:
				URL url;
				try {
					url = new URL(lic.getText());
					Reader reader = new InputStreamReader(
							new BufferedInputStream(url.openStream()));
					int letter;
					while ((letter = reader.read()) != -1) {
						licTxtBuf.append((char) letter);
					}
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case FILE:
				licTxtBuf.append(readFile(new File(lic.getText())));
				break;
			case INLINE:
				licTxtBuf.append(lic.getText());
				break;
			default:
				break;
			}
			CountWorker worker = new CountWorker(countDown, licTxtBuf
					.toString());
			service.execute(worker);
		}
		try {
			countDown.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<KeyCounterPair> keyTerms = new ArrayList<KeyCounterPair>();
		for (String term : terms) {
			AtomicInteger tmpCount = termCounts.get(term);
			if (tmpCount != null) {
				Integer termCount = tmpCount.intValue();
				KeyCounterPair keyTerm = new KeyCounterPair(term, termCount);
				keyTerms.add(keyTerm);
			}
		}
		Collections.sort(keyTerms, new Comparator<KeyCounterPair>() {

			@Override
			public int compare(KeyCounterPair o1, KeyCounterPair o2) {
				return o2.compareTo(o1);
			}

		});
		Map<String, Integer> results = new HashMap<String, Integer>();
		int num = 1;
		for (KeyCounterPair keyTerm : keyTerms) {
			if (num > count) {
				break;
			}
			results.put(keyTerm.getKey(), keyTerm.getCount());
			num++;
		}
		return results;
	}

	/**
	 *  Thread to compute the counter of term key words in license
	 */
	class CountWorker implements Runnable {

		private String licTxt;
		private CountDownLatch countDown;

		public CountWorker(CountDownLatch countDown, String licTxt) {
			this.licTxt = licTxt;
			this.countDown = countDown;
		}

		@Override
		public void run() {
			try {
				if (licTxt == null || licTxt.length() == 0) {
					return;
				}
				Set<String> keys = termCounts.keySet();
				for (String key : keys) {
					Pattern pattern = Pattern.compile("\\b" + key.toLowerCase()
							+ "\\b");
					AtomicInteger wordCount = termCounts.get(key);
					Matcher matcher = pattern.matcher(licTxt.toLowerCase());
					while (matcher.find()) {
						wordCount.getAndIncrement();
					}
				}
			} finally {
				countDown.countDown();
			}
		}
	}

	/**
	 * Read a file into String
	 *
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String readFile(File file) {
		if (!file.exists()) {
		}
		FileInputStream fis = null;
		BufferedReader br = null;
		StringBuffer contents = new StringBuffer();
		String line = null;
		try {
			fis = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(fis));
			while (((line = br.readLine()) != null)) {
				contents.append(line).append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return contents.toString();
	}

}
