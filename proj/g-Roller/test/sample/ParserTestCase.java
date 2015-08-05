/* 
 * Project: OSWorkflow
 * Created on 2004-4-7
 */
package sample;

import junit.framework.TestCase;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class ParserTestCase extends TestCase {
	public void testFindSax() throws Exception {
		System.out.println(System.getProperty("org.xml.sax.driver"));
		System.out.println(getClass().getClassLoader().getResource("org/xml/sax/XMLReader.class"));
		
		XMLReader reader = XMLReaderFactory.createXMLReader();
		assertNotNull(reader);
	}
}
