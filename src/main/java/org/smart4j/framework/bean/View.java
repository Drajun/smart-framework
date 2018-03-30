package org.smart4j.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月30日下午1:51:32
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 返回视图对象
 */
public class View {
	
	/**
	 * 视图路径
	 */
	private String path;
	
	/**
	 * 模型数据
	 */
	private Map<String, Object> model;
	
	public View(String path) {
		this.path = path;
		model = new HashMap<>();
	}
	
	/**添加模型数据
	 * @param key
	 * @param value
	 * @return
	 */
	public View addModel(String key, Object value) {
		model.put(key, value);
		return this;
	}
	
	/**获取视图路径
	 * @return
	 */
	public String getPath() {
		return path;
	}
	
	/**返回模型数据
	 * @return
	 */
	public Map<String, Object> getModel() {
		return model;
	}
	
}
