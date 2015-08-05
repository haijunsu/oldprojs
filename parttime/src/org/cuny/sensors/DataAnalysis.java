/*
 *
 */
package org.cuny.sensors;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Userage: java -cp .:./Bpl-0.0.1-SNAPSHOT.jar org.cuny.sensors.DataAnalysis
 *
 *
 * @author Haijun Su Created date: Jan 5, 2014
 */
public class DataAnalysis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// load spring context
		GenericApplicationContext context = new GenericApplicationContext();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(context);
		xmlReader.loadBeanDefinitions(new ClassPathResource(
				"applicationContext.xml"));
		context.refresh();
		System.out.println("Importing schudle started!");
	}

}
