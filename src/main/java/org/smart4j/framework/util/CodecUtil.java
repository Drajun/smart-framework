package org.smart4j.framework.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月30日下午5:10:05
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 编码与解码操作工具类
 */
public final class CodecUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);
	
	/**将URL编码
	 * @param source
	 * @return
	 */
	public static String encodeURL(String source) {
		String target;
		try {
			target = URLEncoder.encode(source, "UTF-8");
		}catch(Exception e) {
			LOGGER.error("encode url failure",e);
			throw new RuntimeException(e);
		}
		return target;
	}
	
	public static String decodeURL(String source) {
		String target;
		try {
			target = URLDecoder.decode(source,"UTF-8");
		}catch(Exception e) {
			LOGGER.error("decode url failure",e);
			throw new RuntimeException(e);
		}
		return target;
	}
}
