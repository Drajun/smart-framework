package org.smart4j.framework.bean;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月30日下午1:55:52
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 返回数据对象 （JSON数据）
 */
public class Data {
	
	/**
	 * 模型数据
	 */
	private Object model;
	
	public Data(Object model) {
		this.model = model;
	}
	
	public Object getModel() {
		return model;
	}
}
