package org.smart4j.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月29日下午11:28:49
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 数组工具类
 */
public final class ArrayUtil {
	
	/**判断数组是否非空
	 * @param array
	 * @return
	 */
	public static boolean isNotEmpty(Object[] array) {
		return !ArrayUtils.isEmpty(array);
	}
	
	/**判断数组是否为空
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(Object[] array) {
		return ArrayUtils.isEmpty(array);
	}
}
