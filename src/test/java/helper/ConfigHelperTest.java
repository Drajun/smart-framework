package helper;

import org.junit.Test;
import org.smart4j.framework.helper.ConfigHelper;

public class ConfigHelperTest {
	
	@Test
	public void getJdbcDriverTest() {
		String driver = ConfigHelper.getJdbcDriver();
		System.out.println(driver);
	}
	
	@Test
	public void getJdbcUrlTest() {
		String url = ConfigHelper.getJdbcUrl();
		System.out.println(url);
	}
	
	@Test
	public void getJdbcUsernameAndPasswordTest() {
		String username = ConfigHelper.getJdbcUsername();
		String password =ConfigHelper.getJdbcPassword();
		System.out.println(username);
		System.out.println(password);
	}
	
	@Test
	public void getAppBasePackageTest() {
		String basePackage = ConfigHelper.getAppBasePackage();
		System.out.println(basePackage);
	}
	
	@Test
	public void getAppJspPathTest() {
		String jspPath = ConfigHelper.getAppJspPath();
		System.out.println(jspPath);
	}
	
	public void getAppAssetPathTest() {
		String assetPath = ConfigHelper.getAppAssetPath();
		System.out.println(assetPath);
	}
}
