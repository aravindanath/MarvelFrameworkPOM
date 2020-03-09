package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * 
 * @author aravindanathdm <dependency> 
 * 			<groupId>com.aventstack</groupId>
 *         <artifactId>extentreports</artifactId>
 *          <version>4.0.9</version>
 *         </dependency>
 *
 */

public class ExtentReport4Demo {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	public WebDriver driver;

	@BeforeTest
	public void setExtent() {
		htmlReporter = new ExtentHtmlReporter("./test-output/myReport.html");
		htmlReporter.config().setDocumentTitle("Automation Report for Regression Suite"); // Title of the report
		htmlReporter.config().setReportName("Functional Report for Amazon"); // name of the report
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Hostname", "LocalHost");
		extent.setSystemInfo("os", "macOS Mojave");
		extent.setSystemInfo("Tester Name", "ARAVINDA");
		extent.setSystemInfo("Browser", "Chrome");
	}

	@BeforeMethod
	public void setup() {

		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"/Users/aravindanathdm/Documents/Aravinda/chromedriver");

		driver = new ChromeDriver();
		System.out.println("Launch Browser");

		driver.get("https://www.amazon.com");
 

	}

	@Test
	public void amazonPage() throws IOException {

		test = extent.createTest("Amazon page");
		String title = driver.getTitle();
		System.out.println(title);
		test.info("============Test started=============");
		test.log(Status.PASS, "THis case pass");
		 
		Assert.assertEquals(title,title);

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent
																					// report
			String screenshotPath = getScreenshot(driver, result.getName());
			test.addScreenCaptureFromPath(screenshotPath);// adding screen shot
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
		}
		driver.quit();
	}

	@AfterTest
	public void endReport() {
		extent.flush();
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
}
