package org.smart4j.framework.util;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月29日上午9:50:36
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 类操作工具
 */
public final class ClassUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);
	
	/**获取类加载器
	 * @return
	 */
	public static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}
	
	/**加载类
	 * @param className
	 * @param isInitialized 表示是否执行静态代码块
	 * @return
	 */
	public static Class<?> loadClass(String className, boolean isInitialized){
		Class<?> cls;
		try {
			cls = Class.forName(className, isInitialized, getClassLoader());
		}catch(ClassNotFoundException e) {
			LOGGER.error("load class failure",e);
			throw new RuntimeException(e);
		}
		return cls;
	}
	
	/**获取指定包名下的所有类
	 * @param packageName
	 * @return
	 */
	public static Set<Class<?>> getClassSet(String packageName){
		Set<Class<?>> classSet = new HashSet<>();
		return classSet;
	}
}
