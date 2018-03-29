package org.smart4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月29日下午4:58:35
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 控制器注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

}
