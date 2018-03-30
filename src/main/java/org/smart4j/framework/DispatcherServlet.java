package org.smart4j.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CodecUtil;
import org.smart4j.framework.util.JsonUtil;
import org.smart4j.framework.util.ReflectionUtil;
import org.smart4j.framework.util.StreamUtil;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月30日下午2:00:36
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 请求转发器
 * 接收所有请求，封装好请求参数
 * 调用指定的Action方法，返回指定的页面或json数据
 */

@WebServlet(urlPatterns="/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		//初始化Helper类
		HelperLoader.init();
		
		//获取ServletContext对象(用于注册Servlet)
		ServletContext servletContext = servletConfig.getServletContext();
		
		//注册jsp的servlet
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
		
		//注册处理静态资源的默认servlet
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestMethod = request.getMethod().toLowerCase();
		String requestPath = request.getPathInfo();
		
		//根据请求路径和请求方法获取handler
		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
		if(handler!=null) {
			
			//根据Controller类获取Controller实例
			Class<?> controllerClass = handler.getControllerClass();
			Object controllerBean = BeanHelper.getBean(controllerClass);
			
			//获取请求参数
			Map<String, Object> paramMap = new HashMap<>();
			Enumeration<String> paramNames = request.getParameterNames();
			while(paramNames.hasMoreElements()) {
				String paramName = paramNames.nextElement();
				String paramValue = request.getParameter(paramName);
				paramMap.put(paramName, paramValue);
			}
			
			//获取流中的请求参数
			String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
			if(StringUtils.isNotEmpty(body)) {
				String[] params = StringUtils.split(body,"&");
				if(ArrayUtil.isNotEmpty(params)) {
					for(String param : params) {
						String[] array = StringUtils.split(param, "=");
						if(ArrayUtil.isNotEmpty(array) && array.length==2) {
							String paramName = array[0];
							String paramValue = array[1];
							paramMap.put(paramName, paramValue);
						}
					}
				}
			}
			
			//执行Action方法
			Param param = new Param(paramMap);
			Method actionMethod = handler.getActionMethod();
			Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
			
			//解析Action方法执行结果
			if(result instanceof View) {
				//返回JSP页面
				View view = (View) result;
				String path = view.getPath();
				if(StringUtils.isNotEmpty(path)) {
					if(path.startsWith("/")) {
						response.sendRedirect(request.getContextPath()+path);
					}else {
						Map<String, Object> model = view.getModel();
						for(Map.Entry<String, Object>entry : model.entrySet()) {
							request.setAttribute(entry.getKey(), entry.getValue());
							request.getRequestDispatcher(ConfigHelper.getAppJspPath()+"path").forward(request, response);
						}
					}
				}else if(result instanceof Data) {
					//返回JSON数据
					Data data = (Data) result;
					Object model = data.getModel();
					if(model != null) {
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						PrintWriter writer = response.getWriter();
						String json = JsonUtil.toJson(model);
						writer.write(json);
						writer.flush();
						writer.close();
					}
				}
			}
		}
	}

}
