/*
 * Created on 2003-10-15
 */
package groller.framework.service;

import groller.framework.common.BeanFactoryWrapper;
import groller.framework.service.pojo.BaseService;

import java.util.Collection;

import org.springframework.aop.framework.ProxyFactoryBean;

/**
 * Base class of all kinds of Service Locators.
 * Concrete locators should extend this class, 
 * and provide neccessary factory methods, 
 * which delegates <code>getService</code> method,
 * so that service component creation can be managed.
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class ServiceLocator {
	private String _configName;
	private String[] _interceptorNames;
	
	/**
	 * Setter dependency injection (Type 2 IoC). 
	 * <code>getService</code> method will determine how to get configuration info-set
	 * based on the property "configName".
	 * @param configName The name of the configuration info-set
	 */
	public void setConfigName(String configName) {
		_configName = configName;
	}

	public void setInterceptorNames(Collection interceptorNames) {
		_interceptorNames = new String[interceptorNames.size()];
		interceptorNames.toArray(_interceptorNames);
	}

	/**
	 * Actually retrieve (not always create, based on the component configuration) 
	 * a Business Service component, whose all dependencies are injected.
	 * The retrieved component will be wrapped with a dynamic proxy (ServiceProxy),
	 * which will manage the transaction demarcation and resource releasing automatically.
	 * @param beanName The name of the Business Service which will be retrieved
	 * @param type The interface type of the Business Service
	 * @return Retrieved Business Service component
	 */
	protected IService getService(String beanName, Class type) {
		BaseService service = (BaseService) BeanFactoryWrapper.getBean(_configName, beanName);
//		return (IService) ServiceProxy.newInstance(type, service);
		ProxyFactoryBean factory = new ProxyFactoryBean();
		factory.setTarget(service);
		factory.setInterfaces(new Class[]{type});
		factory.setInterceptorNames(_interceptorNames);
		factory.setBeanFactory(BeanFactoryWrapper.getBeanFactory(_configName));
		return (IService) factory.getObject();
	}
}
