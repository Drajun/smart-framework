package org.smart4j.framework.helper;

import java.util.HashSet;
import java.util.Set;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月29日下午5:09:58
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 类操作助手类
 */
public final class ClassHelper {
	
	/**
	 * 存放所加载的类
	 */
	private static final Set<Class<?>> CLASS_SET;
	
	static {
		String basePackge = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(basePackge);
	}
	
	/**获取应用包名下的所有类
	 * @return
	 */
	public static Set<Class<?>> getClassSet(){
		return CLASS_SET;
	}
	
	/**获取所有的service类
	 * @return
	 */
	public static Set<Class<?>> getServiceClassSet(){
		Set<Class<?>> classSet = new HashSet<>();
		for(Class<?> cls : CLASS_SET) {
			if(cls.isAnnotationPresent(Service.class)) {
				classSet.add(cls);
			}
		}
		
		return classSet;
	}
	
	/**获取所有的controller类
	 * @return
	 */
	public static Set<Class<?>> getControllerClassSet(){
		Set<Class<?>> classSet = new HashSet<>();
		for(Class<?> cls : CLASS_SET) {
			if(cls.isAnnotationPresent(Controller.class)) {
				classSet.add(cls);
			}
		}
		
		return classSet;
	}
	
	/**获取应用包名下的所有bean类
	 * @return
	 */
	public static Set<Class<?>> getBeanClassSet(){
		Set<Class<?>> beanClassSet = new HashSet<>();
		beanClassSet.addAll(getServiceClassSet());
		beanClassSet.addAll(getControllerClassSet());
		return beanClassSet;
	}
}
