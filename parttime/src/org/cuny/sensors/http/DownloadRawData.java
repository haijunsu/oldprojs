/*
 *
 */
package org.cuny.sensors.http;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Haijun Su Created date: Feb 1, 2014
 */
public class DownloadRawData {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			/**
			HttpGet httpGet = new HttpGet("http://targethost/homepage");
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			// The underlying HTTP connection is still held by the response
			// object
			// to allow the response content to be streamed directly from the
			// network socket.
			// In order to ensure correct deallocation of system resources
			// the user MUST either fully consume the response content or abort
			// request
			// execution by calling CloseableHttpResponse#close().

			try {
				System.out.println(response1.getStatusLine());
				HttpEntity entity1 = response1.getEntity();
				// do something useful with the response body
				// and ensure it is fully consumed
				EntityUtils.consume(entity1);
			} finally {
				response1.close();
			}
**/
			HttpPost httpPost = new HttpPost("http://bpl.cisdd.org/login.php");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("username", "tbrown"));
			nvps.add(new BasicNameValuePair("password", "tbrown"));
			nvps.add(new BasicNameValuePair("submit", "Login"));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try {
				System.out.println(response2.getStatusLine());
				System.out.println(response2.getHeaders("Location"));
				Header[] headers = response2.getHeaders("Location");
				if (headers.length > 0) {
					response2.close();
					HttpGet httpGet = new HttpGet("http://bpl.cisdd.org/" + headers[0].getValue());
					response2 = httpclient.execute(httpGet);
				}
				HttpGet httpGet = new HttpGet("http://bpl.cisdd.org/reportlist.php");
				response2 = httpclient.execute(httpGet);


				HttpEntity entity2 = response2.getEntity();
				// do something useful with the response body
				InputStream is = entity2.getContent();
				Document doc = Jsoup.parse(is, "UTF-8", "http://bpl.cisdd.org/");
				//System.out.println(doc.toString());
		        Elements links = doc.select("a[href]");
		        Elements media = doc.select("[src]");
		        Elements imports = doc.select("link[href]");
		        Elements rows = doc.select("tr");

		        print("\nMedia: (%d)", media.size());
		        for (Element src : media) {
		            if (src.tagName().equals("img"))
		                print(" * %s: <%s> %sx%s (%s)",
		                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
		                        trim(src.attr("alt"), 20));
		            else
		                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
		        }

		        print("\nImports: (%d)", imports.size());
		        for (Element link : imports) {
		            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
		        }

		        print("\nLinks: (%d)", links.size());
		        for (Element link : links) {
		            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
		        }
		        System.out.println("rows: ????");

		        //Load ArrayList with table row strings
	            for (Element tableRow : rows){
	            	//System.out.println(tableRow);
	                 Elements tableDatas = tableRow.getAllElements();

	                 //Test if the the TableData contains Text, if so convert that text to a string rowData
	                 if (tableDatas.hasText()){
	                     String rowData = tableDatas.text();
	                     Elements tds = tableDatas.select("td");
	                     for (Element el : tds) {
	                    	 Elements filelinks = el.select("a[href]");
	                    	 for (Element flink : filelinks) {
								System.out.println(flink.attr("abs:href"));
							}
							System.out.println(el.text());
						}

	                    //System.out.println(rowData);
	                 }

	        }
				//System.out.println(doc.toString());
				// and ensure it is fully consumed

				EntityUtils.consume(entity2);

			} finally {
				response2.close();
			}
		} finally {
			httpclient.close();
		}
	}

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }

}
