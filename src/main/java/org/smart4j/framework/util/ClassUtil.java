package org.smart4j.framework.util;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月29日上午9:50:36
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 类操作工具
 */
public final class ClassUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);
	
	/**获取类加载器
	 * @return
	 */
	public static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}
	
	/**加载类
	 * @param className
	 * @param isInitialized 表示是否执行静态代码块
	 * @return
	 */
	public static Class<?> loadClass(String className, boolean isInitialized){
		Class<?> cls;
		try {
			cls = Class.forName(className, isInitialized, getClassLoader());
		}catch(ClassNotFoundException e) {
			LOGGER.error("load class failure",e);
			throw new RuntimeException(e);
		}
		return cls;
	}
	
	/**获取指定包名下的所有类
	 * @param packageName
	 * @return
	 */
	public static Set<Class<?>> getClassSet(String packageName){
		Set<Class<?>> classSet = new HashSet<>();
		try {
			Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
			while(urls.hasMoreElements()) {
				URL url = urls.nextElement();
				if(url!=null) {
					String protocol = url.getProtocol();
					if(protocol.equals("file")) {
						String packagePath = url.getPath().replaceAll("%20", " ");
						addClass(classSet,packagePath,packageName);
					}else if(protocol.equals("jar")) {
						JarURLConnection jarURLConnection = (JarURLConnection)url.openConnection();
						JarFile jarFile = jarURLConnection.getJarFile();
						if(jarFile!=null) {
							Enumeration<JarEntry> jarEntries = jarFile.entries();
							while(jarEntries.hasMoreElements()) {
								JarEntry jarEntry = jarEntries.nextElement();
								String jarEntryName = jarEntry.getName();
								if(jarEntryName.endsWith(".class")) {
									String className = jarEntryName.substring(0,jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
									doAddClass(classSet,className);
								}
							}
						}
					}
				}
			}
		}catch(Exception e) {
			LOGGER.error("get class set failure",e);
			throw new RuntimeException(e);
		}
		return classSet;
	}
	
	/**将包名转换为文件路径
	 * @param classSet
	 * @param packagePath
	 * @param packageName
	 */
	private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
		File[] files = new File(packagePath).listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return (pathname.isFile() && pathname.getName().endsWith(".class")) || pathname.isDirectory();
			}
		});
		
		for(File file : files) {
			String fileName = file.getName();
			if(file.isFile()) {
				String className = fileName.substring(0, fileName.lastIndexOf("."));
				if(StringUtils.isNotEmpty(packageName)) {
					className = packageName+"."+className;	//完整的类名
				}
				doAddClass(classSet,className);
			}else {
				//如果不是文件(而是文件夹)，则获取子目录后继续递归处理
				String subPackagePath = fileName;
				if(StringUtils.isNotEmpty(packagePath)) {
					subPackagePath = packagePath + "/" + subPackagePath;
				}
				String subPackageName = fileName;
				if(StringUtils.isNotEmpty(packageName)) {
					subPackageName = packageName + "." +subPackageName;
				}
				addClass(classSet,subPackagePath,subPackageName);
			}
		}
	}
	
	/**真正地加载一个类
	 * @param classSet
	 * @param className
	 */
	private static void doAddClass(Set<Class<?>> classSet, String className) {
		Class<?> cls = loadClass(className,false);
		classSet.add(cls);
	}
}
