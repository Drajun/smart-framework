package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月30日上午11:24:22
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 封装Action信息
 */
public class Handler {
	
	/**
	 * Controller类
	 */
	private Class<?> controllerClass;
	
	/**
	 * Action方法
	 */
	private Method actionMethod;

	public Handler(Class<?> controllerClass, Method actionMethod) {
		this.controllerClass = controllerClass;
		this.actionMethod = actionMethod;
	}

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public Method getActionMethod() {
		return actionMethod;
	}
	
	
}
