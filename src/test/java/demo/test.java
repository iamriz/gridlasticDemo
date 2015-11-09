package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;

// Notes: hub and videoURL parameter is passed in from Jenkins maven job like:
// GOAL: test -Dhub=http://USERNAME:USER_KEY@SUBDOMAIN.gridlastic.com:80/wd/hub -DvideoUrl=https://s3-ap-southeast-2.amazonaws.com/b2729248-ak68-6948-a2y8-80e7479te16a/9ag7b09j-6a38-58w2-bb01-17qw724ce46t
// You will find these parameters in your Gridlastic dashboard after starting your selenium grid
// Also, the Jenkins hostname is passed in via -DjenkinsHostname from Jenkins maven job

public class test {
	private String baseUrl;
	private RemoteWebDriver driver;
	ITestContext myTestContext;
	DesiredCapabilities capabilities;

	@Parameters({ "browser-name", "platform-name", "browser-version", "hub" })
	@Test(alwaysRun = true)
	public void testTemp1(String browser_name, String platform_name,
			String browser_version, String hub, ITestContext myTestContext)
			throws Exception {

		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability("jenkins.label", "amd64");
		capabilities.setBrowserName(browser_name); 
		capabilities.setVersion(browser_version);
		capabilities.setCapability(FirefoxDriver.PROFILE, new FirefoxProfile());
		if (platform_name.equalsIgnoreCase("linux")) {
			capabilities.setPlatform(Platform.LINUX);
		}

//		if (browser_name.equalsIgnoreCase("chrome")) {
//			ChromeOptions options = new ChromeOptions();
//			// On LINUX the "start-maximized" Chrome option does not expand
//			// browser window to max screen size.
//			if (platform_name.equalsIgnoreCase("linux")) {
//				options.addArguments(Arrays.asList("--window-size=1920,1080"));
//			} else {
//				options.addArguments(Arrays.asList("--start-maximized"));
//			}
//			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//		}

		this.driver = new RemoteWebDriver(new URL(hub), capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		baseUrl = "https://docs.google.com/";
		driver.get(baseUrl + "/forms/d/1xB5BbIf6ZIkhXsZrk0WyerGc-UcBDpCm26ydOFpYLYI/viewform");
		driver.findElement(By.id("entry_1359528431")).clear();
		driver.findElement(By.id("entry_1359528431")).sendKeys("jufni4");
		driver.findElement(By.id("ss-submit")).click();
		System.setProperty(key, value)
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	}
}