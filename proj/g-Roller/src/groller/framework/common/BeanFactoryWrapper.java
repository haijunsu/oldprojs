package groller.framework.common;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;

/**
 * A wrapper for bean-factory.
 * Before use its non-static methods, 
 * you must inject the configName (e.g. "application") into it.
 * You also can use the static method to access a config file directly :<br>
 * <code>Object someBean = BeanFactoryWrapper.getBean(configName, beanName);</code><br>
 * Actual configuration informations are stored in :<br>
 * <code>%default_package%/conf/%configName%.bean.xml</code>
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class BeanFactoryWrapper {
	private String _configName;
	private static Map _factories = new HashMap();
	
	/**
	 * Setter dependency injection (Type 2 IoC).
	 * BeanFactoryWrapper will determine actual config file based on configName.
	 * Actual config file will be :<br>
 	 * <code>%default_package%/conf/%configName%.bean.xml</code>
 	 * @param configName The configuration info-set's name (e.g. "application")
	 */
	public void setConfigName(String configName) {
		_configName = configName;
	}
	
	/**
	 * Retrieve a bean from default configuration info-set.
	 * @param beanName The name of the bean which you want to retrieve
	 * @return retrieved bean (with all the dependencies injected)
	 */
	public Object getBean(String beanName) {
		BeanFactory factory = getBeanFactory(_configName);
		Object bean = factory.getBean(beanName);
		return bean;
	}
	
	/**
	 * Retrieve a bean from given configuration info-set.
	 * @param beanName The name of the bean which you want to retrieve
	 * @return retrieved bean (with all the dependencies injected)
	 */
	public static synchronized Object getBean(String configName, String beanName) {
		BeanFactory factory = getBeanFactory(configName);
		Object bean = factory.getBean(beanName);
		return bean;
	}

	public static synchronized BeanFactory getBeanFactory(String configName) {
		if(_factories.containsKey(configName)) {
			return (BeanFactory) _factories.get(configName);
		}
		
		String fileName = "conf/" + configName + ".bean.xml"; 
		InputStream is = BeanFactoryWrapper.class.getClassLoader().getResourceAsStream(fileName);
		BeanFactory factory = new XmlBeanFactory(is);
		_factories.put(configName, factory);
		return factory;
	}
}
