package org.smart4j.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.smart4j.framework.util.ReflectionUtil;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月29日下午10:41:24
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 助手类
 */
public final class BeanHelper {
	
	/**
	 * 定义 Bean 映射(用于存放Bean类与Bean实例的映射关系)
	 */
	private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();
	
	static {
		//获取所有Controller、Service类
		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
		for(Class<?> beanClass : beanClassSet) {
			Object obj = ReflectionUtil.newInstance(beanClass);
			BEAN_MAP.put(beanClass, obj);
		}
	}
	
	/**获取Bean映射
	 * @return
	 */
	public static Map<Class<?>, Object> getBeanMap(){
		return BEAN_MAP;
	}
	
	/**获取bena实例
	 * @param cls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> cls) {
		if(!BEAN_MAP.containsKey(cls)) {
			throw new RuntimeException("can not get bean by class:"+cls);
		}
		return (T)BEAN_MAP.get(cls);
	}
}
