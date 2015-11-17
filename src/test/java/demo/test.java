package demo;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

/**
 * @author Jufni R.
 *
 */
public class test {
	private String baseUrl;
	private WebDriver driver;
	DesiredCapabilities capabilities;
	
	@Parameters({ "browser-name", "platform-name", "hub" })
	@Before
	public void setUp(String browser_name, String platform_name,
			String browser_version, String hub) throws Exception {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setPlatform(Platform.LINUX);
		capabilities.setBrowserName(browser_name); 
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		driver = new RemoteWebDriver(new URL(hub), capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		baseUrl = "https://docs.google.com/";
	}
	  
	
	@Test(alwaysRun = true)
	public void test001() throws Exception {
	    driver.get(baseUrl + "/forms/d/18nq9YuC0E8p2JOONkqZ5IAMIdP1eytiEDV8hJn_spHk/viewform");
	    driver.findElement(By.id("entry_785445797")).clear();
	    driver.findElement(By.id("entry_785445797")).sendKeys("Jufni R.");
	    driver.findElement(By.id("group_396363777_4")).click();
	    driver.findElement(By.id("group_277070397_3")).click();
	    driver.findElement(By.id("ss-submit")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}