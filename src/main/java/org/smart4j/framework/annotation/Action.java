package org.smart4j.framework.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月29日下午4:58:25
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 方法注解
 */
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface Action {
	
	/*
	 * 请求类型与路径
	 */
	String value();
}
