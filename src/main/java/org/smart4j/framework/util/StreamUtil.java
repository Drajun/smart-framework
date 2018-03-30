package org.smart4j.framework.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月30日下午5:05:38
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 流操作工具类
 */
public final class StreamUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);
	
	/**从流中获取字符串
	 * @param is
	 * @return
	 */
	public static String getString(InputStream is) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			while((line=reader.readLine())!=null) {
				sb.append(line);
			}
		}catch(Exception e){
			LOGGER.error("get string failure",e);
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
}
