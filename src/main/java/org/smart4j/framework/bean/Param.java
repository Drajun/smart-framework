package org.smart4j.framework.bean;

import java.util.Map;

import org.smart4j.framework.util.CastUtil;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月30日下午1:46:23
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 请求参数对象
 */
public class Param {
	
	private Map<String, Object> paramMap;
	
	public Param(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	/**根据参数名获取long型参数值
	 * @param name
	 * @return
	 */
	public long getLong(String name) {
		return CastUtil.castLong(paramMap.get(name));
	}
	
	/**根据参数名获取int型参数值
	 * @param name
	 * @return
	 */
	public int getInt(String name) {
		return CastUtil.castInt(paramMap.get(name));
	}
	
	/**根据参数名获取String型参数值
	 * @param name
	 * @return
	 */
	public String getString(String name) {
		return CastUtil.castString(paramMap.get(name));
	}
	
	/**根据参数名获取boolean型参数值
	 * @param name
	 * @return
	 */
	public boolean getBoolean(String name) {
		return CastUtil.castBoolean(paramMap.get(name));
	}
	
	/**根据参数名获取Double型参数值
	 * @param name
	 * @return
	 */
	public double getdouble(String name) {
		return CastUtil.castDouble(paramMap.get(name));
	}
	
	/**获取所有字段信息
	 * @return
	 */
	public Map<String, Object> getMap(){
		return paramMap;
	}
}
