package util;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.smart4j.framework.util.PropsUtil;

public class PropsUtilsTest {
	
	Properties props;
	
	@Before
	public void loadProps() {
		props = PropsUtil.loadProps("smart.properties");
	}
		
	@Test
	public void getJdbcDriverTest() {
		String driver = PropsUtil.getString(props, "smart.framework.jdbc.driver");
		System.out.println(driver);
	}
	
	@Test
	public void getJdbcUrlTest() {
		String url = PropsUtil.getString(props, "smart.framework.jdbc.url");
		System.out.println(url);
	}
	
	@Test
	public void getJdbcUserNameAndPasswordTest() {
		String username = PropsUtil.getString(props, "smart.framework.jdbc.username");
		String password = PropsUtil.getString(props, "smart.framework.jdbc.password");
		System.out.println(username);
		System.out.println(password);
	}
	
	@Test
	public void getAppBasePackageTest() {
		String basePackage = PropsUtil.getString(props, "smart.framework.app.base_package");
		System.out.println(basePackage);
	}
	
	@Test
	public void getAppJspPathTest() {
		String jspPath = PropsUtil.getString(props, "smart.framework.app.jsp_path");
		System.out.println(jspPath);
	}
	
	@Test
	public void getAppAssetTest() {
		String asset = PropsUtil.getString(props, "smart.framework.app.asset_path");
		System.out.println(asset);
	}
	
	@Test
	public void getAppAssetForFailInput() {
		String asset = PropsUtil.getString(props, "xxx","未找到相关配置");
		System.out.println(asset);
	}
}
