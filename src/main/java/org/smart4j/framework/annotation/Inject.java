package org.smart4j.framework.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月29日下午5:01:37
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 依赖注入注解
 */
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface Inject {

}
