package demo;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
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
	private WebDriver driver;
	ITestContext myTestContext;
	DesiredCapabilities capabilities;
	
	@Parameters({ "browser-name", "platform-name", "browser-version", "hub" })
	@Test(alwaysRun = true)
	public void testTemp1(String browser_name, String platform_name,
			String browser_version, String hub, ITestContext myTestContext)
			throws Exception {
		// Enable logging
//		System.setProperty("webdriver.chrome.logfile", "/usr/local/bin/chromedriver.log");
//		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--no-sandbox");
		// On LINUX the "start-maximized" Chrome option does not expand
		// browser window to max screen size.
		options.addArguments(Arrays.asList("--window-size=1920,1080"));
		options.addArguments(Arrays.asList("--start-maximized"));
		capabilities.setPlatform(Platform.LINUX);
		capabilities.setBrowserName(browser_name); 
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		// Logging ----------------------------
//		LoggingPreferences logPrefs = new LoggingPreferences();
//		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
//		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
//
//		HashMap<String, Object> perfLogPrefs = new HashMap<String, Object>();
//		perfLogPrefs.put("traceCategories", "browser,devtools.timeline,devtools"); // comma-separated trace categories
//		perfLogPrefs.put("enableTimeline", true);
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("perfLoggingPrefs", perfLogPrefs);
//		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
//		capabilities.setVersion(browser_version);
//		capabilities.setCapability(FirefoxDriver.PROFILE, new FirefoxProfile());
		
//		if (platform_name.equalsIgnoreCase("linux")) {
//			capabilities.setPlatform(Platform.LINUX);
//		}

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

		driver = new RemoteWebDriver(new URL("http://ec2-52-33-199-234.us-west-2.compute.amazonaws.com:4444/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		baseUrl = "https://docs.google.com/";
		driver.get(baseUrl + "/forms/d/1xB5BbIf6ZIkhXsZrk0WyerGc-UcBDpCm26ydOFpYLYI/viewform");
		driver.findElement(By.id("entry_1359528431")).clear();
		driver.findElement(By.id("entry_1359528431")).sendKeys("jufni5");
		driver.findElement(By.id("ss-submit")).click();
//		System.setProperty(key, value)
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	}
}