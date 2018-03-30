package org.smart4j.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月30日上午11:32:15
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 控制器助手类
 * 通过检查每个Controller类的每个Action方法，
 * 将这些@Action中所写的请求路径和请求方法提取出来，
 * 形成请求路径、方法 与 该Action方法对应，
 * 即根据 路径+请求方法 就可以让这个Action来处理客户端的请求
 */
public final class ControllerHelper {
	
	/**
	 * 存放请求与处理器的映射关系
	 */
	private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();
	
	static {
		//获取所有的Controller类
		Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
		
		if(CollectionUtil.isNotEmpty(controllerClassSet)) {
			//遍历每个Controller类
			for(Class<?> controllerClass : controllerClassSet) {
				Method[] methods = controllerClass.getDeclaredMethods();
				if(ArrayUtil.isNotEmpty(methods)) {
					
					//遍历每一个Controller类的每个方法
					for(Method method : methods) {
						if(method.isAnnotationPresent(Action.class)) {
							
							//如果这个方法带有Action注解，就将它的所配置的路径和请求方法提取出来
							//形成 “路径-请求方式”  与  “具体的Controller Action” 对应
							Action action = method.getAnnotation(Action.class);
							String mapping = action.value();
							if(mapping.matches("\\w+:/\\w*")) {
								String[] array = mapping.split(":");
								if(ArrayUtil.isNotEmpty(array) & array.length==2) {
									String requestMethod = array[0];
									String requestPath = array[1];
									Request request = new Request(requestMethod, requestPath);
									Handler handler = new Handler(controllerClass, method);
									
									ACTION_MAP.put(request, handler);
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**获取Handler
	 * @param requestMethod
	 * @param requestPath
	 * @return
	 */
	public static Handler getHandler(String requestMethod, String requestPath) {
		Request request = new Request(requestMethod, requestPath);
		return ACTION_MAP.get(request);
	}
}
