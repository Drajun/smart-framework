package org.smart4j.framework;

import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.helper.IocHelper;
import org.smart4j.framework.util.ClassUtil;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月30日下午1:40:25
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 加载相应的Helper类
 * 即初始化
 */
public final class HelperLoader {
	
	public static void init() {
		Class<?>[] classList = {
			ClassHelper.class, 
			BeanHelper.class, 
			IocHelper.class, 
			ControllerHelper.class
		};
		
		for(Class<?> cls : classList) {
			ClassUtil.loadClass(cls.getName(),true);
		}
	}
}
