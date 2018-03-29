package util;

import java.util.Set;

import org.junit.Test;
import org.smart4j.framework.util.ClassUtil;

public class ClassUtilTest {
	
	@Test
	public void getClassLoaderTest() {
		ClassLoader cl = ClassUtil.getClassLoader();
		System.out.println(cl.toString());
	}
	
	@Test
	public void loadClassTest() {
		Class<?> clazz = ClassUtil.loadClass("org.smart4j.framework.util.CastUtil", false);
		System.out.println(clazz.getName().toString());
	}
	
	@Test
	public void getClassSetTest() {
		Set<Class<?>> classSet = ClassUtil.getClassSet("org.smart4j.framework.util");
		for(Class<?> c : classSet) {
			System.out.println(c.getName().toString());
		}
	}
	
	@Test
	public void getClassSetTestWithfailInput() {
		Set<Class<?>> classSet = ClassUtil.getClassSet("x");
		for(Class<?> c : classSet) {
			System.out.println(c.getName().toString());
		}
	}
	
}
