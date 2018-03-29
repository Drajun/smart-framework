package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月29日下午9:49:27
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 反射工具类
 */
public final class ReflectionUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);
	
	
	/**创建实例
	 * @param cls
	 * @return
	 */
	public static Object newInstance(Class<?> cls) {
		Object instance;
		try {
			instance = cls.newInstance();
		}catch(Exception e) {
			LOGGER.error("new instance failure",e);
			throw new RuntimeException(e);
		}
		return instance;
	}
}
