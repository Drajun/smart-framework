package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月30日下午5:15:14
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe JSON工具类
 */
public final class JsonUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	/**将pojo 转为JSON
	 * @param obj
	 * @return
	 */
	public static <T> String toJson(T obj) {
		String json;
		try {
			json = OBJECT_MAPPER.writeValueAsString(obj);
		}catch(Exception e) {
			LOGGER.error("convert POJO  to JSON failure",e);
			throw new RuntimeException(e);
		}
		return json;
	}
	
	/**json 转 pojo
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> type) {
		T pojo;
		try {
			pojo = OBJECT_MAPPER.readValue(json, type);
		}catch(Exception e) {
			LOGGER.error("convert JSON to POJO failure",e);
			throw new RuntimeException(e);
		}
		return pojo;
	}
}
