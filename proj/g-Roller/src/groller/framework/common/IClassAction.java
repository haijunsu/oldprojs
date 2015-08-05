package groller.framework.common;

/**
 * @author xiongj
 * @created 2003-3-26
 * 一个函数对象，可以在Class上作某些动作
 * @see PackageUtil#processClasses
 *
 * .
 */
public interface IClassAction {
	public void execute(Class clazz) ;
}
