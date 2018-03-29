package org.smart4j.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectionUtil;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月29日下午11:04:44
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 依赖注入助手类
 */
public final class IocHelper {
	
	static {
		//获取所有的Bean类与Bean类实例的映射(Bean Map)
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
		
		if(CollectionUtil.isNotEmpty(beanMap)) {
			for(Map.Entry<Class<?>, Object>beanEntry : beanMap.entrySet()) {
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				
				//获取当前bean的所有成员变量
				Field[] beanFields = beanClass.getDeclaredFields();
				if (ArrayUtil.isNotEmpty(beanFields)) {
					for (Field beanField : beanFields) {
						//判断当前Bean Field 是否带有Inject注解
						if(beanField.isAnnotationPresent(Inject.class)) {
							//在Bean Map中获取Bean Field对应的实例
							Class<?>beanFieldClass = beanField.getType();
							Object beanFieldInstance = beanMap.get(beanFieldClass);
							if(beanFieldInstance!=null) {
								//通过反射初始化BeanField的值
								ReflectionUtil.setField(beanInstance, beanField, beanInstance);
							}
						}
					}
				}
			}
		}
	}
}
